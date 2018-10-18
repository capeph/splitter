package splitter;

import java.util.Comparator;

/** compare to symEntries */
public class EntryComparator implements Comparator<SymEntry> {

    @Override
    public int compare(SymEntry o1, SymEntry o2) {
        if (o1.getPrice() == o2.getPrice()) {
            return Long.compare(o2.getSeqNo(), o1.getSeqNo());
        }
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
