package serialization;

import brackets.Bracket;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
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
public class BracketWriter {
    private final static Logger log = LoggerFactory.getLogger(BracketWriter.class);

    public void writeBracket(Bracket bracket, String outputFile) {
        try (BufferedWriter output = getWriter(outputFile)) {
                bracket.writeTournament(output);
        } catch (IOException e) {
            log.error("Error writing bracket: " + e);
        }
    }

    private BufferedWriter getWriter(String outputFile) {
        try {
            File brackets = Paths.get("src/resources", outputFile).toFile();
            return Files.newWriter(brackets, Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            log.error("Could not find file: " + e);
            throw new RuntimeException(e);
        }
    }
}
