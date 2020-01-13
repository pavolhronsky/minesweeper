package minesweeper.validator;

import minesweeper.exception.rule.InvalidDimensionException;

public class CommandValidator {

    public static void validateWidth(int width) throws InvalidDimensionException {
        validateDimensionValue(width, "width");
    }

    private static void validateDimensionValue(int value, String valueName) throws InvalidDimensionException {
        if (value < 1 || value > Integer.MAX_VALUE - 2) {
            throw new InvalidDimensionException("The " + valueName + "of the game has to be greater than 0 and less than or equal to " + (Integer.MAX_VALUE - 2) + ". Was " + value + ".");
        }
    }

    public static void validateHeight(int height) throws InvalidDimensionException {
        validateDimensionValue(height, "height");
    }
}