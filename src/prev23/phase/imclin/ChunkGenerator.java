package prev23.phase.imclin;

import java.util.*;

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
import prev23.data.ast.tree.stmt.*;


import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;

import prev23.data.lin.*;

public class ChunkGenerator extends AstFullVisitor<Object, Vector<ImcStmt>> {
	
	StmtCanonizer stmtCanonizer;
	
	int scope;

	boolean noAdd;

	public ChunkGenerator(){
		scope = 0;
		stmtCanonizer = new StmtCanonizer();
		noAdd = false;
	}
	
	// GENERAL PURPOSE

	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Vector<ImcStmt> arg) {
		if(trees.size() == 0
			|| (trees.get(0) instanceof AstTypDecl)
			|| (trees.get(0) instanceof AstCmpDecl)
			|| (trees.get(0) instanceof AstParDecl)){
			return null;
		}
		for (AstTree t : trees)
			if (t != null)
				t.accept(this, arg);
		return null;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstVarDecl varDecl, Vector<ImcStmt> arg){

		if(scope > 0) return null;
		MemAbsAccess acc = (MemAbsAccess) Memory.accesses.get(varDecl);
		ImcLin.addDataChunk(new LinDataChunk(acc));
		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Vector<ImcStmt> arg) {
		boolean prev = noAdd;
		noAdd = false;
		scope++;
		MemFrame frame = Memory.frames.get(funDecl);
		Vector<ImcStmt> vector = new Vector();
		vector.add(new ImcLABEL(frame.label));
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, vector);
		if(!(SemAn.isType.get(funDecl.type) instanceof SemVoid)){
			if(vector.lastElement() instanceof ImcESTMT){
				ImcESTMT aux = (ImcESTMT) vector.remove(vector.size() - 1);
				vector.add(new ImcMOVE(
					new ImcTEMP(frame.RV),
					aux.expr
				));
			}
		}else{
			vector.add(new ImcMOVE(
				new ImcTEMP(frame.RV),
				new ImcCONST(Long.valueOf(0))
			));
		}
		MemLabel exit = new MemLabel();
		vector.add(new ImcJUMP(exit));
		vector.add(new ImcLABEL(exit));
		
		ImcLin.addCodeChunk(
			new LinCodeChunk(
				frame,
				vector,
				frame.label,
				exit
			)
		);
		scope--;
		noAdd = prev;
		return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Vector<ImcStmt> arg) {
		if(noAdd){return null;}
		arg.addAll(ImcGen.stmtImc.get(assignStmt).accept(stmtCanonizer, null));
		return null;
	}

	@Override
	public Object visit(AstDeclStmt declStmt, Vector<ImcStmt> arg) {
		declStmt.decls.accept(this, arg);
		declStmt.stmt.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Vector<ImcStmt> arg) {
		if(noAdd){return null;}
		arg.addAll(ImcGen.stmtImc.get(exprStmt).accept(stmtCanonizer, null));
		return null;
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Vector<ImcStmt> arg) {
		boolean prev = noAdd;
		noAdd = true;
		ifStmt.thenStmt.accept(this,arg);
		if(ifStmt.elseStmt != null) ifStmt.elseStmt.accept(this, arg);
		noAdd = prev;
		if(noAdd){return null;}
		arg.addAll(ImcGen.stmtImc.get(ifStmt).accept(stmtCanonizer, null));
		return null;
	}

	@Override
	public Object visit(AstStmts stmts, Vector<ImcStmt> arg) {
		stmts.stmts.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Vector<ImcStmt> arg) {
		boolean prev = noAdd;
		noAdd = true;
		whileStmt.bodyStmt.accept(this,arg);
		noAdd = prev;
		if(noAdd){return null;}
		arg.addAll(ImcGen.stmtImc.get(whileStmt).accept(stmtCanonizer, null));
		return null;
	}

}
