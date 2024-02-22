package prev23.phase.asmgen;

import java.util.*;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.visitor.*;
import prev23.data.asm.*;
import java.lang.Math.*;
import prev23.common.report.*;


/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {

	public MemFrame frame;

	public ExprGenerator(MemFrame c){
		this.frame = c;
	}

	public MemTemp visit(ImcBINOP binOp, Vector v) {
		
		String regs = "";

		Vector<MemTemp> uses = new Vector<MemTemp>();

		MemTemp fst = binOp.fstExpr.accept(this, v);
		MemTemp snd = binOp.sndExpr.accept(this, v);
		uses.add(fst); uses.add(snd);
		
		Vector<AsmOPER> cmps = new Vector();

		MemTemp ret = new MemTemp();

		String op;
		switch(binOp.oper){
			case OR:
				op = "OR";
				break;
			case AND:
				op = "AND";
				break;
			case ADD:
				op = "ADD";
				break;
			case SUB:
				op = "SUB";
				break;
			case MUL:
				op = "MUL";
				break;
			case DIV:
				op = "DIV";
				break;
			case MOD:
				op = "DIV";
				break;
			default:
				op = "CMP";
				Vector<MemTemp> cmpV = new Vector();
				cmpV.add(ret);
				switch(binOp.oper){
					case EQU:
						cmps.add(
							new AsmOPER(
								"AND `d0,`s0,1",
								cmpV,
								cmpV,
								null
							)
						);
						cmps.add(
							new AsmOPER(
								"SUB `d0,`s0,1",
								cmpV,
								cmpV,
								null
							)
						);
						break;
					case NEQ:
						break;
					case GTH:
						cmps.add(new AsmOPER(
							"ADD `d0,`s0,1",
							cmpV,
							cmpV,
							null
						));
						cmps.add(new AsmOPER(
							"AND `d0,`s0,2",
							cmpV,
							cmpV,
							null
						));
						break;
					case LTH:
						cmps.add(new AsmOPER(
							"SR `d0,`s0,1",
							cmpV,
							cmpV,
							null
						));
						break;
					case LEQ:
						cmps.add(new AsmOPER(
							"SUB `d0,`s0,1",
							cmpV,
							cmpV,
							null
						));
						break;
					case GEQ:
						cmps.add(new AsmOPER(
							"ADD `d0,`s0,1",
							cmpV,
							cmpV,
							null
						));
						break;
					default:
						break;
				}
				break;
		}

		Vector<MemTemp> defs = new Vector<MemTemp>();

		defs.add(ret);

		v.add(
			new AsmOPER(
				op + " " + "`d0,`s0,`s1",
				uses,
				defs,
				null
			)
		);
		if(binOp.oper == ImcBINOP.Oper.MOD){
			v.add(
				new AsmOPER(
					"GET `d0,rR",
					null,
					defs,
					null
				)
			);
		}else if(cmps.size() > 0){
			for(AsmOPER o : cmps){
				v.add(o);
			}
		}
		return ret;
	}

	public MemTemp visit(ImcCALL call, Vector v) {

		Vector<MemLabel> jumps = new Vector<MemLabel>();
		int off = 0;
		for(int i = 0; i < call.args.size(); i++){
			Vector<MemTemp> uses = new Vector();
			uses.add(((ImcTEMP) call.args.get(i)).temp);

			v.add(
				new AsmOPER(
					"STO `s0,$255," + off,
					uses,
					null,
					null
				)
			);
			off += 8;
		}

		jumps.add(call.label);
		v.add(
			new AsmOPER(
				"PUSHJ 0," + call.label.name,
				null,
				null,
				jumps
			)
		);

		Vector<MemTemp> moveDefs = new Vector<MemTemp>();

		moveDefs.add(new MemTemp());

		v.add(
			new AsmOPER(
				"LDO `d0,$255,0",
				null,
				moveDefs,
				null
			)
		);

		return moveDefs.get(0);
	}

	public MemTemp visit(ImcCONST constant, Vector v) {
		Vector<MemTemp> defs = new Vector<MemTemp>();

		MemTemp ret = new MemTemp();

		defs.add(ret);
		v.add(
			new AsmOPER(
				"SET `d0," + Math.abs(constant.value),
				null,
				defs,
				null
			)
		);

		if(constant.value < 0){
			Vector<MemTemp> neg = new Vector();

			neg.add(ret);

			v.add(
				new AsmOPER(
					"NEG `d0,`s0",
					neg,
					(Vector<MemTemp>) neg.clone(),
					null
				)
			);
		}

		return ret; 
	}

	public MemTemp visit(ImcMEM mem, Vector v) {
		Vector<MemTemp> uses = new Vector();
		Vector<MemTemp> defs = new Vector();

		MemTemp ret = new MemTemp();

		uses.add(mem.addr.accept(this, v));
		defs.add(ret);

		v.add(
			new AsmOPER(
				"LDO `d0,`s0,0",
				uses,
				defs,
				null
			)
		);

		return ret;
	}

	public MemTemp visit(ImcNAME name, Vector v) {

		Vector<MemTemp> defs = new Vector<MemTemp>();

		MemTemp ret = new MemTemp();

		defs.add(ret);

		v.add(
			new AsmOPER(
				"LDA `d0," + name.label.name,
				null,
				defs,
				null
			)
		);
		return ret;
	}

	public MemTemp visit(ImcTEMP temp, Vector v) {
		return temp.temp;
	}

	public MemTemp visit(ImcUNOP unOp, Vector v) {
		
		Vector<MemTemp> uses = new Vector<MemTemp>();
		uses.add(unOp.subExpr.accept(this, v));

		Vector<MemTemp> defs = new Vector<MemTemp>();

		MemTemp ret = new MemTemp();

		defs.add(ret);

		String op;
		switch(unOp.oper){
			case NEG:
				op = "NEG `d0,`s0";
				break;
			case NOT:
				op = "NXOR `d0,`s0,0";
				break;
			default:
				op = "";
		}

		v.add(
			new AsmOPER(
				op,
				uses,
				defs,
				null
			)
		);
		return ret;
	}
}
