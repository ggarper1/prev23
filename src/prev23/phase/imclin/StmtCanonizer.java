package prev23.phase.imclin;

import java.util.*;

import prev23.common.report.*;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;

/**
 * Statement canonizer.
 */
public class StmtCanonizer implements ImcVisitor<Vector<ImcStmt>, Object> {

	ExprCanonizer exprCanonizer;

	public StmtCanonizer(){
		exprCanonizer = new ExprCanonizer(this);
	}

	//Expressions

	public Vector visit(ImcCJUMP cjump, Object arg) {
		Vector<ImcStmt> v = new Vector();
		ImcCJUMP newCJump = new ImcCJUMP(
			cjump.cond.accept(exprCanonizer, v),
			cjump.posLabel,
			cjump.negLabel
		);
		v.add(newCJump);
		return v;
	}
	
	public Vector visit(ImcJUMP jump, Object arg) {
		Vector<ImcStmt> v = new Vector();
		v.add(new ImcJUMP(jump.label));
		return v;
	}

	public Vector visit(ImcESTMT eStmt, Object arg) {
		Vector<ImcStmt> v = new Vector();
		ImcESTMT newEStmt = new ImcESTMT(eStmt.expr.accept(exprCanonizer, v));
		v.add(newEStmt);
		return v;
	}

	public Vector visit(ImcLABEL label, Object arg) {
		Vector<ImcStmt> v = new Vector();
		v.add(new ImcLABEL(label.label));
		return v;
	}

	public Vector visit(ImcMOVE move, Object arg) {
		Vector<ImcStmt> v = new Vector();
		ImcMOVE newMove = new ImcMOVE(
			move.dst.accept(exprCanonizer, v),
			move.src.accept(exprCanonizer, v)
		);
		v.add(newMove);
		return v;
	}

	public Vector visit(ImcSTMTS stmts, Object arg) {
		Vector<ImcStmt> v = new Vector();
		stmts.stmts.forEach((stmt)->{v.addAll(stmt.accept(this, arg));});
		return v;
	}

}
