package au.edu.holmesglen.hdworkoski.assignment;

/**
 * File: Item.java
 * Author: Hillary Dworkoski
 * Last Updated: 12/9/18
 * Description: Item class to define item object
 */

public class Item {
    //create variables
    private int colorImg;
    private String title;

    //constructor
    public Item(int colImg, String title) {
        this.setColor(colImg);
        this.title = title;
    }

    //get color method
    public int getColor() {
        return colorImg;
    }

    //set color method
    public void setColor(int position) {
        colorImg = position;
    }

    //get title method
    public String getTitle() {
        return title;
    }
}