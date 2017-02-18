package bot;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BotStarter {
    private final Map<String, Boolean> fourInARowCache = new HashMap<>();
    private final Map<String, Transposition> transpositions = new HashMap<>();
    private final KillerMoves killerMoves = new KillerMoves(42);
//    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * Makes a turn.
     *
     * @param mField
     * @param mBotId
     * @return The column where the turn was made.
     */
    public int makeTurn(Field mField, int mBotId) {

        int round = mField.getRound();
        //Some static turns
        if (round == 1) {
            return 3;
        } else if (round == 2 && mField.getDisc(3, 5) == 1) {
            return 3;
        } else if (round == 3 && mField.getDisc(3, 4) == 2) {
            return 3;
        } else if (round == 4 && mField.getDisc(3, 3) == 1) {
            return 3;
        }

        Instant start = Instant.now();
        Instant deadline = start.plusMillis(500);

        final MiniMax miniMax = new MiniMax(mField, mBotId, fourInARowCache, transpositions, killerMoves);

        MiniMaxScore miniMaxScore = null;
        for (int depth = 8; depth < 43; depth++) {
            long millisLeft = deadline.minusMillis(Instant.now().toEpochMilli() + 50).toEpochMilli();
            if (millisLeft < 100) {
                break;
            }
            miniMaxScore = miniMax.miniMax(depth, mBotId);
//            final int finalDepth = depth;
//            Future<MiniMaxScore> submit = executorService.submit(() -> miniMax.miniMax(finalDepth, mBotId));
//            try {
//                miniMaxScore = submit.get(deadline.minusMillis(Instant.now().toEpochMilli() + 50).toEpochMilli(), TimeUnit.MILLISECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//                break;
//            } catch (TimeoutException e) {
//                System.err.println("Taking too long time, aborting.");
//                break;
//            }
            System.err.println("Depth: " + depth);
        }

        if (miniMaxScore == null) {
            System.err.println("Did not have time to calculate ANY score...");
            return randomColumn(mField);
        } else if (miniMaxScore.column == -1) {
            System.err.println("Move was -1, randomizing.");
            return randomColumn(mField);
        }
        System.err.println("Calculation took:" + Instant.now().minusMillis(start.toEpochMilli()).toEpochMilli());
        return miniMaxScore.column;
    }

    private int randomColumn(Field mField) {
        List<Integer> availableMoves = mField.getAvailableMoves();
        Collections.shuffle(availableMoves);
        return availableMoves.get(0);
    }

    public static void main(String[] args) {
        BotParser parser = new BotParser(new BotStarter());
        parser.run();
    }

}
