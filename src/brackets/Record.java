package brackets;

/**
 * User: Jon
 * Date: 3/17/14
 * Time: 8:41 PM
 */
public class Record {
    private final int wins;
    private final int loses;
    private final double percent;

    public Record(int wins, int loses, double percent) {
        this.wins = wins;
        this.loses = loses;
        this.percent = percent;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public double getPercent() {
        return percent;
    }
}
