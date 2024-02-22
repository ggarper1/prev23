package prev23.phase.asmgen;

import java.util.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;
import prev23.data.mem.*;
import prev23.data.asm.*;
import prev23.common.report.*;

/**
 * Machine code generator for statements.
 */
public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {

	private ExprGenerator exprGen;

	public StmtGenerator(MemFrame c){
		exprGen = new ExprGenerator(c);
	}

    public Vector visit(ImcCJUMP cjump, Object visArg) {
		Vector<AsmInstr> v = new Vector();

		Vector<MemTemp> uses = new Vector<MemTemp>();

		MemTemp t = cjump.cond.accept(exprGen, v);
/*
		String op;
		if(cjump.cond instanceof ImcBINOP){
			ImcBINOP bin = (ImcBINOP) cjump.cond;
			uses.add(t);

			switch(bin.oper){
				case EQU:
					op = "BNZ";
					break;
				case NEQ:
					op = "BZ";
					break;
				case LTH:
					op = "BNN";
					break;
				case GTH:
					op = "BNP";
					break;
				case LEQ:
					op = "BP";
					break;
				case GEQ:
					op = "BN";
					break;
				default://ORs and ANDs
					op = "BNZ";
			}
			op = op + " `s0," + cjump.negLabel.name;
		}else{
			op = "BZ `s0," + cjump.negLabel.name;
			uses.add(t);
		}*/

		String op = "BZ `s0," + cjump.negLabel.name;
		uses.add(t);
		
		Vector<MemLabel> jumps = new Vector<MemLabel>();
		jumps.add(cjump.posLabel);

		v.add(new AsmOPER(
			op,
			uses,
			null,
			jumps
		));

		return v;
	}

	public Vector visit(ImcESTMT eStmt, Object visArg) {
		Vector<AsmInstr> v = new Vector();
		eStmt.expr.accept(exprGen, v);
		return v;
	}

	public Vector visit(ImcJUMP jump, Object visArg) {
		Vector<AsmInstr> v = new Vector();
		
		Vector<MemLabel> jumps = new Vector<MemLabel>();
		jumps.add(jump.label);

		v.add(new AsmOPER(
			"JMP " + jump.label.name,
			null,
			null,
			jumps
		));
		return v;
	}

	public Vector visit(ImcLABEL label, Object visArg) {
		Vector<AsmInstr> v = new Vector<AsmInstr>();
		v.add(new AsmLABEL(label.label));
		return v;
	}

	public Vector visit(ImcMOVE move, Object visArg) {
		Vector<AsmInstr> v = new Vector<AsmInstr>();

		MemTemp src = move.src.accept(exprGen, v);
		MemTemp dst = move.dst.accept(exprGen, v);

		Vector<MemTemp> uses = new Vector<MemTemp>();
		Vector<MemTemp> defs = new Vector<MemTemp>();
		
		String op;
		if(move.dst instanceof ImcMEM){
			op = "STO `s0,`s1,0";
			v.remove(v.lastElement());
			uses.add(src);
			uses.add(v.lastElement().defs().get(0));
		}else{//If it's not ImcMEM it's ImcTEMP
			defs.add(dst);
			uses.add(src);
			op = "SET `d0,`s0";
		}

		v.add(new AsmOPER(op, uses, defs, null));
		return v;
	}
}
