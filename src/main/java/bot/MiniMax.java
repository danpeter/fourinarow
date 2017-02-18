package bot;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MiniMax {

    private final Field field;
    private int maximizingPlayer;
    private Map<String, Boolean> fourInARowCache;
    private Map<String, Transposition> transpositions;
    private KillerMoves killerMoves;

    public MiniMax(Field field, int maximizingPlayer, Map<String, Boolean> fourInARowCache, Map<String, Transposition> transpositions, KillerMoves killerMoves) {
        this.field = field;
        this.maximizingPlayer = maximizingPlayer;
        this.fourInARowCache = fourInARowCache;
        this.transpositions = transpositions;
        this.killerMoves = killerMoves;
    }

    /**
     * returns best column
     */
    public MiniMaxScore miniMax(int depth, int player) {
        return miniMax(depth, player, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    }

    private MiniMaxScore miniMax(int depth, int player, int alpha, int beta, int ply) {
        int bestColumnMove = -1;
        int origAlpha = alpha;
        final Transposition transposition = transpositions.get(field.getBoardHash());
        if (transposition != null && transposition.depth >= depth) {
            if (transposition.type == Transposition.Type.EXACT) {
                return transposition.asScore();
            } else if (transposition.type == Transposition.Type.UPPER) {
                beta = Math.min(beta, transposition.value);
            } else if (transposition.type == Transposition.Type.LOWER) {
                alpha = Math.max(alpha, transposition.value);
            }
            if (alpha >= beta) {
                return transposition.asScore();
            }
        }

        List<Integer> moves = field.getAvailableMoves();

        if (moves.size() == 0) {
            //Board is full, draw
            return MiniMaxScore.of(0, bestColumnMove);
        } else if (depth == 0 || anyPlayerHasFourInARow()) {
            int score = field.getScore(maximizingPlayer);
            return MiniMaxScore.of(score, bestColumnMove);
        }

        Integer killerMove = killerMoves.getKillerMove(ply);
        for (int i = 0; i < moves.size(); i++) {
            if (transposition != null
                    && transposition.depth < depth
                    && (transposition.type == Transposition.Type.UPPER || transposition.type == Transposition.Type.EXACT)
                    && moves.get(i).equals(transposition.bestColumn)) {
                //We already know what the best move is from the transposition, so lets put the best move first
                Collections.swap(moves, 0, i);
                break;
            }

            if (killerMove != null && killerMove == i) {
                Collections.swap(moves, 0, i);
            }
        }

        for (int move : moves) {
            field.addDisc(move, player);
            int score = miniMax(depth - 1, otherPlayer(player), alpha, beta, ply + 1).score;
            if (player == maximizingPlayer) {
                if (score > alpha) {
                    alpha = score;
                    bestColumnMove = move;
                }
            } else {
                if (score < beta) {
                    beta = score;
                    bestColumnMove = move;
                }
            }
            field.undoLatestMove(move);
            if (alpha >= beta) {
                killerMoves.addKillerMove(ply, bestColumnMove);
                break;
            }

        }

        Transposition.Type type;
        if (alpha <= origAlpha) {
            type = Transposition.Type.UPPER;
        } else if (alpha >= beta) {
            type = Transposition.Type.LOWER;
        } else {
            type = Transposition.Type.EXACT;

        }

        Transposition newTransposition = new Transposition(depth, alpha, bestColumnMove, type);
        transpositions.put(field.getBoardHash(), newTransposition);
        return MiniMaxScore.of(player == maximizingPlayer ? alpha : beta, bestColumnMove);
    }

    private boolean anyPlayerHasFourInARow() {
        final String boardHash = field.getBoardHash();
        final Boolean cachedValue = fourInARowCache.get(boardHash);
        if (cachedValue == null) {
            boolean anyPlayerHasFourInARow = field.anyPlayerHasFourInARow();
            fourInARowCache.put(boardHash, anyPlayerHasFourInARow);
            return anyPlayerHasFourInARow;
        } else {
            return cachedValue;
        }
    }

    public static int otherPlayer(int player) {
        return player == 1 ? 2 : 1;
    }

}
