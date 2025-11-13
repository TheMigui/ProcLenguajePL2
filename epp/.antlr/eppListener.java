// Generated from c:/Users/jorge/Documents/PdL/ProcLenguajePL2/epp/epp.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link eppParser}.
 */
public interface eppListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link eppParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(eppParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(eppParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(eppParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(eppParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(eppParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(eppParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(eppParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(eppParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#simpleAssign}.
	 * @param ctx the parse tree
	 */
	void enterSimpleAssign(eppParser.SimpleAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#simpleAssign}.
	 * @param ctx the parse tree
	 */
	void exitSimpleAssign(eppParser.SimpleAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#show}.
	 * @param ctx the parse tree
	 */
	void enterShow(eppParser.ShowContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#show}.
	 * @param ctx the parse tree
	 */
	void exitShow(eppParser.ShowContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#cond_line}.
	 * @param ctx the parse tree
	 */
	void enterCond_line(eppParser.Cond_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#cond_line}.
	 * @param ctx the parse tree
	 */
	void exitCond_line(eppParser.Cond_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(eppParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(eppParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(eppParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(eppParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#terminarStmt}.
	 * @param ctx the parse tree
	 */
	void enterTerminarStmt(eppParser.TerminarStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#terminarStmt}.
	 * @param ctx the parse tree
	 */
	void exitTerminarStmt(eppParser.TerminarStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(eppParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(eppParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(eppParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(eppParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(eppParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(eppParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(eppParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(eppParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(eppParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(eppParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link eppParser#compOp}.
	 * @param ctx the parse tree
	 */
	void enterCompOp(eppParser.CompOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link eppParser#compOp}.
	 * @param ctx the parse tree
	 */
	void exitCompOp(eppParser.CompOpContext ctx);
}