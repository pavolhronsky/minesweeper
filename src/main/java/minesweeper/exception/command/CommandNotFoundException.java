package minesweeper.exception.command;

public class CommandNotFoundException extends CommandException {

    public CommandNotFoundException(String message) {
        super(message);
    }
}