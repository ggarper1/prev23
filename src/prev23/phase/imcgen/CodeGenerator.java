package prev23.phase.imcgen;

import prev23.phase.seman.*;
import prev23.phase.memory.*;
import prev23.phase.imcgen.*;

import prev23.common.report.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;

import prev23.data.mem.*;
import prev23.data.typ.*;
import prev23.data.ast.visitor.*;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import java.util.*;

/**
 * An abstract visitor of the intermediate code.
 * 
 * @author sliva
 *
 * @param <Object> The result the visitor produces.
 * @param <Arg>    The argument the visitor carries around.
 */
public class CodeGenerator extends AstFullVisitor<Object, Object> {

	int currDepth;
	MemTemp currFP;
	public CodeGenerator(){
		currDepth = 0;
		currFP = null;
	}

	//GENERAL
	
	public boolean check(AstTrees trees){
		return trees.size() == 0
			|| trees.get(0) instanceof AstMemDecl 
			|| trees.get(0) instanceof AstTypDecl;
	} 

	@Override //(DONE)
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		if(check(trees)){
			return null;
		}

		if(trees.get(0) instanceof AstFunDecl){
			for (AstTree t : trees){
				if (t != null) 	t.accept(this, arg);
			}
			return null;
		}

		int count = 1;
		for (AstTree t : trees){
			if (t != null)
				//if(count == trees.size()) 
			    t.accept(this, arg);
		}
		return null;
	}

	//DECLARATIONS
	
	@Override //(DONE)
	public Object visit(AstFunDecl funDecl, Object arg){
		MemTemp previous = currFP;
		currFP = Memory.frames.get(funDecl).FP;
		currDepth++;
		if(funDecl.stmt != null)
			funDecl.stmt.accept(this, null);
		currDepth--;
		currFP = previous;
		return null;
	}

	// EXPRESSIONS

	@Override //EX10 (DONE)
	public Object visit(AstArrExpr arrExpr, Object arg) {
		arrExpr.arr.accept(this, arg);
		arrExpr.idx.accept(this, arg);
		
		ImcBINOP biMult = new ImcBINOP(
			ImcBINOP.Oper.MUL, 
			ImcGen.exprImc.get(arrExpr.idx),
			new ImcCONST(SemAn.ofType.get(arrExpr).size())
		);
		ImcBINOP biAdd = new ImcBINOP(
			ImcBINOP.Oper.ADD,
			biMult,
			((ImcMEM) ImcGen.exprImc.get(arrExpr.arr)).addr
		);
		ImcGen.exprImc.put(arrExpr, new ImcMEM(biAdd)); 
		//It's putting a value
		return null;
	}

	@Override //A1, EX1, EX2, EX3
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		switch(atomExpr.type){
			case VOID:
				ImcGen.exprImc.put(atomExpr, new ImcCONST((long)1));//Question
				break;
			case CHAR:
				ImcGen.exprImc.put(
					atomExpr,
					new ImcCONST((long) atomExpr.value.charAt(1))
				);
				break;

			case INT:
				Long l;
				try{
					l = Long.parseLong(atomExpr.value);
				}catch(NumberFormatException e){
					l = Long.MAX_VALUE;
				}
				ImcGen.exprImc.put(
					atomExpr,
					new ImcCONST(l)
				);
				break;

			case BOOL:
				long i = 0;
				if(atomExpr.value.equals("true")){
					i = 1;
				}
				ImcGen.exprImc.put(atomExpr, new ImcCONST(i));
				break;

			case PTR:
				ImcGen.exprImc.put(atomExpr, new ImcCONST((long) 0));
				break;
			case STR:
				ImcGen.exprImc.put(
					atomExpr,
					new ImcNAME(Memory.strings.get(atomExpr).label)
				);
				break;
			default:
				throw new Report.InternalError();
		}
		
		return null;
	}

	@Override //EX5 (DONE)
	public Object visit(AstBinExpr binExpr, Object arg) {
		
		binExpr.fstExpr.accept(this, arg);
		binExpr.sndExpr.accept(this, arg);
		ImcBINOP.Oper oper;
		switch(binExpr.oper){
			case OR:
				oper = ImcBINOP.Oper.OR;
				break;
			case AND:
				oper = ImcBINOP.Oper.AND;
				break;
			case EQU:
				oper = ImcBINOP.Oper.EQU;
				break;
			case NEQ:
				oper = ImcBINOP.Oper.NEQ;
				break;
			case LTH:
				oper = ImcBINOP.Oper.LTH;
				break;
			case GTH:
				oper = ImcBINOP.Oper.GTH;
				break;
			case LEQ:
				oper = ImcBINOP.Oper.LEQ;
				break;
			case GEQ:
				oper = ImcBINOP.Oper.GEQ;
				break;
			case ADD:
				oper = ImcBINOP.Oper.ADD;
				break;
			case SUB:
				oper = ImcBINOP.Oper.SUB;
				break;
			case MUL:
				oper = ImcBINOP.Oper.MUL;
				break;
			case DIV:
				oper = ImcBINOP.Oper.DIV;
				break;
			case MOD:
				oper = ImcBINOP.Oper.MOD;
				break;
			default: throw new Report.Error("Something bad happened in CodeGenerator for ImcBINOP!");
		}
		ImcGen.exprImc.put(
			binExpr,
			new ImcBINOP(
				oper,
				ImcGen.exprImc.get(binExpr.fstExpr),
				ImcGen.exprImc.get(binExpr.sndExpr)
			)
		);
		return null;
	}

	@Override //EX12
	public Object visit(AstCallExpr callExpr, Object arg) {
		Vector<Long> offsets = new Vector();
		Vector<ImcExpr> args = new Vector();
		MemFrame frame = Memory.frames.get(
			(AstFunDecl) SemAn.declaredAt.get(callExpr)
		);
		int aux = currDepth;
		int depth = frame.depth;
		ImcExpr fp = new ImcTEMP(currFP);
		if(aux - 1 == depth){
			fp = new ImcMEM(fp);
		}
		args.add(fp);
		offsets.add(Long.valueOf(0));
		for (AstExpr e : callExpr.args){
			if (e != null)
			    e.accept(this, arg);
			    args.add(ImcGen.exprImc.get(e));
			    if(e instanceof AstName && !(e instanceof AstCallExpr) &&
			    	Memory.accesses.get(
			    		(AstMemDecl) SemAn.declaredAt.get((AstName) e)
			    	) instanceof MemRelAccess)
			    	{
			    	offsets.add(
			    		Long.valueOf(
			    			((MemRelAccess) Memory.accesses.get(
			    				(AstMemDecl) SemAn.declaredAt.get(
			    					(AstName) e
			    				)
			    			)).offset 
			    		)
			    	);
			    }else{
			    	offsets.add(Long.valueOf(0));
			    }			    
		}
		ImcGen.exprImc.put(
			callExpr,
			new ImcCALL(
				frame.label,
				offsets,
				args
			)
		);
		return null;
	}

	@Override //EX14, EX15 (DONE)
	public Object visit(AstCastExpr castExpr, Object arg) {
		castExpr.expr.accept(this, arg);
		if(SemAn.ofType.get(castExpr.expr).getClass().equals(
				SemAn.isType.get(castExpr.type).getClass())){

			ImcGen.exprImc.put(
				castExpr,
				ImcGen.exprImc.get(castExpr.expr)
			);
			return null;
		}
		if(SemAn.isType.get(castExpr.type) instanceof SemChar){
			ImcGen.exprImc.put(
				castExpr,
				new ImcBINOP(
					ImcBINOP.Oper.MOD,
					ImcGen.exprImc.get(castExpr.expr),
					new ImcCONST((long) 256)
				)
			);
		}else{
			ImcGen.exprImc.put(
				castExpr,
				ImcGen.exprImc.get(castExpr.expr)
			);
		}
		return null;
	}

	@Override //EX8
	public Object visit(AstDelExpr delExpr, Object arg) {
		delExpr.expr.accept(this, arg);
		Vector<Long> offsets = new Vector();
		offsets.add(Long.valueOf((long) 0));
		Vector<ImcExpr> expr = new Vector();
		expr.add(ImcGen.exprImc.get(delExpr.expr));
		ImcGen.exprImc.put(
			delExpr,
			new ImcCALL(
				new MemLabel("del"),
				offsets,
				expr
			)
		);
		return null;
	}

	@Override //A2
	public Object visit(AstNameExpr nameExpr, Object arg) {
		MemAccess acc = Memory.accesses.get(
			(AstMemDecl) SemAn.declaredAt.get(nameExpr)
		);
		if(acc instanceof MemAbsAccess){
			ImcGen.exprImc.put(
				nameExpr,
				new ImcMEM(new ImcNAME(((MemAbsAccess) acc).label))
			);
		}else{
			int depth = ((MemRelAccess) acc).depth;
			if(SemAn.declaredAt.get(nameExpr) instanceof AstCmpDecl) depth = 0;
			ImcExpr r = new ImcTEMP(currFP);
			int aux = currDepth;
			while(aux > depth){
				r = new ImcMEM(r);
				aux--;
			}
			ImcBINOP bin = new ImcBINOP(
				ImcBINOP.Oper.ADD,
				new ImcCONST(((MemRelAccess) acc).offset),
				r
			);
			ImcGen.exprImc.put(
				nameExpr,
				new ImcMEM(bin)
			);
		}
		return null;
	}

	@Override //EX8 (DONE)
	public Object visit(AstNewExpr newExpr, Object arg) {
		Vector<Long> offsets = new Vector();
		offsets.add(Long.valueOf((long) 0));
		Vector<ImcExpr> expr = new Vector();
		expr.add(new ImcCONST(SemAn.isType.get(newExpr.type).size()));
		ImcGen.exprImc.put(
			newExpr,
			new ImcCALL(
				new MemLabel("new"),
				offsets,
				expr
			)
		);
		return null;
	}

	@Override //EX4, EX6 (DONE)
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		pfxExpr.expr.accept(this,arg);
		switch(pfxExpr.oper){
			case ADD:
				ImcGen.exprImc.put(pfxExpr, ImcGen.exprImc.get(pfxExpr.expr));
				break;
			case SUB:
				ImcGen.exprImc.put(
					pfxExpr,
					new ImcUNOP(
						ImcUNOP.Oper.NEG,
						ImcGen.exprImc.get(pfxExpr.expr)
					)
				);
				break;
			case NOT:
				ImcGen.exprImc.put(
					pfxExpr,
					new ImcUNOP(
						ImcUNOP.Oper.NOT,
						ImcGen.exprImc.get(pfxExpr.expr)
					)
				);
				break;
			case PTR:
				ImcGen.exprImc.put(
					pfxExpr, 
					((ImcMEM) ImcGen.exprImc.get(pfxExpr.expr)).addr
				);
				break;
			default: throw new Report.InternalError();
		}
		return null;
	}

	@Override //EX11
	public Object visit(AstRecExpr recExpr, Object arg) {
		recExpr.rec.accept(this, arg);
		recExpr.comp.accept(this, arg);
		
		ImcBINOP add = new ImcBINOP(
			ImcBINOP.Oper.ADD,
			((ImcMEM) ImcGen.exprImc.get(recExpr.rec)).addr,
			new ImcCONST(
				((MemRelAccess) Memory.accesses.get((AstMemDecl) SemAn.declaredAt.get(recExpr.comp))).offset
			)
		);
		
		ImcGen.exprImc.put(recExpr, new ImcMEM(add));
		return null;
	}

	@Override  //EX6
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		sfxExpr.expr.accept(this, arg);
		ImcGen.exprImc.put(sfxExpr, new ImcMEM(ImcGen.exprImc.get(sfxExpr.expr)));
		return null;
	}

	// STATEMENTS

	@Override //ST2
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		assignStmt.src.accept(this, arg);
		assignStmt.dst.accept(this, arg);
			ImcGen.stmtImc.put(
				assignStmt,
				new ImcMOVE(
					ImcGen.exprImc.get(assignStmt.dst),
					ImcGen.exprImc.get(assignStmt.src)
				)
			);
		return null;
	}
 
	@Override //ST7 (DONE)
	public Object visit(AstDeclStmt declStmt, Object arg) {
		declStmt.decls.accept(this, arg);
		declStmt.stmt.accept(this, arg);
		ImcGen.stmtImc.put(declStmt, ImcGen.stmtImc.get(declStmt.stmt));
		return null;
	}

	@Override //ST1 (DONE)
	public Object visit(AstExprStmt exprStmt, Object arg) {
		exprStmt.expr.accept(this, arg);
		ImcGen.stmtImc.put(
			exprStmt,
			new ImcESTMT(ImcGen.exprImc.get(exprStmt.expr))
		);
		return null;
	}

	@Override //ST3, ST4 (DONE)
