// Generated from PrevParser.g4 by ANTLR 4.13.0


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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LF=2, TAB=3, BOOL=4, CHAR=5, DEL=6, DO=7, ELSE=8, FUN=9, IF=10, 
		IN=11, INT=12, LET=13, NEW=14, THEN=15, TYP=16, VAR=17, VOID=18, WHILE=19, 
		COMMENT=20, OPARENTHESES=21, OBRACES=22, OBRACKETS=23, CPARENTHESES=24, 
		CBRACES=25, CBRACKETS=26, DOT=27, COMMA=28, COLON=29, SEMICOLON=30, AND=31, 
		OR=32, NOT=33, EQUAL=34, DIFFER=35, GREATER=36, LESSER=37, LESSER_OR_EQUAL=38, 
		GREATER_OR_EQUAL=39, MULT=40, DIV=41, REMAINDER=42, SUM=43, SUB=44, CIRCUMFLEX=45, 
		ASIGN=46, NONE=47, TRUE=48, FALSE=49, INTEGER=50, CHARACTER=51, STRING=52, 
		NIL=53, ID=54, QUOTEERROR=55, NONASCII=56;
	public static final int
		RULE_source = 0, RULE_decl = 1, RULE_type_decl = 2, RULE_func_decl = 3, 
		RULE_var_decl = 4, RULE_type = 5, RULE_statement = 6, RULE_expr = 7, RULE_level1 = 8, 
		RULE_aux1 = 9, RULE_level2 = 10, RULE_aux2 = 11, RULE_level3 = 12, RULE_aux3 = 13, 
		RULE_level4 = 14, RULE_aux4 = 15, RULE_level5 = 16, RULE_pre = 17, RULE_pos = 18, 
		RULE_exprPos = 19, RULE_posOps = 20, RULE_op1 = 21, RULE_op2 = 22, RULE_op3 = 23, 
		RULE_op4 = 24, RULE_op5 = 25, RULE_preOps = 26, RULE_exprEnd = 27, RULE_constant = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "decl", "type_decl", "func_decl", "var_decl", "type", "statement", 
			"expr", "level1", "aux1", "level2", "aux2", "level3", "aux3", "level4", 
			"aux4", "level5", "pre", "pos", "exprPos", "posOps", "op1", "op2", "op3", 
			"op4", "op5", "preOps", "exprEnd", "constant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'\\n'", null, "'bool'", "'char'", "'del'", "'do'", "'else'", 
			"'fun'", "'if'", "'in'", "'int'", "'let'", "'new'", "'then'", "'typ'", 
			"'var'", "'void'", "'while'", null, "'('", "'{'", "'['", "')'", "'}'", 
			"']'", "'.'", "','", "':'", "';'", "'&'", "'|'", "'!'", "'=='", "'!='", 
			"'>'", "'<'", "'<='", "'>='", "'*'", "'/'", "'%'", "'+'", "'-'", "'^'", 
			"'='", "'none'", "'true'", "'false'", null, null, null, "'nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "LF", "TAB", "BOOL", "CHAR", "DEL", "DO", "ELSE", "FUN", 
			"IF", "IN", "INT", "LET", "NEW", "THEN", "TYP", "VAR", "VOID", "WHILE", 
			"COMMENT", "OPARENTHESES", "OBRACES", "OBRACKETS", "CPARENTHESES", "CBRACES", 
			"CBRACKETS", "DOT", "COMMA", "COLON", "SEMICOLON", "AND", "OR", "NOT", 
			"EQUAL", "DIFFER", "GREATER", "LESSER", "LESSER_OR_EQUAL", "GREATER_OR_EQUAL", 
			"MULT", "DIV", "REMAINDER", "SUM", "SUB", "CIRCUMFLEX", "ASIGN", "NONE", 
			"TRUE", "FALSE", "INTEGER", "CHARACTER", "STRING", "NIL", "ID", "QUOTEERROR", 
			"NONASCII"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PrevParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



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

	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public AstTrees<AstTrees<AstDecl>> ast;
		public DeclContext decl;
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PrevParser.EOF, 0); }
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((SourceContext)_localctx).decl = decl();
			setState(59);
			match(EOF);
			((SourceContext)_localctx).ast =  ((SourceContext)_localctx).decl.ast;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public AstTrees<AstTrees<AstDecl>> ast;
		public Type_declContext type_decl;
		public Func_declContext func_decl;
		public Var_declContext var_decl;
		public List<Type_declContext> type_decl() {
			return getRuleContexts(Type_declContext.class);
		}
		public Type_declContext type_decl(int i) {
			return getRuleContext(Type_declContext.class,i);
		}
		public List<Func_declContext> func_decl() {
			return getRuleContexts(Func_declContext.class);
		}
		public Func_declContext func_decl(int i) {
			return getRuleContext(Func_declContext.class,i);
		}
		public List<Var_declContext> var_decl() {
			return getRuleContexts(Var_declContext.class);
		}
		public Var_declContext var_decl(int i) {
			return getRuleContext(Var_declContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstTrees<AstDecl>> declVector = new Vector<>();
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(72);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TYP:
					{
					setState(63);
					((DeclContext)_localctx).type_decl = type_decl();
					declVector.add(((DeclContext)_localctx).type_decl.typeDeclTree);
					}
					break;
				case FUN:
					{
					setState(66);
					((DeclContext)_localctx).func_decl = func_decl();
					declVector.add(((DeclContext)_localctx).func_decl.funDeclTree);
					}
					break;
				case VAR:
					{
					setState(69);
					((DeclContext)_localctx).var_decl = var_decl();
					declVector.add(((DeclContext)_localctx).var_decl.varDeclTree);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 197120L) != 0) );
			((DeclContext)_localctx).ast =  new AstTrees<AstTrees<AstDecl>>("Declaration Tree", declVector);
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_declContext extends ParserRuleContext {
		public AstTrees<AstDecl> typeDeclTree;
		public Token fId;
		public TypeContext fTyp;
		public Token oId;
		public TypeContext oTyp;
		public TerminalNode TYP() { return getToken(PrevParser.TYP, 0); }
		public List<TerminalNode> ASIGN() { return getTokens(PrevParser.ASIGN); }
		public TerminalNode ASIGN(int i) {
			return getToken(PrevParser.ASIGN, i);
		}
		public TerminalNode SEMICOLON() { return getToken(PrevParser.SEMICOLON, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public Type_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_decl; }
	}

	public final Type_declContext type_decl() throws RecognitionException {
		Type_declContext _localctx = new Type_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstDecl> typeDeclVector = new Vector<>();
			{
			setState(79);
			match(TYP);
			setState(80);
			((Type_declContext)_localctx).fId = match(ID);
			setState(81);
			match(ASIGN);
			setState(82);
			((Type_declContext)_localctx).fTyp = type();

			                        typeDeclVector.add(
			                            new AstTypDecl(
			                                location(((Type_declContext)_localctx).fId),
			                                (((Type_declContext)_localctx).fId!=null?((Type_declContext)_localctx).fId.getText():null),         
			                                ((Type_declContext)_localctx).fTyp.typeReturn
			                            )
			                        );
			                    
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(84);
				match(COMMA);
				setState(85);
				((Type_declContext)_localctx).oId = match(ID);
				setState(86);
				match(ASIGN);
				setState(87);
				((Type_declContext)_localctx).oTyp = type();

				                        typeDeclVector.add(
				                            new AstTypDecl(
				                                location(((Type_declContext)_localctx).oId), 
				                                (((Type_declContext)_localctx).oId!=null?((Type_declContext)_localctx).oId.getText():null),
				                                ((Type_declContext)_localctx).oTyp.typeReturn
				                            )
				                        );
				                    
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
			match(SEMICOLON);
			}

			                        ((Type_declContext)_localctx).typeDeclTree =  new AstTrees<AstDecl>("Type Declaration Tree", typeDeclVector);
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_declContext extends ParserRuleContext {
		public AstTrees<AstDecl> funDeclTree;
		public Token fId;
		public Token fpId;
		public TypeContext fpTyp;
		public Token opId;
		public TypeContext opTyp;
		public TypeContext fTyp;
		public StatementContext statement;
		public TerminalNode FUN() { return getToken(PrevParser.FUN, 0); }
		public List<TerminalNode> OPARENTHESES() { return getTokens(PrevParser.OPARENTHESES); }
		public TerminalNode OPARENTHESES(int i) {
			return getToken(PrevParser.OPARENTHESES, i);
		}
		public List<TerminalNode> CPARENTHESES() { return getTokens(PrevParser.CPARENTHESES); }
		public TerminalNode CPARENTHESES(int i) {
			return getToken(PrevParser.CPARENTHESES, i);
		}
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode SEMICOLON() { return getToken(PrevParser.SEMICOLON, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> ASIGN() { return getTokens(PrevParser.ASIGN); }
		public TerminalNode ASIGN(int i) {
			return getToken(PrevParser.ASIGN, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public Func_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_decl; }
	}

	public final Func_declContext func_decl() throws RecognitionException {
		Func_declContext _localctx = new Func_declContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			                        Vector<AstDecl> funDeclVector = new Vector<>();
			                        Vector<AstParDecl> paramDeclVector = new Vector<>();
			                        AstStmt statement = null;
			                    
			{
			setState(100);
			match(FUN);
			setState(101);
			((Func_declContext)_localctx).fId = match(ID);
			setState(102);
			match(OPARENTHESES);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(103);
				((Func_declContext)_localctx).fpId = match(ID);
				setState(104);
				match(COLON);
				setState(105);
				((Func_declContext)_localctx).fpTyp = type();

				                        paramDeclVector.add(
				                            new AstParDecl(
				                                location(((Func_declContext)_localctx).fpId),
				                                (((Func_declContext)_localctx).fpId!=null?((Func_declContext)_localctx).fpId.getText():null),
				                                ((Func_declContext)_localctx).fpTyp.typeReturn
				                            )
				                        );
				                    
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(107);
					match(COMMA);
					setState(108);
					((Func_declContext)_localctx).opId = match(ID);
					setState(109);
					match(COLON);
					setState(110);
					((Func_declContext)_localctx).opTyp = type();

					                        paramDeclVector.add(
					                            new AstParDecl(
					                                location(((Func_declContext)_localctx).opId),
					                                (((Func_declContext)_localctx).opId!=null?((Func_declContext)_localctx).opId.getText():null),
					                                ((Func_declContext)_localctx).opTyp.typeReturn
					                            )
					                        );
					                    
					}
					}
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(120);
			match(CPARENTHESES);
			setState(121);
			match(COLON);
			setState(122);
			((Func_declContext)_localctx).fTyp = type();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASIGN) {
				{
				setState(123);
				match(ASIGN);
				setState(124);
				((Func_declContext)_localctx).statement = statement();

				                        statement = ((Func_declContext)_localctx).statement.statementReturn;
				                    
				}
			}


			                        funDeclVector.add(
			                            new AstFunDecl(
			                                location(((Func_declContext)_localctx).fId),
			                                (((Func_declContext)_localctx).fId!=null?((Func_declContext)_localctx).fId.getText():null),
			                                new AstTrees("Param Tree",paramDeclVector),
			                                ((Func_declContext)_localctx).fTyp.typeReturn,
			                                (AstStmt) statement
			                            )
			                        );
			                    
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{

				                        statement = null; paramDeclVector = new Vector<AstParDecl>();
				                    
				setState(131);
				match(COMMA);
				setState(132);
				((Func_declContext)_localctx).fId = match(ID);
				setState(133);
				match(OPARENTHESES);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(134);
					((Func_declContext)_localctx).fpId = match(ID);
					setState(135);
					match(COLON);
					setState(136);
					((Func_declContext)_localctx).fpTyp = type();

					                        paramDeclVector.add(
					                            new AstParDecl(
					                                location(((Func_declContext)_localctx).fpId),
					                                (((Func_declContext)_localctx).fpId!=null?((Func_declContext)_localctx).fpId.getText():null),
					                                ((Func_declContext)_localctx).fpTyp.typeReturn
					                            )
					                        );
					                    
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(138);
						match(COMMA);
						setState(139);
						((Func_declContext)_localctx).opId = match(ID);
						setState(140);
						match(COLON);
						setState(141);
						((Func_declContext)_localctx).opTyp = type();

						                        paramDeclVector.add(
						                            new AstParDecl(
						                                location(((Func_declContext)_localctx).opId),
						                                (((Func_declContext)_localctx).opId!=null?((Func_declContext)_localctx).opId.getText():null),
						                                ((Func_declContext)_localctx).opTyp.typeReturn
						                            )
						                        );
						                    
						}
						}
						setState(148);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(151);
				match(CPARENTHESES);
				setState(152);
				match(COLON);
				setState(153);
				((Func_declContext)_localctx).fTyp = type();
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASIGN) {
					{
					setState(154);
					match(ASIGN);
					setState(155);
					((Func_declContext)_localctx).statement = statement();

					                        statement = ((Func_declContext)_localctx).statement.statementReturn;
					                    
					}
				}


				                        funDeclVector.add(
				                            new AstFunDecl(
				                                location(((Func_declContext)_localctx).fId),
				                                (((Func_declContext)_localctx).fId!=null?((Func_declContext)_localctx).fId.getText():null),
				                                new AstTrees("Param Tree",paramDeclVector),
				                                ((Func_declContext)_localctx).fTyp.typeReturn,
				                                (AstStmt)statement
				                            )
				                        );
				                    
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
			match(SEMICOLON);
			}

			                        ((Func_declContext)_localctx).funDeclTree =  new AstTrees<AstDecl>("Function Declaration Tree", funDeclVector);
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_declContext extends ParserRuleContext {
		public AstTrees<AstDecl> varDeclTree;
		public Token id;
		public TypeContext typ;
		public TerminalNode VAR() { return getToken(PrevParser.VAR, 0); }
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode SEMICOLON() { return getToken(PrevParser.SEMICOLON, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_var_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstDecl> varDeclVector = new Vector<>();
			{
			setState(172);
			match(VAR);
			setState(173);
			((Var_declContext)_localctx).id = match(ID);
			setState(174);
			match(COLON);
			setState(175);
			((Var_declContext)_localctx).typ = type();

			                        varDeclVector.add(
			                            new AstVarDecl(
			                                location(((Var_declContext)_localctx).id),
			                                (((Var_declContext)_localctx).id!=null?((Var_declContext)_localctx).id.getText():null),
			                                ((Var_declContext)_localctx).typ.typeReturn
			                            )
			                        );
			                    
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(177);
				match(COMMA);
				setState(178);
				((Var_declContext)_localctx).id = match(ID);
				setState(179);
				match(COLON);
				setState(180);
				((Var_declContext)_localctx).typ = type();

				                        varDeclVector.add(
				                            new AstVarDecl(
				                                location(((Var_declContext)_localctx).id),
				                                (((Var_declContext)_localctx).id!=null?((Var_declContext)_localctx).id.getText():null),
				                                ((Var_declContext)_localctx).typ.typeReturn
				                            )
				                        );
				                    
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(188);
			match(SEMICOLON);
			}

			                        ((Var_declContext)_localctx).varDeclTree =  new AstTrees<AstDecl>("Variable Declaration Tree", varDeclVector);
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public AstType typeReturn;
		public Token VOID;
		public Token CHAR;
		public Token INT;
		public Token BOOL;
		public Token ID;
		public Token OBRACKETS;
		public ExprContext expr;
		public TypeContext type;
		public Token CIRCUMFLEX;
		public Token fId;
		public TypeContext typ;
		public Token oId;
		public TypeContext oTyp;
		public TerminalNode VOID() { return getToken(PrevParser.VOID, 0); }
		public TerminalNode CHAR() { return getToken(PrevParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(PrevParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(PrevParser.BOOL, 0); }
		public List<TerminalNode> ID() { return getTokens(PrevParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PrevParser.ID, i);
		}
		public TerminalNode OBRACKETS() { return getToken(PrevParser.OBRACKETS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CBRACKETS() { return getToken(PrevParser.CBRACKETS, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode CIRCUMFLEX() { return getToken(PrevParser.CIRCUMFLEX, 0); }
		public TerminalNode OBRACES() { return getToken(PrevParser.OBRACES, 0); }
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode CBRACES() { return getToken(PrevParser.CBRACES, 0); }
		public TerminalNode OPARENTHESES() { return getToken(PrevParser.OPARENTHESES, 0); }
		public TerminalNode CPARENTHESES() { return getToken(PrevParser.CPARENTHESES, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				{
				setState(192);
				((TypeContext)_localctx).VOID = match(VOID);

				                        ((TypeContext)_localctx).typeReturn =  new AstAtomType(
				                            location(((TypeContext)_localctx).VOID),
				                            AstAtomType.Type.VOID
				                        );
				                    
				}
				break;
			case CHAR:
				{
				setState(194);
				((TypeContext)_localctx).CHAR = match(CHAR);

				                        ((TypeContext)_localctx).typeReturn =  new AstAtomType(
				                            location(((TypeContext)_localctx).CHAR),
				                            AstAtomType.Type.CHAR
				                        );
				                    
				}
				break;
			case INT:
				{
				setState(196);
				((TypeContext)_localctx).INT = match(INT);

				                        ((TypeContext)_localctx).typeReturn =  new AstAtomType(
				                            location(((TypeContext)_localctx).INT),
				                            AstAtomType.Type.INT);
				                    
				}
				break;
			case BOOL:
				{
				setState(198);
				((TypeContext)_localctx).BOOL = match(BOOL);

				                        ((TypeContext)_localctx).typeReturn =  new AstAtomType(
				                            location(((TypeContext)_localctx).BOOL),
				                            AstAtomType.Type.BOOL);
				                    
				}
				break;
			case ID:
				{
				setState(200);
				((TypeContext)_localctx).ID = match(ID);

				                        ((TypeContext)_localctx).typeReturn =  new AstNameType(
				                            location(((TypeContext)_localctx).ID),
				                            (((TypeContext)_localctx).ID!=null?((TypeContext)_localctx).ID.getText():null));
				                    
				}
				break;
			case OBRACKETS:
				{
				setState(202);
				((TypeContext)_localctx).OBRACKETS = match(OBRACKETS);
				setState(203);
				((TypeContext)_localctx).expr = expr();
				setState(204);
				match(CBRACKETS);
				setState(205);
				((TypeContext)_localctx).type = type();

				                        ((TypeContext)_localctx).typeReturn =  new AstArrType(
				                            location(((TypeContext)_localctx).OBRACKETS),
				                            ((TypeContext)_localctx).type.typeReturn,
				                            ((TypeContext)_localctx).expr.returnExp);
				                    
				}
				break;
			case CIRCUMFLEX:
				{
				setState(208);
				((TypeContext)_localctx).CIRCUMFLEX = match(CIRCUMFLEX);
				setState(209);
				((TypeContext)_localctx).type = type();

				                        ((TypeContext)_localctx).typeReturn =  new AstPtrType(
				                            location(((TypeContext)_localctx).CIRCUMFLEX),
				                            ((TypeContext)_localctx).type.typeReturn);
				                    
				}
				break;
			case OBRACES:
				{

				                        Vector<AstCmpDecl> cmpDeclVector = new Vector<>();
				                    
				setState(213);
				match(OBRACES);
				setState(214);
				((TypeContext)_localctx).fId = match(ID);
				setState(215);
				match(COLON);
				setState(216);
				((TypeContext)_localctx).typ = ((TypeContext)_localctx).type = type();

				                        cmpDeclVector.add(
				                            new AstCmpDecl(
				                                location(((TypeContext)_localctx).fId),
				                                (((TypeContext)_localctx).fId!=null?((TypeContext)_localctx).fId.getText():null),
				                                ((TypeContext)_localctx).typ.typeReturn
				                            )
				                        );
				                    
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(218);
					match(COMMA);
					setState(219);
					((TypeContext)_localctx).oId = match(ID);
					setState(220);
					match(COLON);
					setState(221);
					((TypeContext)_localctx).oTyp = ((TypeContext)_localctx).type = type();

					                        cmpDeclVector.add(
					                            new AstCmpDecl(
					                                location(((TypeContext)_localctx).oId),
					                                (((TypeContext)_localctx).oId!=null?((TypeContext)_localctx).oId.getText():null),
					                                ((TypeContext)_localctx).oTyp.typeReturn
					                            )
					                        );
					                    
					}
					}
					setState(228);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(229);
				match(CBRACES);

				                        AstTrees cmpDeclTree = new AstTrees("cmp Declaration Tree", cmpDeclVector);
				                        ((TypeContext)_localctx).typeReturn =  new AstRecType(
				                            location(((TypeContext)_localctx).fId),
				                            new AstTrees("cmp Delcaration Tree", cmpDeclVector)
				                        );
				                    
				}
				break;
			case OPARENTHESES:
				{
				setState(232);
				match(OPARENTHESES);
				setState(233);
				((TypeContext)_localctx).type = type();
				setState(234);
				match(CPARENTHESES);

				                        ((TypeContext)_localctx).typeReturn =  ((TypeContext)_localctx).type.typeReturn;
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AstStmt statementReturn;
		public ExprContext fExpr;
		public ExprContext expr;
		public ExprContext sExpr;
		public Token IF;
		public StatementContext fState;
		public StatementContext statement;
		public StatementContext sState;
		public Token WHILE;
		public Token LET;
		public DeclContext decl;
		public TerminalNode IF() { return getToken(PrevParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode THEN() { return getToken(PrevParser.THEN, 0); }
		public TerminalNode WHILE() { return getToken(PrevParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(PrevParser.DO, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode LET() { return getToken(PrevParser.LET, 0); }
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public TerminalNode IN() { return getToken(PrevParser.IN, 0); }
		public TerminalNode OBRACES() { return getToken(PrevParser.OBRACES, 0); }
		public TerminalNode CBRACES() { return getToken(PrevParser.CBRACES, 0); }
		public TerminalNode ASIGN() { return getToken(PrevParser.ASIGN, 0); }
		public TerminalNode ELSE() { return getToken(PrevParser.ELSE, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(PrevParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(PrevParser.SEMICOLON, i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEL:
			case NEW:
			case OPARENTHESES:
			case NOT:
			case SUM:
			case SUB:
			case CIRCUMFLEX:
			case NONE:
			case TRUE:
			case FALSE:
			case INTEGER:
			case CHARACTER:
			case STRING:
			case NIL:
			case ID:
				{

				                        AstExpr expr = null;
				                    
				setState(240);
				((StatementContext)_localctx).fExpr = ((StatementContext)_localctx).expr = expr();
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASIGN) {
					{
					setState(241);
					match(ASIGN);
					setState(242);
					((StatementContext)_localctx).sExpr = ((StatementContext)_localctx).expr = expr();

					                        expr =  ((StatementContext)_localctx).sExpr.returnExp;
					                    
					}
				}


				                        if(expr==null){
				                            ((StatementContext)_localctx).statementReturn =  new AstExprStmt(
				                                location(),    
				                                ((StatementContext)_localctx).fExpr.returnExp
				                            );
				                        }else{
				                            ((StatementContext)_localctx).statementReturn =  new AstAssignStmt(
				                                location(),
				                                ((StatementContext)_localctx).fExpr.returnExp,
				                                ((StatementContext)_localctx).sExpr.returnExp
				                            );
				                        }
				                    
				}
				break;
			case IF:
				{

				                        AstStmt statement = null;
				                    
				setState(250);
				((StatementContext)_localctx).IF = match(IF);
				setState(251);
				((StatementContext)_localctx).expr = expr();
				setState(252);
				match(THEN);
				setState(253);
				((StatementContext)_localctx).fState = ((StatementContext)_localctx).statement = statement();
				setState(258);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(254);
					match(ELSE);
					setState(255);
					((StatementContext)_localctx).sState = ((StatementContext)_localctx).statement = statement();

					                        statement = ((StatementContext)_localctx).sState.statementReturn;
					                    
					}
					break;
				}

				                        ((StatementContext)_localctx).statementReturn =  new AstIfStmt(
				                            location(((StatementContext)_localctx).IF),
				                            ((StatementContext)_localctx).expr.returnExp,
				                            ((StatementContext)_localctx).fState.statementReturn,
				                            statement
				                        );
				                    
				}
				break;
			case WHILE:
				{
				setState(262);
				((StatementContext)_localctx).WHILE = match(WHILE);
				setState(263);
				((StatementContext)_localctx).expr = expr();
				setState(264);
				match(DO);
				setState(265);
				((StatementContext)_localctx).statement = statement();

				                        ((StatementContext)_localctx).statementReturn =  new AstWhileStmt(
				                            location(((StatementContext)_localctx).WHILE),
				                            ((StatementContext)_localctx).expr.returnExp,
				                            ((StatementContext)_localctx).statement.statementReturn
				                        );
				                    
				}
				break;
			case LET:
				{
				setState(268);
				((StatementContext)_localctx).LET = match(LET);
				setState(269);
				((StatementContext)_localctx).decl = decl();
				setState(270);
				match(IN);
				setState(271);
				((StatementContext)_localctx).statement = statement();

				                        ((StatementContext)_localctx).statementReturn =  new AstDeclStmt(
				                            location(((StatementContext)_localctx).LET),
				                            ((StatementContext)_localctx).decl.ast,
				                            ((StatementContext)_localctx).statement.statementReturn
				                        );
				                    
				}
				break;
			case OBRACES:
				{

				                        Vector<AstStmt> stateVector = new Vector<>();
				                    
				setState(275);
				match(OBRACES);
				setState(276);
				((StatementContext)_localctx).fState = ((StatementContext)_localctx).statement = statement();

				                        stateVector.add(((StatementContext)_localctx).fState.statementReturn);
				                    
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(278);
					match(SEMICOLON);
					setState(279);
					((StatementContext)_localctx).sState = ((StatementContext)_localctx).statement = statement();

					                        stateVector.add(((StatementContext)_localctx).sState.statementReturn);
					                    
					}
					}
					setState(286);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(287);
				match(CBRACES);

				                        ((StatementContext)_localctx).statementReturn =  new AstStmts(
				                            location(),
				                            new AstTrees<AstStmt>("Statements Tree", stateVector)
				                        );
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public AstExpr returnExp;
		public Aux1Context aux1;
		public Level1Context level1;
		public Aux1Context aux1() {
			return getRuleContext(Aux1Context.class,0);
		}
		public Level1Context level1() {
			return getRuleContext(Level1Context.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			((ExprContext)_localctx).aux1 = aux1();
			setState(293);
			((ExprContext)_localctx).level1 = level1();

			                        ((ExprContext)_localctx).returnExp =  ((ExprContext)_localctx).aux1.returnExp;
			                        while(!((ExprContext)_localctx).level1.stack.empty()){
			                            LeftAsHelper aux = ((ExprContext)_localctx).level1.stack.pop();
			                            ((ExprContext)_localctx).returnExp =  LeftAsHelper.createBinExpr(
			                                location(),
			                                _localctx.returnExp,
			                                aux
			                            );
			                        }
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Level1Context extends ParserRuleContext {
		public Stack<LeftAsHelper> stack;
		public Op1Context op1;
		public Aux1Context aux1;
		public Level1Context level1;
		public Op1Context op1() {
			return getRuleContext(Op1Context.class,0);
		}
		public Aux1Context aux1() {
			return getRuleContext(Aux1Context.class,0);
		}
		public Level1Context level1() {
			return getRuleContext(Level1Context.class,0);
		}
		public Level1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level1; }
	}

	public final Level1Context level1() throws RecognitionException {
		Level1Context _localctx = new Level1Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_level1);
		try {
			setState(302);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				((Level1Context)_localctx).op1 = op1();
				setState(297);
				((Level1Context)_localctx).aux1 = aux1();
				setState(298);
				((Level1Context)_localctx).level1 = level1();

				                        ((Level1Context)_localctx).stack =  ((Level1Context)_localctx).level1.stack;
				                        _localctx.stack.push(
				                            new LeftAsHelper(
				                                ((Level1Context)_localctx).op1.operator,
				                                ((Level1Context)_localctx).aux1.returnExp
				                            )
				                        );
				                    
				}
				break;
			case DO:
			case ELSE:
			case THEN:
			case CPARENTHESES:
			case CBRACES:
			case CBRACKETS:
			case COMMA:
			case COLON:
			case SEMICOLON:
			case ASIGN:
				enterOuterAlt(_localctx, 2);
				{

				                        ((Level1Context)_localctx).stack =  new Stack<LeftAsHelper>();
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Aux1Context extends ParserRuleContext {
		public AstExpr returnExp;
		public Aux2Context aux2;
		public Level2Context level2;
		public Aux2Context aux2() {
			return getRuleContext(Aux2Context.class,0);
		}
		public Level2Context level2() {
			return getRuleContext(Level2Context.class,0);
		}
		public Aux1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aux1; }
	}

	public final Aux1Context aux1() throws RecognitionException {
		Aux1Context _localctx = new Aux1Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_aux1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			((Aux1Context)_localctx).aux2 = aux2();
			setState(305);
			((Aux1Context)_localctx).level2 = level2();

			                        ((Aux1Context)_localctx).returnExp =  ((Aux1Context)_localctx).aux2.returnExp;
			                        while(!((Aux1Context)_localctx).level2.stack.empty()){
			                            LeftAsHelper aux = ((Aux1Context)_localctx).level2.stack.pop();
			                            ((Aux1Context)_localctx).returnExp =  LeftAsHelper.createBinExpr(
			                                location(),
			                                _localctx.returnExp,
			                                aux
			                            );
			                        }
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Level2Context extends ParserRuleContext {
		public Stack<LeftAsHelper> stack;
		public Op2Context op2;
		public Aux2Context aux2;
		public Level2Context level2;
		public Op2Context op2() {
			return getRuleContext(Op2Context.class,0);
		}
		public Aux2Context aux2() {
			return getRuleContext(Aux2Context.class,0);
		}
		public Level2Context level2() {
			return getRuleContext(Level2Context.class,0);
		}
		public Level2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level2; }
	}

	public final Level2Context level2() throws RecognitionException {
		Level2Context _localctx = new Level2Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_level2);
		try {
			setState(314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				((Level2Context)_localctx).op2 = op2();
				setState(309);
				((Level2Context)_localctx).aux2 = aux2();
				setState(310);
				((Level2Context)_localctx).level2 = level2();

				                        ((Level2Context)_localctx).stack =  ((Level2Context)_localctx).level2.stack;
				                        _localctx.stack.push(
				                            new LeftAsHelper(
				                                ((Level2Context)_localctx).op2.operator,
				                                ((Level2Context)_localctx).aux2.returnExp
				                            )
				                        );
				                    
				}
				break;
			case DO:
			case ELSE:
			case THEN:
			case CPARENTHESES:
			case CBRACES:
			case CBRACKETS:
			case COMMA:
			case COLON:
			case SEMICOLON:
			case OR:
			case ASIGN:
				enterOuterAlt(_localctx, 2);
				{

				                        ((Level2Context)_localctx).stack =  new Stack<LeftAsHelper>();
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Aux2Context extends ParserRuleContext {
		public AstExpr returnExp;
		public Aux3Context aux3;
		public Level3Context level3;
		public Aux3Context aux3() {
			return getRuleContext(Aux3Context.class,0);
		}
		public Level3Context level3() {
			return getRuleContext(Level3Context.class,0);
		}
		public Aux2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aux2; }
	}

	public final Aux2Context aux2() throws RecognitionException {
		Aux2Context _localctx = new Aux2Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_aux2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			((Aux2Context)_localctx).aux3 = aux3();
			setState(317);
			((Aux2Context)_localctx).level3 = level3();

			                        ((Aux2Context)_localctx).returnExp =  ((Aux2Context)_localctx).aux3.returnExp;
			                        while(!((Aux2Context)_localctx).level3.stack.empty()){
			                            LeftAsHelper aux = ((Aux2Context)_localctx).level3.stack.pop();
			                            ((Aux2Context)_localctx).returnExp =  LeftAsHelper.createBinExpr(
			                                location(),
			                                _localctx.returnExp,
			                                aux
			                            );
			                        }
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Level3Context extends ParserRuleContext {
		public Stack<LeftAsHelper> stack;
		public Op3Context op3;
		public Aux3Context aux3;
		public Op3Context op3() {
			return getRuleContext(Op3Context.class,0);
		}
		public Aux3Context aux3() {
			return getRuleContext(Aux3Context.class,0);
		}
		public Level3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level3; }
	}

	public final Level3Context level3() throws RecognitionException {
		Level3Context _localctx = new Level3Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_level3);
		try {
			setState(325);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUAL:
			case DIFFER:
			case GREATER:
			case LESSER:
			case LESSER_OR_EQUAL:
			case GREATER_OR_EQUAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				((Level3Context)_localctx).op3 = op3();
				setState(321);
				((Level3Context)_localctx).aux3 = aux3();

				                        ((Level3Context)_localctx).stack =  new Stack<LeftAsHelper>();
				                        _localctx.stack.push(
				                            new LeftAsHelper(
				                                ((Level3Context)_localctx).op3.operator,
				                                ((Level3Context)_localctx).aux3.returnExp
				                            )
				                        );
				                    
				}
				break;
			case DO:
			case ELSE:
			case THEN:
			case CPARENTHESES:
			case CBRACES:
			case CBRACKETS:
			case COMMA:
			case COLON:
			case SEMICOLON:
			case AND:
			case OR:
			case ASIGN:
				enterOuterAlt(_localctx, 2);
				{

				                        ((Level3Context)_localctx).stack =  new Stack<LeftAsHelper>();
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Aux3Context extends ParserRuleContext {
		public AstExpr returnExp;
		public Aux4Context aux4;
		public Level4Context level4;
		public Aux4Context aux4() {
			return getRuleContext(Aux4Context.class,0);
		}
		public Level4Context level4() {
			return getRuleContext(Level4Context.class,0);
		}
		public Aux3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aux3; }
	}

	public final Aux3Context aux3() throws RecognitionException {
		Aux3Context _localctx = new Aux3Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_aux3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			((Aux3Context)_localctx).aux4 = aux4();
			setState(328);
			((Aux3Context)_localctx).level4 = level4();

			                        ((Aux3Context)_localctx).returnExp =  ((Aux3Context)_localctx).aux4.returnExp;
			                        while(!((Aux3Context)_localctx).level4.stack.empty()){
			                            LeftAsHelper aux = ((Aux3Context)_localctx).level4.stack.pop();
			                            ((Aux3Context)_localctx).returnExp =  LeftAsHelper.createBinExpr(
			                                location(),
			                                _localctx.returnExp,
			                                aux
			                            );
			                        }
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Level4Context extends ParserRuleContext {
		public Stack<LeftAsHelper> stack;
		public Op4Context op4;
		public Aux4Context aux4;
		public Level4Context level4;
		public Op4Context op4() {
			return getRuleContext(Op4Context.class,0);
		}
		public Aux4Context aux4() {
			return getRuleContext(Aux4Context.class,0);
		}
		public Level4Context level4() {
			return getRuleContext(Level4Context.class,0);
		}
		public Level4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level4; }
	}

	public final Level4Context level4() throws RecognitionException {
		Level4Context _localctx = new Level4Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_level4);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUM:
			case SUB:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				((Level4Context)_localctx).op4 = op4();
				setState(332);
				((Level4Context)_localctx).aux4 = aux4();
				setState(333);
				((Level4Context)_localctx).level4 = level4();

				                        ((Level4Context)_localctx).stack =  ((Level4Context)_localctx).level4.stack;
				                        _localctx.stack.push(
				                            new LeftAsHelper(
				                                ((Level4Context)_localctx).op4.operator,
				                                ((Level4Context)_localctx).aux4.returnExp
				                            )
				                        );
				                    
				}
				break;
			case DO:
			case ELSE:
			case THEN:
			case CPARENTHESES:
			case CBRACES:
			case CBRACKETS:
			case COMMA:
			case COLON:
			case SEMICOLON:
			case AND:
			case OR:
			case EQUAL:
			case DIFFER:
			case GREATER:
			case LESSER:
			case LESSER_OR_EQUAL:
			case GREATER_OR_EQUAL:
			case ASIGN:
				enterOuterAlt(_localctx, 2);
				{

				                        ((Level4Context)_localctx).stack =  new Stack<LeftAsHelper>();
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Aux4Context extends ParserRuleContext {
		public AstExpr returnExp;
		public PreContext pre;
		public Level5Context level5;
		public PreContext pre() {
			return getRuleContext(PreContext.class,0);
		}
		public Level5Context level5() {
			return getRuleContext(Level5Context.class,0);
		}
		public Aux4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aux4; }
	}

	public final Aux4Context aux4() throws RecognitionException {
		Aux4Context _localctx = new Aux4Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_aux4);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			((Aux4Context)_localctx).pre = pre();
			setState(340);
			((Aux4Context)_localctx).level5 = level5();

			                        ((Aux4Context)_localctx).returnExp =  ((Aux4Context)_localctx).pre.returnExp;
			                        while(!((Aux4Context)_localctx).level5.stack.empty()){
			                            LeftAsHelper aux = ((Aux4Context)_localctx).level5.stack.pop();
			                            ((Aux4Context)_localctx).returnExp =  LeftAsHelper.createBinExpr(
			                                location(),
			                                _localctx.returnExp,
			                                aux
			                            );
			                        }
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Level5Context extends ParserRuleContext {
		public Stack<LeftAsHelper> stack;
		public Op5Context op5;
		public PreContext pre;
		public Level5Context level5;
		public Op5Context op5() {
			return getRuleContext(Op5Context.class,0);
		}
		public PreContext pre() {
			return getRuleContext(PreContext.class,0);
		}
		public Level5Context level5() {
			return getRuleContext(Level5Context.class,0);
		}
		public Level5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level5; }
	}

	public final Level5Context level5() throws RecognitionException {
		Level5Context _localctx = new Level5Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_level5);
		try {
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULT:
			case DIV:
			case REMAINDER:
				enterOuterAlt(_localctx, 1);
				{
				setState(343);
				((Level5Context)_localctx).op5 = op5();
				setState(344);
				((Level5Context)_localctx).pre = pre();
				setState(345);
				((Level5Context)_localctx).level5 = level5();

				                        ((Level5Context)_localctx).stack =  ((Level5Context)_localctx).level5.stack;
				                        _localctx.stack.push(
				                            new LeftAsHelper(
				                                ((Level5Context)_localctx).op5.operator,
				                                ((Level5Context)_localctx).pre.returnExp
				                            )
				                        );
				                    
				}
				break;
			case DO:
			case ELSE:
			case THEN:
			case CPARENTHESES:
			case CBRACES:
			case CBRACKETS:
			case COMMA:
			case COLON:
			case SEMICOLON:
			case AND:
			case OR:
			case EQUAL:
			case DIFFER:
			case GREATER:
			case LESSER:
			case LESSER_OR_EQUAL:
			case GREATER_OR_EQUAL:
			case SUM:
			case SUB:
			case ASIGN:
				enterOuterAlt(_localctx, 2);
				{

				                        ((Level5Context)_localctx).stack =  new Stack<LeftAsHelper>();
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PreContext extends ParserRuleContext {
		public AstExpr returnExp;
		public PreOpsContext preOps;
		public PreContext pre;
		public PosContext pos;
		public PreOpsContext preOps() {
			return getRuleContext(PreOpsContext.class,0);
		}
		public PreContext pre() {
			return getRuleContext(PreContext.class,0);
		}
		public PosContext pos() {
			return getRuleContext(PosContext.class,0);
		}
		public PreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pre; }
	}

	public final PreContext pre() throws RecognitionException {
		PreContext _localctx = new PreContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_pre);
		try {
			setState(358);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case SUM:
			case SUB:
			case CIRCUMFLEX:
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				((PreContext)_localctx).preOps = preOps();
				setState(352);
				((PreContext)_localctx).pre = pre();

				                        ((PreContext)_localctx).returnExp =  new AstPfxExpr(
				                            location(),
				                            ((PreContext)_localctx).preOps.operator, ((PreContext)_localctx).pre.returnExp
				                        );
				                    
				}
				break;
			case DEL:
			case NEW:
			case OPARENTHESES:
			case NONE:
			case TRUE:
			case FALSE:
			case INTEGER:
			case CHARACTER:
			case STRING:
			case NIL:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				((PreContext)_localctx).pos = pos();

				                        ((PreContext)_localctx).returnExp =  ((PreContext)_localctx).pos.returnExp;
				                    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PosContext extends ParserRuleContext {
		public AstExpr returnExp;
		public ExprEndContext exprEnd;
		public ExprPosContext exprPos;
		public ExprEndContext exprEnd() {
			return getRuleContext(ExprEndContext.class,0);
		}
		public ExprPosContext exprPos() {
			return getRuleContext(ExprPosContext.class,0);
		}
		public PosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos; }
	}

	public final PosContext pos() throws RecognitionException {
		PosContext _localctx = new PosContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_pos);
		try {
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(360);
				((PosContext)_localctx).exprEnd = exprEnd();
				setState(361);
				((PosContext)_localctx).exprPos = exprPos();

				                        AstExpr e = ((PosContext)_localctx).exprEnd.returnExp;
				                                                
				                        while(!((PosContext)_localctx).exprPos.v.empty()){
				                            Vector<Object> aux = ((PosContext)_localctx).exprPos.v.pop();
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
				                        ((PosContext)_localctx).returnExp =  e;
				                    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(364);
				((PosContext)_localctx).exprEnd = exprEnd();

				                        ((PosContext)_localctx).returnExp =  ((PosContext)_localctx).exprEnd.returnExp;
				                    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprPosContext extends ParserRuleContext {
		public Stack<Vector<Object>> v;
		public PosOpsContext posOps;
		public ExprPosContext exprPos;
		public PosOpsContext posOps() {
			return getRuleContext(PosOpsContext.class,0);
		}
		public ExprPosContext exprPos() {
			return getRuleContext(ExprPosContext.class,0);
		}
		public ExprPosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprPos; }
	}

	public final ExprPosContext exprPos() throws RecognitionException {
		ExprPosContext _localctx = new ExprPosContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exprPos);
		try {
			setState(376);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				((ExprPosContext)_localctx).posOps = posOps();
				setState(370);
				((ExprPosContext)_localctx).exprPos = exprPos();

				                        ((ExprPosContext)_localctx).exprPos.v.push(((ExprPosContext)_localctx).posOps.r);
				                        ((ExprPosContext)_localctx).v =  ((ExprPosContext)_localctx).exprPos.v;
				                    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				((ExprPosContext)_localctx).posOps = posOps();

				                        ((ExprPosContext)_localctx).v =  new Stack<Vector<Object>>(); _localctx.v.push(((ExprPosContext)_localctx).posOps.r);
				                    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PosOpsContext extends ParserRuleContext {
		public Vector<Object> r;
		public ExprContext expr;
		public Token DOT;
		public Token ID;
		public TerminalNode CIRCUMFLEX() { return getToken(PrevParser.CIRCUMFLEX, 0); }
		public TerminalNode OBRACKETS() { return getToken(PrevParser.OBRACKETS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CBRACKETS() { return getToken(PrevParser.CBRACKETS, 0); }
		public TerminalNode DOT() { return getToken(PrevParser.DOT, 0); }
		public TerminalNode ID() { return getToken(PrevParser.ID, 0); }
		public PosOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_posOps; }
	}

	public final PosOpsContext posOps() throws RecognitionException {
		PosOpsContext _localctx = new PosOpsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_posOps);
		try {
			enterOuterAlt(_localctx, 1);
			{

			                    ((PosOpsContext)_localctx).r =  new Vector<Object>();
			                
			setState(389);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CIRCUMFLEX:
				{
				setState(379);
				match(CIRCUMFLEX);

				                    _localctx.r.add("C");
				                    _localctx.r.add(location());
				                
				}
				break;
			case OBRACKETS:
				{
				setState(381);
				match(OBRACKETS);
				setState(382);
				((PosOpsContext)_localctx).expr = expr();
				setState(383);
				match(CBRACKETS);

				                    _localctx.r.add("A");
				                    _localctx.r.add(((PosOpsContext)_localctx).expr.returnExp);
				                    _localctx.r.add(location());
				                
				}
				break;
			case DOT:
				{
				setState(386);
				((PosOpsContext)_localctx).DOT = match(DOT);
				setState(387);
				((PosOpsContext)_localctx).ID = match(ID);

				                    _localctx.r.add("D");
				                    _localctx.r.add(
				                        new AstNameExpr(
				                            location(((PosOpsContext)_localctx).ID),
				                            (((PosOpsContext)_localctx).ID!=null?((PosOpsContext)_localctx).ID.getText():null)
				                        )
				                    );
				                    _localctx.r.add(
				                        location(((PosOpsContext)_localctx).DOT)      
				                    );
				                
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op1Context extends ParserRuleContext {
		public AstBinExpr.Oper operator;
		public TerminalNode OR() { return getToken(PrevParser.OR, 0); }
		public Op1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op1; }
	}

	public final Op1Context op1() throws RecognitionException {
		Op1Context _localctx = new Op1Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_op1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(OR);
			((Op1Context)_localctx).operator =  AstBinExpr.Oper.OR;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op2Context extends ParserRuleContext {
		public AstBinExpr.Oper operator;
		public TerminalNode AND() { return getToken(PrevParser.AND, 0); }
		public Op2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op2; }
	}

	public final Op2Context op2() throws RecognitionException {
		Op2Context _localctx = new Op2Context(_ctx, getState());
		enterRule(_localctx, 44, RULE_op2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			match(AND);
			((Op2Context)_localctx).operator =  AstBinExpr.Oper.AND;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op3Context extends ParserRuleContext {
		public AstBinExpr.Oper operator;
		public TerminalNode EQUAL() { return getToken(PrevParser.EQUAL, 0); }
		public TerminalNode GREATER() { return getToken(PrevParser.GREATER, 0); }
		public TerminalNode LESSER() { return getToken(PrevParser.LESSER, 0); }
		public TerminalNode DIFFER() { return getToken(PrevParser.DIFFER, 0); }
		public TerminalNode GREATER_OR_EQUAL() { return getToken(PrevParser.GREATER_OR_EQUAL, 0); }
		public TerminalNode LESSER_OR_EQUAL() { return getToken(PrevParser.LESSER_OR_EQUAL, 0); }
		public Op3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op3; }
	}

	public final Op3Context op3() throws RecognitionException {
		Op3Context _localctx = new Op3Context(_ctx, getState());
		enterRule(_localctx, 46, RULE_op3);
		try {
			setState(409);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(397);
				match(EQUAL);
				((Op3Context)_localctx).operator =  AstBinExpr.Oper.EQU;
				}
				break;
			case GREATER:
				enterOuterAlt(_localctx, 2);
				{
				setState(399);
				match(GREATER);
				((Op3Context)_localctx).operator =  AstBinExpr.Oper.GTH;
				}
				break;
			case LESSER:
				enterOuterAlt(_localctx, 3);
				{
				setState(401);
				match(LESSER);
				((Op3Context)_localctx).operator =  AstBinExpr.Oper.LTH;
				}
				break;
			case DIFFER:
				enterOuterAlt(_localctx, 4);
				{
				setState(403);
				match(DIFFER);
				((Op3Context)_localctx).operator =  AstBinExpr.Oper.NEQ;
				}
				break;
			case GREATER_OR_EQUAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(405);
				match(GREATER_OR_EQUAL);
				((Op3Context)_localctx).operator =  AstBinExpr.Oper.GEQ;
				}
				break;
			case LESSER_OR_EQUAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(407);
				match(LESSER_OR_EQUAL);
				((Op3Context)_localctx).operator =  AstBinExpr.Oper.LEQ;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op4Context extends ParserRuleContext {
		public AstBinExpr.Oper operator;
		public TerminalNode SUM() { return getToken(PrevParser.SUM, 0); }
		public TerminalNode SUB() { return getToken(PrevParser.SUB, 0); }
		public Op4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op4; }
	}

	public final Op4Context op4() throws RecognitionException {
		Op4Context _localctx = new Op4Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_op4);
		try {
			setState(415);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(411);
				match(SUM);
				((Op4Context)_localctx).operator =  AstBinExpr.Oper.ADD;
				}
				break;
			case SUB:
				enterOuterAlt(_localctx, 2);
				{
				setState(413);
				match(SUB);
				((Op4Context)_localctx).operator =  AstBinExpr.Oper.SUB;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Op5Context extends ParserRuleContext {
		public AstBinExpr.Oper operator;
		public TerminalNode MULT() { return getToken(PrevParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(PrevParser.DIV, 0); }
		public TerminalNode REMAINDER() { return getToken(PrevParser.REMAINDER, 0); }
		public Op5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op5; }
	}

	public final Op5Context op5() throws RecognitionException {
		Op5Context _localctx = new Op5Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_op5);
		try {
			setState(423);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULT:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				match(MULT);
				((Op5Context)_localctx).operator =  AstBinExpr.Oper.MUL;
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(419);
				match(DIV);
				((Op5Context)_localctx).operator =  AstBinExpr.Oper.DIV;
				}
				break;
			case REMAINDER:
				enterOuterAlt(_localctx, 3);
				{
				setState(421);
				match(REMAINDER);
				((Op5Context)_localctx).operator =  AstBinExpr.Oper.MOD;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PreOpsContext extends ParserRuleContext {
		public AstPfxExpr.Oper operator;
		public TerminalNode NOT() { return getToken(PrevParser.NOT, 0); }
		public TerminalNode SUM() { return getToken(PrevParser.SUM, 0); }
		public TerminalNode SUB() { return getToken(PrevParser.SUB, 0); }
		public TerminalNode CIRCUMFLEX() { return getToken(PrevParser.CIRCUMFLEX, 0); }
		public PreOpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preOps; }
	}

	public final PreOpsContext preOps() throws RecognitionException {
		PreOpsContext _localctx = new PreOpsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_preOps);
		try {
			setState(433);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(425);
				match(NOT);
				((PreOpsContext)_localctx).operator =  AstPfxExpr.Oper.NOT;
				}
				break;
			case SUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(427);
				match(SUM);
				((PreOpsContext)_localctx).operator =  AstPfxExpr.Oper.ADD;
				}
				break;
			case SUB:
				enterOuterAlt(_localctx, 3);
				{
				setState(429);
				match(SUB);
				((PreOpsContext)_localctx).operator =  AstPfxExpr.Oper.SUB;
				}
				break;
			case CIRCUMFLEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(431);
				match(CIRCUMFLEX);
				((PreOpsContext)_localctx).operator =  AstPfxExpr.Oper.PTR;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprEndContext extends ParserRuleContext {
		public AstExpr returnExp;
		public ConstantContext constant;
		public Token ID;
		public Token OPARENTHESES;
		public ExprContext fExpr;
		public ExprContext expr;
		public ExprContext sExpr;
		public TypeContext type;
		public Token NEW;
		public Token DEL;
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode ID() { return getToken(PrevParser.ID, 0); }
		public TerminalNode OPARENTHESES() { return getToken(PrevParser.OPARENTHESES, 0); }
		public TerminalNode CPARENTHESES() { return getToken(PrevParser.CPARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public TerminalNode COLON() { return getToken(PrevParser.COLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public ExprEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprEnd; }
	}

	public final ExprEndContext exprEnd() throws RecognitionException {
		ExprEndContext _localctx = new ExprEndContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_exprEnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(435);
				((ExprEndContext)_localctx).constant = constant();

				                    ((ExprEndContext)_localctx).returnExp =  ((ExprEndContext)_localctx).constant.atomExpr;
				                
				}
				break;
			case 2:
				{
				setState(438);
				((ExprEndContext)_localctx).ID = match(ID);

				                    ((ExprEndContext)_localctx).returnExp =  new AstNameExpr(
				                        location(((ExprEndContext)_localctx).ID),
				                        (((ExprEndContext)_localctx).ID!=null?((ExprEndContext)_localctx).ID.getText():null)
				                    );
				                
				}
				break;
			case 3:
				{
				setState(440);
				((ExprEndContext)_localctx).ID = match(ID);

				                    Vector<AstExpr> exprVector = new Vector<>();
				                
				setState(442);
				((ExprEndContext)_localctx).OPARENTHESES = match(OPARENTHESES);
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35949640773812288L) != 0)) {
					{
					setState(443);
					((ExprEndContext)_localctx).fExpr = ((ExprEndContext)_localctx).expr = expr();

					                    exprVector.add(((ExprEndContext)_localctx).fExpr.returnExp);
					                
					setState(451);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(445);
						match(COMMA);
						setState(446);
						((ExprEndContext)_localctx).sExpr = ((ExprEndContext)_localctx).expr = expr();

						                    exprVector.add(((ExprEndContext)_localctx).sExpr.returnExp);
						                
						}
						}
						setState(453);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(456);
				match(CPARENTHESES);

				                    ((ExprEndContext)_localctx).returnExp =  new AstCallExpr(
				                        location(((ExprEndContext)_localctx).ID),
				                        (((ExprEndContext)_localctx).ID!=null?((ExprEndContext)_localctx).ID.getText():null),
				                        new AstTrees("Expression Tree", exprVector)
				                    );
				                
				}
				break;
			case 4:
				{

				                    AstType t = null;
				                
				setState(459);
				((ExprEndContext)_localctx).OPARENTHESES = match(OPARENTHESES);
				setState(460);
				((ExprEndContext)_localctx).expr = expr();
				setState(465);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(461);
					match(COLON);
					setState(462);
					((ExprEndContext)_localctx).type = type();

					                    t =  ((ExprEndContext)_localctx).type.typeReturn;
					                
					}
				}

				setState(467);
				match(CPARENTHESES);

				                    if(t == null){
				                        ((ExprEndContext)_localctx).returnExp =  ((ExprEndContext)_localctx).expr.returnExp;
				                    }else{
				                       	((ExprEndContext)_localctx).returnExp = new AstCastExpr(
				                            location(((ExprEndContext)_localctx).OPARENTHESES),
				                       	    ((ExprEndContext)_localctx).expr.returnExp,
				                            t
				                        );
				                    }
				                
				}
				break;
			case 5:
				{
				setState(470);
				((ExprEndContext)_localctx).NEW = match(NEW);
				setState(471);
				((ExprEndContext)_localctx).OPARENTHESES = match(OPARENTHESES);
				setState(472);
				((ExprEndContext)_localctx).type = type();
				setState(473);
				match(CPARENTHESES);

				                    ((ExprEndContext)_localctx).returnExp =  new AstNewExpr(
				                        location(((ExprEndContext)_localctx).NEW),
				                        ((ExprEndContext)_localctx).type.typeReturn
				                    );
				                
				}
				break;
			case 6:
				{
				setState(476);
				((ExprEndContext)_localctx).DEL = match(DEL);
				setState(477);
				((ExprEndContext)_localctx).OPARENTHESES = match(OPARENTHESES);
				setState(478);
				((ExprEndContext)_localctx).expr = expr();
				setState(479);
				match(CPARENTHESES);

				                    ((ExprEndContext)_localctx).returnExp =  new AstDelExpr(
				                        location(((ExprEndContext)_localctx).DEL),
				                        ((ExprEndContext)_localctx).expr.returnExp
				                    );
				                
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public AstAtomExpr atomExpr;
		public Token NONE;
		public Token TRUE;
		public Token FALSE;
		public Token INTEGER;
		public Token CHARACTER;
		public Token STRING;
		public Token NIL;
		public TerminalNode NONE() { return getToken(PrevParser.NONE, 0); }
		public TerminalNode TRUE() { return getToken(PrevParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(PrevParser.FALSE, 0); }
		public TerminalNode INTEGER() { return getToken(PrevParser.INTEGER, 0); }
		public TerminalNode CHARACTER() { return getToken(PrevParser.CHARACTER, 0); }
		public TerminalNode STRING() { return getToken(PrevParser.STRING, 0); }
		public TerminalNode NIL() { return getToken(PrevParser.NIL, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				{
				setState(484);
				((ConstantContext)_localctx).NONE = match(NONE);

				                    ((ConstantContext)_localctx).atomExpr =  new AstAtomExpr(
				                        location(((ConstantContext)_localctx).NONE),
				                        AstAtomExpr.Type.VOID, (((ConstantContext)_localctx).NONE!=null?((ConstantContext)_localctx).NONE.getText():null)
				                    );
				                
				}
				break;
			case TRUE:
				{
				setState(486);
				((ConstantContext)_localctx).TRUE = match(TRUE);

				                    ((ConstantContext)_localctx).atomExpr =  new AstAtomExpr(
				                        location(((ConstantContext)_localctx).TRUE),
				                        AstAtomExpr.Type.BOOL, (((ConstantContext)_localctx).TRUE!=null?((ConstantContext)_localctx).TRUE.getText():null));
				                
				}
				break;
			case FALSE:
				{
				setState(488);
				((ConstantContext)_localctx).FALSE = match(FALSE);

				                    ((ConstantContext)_localctx).atomExpr =  new AstAtomExpr(
				                        location(((ConstantContext)_localctx).FALSE),
				                        AstAtomExpr.Type.BOOL, (((ConstantContext)_localctx).FALSE!=null?((ConstantContext)_localctx).FALSE.getText():null));
				                
				}
				break;
			case INTEGER:
				{
				setState(490);
				((ConstantContext)_localctx).INTEGER = match(INTEGER);

				                    ((ConstantContext)_localctx).atomExpr =  new AstAtomExpr(
				                        location(((ConstantContext)_localctx).INTEGER),
				                        AstAtomExpr.Type.INT, (((ConstantContext)_localctx).INTEGER!=null?((ConstantContext)_localctx).INTEGER.getText():null)
				                        );
				                
				}
				break;
			case CHARACTER:
				{
				setState(492);
				((ConstantContext)_localctx).CHARACTER = match(CHARACTER);

				                    ((ConstantContext)_localctx).atomExpr =  new AstAtomExpr(
				                        location(((ConstantContext)_localctx).CHARACTER),
				                        AstAtomExpr.Type.CHAR, (((ConstantContext)_localctx).CHARACTER!=null?((ConstantContext)_localctx).CHARACTER.getText():null)
				                    );
				                
				}
				break;
			case STRING:
				{
				setState(494);
				((ConstantContext)_localctx).STRING = match(STRING);

				                    ((ConstantContext)_localctx).atomExpr =  new AstAtomExpr(
				                        location(((ConstantContext)_localctx).STRING),
				                        AstAtomExpr.Type.STR, (((ConstantContext)_localctx).STRING!=null?((ConstantContext)_localctx).STRING.getText():null)
				                    );
				                
				}
				break;
			case NIL:
				{
				setState(496);
				((ConstantContext)_localctx).NIL = match(NIL);

				                    ((ConstantContext)_localctx).atomExpr =  new AstAtomExpr(
				                        location(((ConstantContext)_localctx).NIL),
				                        AstAtomExpr.Type.PTR,
				                        (((ConstantContext)_localctx).NIL!=null?((ConstantContext)_localctx).NIL.getText():null)
				                    );
				                
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u00018\u01f5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001I\b\u0001"+
		"\u000b\u0001\f\u0001J\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002[\b\u0002"+
		"\n\u0002\f\u0002^\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003r\b\u0003\n\u0003\f\u0003u\t\u0003"+
		"\u0003\u0003w\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0080\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003\u0091\b\u0003\n\u0003\f\u0003\u0094"+
		"\t\u0003\u0003\u0003\u0096\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u009f\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003\u00a3\b\u0003\n\u0003\f\u0003\u00a6"+
		"\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u00b8"+
		"\b\u0004\n\u0004\f\u0004\u00bb\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00e1\b\u0005\n\u0005"+
		"\f\u0005\u00e4\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00ee\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006\u00f6\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u0103\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006\u011b\b\u0006\n\u0006\f\u0006\u011e\t\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0123\b\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0003\b\u012f\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u013b\b\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0003\f\u0146\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0152\b\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u015e\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u0167\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0170\b\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u0179\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u0186\b\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u019a\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01a0\b\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u01a8\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u01b2\b\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0005\u001b\u01c2\b\u001b\n\u001b\f\u001b\u01c5"+
		"\t\u001b\u0003\u001b\u01c7\b\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0003\u001b\u01d2\b\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b"+
		"\u01e3\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u01f3\b\u001c\u0001\u001c"+
		"\u0000\u0000\u001d\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468\u0000\u0000\u0216\u0000:"+
		"\u0001\u0000\u0000\u0000\u0002>\u0001\u0000\u0000\u0000\u0004N\u0001\u0000"+
		"\u0000\u0000\u0006c\u0001\u0000\u0000\u0000\b\u00ab\u0001\u0000\u0000"+
		"\u0000\n\u00ed\u0001\u0000\u0000\u0000\f\u0122\u0001\u0000\u0000\u0000"+
		"\u000e\u0124\u0001\u0000\u0000\u0000\u0010\u012e\u0001\u0000\u0000\u0000"+
		"\u0012\u0130\u0001\u0000\u0000\u0000\u0014\u013a\u0001\u0000\u0000\u0000"+
		"\u0016\u013c\u0001\u0000\u0000\u0000\u0018\u0145\u0001\u0000\u0000\u0000"+
		"\u001a\u0147\u0001\u0000\u0000\u0000\u001c\u0151\u0001\u0000\u0000\u0000"+
		"\u001e\u0153\u0001\u0000\u0000\u0000 \u015d\u0001\u0000\u0000\u0000\""+
		"\u0166\u0001\u0000\u0000\u0000$\u016f\u0001\u0000\u0000\u0000&\u0178\u0001"+
		"\u0000\u0000\u0000(\u017a\u0001\u0000\u0000\u0000*\u0187\u0001\u0000\u0000"+
		"\u0000,\u018a\u0001\u0000\u0000\u0000.\u0199\u0001\u0000\u0000\u00000"+
		"\u019f\u0001\u0000\u0000\u00002\u01a7\u0001\u0000\u0000\u00004\u01b1\u0001"+
		"\u0000\u0000\u00006\u01e2\u0001\u0000\u0000\u00008\u01f2\u0001\u0000\u0000"+
		"\u0000:;\u0003\u0002\u0001\u0000;<\u0005\u0000\u0000\u0001<=\u0006\u0000"+
		"\uffff\uffff\u0000=\u0001\u0001\u0000\u0000\u0000>H\u0006\u0001\uffff"+
		"\uffff\u0000?@\u0003\u0004\u0002\u0000@A\u0006\u0001\uffff\uffff\u0000"+
		"AI\u0001\u0000\u0000\u0000BC\u0003\u0006\u0003\u0000CD\u0006\u0001\uffff"+
		"\uffff\u0000DI\u0001\u0000\u0000\u0000EF\u0003\b\u0004\u0000FG\u0006\u0001"+
		"\uffff\uffff\u0000GI\u0001\u0000\u0000\u0000H?\u0001\u0000\u0000\u0000"+
		"HB\u0001\u0000\u0000\u0000HE\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000"+
		"\u0000JH\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0001\u0000"+
		"\u0000\u0000LM\u0006\u0001\uffff\uffff\u0000M\u0003\u0001\u0000\u0000"+
		"\u0000NO\u0006\u0002\uffff\uffff\u0000OP\u0005\u0010\u0000\u0000PQ\u0005"+
		"6\u0000\u0000QR\u0005.\u0000\u0000RS\u0003\n\u0005\u0000S\\\u0006\u0002"+
		"\uffff\uffff\u0000TU\u0005\u001c\u0000\u0000UV\u00056\u0000\u0000VW\u0005"+
		".\u0000\u0000WX\u0003\n\u0005\u0000XY\u0006\u0002\uffff\uffff\u0000Y["+
		"\u0001\u0000\u0000\u0000ZT\u0001\u0000\u0000\u0000[^\u0001\u0000\u0000"+
		"\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]_\u0001\u0000"+
		"\u0000\u0000^\\\u0001\u0000\u0000\u0000_`\u0005\u001e\u0000\u0000`a\u0001"+
		"\u0000\u0000\u0000ab\u0006\u0002\uffff\uffff\u0000b\u0005\u0001\u0000"+
		"\u0000\u0000cd\u0006\u0003\uffff\uffff\u0000de\u0005\t\u0000\u0000ef\u0005"+
		"6\u0000\u0000fv\u0005\u0015\u0000\u0000gh\u00056\u0000\u0000hi\u0005\u001d"+
		"\u0000\u0000ij\u0003\n\u0005\u0000js\u0006\u0003\uffff\uffff\u0000kl\u0005"+
		"\u001c\u0000\u0000lm\u00056\u0000\u0000mn\u0005\u001d\u0000\u0000no\u0003"+
		"\n\u0005\u0000op\u0006\u0003\uffff\uffff\u0000pr\u0001\u0000\u0000\u0000"+
		"qk\u0001\u0000\u0000\u0000ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000"+
		"\u0000st\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000us\u0001\u0000"+
		"\u0000\u0000vg\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000xy\u0005\u0018\u0000\u0000yz\u0005\u001d\u0000\u0000"+
		"z\u007f\u0003\n\u0005\u0000{|\u0005.\u0000\u0000|}\u0003\f\u0006\u0000"+
		"}~\u0006\u0003\uffff\uffff\u0000~\u0080\u0001\u0000\u0000\u0000\u007f"+
		"{\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081"+
		"\u0001\u0000\u0000\u0000\u0081\u00a4\u0006\u0003\uffff\uffff\u0000\u0082"+
		"\u0083\u0006\u0003\uffff\uffff\u0000\u0083\u0084\u0005\u001c\u0000\u0000"+
		"\u0084\u0085\u00056\u0000\u0000\u0085\u0095\u0005\u0015\u0000\u0000\u0086"+
		"\u0087\u00056\u0000\u0000\u0087\u0088\u0005\u001d\u0000\u0000\u0088\u0089"+
		"\u0003\n\u0005\u0000\u0089\u0092\u0006\u0003\uffff\uffff\u0000\u008a\u008b"+
		"\u0005\u001c\u0000\u0000\u008b\u008c\u00056\u0000\u0000\u008c\u008d\u0005"+
		"\u001d\u0000\u0000\u008d\u008e\u0003\n\u0005\u0000\u008e\u008f\u0006\u0003"+
		"\uffff\uffff\u0000\u008f\u0091\u0001\u0000\u0000\u0000\u0090\u008a\u0001"+
		"\u0000\u0000\u0000\u0091\u0094\u0001\u0000\u0000\u0000\u0092\u0090\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0096\u0001"+
		"\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0095\u0086\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0005\u0018\u0000\u0000\u0098\u0099\u0005"+
		"\u001d\u0000\u0000\u0099\u009e\u0003\n\u0005\u0000\u009a\u009b\u0005."+
		"\u0000\u0000\u009b\u009c\u0003\f\u0006\u0000\u009c\u009d\u0006\u0003\uffff"+
		"\uffff\u0000\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u009a\u0001\u0000"+
		"\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0006\u0003\uffff\uffff\u0000\u00a1\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a2\u0082\u0001\u0000\u0000\u0000\u00a3\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a7\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0005\u001e\u0000\u0000\u00a8\u00a9\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0006\u0003\uffff\uffff\u0000\u00aa\u0007"+
		"\u0001\u0000\u0000\u0000\u00ab\u00ac\u0006\u0004\uffff\uffff\u0000\u00ac"+
		"\u00ad\u0005\u0011\u0000\u0000\u00ad\u00ae\u00056\u0000\u0000\u00ae\u00af"+
		"\u0005\u001d\u0000\u0000\u00af\u00b0\u0003\n\u0005\u0000\u00b0\u00b9\u0006"+
		"\u0004\uffff\uffff\u0000\u00b1\u00b2\u0005\u001c\u0000\u0000\u00b2\u00b3"+
		"\u00056\u0000\u0000\u00b3\u00b4\u0005\u001d\u0000\u0000\u00b4\u00b5\u0003"+
		"\n\u0005\u0000\u00b5\u00b6\u0006\u0004\uffff\uffff\u0000\u00b6\u00b8\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b1\u0001\u0000\u0000\u0000\u00b8\u00bb\u0001"+
		"\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bc\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001"+
		"\u0000\u0000\u0000\u00bc\u00bd\u0005\u001e\u0000\u0000\u00bd\u00be\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0006\u0004\uffff\uffff\u0000\u00bf\t\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c1\u0005\u0012\u0000\u0000\u00c1\u00ee\u0006"+
		"\u0005\uffff\uffff\u0000\u00c2\u00c3\u0005\u0005\u0000\u0000\u00c3\u00ee"+
		"\u0006\u0005\uffff\uffff\u0000\u00c4\u00c5\u0005\f\u0000\u0000\u00c5\u00ee"+
		"\u0006\u0005\uffff\uffff\u0000\u00c6\u00c7\u0005\u0004\u0000\u0000\u00c7"+
		"\u00ee\u0006\u0005\uffff\uffff\u0000\u00c8\u00c9\u00056\u0000\u0000\u00c9"+
		"\u00ee\u0006\u0005\uffff\uffff\u0000\u00ca\u00cb\u0005\u0017\u0000\u0000"+
		"\u00cb\u00cc\u0003\u000e\u0007\u0000\u00cc\u00cd\u0005\u001a\u0000\u0000"+
		"\u00cd\u00ce\u0003\n\u0005\u0000\u00ce\u00cf\u0006\u0005\uffff\uffff\u0000"+
		"\u00cf\u00ee\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005-\u0000\u0000\u00d1"+
		"\u00d2\u0003\n\u0005\u0000\u00d2\u00d3\u0006\u0005\uffff\uffff\u0000\u00d3"+
		"\u00ee\u0001\u0000\u0000\u0000\u00d4\u00d5\u0006\u0005\uffff\uffff\u0000"+
		"\u00d5\u00d6\u0005\u0016\u0000\u0000\u00d6\u00d7\u00056\u0000\u0000\u00d7"+
		"\u00d8\u0005\u001d\u0000\u0000\u00d8\u00d9\u0003\n\u0005\u0000\u00d9\u00e2"+
		"\u0006\u0005\uffff\uffff\u0000\u00da\u00db\u0005\u001c\u0000\u0000\u00db"+
		"\u00dc\u00056\u0000\u0000\u00dc\u00dd\u0005\u001d\u0000\u0000\u00dd\u00de"+
		"\u0003\n\u0005\u0000\u00de\u00df\u0006\u0005\uffff\uffff\u0000\u00df\u00e1"+
		"\u0001\u0000\u0000\u0000\u00e0\u00da\u0001\u0000\u0000\u0000\u00e1\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e5\u0001\u0000\u0000\u0000\u00e4\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\u0019\u0000\u0000\u00e6\u00e7"+
		"\u0006\u0005\uffff\uffff\u0000\u00e7\u00ee\u0001\u0000\u0000\u0000\u00e8"+
		"\u00e9\u0005\u0015\u0000\u0000\u00e9\u00ea\u0003\n\u0005\u0000\u00ea\u00eb"+
		"\u0005\u0018\u0000\u0000\u00eb\u00ec\u0006\u0005\uffff\uffff\u0000\u00ec"+
		"\u00ee\u0001\u0000\u0000\u0000\u00ed\u00c0\u0001\u0000\u0000\u0000\u00ed"+
		"\u00c2\u0001\u0000\u0000\u0000\u00ed\u00c4\u0001\u0000\u0000\u0000\u00ed"+
		"\u00c6\u0001\u0000\u0000\u0000\u00ed\u00c8\u0001\u0000\u0000\u0000\u00ed"+
		"\u00ca\u0001\u0000\u0000\u0000\u00ed\u00d0\u0001\u0000\u0000\u0000\u00ed"+
		"\u00d4\u0001\u0000\u0000\u0000\u00ed\u00e8\u0001\u0000\u0000\u0000\u00ee"+
		"\u000b\u0001\u0000\u0000\u0000\u00ef\u00f0\u0006\u0006\uffff\uffff\u0000"+
		"\u00f0\u00f5\u0003\u000e\u0007\u0000\u00f1\u00f2\u0005.\u0000\u0000\u00f2"+
		"\u00f3\u0003\u000e\u0007\u0000\u00f3\u00f4\u0006\u0006\uffff\uffff\u0000"+
		"\u00f4\u00f6\u0001\u0000\u0000\u0000\u00f5\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000"+
		"\u00f7\u00f8\u0006\u0006\uffff\uffff\u0000\u00f8\u0123\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fa\u0006\u0006\uffff\uffff\u0000\u00fa\u00fb\u0005\n\u0000"+
		"\u0000\u00fb\u00fc\u0003\u000e\u0007\u0000\u00fc\u00fd\u0005\u000f\u0000"+
		"\u0000\u00fd\u0102\u0003\f\u0006\u0000\u00fe\u00ff\u0005\b\u0000\u0000"+
		"\u00ff\u0100\u0003\f\u0006\u0000\u0100\u0101\u0006\u0006\uffff\uffff\u0000"+
		"\u0101\u0103\u0001\u0000\u0000\u0000\u0102\u00fe\u0001\u0000\u0000\u0000"+
		"\u0102\u0103\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000"+
		"\u0104\u0105\u0006\u0006\uffff\uffff\u0000\u0105\u0123\u0001\u0000\u0000"+
		"\u0000\u0106\u0107\u0005\u0013\u0000\u0000\u0107\u0108\u0003\u000e\u0007"+
		"\u0000\u0108\u0109\u0005\u0007\u0000\u0000\u0109\u010a\u0003\f\u0006\u0000"+
		"\u010a\u010b\u0006\u0006\uffff\uffff\u0000\u010b\u0123\u0001\u0000\u0000"+
		"\u0000\u010c\u010d\u0005\r\u0000\u0000\u010d\u010e\u0003\u0002\u0001\u0000"+
		"\u010e\u010f\u0005\u000b\u0000\u0000\u010f\u0110\u0003\f\u0006\u0000\u0110"+
		"\u0111\u0006\u0006\uffff\uffff\u0000\u0111\u0123\u0001\u0000\u0000\u0000"+
		"\u0112\u0113\u0006\u0006\uffff\uffff\u0000\u0113\u0114\u0005\u0016\u0000"+
		"\u0000\u0114\u0115\u0003\f\u0006\u0000\u0115\u011c\u0006\u0006\uffff\uffff"+
		"\u0000\u0116\u0117\u0005\u001e\u0000\u0000\u0117\u0118\u0003\f\u0006\u0000"+
		"\u0118\u0119\u0006\u0006\uffff\uffff\u0000\u0119\u011b\u0001\u0000\u0000"+
		"\u0000\u011a\u0116\u0001\u0000\u0000\u0000\u011b\u011e\u0001\u0000\u0000"+
		"\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000"+
		"\u0000\u011d\u011f\u0001\u0000\u0000\u0000\u011e\u011c\u0001\u0000\u0000"+
		"\u0000\u011f\u0120\u0005\u0019\u0000\u0000\u0120\u0121\u0006\u0006\uffff"+
		"\uffff\u0000\u0121\u0123\u0001\u0000\u0000\u0000\u0122\u00ef\u0001\u0000"+
		"\u0000\u0000\u0122\u00f9\u0001\u0000\u0000\u0000\u0122\u0106\u0001\u0000"+
		"\u0000\u0000\u0122\u010c\u0001\u0000\u0000\u0000\u0122\u0112\u0001\u0000"+
		"\u0000\u0000\u0123\r\u0001\u0000\u0000\u0000\u0124\u0125\u0003\u0012\t"+
		"\u0000\u0125\u0126\u0003\u0010\b\u0000\u0126\u0127\u0006\u0007\uffff\uffff"+
		"\u0000\u0127\u000f\u0001\u0000\u0000\u0000\u0128\u0129\u0003*\u0015\u0000"+
		"\u0129\u012a\u0003\u0012\t\u0000\u012a\u012b\u0003\u0010\b\u0000\u012b"+
		"\u012c\u0006\b\uffff\uffff\u0000\u012c\u012f\u0001\u0000\u0000\u0000\u012d"+
		"\u012f\u0006\b\uffff\uffff\u0000\u012e\u0128\u0001\u0000\u0000\u0000\u012e"+
		"\u012d\u0001\u0000\u0000\u0000\u012f\u0011\u0001\u0000\u0000\u0000\u0130"+
		"\u0131\u0003\u0016\u000b\u0000\u0131\u0132\u0003\u0014\n\u0000\u0132\u0133"+
		"\u0006\t\uffff\uffff\u0000\u0133\u0013\u0001\u0000\u0000\u0000\u0134\u0135"+
		"\u0003,\u0016\u0000\u0135\u0136\u0003\u0016\u000b\u0000\u0136\u0137\u0003"+
		"\u0014\n\u0000\u0137\u0138\u0006\n\uffff\uffff\u0000\u0138\u013b\u0001"+
		"\u0000\u0000\u0000\u0139\u013b\u0006\n\uffff\uffff\u0000\u013a\u0134\u0001"+
		"\u0000\u0000\u0000\u013a\u0139\u0001\u0000\u0000\u0000\u013b\u0015\u0001"+
		"\u0000\u0000\u0000\u013c\u013d\u0003\u001a\r\u0000\u013d\u013e\u0003\u0018"+
		"\f\u0000\u013e\u013f\u0006\u000b\uffff\uffff\u0000\u013f\u0017\u0001\u0000"+
		"\u0000\u0000\u0140\u0141\u0003.\u0017\u0000\u0141\u0142\u0003\u001a\r"+
		"\u0000\u0142\u0143\u0006\f\uffff\uffff\u0000\u0143\u0146\u0001\u0000\u0000"+
		"\u0000\u0144\u0146\u0006\f\uffff\uffff\u0000\u0145\u0140\u0001\u0000\u0000"+
		"\u0000\u0145\u0144\u0001\u0000\u0000\u0000\u0146\u0019\u0001\u0000\u0000"+
		"\u0000\u0147\u0148\u0003\u001e\u000f\u0000\u0148\u0149\u0003\u001c\u000e"+
		"\u0000\u0149\u014a\u0006\r\uffff\uffff\u0000\u014a\u001b\u0001\u0000\u0000"+
		"\u0000\u014b\u014c\u00030\u0018\u0000\u014c\u014d\u0003\u001e\u000f\u0000"+
		"\u014d\u014e\u0003\u001c\u000e\u0000\u014e\u014f\u0006\u000e\uffff\uffff"+
		"\u0000\u014f\u0152\u0001\u0000\u0000\u0000\u0150\u0152\u0006\u000e\uffff"+
		"\uffff\u0000\u0151\u014b\u0001\u0000\u0000\u0000\u0151\u0150\u0001\u0000"+
		"\u0000\u0000\u0152\u001d\u0001\u0000\u0000\u0000\u0153\u0154\u0003\"\u0011"+
		"\u0000\u0154\u0155\u0003 \u0010\u0000\u0155\u0156\u0006\u000f\uffff\uffff"+
		"\u0000\u0156\u001f\u0001\u0000\u0000\u0000\u0157\u0158\u00032\u0019\u0000"+
		"\u0158\u0159\u0003\"\u0011\u0000\u0159\u015a\u0003 \u0010\u0000\u015a"+
		"\u015b\u0006\u0010\uffff\uffff\u0000\u015b\u015e\u0001\u0000\u0000\u0000"+
		"\u015c\u015e\u0006\u0010\uffff\uffff\u0000\u015d\u0157\u0001\u0000\u0000"+
		"\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015e!\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u00034\u001a\u0000\u0160\u0161\u0003\"\u0011\u0000\u0161"+
		"\u0162\u0006\u0011\uffff\uffff\u0000\u0162\u0167\u0001\u0000\u0000\u0000"+
		"\u0163\u0164\u0003$\u0012\u0000\u0164\u0165\u0006\u0011\uffff\uffff\u0000"+
		"\u0165\u0167\u0001\u0000\u0000\u0000\u0166\u015f\u0001\u0000\u0000\u0000"+
		"\u0166\u0163\u0001\u0000\u0000\u0000\u0167#\u0001\u0000\u0000\u0000\u0168"+
		"\u0169\u00036\u001b\u0000\u0169\u016a\u0003&\u0013\u0000\u016a\u016b\u0006"+
		"\u0012\uffff\uffff\u0000\u016b\u0170\u0001\u0000\u0000\u0000\u016c\u016d"+
		"\u00036\u001b\u0000\u016d\u016e\u0006\u0012\uffff\uffff\u0000\u016e\u0170"+
		"\u0001\u0000\u0000\u0000\u016f\u0168\u0001\u0000\u0000\u0000\u016f\u016c"+
		"\u0001\u0000\u0000\u0000\u0170%\u0001\u0000\u0000\u0000\u0171\u0172\u0003"+
		"(\u0014\u0000\u0172\u0173\u0003&\u0013\u0000\u0173\u0174\u0006\u0013\uffff"+
		"\uffff\u0000\u0174\u0179\u0001\u0000\u0000\u0000\u0175\u0176\u0003(\u0014"+
		"\u0000\u0176\u0177\u0006\u0013\uffff\uffff\u0000\u0177\u0179\u0001\u0000"+
		"\u0000\u0000\u0178\u0171\u0001\u0000\u0000\u0000\u0178\u0175\u0001\u0000"+
		"\u0000\u0000\u0179\'\u0001\u0000\u0000\u0000\u017a\u0185\u0006\u0014\uffff"+
		"\uffff\u0000\u017b\u017c\u0005-\u0000\u0000\u017c\u0186\u0006\u0014\uffff"+
		"\uffff\u0000\u017d\u017e\u0005\u0017\u0000\u0000\u017e\u017f\u0003\u000e"+
		"\u0007\u0000\u017f\u0180\u0005\u001a\u0000\u0000\u0180\u0181\u0006\u0014"+
		"\uffff\uffff\u0000\u0181\u0186\u0001\u0000\u0000\u0000\u0182\u0183\u0005"+
		"\u001b\u0000\u0000\u0183\u0184\u00056\u0000\u0000\u0184\u0186\u0006\u0014"+
		"\uffff\uffff\u0000\u0185\u017b\u0001\u0000\u0000\u0000\u0185\u017d\u0001"+
		"\u0000\u0000\u0000\u0185\u0182\u0001\u0000\u0000\u0000\u0186)\u0001\u0000"+
		"\u0000\u0000\u0187\u0188\u0005 \u0000\u0000\u0188\u0189\u0006\u0015\uffff"+
		"\uffff\u0000\u0189+\u0001\u0000\u0000\u0000\u018a\u018b\u0005\u001f\u0000"+
		"\u0000\u018b\u018c\u0006\u0016\uffff\uffff\u0000\u018c-\u0001\u0000\u0000"+
		"\u0000\u018d\u018e\u0005\"\u0000\u0000\u018e\u019a\u0006\u0017\uffff\uffff"+
		"\u0000\u018f\u0190\u0005$\u0000\u0000\u0190\u019a\u0006\u0017\uffff\uffff"+
		"\u0000\u0191\u0192\u0005%\u0000\u0000\u0192\u019a\u0006\u0017\uffff\uffff"+
		"\u0000\u0193\u0194\u0005#\u0000\u0000\u0194\u019a\u0006\u0017\uffff\uffff"+
		"\u0000\u0195\u0196\u0005\'\u0000\u0000\u0196\u019a\u0006\u0017\uffff\uffff"+
		"\u0000\u0197\u0198\u0005&\u0000\u0000\u0198\u019a\u0006\u0017\uffff\uffff"+
		"\u0000\u0199\u018d\u0001\u0000\u0000\u0000\u0199\u018f\u0001\u0000\u0000"+
		"\u0000\u0199\u0191\u0001\u0000\u0000\u0000\u0199\u0193\u0001\u0000\u0000"+
		"\u0000\u0199\u0195\u0001\u0000\u0000\u0000\u0199\u0197\u0001\u0000\u0000"+
		"\u0000\u019a/\u0001\u0000\u0000\u0000\u019b\u019c\u0005+\u0000\u0000\u019c"+
		"\u01a0\u0006\u0018\uffff\uffff\u0000\u019d\u019e\u0005,\u0000\u0000\u019e"+
		"\u01a0\u0006\u0018\uffff\uffff\u0000\u019f\u019b\u0001\u0000\u0000\u0000"+
		"\u019f\u019d\u0001\u0000\u0000\u0000\u01a01\u0001\u0000\u0000\u0000\u01a1"+
		"\u01a2\u0005(\u0000\u0000\u01a2\u01a8\u0006\u0019\uffff\uffff\u0000\u01a3"+
		"\u01a4\u0005)\u0000\u0000\u01a4\u01a8\u0006\u0019\uffff\uffff\u0000\u01a5"+
		"\u01a6\u0005*\u0000\u0000\u01a6\u01a8\u0006\u0019\uffff\uffff\u0000\u01a7"+
		"\u01a1\u0001\u0000\u0000\u0000\u01a7\u01a3\u0001\u0000\u0000\u0000\u01a7"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a83\u0001\u0000\u0000\u0000\u01a9\u01aa"+
		"\u0005!\u0000\u0000\u01aa\u01b2\u0006\u001a\uffff\uffff\u0000\u01ab\u01ac"+
		"\u0005+\u0000\u0000\u01ac\u01b2\u0006\u001a\uffff\uffff\u0000\u01ad\u01ae"+
		"\u0005,\u0000\u0000\u01ae\u01b2\u0006\u001a\uffff\uffff\u0000\u01af\u01b0"+
		"\u0005-\u0000\u0000\u01b0\u01b2\u0006\u001a\uffff\uffff\u0000\u01b1\u01a9"+
		"\u0001\u0000\u0000\u0000\u01b1\u01ab\u0001\u0000\u0000\u0000\u01b1\u01ad"+
		"\u0001\u0000\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b25\u0001"+
		"\u0000\u0000\u0000\u01b3\u01b4\u00038\u001c\u0000\u01b4\u01b5\u0006\u001b"+
		"\uffff\uffff\u0000\u01b5\u01e3\u0001\u0000\u0000\u0000\u01b6\u01b7\u0005"+
		"6\u0000\u0000\u01b7\u01e3\u0006\u001b\uffff\uffff\u0000\u01b8\u01b9\u0005"+
		"6\u0000\u0000\u01b9\u01ba\u0006\u001b\uffff\uffff\u0000\u01ba\u01c6\u0005"+
		"\u0015\u0000\u0000\u01bb\u01bc\u0003\u000e\u0007\u0000\u01bc\u01c3\u0006"+
		"\u001b\uffff\uffff\u0000\u01bd\u01be\u0005\u001c\u0000\u0000\u01be\u01bf"+
		"\u0003\u000e\u0007\u0000\u01bf\u01c0\u0006\u001b\uffff\uffff\u0000\u01c0"+
		"\u01c2\u0001\u0000\u0000\u0000\u01c1\u01bd\u0001\u0000\u0000\u0000\u01c2"+
		"\u01c5\u0001\u0000\u0000\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c3"+
		"\u01c4\u0001\u0000\u0000\u0000\u01c4\u01c7\u0001\u0000\u0000\u0000\u01c5"+
		"\u01c3\u0001\u0000\u0000\u0000\u01c6\u01bb\u0001\u0000\u0000\u0000\u01c6"+
		"\u01c7\u0001\u0000\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8"+
		"\u01c9\u0005\u0018\u0000\u0000\u01c9\u01e3\u0006\u001b\uffff\uffff\u0000"+
		"\u01ca\u01cb\u0006\u001b\uffff\uffff\u0000\u01cb\u01cc\u0005\u0015\u0000"+
		"\u0000\u01cc\u01d1\u0003\u000e\u0007\u0000\u01cd\u01ce\u0005\u001d\u0000"+
		"\u0000\u01ce\u01cf\u0003\n\u0005\u0000\u01cf\u01d0\u0006\u001b\uffff\uffff"+
		"\u0000\u01d0\u01d2\u0001\u0000\u0000\u0000\u01d1\u01cd\u0001\u0000\u0000"+
		"\u0000\u01d1\u01d2\u0001\u0000\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000"+
		"\u0000\u01d3\u01d4\u0005\u0018\u0000\u0000\u01d4\u01d5\u0006\u001b\uffff"+
		"\uffff\u0000\u01d5\u01e3\u0001\u0000\u0000\u0000\u01d6\u01d7\u0005\u000e"+
		"\u0000\u0000\u01d7\u01d8\u0005\u0015\u0000\u0000\u01d8\u01d9\u0003\n\u0005"+
		"\u0000\u01d9\u01da\u0005\u0018\u0000\u0000\u01da\u01db\u0006\u001b\uffff"+
		"\uffff\u0000\u01db\u01e3\u0001\u0000\u0000\u0000\u01dc\u01dd\u0005\u0006"+
		"\u0000\u0000\u01dd\u01de\u0005\u0015\u0000\u0000\u01de\u01df\u0003\u000e"+
		"\u0007\u0000\u01df\u01e0\u0005\u0018\u0000\u0000\u01e0\u01e1\u0006\u001b"+
		"\uffff\uffff\u0000\u01e1\u01e3\u0001\u0000\u0000\u0000\u01e2\u01b3\u0001"+
		"\u0000\u0000\u0000\u01e2\u01b6\u0001\u0000\u0000\u0000\u01e2\u01b8\u0001"+
		"\u0000\u0000\u0000\u01e2\u01ca\u0001\u0000\u0000\u0000\u01e2\u01d6\u0001"+
		"\u0000\u0000\u0000\u01e2\u01dc\u0001\u0000\u0000\u0000\u01e37\u0001\u0000"+
		"\u0000\u0000\u01e4\u01e5\u0005/\u0000\u0000\u01e5\u01f3\u0006\u001c\uffff"+
		"\uffff\u0000\u01e6\u01e7\u00050\u0000\u0000\u01e7\u01f3\u0006\u001c\uffff"+
		"\uffff\u0000\u01e8\u01e9\u00051\u0000\u0000\u01e9\u01f3\u0006\u001c\uffff"+
		"\uffff\u0000\u01ea\u01eb\u00052\u0000\u0000\u01eb\u01f3\u0006\u001c\uffff"+
		"\uffff\u0000\u01ec\u01ed\u00053\u0000\u0000\u01ed\u01f3\u0006\u001c\uffff"+
		"\uffff\u0000\u01ee\u01ef\u00054\u0000\u0000\u01ef\u01f3\u0006\u001c\uffff"+
		"\uffff\u0000\u01f0\u01f1\u00055\u0000\u0000\u01f1\u01f3\u0006\u001c\uffff"+
		"\uffff\u0000\u01f2\u01e4\u0001\u0000\u0000\u0000\u01f2\u01e6\u0001\u0000"+
		"\u0000\u0000\u01f2\u01e8\u0001\u0000\u0000\u0000\u01f2\u01ea\u0001\u0000"+
		"\u0000\u0000\u01f2\u01ec\u0001\u0000\u0000\u0000\u01f2\u01ee\u0001\u0000"+
		"\u0000\u0000\u01f2\u01f0\u0001\u0000\u0000\u0000\u01f39\u0001\u0000\u0000"+
		"\u0000#HJ\\sv\u007f\u0092\u0095\u009e\u00a4\u00b9\u00e2\u00ed\u00f5\u0102"+
		"\u011c\u0122\u012e\u013a\u0145\u0151\u015d\u0166\u016f\u0178\u0185\u0199"+
		"\u019f\u01a7\u01b1\u01c3\u01c6\u01d1\u01e2\u01f2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}