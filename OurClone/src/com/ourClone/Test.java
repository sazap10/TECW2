package com.ourClone;

import java.io.File;

public class Test {
	public static String TEST_DIR = "tests";
	public static final int NUM_TESTS = 3;

	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		String path = root + File.separator + TEST_DIR + File.separator;

		for (int i = 1; i <= NUM_TESTS; i++) {
			for (int j = i; j <= NUM_TESTS; j++) {
				String[] files = { path + "test" + i, path + "test" + j };
				System.out
						.printf("Test files: %s, %s ", "test" + i, "test" + j);
				OurClone.main(files);
			}
		}
	}
}
