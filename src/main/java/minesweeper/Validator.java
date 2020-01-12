package minesweeper;

import minesweeper.exception.InvalidDimensionException;
import minesweeper.exception.InvalidNumberOfMinesException;
import minesweeper.exception.MoveOutOfBoundsException;

public class Validator {

    public static void validateWidth(int width) throws InvalidDimensionException {
        validateDimensionValue(width, "width");
    }

    private static void validateDimensionValue(int value, String valueName) throws InvalidDimensionException {
        if (value <= 0 || value > Integer.MAX_VALUE - 2) {
            throw new InvalidDimensionException("The " + valueName + "of the game has to be greater than 0 and less than Integer.MAX_VALUE. Was " + value + ".");
        }
    }

    public static void validateHeight(int height) throws InvalidDimensionException {
        validateDimensionValue(height, "height");
    }

    public static void validateNumberOfMines(int width, int height, long numberOfMines) throws InvalidNumberOfMinesException {
        if (numberOfMines < 1 || numberOfMines >= width * height) {
            throw new InvalidNumberOfMinesException("The number of mines must be greater than 0 and less than a product of the width and the height (" + width * height + "). Was " + numberOfMines + ".");
        }
    }

    public static void validateMoveWithinBounds(int height, int width, int row, int column) throws MoveOutOfBoundsException {
        if (row < 1 || row > height) {
            throw new MoveOutOfBoundsException("Possible row are 1..." + height + ". Requested " + row + ".");
        }

        if (column < 1 || column > width) {
            throw new MoveOutOfBoundsException("Possible columns are 1..." + width + ". Requested " + column + ".");
        }
    }
}