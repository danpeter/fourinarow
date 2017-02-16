package bot;

public  class MiniMaxScore {
    public final int score;
    public final int column;

    private MiniMaxScore(int score, int column) {
        this.score = score;
        this.column = column;
    }

    public static MiniMaxScore of(int score, int column) {
        return new MiniMaxScore(score, column);
    }
}
