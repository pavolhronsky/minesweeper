package minesweeper.game;

public class Minesweeper {

    private boolean running;

    private int[][] fields;

    private long numberOfMines;

    public int getHeight() {
        return fields.length - 2;
    }

    public int getWidth() {
        return fields[0].length - 2;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int[][] getFields() {
        return fields;
    }

    public void setFields(int[][] fields) {
        this.fields = fields;
    }

    public int getField(int row, int column) {
        return fields[row][column];
    }

    public void setField(int row, int column, int value) {
        fields[row][column] = value;
    }

    public long getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(long numberOfMines) {
        this.numberOfMines = numberOfMines;
    }
}