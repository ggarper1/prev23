parser grammar PrevParser;

@header {

	package prev23.phase.synan;
	
	import java.util.*;
	
	import prev23.common.report.*;
	import prev23.phase.lexan.*;
	import prev23.phase.abstr.*;

	import prev23.data.ast.tree.*;
	import prev23.data.ast.tree.expr.*;
	import prev23.data.ast.tree.stmt.*;
	import prev23.data.ast.tree.type.*;
	import prev23.data.ast.tree.decl.*;

	import prev23.data.ast.attribute.*;
}

@members{

    public Location location(){
        return new Location(getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine());
    }

    public Location location(Token t){
        return new Location(
            t.getLine(),
            t.getCharPositionInLine(),
            getCurrentToken().getLine(),
            getCurrentToken().getCharPositionInLine()
        );
    }

    public class LeftAsHelper{

        public AstBinExpr.Oper oper;
        public AstExpr expr;

        public LeftAsHelper(AstBinExpr.Oper o, AstExpr e){
            this.oper = o;
            this.expr = e;
        }

        public static AstBinExpr createBinExpr(Location l, AstExpr e, LeftAsHelper h){
            return new AstBinExpr(
                l, 
                h.oper,
                e,
                h.expr
            );
        }
    }
}


options{
    tokenVocab=PrevLexer;
}

source returns [AstTrees<AstTrees<AstDecl>> ast]
  : decl EOF {$ast = $decl.ast;}
  ;

decl        returns [AstTrees<AstTrees<AstDecl>> ast]
                :   {Vector<AstTrees<AstDecl>> declVector = new Vector<>();} 
                (
                        type_decl  {declVector.add($type_decl.typeDeclTree);}
                    |   func_decl  {declVector.add($func_decl.funDeclTree);}
                    |   var_decl   {declVector.add($var_decl.varDeclTree);}
                )+
                    {$ast = new AstTrees<AstTrees<AstDecl>>("Declaration Tree", declVector);
                }
            ;

type_decl   returns [AstTrees<AstDecl> typeDeclTree]
                :   {Vector<AstDecl> typeDeclVector = new Vector<>();}
                (
                    TYP fId=ID ASIGN fTyp=type{
                        typeDeclVector.add(
                            new AstTypDecl(
                                location($fId),
                                $fId.text,         
                                $fTyp.typeReturn
                            )
                        );
                    }
                    (COMMA oId=ID ASIGN oTyp=type{
                        typeDeclVector.add(
                            new AstTypDecl(
                                location($oId), 
                                $oId.text,
                                $oTyp.typeReturn
                            )
                        );
                    })*SEMICOLON){
                        $typeDeclTree = new AstTrees<AstDecl>("Type Declaration Tree", typeDeclVector);
                    }
            ;

func_decl   returns [AstTrees<AstDecl>  funDeclTree]
                :   {
                        Vector<AstDecl> funDeclVector = new Vector<>();
                        Vector<AstParDecl> paramDeclVector = new Vector<>();
                        AstStmt statement = null;
                    }
                (FUN fId=ID OPARENTHESES(fpId=ID COLON fpTyp=type
                    {
                        paramDeclVector.add(
                            new AstParDecl(
                                location($fpId),
                                $fpId.text,
                                $fpTyp.typeReturn
                            )
                        );
                    }(COMMA opId=ID COLON opTyp=type{
                        paramDeclVector.add(
                            new AstParDecl(
                                location($opId),
                                $opId.text,
                                $opTyp.typeReturn
                            )
                        );
                    })*)?CPARENTHESES COLON fTyp=type(ASIGN statement{
                        statement = $statement.statementReturn;
                    })?{
                        funDeclVector.add(
                            new AstFunDecl(
                                location($fId),
                                $fId.text,
                                new AstTrees("Param Tree",paramDeclVector),
                                $fTyp.typeReturn,
                                (AstStmt) statement
                            )
                        );
                    }({
                        statement = null; paramDeclVector = new Vector<AstParDecl>();
                    }COMMA fId=ID OPARENTHESES(fpId=ID COLON fpTyp=type{
                        paramDeclVector.add(
                            new AstParDecl(
                                location($fpId),
                                $fpId.text,
                                $fpTyp.typeReturn
                            )
                        );
                    }(COMMA opId=ID COLON opTyp=type{
                        paramDeclVector.add(
                            new AstParDecl(
                                location($opId),
                                $opId.text,
                                $opTyp.typeReturn
                            )
                        );
                    })*)?CPARENTHESES COLON fTyp=type(ASIGN statement{
                        statement = $statement.statementReturn;
                    })?{
                        funDeclVector.add(
                            new AstFunDecl(
                                location($fId),
                                $fId.text,
                                new AstTrees("Param Tree",paramDeclVector),
                                $fTyp.typeReturn,
                                (AstStmt)statement
                            )
                        );
                    })*SEMICOLON){
                        $funDeclTree = new AstTrees<AstDecl>("Function Declaration Tree", funDeclVector);
                    }
            ;

