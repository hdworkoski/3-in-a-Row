package au.edu.holmesglen.hdworkoski.assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    //create an array to hold 16 Item objects (the 4x4 grid)
    Item[] gridArray = new Item[16];
    boolean[] wasClicked = new boolean[16];
    GridView grid;
    ImageView iv;
    ImageAdapter iAdapter;
    int turn = 0;
    Item color1;
    Item color2;
    int c1;
    int c2;

    //name of keys and file
    public static final String SETTINGS = "Settings";
    public static final String BG = "bgKey";
    public static final String COL1 = "col1Key";
    public static final String COL2 = "col2Key";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up preferences
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);

        //assign values to variables
        grid = (GridView) findViewById(R.id.gridView);
        iv = (ImageView) findViewById(R.id.img_next_color);

        //set game colors
        setColors();
        //set next color
        setNext(turn + 1);
        //set background color
        setBG();

        //set 4 random squares to be colored already
        int g1 = (int) Math.floor(Math.random() * 16);
        int g2;
        int b1;
        int b2;

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

        //generate 4x4 array with all Items in the grid set to the grey image
        for (int i = 0; i < 16; i++) {
            gridArray[i] = new Item(R.drawable.grey, "grey");
        }

        //array of spaces to see if it was clicked it
        for (int i=0; i<16; i++) {
            wasClicked[i] = false;
        }

        //set the randomly selected squares to green and blue
        gridArray[g1] = color1;
        gridArray[g2] = color1;
        gridArray[b1] = color2;
        gridArray[b2] = color2;

        //now pass the ImageAdapter a reference to the array created above
        iAdapter = new ImageAdapter(this, gridArray);
        grid.setAdapter(iAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                if(!wasClicked[position]) {
                    wasClicked[position] = true;
                    //change the color
                    if ((turn % 2) != 0) {
                        ((ImageView) v).setImageResource(c2);
                        gridArray[position] = color2;
                    }
                    else {
                        ((ImageView) v).setImageResource(c1);
                        gridArray[position] = color1;
                    }
                    setNext(turn);
                    turn++;
                    boolean gameOver = gameOver(position);
                    if(gameOver) {
                        //tell all spaces in array that they have been clicked so the game does not continue
                        for (int i=0; i<16; i++) {
                            wasClicked[i] = true;
                        }

                        Toast toast = Toast.makeText(getApplicationContext(), "Game Over\nYou lost!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                if(turn == 12)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Game Over\nYou won!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        setBG();
        setColors();
    }

    public void setNext(int turn) {
        if (turn % 2 != 0)
            iv.setImageResource(c1);
        else
            iv.setImageResource(c2);
    }

    public void setBG() {
        if(sharedPref.contains(BG)) {
            String bg = sharedPref.getString(BG, "");
            if(bg.equals("White")) {
                findViewById(R.id.game_layout).setBackgroundColor(this.getResources().getColor(R.color.White));
            }
            else if(bg.equals("Grey")) {
                findViewById(R.id.game_layout).setBackgroundColor(this.getResources().getColor(R.color.Grey));
            }
            else if(bg.equals("Black")) {
                findViewById(R.id.game_layout).setBackgroundColor(this.getResources().getColor(R.color.Black));
            }
        }
    }

    public void setColors() {
        if(sharedPref.contains(COL1) && sharedPref.contains(COL2)) {
            String col1 = sharedPref.getString(COL1, "");
            String col2 = sharedPref.getString(COL2, "");

            if(col1.equals("Green")) {
                color1 = new Item(R.drawable.green, "green");
                c1 = R.drawable.green;
            }
            else if(col1.equals("Blue")) {
                color1 = new Item(R.drawable.blue, "blue");
                c1 = R.drawable.blue;
            }
            else if(col1.equals("Purple")) {
                color1 = new Item(R.drawable.purple, "purple");
                c1 = R.drawable.purple;
            }

            if(col2.equals("Red")) {
                color2 = new Item(R.drawable.red, "red");
                c2 = R.drawable.red;
            }
            else if(col2.equals("Orange")) {
                color2 = new Item(R.drawable.orange, "orange");
                c2 = R.drawable.orange;
            }
            else if(col2.equals("Yellow")) {
                color2 = new Item(R.drawable.yellow, "yellow");
                c2 = R.drawable.yellow;
            }
        }
        else {
            color1 = new Item(R.drawable.green, "green");
            c1 = R.drawable.green;
            color2 = new Item(R.drawable.red, "red");
            c2 = R.drawable.red;
        }
    }

    public boolean gameOver(int position) {
        System.out.println("color 1: " + color1);
        System.out.println("color 2: " + color2);
        System.out.println("selected color: " + gridArray[position]);
        System.out.println("position: " + position);
        boolean over = false;
        if(gridArray[position] == color1) {
            if ((position == 0) || (position == 1) || (position == 4) || (position == 5) || (position == 8) || (position == 9) || (position == 12) || (position == 13)) {
                System.out.println("here1");
                System.out.println("position + 1" + gridArray[position + 1]);
                System.out.println("position + 2" + gridArray[position + 2]);
                if(gridArray[position + 1].equals(color1) && gridArray[position + 2].equals(color1))
                    over = true;
            }
            if ((position == 0) || (position == 1) || (position == 2) || (position == 3) || (position == 4) || (position == 5) || (position == 6) || (position == 7)) {
                System.out.println("here2");
                if(gridArray[position + 4].equals(color1) && gridArray[position + 8].equals(color1))
                    over = true;
            }
            if ((position == 1) || (position == 2) || (position == 5) || (position == 6) || (position == 9) || (position == 10) || (position == 13) || (position == 14)) {
                System.out.println("here3");
                if(gridArray[position + 1].equals(color1) && gridArray[position - 1].equals(color1))
                    over = true;
            }
            if ((position == 2) || (position == 3) || (position == 6) || (position == 7) || (position == 10) || (position == 11) || (position == 14) || (position == 15)) {
                System.out.println("here4");
                if(gridArray[position - 1].equals(color1) && gridArray[position - 2].equals(color1))
                    over = true;
            }
            if ((position == 4) || (position == 5) || (position == 6) || (position == 7) || (position == 8) || (position == 9) || (position == 10) || (position == 11)) {
                System.out.println("here5");
                if(gridArray[position + 4].equals(color1) && gridArray[position - 4].equals(color1))
                    over = true;
            }
            if ((position == 8) || (position == 9) || (position == 10) || (position == 11) || (position == 12) || (position == 13) || (position == 14) || (position == 15)) {
                System.out.println("here6");
                if(gridArray[position - 4].equals(color1) && gridArray[position - 8].equals(color1))
                    over = true;
            }
        }
        else if(gridArray[position] == color2) {
            if ((position == 0) || (position == 1) || (position == 4) || (position == 5) || (position == 8) || (position == 9) || (position == 12) || (position == 13)) {
                System.out.println("here1.1");
                if(gridArray[position + 1].equals(color2) && gridArray[position + 2].equals(color2))
                    over = true;
            }
            if ((position == 0) || (position == 1) || (position == 2) || (position == 3) || (position == 4) || (position == 5) || (position == 6) || (position == 7)) {
                System.out.println("here1.2");
                if(gridArray[position + 4].equals(color2) && gridArray[position + 8].equals(color2))
                    over = true;
            }
            if ((position == 1) || (position == 2) || (position == 5) || (position == 6) || (position == 9) || (position == 10) || (position == 13) || (position == 14)) {
                System.out.println("here1.3");
                if(gridArray[position + 1].equals(color2) && gridArray[position - 1].equals(color2))
                    over = true;
            }
            if ((position == 2) || (position == 3) || (position == 6) || (position == 7) || (position == 10) || (position == 11) || (position == 14) || (position == 15)) {
                System.out.println("here1.4");
                if(gridArray[position - 1].equals(color2) && gridArray[position - 2].equals(color2))
                    over = true;
            }
            if ((position == 4) || (position == 5) || (position == 6) || (position == 7) || (position == 8) || (position == 9) || (position == 10) || (position == 11)) {
                System.out.println("here1.5");
                if(gridArray[position + 4].equals(color2) && gridArray[position - 4].equals(color2))
                    over = true;
            }
            if ((position == 8) || (position == 9) || (position == 10) || (position == 11) || (position == 12) || (position == 13) || (position == 14) || (position == 15)) {
                System.out.println("here1.6");
                if(gridArray[position - 4].equals(color2) && gridArray[position - 8].equals(color2))
                    over = true;
            }
        }
        return over;
    }
}