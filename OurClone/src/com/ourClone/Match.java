package com.ourClone;

public class Match {
	
	private int indexA;
	private int indexB;
	private int length;
	
	public Match(int indexA, int indexB, int length)
	{
		this.indexA = indexA;
		this.indexB = indexB;
		this.length = length;
	}

	public int getIndexA() {
		return indexA;
	}

	public int getIndexB() {
		return indexB;
	}

	public int getLength() {
		return length;
	}

	
	
}
