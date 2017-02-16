package bot;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


public class Field {
    private int[][] mBoard;
    private int mCols = 0, mRows = 0;
    private String mLastError = "";
    private final char[] boardHash = {'0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0',
            '0', '0', '0', '0', '0', '0', '0'};


    public Field(int columns, int rows) {
        mBoard = new int[rows][columns];
        mCols = columns;
        mRows = rows;
    }

    public Field(int[][] mBoard) {
        this.mBoard = mBoard;
        mRows = mBoard.length;
        mCols = mBoard[0].length;
    }

    /**
     * Sets the number of columns (this clears the board)
     *
     * @param args : int cols
     */
    public void setColumns(int cols) {
        mCols = cols;
        mBoard = new int[mRows][mCols];
    }

    /**
     * Sets the number of rows (this clears the board)
     *
     * @param args : int rows
     */
    public void setRows(int rows) {
        mRows = rows;
        mBoard = new int[mRows][mCols];
    }

    public int score(int player) {

        int score = 0;

        //check horizontal
        for (int row = 0; row < mRows; row++) {
            //Four in a row
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] == player
                        && mBoard[row][col + 1] == player
                        && mBoard[row][col + 2] == player
                        && mBoard[row][col + 3] == player) {
                    return Integer.MAX_VALUE;
                }
            }
            //Three in a row
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] == player
                        && mBoard[row][col + 1] == player
                        && mBoard[row][col + 2] == player
                        && mBoard[row][col + 3] == 0) {
                    score += 100;
                    col += 3; //dont evaluate those columns again
                } else if (mBoard[row][col] == 0
                        && mBoard[row][col + 1] == player
                        && mBoard[row][col + 2] == player
                        && mBoard[row][col + 3] == player) {
                    score += 100;
                    col += 3;
                }
            }
        }
        //check vertical
        for (int col = 0; col < mCols; col++) {
            //Four in a row
            for (int row = 0; row < mRows - 3; row++) {
                if (mBoard[row][col] == player
                        && mBoard[row + 1][col] == player
                        && mBoard[row + 2][col] == player
                        && mBoard[row + 3][col] == player) {
                    return Integer.MAX_VALUE;
                }
            }
            //Three in a row
            for (int row = 0; row < mRows - 3; row++) {
                if (mBoard[row][col] == player
                        && mBoard[row + 1][col] == player
                        && mBoard[row + 2][col] == player
                        && mBoard[row + 3][col] == 0) {
                    score += 100;
                    row += 3; //dont evaluate those columns again
                } else if (mBoard[row][col] == 0
                        && mBoard[row + 1][col] == player
                        && mBoard[row + 2][col] == player
                        && mBoard[row + 3][col] == player) {
                    score += 100;
                    row += 3;
                }
            }
        }
        //check diagonal up
        //Four in a row
        for (int row = 0; row < mRows - 3; row++) {
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] == player
                        && mBoard[row + 1][col + 1] == player
                        && mBoard[row + 2][col + 2] == player
                        && mBoard[row + 3][col + 3] == player) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        //Three in a row
        for (int row = 0; row < mRows - 3; row++) {
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] == player
                        && mBoard[row + 1][col + 1] == player
                        && mBoard[row + 2][col + 2] == player
                        && mBoard[row + 3][col + 3] == 0) {
                    score += 100;
                    row += 3;
                    col += 3;
                } else if (mBoard[row][col] == 0
                        && mBoard[row + 1][col + 1] == player
                        && mBoard[row + 2][col + 2] == player
                        && mBoard[row + 3][col + 3] == player) {
                    score += 100;
                    row += 3;
                    col += 3;
                }
            }
        }
        //check diagonal down
        //Four in a row
        for (int row = mRows - 1; row >= mRows - 3; row--) {
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] == player
                        && mBoard[row - 1][col + 1] == player
                        && mBoard[row - 2][col + 2] == player
                        && mBoard[row - 3][col + 3] == player) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        //Three in a row
        for (int row = mRows - 1; row >= mRows - 3; row--) {
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] == player
                        && mBoard[row - 1][col + 1] == player
                        && mBoard[row - 2][col + 2] == player
                        && mBoard[row - 3][col + 3] == 0) {
                    score += 100;
                    row -= 3;
                    col += 3;
                } else if (mBoard[row][col] == 0
                        && mBoard[row - 1][col + 1] == player
                        && mBoard[row - 2][col + 2] == player
                        && mBoard[row - 3][col + 3] == player) {
                    score += 100;
                    row -= 3;
                    col += 3;
                }
            }
        }
        return score;
    }

    /**
     * Adds a disc to the board
     *
     * @param args : command line arguments passed on running of application
     * @return : true if disc fits, otherwise false
     */
    public Boolean addDisc(int column, int disc) {
        mLastError = "";
        if (column < mCols) {
            for (int row = mRows - 1; row >= 0; row--) { // From bottom column up
                if (mBoard[row][column] == 0) {
                    mBoard[row][column] = disc;
                    updateHash(row, column, disc);
                    return true;
                }
            }
            mLastError = "Column is full.";
        } else {
            mLastError = "Move out of bounds.";
        }
        return false;
    }

    public void undoLatestMove(int column) {
        for (int row = 0; row < mRows; row++) { // From top column down
            if (mBoard[row][column] != 0) {
                mBoard[row][column] = 0;
                updateHash(row, column, 0);
                break;
            }
        }
    }

    /**
     * Initialise field from comma separated String
     *
     * @param String :
     */
    public void parseFromString(String s) {
        s = s.replace(';', ',');
        String[] r = s.split(",");
        int counter = 0;
        for (int y = 0; y < mRows; y++) {
            for (int x = 0; x < mCols; x++) {
                int player = Integer.parseInt(r[counter]);
                mBoard[y][x] = player;
                updateHash(y, x, player);
                counter++;
            }
        }
    }

    private void updateHash(int row, int col, int player) {
        boardHash[(row * mCols) + col] = Character.forDigit(player, 3);
    }

    /**
     * Returns the current piece on a given column and row
     *
     * @param args : int column, int row
     * @return : int
     */
    public int getDisc(int column, int row) {
        return mBoard[row][column];
    }

    /**
     * Returns whether a slot is open at given column
     *
     * @param args : int column
     * @return : Boolean
     */
    public Boolean isValidMove(int column) {
        return (mBoard[0][column] == 0);
    }

    /**
     * Returns reason why addDisc returns false
     *
     * @param args :
     * @return : reason why addDisc returns false
     */
    public String getLastError() {
        return mLastError;
    }

    @Override
    /**
     * Creates comma separated String with every cell.
     * @param args :
     * @return : String
     */
    public String toString() {
        String r = "";
        int counter = 0;
        for (int y = 0; y < mRows; y++) {
            for (int x = 0; x < mCols; x++) {
                if (counter > 0) {
                    r += ",";
                }
                if (counter % mCols == 0) {
                    r = r + "\n";
                }
                r += mBoard[y][x];
                counter++;
            }
        }
        return r;
    }

    /**
     * Checks whether the field is full
     *
     * @return : Returns true when field is full, otherwise returns false.
     */
    public boolean isFull() {
        for (int x = 0; x < mRows; x++)
            for (int y = 0; y < mCols; y++)
                if (mBoard[x][y] == 0)
                    return false; // At least one cell is not filled
        // All cells are filled
        return true;
    }

    /**
     * Checks whether the given column is full
     *
     * @return : Returns true when given column is full, otherwise returns false.
     */
    public boolean isColumnFull(int column) {
        return (mBoard[0][column] != 0);
    }

    /**
     * @return : Returns the number of columns in the field.
     */
    public int getNrColumns() {
        return mCols;
    }

    /**
     * @return : Returns the number of rows in the field.
     */
    public int getNrRows() {
        return mRows;
    }

    public List<Integer> getAvailableMoves() {
        if (isFull()) {
            return Collections.emptyList();
        } else {
            return IntStream.range(0, mCols)
                    .filter(i -> !isColumnFull(i))
                    .boxed()
                    .collect(toList());
        }
    }

    public int getScore(int maximizingPlayer) {
        int maximizerScore = score(maximizingPlayer);
        int minimizerScore = score(MiniMax.otherPlayer(maximizingPlayer));
        return maximizerScore - minimizerScore;
    }

    public boolean anyPlayerHasFourInARow() {
        //check horizontal
        for (int row = 0; row < mRows; row++) {
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] != 0
                        && mBoard[row][col] == mBoard[row][col + 1]
                        && mBoard[row][col + 1] == mBoard[row][col + 2]
                        && mBoard[row][col + 2] == mBoard[row][col + 3]) {
                    return true;
                }
            }
        }

        //check vertical
        for (int col = 0; col < mCols; col++) {
            //Four in a row
            for (int row = 0; row < mRows - 3; row++) {
                if (mBoard[row][col] != 0
                        && mBoard[row][col] == mBoard[row + 1][col]
                        && mBoard[row + 1][col] == mBoard[row + 2][col]
                        && mBoard[row + 2][col] == mBoard[row + 3][col]) {
                    return true;
                }
            }
        }
        //check diagonal up
        for (int row = 0; row < mRows - 3; row++) {
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] != 0
                        && mBoard[row][col] == mBoard[row + 1][col + 1]
                        && mBoard[row + 1][col + 1] == mBoard[row + 2][col + 2]
                        && mBoard[row + 2][col + 2] == mBoard[row + 3][col + 3]) {
                    return true;
                }
            }
        }


        //check diagonal down
        for (int row = mRows - 1; row >= mRows - 3; row--) {
            for (int col = 0; col < mCols - 3; col++) {
                if (mBoard[row][col] != 0
                        && mBoard[row][col] == mBoard[row - 1][col + 1]
                        && mBoard[row - 1][col + 1] == mBoard[row - 2][col + 2]
                        && mBoard[row - 2][col + 2] == mBoard[row - 3][col + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getBoardHash() {
        return String.valueOf(boardHash);
    }
}
