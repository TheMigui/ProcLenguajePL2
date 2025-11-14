// Generated from c:/Users/tizia/Desktop/Universidad/Procesadores del lenguaje/ProcLenguajePL2/IKEA/IKEA.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IKEAParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, NOMBRE=17, 
		ID=18, NUM=19, STRING=20, WS=21, COMENTARIO=22;
	public static final int
		RULE_prog = 0, RULE_declaracion = 1, RULE_paso = 2, RULE_instruccion = 3, 
		RULE_herramienta = 4, RULE_herraje = 5, RULE_pieza = 6, RULE_montaje = 7, 
		RULE_cantidad = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "declaracion", "paso", "instruccion", "herramienta", "herraje", 
			"pieza", "montaje", "cantidad"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "','", "'Usable'", "'PASO '", "':'", "' CON '", "' MONTAR'", 
			"'{'", "'}'", "' FIJAR'", "'NOTA '", "'HERRAMIENTA '", "'HERRAJE '", 
			"'PIEZA '", "'MONTAJE '", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "NOMBRE", "ID", "NUM", "STRING", "WS", 
			"COMENTARIO"
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
	public String getGrammarFileName() { return "IKEA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public IKEAParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public DeclaracionContext declaracion() {
			return getRuleContext(DeclaracionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(IKEAParser.EOF, 0); }
		public List<PasoContext> paso() {
			return getRuleContexts(PasoContext.class);
		}
		public PasoContext paso(int i) {
			return getRuleContext(PasoContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			declaracion();
			setState(20); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(19);
				paso();
				}
				}
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			setState(24);
			match(EOF);
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
	public static class DeclaracionContext extends ParserRuleContext {
		public List<HerramientaContext> herramienta() {
			return getRuleContexts(HerramientaContext.class);
		}
		public HerramientaContext herramienta(int i) {
			return getRuleContext(HerramientaContext.class,i);
		}
		public List<HerrajeContext> herraje() {
			return getRuleContexts(HerrajeContext.class);
		}
		public HerrajeContext herraje(int i) {
			return getRuleContext(HerrajeContext.class,i);
		}
		public DeclaracionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion; }
	}

	public final DeclaracionContext declaracion() throws RecognitionException {
		DeclaracionContext _localctx = new DeclaracionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaracion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(26);
				herramienta();
				setState(27);
				match(T__0);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(34);
				herraje();
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(35);
					match(T__1);
					setState(36);
					match(T__2);
					}
				}

				setState(39);
				match(T__0);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class PasoContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(IKEAParser.NUM, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public PasoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paso; }
	}

	public final PasoContext paso() throws RecognitionException {
		PasoContext _localctx = new PasoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_paso);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__3);
			setState(47);
			match(NUM);
			setState(48);
			match(T__4);
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				instruccion();
				setState(50);
				match(T__0);
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__10 || _la==T__14 );
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
	public static class InstruccionContext extends ParserRuleContext {
		public List<MontajeContext> montaje() {
			return getRuleContexts(MontajeContext.class);
		}
		public MontajeContext montaje(int i) {
			return getRuleContext(MontajeContext.class,i);
		}
		public HerramientaContext herramienta() {
			return getRuleContext(HerramientaContext.class,0);
		}
		public List<HerrajeContext> herraje() {
			return getRuleContexts(HerrajeContext.class);
		}
		public HerrajeContext herraje(int i) {
			return getRuleContext(HerrajeContext.class,i);
		}
		public List<PiezaContext> pieza() {
			return getRuleContexts(PiezaContext.class);
		}
		public PiezaContext pieza(int i) {
			return getRuleContext(PiezaContext.class,i);
		}
		public TerminalNode STRING() { return getToken(IKEAParser.STRING, 0); }
		public InstruccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion; }
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_instruccion);
		int _la;
		try {
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				montaje();
				setState(63);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
					{
					setState(57);
					match(T__5);
					setState(60);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__11:
						{
						setState(58);
						herramienta();
						}
						break;
					case T__12:
						{
						setState(59);
						herraje();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				case T__6:
					{
					setState(62);
					match(T__6);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(65);
				match(T__7);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) {
					{
					setState(69);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__13:
						{
						setState(66);
						pieza();
						}
						break;
					case T__12:
						{
						setState(67);
						herraje();
						}
						break;
					case T__14:
						{
						setState(68);
						montaje();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(71);
						match(T__1);
						setState(75);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__13:
							{
							setState(72);
							pieza();
							}
							break;
						case T__12:
							{
							setState(73);
							herraje();
							}
							break;
						case T__14:
							{
							setState(74);
							montaje();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						}
						setState(81);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(84);
				match(T__8);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				montaje();
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(87);
					match(T__5);
					setState(88);
					herramienta();
					}
				}

				setState(91);
				match(T__9);
				setState(92);
				match(T__7);
				setState(93);
				herraje();
				setState(94);
				match(T__8);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				match(T__10);
				setState(97);
				match(STRING);
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
	public static class HerramientaContext extends ParserRuleContext {
		public TerminalNode NOMBRE() { return getToken(IKEAParser.NOMBRE, 0); }
		public HerramientaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_herramienta; }
	}

	public final HerramientaContext herramienta() throws RecognitionException {
		HerramientaContext _localctx = new HerramientaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_herramienta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__11);
			setState(101);
			match(NOMBRE);
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
	public static class HerrajeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IKEAParser.ID, 0); }
		public CantidadContext cantidad() {
			return getRuleContext(CantidadContext.class,0);
		}
		public HerrajeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_herraje; }
	}

	public final HerrajeContext herraje() throws RecognitionException {
		HerrajeContext _localctx = new HerrajeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_herraje);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__12);
			setState(104);
			match(ID);
			setState(105);
			cantidad();
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
	public static class PiezaContext extends ParserRuleContext {
		public TerminalNode NOMBRE() { return getToken(IKEAParser.NOMBRE, 0); }
		public PiezaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pieza; }
	}

	public final PiezaContext pieza() throws RecognitionException {
		PiezaContext _localctx = new PiezaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_pieza);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(T__13);
			setState(108);
			match(NOMBRE);
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
	public static class MontajeContext extends ParserRuleContext {
		public TerminalNode NOMBRE() { return getToken(IKEAParser.NOMBRE, 0); }
		public MontajeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_montaje; }
	}

	public final MontajeContext montaje() throws RecognitionException {
		MontajeContext _localctx = new MontajeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_montaje);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__14);
			setState(111);
			match(NOMBRE);
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
	public static class CantidadContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(IKEAParser.NUM, 0); }
		public CantidadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cantidad; }
	}

	public final CantidadContext cantidad() throws RecognitionException {
		CantidadContext _localctx = new CantidadContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cantidad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__15);
			setState(114);
			match(NUM);
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
		"\u0004\u0001\u0016u\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0004\u0000\u0015\b\u0000\u000b\u0000"+
		"\f\u0000\u0016\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001\u001e\b\u0001\n\u0001\f\u0001!\t\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001&\b\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u0001*\b\u0001\n\u0001\f\u0001-\t\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u00025\b\u0002\u000b"+
		"\u0002\f\u00026\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003=\b\u0003\u0001\u0003\u0003\u0003@\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003F\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003L\b\u0003\u0005\u0003N\b\u0003\n\u0003"+
		"\f\u0003Q\t\u0003\u0003\u0003S\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003Z\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"c\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0000\u0000\t"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0000\u0000{\u0000\u0012\u0001"+
		"\u0000\u0000\u0000\u0002\u001f\u0001\u0000\u0000\u0000\u0004.\u0001\u0000"+
		"\u0000\u0000\u0006b\u0001\u0000\u0000\u0000\bd\u0001\u0000\u0000\u0000"+
		"\ng\u0001\u0000\u0000\u0000\fk\u0001\u0000\u0000\u0000\u000en\u0001\u0000"+
		"\u0000\u0000\u0010q\u0001\u0000\u0000\u0000\u0012\u0014\u0003\u0002\u0001"+
		"\u0000\u0013\u0015\u0003\u0004\u0002\u0000\u0014\u0013\u0001\u0000\u0000"+
		"\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0014\u0001\u0000\u0000"+
		"\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0018\u0001\u0000\u0000"+
		"\u0000\u0018\u0019\u0005\u0000\u0000\u0001\u0019\u0001\u0001\u0000\u0000"+
		"\u0000\u001a\u001b\u0003\b\u0004\u0000\u001b\u001c\u0005\u0001\u0000\u0000"+
		"\u001c\u001e\u0001\u0000\u0000\u0000\u001d\u001a\u0001\u0000\u0000\u0000"+
		"\u001e!\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000\u001f"+
		" \u0001\u0000\u0000\u0000 +\u0001\u0000\u0000\u0000!\u001f\u0001\u0000"+
		"\u0000\u0000\"%\u0003\n\u0005\u0000#$\u0005\u0002\u0000\u0000$&\u0005"+
		"\u0003\u0000\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000"+
		"&\'\u0001\u0000\u0000\u0000\'(\u0005\u0001\u0000\u0000(*\u0001\u0000\u0000"+
		"\u0000)\"\u0001\u0000\u0000\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000"+
		"\u0000\u0000+,\u0001\u0000\u0000\u0000,\u0003\u0001\u0000\u0000\u0000"+
		"-+\u0001\u0000\u0000\u0000./\u0005\u0004\u0000\u0000/0\u0005\u0013\u0000"+
		"\u000004\u0005\u0005\u0000\u000012\u0003\u0006\u0003\u000023\u0005\u0001"+
		"\u0000\u000035\u0001\u0000\u0000\u000041\u0001\u0000\u0000\u000056\u0001"+
		"\u0000\u0000\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u0000"+
		"7\u0005\u0001\u0000\u0000\u00008?\u0003\u000e\u0007\u00009<\u0005\u0006"+
		"\u0000\u0000:=\u0003\b\u0004\u0000;=\u0003\n\u0005\u0000<:\u0001\u0000"+
		"\u0000\u0000<;\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000\u0000>@\u0005"+
		"\u0007\u0000\u0000?9\u0001\u0000\u0000\u0000?>\u0001\u0000\u0000\u0000"+
		"@A\u0001\u0000\u0000\u0000AR\u0005\b\u0000\u0000BF\u0003\f\u0006\u0000"+
		"CF\u0003\n\u0005\u0000DF\u0003\u000e\u0007\u0000EB\u0001\u0000\u0000\u0000"+
		"EC\u0001\u0000\u0000\u0000ED\u0001\u0000\u0000\u0000FO\u0001\u0000\u0000"+
		"\u0000GK\u0005\u0002\u0000\u0000HL\u0003\f\u0006\u0000IL\u0003\n\u0005"+
		"\u0000JL\u0003\u000e\u0007\u0000KH\u0001\u0000\u0000\u0000KI\u0001\u0000"+
		"\u0000\u0000KJ\u0001\u0000\u0000\u0000LN\u0001\u0000\u0000\u0000MG\u0001"+
		"\u0000\u0000\u0000NQ\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000"+
		"OP\u0001\u0000\u0000\u0000PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000"+
		"\u0000RE\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000"+
		"\u0000\u0000TU\u0005\t\u0000\u0000Uc\u0001\u0000\u0000\u0000VY\u0003\u000e"+
		"\u0007\u0000WX\u0005\u0006\u0000\u0000XZ\u0003\b\u0004\u0000YW\u0001\u0000"+
		"\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\\\u0005"+
		"\n\u0000\u0000\\]\u0005\b\u0000\u0000]^\u0003\n\u0005\u0000^_\u0005\t"+
		"\u0000\u0000_c\u0001\u0000\u0000\u0000`a\u0005\u000b\u0000\u0000ac\u0005"+
		"\u0014\u0000\u0000b8\u0001\u0000\u0000\u0000bV\u0001\u0000\u0000\u0000"+
		"b`\u0001\u0000\u0000\u0000c\u0007\u0001\u0000\u0000\u0000de\u0005\f\u0000"+
		"\u0000ef\u0005\u0011\u0000\u0000f\t\u0001\u0000\u0000\u0000gh\u0005\r"+
		"\u0000\u0000hi\u0005\u0012\u0000\u0000ij\u0003\u0010\b\u0000j\u000b\u0001"+
		"\u0000\u0000\u0000kl\u0005\u000e\u0000\u0000lm\u0005\u0011\u0000\u0000"+
		"m\r\u0001\u0000\u0000\u0000no\u0005\u000f\u0000\u0000op\u0005\u0011\u0000"+
		"\u0000p\u000f\u0001\u0000\u0000\u0000qr\u0005\u0010\u0000\u0000rs\u0005"+
		"\u0013\u0000\u0000s\u0011\u0001\u0000\u0000\u0000\r\u0016\u001f%+6<?E"+
		"KORYb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}