var_decl    returns [AstTrees<AstDecl> varDeclTree]
                :   {Vector<AstDecl> varDeclVector = new Vector<>();}
                    (VAR id=ID COLON typ=type{
                        varDeclVector.add(
                            new AstVarDecl(
                                location($id),
                                $id.text,
                                $typ.typeReturn
                            )
                        );
                    }(COMMA id=ID COLON typ=type{
                        varDeclVector.add(
                            new AstVarDecl(
                                location($id),
                                $id.text,
                                $typ.typeReturn
                            )
                        );
                    })*SEMICOLON){
                        $varDeclTree = new AstTrees<AstDecl>("Variable Declaration Tree", varDeclVector);
                    }
            ;

type        returns [AstType typeReturn]
                :
                    (VOID{
                        $typeReturn = new AstAtomType(
                            location($VOID),
                            AstAtomType.Type.VOID
                        );
                    }|CHAR{
                        $typeReturn = new AstAtomType(
                            location($CHAR),
                            AstAtomType.Type.CHAR
                        );
                    }|INT{
                        $typeReturn = new AstAtomType(
                            location($INT),
                            AstAtomType.Type.INT);
                    }|BOOL{
                        $typeReturn = new AstAtomType(
                            location($BOOL),
                            AstAtomType.Type.BOOL);
                    }|ID{
                        $typeReturn = new AstNameType(
                            location($ID),
                            $ID.text);
                    }|OBRACKETS expr CBRACKETS type{
                        $typeReturn = new AstArrType(
                            location($OBRACKETS),
                            $type.typeReturn,
                            $expr.returnExp);
                    }|CIRCUMFLEX type{
                        $typeReturn = new AstPtrType(
                            location($CIRCUMFLEX),
                            $type.typeReturn);
                    }|{
                        Vector<AstCmpDecl> cmpDeclVector = new Vector<>();
                    }OBRACES fId=ID COLON typ=type{
                        cmpDeclVector.add(
                            new AstCmpDecl(
                                location($fId),
                                $fId.text,
                                $typ.typeReturn
                            )
                        );
                    }(COMMA oId=ID COLON oTyp=type{
                        cmpDeclVector.add(
                            new AstCmpDecl(
                                location($oId),
                                $oId.text,
                                $oTyp.typeReturn
                            )
                        );
                    })*CBRACES{
                        AstTrees cmpDeclTree = new AstTrees("cmp Declaration Tree", cmpDeclVector);
                        $typeReturn = new AstRecType(
                            location($fId),
                            new AstTrees("cmp Delcaration Tree", cmpDeclVector)
                        );
                    }|OPARENTHESES type CPARENTHESES{
                        $typeReturn = $type.typeReturn;
                    })
            ;

