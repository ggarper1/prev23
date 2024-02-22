package prev23.phase.memory;

import prev23.common.report.*;
import prev23.data.typ.*;
import prev23.data.ast.visitor.*;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.mem.*;
import prev23.phase.seman.*;
import java.util.ArrayList;

/**
 * Computing memory layout: frames and accesses.
 */
public class MemEvaluator extends AstFullVisitor<Object, Object> {
    
    public int fScope;
    public ArrayList<Number> argsSizeArray;
	public long offset;
    
    public MemEvaluator(){
        this.fScope = 0;
        this.argsSizeArray = new ArrayList<Number>(); argsSizeArray.add(0, (long) 0);
    }

	// GENERAL PURPOSE

	@Override
	public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
		long aux;
		if(arg == null){aux=(long) 0;}else{aux = (long) arg;}
		for (AstTree t : trees){
			if (t != null){
				if(t instanceof AstExpr){
					t.accept(this, aux);
				}else if(t instanceof AstTrees){
					aux = (long) t.accept(this, aux);
				}else if(t instanceof AstStmt){
					long r = (long) t.accept(this, null);
					if(r > aux) aux = r;
				}else{
					aux += (long) t.accept(this, aux);
				}
			}
		}
		return aux;
	}

	// DECLARATIONS

	@Override
	public Object visit(AstCmpDecl cmpDecl, Object arg) {
		if (cmpDecl.type != null)
			cmpDecl.type.accept(this, arg);
		
		Memory.accesses.put(
		    cmpDecl,
		    new MemRelAccess(
		        SemAn.isType.get(cmpDecl.type).size(),
		        (long) arg,
		        0
		    )
		);
				
		return SemAn.isType.get(cmpDecl.type).size();
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		offset = 0;
	    fScope++; argsSizeArray.add(fScope, (long) 0);
	    long locsSize = 0;
        		
		if (funDecl.type != null)
			funDecl.type.accept(this, arg);
		if (funDecl.pars != null)
			funDecl.pars.accept(this, (long) 0);	
		if (funDecl.stmt != null){
			locsSize = (long) funDecl.stmt.accept(this, (long) 0);
		}
		MemLabel label;
		if(fScope-1>0){
			label = new MemLabel();
		}else{label = new MemLabel(funDecl.name);}
		Memory.frames.put(
		    funDecl,
		    new MemFrame(
		        label,
		        fScope-1,
		        locsSize,
		        (long) argsSizeArray.get(fScope) + 8
		    )
		);
		argsSizeArray.add(fScope, (long) 0);
		fScope--;
				
		return (long) 0;
	}

	@Override
	public Object visit(AstParDecl parDecl, Object arg) {
		if (parDecl.type != null)
			parDecl.type.accept(this, arg);
			
		Memory.accesses.put(
		    parDecl,
		    new MemRelAccess(
		        SemAn.isType.get(parDecl.type).size(),
		        (long) arg + SemAn.isType.get(parDecl.type).size(),
		        fScope
		    )
		);
		
		return SemAn.isType.get(parDecl.type).size();
	}

	@Override
	public Object visit(AstTypDecl typDecl, Object arg) {
		if (typDecl.type != null)
			typDecl.type.accept(this, arg);
		return (long) 0;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		if (varDecl.type != null)
			varDecl.type.accept(this, arg);
			
		if(fScope > 0){
			offset = offset - SemAn.isType.get(varDecl.type).size();
            Memory.accesses.put(
                varDecl,
                new MemRelAccess(
                    SemAn.isType.get(varDecl.type).size(),
                    offset,
                    fScope
                )
            	);
            return SemAn.isType.get(varDecl.type).size();   
		}else{
		    Memory.accesses.put(
		        varDecl,
		        new MemAbsAccess(
		            SemAn.isType.get(varDecl.type).size(),
		            new MemLabel(varDecl.name)
		        )
		    );
		    return (long) 0;
		}
	}

	// EXPRESSIONS

	@Override
	public Object visit(AstArrExpr arrExpr, Object arg) {
		if (arrExpr.arr != null)
			arrExpr.arr.accept(this, arg);
		if (arrExpr.idx != null)
			arrExpr.idx.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
	
	    if(atomExpr.type == AstAtomExpr.Type.STR){
	        Memory.strings.put(
	            atomExpr,
	            new MemAbsAccess(
	                atomExpr.value.length() * ((SemPtr) SemAn.ofType.get(atomExpr)).baseType.size(),
	                new MemLabel(atomExpr.value),
	                atomExpr.value
	            )
	        );
	    }
		return null;
	}

	@Override
	public Object visit(AstBinExpr binExpr, Object arg) {
		if (binExpr.fstExpr != null)
			binExpr.fstExpr.accept(this, arg);
		if (binExpr.sndExpr != null)
			binExpr.sndExpr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Object arg) {
	    long size = (long) callExpr.args.size() * 8;
		if((long) argsSizeArray.get(fScope) < size){
            argsSizeArray.add(fScope, size);
        }
        callExpr.args.accept(this, null);
        return null;
	}

	@Override
	public Object visit(AstCastExpr castExpr, Object arg) {
		if (castExpr.expr != null)
			castExpr.expr.accept(this, arg);
		if (castExpr.type != null)
			castExpr.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstDelExpr delExpr, Object arg) {
		if (delExpr.expr != null)
			delExpr.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Object arg) {
	    return null;
	}

	@Override
	public Object visit(AstNewExpr newExpr, Object arg) {
		if (newExpr.type != null)
			newExpr.type.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Object arg) {
		if (pfxExpr.expr != null)
			pfxExpr.expr.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstRecExpr recExpr, Object arg) {
		if (recExpr.rec != null)
			recExpr.rec.accept(this, arg);
		if (recExpr.comp != null)
			recExpr.comp.accept(this, arg);
		return null;
	}

	@Override
	public Object visit(AstSfxExpr sfxExpr, Object arg) {
		if (sfxExpr.expr != null)
			sfxExpr.expr.accept(this, arg);
		return null;
	}

	// STATEMENTS

	@Override
	public Object visit(AstAssignStmt assignStmt, Object arg) {
		if (assignStmt.dst != null)
			assignStmt.dst.accept(this, arg);
		if (assignStmt.src != null)
			assignStmt.src.accept(this, arg);
		return (long) 0;
	}

	@Override
	public Object visit(AstDeclStmt declStmt, Object arg) {
	    long locsSize = 0;
		long aux = 0;
	    
	    if (declStmt.decls != null)
		    locsSize = (long) declStmt.decls.accept(this, locsSize);
	    if (declStmt.stmt != null)
		    aux = (long) declStmt.stmt.accept(this, locsSize);
		return locsSize + aux;
	}

	@Override
	public Object visit(AstExprStmt exprStmt, Object arg) {
		if (exprStmt.expr != null)
			exprStmt.expr.accept(this, arg);
		return (long) 0;
	}

	@Override
	public Object visit(AstIfStmt ifStmt, Object arg) {
	    long ret = 0;
	    long aux = 0;
		long off = offset;
		if (ifStmt.cond != null)
			ifStmt.cond.accept(this, arg);
		if (ifStmt.thenStmt != null)
			ret = (long) ifStmt.thenStmt.accept(this, arg);
		long foffset = offset;
		offset = off;
		if (ifStmt.elseStmt != null)
			aux = (long) ifStmt.elseStmt.accept(this, arg);
		if(aux < ret){offset = foffset; return ret;}
		return aux;
	}

	@Override
	public Object visit(AstStmts stmts, Object arg) {
		return stmts.stmts.accept(this, null);
	}

	@Override
	public Object visit(AstWhileStmt whileStmt, Object arg) {
		if (whileStmt.cond != null)
			whileStmt.cond.accept(this, arg);
		return whileStmt.bodyStmt.accept(this, null);
	}
}
