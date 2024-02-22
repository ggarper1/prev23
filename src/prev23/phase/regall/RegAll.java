package prev23.phase.regall;

import java.util.*;

import prev23.common.report.*;

import prev23.data.mem.*;
import prev23.data.asm.*;
import prev23.phase.*;
import prev23.phase.asmgen.*;

/**
 * Register allocation.
 */
public class RegAll extends Phase {

	/** Mapping of temporary variables to registers. */
	public static final HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();
	
	public Graph<MemTemp> graph;

	private Stack<MemTemp> tempStack;

	private Stack<Boolean> isSpill;

	private int regs;


	public class Graph<T>{

		private HashMap<T, HashSet<Node<T>>> hidden;

		private HashMap<T,Node<T>> nodes;

		private HashMap<Node<T>, HashSet<Node<T>>> connections;

		public Graph(){
			this.nodes = new HashMap();
			this.connections = new HashMap();
			this.hidden = new HashMap();
		}

		public Collection<T> nodes(){return nodes.keySet();}

		public Collection<T> hidden(){return hidden.keySet();}

		public void hide(T t){
			if(!nodes.containsKey(t)) return;
			Node<T> n = nodes.get(t);
			HashSet<Node<T>> aux = connections.get(n);
			removeNode(t);
			aux.add(n);
			hidden.put(t, aux);
		}

		public void unHide(T t) throws Exception{
			if(!hidden.containsKey(t)) throw new Exception();

			Node<T> n = null;
			HashSet<Node<T>> set = hidden.get(t);
			
			Vector<T> v = new Vector();
			
			for(Node<T> aux : set){
				if(aux.value().equals(t)){
					n = aux;
				}else{
					v.add(aux.value());
				}
			}

			hidden.remove(t);

			addNode(n.value(), v);
		}

		public Vector<T> getConnectionsH(T t){
			Vector<T> v = new Vector();

			for(Node<T> aux : hidden.get(t)){
				if(!aux.value().equals(t)) v.add(aux.value());
			}
			return v;
		}

		public void connect(T t, Collection<T> c){
			Node n = nodes.get(t);
			if(n == null){
				n = new Node(t);
				nodes.put(t, n);
				connections.put(n, new HashSet());
			}
			for(T aux : c){
				Node toAdd = nodes.get(aux);
				if(toAdd == null){
					toAdd = new Node(t);
					nodes.put(t, toAdd);
					connections.put(toAdd, new HashSet());
				}
				connections.get(n).add(toAdd);
				connections.get(toAdd).add(n);
			}
		}

		public boolean areConnected(T t1, T t2){
			return connections.get(nodes.get(t1)).contains(nodes.get(t2));
		}

		public void removeNode(T t){
			if(!nodes.containsKey(t)) return;
			Node del = nodes.get(t);
			nodes.remove(t);

			connections.get(del).forEach((Node n)->{
				connections.get(n).remove(del);
			});

			connections.remove(del);
		}

		public void addNode(T t, Collection<T> c){
			Node toAdd;
			if(nodes.containsKey(t)){
				toAdd = nodes.get(t);
			}else{
				toAdd = new Node(t);
				nodes.put(t, toAdd);
				connections.put(toAdd, new HashSet());
			}

			c.forEach((T aux)->{
				if(!aux.equals(t)){
					Node connect = nodes.get(aux);
					if(connect == null){
						connect = new Node(aux);
						nodes.put(aux, connect);
						connections.put(connect, new HashSet());
					}
					connections.get(toAdd).add(connect);
					connections.get(connect).add(toAdd);
				}
			});
		}

		public boolean isEmpty(){
			return nodes.size() == 0;
		}

		public int degree(T t){
			return connections.get(nodes.get(t)).size();
		}

		public T getNodeMinDegree(){
			Node<T> ret = null;
			int min = nodes.size();
			for(Node n : connections.keySet()){
				int aux = connections.get(n).size();
				if(aux <= min){
					min = aux;
					ret = n;
				}
			}
			return ret.value();
		}

		public T getNodeMaxDegree(){
			Node<T> ret = null;
			int max = -1;
			for(Node n : connections.keySet()){
				int aux = connections.get(n).size();
				if(aux >= max){
					max = aux;
					ret = n;
				}
			}
			return ret.value();
		}

		public void debugGraph(){
			for(Node<T> n : connections.keySet()){
				if(connections.get(n).contains(n)){
					Report.info("Error here!");
				}
			}
		}
	}

	public class Node<T>{

		private static int i = 0;
		
		private T value;
		private int index;

		public Node(T t){
			this.value = t;
			this.index = i;
			i++;
		}

		public T value(){
			return value;
		}

		@Override
		public int hashCode(){
			return this.index;
		}

	}

	public RegAll(Integer i){
		super("regall");
		regs = i;
	}

	public void allocate(){

		for(Code code : AsmGen.codes){
			
			tempToReg.put(code.frame.FP, Integer.valueOf(254));
			tempToReg.put(code.frame.RV, Integer.valueOf(0));

			createGraph(code);

			tempStack = new Stack();
			isSpill = new Stack();
			while(!graph.isEmpty()){
				emptyGraph(code);
			}

			refillGraph(code);

			for(AsmInstr i : code.instrs){
				for(MemTemp t : i.defs()){
					if(tempToReg.get(t) == null){
						tempToReg.put(t, Integer.valueOf(0));
					}
				}
				for(MemTemp t : i.uses()){
					if(tempToReg.get(t) == null){
						tempToReg.put(t, Integer.valueOf(0));
					}
				}
			}

		}
	}

	public void handleSpill(MemTemp t, Code code){
		Report.info("There was a spill!");//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	public void refillGraph(Code code){
		while(!tempStack.isEmpty()){
			MemTemp t = tempStack.pop();
			if(isSpill.pop().booleanValue()){
				handleSpill(t, code);
			}else{
				boolean[] array = new boolean[253];
				for(MemTemp  aux : graph.getConnectionsH(t)){
					if(tempToReg.containsKey(aux)){
						array[tempToReg.get(aux).intValue()] = true;
					}
				}
				
				for(int i = 0; i < regs; i++){
					if(!array[i]){
						tempToReg.put(t, Integer.valueOf(i));
						break;
					}
				}
			}
		}
	}

	public void debugTempToReg(){
		Report.info("-------------Debugging tempToReg-------------");
		for(MemTemp t : tempToReg.keySet()){
			Report.info(t.toString() + ": " + tempToReg.get(t).toString());
		}
		Report.info("--------Finished debugging tempToReg---------");
	}

	public void debugTempStack(){
		Report.info("-------------Debugging tempStack-------------");
		for(MemTemp t : tempStack){
			Report.info(t.toString());
		}
		Report.info("--------Finished debugging tempStack---------");
	}

	public void emptyGraph(Code code){
		MemTemp t = graph.getNodeMinDegree();
		if(graph.degree(t) < regs){
			isSpill.push(Boolean.FALSE);
		}else{
			t = graph.getNodeMaxDegree();
			isSpill.push(Boolean.TRUE);
		}
		graph.hide(t);
		tempStack.push(t);
	}

	public void createGraph(Code code){
		graph = new Graph();
		for(AsmInstr i : code.instrs){
			HashSet<MemTemp> aux = i.out();
			for(MemTemp t : aux){
				graph.addNode(t, aux);
			}
		}
		graph.removeNode(code.frame.FP);
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
				logger.addAttribute("code", instr.toString(tempToReg));
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
