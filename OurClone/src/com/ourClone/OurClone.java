package com.ourClone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.ourClone.Parser.JavaLexer;
import com.ourClone.Parser.JavaParser;
import com.ourClone.Parser.OurCloneListener;

public class OurClone {

	private final int MINIMUM = 3;

	public static void main(String[] args) {
		OurClone c = new OurClone();
		File fileA = new File(args[0]);
		File fileB = new File(args[1]);

		try 
		{
			String tokensForFileA = c.parseFile(fileA);
			String tokensForFileB = c.parseFile(fileB);

			c.detect(tokensForFileA, tokensForFileB);
		} 

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void detect(String tokensStringsA, String tokensStringsB)
	{
		List<Token> tokensA = getTokenObjects(tokensStringsA);
		List<Token> tokensB = getTokenObjects(tokensStringsB);

		compare(tokensA, tokensB);

	}

	private void compare(List<Token> tokensA, List<Token> tokensB) {

		List<Match> tiles = new ArrayList<Match>();
		int maxMatch;

		do
		{
			maxMatch = MINIMUM;
			int j;
			List<Match> matches = new ArrayList<Match>();
			for(Token tokenA : tokensA)
			{
				if(!tokenA.isMarked())
				{
					for(Token tokenB : tokensB)
					{
						if(!tokenB.isMarked())
						{
							j=0;
							int indexOfTokenA = tokensA.indexOf(tokenA);
							int indexOfTokenB = tokensB.indexOf(tokenB);
							Token tokenAj = tokensA.get(indexOfTokenA+j);
							Token tokenBj = tokensA.get(indexOfTokenB+j);
							String tokenAjString = tokenAj.getTokenString();
							String tokenBjString = tokenBj.getTokenString();
							while((tokenAjString.equals(tokenBjString))
									&& !tokenAj.isMarked() && !tokenBj.isMarked())
							{
								j++;
							}
							if(j == maxMatch)
							{
								
							}
						}
					}
				}
			}


		}while(maxMatch > MINIMUM);
	}
	
	private List<Token> getTokenSubString(List<Token> fullTokensList, Match match)
	{
		List<Token> tokenSubString = new ArrayList<Token>();
		int startIndex = match.getIndexA();
		for(int i = 0 ; i< match.getLength(); i++)
		{
			tokenSubString.add(fullTokensList.get(startIndex+i));
			
		}
		
		return tokenSubString;
	}
	
	private boolean isOverlapped(List<Token> matchA, List<Token> matchB)
	{
		if(matchA.size() > matchB.size())
		{
			return overlaps(matchA, matchB);
		}
		else
		{
			return overlaps(matchB, matchA);
		}
	}
	
	private boolean overlaps(List<Token> matchA, List<Token> matchB)
	{
		boolean match  = false;
		for(int a = 0 ; a< matchA.size()-1; a++)
		{
			for(int b = 0 ; b< matchB.size();b++)
			{
				if(matchB.get(b).getTokenString().equals(matchA.get(a).getTokenString()))
				{
					match = true;
					break;
				}
				else
				{
					match = false;
				}
			}
		}
	}

	//	private List<Token> getUnmrkedTokens(List<Token> tokens)
	//	{
	//		List<Token> unmarkedTokens = new ArrayList<Token>();		
	//		for(Token token : tokens)
	//		{
	//			if(!token.isMarked())
	//			{
	//				unmarkedTokens.add(token);
	//			}
	//		}
	//		return unmarkedTokens;
	//	}

	private List<Token> getTokenObjects(String commaSeparatedtokenString)
	{
		String[] tokenStrings = commaSeparatedtokenString.split(OurCloneListener.DELIMETER);
		List<Token> tokens = new ArrayList<Token>();

		for(String token : tokenStrings)
		{
			Token tokenObject = new Token(token);
			tokens.add(tokenObject);
		}

		return tokens;
	}

	private String parseFile(File file) throws IOException {

		InputStream input = new FileInputStream(file);

		ANTLRInputStream inputStream = new ANTLRInputStream(input);

		// Create a scanner that reads from the input stream passed to us
		JavaLexer lexer = new JavaLexer(inputStream);

		// Create a parser that reads from the scanner
		JavaParser parser = new JavaParser(new CommonTokenStream(lexer));

		OurCloneListener listener = new OurCloneListener	();
		// Add the listener that is responsible for the token printing
		parser.addParseListener(listener);

		//parse the file
		parser.compilationUnit();

		return listener.getTokens();
	}

}
