package Minesweeper;

import Minesweeper.GameLogic.GameLogic;

import java.util.Random;
import java.util.Scanner;

public class MinesweeperApp {

    public static void main (String args []){


        GameLogic game1 = new GameLogic();
        game1.clearBoard();
        game1.displayGrid();
        game1.placeBombs();
        game1.askCoordinates();
        //game1.showNeighbours (); have to display near by bombs

    }



}