//<<CJUMP(L1,L2)-----L1-----(then Code)-----L2>> if then
//<<CJUMP(L1,L2)--L1--(then Code)--JUMP(L3)--L2--(else Code)--L3>> if then else
	public Object visit(AstIfStmt ifStmt, Object arg) {
		ifStmt.cond.accept(this, arg);
		ifStmt.thenStmt.accept(this, arg);
		
		Vector<ImcStmt> v = new Vector(); 
		
		MemLabel l1 = new MemLabel();
		MemLabel l2 = new MemLabel();
		
		if(ifStmt.elseStmt == null){
			v.add(
				new ImcCJUMP(
					ImcGen.exprImc.get(ifStmt.cond),
					l1,
					l2
				)
			);
			v.add(new ImcLABEL(l1));
			v.add(ImcGen.stmtImc.get(ifStmt.thenStmt));
			v.add(new ImcLABEL(l2));
		}else{
			ifStmt.elseStmt.accept(this, arg);
			
			MemLabel l3 = new MemLabel();
			
			v.add(
				new ImcCJUMP(
					ImcGen.exprImc.get(ifStmt.cond),
					l1,
					l2
				)
			);
			v.add(new ImcLABEL(l1));
			v.add(ImcGen.stmtImc.get(ifStmt.thenStmt));
			v.add(new ImcJUMP(l3));
			v.add(new ImcLABEL(l2));
			v.add(ImcGen.stmtImc.get(ifStmt.elseStmt));
			v.add(new ImcLABEL(l3));
		}
		
		ImcGen.stmtImc.put(ifStmt, new ImcSTMTS(v));
		return null;
	}

	@Override //ST8 (DONE)
	public Object visit(AstStmts stmts, Object arg) {
		stmts.stmts.accept(this, arg);

		Vector<ImcStmt> stmtVector = new Vector();
		stmts.stmts.forEach((statement)->{
			stmtVector.add(ImcGen.stmtImc.get(statement));
		});

		ImcGen.stmtImc.put(
			stmts,
			new ImcSTMTS(stmtVector)
		);
		return null;
	}

	@Override //ST5, ST6 (DONE)
//<<L1----CJUMP(L2,L3)----L2----(while Code)----JUMP(L1)----L3>> while
	public Object visit(AstWhileStmt whileStmt, Object arg) {
		whileStmt.cond.accept(this, arg);
		whileStmt.bodyStmt.accept(this, arg);
		
		MemLabel l1 = new MemLabel();
		MemLabel l2 = new MemLabel();
		MemLabel l3 = new MemLabel();
		
		Vector<ImcStmt> v = new Vector();
		
		v.add(new ImcLABEL(l1));
		v.add(
			new ImcCJUMP(
				ImcGen.exprImc.get(whileStmt.cond),
				l2,
				l3
			)
		);
		v.add(new ImcLABEL(l2));
		v.add(ImcGen.stmtImc.get(whileStmt.bodyStmt));
		v.add(new ImcJUMP(l1));
		v.add(new ImcLABEL(l3));
		
		ImcGen.stmtImc.put(whileStmt, new ImcSTMTS(v));
		return null;
	}

}