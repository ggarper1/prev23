package prev23.phase.seman;

import java.util.Vector;
import prev23.common.report.*;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.typ.*;
import prev23.data.ast.visitor.*;
import java.util.Hashtable;

public class TypeResolver extends AstFullVisitor<Object, /* TODO */ Object> {

    private Hashtable<AstTypDecl, Boolean> hash;
    private boolean ptr;
    
       
    public TypeResolver(){
    	hash = new Hashtable<AstTypDecl, Boolean>();
    	ptr = false;
    }
    
	// GENERAL PURPOSE
    
    public boolean checkTreeOfTyp(AstTrees<? extends AstTree> trees){
        return trees.size() > 0 && trees.get(0) instanceof AstTypDecl;
    }
    
    public Object visitTypTree(AstTrees<? extends AstTree> trees, Object arg){
    	//first = true;
        for (AstTree t : trees){
  	        if (t != null){
        	    t.accept(this, arg);
 	        }
 	        hash.clear();
        }
        return null;
    }
    
    public Object visitFunDeclTree(AstTrees<? extends AstTree> trees, Object arg){
        for (AstTree t : trees){
  	        if (t != null){
        	    t.accept(this, arg);
 	        }
        }
        for (AstTree t : trees){
  	        if (t != null){
        	    checkFunStmt((AstFunDecl) t, arg);
 	        }
        }
        return null;
    }
    
	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {  
        if(checkTreeOfTyp(trees)){
            visitTypTree(trees, arg);
            return null;
        }
        if(trees.size() > 0 && trees.get(0) instanceof AstFunDecl){
            visitFunDeclTree(trees, arg);
            return null;
        }
        for (AstTree t : trees){
  	        if (t != null){
        	    t.accept(this, arg);
 	        }
        }
        return null;
	}

	// DECLARATIONS
	
	public Object checkFunStmt(AstFunDecl funDecl, Object arg){
	    if (funDecl.stmt != null){
			funDecl.stmt.accept(this, arg);
			if(!(SemAn.ofType.get(funDecl.stmt) instanceof SemBool
				|| SemAn.ofType.get(funDecl.stmt) instanceof SemChar
				|| SemAn.ofType.get(funDecl.stmt) instanceof SemInt
				|| SemAn.ofType.get(funDecl.stmt) instanceof SemVoid
				|| SemAn.ofType.get(funDecl.stmt) instanceof SemPtr)){
			
				throw new Report.Error(funDecl.location(), "Function's statement type has to be void, bool, char, int or pointer!");
			}
		    if(SemAn.isType.get(funDecl.type).getClass() != SemAn.ofType.get(funDecl.stmt).getClass()){
		        throw new Report.Error(funDecl.type.location(), "Function return type and statement have to be of same type!");
		    }else{
		    	if(SemAn.ofType.get(funDecl.stmt) instanceof SemPtr && 
		    	((SemPtr) SemAn.isType.get(funDecl.type)).baseType.getClass() !=
		    	((SemPtr) SemAn.ofType.get(funDecl.stmt)).baseType.getClass()){
		    	
		    		throw new Report.Error(funDecl.type.location(), "Function return pointer type and statement pointer type do not have same base types!");
		    	}
		    }
		}
		return null;
	}
	
	@Override
	public Object visit(AstCmpDecl cmpDecl, Object arg) {
		if (cmpDecl.type != null)
			cmpDecl.type.accept(this, arg);
		
		if(SemAn.isType.get(cmpDecl.type) instanceof SemVoid){
			throw new Report.Error(cmpDecl.location(), "Component can't be of type void!");
		}
		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		if (funDecl.pars != null)
			funDecl.pars.accept(this, arg);
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);
			
