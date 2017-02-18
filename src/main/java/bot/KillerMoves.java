package bot;

public class KillerMoves {

    private final Integer[] killerMoves;

    public KillerMoves(int totalPly) {
        killerMoves = new Integer[totalPly];
    }

    public void addKillerMove(int ply, int column) {
        killerMoves[ply] = column;
    }

    public Integer getKillerMove(int ply) {
        Integer killerMoveSamePly = killerMoves[ply];
        if (killerMoveSamePly != null) {
            return killerMoveSamePly;
        } else if (ply < killerMoves.length - 1) {
            Integer killerMovePlyPlus = killerMoves[ply + 1];
            if (killerMovePlyPlus != null) {
                return killerMovePlyPlus;
            }
        } else if (ply > 0) {
            Integer killerMovePlyMinus = killerMoves[ply - 1];
            if (killerMovePlyMinus != null) {
                return killerMovePlyMinus;
            }
        }
        return null;
    }
}
