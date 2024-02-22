package prev23.phase.imclin;

import java.util.*;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;

import prev23.common.report.*;

/**
 * Expression canonizer.
 */
public class ExprCanonizer implements ImcVisitor<ImcExpr, Vector<ImcStmt>> {

	private StmtCanonizer stmtCanonizer;

	public ExprCanonizer(StmtCanonizer s){
		stmtCanonizer = s;

	}

	public ImcExpr visit(ImcBINOP binOp, Vector arg) {
		ImcBINOP newBinOp = new ImcBINOP(
			binOp.oper,
			binOp.fstExpr.accept(this, arg),
			binOp.sndExpr.accept(this, arg)
		);
		return newBinOp;
	}

	public ImcExpr visit(ImcCONST constant, Vector arg) {
		return new ImcCONST(constant.value);
	}

	public ImcExpr visit(ImcMEM mem, Vector arg) {
		ImcMEM newMem = new ImcMEM(
			mem.addr.accept(this, arg)
		);
		return newMem;
	}

	public ImcExpr visit(ImcUNOP unOp, Vector arg) {
		ImcUNOP newUnop = new ImcUNOP(
			unOp.oper,
			unOp.subExpr.accept(this, arg)
		);
		return newUnop;
	}

	public ImcExpr visit(ImcSEXPR sExpr, Vector arg) {
		//?
		return null;//Errors: 1.what is this for? 2.The Sl has to be in the call?
	}

	public ImcExpr visit(ImcTEMP temp, Vector arg) {
		return new ImcTEMP(temp.temp);
	}

	public ImcExpr visit(ImcNAME name, Vector arg) {
		return new ImcNAME(name.label);
	}

	public ImcExpr visit(ImcCALL call, Vector arg) {
		Vector<ImcMOVE> vector = new Vector();
		call.args.forEach((expr)->{
			ImcTEMP t = new ImcTEMP(new MemTemp());
			arg.add(
				new ImcMOVE(
					t,
					expr.accept(this, arg)
				)
			);
			vector.add((ImcMOVE) arg.lastElement());
		});
		Vector<ImcExpr> temps = new Vector();
		vector.forEach((e)->{
			temps.add(e.dst);
		});
		ImcTEMP result = new ImcTEMP(new MemTemp());
		arg.add(
			new ImcMOVE(
				result,
				new ImcCALL(
					call.label,
					new Vector<Long>(call.offs),
					temps
				)
			)
		);
		return result;
	}
}