statement   returns [AstStmt statementReturn]
                :
                    ({
                        AstExpr expr = null;
                    }fExpr=expr(ASIGN sExpr=expr{
                        expr =  $sExpr.returnExp;
                    })?{
                        if(expr==null){
                            $statementReturn = new AstExprStmt(
                                location(),    
                                $fExpr.returnExp
                            );
                        }else{
                            $statementReturn = new AstAssignStmt(
                                location(),
                                $fExpr.returnExp,
                                $sExpr.returnExp
                            );
                        }
                    }| {
                        AstStmt statement = null;
                    }IF expr THEN fState=statement(ELSE sState=statement{
                        statement = $sState.statementReturn;
                    })?{
                        $statementReturn = new AstIfStmt(
                            location($IF),
                            $expr.returnExp,
                            $fState.statementReturn,
                            statement
                        );
                    }| WHILE expr DO statement{
                        $statementReturn = new AstWhileStmt(
                            location($WHILE),
                            $expr.returnExp,
                            $statement.statementReturn
                        );
                    }| LET decl IN statement{
                        $statementReturn = new AstDeclStmt(
                            location($LET),
                            $decl.ast,
                            $statement.statementReturn
                        );
                    }|{
                        Vector<AstStmt> stateVector = new Vector<>();
                    }OBRACES fState=statement{
                        stateVector.add($fState.statementReturn);
                    }(SEMICOLON sState=statement{
                        stateVector.add($sState.statementReturn);
                    })*CBRACES{
                        $statementReturn = new AstStmts(
                            location(),
                            new AstTrees<AstStmt>("Statements Tree", stateVector)
                        );
                    })
            ;

expr        returns [AstExpr returnExp]
                :
                    aux1 level1{
                        $returnExp = $aux1.returnExp;
                        while(!$level1.stack.empty()){
                            LeftAsHelper aux = $level1.stack.pop();
                            $returnExp = LeftAsHelper.createBinExpr(
                                location(),
                                $returnExp,
                                aux
                            );
                        }
                    }
            ;

level1      returns [Stack<LeftAsHelper> stack]
                :
                    op1 aux1 level1{
                        $stack = $level1.stack;
                        $stack.push(
                            new LeftAsHelper(
                                $op1.operator,
                                $aux1.returnExp
                            )
                        );
                    }
                    |{
                        $stack = new Stack<LeftAsHelper>();
                    }
            ;

aux1        returns [AstExpr returnExp]
                : 
                    aux2 level2{
                        $returnExp = $aux2.returnExp;
                        while(!$level2.stack.empty()){
                            LeftAsHelper aux = $level2.stack.pop();
                            $returnExp = LeftAsHelper.createBinExpr(
                                location(),
                                $returnExp,
                                aux
                            );
                        }
                    }
            ;

level2      returns [Stack<LeftAsHelper> stack]
                :
                    op2 aux2 level2{
                        $stack = $level2.stack;
                        $stack.push(
                            new LeftAsHelper(
                                $op2.operator,
                                $aux2.returnExp
                            )
                        );
                    }
                    |{
                        $stack = new Stack<LeftAsHelper>();
                    }
            ;

aux2        returns [AstExpr returnExp]
                :
                    aux3 level3{
                        $returnExp = $aux3.returnExp;
                        while(!$level3.stack.empty()){
                            LeftAsHelper aux = $level3.stack.pop();
                            $returnExp = LeftAsHelper.createBinExpr(
                                location(),
                                $returnExp,
                                aux
                            );
                        }
                    }
            ;

level3      returns [Stack<LeftAsHelper> stack]
                :
                    op3 aux3{
                        $stack = new Stack<LeftAsHelper>();
                        $stack.push(
                            new LeftAsHelper(
                                $op3.operator,
                                $aux3.returnExp
                            )
                        );
                    }
                    |{
                        $stack = new Stack<LeftAsHelper>();
                    }
            ;

