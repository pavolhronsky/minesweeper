package minesweeper.exception.rule;

public class MoveOutOfBoundsException extends RuleException {
    public MoveOutOfBoundsException(String message) {
        super(message);
    }
}