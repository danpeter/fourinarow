package bot;

public class Transposition {
    public final int depth;
    public final int value;
    public final int bestColumn;
    public final Type type;

    public Transposition(int depth, int value, int bestColumn, Type type) {
        this.depth = depth;
        this.value = value;
        this.bestColumn = bestColumn;
        this.type = type;
    }

    public enum Type  {
        UPPER, LOWER, EXACT
    }

    public MiniMaxScore asScore() {
        return MiniMaxScore.of(value, bestColumn);
    }
}
