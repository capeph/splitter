package splitter;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

/** inserts a symEntry in the symbol table */
public class Sorter implements Runnable {

    private final String symbol;
    private final SymEntry entry;
    private final ConcurrentMap<String, ConcurrentSkipListSet<SymEntry>> symData;

    public Sorter(String symbol, SymEntry entry, ConcurrentMap<String, ConcurrentSkipListSet<SymEntry>> symData) {
        this.symbol = symbol;
        this.entry = entry;
        this.symData = symData;
    }


    @Override
    public void run() {
        try {
            ConcurrentSkipListSet<SymEntry> entries = symData.putIfAbsent(symbol,
                    new ConcurrentSkipListSet<>(new EntryComparator()));
            if (entries == null) {
                entries = symData.get(symbol);
            }
            entries.add(entry);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}