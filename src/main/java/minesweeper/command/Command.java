package minesweeper.command;

import minesweeper.game.GameEngine;

public interface Command {

    void execute(GameEngine engine);
}
