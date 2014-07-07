package com.pigtutorial.ch01;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.pig.pigunit.PigTest;
import org.apache.pig.tools.parameters.ParseException;
import org.junit.Test;

public class TestSplit extends TestCase {

	@Test
	public void testSplitOperation() {
		String[] args = {};
		final String EN_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/english_students_split_output";
		final String MA_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/mathematics_students_split_output";
		final String ST_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/statistics_students_split_output";
		final String OT_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/other_students_split_output";
		// Specify the expected output file path and create file object
		File enFile = new File(EN_STUD_EXPECTED_OUTPUT_FILE_PATH);
		File maFile = new File(MA_STUD_EXPECTED_OUTPUT_FILE_PATH);
		File stFile = new File(ST_STUD_EXPECTED_OUTPUT_FILE_PATH);
		File otFile = new File(OT_STUD_EXPECTED_OUTPUT_FILE_PATH);

		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/split.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

		try {
			// Test 4 split relations one by one
			test.assertOutput("english_students", enFile);
			test.assertOutput("mathematics_students", maFile);
			test.assertOutput("statistics_students", stFile);
			test.assertOutput("other_students", otFile);
		} catch (IOException e) {
			System.err.println("Caught exception while reading file");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Caught exception while parsing script");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFilterEquivalentForSplitOperation() {
		String[] args = {};
		final String EN_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/english_students_split_output";
		final String MA_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/mathematics_students_split_output";
		final String ST_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/statistics_students_split_output";
		final String OT_STUD_EXPECTED_OUTPUT_FILE_PATH = "src/resources/data/expected_output/other_students_split_output";
		// Specify the expected output file path and create file object
		File enFile = new File(EN_STUD_EXPECTED_OUTPUT_FILE_PATH);
		File maFile = new File(MA_STUD_EXPECTED_OUTPUT_FILE_PATH);
		File stFile = new File(ST_STUD_EXPECTED_OUTPUT_FILE_PATH);
		File otFile = new File(OT_STUD_EXPECTED_OUTPUT_FILE_PATH);

		PigTest test = null;
		try {
			test = new PigTest("src/resources/pig/filter_equivalent_of_split.pig", args);
		} catch (IOException e) {
			System.err.println("Caught exception while reading pig script");
			e.printStackTrace();
		}

		try {
			// Test 4 split relations one by one
			test.assertOutput("english_students", enFile);
			test.assertOutput("mathematics_students", maFile);
			test.assertOutput("statistics_students", stFile);
			test.assertOutput("other_students", otFile);
		} catch (IOException e) {
			System.err.println("Caught exception while reading file");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Caught exception while parsing script");
			e.printStackTrace();
		}
	}
}
