package minesweeper.exception.command;

public class EmptyCommandException extends CommandException {

    public EmptyCommandException(String message) {
        super(message);
    }
}