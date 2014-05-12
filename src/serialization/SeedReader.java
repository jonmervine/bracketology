package serialization;

import brackets.SeedRecords;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

/**
 * User: Jon
 * Date: 3/18/14
 * Time: 8:46 PM
 */
public class SeedReader {
    private final static Logger log = LoggerFactory.getLogger(SeedReader.class);
    private final SeedRecords seedRecords = new SeedRecords();

    public SeedRecords readSeeds(String filename) {
        try (BufferedReader input = getReader(filename)) {
            String line;
            while ((line = input.readLine()) != null) {
                seedRecords.buildSeedRecords(line);
            }
        } catch (IOException e) {
            log.error("Problem reading file: " + e);
            throw new RuntimeException(e);
        }
        return seedRecords;
    }

    private BufferedReader getReader(String filename) {
        try {
            File seedRankings = Paths.get("src/resources", filename).toFile();
            return Files.newReader(seedRankings, Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            log.error("Could not find file: " + e);
            throw new RuntimeException(e);
        }
    }
}
