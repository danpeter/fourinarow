package bot;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MiniMaxTest {

    @Test
    public void basicTest() throws Exception {
        Instant start = Instant.now();
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}});

        HashMap<String, Transposition> transpositions = new HashMap<>();
        MiniMax miniMax = new MiniMax(field, 1, new HashMap<>(), transpositions, new KillerMoves(42));
        MiniMaxScore miniMaxScore = miniMax.miniMax(9, 1);
        System.out.println("Column: " + miniMaxScore.column + ", Score: " + miniMaxScore.score);
        System.out.println(Duration.between(start, Instant.now()).toMillis());
    }

    @Test
    public void blockHisFourInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 2, 0, 2, 2, 0}});

        MiniMax miniMax = new MiniMax(field, 1, new HashMap<>(), new HashMap<>(), new KillerMoves(42));
        MiniMaxScore miniMaxScore = miniMax.miniMax(6, 1);
        assertThat(miniMaxScore.column, is(3));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 2, 2, 0}});

        miniMax = new MiniMax(field, 1, new HashMap<>(), new HashMap<>(), new KillerMoves(42));
        miniMaxScore = miniMax.miniMax(6, 1);
        assertThat(miniMaxScore.column, is(0));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0},
                {2, 2, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0}});

        miniMax = new MiniMax(field, 2, new HashMap<>(), new HashMap<>(), new KillerMoves(42));
        miniMaxScore = miniMax.miniMax(6, 2);
        assertThat(miniMaxScore.column, is(3));

    }

    @Test
    public void winningMove() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 2, 0},
                {2, 0, 1, 0, 0, 1, 0},
                {2, 0, 2, 1, 0, 2, 0}});

        MiniMax miniMax = new MiniMax(field, 1, new HashMap<>(), new HashMap<>(), new KillerMoves(42));
        MiniMaxScore miniMaxScore = miniMax.miniMax(10, 1);
        assertThat(miniMaxScore.column, is(2));

        //This situation came up in a testgame and the bot failed to pick the right one
//        Field field = new Field(new int[][]{
//                {0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0, 0, 0},
//                {2, 0, 1, 2, 1, 0, 0},
//                {2, 0, 1, 1, 1, 0, 2},
//                {2, 2, 2, 1, 1, 0, 2}});
//
//        MiniMax miniMax = new MiniMax(field, 1, new HashMap<>(), new HashMap<>());
//        MiniMaxScore miniMaxScore = miniMax.miniMax(6, 1);
//        assertThat(miniMaxScore.column, anyOf(is(4), is(1)));

    }

    @Test
    public void iterativeMiniMax() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}});
        Instant start = Instant.now();
        MiniMax miniMax = new MiniMax(field, 1, new HashMap<>(), new HashMap<>(), new KillerMoves(42));
        for (int i = 8; i < 10; i++) {
            miniMax.miniMax(i, 1);
        }
        System.out.println(Duration.between(start, Instant.now()).toMillis());

    }
}