package com.pigtutorial.ch01;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.pig.pigunit.PigTest;
import org.apache.pig.tools.parameters.ParseException;
import org.junit.Test;

public class TestSimpleLoadStore extends TestCase {

	private static final String EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/simple_load_store_output";

	@Test
	public void testSimpleLoadStoreUsingFiles() {
		String[] args = {};

		// Specify the expected output file path and create file object
		File file = new File(EXPECTED_OUTPUT_FILE_PATH);

		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/simple_load_store.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

		try {
			test.assertOutput("data_alias", file);
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

	@Test
	public void testSimpleLoadStoreUsingVariables() {
		String[] args = {};
		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/simple_load_store.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

		/*
		 * Mention input output in the Java Test Script Use tab delimited data
		 * here, even though you have mentioned different delimiter in the
		 * PigStorage. The assertOutput do not accept any other data accept the
		 * tab delimited data . This issue is already logged in Apacge Pig Jira.
		 * 
		 * JIRA - PIG-3162 Link : https://issues.apache.org/jira/browse/PIG-3162
		 */
		String[] input = { "STUD0001\tMarita\t29", "STUD0002\tShannon\t21",
				"STUD0003\tGayle\t24", "STUD0004\tStephen\t32" };
		String[] output = { "(STUD0001,Marita,29)", "(STUD0002,Shannon,21)",
				"(STUD0003,Gayle,24)", "(STUD0004,Stephen,32)" };
		try {
			test.assertOutput("data_alias", input, "data_alias", output);
		} catch (IOException e) {
			System.err.println("Caught exception while reading file");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Caught exception while parsing script");
			e.printStackTrace();
		}
	}
}
