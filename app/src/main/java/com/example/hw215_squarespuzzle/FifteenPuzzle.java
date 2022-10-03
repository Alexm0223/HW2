package com.example.hw215_squarespuzzle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Alex Martinez-Lopez
 * @version October 2, 2022
 */

public class FifteenPuzzle extends AppCompatActivity {
    private Button newGame;
    private GameBoard game;
    private TextView labels[][] = new TextView[4][4];
    private TextView moves;
    private int moveCounter; //count the number of moves
    private int[] labelColors = {Color.parseColor("#b4f8ffab"), Color.parseColor("#00000000")};

    public void newGame() {
        game = new GameBoard();
        printBoard();
        //set clickable so that in the end of the game it will become unclickable
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                labels[i][j].setClickable(true);
            }
        }
        //count number of moves the user has made during the game
        moveCounter = 0;
        moves.setText("Moves Made: " + moveCounter);

    }

    private void printBoard() {
        int label;
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                if (game.board[i][j] == 16) {
                    label = game.board[i][j];
                    labels[i][j].setBackgroundColor(labelColors[label - 1]);
                    labels[i][j].setText("");
                } else {
                    label = game.board[i][j];
                    labels[i][j].setText(String.valueOf(game.board[i][j]));
                    labels[i][j].setBackgroundColor(labelColors[label - 1]);
                }
            }
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame = (Button)findViewById(R.id.button);
        moves = (TextView)findViewById(R.id.moves);

        //=========== initialize the labels array ============

        labels[0][0]=(TextView) findViewById(R.id.textView2);
        labels[0][1]=(TextView) findViewById(R.id.textView3);
        labels[0][2]=(TextView) findViewById(R.id.textView4);
        labels[0][3]=(TextView) findViewById(R.id.textView5);
        labels[1][0]=(TextView) findViewById(R.id.textView6);
        labels[1][1]=(TextView) findViewById(R.id.textView7);
        labels[1][2]=(TextView) findViewById(R.id.textView8);
        labels[1][3]=(TextView) findViewById(R.id.textView9);
        labels[2][0]=(TextView) findViewById(R.id.textView10);
        labels[2][1]=(TextView) findViewById(R.id.textView11);
        labels[2][2]=(TextView) findViewById(R.id.textView12);
        labels[2][3]=(TextView) findViewById(R.id.textView13);
        labels[3][0]=(TextView) findViewById(R.id.textView14);
        labels[3][1]=(TextView) findViewById(R.id.textView15);
        labels[3][2]=(TextView) findViewById(R.id.textView16);
        labels[3][3]=(TextView) findViewById(R.id.textView17);

        //====================================================

        //start a new game
        newGame();

        newGame.setOnClickListener(new View.OnClickListener() {
            //when clicking on new game will jump a pop-up box to choose what to do
            public void onClick(View v) {
                //If will make sure the user actually wants to reset the game
                AlertDialog.Builder builder = new AlertDialog.Builder(FifteenPuzzle.this);
                builder.setTitle("RESET GAME");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        newGame();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        for (int i = 0; i < labels.length; i++) //same listener for all number buttons. after each move it will print the board again
        {
            for (int j = 0; j < labels.length; j++) {
                labels[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        for (int k = 0; k < labels.length; k++) {
                            for (int m = 0; m < labels.length; m++) {
                                if (labels[k][m].getId() == v.getId())//find the spot to move
                                {
                                    if(game.isMoveable(k,m))//for the counter of moves
                                    {
                                        moveCounter++;
                                        moves.setText("Moves Made: " + moveCounter);
                                    }
                                    game.move(k,m);
                                }
                            }
                            printBoard();

                            if (game.isWon())
                            {

                                for (int i = 0; i < labels.length; i++) {
                                    for (int j = 0; j < labels.length; j++)
                                    {
                                        labels[i][j].setClickable(false); //disable clicking on the numbers till new game starts
                                    }
                                }
                                Toast.makeText(FifteenPuzzle.this,"You won the the game!", Toast.LENGTH_LONG).show(); //victory message

                            }
                        }
                    }
                });
            }
        }
    }
}

