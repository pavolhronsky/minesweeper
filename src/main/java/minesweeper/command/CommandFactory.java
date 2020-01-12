package minesweeper.command;

import minesweeper.Converter;
import minesweeper.Validator;
import minesweeper.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {

    private final Logger log = LogManager.getLogger(this.getClass());

    private static final String COMMAND_DELIMITER = " ";

    public Command create(String rawInput) throws EmptyCommandException, CommandNotFoundException, InvalidNumberOfParametersException, InvalidNumberOfMinesException, InvalidDimensionException {
        log.debug("Command selected: '{}'.", rawInput);
        String[] splitInput = rawInput.trim().split(COMMAND_DELIMITER);
        if (splitInput.length == 0) {
            throw new EmptyCommandException();
        }

        switch (splitInput[0]) {
            case "C":
                return createCreateCommand(splitInput);
            case "D":
                return createDigCommand(splitInput);
            case "F":
                return createFlagCommand(splitInput);
            case "Q":
                return createQuitCommand(splitInput);
            default:
                throw new CommandNotFoundException("Command '" + rawInput + "' does not exist.");
        }
    }

    private Command createCreateCommand(String[] input) throws InvalidNumberOfParametersException, InvalidNumberOfMinesException, InvalidDimensionException {
        if (input.length < 4) {
            throw new InvalidNumberOfParametersException("Command C requires 3 integer inputs.");
        }

        int width = Converter.convertInt(input[1]);
        Validator.validateWidth(width);

        int height = Converter.convertInt(input[2]);
        Validator.validateHeight(height);

        long numberOfMines = Converter.convertLong(input[3]);
        Validator.validateNumberOfMines(width, height, numberOfMines);

        return new CreateCommand(width, height, numberOfMines);
    }

    private Command createDigCommand(String[] input) throws InvalidNumberOfParametersException {
        if (input.length < 3) {
            throw new InvalidNumberOfParametersException("Command D requires 2 integer inputs.");
        }

        int column = Converter.convertInt(input[1]);
        int row = Converter.convertInt(input[2]);
        return new DigCommand(row, column);

    }

    private Command createFlagCommand(String[] input) throws InvalidNumberOfParametersException {
        if (input.length < 3) {
            throw new InvalidNumberOfParametersException("Command F requires 2 integer inputs.");
        }

        int column = Converter.convertInt(input[1]);
        int row = Converter.convertInt(input[2]);

        return new FlagCommand(row, column);
    }

    private Command createQuitCommand(String[] input) {
        return new QuitCommand();
    }
}