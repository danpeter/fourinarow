package bot;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
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
                break;
            }

        }

        return miniMaxScore.column;
    }

    public static void main(String[] args) {
        BotParser parser = new BotParser(new BotStarter());
        parser.run();
    }

}
