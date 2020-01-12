package minesweeper.command;

import minesweeper.game.GameEngine;

public class QuitCommand implements Command {

    @Override
    public void execute(GameEngine engine) {
        System.exit(0);
    }
}