		if(!(SemAn.isType.get(funDecl.type) instanceof SemBool
			|| SemAn.isType.get(funDecl.type) instanceof SemChar
			|| SemAn.isType.get(funDecl.type) instanceof SemInt
			|| SemAn.isType.get(funDecl.type) instanceof SemVoid
			|| SemAn.isType.get(funDecl.type) instanceof SemPtr)){
			
			throw new Report.Error(funDecl.location(), "Function's return type has to be void, bool, char, int or pointer!");	
		}
		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Object arg) {
		if (parDecl.type != null)
			parDecl.type.accept(this, arg);
		if(!(SemAn.isType.get(parDecl.type) instanceof SemBool
			|| SemAn.isType.get(parDecl.type) instanceof SemChar
			|| SemAn.isType.get(parDecl.type) instanceof SemInt
			|| SemAn.isType.get(parDecl.type) instanceof SemPtr)){
			
			throw new Report.Error(parDecl.location(), "Parameter has to be of type bool, char, int or pointer!");
		}
		return null;
	}

	public void print(){
		Report.info("-----------------------------------");
		hash.forEach((key, value)->{
			Report.info(key.name+": "+value.toString());
		});
		Report.info("-----------------------------------\n");
	}

	@Override
	public Object visit(AstTypDecl typDecl, Object arg) {
		if(hash.containsKey(typDecl) && !ptr){
			throw new Report.Error(typDecl.location(), "Cyclic types!");
		}else if(hash.containsKey(typDecl) && ptr){
			SemName newType = new SemName(typDecl.name);
			newType.define(new SemPtr(new SemVoid()));
			SemAn.declaresType.put(typDecl, newType);
			return null;
		}else{
			hash.put(typDecl, Boolean.FALSE);
		}
		if (typDecl.type != null){
			typDecl.type.accept(this, arg);
		}
		SemName newType = new SemName(typDecl.name);
		newType.define(SemAn.isType.get(typDecl.type));
		SemAn.declaresType.put(typDecl, newType);
		
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		if (varDecl.type != null)
			varDecl.type.accept(this, arg);
		if(SemAn.isType.get(varDecl.type) instanceof SemVoid){
			throw new Report.Error(varDecl.location(), "Variable cannot be of type void!");
		}
		return null;
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);
		if(SemAn.ofType.get(arrExpr.arr) instanceof SemArr){
			if(SemAn.ofType.get(arrExpr.idx) instanceof SemInt){
				SemAn.ofType.put(arrExpr, ((SemArr) SemAn.ofType.get(arrExpr.arr)).elemType);
			}else{
				throw new Report.Error(arrExpr.location(), "Index of array is not an int!");
			}
		}else{
			throw new Report.Error(arrExpr.location(), "Tried to access element of non array type!");
		}
		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
	    switch(atomExpr.type){
	        case CHAR:
	            SemAn.ofType.put(atomExpr, new SemChar());
	            break;
	        case INT:
	            SemAn.ofType.put(atomExpr, new SemInt());
	            break;
	        case BOOL:
	            SemAn.ofType.put(atomExpr, new SemBool());
	            break;
	        case PTR:
	                SemAn.ofType.put(atomExpr, new SemPtr(new SemVoid()));
	            break;
	        case STR:
	            SemAn.ofType.put(atomExpr, new SemPtr(new SemChar()));
	            break;
	        default:
	            SemAn.ofType.put(atomExpr, new SemVoid());
	    }
	    return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Object arg) {
		if (binExpr.fstExpr != null)
			binExpr.fstExpr.accept(this, arg);
		if (binExpr.sndExpr != null)
			binExpr.sndExpr.accept(this, arg);
		switch(binExpr.oper){
		    case AND:
		    case OR:
		        if(SemAn.ofType.get(binExpr.fstExpr) instanceof SemBool &&
		            SemAn.ofType.get(binExpr.sndExpr) instanceof SemBool){
		            SemAn.ofType.put(binExpr, new SemBool());
		        }else{
		            throw new Report.Error(binExpr.location(),
		            "'|' and  '&' operators require boolean types on both sides of the operation!");
		        }

		        break;
		    case ADD:
		    case SUB:
		    case MUL:
		    case DIV:
		    case MOD:
		        if(SemAn.ofType.get(binExpr.fstExpr) instanceof SemInt &&
		            SemAn.ofType.get(binExpr.sndExpr) instanceof SemInt){
		            SemAn.ofType.put(binExpr, new SemInt());
		        }else{
		        	throw new Report.Error(binExpr.location(),
		            "'+', '-', '*', '/' and '%' operators require int types on both sides of the operation!");
		        }
		        break;
		    case EQU:
		    case NEQ:
		    	
		    	if((SemAn.ofType.get(binExpr.fstExpr) instanceof SemBool
		    		&& SemAn.ofType.get(binExpr.sndExpr) instanceof SemBool)
		    		|| (SemAn.ofType.get(binExpr.fstExpr) instanceof SemInt
		    		&& SemAn.ofType.get(binExpr.sndExpr) instanceof SemInt)
		    		|| (SemAn.ofType.get(binExpr.fstExpr) instanceof SemChar
		    		&& SemAn.ofType.get(binExpr.sndExpr)instanceof SemChar)
		    		|| (SemAn.ofType.get(binExpr.fstExpr) instanceof SemPtr
		    		&& SemAn.ofType.get(binExpr.sndExpr) instanceof SemPtr
		    		&& (((SemPtr)SemAn.ofType.get(binExpr.fstExpr)).baseType.getClass().equals(
		    		((SemPtr)SemAn.ofType.get(binExpr.sndExpr)).baseType.getClass())))){
		    		
		    			SemAn.ofType.put(binExpr, new SemBool());		    	
		    	}else{
		    		throw new Report.Error(binExpr.location(), "Operands used with '==' and '!=' operators have to be of same type and char, bool, int or pointers of same base type!");
		    	}
		    	break;
		    case LTH:
		    case GTH:
		    case LEQ:
		    case GEQ:
		    	if(SemAn.ofType.get(binExpr.fstExpr).getClass().equals(
		    	SemAn.ofType.get(binExpr.sndExpr).getClass())){
		    		if(SemAn.ofType.get(binExpr.fstExpr) instanceof SemInt
		    		|| SemAn.ofType.get(binExpr.fstExpr) instanceof SemChar
		    		|| (SemAn.ofType.get(binExpr.fstExpr) instanceof SemPtr
		    		&& (((SemPtr)SemAn.ofType.get(binExpr.fstExpr)).baseType.getClass().equals(
		    		((SemPtr)SemAn.ofType.get(binExpr.sndExpr)).baseType.getClass())))){
		    		
		    			SemAn.ofType.put(binExpr, new SemBool());
		    		}else{
		    			throw new Report.Error(binExpr.location(), "Operands used with '<', '>', '<=' and '>=' operators have to be both int, char or pointer types!");
		    		}
		    	
		    	}else{
		    		throw new Report.Error(binExpr.location(), "Operands used with '<', '>', '<=' and '>=' operators have to be of same type!");
		    	}
		    	break;
		}
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
		if (callExpr.args != null)
			callExpr.args.accept(this, arg);
		if(SemAn.declaredAt.get(callExpr) instanceof AstFunDecl){
		    AstFunDecl f = (AstFunDecl) SemAn.declaredAt.get(callExpr);
		    if(callExpr.args.size() != f.pars.size()){
		        throw new Report.Error(callExpr.location(), "Function call's parameters differ in length from function's definition!");
		    }
		    for(int i = 0; i < f.pars.size(); i++){
		    	if(SemAn.ofType.get(callExpr.args.get(i)).getClass() != 
		            SemAn.isType.get(f.pars.get(i).type).getClass()){
		                throw new Report.Error(callExpr.args.get(i).location(), "Parameter type in function call differs from that defined in function declaration!");
		        }
		    }
		    SemAn.ofType.put(callExpr, SemAn.isType.get(((AstFunDecl) SemAn.declaredAt.get(callExpr)).type));
		}else{
		    throw new Report.Error(callExpr.location(), "Identifier in function call is not from a function!");
		}
		return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Object arg) {
		if (castExpr.expr != null)
			castExpr.expr.accept(this, arg);
		if (castExpr.type != null)
			castExpr.type.accept(this, arg);
		if(SemAn.ofType.get(castExpr.expr) instanceof SemChar ||
			SemAn.ofType.get(castExpr.expr) instanceof SemInt ||
	        SemAn.ofType.get(castExpr.expr) instanceof SemPtr){
			    if(SemAn.isType.get(castExpr.type) instanceof SemChar ||
				    SemAn.isType.get(castExpr.type) instanceof SemInt ||
				    SemAn.isType.get(castExpr.type) instanceof SemPtr){
			        
			        SemAn.ofType.put(castExpr, SemAn.isType.get(castExpr.type));
			    }else{
				    throw new Report.Error(castExpr.location(), "Type in cast '(expr:_)' has to be of type int, char or ptr!");
			    }
		}else{
			throw new Report.Error(castExpr.location(), "Expression in cast '(_:type)' has to be of type int, char or ptr!");
		}
		return null;
	}

	@Override
	public Object visit(AstDelExpr delExpr, Object arg) {
		if (delExpr.expr != null)
			delExpr.expr.accept(this, arg);
		if(!(SemAn.ofType.get(delExpr.expr) instanceof SemPtr)){
			throw new Report.Error(delExpr.location(), "del expression has to be applied to a pointer!");
		}
		SemAn.ofType.put(delExpr, new SemVoid());
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
		SemAn.ofType.put(
			nameExpr,
			SemAn.isType.get(((AstMemDecl) SemAn.declaredAt.get(nameExpr)).type));
		return null;
	}

	@Override
	public Object visit(AstNewExpr newExpr, Object arg) {
		if (newExpr.type != null)
			newExpr.type.accept(this, arg);
			SemAn.ofType.put(newExpr, new SemPtr(SemAn.isType.get(newExpr.type)));
		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		if (pfxExpr.expr != null)
			pfxExpr.expr.accept(this, arg);
			switch(pfxExpr.oper){
			case ADD:
	        case SUB:
	            if(SemAn.ofType.get(pfxExpr.expr) instanceof SemInt){
	                SemAn.ofType.put(pfxExpr, new SemInt());
	            }else{
	                throw new Report.Error(pfxExpr.location(), "Used '+' or '-' operator with none int type!");
	            }
	            break;
	        case NOT:
	            if(SemAn.ofType.get(pfxExpr.expr) instanceof SemBool){
	                SemAn.ofType.put(pfxExpr, new SemBool());
	            }else{
	                throw new Report.Error(pfxExpr.location(), "Used '!' operator with none boolean type!");
	            }
	            break;
	        default:
	            SemAn.ofType.put(pfxExpr,  new SemPtr(SemAn.ofType.get(pfxExpr.expr)));
	    }
		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);
		if(SemAn.ofType.get(recExpr.rec) instanceof SemRec){
		        SemRec sem = (SemRec) SemAn.ofType.get(recExpr.rec);
		        for(int i = 0; i < sem.numCmps(); i++){
		            if(((SemName) sem.cmpType(i)).name.equals(recExpr.comp.name)){
						SemAn.ofType.put(recExpr.comp, ((SemName) sem.cmpType(i)).type());
		            	SemAn.ofType.put(recExpr, ((SemName) sem.cmpType(i)).type());
		                return null;
		            }
		        }
		        throw new Report.Error(recExpr.location(), "No component with such name in record type!");
		}else{
		    throw new Report.Error(recExpr.location(), "Not a record type!");
		}
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		if (sfxExpr.expr != null)
			sfxExpr.expr.accept(this, arg);
			if(SemAn.ofType.get(sfxExpr.expr) instanceof SemPtr){
			    SemAn.ofType.put(sfxExpr, ((SemPtr) SemAn.ofType.get(sfxExpr.expr)).baseType);
			}else{
			    throw new Report.Error(sfxExpr.location(), "Used '^' as a sufix operator with non pointer type!");
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
		if(SemAn.ofType.get(assignStmt.dst).getClass() ==
			SemAn.ofType.get(assignStmt.src).getClass()){
		    		if(SemAn.ofType.get(assignStmt.dst) instanceof SemBool
		    			|| SemAn.ofType.get(assignStmt.dst) instanceof SemInt
		    			|| SemAn.ofType.get(assignStmt.dst) instanceof SemChar
		    			|| SemAn.ofType.get(assignStmt.dst) instanceof SemPtr){
		    			
		    			SemAn.ofType.put(assignStmt, new SemVoid());
					}else{
					throw new Report.Error(assignStmt.location(), "Both expressions in assign statement have to be of type bool, int, char or pointer!");
					}
		}else{
			throw new Report.Error(assignStmt.location(), "The types of the elements in an assign statement have to be the same!");
		}
		return null;
	}

	@Override
	public Object visit(AstDeclStmt declStmt, Object arg) {
		if (declStmt.decls != null)
			declStmt.decls.accept(this, arg);
		if (declStmt.stmt != null)
			declStmt.stmt.accept(this, arg);
		SemAn.ofType.put(declStmt, SemAn.ofType.get(declStmt.stmt));
		return null;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Object arg) {
		if (exprStmt.expr != null)
			exprStmt.expr.accept(this, arg);
		SemAn.ofType.put(exprStmt, SemAn.ofType.get(exprStmt.expr));
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
		if(SemAn.ofType.get(ifStmt.cond) instanceof SemBool){
			SemAn.ofType.put(ifStmt, new SemVoid());
		}else{
			throw new Report.Error(ifStmt.location(), "Condition in 'if' statement is not of type bool!");
		}
		return null;
	}

	@Override
	public Object visit(AstStmts stmts, Object arg) {
		if (stmts.stmts != null)
			stmts.stmts.accept(this, arg);
		SemAn.ofType.put(stmts, SemAn.ofType.get(stmts.stmts.get(stmts.stmts.size() - 1)));
		return null;
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Object arg) {
		if (whileStmt.cond != null)
			whileStmt.cond.accept(this, arg);
		if (whileStmt.bodyStmt != null)
			whileStmt.bodyStmt.accept(this, arg);
		if(SemAn.ofType.get(whileStmt.cond) instanceof SemBool){
			SemAn.ofType.put(whileStmt, new SemVoid());
		}else{
			throw new Report.Error(whileStmt.location(), "Condition in 'while' statement is not of type bool!");
		}
		return null;
	}

	// TYPES

	@Override
	public Object visit(AstArrType arrType, Object arg){
		if (arrType.elemType != null){
			arrType.elemType.accept(this, arg);
			if(SemAn.isType.get(arrType.elemType) instanceof SemVoid){
			    throw new Report.Error(arrType.location(), "Array of type void!");
			}
		}
		if (arrType.numElems != null){
			arrType.numElems.accept(this, arg);
			if(SemAn.ofType.get(arrType.numElems) instanceof SemInt
			    && arrType.numElems instanceof AstAtomExpr){
			    if(Integer.parseInt(((AstAtomExpr) arrType.numElems).value) > 0 
			        && Integer.parseInt(((AstAtomExpr) arrType.numElems).value) < Math.pow(2,63) - 1 ){
			        SemAn.isType.put(arrType, new SemArr(SemAn.isType.get(arrType.elemType),
			            Integer.parseInt(((AstAtomExpr) arrType.numElems).value)));
			    }else{
    			    throw new Report.Error(arrType.location(), "Invalid value for array size!");
			    }
			}else{
			    throw new Report.Error(arrType.location(), "Array size has to be of type int and positive!");
			}		
		}
		
		return null;
	}

	@Override
	public Object visit(AstAtomType atomType, Object arg) {
	    switch(atomType.type){
	        case CHAR:
	            SemAn.isType.put(atomType, new SemChar());
	            break;
	        case INT:
	            SemAn.isType.put(atomType, new SemInt());
	            break;
	        case BOOL:
	            SemAn.isType.put(atomType, new SemBool());
	            break;
	        default:
	            SemAn.isType.put(atomType, new SemVoid());
	    }
	    return null;
	}

	@Override
	public Object visit(AstNameType nameType, Object arg) {
		if(SemAn.declaredAt.get(nameType) instanceof AstTypDecl){
			AstTypDecl aux = (AstTypDecl) SemAn.declaredAt.get(nameType);
			if(SemAn.declaresType.get(aux)!=null){
				SemAn.isType.put(nameType, SemAn.declaresType.get(aux).type());
			}else{
				aux.accept(this, arg);
				SemAn.isType.put(nameType, SemAn.declaresType.get(aux).type());
			}
	    }else{
	    	throw new Report.Error(nameType.location(), "Identifier should be an identifier of a type yet it isn't!");
	    }	
		return null;
	}

	@Override
	public Object visit(AstPtrType ptrType, Object arg) {
		if (ptrType.baseType != null){
			ptr = true;
			ptrType.baseType.accept(this, arg);
			if(SemAn.isType.get(ptrType.baseType) == null){
				SemAn.isType.put(ptrType, new SemPtr(new SemVoid()));
			}else{
				SemAn.isType.put(ptrType, new SemPtr(SemAn.isType.get(ptrType.baseType)));
			}
			ptr = false;
		}
		return null;
	}

	@Override
	public Object visit(AstRecType recType, Object arg) {
		if (recType.comps != null){
			recType.comps.accept(this, arg);
		}
		Vector<SemType> v = new Vector();
		
		for(int i = 0; i < recType.comps.size(); i++){
			SemName aux = new SemName(recType.comps.get(i).name);
			aux.define(SemAn.isType.get(recType.comps.get(i).type));
			v.add(aux);
		}
		SemAn.isType.put(recType, new SemRec(v));
		return null;
	}

}