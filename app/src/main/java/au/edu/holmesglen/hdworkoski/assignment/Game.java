package au.edu.holmesglen.hdworkoski.assignment;

/**
 * File: Game.java
 * Author: Hillary Dworkoski
 * Last Updated: 12/9/18
 * Description: Game class to define game values and create a new game
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

    //constructor
    public Game(Item color1, Item color2) {
        this.color1 = color1;
        this.color2 = color2;

        turn = 0;

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
     * method to determine if the user has lost the game because there is a 3 in a row
     * @param position position selected by the user
     * @return boolean if the game has been lost
     */
    public boolean gameOver(int position) {
        System.out.println("color 1: " + color1);
        System.out.println("color 2: " + color2);
        System.out.println("selected color: " + gridArray[position]);
        System.out.println("position: " + position);

        //variable to save if the user has lost or not
        boolean over = false;

        //if the selected position is color1
        if(gridArray[position].getTitle().equals(color1.getTitle())) {
            if ((position == 0) || (position == 1) || (position == 4) || (position == 5) || (position == 8) || (position == 9) || (position == 12) || (position == 13)) {
                System.out.println("here1");
                System.out.println("position + 1" + gridArray[position + 1].getTitle());
                System.out.println("position + 2" + gridArray[position + 2].getTitle());
                if(gridArray[position + 1].getTitle().equals(color1.getTitle()) && gridArray[position + 2].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 0) || (position == 1) || (position == 2) || (position == 3) || (position == 4) || (position == 5) || (position == 6) || (position == 7)) {
                System.out.println("here2");
                if(gridArray[position + 4].getTitle().equals(color1.getTitle()) && gridArray[position + 8].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 1) || (position == 2) || (position == 5) || (position == 6) || (position == 9) || (position == 10) || (position == 13) || (position == 14)) {
                System.out.println("here3");
                if(gridArray[position + 1].getTitle().equals(color1.getTitle()) && gridArray[position - 1].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 2) || (position == 3) || (position == 6) || (position == 7) || (position == 10) || (position == 11) || (position == 14) || (position == 15)) {
                System.out.println("here4");
                if(gridArray[position - 1].getTitle().equals(color1.getTitle()) && gridArray[position - 2].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 4) || (position == 5) || (position == 6) || (position == 7) || (position == 8) || (position == 9) || (position == 10) || (position == 11)) {
                System.out.println("here5");
                if(gridArray[position + 4].getTitle().equals(color1.getTitle()) && gridArray[position - 4].getTitle().equals(color1.getTitle()))
                    over = true;
            }
            if ((position == 8) || (position == 9) || (position == 10) || (position == 11) || (position == 12) || (position == 13) || (position == 14) || (position == 15)) {
                System.out.println("here6");
                if(gridArray[position - 4].getTitle().equals(color1.getTitle()) && gridArray[position - 8].getTitle().equals(color1.getTitle()))
                    over = true;
            }
        }
        //if the selected position is color 2
        else if(gridArray[position].getTitle().equals(color2.getTitle())) {
            if ((position == 0) || (position == 1) || (position == 4) || (position == 5) || (position == 8) || (position == 9) || (position == 12) || (position == 13)) {
                System.out.println("here1.1");
                if(gridArray[position + 1].getTitle().equals(color2.getTitle()) && gridArray[position + 2].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 0) || (position == 1) || (position == 2) || (position == 3) || (position == 4) || (position == 5) || (position == 6) || (position == 7)) {
                System.out.println("here1.2");
                if(gridArray[position + 4].getTitle().equals(color2.getTitle()) && gridArray[position + 8].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 1) || (position == 2) || (position == 5) || (position == 6) || (position == 9) || (position == 10) || (position == 13) || (position == 14)) {
                System.out.println("here1.3");
                if(gridArray[position + 1].getTitle().equals(color2.getTitle()) && gridArray[position - 1].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 2) || (position == 3) || (position == 6) || (position == 7) || (position == 10) || (position == 11) || (position == 14) || (position == 15)) {
                System.out.println("here1.4");
                if(gridArray[position - 1].getTitle().equals(color2.getTitle()) && gridArray[position - 2].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 4) || (position == 5) || (position == 6) || (position == 7) || (position == 8) || (position == 9) || (position == 10) || (position == 11)) {
                System.out.println("here1.5");
                if(gridArray[position + 4].getTitle().equals(color2.getTitle()) && gridArray[position - 4].getTitle().equals(color2.getTitle()))
                    over = true;
            }
            if ((position == 8) || (position == 9) || (position == 10) || (position == 11) || (position == 12) || (position == 13) || (position == 14) || (position == 15)) {
                System.out.println("here1.6");
                if(gridArray[position - 4].getTitle().equals(color2.getTitle()) && gridArray[position - 8].getTitle().equals(color2.getTitle()))
                    over = true;
            }
        }
        return over;
    }
}
