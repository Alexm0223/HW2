package com.example.hw215_squarespuzzle;
import java.util.Random;

public class GameBoard {
    public int [][] board;
    private final int MAX_ROWS = 4;
    private final int MAX_COLUMNS = 4;
    private static final int EMPTY_POSITION = 16;

    public GameBoard(){
        this.board = new int [MAX_ROWS][MAX_COLUMNS];
        this.board = makeBoard(this.board);
    }

    //Randomly make a number between 1-16, having 16 be empty
    private int randomNumberMaker(){
        Random rand = new Random();
        int number = rand.nextInt(16);

            while(checkDoubles(number)){
                number = rand.nextInt(17);
            }
            return number;
    }

    //Check to make sure there are no doubles(same number repeated twice)
    private boolean checkDoubles(int number){
        boolean isFound = false;
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[i][j] == number){
                    isFound = true;
                }
            }
        }
        return isFound;
    }

    //help function that creates the board
    private int[][] makeBoard(int[][] board) {
        for(int i = 0; i <board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[i][j] = randomNumberMaker();
            }
        }
        return board;
    }

    //check if the board is sorted
    private boolean isSorted() {
        int number = 0;
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[i][j] < number){
                    return false;
                }
                number = this.board[i][j];
            }
        }
        return true;
    }

    // Find the X coordinate of the movable spot
    public int findEmptyPositionX() {
        int x = 0;
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[i][j] == EMPTY_POSITION){
                    x = i;
                }
            }
        }
        return x;
    }
    // Find the Y coordinate of the movable spot
    private int findEmptyPositionY(){
        int y = 0;
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[i][j] == EMPTY_POSITION){
                    y = j;
                }
            }
        }
        return y;
    }

    //Check if the current spot is movable
    public boolean isMoveable(int x, int y) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (i == x && j == y) {
                    System.out.println(i + " " + j);
                    if (i == 0 && j == 0) {
                        if (this.board[i + 1][j] == EMPTY_POSITION || this.board[i][j + 1] == EMPTY_POSITION) {

                            return true;
                        }
                    } else if (i == 3 && j == 3) {
                        if (this.board[i - 1][j] == EMPTY_POSITION || this.board[i][j - 1] == EMPTY_POSITION) {
                            return true;
                        }
                    } else if (i == 0 && j == 3) {
                        if (this.board[i + 1][j] == EMPTY_POSITION || this.board[i][j - 1] == EMPTY_POSITION) {
                            return true;
                        }
                    } else if (i == 3 && j == 0) {
                        if (this.board[i - 1][j] == EMPTY_POSITION || this.board[i][j + 1] == EMPTY_POSITION) {
                            return true;
                        }
                    } else if (i == 0) {
                        if (this.board[i + 1][j] == EMPTY_POSITION || this.board[i][j + 1] == EMPTY_POSITION
                                || this.board[i][j - 1] == EMPTY_POSITION) {
                            return true;
                        }
                    } else if (i == 3) {
                        if (this.board[i][j - 1] == EMPTY_POSITION || this.board[i][j + 1] == EMPTY_POSITION
                                || this.board[i - 1][j] == EMPTY_POSITION) {
                            return true;
                        }
                    } else if (j == 0) {
                        if (this.board[i][j + 1] == EMPTY_POSITION || this.board[i + 1][j] == EMPTY_POSITION
                                || this.board[i - 1][j] == EMPTY_POSITION) {
                            return true;
                        }
                    } else if (j == 3) {
                        if (this.board[i][j - 1] == EMPTY_POSITION || this.board[i + 1][j] == EMPTY_POSITION
                                || this.board[i - 1][j] == EMPTY_POSITION) {
                            return true;
                        }
                    } else if (this.board[i][j - 1] == EMPTY_POSITION || this.board[i][j + 1] == EMPTY_POSITION
                            || this.board[i + 1][j] == EMPTY_POSITION || this.board[i - 1][j] == EMPTY_POSITION) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //When making a move
    public void move(int x, int y) {
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if((i == x && j == y) && isMoveable(x, y)) {
                    this.board[findEmptyPositionX()][findEmptyPositionY()] = this.board[i][j];
                    this.board[i][j] = EMPTY_POSITION;
                }
            }
        }
    }

    //Checks if the user won the game
    public boolean isWon(){
        if(isSorted()){
            return true;
        }
        return false;
    }

}
