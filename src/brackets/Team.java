package brackets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Jon
 * Date: 3/17/14
 * Time: 8:18 PM
 */
public class Team {
    private static final Logger log = LoggerFactory.getLogger(Team.class);
    private final int seed;
    private final String name;

    public static Team ConstructTeam(String team) {
        String seedToBeConverted = team.substring(0, team.indexOf("|"));
        try {
            int seed = Integer.parseInt(seedToBeConverted);
            return new Team(seed, team.substring(team.indexOf("|")+1, team.length()));
        } catch (Exception e) {
            log.error("Shit hit the  fan building Team, seed is not an int " + seedToBeConverted + " " + e);
            return new Team(0, "");
        }
    }

    private Team(int seed, String name) {
        this.name = name;
        this.seed = seed;
    }

    public int getSeed() {
        return seed;
    }

    public String getName() {
        return name;
    }
}
