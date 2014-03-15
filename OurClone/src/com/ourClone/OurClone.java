package com.ourClone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.ourClone.Parser.JavaBaseListener;
import com.ourClone.Parser.JavaLexer;
import com.ourClone.Parser.JavaParser;

public class OurClone {

	public static void main(String[] args) {
		OurClone c = new OurClone();
		File file = new File(args[0]);
		c.parse(file);

	}

	private void parse(File file) {
		try {
			InputStream input = new FileInputStream(file);

			ANTLRInputStream inputStream = new ANTLRInputStream(input);

			// Create a scanner that reads from the input stream passed to us
			JavaLexer lexer = new JavaLexer(inputStream);

			// Create a parser that reads from the scanner
			JavaParser parser = new JavaParser(new CommonTokenStream(lexer));

			parser.addParseListener(new JavaBaseListener());
			parser.compilationUnit();
			System.out.println("done");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
