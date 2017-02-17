package bot;

import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BotStarterTest {

    @Test
    public void timingTest() throws Exception {
        Instant start = Instant.now();
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0}});

        BotStarter botStarter = new BotStarter();
        botStarter.makeTurn(field, 1);
        long timeConsumed = Instant.now().minusMillis(start.toEpochMilli()).toEpochMilli();
        System.out.println(timeConsumed);
        assertThat(timeConsumed > 500, is(false));
    }

    @Test
    public void timingTestEndgame() throws Exception {
        Instant start = Instant.now();
        Field field = new Field(new int[][]{
                {1, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 2, 1},
                {2, 0, 0, 0, 1, 1, 2},
                {1, 2, 1, 2, 2, 1, 2},
                {2, 1, 2, 1, 2, 1, 2}});

        field.setRound(10); // Just bigger than 2
        BotStarter botStarter = new BotStarter();
        botStarter.makeTurn(field, 1);
        long timeConsumed = Instant.now().minusMillis(start.toEpochMilli()).toEpochMilli();
        System.out.println(timeConsumed);
        assertThat(timeConsumed > 500, is(false));
    }
}