aux3        returns [AstExpr returnExp]
                :
                    aux4 level4{
                        $returnExp = $aux4.returnExp;
                        while(!$level4.stack.empty()){
                            LeftAsHelper aux = $level4.stack.pop();
                            $returnExp = LeftAsHelper.createBinExpr(
                                location(),
                                $returnExp,
                                aux
                            );
                        }
                    }
            ;

level4      returns [Stack<LeftAsHelper> stack]
                :
                    op4 aux4 level4{
                        $stack = $level4.stack;
                        $stack.push(
                            new LeftAsHelper(
                                $op4.operator,
                                $aux4.returnExp
                            )
                        );
                    }
                    |{
                        $stack = new Stack<LeftAsHelper>();
                    }
            ;

aux4        returns [AstExpr returnExp]
                :
                    pre level5{
                        $returnExp = $pre.returnExp;
                        while(!$level5.stack.empty()){
                            LeftAsHelper aux = $level5.stack.pop();
                            $returnExp = LeftAsHelper.createBinExpr(
                                location(),
                                $returnExp,
                                aux
                            );
                        }
                    }
            ;

level5      returns [Stack<LeftAsHelper> stack]
                :
                    op5 pre level5{
                        $stack = $level5.stack;
                        $stack.push(
                            new LeftAsHelper(
                                $op5.operator,
                                $pre.returnExp
                            )
                        );
                    }
                    |{
                        $stack = new Stack<LeftAsHelper>();
                    }
            ;

pre         returns [AstExpr returnExp]
                :
                    preOps pre{
                        $returnExp = new AstPfxExpr(
                            location(),
                            $preOps.operator, $pre.returnExp
                        );
                    }|pos{
                        $returnExp = $pos.returnExp;
                    }
            ;

pos         returns [AstExpr returnExp]
                :
                    exprEnd exprPos{
                        AstExpr e = $exprEnd.returnExp;
                                                
                        while(!$exprPos.v.empty()){
                            Vector<Object> aux = $exprPos.v.pop();
                            switch ((String) aux.get(0)){
                                case "C":
                                    e = new AstSfxExpr((Location) aux.get(1),AstSfxExpr.Oper.PTR, e);
                                    break;
                                case "A":
                                    e = new AstArrExpr((Location) aux.get(2), e, (AstExpr) aux.get(1));
                                    break;
                                default:
                                    e = new AstRecExpr(
                                        location(),
                                        e,
                                        (AstNameExpr) aux.get(1)
                                    );
                            }                      
                        }
                        $returnExp = e;
                    }
                    |exprEnd{
                        $returnExp = $exprEnd.returnExp;
                    }
            ;

exprPos     returns [Stack<Vector<Object>> v]
                :
                    posOps exprPos{
                        $exprPos.v.push($posOps.r);
                        $v = $exprPos.v;
                    }| posOps{
                        $v = new Stack<Vector<Object>>(); $v.push($posOps.r);
                    }
            ;
                    
posOps      returns [Vector<Object> r]
                :{
                    $r = new Vector<Object>();
                }(CIRCUMFLEX{
                    $r.add("C");
                    $r.add(location());
                }|OBRACKETS expr CBRACKETS{
                    $r.add("A");
                    $r.add($expr.returnExp);
                    $r.add(location());
                }| DOT ID{
                    $r.add("D");
                    $r.add(
                        new AstNameExpr(
                            location($ID),
                            $ID.text
                        )
                    );
                    $r.add(
                        location($DOT)      
                    );
                })
            ;

op1         returns [AstBinExpr.Oper operator]
                : OR{$operator = AstBinExpr.Oper.OR;};
op2         returns [AstBinExpr.Oper operator]
                : AND{$operator = AstBinExpr.Oper.AND;};
