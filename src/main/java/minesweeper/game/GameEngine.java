package minesweeper.game;

import minesweeper.validator.RulesValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameEngine {

    private static final String WIN_MSG = "You win!";
    private static final String LOOSE_MSG = "You loose!";

    private final Logger log = LogManager.getLogger(this.getClass());

    private Minesweeper minesweeper;

    public int getHeight() {
        return minesweeper.getHeight();
    }

    public int getWidth() {
        return minesweeper.getWidth();
    }

    public void display() {
        for (int row = 1; row <= getHeight(); row++) {
            for (int column = 1; column <= getWidth(); column++) {
                if (isFlagged(row, column)) {
                    System.out.print("F");
                } else if (isHidden(row, column)) {
                    System.out.print("#");
                } else if (isUncoveredBomb(row, column)) {
                    System.out.print("o");
                } else if (isUncoveredEmptyField(row, column)) {
                    System.out.print(".");
                } else {
                    System.out.print(minesweeper.getField(row, column));
                }
            }
            System.out.println();
        }
    }

    private boolean minesweeperExists() {
        return null != minesweeper;
    }

    private boolean isFlagged(int row, int column) {
        return minesweeper.getField(row, column) >= 100;
    }

    private boolean isHidden(int row, int column) {
        return minesweeper.getField(row, column) >= 10;
    }

    private boolean isUncoveredBomb(int row, int column) {
        return minesweeper.getField(row, column) == 9;
    }

    private boolean isUncoveredEmptyField(int row, int column) {
        return minesweeper.getField(row, column) == 0;
    }

    public void create(int width, int height, long numberOfMines) {
        int extendedWidth = width + 2;
        int extendedHeight = height + 2;

        createMinesweeper(width, height, numberOfMines);
        setBoundaries(extendedWidth, extendedHeight);
        setMines(width, height, numberOfMines);
        populateOtherFields(extendedWidth, extendedHeight);
        startGame();
    }

    private void createMinesweeper(int width, int height, long numberOfMines) {
        RulesValidator.validateNumberOfMines(width, height, numberOfMines);

        minesweeper = new Minesweeper();
        int[][] fields = new int[height + 2][width + 2];
        minesweeper.setFields(fields);
        minesweeper.setNumberOfMines(numberOfMines);
    }

    private void setBoundaries(int extendedWidth, int extendedHeight) {
        setFirstAndLastColumn(extendedWidth, extendedHeight);
        setFirstAndLastRow(extendedWidth, extendedHeight);
    }

    private void setFirstAndLastColumn(int extendedWidth, int extendedHeight) {
        for (int row = 0; row < extendedHeight; row++) {
            minesweeper.setField(row, 0, 100);
            minesweeper.setField(row, extendedWidth - 1, 100);
        }
    }

    private void setFirstAndLastRow(int extendedWidth, int extendedHeight) {
        for (int column = 0; column < extendedWidth; column++) {
            minesweeper.setField(0, column, 100);
            minesweeper.setField(extendedHeight - 1, column, 100);
        }
    }

    private void setMines(int width, int height, long numberOfMines) {
        List<Integer> mines = new ArrayList<>(width * height);
        for (int i = 0; i < width * height; i++) {
            mines.add(i);
        }
        Collections.shuffle(mines);

        for (int m = 0; m < numberOfMines; m++) {
            int mine = mines.get(m);
            minesweeper.setField((mine / width) + 1, (mine % width) + 1, 19);
        }
    }

    private void populateOtherFields(int extendedWidth, int extendedHeight) {
        for (int row = 1; row < extendedHeight - 1; row++) {
            for (int column = 1; column < extendedWidth - 1; column++) {
                if (isMine(row, column)) {
                    continue;
                }

                int numberOfMinesAround = 0;
                if (isMine(row - 1, column - 1)) {
                    numberOfMinesAround++;
                }
                if (isMine(row - 1, column)) {
                    numberOfMinesAround++;
                }
                if (isMine(row - 1, column + 1)) {
                    numberOfMinesAround++;
                }
                if (isMine(row, column + 1)) {
                    numberOfMinesAround++;
                }
                if (isMine(row + 1, column + 1)) {
                    numberOfMinesAround++;
                }
                if (isMine(row + 1, column)) {
                    numberOfMinesAround++;
                }
                if (isMine(row + 1, column - 1)) {
                    numberOfMinesAround++;
                }
                if (isMine(row, column - 1)) {
                    numberOfMinesAround++;
                }
                minesweeper.setField(row, column, 10 + numberOfMinesAround);
            }
        }
    }

    private boolean isMine(int row, int column) {
        return minesweeper.getField(row, column) == 19;
    }

    private void startGame() {
        minesweeper.setRunning(true);
    }

    public void dig(int row, int column) {
        if (isFlagged(row, column) || isUncoveredField(row, column)) {
            return;
        }

        minesweeper.setField(row, column, minesweeper.getField(row, column) % 10);

        if (isUncoveredBomb(row, column)) {
            return;
        }

        if (isUncoveredNonEmptyField(row, column)) {
            return;
        }

        dig(row - 1, column - 1);
        dig(row - 1, column);
        dig(row - 1, column + 1);
        dig(row, column + 1);
        dig(row + 1, column + 1);
        dig(row + 1, column);
        dig(row + 1, column - 1);
        dig(row, column - 1);
    }

    private boolean isUncoveredField(int row, int column) {
        return minesweeper.getField(row, column) < 10;
    }

    private boolean isUncoveredNonEmptyField(int row, int column) {
        return minesweeper.getField(row, column) > 0;
    }

    public void checkGameStatus() {
        if (isWin()) {
            stopGame();
            log.info(WIN_MSG);
        }

        if (isLost()) {
            stopGame();
            log.info(LOOSE_MSG);
        }
    }

    private boolean isWin() {
        long numberOfUncoveredFields = 0L;
        for (int row = 1; row <= getHeight(); row++) {
            for (int column = 1; column <= getWidth(); column++) {
                if (isUncoveredNonBomb(row, column)) {
                    numberOfUncoveredFields++;
                }
            }
        }
        return getWidth() * getHeight() - numberOfUncoveredFields == minesweeper.getNumberOfMines();
    }

    private boolean isUncoveredNonBomb(int row, int column) {
        return isUncoveredField(row, column) && !isUncoveredBomb(row, column);
    }

    private boolean isLost() {
        for (int row = 1; row <= getHeight(); row++) {
            for (int column = 1; column <= getWidth(); column++) {
                if (isUncoveredBomb(row, column)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void stopGame() {
        minesweeper.setRunning(false);
    }

    public void setFlag(int row, int column) {
        minesweeper.setField(row, column, minesweeper.getField(row, column) + 100);
    }

    public boolean isRunning() {
        return minesweeperExists() && minesweeper.isRunning();
    }
}