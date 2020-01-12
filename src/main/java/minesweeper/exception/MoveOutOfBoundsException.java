package minesweeper.exception;

public class MoveOutOfBoundsException extends Exception {
    public MoveOutOfBoundsException(String message) {
        super(message);
    }
}