op3         returns [AstBinExpr.Oper operator]
                : EQUAL{$operator = AstBinExpr.Oper.EQU;}
                |GREATER{$operator = AstBinExpr.Oper.GTH;}
                |LESSER{$operator = AstBinExpr.Oper.LTH;}
                |DIFFER{$operator = AstBinExpr.Oper.NEQ;}
                |GREATER_OR_EQUAL{$operator = AstBinExpr.Oper.GEQ;}
                |LESSER_OR_EQUAL{$operator = AstBinExpr.Oper.LEQ;}
            ;
op4         returns [AstBinExpr.Oper operator]
                :SUM{$operator = AstBinExpr.Oper.ADD;}
                |SUB{$operator = AstBinExpr.Oper.SUB;}
            ;
op5         returns [AstBinExpr.Oper operator]
                :MULT{$operator = AstBinExpr.Oper.MUL;}
                |DIV{$operator = AstBinExpr.Oper.DIV;}
                |REMAINDER{$operator = AstBinExpr.Oper.MOD;}
            ;
preOps      returns [AstPfxExpr.Oper operator]
                :NOT{$operator = AstPfxExpr.Oper.NOT;}
                |SUM{$operator = AstPfxExpr.Oper.ADD;}
                |SUB{$operator = AstPfxExpr.Oper.SUB;}
                |CIRCUMFLEX{$operator = AstPfxExpr.Oper.PTR;}
            ;

exprEnd     returns [AstExpr returnExp]
                :(constant{
                    $returnExp = $constant.atomExpr;
                }|ID{
                    $returnExp = new AstNameExpr(
                        location($ID),
                        $ID.text
                    );
                }|ID{
                    Vector<AstExpr> exprVector = new Vector<>();
                }OPARENTHESES(fExpr=expr{
                    exprVector.add($fExpr.returnExp);
                }(COMMA sExpr=expr{
                    exprVector.add($sExpr.returnExp);
                })*)?CPARENTHESES{
                    $returnExp = new AstCallExpr(
                        location($ID),
                        $ID.text,
                        new AstTrees("Expression Tree", exprVector)
                    );
                }|{
                    AstType t = null;
                }OPARENTHESES expr(COLON type{
                    t =  $type.typeReturn;
                })?CPARENTHESES{
                    if(t == null){
                        $returnExp = $expr.returnExp;
                    }else{
                       	$returnExp =new AstCastExpr(
                            location($OPARENTHESES),
                       	    $expr.returnExp,
                            t
                        );
                    }
                }|NEW OPARENTHESES type CPARENTHESES{
                    $returnExp = new AstNewExpr(
                        location($NEW),
                        $type.typeReturn
                    );
                }| DEL OPARENTHESES expr CPARENTHESES{
                    $returnExp = new AstDelExpr(
                        location($DEL),
                        $expr.returnExp
                    );
                })
            ;

constant    returns [AstAtomExpr atomExpr]
                :(NONE{
                    $atomExpr = new AstAtomExpr(
                        location($NONE),
                        AstAtomExpr.Type.VOID, $NONE.text
                    );
                }|TRUE{
                    $atomExpr = new AstAtomExpr(
                        location($TRUE),
                        AstAtomExpr.Type.BOOL, $TRUE.text);
                }|FALSE{
                    $atomExpr = new AstAtomExpr(
                        location($FALSE),
                        AstAtomExpr.Type.BOOL, $FALSE.text);
                }|INTEGER{
                    $atomExpr = new AstAtomExpr(
                        location($INTEGER),
                        AstAtomExpr.Type.INT, $INTEGER.text
                        );
                }|CHARACTER{
                    $atomExpr = new AstAtomExpr(
                        location($CHARACTER),
                        AstAtomExpr.Type.CHAR, $CHARACTER.text
                    );
                }|STRING{
                    $atomExpr = new AstAtomExpr(
                        location($STRING),
                        AstAtomExpr.Type.STR, $STRING.text
                    );
                }|NIL{
                    $atomExpr = new AstAtomExpr(
                        location($NIL),
                        AstAtomExpr.Type.PTR,
                        $NIL.text
                    );
                })
            ;
            