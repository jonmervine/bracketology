package brackets;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * User: Jon
 * Date: 3/17/14
 * Time: 8:17 PM
 */
public class Round {
    private SeedRecords seedRecords;

    public void setSeedRecords(SeedRecords seedRecords) {
        this.seedRecords = seedRecords;
    }

    public List<Team> play(List<Team> bracket) {
        List<Team> winners = Lists.newArrayList();
        Preconditions.checkState(bracket.size() % 2 == 0);
        for (int i = 0; i < bracket.size(); i+=2) {
            Team one = bracket.get(i);
            Team two = bracket.get(i+1);

            winners.add(playGame(one, two));
        }
        return winners;
    }

    private Team playGame(Team one, Team two) {
        Map<Integer, Record> seedRecord = seedRecords.getSeedRecords(one.getSeed());
        Record matchup = seedRecord.get(two.getSeed());
        int thresholdToLose = (int) matchup.getPercent();
        Random random = new Random();
        int score = random.nextInt(100) + 1;

        if(score < thresholdToLose) return one;
        else return two;
    }
}
