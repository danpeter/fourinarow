package bot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class FieldTest {
    @Test
    public void noScore() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(0));
    }

    @Test
    public void horizontalFourInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(Integer.MAX_VALUE));
    }

    @Test
    public void horizontalThreeInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));


        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 1, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));
    }

    @Test
    public void verticalFourInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(Integer.MAX_VALUE));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0},
                {2, 0, 1, 2, 1, 0, 0},
                {2, 0, 1, 1, 1, 0, 2},
                {2, 2, 2, 1, 1, 0, 2}});
        assertThat(field.score(1), is(Integer.MAX_VALUE));
    }

    @Test
    public void verticalThreeInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 2}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));
    }

    @Test
    public void diagonalUpFourInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(Integer.MAX_VALUE));
    }


    @Test
    public void diagonalUpThreeInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 1, 2, 2, 0, 0, 0},
                {1, 2, 2, 2, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));
    }

    @Test
    public void diagonalDownFourInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 1},
                {2, 1, 0, 0, 0, 0, 0},
                {2, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 1}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(Integer.MAX_VALUE));
    }

    @Test
    public void diagonalDownThreeInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 2, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 2, 1, 0, 0, 0},
                {0, 0, 2, 2, 0, 0, 0},
                {0, 0, 2, 2, 2, 1, 0}});
        System.out.println(field.toString());
        assertThat(field.score(1), is(100));
    }

    @Test
    public void undoLatestMove() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0}});
        System.out.println(field.toString());
        field.undoLatestMove(0);
        assertThat(field.getDisc(0, 5), is(0));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0}});
        System.out.println(field.toString());
        field.undoLatestMove(2);
        assertThat(field.getDisc(2, 5), is(0));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0}});
        System.out.println(field.toString());
        field.undoLatestMove(0);
        assertThat(field.getDisc(0, 5), is(1));
    }

    @Test
    public void name() throws Exception {
        Field field = new Field(7, 6);
        field.addDisc(2, 1);
        field.addDisc(2, 1);
        field.addDisc(2, 1);
        field.addDisc(2, 1);
        assertThat(field.score(1), is(Integer.MAX_VALUE));

    }

    @Test
    public void heuristicShouldBeZero() throws Exception {
        Field field = new Field(7, 6);
        field.addDisc(0,1);
        field.addDisc(0,2);
        field.addDisc(0,1);
        field.addDisc(0,2);
        field.addDisc(0,1);
        field.addDisc(0,2);
        assertThat(field.score(1), is(0));
        System.out.println(field.getBoardHash());
    }

    @Test
    public void checkHash() throws Exception {
        Field field = new Field(7, 6);
        field.addDisc(5, 1);
        field.addDisc(6, 2);
        field.addDisc(6, 1);
        assertThat(field.getBoardHash(), is("000000000000000000000000000000000010000012"));

        field = new Field(7, 6);
        field.addDisc(0, 1);
        field.addDisc(0, 2);
        field.addDisc(0, 1);
        field.addDisc(0, 1);
        field.addDisc(0, 1);
        assertThat(field.getBoardHash(), is("000000010000001000000100000020000001000000"));
    }

    @Test
    public void hasFourInARow() throws Exception {
        Field field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0}});
        assertThat(field.anyPlayerHasFourInARow(), is(true));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 2, 2, 2, 0}});
        assertThat(field.anyPlayerHasFourInARow(), is(true));

        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0}});
        assertThat(field.anyPlayerHasFourInARow(), is(true));


        field = new Field(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0}});
        assertThat(field.anyPlayerHasFourInARow(), is(false));

    }
}