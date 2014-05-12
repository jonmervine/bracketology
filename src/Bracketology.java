import brackets.Bracket;
import brackets.SeedRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serialization.BracketReader;
import serialization.BracketWriter;
import serialization.SeedReader;


/**
 * User: Jon
 * Date: 3/17/14
 * Time: 8:47 PM
 */
public class Bracketology {
    private static final Logger log = LoggerFactory.getLogger(Bracketology.class);

    private final BracketReader bracketReader = new BracketReader();
    private final BracketWriter bracketWriter = new BracketWriter();
    private final SeedReader seedReader = new SeedReader();

    private void scheduler(String seeds, String inputBracket, String output) {
        log.info("Read the seeds");
        SeedRecords seedRecords = seedReader.readSeeds(seeds);
        log.info("Read the bracket");
        Bracket bracket = bracketReader.readBracket(inputBracket);

        bracket.setSeedRecords(seedRecords);
        log.info("Calculating bracket");
        bracket.predictBracket();

        log.info("Exporting bracket");
        bracketWriter.writeBracket(bracket, output);
    }


    public static void main(String[] args) {
        Bracketology b = new Bracketology();
        try {
            if (args.length != 3) {
                b.scheduler("seedrankings.txt", "bracket.txt", "tournamentPredictions.txt");
            } else {
                b.scheduler(args[0], args[1], args[2]);
            }
        } catch (Exception e) {
            log.error("Exception: " + e);
        }
    }
}
