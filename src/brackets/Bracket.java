package brackets;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * User: Jon
 * Date: 3/18/14
 * Time: 8:57 PM
 */
public class Bracket {
    private List<Team> bracket = Lists.newArrayList();
    private TreeMap<Integer, List<Team>> tournament = new TreeMap<>();
    private Round round = new Round();
    private final static Logger log = LoggerFactory.getLogger(Bracket.class);

    public void setSeedRecords(SeedRecords seedRecords) {
        round.setSeedRecords(seedRecords);
    }

    public void predictBracket() {
        while(bracket.size() != 1) {
            tournament.put(bracket.size(), bracket);
            bracket = round.play(bracket);
        }
        tournament.put(bracket.size(), bracket);
    }

    public void addToBracket(Team team) {
        bracket.add(team);
    }

    public void writeTournament(BufferedWriter writer) {
        for(Integer key : tournament.descendingKeySet()) {
            writeRound(writer, tournament.get(key));
        }
    }

    private void writeRound(BufferedWriter writer, List<Team> teams) {
        try {
            writer.write("Begin Round " + teams.size());
            writer.newLine();
            for (Team team : teams) {
                writer.write(team.getName());
                writer.newLine();
            }
            writer.write("End Round");
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            log.error("Error writing Round: " + e);
        }
    }
}
