package splitter;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntryComparatorTest {

    @Test
    public void testComparator() {

        SymEntry a = new SymEntry(1,10.1,1000);
        SymEntry b = new SymEntry(2,10.1,3000);
        SymEntry c = new SymEntry(3,10.0,2000);
        SymEntry d = new SymEntry(3,10.0,2000);

        EntryComparator cmp = new EntryComparator();
        assertEquals(0, cmp.compare(c, d));
        assertTrue(cmp.compare(a,b) > 0);
        assertTrue(cmp.compare(b,a) < 0); // just a sanity check
        assertTrue(cmp.compare(b,c) > 0);
        assertTrue(cmp.compare(c,b) < 0); // just a sanity check

    }

}