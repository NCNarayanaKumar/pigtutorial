package com.pigtutorial.ch01;

import java.io.File;
import java.io.IOException;

import org.apache.pig.pigunit.PigTest;
import org.apache.pig.tools.parameters.ParseException;
import org.junit.Test;

import junit.framework.TestCase;

public class TestTopQueries extends TestCase {

	private static final String EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/top_sites_output";
	File file = new File(EXPECTED_OUTPUT_FILE_PATH);

	@Test
	public void testTop2Queries() {
		String[] args = { "n=2", };

		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/top_queries.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

//		String[] input = { "yahoo", "yahoo", "yahoo", "twitter", "facebook",
//				"facebook", "linkedin", };

		//String[] output = { "(yahoo,3)", "(facebook,2)", };

		try {
			//test.assertOutput("data", input, "queries_limit", output);
			test.assertOutput("queries_limit", file);
			test.unoverride("STORE");
			test.runScript();
		} catch (IOException e) {
			System.err.println("Caught exception while reading file");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Caught exception while parsing script");
			e.printStackTrace();
		}
	}

}
