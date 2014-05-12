package brackets;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * User: Jon
 * Date: 3/17/14
 * Time: 8:40 PM
 */
public class SeedRecords {
    private static final Logger log = LoggerFactory.getLogger(SeedRecords.class);
    private Map<Integer, Map<Integer, Record>> seedRecords = Maps.newHashMap();
    private int seedKey;

    public void buildSeedRecords(String line) {
        if (line.startsWith("#")) {
            try {
                seedKey = Integer.parseInt(line.substring(1, line.indexOf("(")));
            } catch (Exception e) {
                log.error("Build Seed Record couldn't parse seedKey: " + e);
            }
            seedRecords.put(seedKey, Maps.<Integer, Record>newHashMap());
            return;
        }
        String[] split = line.split("\\D");
        Preconditions.checkState(split.length == 5);
        try {
            int wins = Integer.parseInt(split[1]);
            int loses = Integer.parseInt(split[2]);
            String percent = split[3] + "." + split[4];
            double percentage = Double.parseDouble(percent);
            int seed = Integer.parseInt(split[0]);
            seedRecords.get(seedKey).put(seed, new Record(wins, loses, percentage));
        } catch (Exception e) {
            log.error("Problem parsing wins loses and percents of seedRecords: " + e);
        }
    }

    public Map<Integer, Record> getSeedRecords(int seed) {
        return seedRecords.get(seed);
    }
}
