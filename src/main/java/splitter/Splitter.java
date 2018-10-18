package splitter;

import java.io.*;
import java.util.Map;
import java.util.concurrent.*;

/** main class */
public class Splitter {


    private ConcurrentMap<String, ConcurrentSkipListSet<SymEntry>> symData = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage:  splitter filename");
            return;
        }
        File inputFile = new File(args[0]);
        System.out.println("Reading the file " + inputFile.getAbsolutePath());
        Splitter splitter = new Splitter();
        splitter.split(inputFile);
    }


    private void split(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            ExecutorService handler = Executors.newCachedThreadPool();
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                SymEntry entry = new SymEntry(Long.decode(parts[0]), Double.parseDouble(parts[2]), Long.parseLong(parts[3]));
                handler.execute(new Sorter(parts[1], entry, symData));
                count ++;
            }
            System.out.println("Read " + count + " lines");
            br.close();
            while (((ThreadPoolExecutor) handler).getCompletedTaskCount() < count) {
                Thread.sleep(1);
            }
            for (Map.Entry<String, ConcurrentSkipListSet<SymEntry>> e : symData.entrySet()) {
                    handler.execute(new SymWriter(e.getKey(), e.getValue()));
                    System.out.println("Writing sym data for " + e.getKey());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
