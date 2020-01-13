package minesweeper.validator;

import minesweeper.exception.rule.GameNotYetStartedException;
import minesweeper.exception.rule.InvalidNumberOfMinesException;
import minesweeper.exception.rule.MoveOutOfBoundsException;
import minesweeper.game.GameEngine;

public class RulesValidator {

    public static void validateNumberOfMines(int width, int height, long numberOfMines) throws InvalidNumberOfMinesException {
        if (numberOfMines < 1 || numberOfMines >= width * height) {
            throw new InvalidNumberOfMinesException("The number of mines must be greater than 0 and less than a product of the width and the height (" + (width * height) + "). Was " + numberOfMines + ".");
        }
    }

    public static void validateMoveWithinBounds(GameEngine engine, int row, int column) throws MoveOutOfBoundsException {
        if (row < 1 || row > engine.getHeight()) {
            throw new MoveOutOfBoundsException("Possible row are 0..." + (engine.getHeight() - 1) + ". Requested " + (row - 1) + ".");
        }

        if (column < 1 || column > engine.getWidth()) {
            throw new MoveOutOfBoundsException("Possible columns are 0..." + (engine.getWidth() - 1) + ". Requested " + (column - 1) + ".");
        }
    }

    public static void validateGameRunning(GameEngine engine) {
        if (!engine.isRunning()) {
            throw new GameNotYetStartedException("A game has to be running in order to dig or set a flag.");
        }
    }
}