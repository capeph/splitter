package splitter;

/** En entry in the symbol table */
public class SymEntry {
    private final long seqNo;
    private final double price;
    private final long quantity;

    public SymEntry(long seqNo, double price, long quantity) {
        this.seqNo = seqNo;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public long getSeqNo() {
        return seqNo;
    }

    public long getQuantity() {
        return quantity;
    }
}