package minesweeper;

public class Converter {

    public static int convertInt(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

    public static long convertLong(String input) throws NumberFormatException {
        return Long.parseLong(input);
    }
}