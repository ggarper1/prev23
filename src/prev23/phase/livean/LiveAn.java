package prev23.phase.livean;

import java.util.*;

import prev23.common.report.*;

import prev23.data.mem.*;
import prev23.data.asm.*;
import prev23.phase.*;
import prev23.phase.asmgen.*;

/**
 * Liveness analysis.
 */
public class LiveAn extends Phase {

	Vector<AsmInstr> vector;
	Hashtable<String, Integer> labels;

	public LiveAn() {
		super("livean");
		vector = new Vector();
		labels = new Hashtable();
	}

	public boolean addIns(int i){
		boolean ret = false;
		AsmInstr instr = vector.get(i);
		
		int aux = instr.out().size();
		instr.addInTemps(vector.get(i).out());
		if(instr instanceof AsmOPER){
			instr.defs().forEach((e)->{
				((AsmOPER)instr).removeFromIn(e);
			});
		}
		if(aux != instr.out().size()) ret = true;
		
		return ret;
	}

	public boolean addOuts(int i){
		boolean ret = false;
		AsmInstr instr = vector.get(i);
		if(instr.jumps() != null){
			for(MemLabel l : instr.jumps()){
				try{
					int index;
					index = labels.get(l.name);
					int aux = vector.get(index).in().size();
					instr.addOutTemp(vector.get(index).in());
					if(aux != vector.get(index).in().size()) ret = true;
				}catch(NullPointerException e){}
			}
		}
		if(((AsmOPER) instr).instr().equals("JMP")){
			return ret;
		}
		
		int aux = instr.out().size();
		instr.addOutTemp(vector.get(getNext(i)).in());
		if(aux != instr.out().size()) ret = true;
		

		return ret;
	}

	private int getNext(int i){
		if(i == vector.size() - 1) return i;
		return i + 1;
	}

	private int getPrev(int i){
		if(i == 0) return i;
		return i - 1;
	}

	private void fstGroup(){
		for(int i = vector.size() - 1; i > 0; i--){
			AsmInstr instr = vector.get(i);
			if(instr instanceof AsmLABEL){
				instr.addOutTemp(new HashSet(vector.get(getNext(i)).out()));
				instr.addInTemps(new HashSet(vector.get(getNext(i)).in()));
				labels.put(((AsmLABEL)instr).toString(), Integer.valueOf(i));
			}else{
				instr.addInTemps(new HashSet(instr.uses()));
				instr.addOutTemp(new HashSet(vector.get(getNext(i)).in()));
				instr.addInTemps(instr.out());
				if(instr instanceof AsmOPER){
					instr.defs().forEach((e)->{
						((AsmOPER)instr).removeFromIn(e);
					});
				}
			}
		}
	}

	private boolean group(){
		boolean repeat = false;
		for(int i = vector.size() - 1; i > 0; i--){
			AsmInstr instr = vector.get(i);
			if(instr instanceof AsmLABEL){
				instr.addOutTemp(new HashSet(vector.get(getNext(i)).out()));
				instr.addInTemps(new HashSet(vector.get(getNext(i)).in()));
			}else{
				repeat = addOuts(i) || repeat;
				repeat = addIns(i) || repeat;
			}
		}
		return repeat;
	}

	public static void print(Vector<AsmInstr> vector){
		vector.forEach((i)->{
			Report.info("Instr: " + i.toString() +"\t\touts: " + i.out().toString() + "\tins:" + i.in().toString());
		});
	}

	public void analysis() {
		// TODO
		for (Code c : AsmGen.codes){
			labels.clear();
			vector = c.instrs;
			fstGroup();
			while(group()){}
		}
	}

	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString());
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
