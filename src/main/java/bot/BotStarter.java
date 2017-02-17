package bot;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

class BotStarter {
    private final Map<String, Boolean> fourInARowCache = new HashMap<>();
    private final Map<String, Transposition> transpositions = new HashMap<>();

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
            if (mBotId == 1) {
                return 3;
            } else if (mBotId == 2 && mField.getDisc(3, 5) == 1) {
                return 3;
            }
        } else if (round == 2) {
            if (mBotId == 1 && mField.getDisc(3, 4) == 2) {
                return 3;
            } else if (mBotId == 2 && mField.getDisc(3, 5) == 1) {
                return 3;
            }
        }

        Instant deadline = Instant.now().plusMillis(500);

        final MiniMax miniMax = new MiniMax(mField, mBotId, fourInARowCache, transpositions);

        MiniMaxScore miniMaxScore = null;
        for (int depth = 8; depth < 20; depth++) {
            long millisLeft = deadline.minusMillis(Instant.now().toEpochMilli() + 50).toEpochMilli();
            if (millisLeft < 50) {
                break;
            }
            final int finalDepth = depth;
            Future<MiniMaxScore> submit = Executors.newSingleThreadExecutor().submit(() -> miniMax.miniMax(finalDepth, mBotId));
            try {
                miniMaxScore = submit.get(deadline.minusMillis(Instant.now().toEpochMilli() + 50).toEpochMilli(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
                break;
            } catch (TimeoutException e) {
//                System.out.println("Taking too long time, aborting.");
                break;
            }
        }

        if(miniMaxScore == null || miniMaxScore.column == -1) {
            // something went wrong in the algo?! still better to return something valid
            List<Integer> availableMoves = mField.getAvailableMoves();
            Collections.shuffle(availableMoves);
            return availableMoves.get(0);
        }
        return miniMaxScore.column;
    }

    public static void main(String[] args) {
        BotParser parser = new BotParser(new BotStarter());
        parser.run();
    }

}
