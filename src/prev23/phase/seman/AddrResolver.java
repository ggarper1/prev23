package prev23.phase.seman;

import prev23.common.report.*;
import prev23.data.typ.*;
import prev23.data.ast.visitor.*;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;

public class AddrResolver extends AstFullVisitor<Object, /* TODO */ Object> {

	// GENERAL PURPOSE

	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		for (AstTree t : trees)
			if (t != null)
				t.accept(this, arg);
		return null;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		if (funDecl.stmt != null)
			funDecl.stmt.accept(this, arg);
		return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);
		SemAn.isAddr.put(arrExpr, SemAn.isAddr.get(arrExpr.arr));
		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		SemAn.isAddr.put(atomExpr, false);
		return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Object arg) {
		if (binExpr.fstExpr != null)
			binExpr.fstExpr.accept(this, arg);
		if (binExpr.sndExpr != null)
			binExpr.sndExpr.accept(this, arg);
		SemAn.isAddr.put(binExpr, false);
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		if (callExpr.args != null)
			callExpr.args.accept(this, arg);
		SemAn.isAddr.put(callExpr, false);
		return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Object arg) {
		if (castExpr.expr != null)
			castExpr.expr.accept(this, arg);
		if (castExpr.type != null)
			castExpr.type.accept(this, arg);
		SemAn.isAddr.put(castExpr, false);
		return null;
	}

	@Override
	public Object visit(AstDelExpr delExpr, Object arg) {
		if (delExpr.expr != null)
			delExpr.expr.accept(this, arg);
		SemAn.isAddr.put(delExpr, false);
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		if(SemAn.declaredAt.get(nameExpr) instanceof AstVarDecl
			|| SemAn.declaredAt.get(nameExpr) instanceof AstParDecl){
			SemAn.isAddr.put(nameExpr, true);
		}else{
			SemAn.isAddr.put(nameExpr, false);
		}
		return null;
	}

	@Override
	public Object visit(AstNewExpr newExpr, Object arg) {
		if (newExpr.type != null)
			newExpr.type.accept(this, arg);
		SemAn.isAddr.put(newExpr, false);
		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		if (pfxExpr.expr != null)
			pfxExpr.expr.accept(this, arg);
		SemAn.isAddr.put(pfxExpr, SemAn.isAddr.get(pfxExpr.expr));
		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);
		if (recExpr.comp != null)
			recExpr.comp.accept(this, arg);
		SemAn.isAddr.put(recExpr, SemAn.isAddr.get(recExpr.rec));
		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		if (sfxExpr.expr != null)
			sfxExpr.expr.accept(this, arg);
		if(sfxExpr.oper == AstSfxExpr.Oper.PTR){
			SemAn.isAddr.put(sfxExpr, SemAn.isAddr.get(sfxExpr.expr));
		}else{
			SemAn.isAddr.put(sfxExpr, false);
		}
		return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		if (assignStmt.dst != null)
			assignStmt.dst.accept(this, arg);
		if (assignStmt.src != null)
			assignStmt.src.accept(this, arg);
		if(!SemAn.isAddr.get(assignStmt.dst)){
			throw new Report.Error(assignStmt.location(), "Expression on the left of assign statement is not valid (not an Lvalue)!");
		}
		return null;
	}

	@Override
	public Object visit(AstDeclStmt declStmt, Object arg) {
		if (declStmt.decls != null)
			declStmt.decls.accept(this, arg);
		if (declStmt.stmt != null)
			declStmt.stmt.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Object arg) {
		if (exprStmt.expr != null)
			exprStmt.expr.accept(this, arg);
		return null;
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
	}

	@Override
	public Object visit(AstStmts stmts, Object arg) {
		if (stmts.stmts != null)
			stmts.stmts.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Object arg) {
		if (whileStmt.cond != null)
			whileStmt.cond.accept(this, arg);
		if (whileStmt.bodyStmt != null)
			whileStmt.bodyStmt.accept(this, arg);
		return null;
	}

	// TYPES

	@Override
	public Object visit(AstArrType arrType, Object arg) {
		if (arrType.elemType != null)
			arrType.elemType.accept(this, arg);
		if (arrType.numElems != null)
			arrType.numElems.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstAtomType atomType, Object arg) {
		return null;
	}

	@Override
	public Object visit(AstNameType nameType, Object arg) {
		return null;
	}

	@Override
	public Object visit(AstPtrType ptrType, Object arg) {
		if (ptrType.baseType != null)
			ptrType.baseType.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstRecType recType, Object arg) {
		if (recType.comps != null)
			recType.comps.accept(this, arg);
		return null;
	}

}
