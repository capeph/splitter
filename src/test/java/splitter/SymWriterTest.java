package splitter;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.junit.Assert.*;

public class SymWriterTest {

    ConcurrentSkipListSet<SymEntry> values;

    @Before
    public void setup() {
        values = new ConcurrentSkipListSet<>(new EntryComparator());
        values.add(new SymEntry(1,10.1,1000));
        values.add(new SymEntry(2,10.1,3000));
        values.add(new SymEntry(3,10.0,2000));
    }

    @Test
    public void testSymWriter() throws IOException {
        SymWriter sw = new SymWriter("TEST", values);
        assertEquals("TEST.csv", sw.getFileName());
        DebugWriter writer = new DebugWriter();
        sw.writeValues(writer);
        assertEquals("10.00,2000,0x00000003\n", writer.lines.get(0));
    }

    // instead of using 3rd party mock-library
    class DebugWriter extends Writer {

        public ArrayList<String> lines = new ArrayList<>();

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            lines.add(new String(cbuf, off, len));
        }

        @Override
        public void flush() throws IOException {

        }

        @Override
        public void close() throws IOException {

        }
    }


}