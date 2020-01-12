package minesweeper.command;

import minesweeper.exception.MoveOutOfBoundsException;
import minesweeper.game.GameEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlagCommand implements Command {

    private final Logger log = LogManager.getLogger(this.getClass());

    private final int row;
    private final int column;

    public FlagCommand(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void execute(GameEngine engine) {
        if (engine.isRunning()) {
            try {
                engine.setFlag(row, column);
            } catch (MoveOutOfBoundsException e) {
                log.warn("Command failed. Reason: {}", e.getMessage());
            }
        } else {
            log.warn("Game is over. Start a new game.");
        }
    }
}