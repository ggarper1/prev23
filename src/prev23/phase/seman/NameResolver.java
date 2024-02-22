package prev23.phase.seman;

import prev23.common.report.*;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.visitor.*;

/**
 * Name resolver.
 * 
 * Name resolver connects each node of a abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the name resolver are stored in
 * {@link prev23.phase.seman.SemAn#declaredAt}.
 */
public class NameResolver extends AstFullVisitor<Object, /* TODO */ Object> {
	
	/* TODO */
	public SymbTable tTable;
	public SymbTable fTable;
	public SymbTable vpTable;
	public SymbTable cmpTable;
	
	public NameResolver(){
		tTable = new SymbTable();
		fTable = new SymbTable();
		vpTable = new SymbTable();
		cmpTable = new SymbTable();
	}
	
	// GENERAL PURPOSE
	
	/*public void newScope(AstTree t){
		if(!(t instanceof AstTrees) || ((AstTrees) t).size() == 0) return;
		AstTrees aux = (AstTrees) t;
		if(aux.get(0) instanceof AstTypDecl){
			tTable.newScope();			
		}else if(aux.get(0) instanceof AstFunDecl){
			fTable.newScope();
		}else if(!(aux.get(0) instanceof AstCmpDecl)){
			vpTable.newScope();
		}
		
	}*/
	
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		for (AstTree t : trees){
			if (t != null) {
				if(t instanceof AstTrees){checkNames((AstTrees) t, arg);}
				t.accept(this, arg);
			}
		}
		return null;
	}
	
	public void checkNames(AstTrees<? extends AstTree> tree, Object arg){
		if(tree.size()==0) return;
		if(tree.get(0) instanceof AstFunDecl){
			for (AstTree decl : tree){
				try{
					fTable.ins(((AstFunDecl) decl).name, (AstFunDecl) decl);
				}catch(SymbTable.CannotInsNameException e){
						throw new Report.Error(
							decl.location(),
							"Function with name '" + ((AstFunDecl) decl).name + "' already declared!");
				}
			}
			fTable.newScope();
		}else if(tree.get(0) instanceof AstCmpDecl){
			for (AstTree decl : tree){
				try{
					cmpTable.ins(((AstCmpDecl) decl).name, (AstCmpDecl) decl);
				}catch(SymbTable.CannotInsNameException e){
					throw new Report.Error(
						decl.location(),
						"Component with this name already exists!");
				}
			}
			cmpTable.newScope();
		}else if(tree.get(0) instanceof AstTypDecl){
			for (AstTree decl : tree){
				try{
					tTable.ins(((AstTypDecl) decl).name, (AstTypDecl) decl);
				}catch(SymbTable.CannotInsNameException e){
					throw new Report.Error(
						decl.location(),
						"Type with name '" + ((AstTypDecl) decl).name + "' already declared!");
				}
			}
			tTable.newScope();
		}else if(tree.get(0) instanceof AstVarDecl){
			for (AstTree decl : tree){
				try{
					vpTable.ins(((AstVarDecl) decl).name, (AstVarDecl) decl);
				}catch(SymbTable.CannotInsNameException e){
					throw new Report.Error(
						decl.location(),
						"Variable with name '" + ((AstVarDecl) decl).name +	 "' already declared!");
				}
			}
			vpTable.newScope();
		}else{
			for (AstTree decl : tree){
				try{
					vpTable.ins(((AstParDecl) decl).name, (AstParDecl) decl);
				}catch(SymbTable.CannotInsNameException e){
					throw new Report.Error(
						decl.location(),
						"More than one parameter with same name!");
				}
			}
		}
	}

	// DECLARATIONS 

	//MODIFY
	@Override
	public Object visit(AstCmpDecl cmpDecl, Object arg) {
		if (cmpDecl.type != null)
			cmpDecl.type.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);
				
		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
			
		vpTable.newScope();
		
		checkNames(funDecl.pars, arg);
				
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, arg);
			
		vpTable.oldScope();
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstParDecl parDecl, Object arg) {
		if (parDecl.type != null)
			parDecl.type.accept(this, arg);
		return parDecl;
		//throw new Report.InternalError();
	}
	
	@Override
	public Object visit(AstTypDecl typDecl, Object arg) {
		if (typDecl.type != null)
			typDecl.type.accept(this, arg);
			
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		if (varDecl.type != null)
			varDecl.type.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstBinExpr binExpr, Object arg) {
		if (binExpr.fstExpr != null)
			binExpr.fstExpr.accept(this, arg);
		if (binExpr.sndExpr != null)
			binExpr.sndExpr.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		try{
			SemAn.declaredAt.put(callExpr, fTable.fnd(callExpr.name));
			if (callExpr.args != null)
				callExpr.args.accept(this, arg);
			return null;
		}catch(SymbTable.CannotFndNameException e){
			throw new Report.Error(callExpr.location(),
				"Function '" + callExpr.name + "' not declared!");
		}
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstCastExpr castExpr, Object arg) {
		if (castExpr.expr != null)
			castExpr.expr.accept(this, arg);
		if (castExpr.type != null)
			castExpr.type.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstDelExpr delExpr, Object arg) {
		if (delExpr.expr != null)
			delExpr.expr.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		try{
			SemAn.declaredAt.put(nameExpr, vpTable.fnd(nameExpr.name));
			return null;
		}catch(SymbTable.CannotFndNameException e){
			throw new Report.Error(nameExpr.location(),
				"Variable '" + nameExpr.name + "' not declared!"); 
		}
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstNewExpr newExpr, Object arg) {
		if (newExpr.type != null)
			newExpr.type.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		if (pfxExpr.expr != null)
			pfxExpr.expr.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);
		if (recExpr.comp != null){
			try{
				SemAn.declaredAt.put(recExpr.comp, cmpTable.fnd(recExpr.comp.name));
			}catch(SymbTable.CannotFndNameException e){
				throw new Report.Error(
					recExpr.comp.location(),
					"Component '" + recExpr.comp.name + "' not declared!"); 
			}
		}
		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		if (sfxExpr.expr != null)
			sfxExpr.expr.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	// STATEMENTS
	@Override
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		if (assignStmt.dst != null)
			assignStmt.dst.accept(this, arg);
		if (assignStmt.src != null)
			assignStmt.src.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}
	
	public void eraseScopes(int f, int v, int t){
		int fE = fTable.currDepth() - f;
		int vE = vpTable.currDepth() - v;
		int tE = tTable.currDepth() - t;
		while(fE > 0){
			fTable.oldScope(); fE--;
		}
		while(vE > 0){
			vpTable.oldScope(); vE--;
		}
		while(tE > 0){
			tTable.oldScope(); tE--;
		}
	}

	@Override
	public Object visit(AstDeclStmt declStmt, Object arg) {
		vpTable.newScope();
		tTable.newScope();
		fTable.newScope();
		int f = fTable.currDepth();
		int v = vpTable.currDepth();
		int t = tTable.currDepth();
		if (declStmt.decls != null)
			declStmt.decls.accept(this, arg);
		if (declStmt.stmt != null)
			declStmt.stmt.accept(this, arg);
		eraseScopes(f, v, t);
		vpTable.oldScope();
		tTable.oldScope();
		fTable.oldScope();
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Object arg) {
		if (exprStmt.expr != null)
			exprStmt.expr.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Object arg) {
		if (ifStmt.cond != null)
			ifStmt.cond.accept(this, arg);
		if (ifStmt.thenStmt != null)
			ifStmt.thenStmt.accept(this, arg);
		if (ifStmt.elseStmt != null)
			ifStmt.elseStmt.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstStmts stmts, Object arg) {
		if (stmts.stmts != null)
			stmts.stmts.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Object arg) {
		if (whileStmt.cond != null)
			whileStmt.cond.accept(this, arg);
		if (whileStmt.bodyStmt != null)
			whileStmt.bodyStmt.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	// TYPES
	@Override
	public Object visit(AstArrType arrType, Object arg) {
		if (arrType.elemType != null)
			arrType.elemType.accept(this, arg);
		if (arrType.numElems != null)
			arrType.numElems.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstAtomType atomType, Object arg) {
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstNameType nameType, Object arg) {
		try{
			SemAn.declaredAt.put(nameType, tTable.fnd(nameType.name));
			return null;
		}catch(SymbTable.CannotFndNameException e){
			throw new Report.Error(nameType.location(),
				"Type '" + nameType.name + "' not declared!"); 
		}
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstPtrType ptrType, Object arg) {
		if (ptrType.baseType != null)
			ptrType.baseType.accept(this, arg);
		return null;
		//throw new Report.InternalError();
	}

	@Override
	public Object visit(AstRecType recType, Object arg) {
		if (recType.comps != null){
    		checkNames(recType.comps, arg);
			recType.comps.accept(this, arg);
		}
		cmpTable.newScope();
		return null;
		//throw new Report.InternalError();
	}

}