package minesweeper.command;

import minesweeper.game.GameEngine;
import minesweeper.validator.RulesValidator;

public class DigCommand implements Command {

    private final int row;
    private final int column;

    public DigCommand(int row, int column) {
        this.row = row + 1;
        this.column = column + 1;
    }

    @Override
    public void execute(GameEngine engine) {
        RulesValidator.validateGameRunning(engine);
        RulesValidator.validateMoveWithinBounds(engine, row, column);

        engine.dig(row, column);
        engine.checkGameStatus();
    }
}