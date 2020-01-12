package minesweeper.command;

import minesweeper.game.GameEngine;

public class CreateCommand implements Command {

    private final int width;
    private final int height;
    private final long numberOfMines;

    public CreateCommand(int width, int height, long numberOfMines) {
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
    }

    @Override
    public void execute(GameEngine engine) {
        engine.create(width, height, numberOfMines);
    }
}