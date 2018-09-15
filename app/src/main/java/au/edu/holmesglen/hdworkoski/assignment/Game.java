package au.edu.holmesglen.hdworkoski.assignment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * File: Game.java
 * Author: Hillary Dworkoski
 * Last Updated: 12/9/18
 * Description: Game class to define game values, create a new game, and control game logic in view
 */

public class Game{
    private Item[] gridArray;
    private boolean[] wasClicked;
    private int turn;
    private Item color1;
    private Item color2;
    private int g1;
    private int g2;
    private int b1;
    private int b2;
    private Button btnNewGame;
    private View iv;

    //constructor
    public Game(Item color1, Item color2, View btnNewGame, View iv) {
        this.color1 = color1;
        this.color2 = color2;

        turn = 0;

        this.iv = iv;

        //hide New Game button
        this.btnNewGame = (Button) btnNewGame;
        btnNewGame.setVisibility(View.GONE);

        //create an array to hold 16 Item objects (the 4x4 grid) with all grey images to start
        gridArray = new Item[16];
        for (int i = 0; i < 16; i++) {
            gridArray[i] = new Item(R.drawable.grey, "grey");
        }

        //create an array of 16 booleans that hold 'true'
        //if that position has been clicked in the game already
        wasClicked = new boolean[16];
        for (int i=0; i<16; i++) {
            wasClicked[i] = false;
        }

        //set 4 random numbers of squares to be colored already
        g1 = (int) Math.floor(Math.random() * 16);

        do {
            g2 = (int) Math.floor(Math.random() * 16);
        }
        while (g2 == g1);

        do {
            b1 = (int) Math.floor(Math.random() * 16);
        }
        while (b1 == g1 || b1 == g2);

        do {
            b2 = (int) Math.floor(Math.random() * 16);
        }
        while (b2 == b1 || b2 == g1 || b2 == g2);

        //set the randomly selected squares in the array to color 1 and color 2
        gridArray[g1] = color1;
        gridArray[g2] = color1;
        gridArray[b1] = color2;
        gridArray[b2] = color2;

        //set the wasClicked array to true for the pre-colored squares
        wasClicked[g1] = true;
        wasClicked[g2] = true;
        wasClicked[b1] = true;
        wasClicked[b2] = true;
    }

    //getters and setters
    public int getG1() {
        return g1;
    }

    public int getG2() {
        return g2;
    }

    public int getB1() {
        return b1;
    }

    public int getB2() {
        return b2;
    }

    public Item[] getGridArray() {
        return gridArray;
    }

    public void setGridArray(Item[] gridArray) {
        this.gridArray = gridArray;
    }

    public boolean[] getWasClicked() {
        return wasClicked;
    }

    public void setWasClicked(boolean[] wasClicked) {
        this.wasClicked = wasClicked;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * method to change the 'Next Color' image after each turn
     * @param turn game turn number
     */
    public void setNext(int turn, View iv) {
        if (turn % 2 != 0)
            ((ImageView) iv).setImageResource(color1.getColor());
        else
            ((ImageView) iv).setImageResource(color2.getColor());
    }

    /**
     * method to make a turn in the game
     * @param position position of selected square
     * @param v view of selected square
     */
    public void setGameTurn(int position, View v) {
        //change clicked boolean to 'true' for that position so it can't be selected again
        wasClicked[position] = true;

        //change the color to either color 1 or color 2
        if ((turn % 2) != 0) {
            ((ImageView) v).setImageResource(color2.getColor());
            gridArray[position] = color2;
        }
        else {
            ((ImageView) v).setImageResource(color1.getColor());
            gridArray[position] = color1;
        }

        //set the 'Next Color' image to the next color
        setNext(turn, iv);

        //increase turn variable
        turn++;
    }

    /**
     * method to determine if the user has lost the game because there is a 3 in a row
     * @param position position selected by the user
     * @return boolean if the game has been lost
     */
    public boolean gameOver(int position) {

        //variable to save if the user has lost or not
        boolean over = false;

        //if the selected position is color1
        if(gridArray[position].getTitle().equals(color1.getTitle())) {
            if ((position == 0) || (position == 1) || (position == 4) || (position == 5) || (position == 8) || (position == 9) || (position == 12) || (position == 13)) {
                if(gridArray[position + 1].getTitle().equals(color1.getTitle()) && gridArray[position + 2].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 0) || (position == 1) || (position == 2) || (position == 3) || (position == 4) || (position == 5) || (position == 6) || (position == 7)) {
                if(gridArray[position + 4].getTitle().equals(color1.getTitle()) && gridArray[position + 8].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 1) || (position == 2) || (position == 5) || (position == 6) || (position == 9) || (position == 10) || (position == 13) || (position == 14)) {
                if(gridArray[position + 1].getTitle().equals(color1.getTitle()) && gridArray[position - 1].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 2) || (position == 3) || (position == 6) || (position == 7) || (position == 10) || (position == 11) || (position == 14) || (position == 15)) {
                if(gridArray[position - 1].getTitle().equals(color1.getTitle()) && gridArray[position - 2].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 4) || (position == 5) || (position == 6) || (position == 7) || (position == 8) || (position == 9) || (position == 10) || (position == 11)) {
                if(gridArray[position + 4].getTitle().equals(color1.getTitle()) && gridArray[position - 4].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 8) || (position == 9) || (position == 10) || (position == 11) || (position == 12) || (position == 13) || (position == 14) || (position == 15)) {
                if(gridArray[position - 4].getTitle().equals(color1.getTitle()) && gridArray[position - 8].getTitle().equals(color1.getTitle()))
                    over = true;
            }
        }
        //if the selected position is color 2
        else if(gridArray[position].getTitle().equals(color2.getTitle())) {
            if ((position == 0) || (position == 1) || (position == 4) || (position == 5) || (position == 8) || (position == 9) || (position == 12) || (position == 13)) {
                if(gridArray[position + 1].getTitle().equals(color2.getTitle()) && gridArray[position + 2].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 0) || (position == 1) || (position == 2) || (position == 3) || (position == 4) || (position == 5) || (position == 6) || (position == 7)) {
                if(gridArray[position + 4].getTitle().equals(color2.getTitle()) && gridArray[position + 8].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 1) || (position == 2) || (position == 5) || (position == 6) || (position == 9) || (position == 10) || (position == 13) || (position == 14)) {
                if(gridArray[position + 1].getTitle().equals(color2.getTitle()) && gridArray[position - 1].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 2) || (position == 3) || (position == 6) || (position == 7) || (position == 10) || (position == 11) || (position == 14) || (position == 15)) {
                if(gridArray[position - 1].getTitle().equals(color2.getTitle()) && gridArray[position - 2].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 4) || (position == 5) || (position == 6) || (position == 7) || (position == 8) || (position == 9) || (position == 10) || (position == 11)) {
                if(gridArray[position + 4].getTitle().equals(color2.getTitle()) && gridArray[position - 4].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 8) || (position == 9) || (position == 10) || (position == 11) || (position == 12) || (position == 13) || (position == 14) || (position == 15)) {
                if(gridArray[position - 4].getTitle().equals(color2.getTitle()) && gridArray[position - 8].getTitle().equals(color2.getTitle()))
                    over = true;
            }
        }
        return over;
    }

    /**
     * method to end the game, only called when 3 in a row is found
     */
    public void endGame() {
        //tell all spaces in array that they have been clicked so the game does not continue
        for (int i=0; i<16; i++) {
            wasClicked[i] = true;
        }

        //show new game button
        btnNewGame.setVisibility(View.VISIBLE);
    }
}