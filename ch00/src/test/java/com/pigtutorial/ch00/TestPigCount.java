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

        List<Tuple> dummySequenceNumbers = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            dummySequenceNumbers.add(tuple(i));
        }

        data.set("input", dummySequenceNumbers);

        pigServer.registerQuery("A = load 'input' using mock.Storage();");
        pigServer.registerQuery("B = foreach A generate $0 as col1;");
        pigServer.registerQuery("C = foreach (group B all) generate COUNT($1);");
        Iterator<Tuple> it = pigServer.openIterator("C");
        assertTrue(it.hasNext());
        Tuple t = it.next();
        assertEquals(1000L, t.get(0));
        assertFalse(it.hasNext());
    }
}
