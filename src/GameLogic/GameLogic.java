package GameLogic;

import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    int[][]mines; //yes or no
    boolean[][]flag; //becomes true if chosen
    boolean[][]hit; //hit or miss

    int emptySpace; //area ;
    int[][] board = new int[9][9]; //size of the board 9 rows and 9 columns
    boolean gamePlay;


    public void clearBoard () {
        gamePlay = true;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = -1;
            }
        }
    }

    public void placeBombs () {
        Random rand = new Random();
        int x;
        int y;
        for (int i = 0; i < board.length; i++) {
            do {
                x = rand.nextInt(board.length);
                y = rand.nextInt(board.length);
            }
            while (board[x][y] == 99); //id like to turn this into an x??
            board[x][y] = 99;
        }
    }

    public void displayGrid () {
        boolean[][] bomb = new boolean[9][9];
        bomb[0][1] = true;
        System.out.print(".");
        for (int i = 0; i < bomb.length; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();

        for (int row = 0; row < board.length; row++) {
            System.out.print(row);
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] > -1 && board[row][column] < 9) {
                    System.out.print("[" + board[row][column] + "]");
                } else {
                    System.out.print("|_|");
                }
            }
            System.out.println();  // go to next line
        }
    }

    public void askCoordinates () {
        //ask user for input to play i want x and y coordinates
        Scanner move = new Scanner(System.in);
        while (gamePlay = true) {
            System.out.println();
            displayGrid();
            int x;
            int y;

            System.out.print("Put your two coordinates in:"); //if i put in 1 value i get an error cause i expect2
            String input = move.nextLine();
            x = input.charAt(0) - '1'; //reads the two coordinated and stores them in the board - i want string here so parseint?
            y = input.charAt(1) - '1';


            if (board[x][y] == 99) {
                gamePlay = false;
                System.out.println("Out of bounds");
                return;
            }

            countMine(x, y);
            if (emptySpace == 81) {
                gamePlay = false;
                displayGrid();
                System.out.println();
            }
        }
    }

    public void countMine ( int x, int y){
        int i, j; //loop variables
        board[x][y] = 0;//used
        //how to prevent two mines to pop up at the same place?
        emptySpace--;
        for (i = x - 1; i <= x + 1; i++) {
            for (j = y - 1; j <= y + 1; j++) {
            }
        }

    }
}
