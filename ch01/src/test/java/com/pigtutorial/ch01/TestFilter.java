package com.pigtutorial.ch01;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.pig.pigunit.PigTest;
import org.apache.pig.tools.parameters.ParseException;
import org.junit.Test;

public class TestFilter extends TestCase {

	@Test
	public void testFilterOperation() {
		String[] args = {};
		final String EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/filter_output";
		// Specify the expected output file path and create file object
		File file = new File(EXPECTED_OUTPUT_FILE_PATH);

		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/filter.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

		try {
			test.assertOutput("filtered_data", file);
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
	public void testFilterOperationWithAndOrNot() {
		String[] args = {};

		final String EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/filter_with_and_or_not_output";
		// Specify the expected output file path and create file object
		File file = new File(EXPECTED_OUTPUT_FILE_PATH);

		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/filter_with_and_or_not.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

		try {
			test.assertOutput("filtered_data", file);
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
	public void testFilterOperationWithNull() {
		String[] args = {};

		final String EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/filter_with_null_output";
		// Specify the expected output file path and create file object
		File file = new File(EXPECTED_OUTPUT_FILE_PATH);

		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/filter_with_null.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

		try {
			test.assertOutput("filtered_data", file);
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
