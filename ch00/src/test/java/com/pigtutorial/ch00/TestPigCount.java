package com.pigtutorial.ch00;

import static org.apache.pig.builtin.mock.Storage.resetData;
import static org.apache.pig.builtin.mock.Storage.tuple;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.pig.builtin.mock.Storage.Data;
import org.apache.pig.data.Tuple;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class TestPigCount extends TestCase {
	private static PigServer pigServer;

	@Before
	public void setUp() throws Exception {
		pigServer = new PigServer(ExecType.LOCAL);
	}

	@Test
	public void testPigCount() throws Exception {
		Data data = resetData(pigServer);

		/*
		 * Generate Dummy data to aggregate in a List of Tuple(Pig Data Type)
		 */
		List<Tuple> dummySequenceNumbers = Lists.newArrayList();
		for (int i = 0; i < 1000; i++) {
			dummySequenceNumbers.add(tuple(i));
		}

		/*
		 * Set the List to Mock Storage data object
		 */
		data.set("input_data", dummySequenceNumbers);

		/*
		 * Step 1 : Load data to Pig alias a using Mock load function 
		 * Step 2 : Generate the 1st column and name the column 
		 * Step 3 : Group all data to aggregate (i,e to find count) 
		 * Step 4 : Use Built in Function COUNT to obtain the count of 
		 * 			the aggregated data
		 */
		// Step 1
		pigServer.registerQuery("a = LOAD 'input_data' USING mock.Storage();");
		// Step 2
		pigServer.registerQuery("b = FOREACH a GENERATE $0 AS COL1;");
		// Step 3
		pigServer.registerQuery("c = GROUP b ALL;");
		// Step 4
		pigServer.registerQuery("d = FOREACH c GENERATE COUNT($1);");
		
		/*
		 * Open the iterator for the alias d. This executes the Pig 
		 * Queries registered to pigServer
		 */
		Iterator<Tuple> it = pigServer.openIterator("d");
		// Check if the iterator has any records
		assertTrue(it.hasNext());
		// Obtain the tuple
		Tuple t = it.next();
		// Check if the 1st column of the row is 1000 (in long data type)
		assertEquals(1000L, t.get(0));
		// Should contain only 1 row. Assert if has more than 1
		assertFalse(it.hasNext());
	}
}