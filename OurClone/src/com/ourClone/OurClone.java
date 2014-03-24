package com.ourClone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		try {
			String tokensForFileA = c.parseFile(fileA);
			String tokensForFileB = c.parseFile(fileB);

			System.out.printf("Similarity of two files is: %f%%",
					c.detect(tokensForFileA, tokensForFileB) * 100);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private float detect(String tokensStringsA, String tokensStringsB) {
		List<Token> tokensA = getTokenObjects(tokensStringsA);
		List<Token> tokensB = getTokenObjects(tokensStringsB);

		Set<Match> tiles = compare(tokensA, tokensB);
		return getSimilarityPercentage(tiles, tokensA, tokensB);

	}

	private Set<Match> compare(List<Token> tokensA, List<Token> tokensB) {

		Set<Match> tiles = new HashSet<Match>();
		int maxMatch;

		do {
			maxMatch = MINIMUM;
			int j;
			List<Match> matches = new ArrayList<Match>();
			for (Token tokenA : tokensA) {
				if (!tokenA.isMarked()) {
					for (Token tokenB : tokensB) {
						if (!tokenB.isMarked()) {
							j = 0;
							int indexOfTokenA = tokensA.indexOf(tokenA);
							int indexOfTokenB = tokensB.indexOf(tokenB);
							/*
							 * Token tokenAj = tokensA.get(indexOfTokenA + j);
							 * Token tokenBj = tokensA.get(indexOfTokenB + j);
							 * String tokenAjString = tokenAj.getTokenString();
							 * String tokenBjString = tokenBj.getTokenString();
							 * while ((tokenAjString.equals(tokenBjString)) &&
							 * !tokenAj.isMarked() && !tokenBj.isMarked()) {
							 * j++; tokenAj = tokensA.get(indexOfTokenA + j);
							 * tokenBj = tokensA.get(indexOfTokenB + j);
							 * tokenAjString = tokenAj.getTokenString();
							 * tokenBjString = tokenBj.getTokenString(); }
							 */
							Token tokenAj, tokenBj;
							String tokenAjString, tokenBjString;
							do {
								tokenAj = tokensA.get(indexOfTokenA + j);
								tokenBj = tokensB.get(indexOfTokenB + j);
								tokenAjString = tokenAj.getTokenString();
								tokenBjString = tokenBj.getTokenString();
								j++;
							} while ((tokenAjString.equals(tokenBjString))
									&& !tokenAj.isMarked()
									&& !tokenBj.isMarked());
							Match newMatch = new Match(indexOfTokenA,
									indexOfTokenB, j);
							List<String> newMatchsubString = getTokenSubString(
									tokensA, newMatch);
							if (j == maxMatch) {
								boolean overlaps = false;
								List<String> matchSubString;
								for (Match match : matches) {
									matchSubString = getTokenSubString(tokensA,
											match);
									if (isOverlapped(matchSubString,
											newMatchsubString)) {
										overlaps = true;
										break;
									}
								}
								if (!overlaps) {
									matches.add(newMatch);
								}
							} else if (j > maxMatch) {
								matches.clear();
								matches.add(newMatch);
								maxMatch = j;
							}
						}
					}
				}
			}
			for (Match match : matches) {
				for (j = 0; j < maxMatch; j++) {
					tokensA.get(match.getIndexA() + j).setMarked(true);
					tokensB.get(match.getIndexB() + j).setMarked(true);
				}
				tiles.add(match);
			}

		} while (maxMatch > MINIMUM);
		return tiles;
	}

	private List<String> getTokenSubString(List<Token> fullTokensList,
			Match match) {
		List<String> tokenSubString = new ArrayList<String>();
		int startIndex = match.getIndexA();
		for (int i = 0; i < match.getLength(); i++) {
			tokenSubString.add(fullTokensList.get(startIndex + i)
					.getTokenString());

		}

		return tokenSubString;
	}

	private boolean isOverlapped(List<String> matchA, List<String> matchB) {
		if (matchA.size() > matchB.size()) {
			return overlaps(matchA, matchB);
		} else {
			return overlaps(matchB, matchA);
		}
	}

	private boolean overlaps(List<String> matchA, List<String> matchB) {
		String substringA = "";
		String substringB = "";
		for (String token : matchA) {
			substringA += token;
		}
		for (String token : matchB) {
			substringB += token;
		}

		if (substringA.contains(substringB)) {
			return true;
		}
		return false;
	}

	// private List<Token> getUnmrkedTokens(List<Token> tokens)
	// {
	// List<Token> unmarkedTokens = new ArrayList<Token>();
	// for(Token token : tokens)
	// {
	// if(!token.isMarked())
	// {
	// unmarkedTokens.add(token);
	// }
	// }
	// return unmarkedTokens;
	// }

	private List<Token> getTokenObjects(String commaSeparatedtokenString) {
		String[] tokenStrings = commaSeparatedtokenString
				.split(OurCloneListener.DELIMETER);
		List<Token> tokens = new ArrayList<Token>();

		for (String token : tokenStrings) {
			Token tokenObject = new Token(token);
			tokens.add(tokenObject);
		}

		return tokens;
	}

	private float getSimilarityPercentage(Set<Match> tiles, List<Token> a,
			List<Token> b) {
		int coverage = 0;
		for (Match match : tiles) {
			coverage += match.getLength();
		}
		float similarity = 2 * coverage / (a.size() + b.size());
		return similarity;
	}

	private String parseFile(File file) throws IOException {

		InputStream input = new FileInputStream(file);

		ANTLRInputStream inputStream = new ANTLRInputStream(input);

		// Create a scanner that reads from the input stream passed to us
		JavaLexer lexer = new JavaLexer(inputStream);

		// Create a parser that reads from the scanner
		JavaParser parser = new JavaParser(new CommonTokenStream(lexer));

		OurCloneListener listener = new OurCloneListener();
		// Add the listener that is responsible for the token printing
		parser.addParseListener(listener);

		// parse the file
		parser.compilationUnit();

		return listener.getTokens();
	}

}
