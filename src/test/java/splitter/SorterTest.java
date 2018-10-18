package splitter;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.junit.Assert.*;

public class SorterTest {

    private ConcurrentMap<String, ConcurrentSkipListSet<SymEntry>> symData;

    @Before
    public void init() {
        symData = new ConcurrentHashMap<>();
    }

    @Test
    public void testSorter() {
        Sorter sorter = new Sorter("TRI", new SymEntry(1,10.1,1000), symData);
        sorter.run();
        sorter = new Sorter("TRI", new SymEntry(2,10.1,3000), symData);
        sorter.run();
        sorter = new Sorter("TRI", new SymEntry(3,10.0,2000), symData);
        sorter.run();
        assertEquals(1, symData.size());
        ConcurrentSkipListSet<SymEntry> triSet = symData.get("TRI");
        assertEquals(3, triSet.size());
        Iterator<SymEntry> si = triSet.iterator();
        SymEntry se = si.next();
        assertEquals(3, se.getSeqNo());
        assertEquals(10.0, se.getPrice(), 0.001);
        assertEquals(2000, se.getQuantity());
        se = si.next();
        assertEquals(2, se.getSeqNo());
        se = si.next();
        assertEquals(1, se.getSeqNo());
    }


}