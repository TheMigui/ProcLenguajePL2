// Generated from IKEA.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IKEAParser}.
 */
public interface IKEAListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IKEAParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IKEAParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IKEAParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(IKEAParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(IKEAParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#paso}.
	 * @param ctx the parse tree
	 */
	void enterPaso(IKEAParser.PasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#paso}.
	 * @param ctx the parse tree
	 */
	void exitPaso(IKEAParser.PasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(IKEAParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(IKEAParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#herramienta}.
	 * @param ctx the parse tree
	 */
	void enterHerramienta(IKEAParser.HerramientaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#herramienta}.
	 * @param ctx the parse tree
	 */
	void exitHerramienta(IKEAParser.HerramientaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#herraje}.
	 * @param ctx the parse tree
	 */
	void enterHerraje(IKEAParser.HerrajeContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#herraje}.
	 * @param ctx the parse tree
	 */
	void exitHerraje(IKEAParser.HerrajeContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#pieza}.
	 * @param ctx the parse tree
	 */
	void enterPieza(IKEAParser.PiezaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#pieza}.
	 * @param ctx the parse tree
	 */
	void exitPieza(IKEAParser.PiezaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#montaje}.
	 * @param ctx the parse tree
	 */
	void enterMontaje(IKEAParser.MontajeContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#montaje}.
	 * @param ctx the parse tree
	 */
	void exitMontaje(IKEAParser.MontajeContext ctx);
	/**
	 * Enter a parse tree produced by {@link IKEAParser#cantidad}.
	 * @param ctx the parse tree
	 */
	void enterCantidad(IKEAParser.CantidadContext ctx);
	/**
	 * Exit a parse tree produced by {@link IKEAParser#cantidad}.
	 * @param ctx the parse tree
	 */
	void exitCantidad(IKEAParser.CantidadContext ctx);
}