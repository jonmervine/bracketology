package serialization;

import brackets.Bracket;
import brackets.Team;
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
public class BracketReader {
    private final static Logger log = LoggerFactory.getLogger(BracketReader.class);

    public Bracket readBracket(String filename) {
        Bracket bracket = new Bracket();
        try (BufferedReader input = getReader(filename)) {
            String line;
            while ((line = input.readLine()) != null) {
                bracket.addToBracket(Team.ConstructTeam(line));
            }
        } catch (IOException ex) {
            log.error("Error reading bracket: " + ex);
        }
        return bracket;
    }

    private BufferedReader getReader(String filename) {
        try {
            File brackets = Paths.get("src/resources", filename).toFile();
            return Files.newReader(brackets, Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            log.error("Could not find file: " + e);
            throw new RuntimeException(e);
        }
    }
}
