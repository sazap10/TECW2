package com.ourClone;

public class Token {
	
	private boolean marked;
	private String tokenString;
	
	public Token(String tokenString)
	{
		this.setTokenString(tokenString);
		this.setMarked(false);
		
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

}
