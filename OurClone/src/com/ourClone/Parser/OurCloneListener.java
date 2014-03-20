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
	public void enterSwitchLabel(@NotNull JavaParser.SwitchLabelContext ctx) {
		addTokenToString("BEGINSWITCH");
	}

	@Override
	public void exitSwitchLabel(@NotNull JavaParser.SwitchLabelContext ctx) {
		addTokenToString("ENDSWITCH");
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
}
