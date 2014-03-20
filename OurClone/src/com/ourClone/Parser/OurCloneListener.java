package com.ourClone.Parser;

import org.antlr.v4.runtime.misc.NotNull;

public class OurCloneListener extends JavaBaseListener {
	private String tokens = "";
	public static final String DELIMETER = ",";

	private void addTokenToString(String token) {
		tokens += token + DELIMETER;
	}

	public String getTokens() {
		return tokens;
	}

	@Override
	public void exitImportDeclaration(JavaParser.ImportDeclarationContext ctx) {
		addTokenToString("IMPORT");
	}

	@Override
	public void exitLocalVariableDeclarationStatement(
			@NotNull JavaParser.LocalVariableDeclarationStatementContext ctx) {
		addTokenToString("VARDEF");
	}

	@Override
	public void enterSwitch_block(@NotNull JavaParser.Switch_blockContext ctx) {
		addTokenToString("BEGINSWITCH");
	}

	@Override
	public void exitSwitch_block(@NotNull JavaParser.Switch_blockContext ctx) {
		addTokenToString("ENDSWITCH");
	}

	@Override
	public void enterSwitchLabel(@NotNull JavaParser.SwitchLabelContext ctx) {
		addTokenToString("CASE");
	}

	@Override
	public void enterClassDeclaration(
			@NotNull JavaParser.ClassDeclarationContext ctx) {
		addTokenToString("BEGINCLASS");
	}

	@Override
	public void exitClassDeclaration(
			@NotNull JavaParser.ClassDeclarationContext ctx) {
		addTokenToString("ENDCLASS");
	}

	@Override
	public void exitPackageDeclaration(
			@NotNull JavaParser.PackageDeclarationContext ctx) {
		addTokenToString("PACKAGE");
	}

	@Override
	public void exitCompilationUnit(
			@NotNull JavaParser.CompilationUnitContext ctx) {
		addTokenToString("EOF");
	}

	@Override
	public void enterMethodDeclaration(
			@NotNull JavaParser.MethodDeclarationContext ctx) {
		addTokenToString("BEGINMETHOD");
	}

	@Override
	public void exitMethodDeclaration(
			@NotNull JavaParser.MethodDeclarationContext ctx) {
		addTokenToString("ENDMETHOD");
	}

	@Override
	public void enterFor_loop(@NotNull JavaParser.For_loopContext ctx) {
		addTokenToString("BEGINFOR");
	}

	@Override
	public void exitFor_loop(@NotNull JavaParser.For_loopContext ctx) {
		addTokenToString("ENDFOR");
	}

	@Override
	public void enterDo_while_loop(@NotNull JavaParser.Do_while_loopContext ctx) {
		addTokenToString("BEGINDOWHILE");
	}

	@Override
	public void exitDo_while_loop(@NotNull JavaParser.Do_while_loopContext ctx) {
		addTokenToString("ENDDOWHILE");
	}

	@Override
	public void enterWhile_loop(@NotNull JavaParser.While_loopContext ctx) {
		addTokenToString("BEGINWHILE");
	}

	@Override
	public void exitWhile_loop(@NotNull JavaParser.While_loopContext ctx) {
		addTokenToString("ENDWHILE");
	}

	@Override
	public void enterTry_statement(@NotNull JavaParser.Try_statementContext ctx) {
		addTokenToString("BEGINTRY");
	}

	@Override
	public void exitTry_statement(@NotNull JavaParser.Try_statementContext ctx) {
		addTokenToString("ENDTRY");
	}

	public void enterIf_else_statement(
			@NotNull JavaParser.If_else_statementContext ctx) {
		addTokenToString("BEGINIF");
	}

	@Override
	public void exitIf_else_statement(
			@NotNull JavaParser.If_else_statementContext ctx) {
		addTokenToString("ENDIF");
	}

	@Override
	public void enterSynchronized_block(
			@NotNull JavaParser.Synchronized_blockContext ctx) {
		addTokenToString("BEGINSYNC");
	}

	@Override
	public void exitSynchronized_block(
			@NotNull JavaParser.Synchronized_blockContext ctx) {
		addTokenToString("ENDSYNC");
	}

	@Override
	public void exitReturn_statement(
			@NotNull JavaParser.Return_statementContext ctx) {
		addTokenToString("RETURN");
	}

	@Override
	public void enterCatchClause(@NotNull JavaParser.CatchClauseContext ctx) {
		addTokenToString("BEGINCATCH");
	}

	@Override
	public void exitCatchClause(@NotNull JavaParser.CatchClauseContext ctx) {
		addTokenToString("ENDCATCH");
	}

	@Override
	public void enterFinallyBlock(@NotNull JavaParser.FinallyBlockContext ctx) {
		addTokenToString("BEGINFINALLY");
	}

	@Override
	public void exitFinallyBlock(@NotNull JavaParser.FinallyBlockContext ctx) {
		addTokenToString("ENDFINALLY");
	}
}
