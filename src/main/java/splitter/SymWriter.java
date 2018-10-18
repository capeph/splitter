package splitter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ConcurrentSkipListSet;

/** writes the entries for a single symbol to file */
public class SymWriter implements Runnable {

    private String symbol;
    private ConcurrentSkipListSet<SymEntry> values;


    public SymWriter(String symbol, ConcurrentSkipListSet<SymEntry> values) {
        this.symbol = symbol;
        this.values = values;
    }


    public void writeValues(Writer fw) throws IOException {
        for (SymEntry e : values) {
            fw.write(String.format("%.2f,%d,0x%08X\n", e.getPrice(), e.getQuantity(), e.getSeqNo()));
        }
    }

    public String getFileName() {
        return symbol + ".csv";
    }

    @Override
    public void run() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(getFileName());
            writeValues(fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}