package au.edu.holmesglen.hdworkoski.assignment;

/**
 * File: GameActivity.java
 * Author: Hillary Dworkoski
 * Last Updated: 12/9/18
 * Description: Activity for playing the game in the 3 in a row game app
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    //initialize variables
    Game newGame;
    Item[] gridArray;
    boolean[] wasClicked;
    GridView grid;
    ImageView iv;
    ImageAdapter iAdapter;
    TextView nextColor;
    int turn;
    Item color1;
    Item color2;
    Button btnNewGame;

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

        //assign object values to variables
        grid = (GridView) findViewById(R.id.gridView);
        iv = (ImageView) findViewById(R.id.img_next_color);
        btnNewGame = (Button) findViewById(R.id.startNewGame);
        nextColor = (TextView) findViewById(R.id.tv_next_color);

        //set game colors
        setColors();
        //set background color
        setBG();
        //start a new game
        startNewGame();

        //set on click listener for new game button
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startNewGame();
            }
        });

        //set on click listeners for  grid items
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
            if(!wasClicked[position]) { //if the position has not been clicked already, do the code below
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
                setNext(turn);

                //increase 'turn' variable
                turn++;

                //reset game variables
                newGame.setGridArray(gridArray);
                newGame.setWasClicked(wasClicked);
                newGame.setTurn(turn);

                //check if the game is over
                boolean gameOver = newGame.gameOver(position);

                //what to do if the game is over (3 in a row)
                if(gameOver) {
                    //tell all spaces in array that they have been clicked so the game does not continue
                    for (int i=0; i<16; i++) {
                        wasClicked[i] = true;
                    }

                    //reset was clicked
                    newGame.setWasClicked(wasClicked);

                    //message to show that the user lost the game
                    Toast toast = Toast.makeText(getApplicationContext(), "Game Over\nYou lost!", Toast.LENGTH_LONG);
                    toast.show();

                    //show new game button
                    btnNewGame.setVisibility(View.VISIBLE);

                    //hide the next color information
                    iv.setVisibility(View.GONE);
                    nextColor.setVisibility(View.GONE);
                }
            }

            //if the game has not been lost and the user has gone through 12 turns
            if(turn == 12)
            {
                //display message telling user that they won
                Toast toast = Toast.makeText(getApplicationContext(), "Game Over\nYou won!", Toast.LENGTH_LONG);
                toast.show();
            }
            }
        });
    }

    /**
     * method to change the background color and game colors when the activity is resumed from pause
     */
    @Override
    protected void onResume() {
        super.onResume();
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        setBG();
        setColors();
    }

    /**
     * method to change the 'Next Color' image after each turn
     * @param turn
     */
    public void setNext(int turn) {
        if (turn % 2 != 0)
            iv.setImageResource(color1.getColor());
        else
            iv.setImageResource(color2.getColor());
    }

    /**
     * method to set the background color from shared preferences
     */
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

    /**
     * method to set the game colors from shared preferences
     */
    public void setColors() {
        if(sharedPref.contains(COL1) && sharedPref.contains(COL2)) {
            String col1 = sharedPref.getString(COL1, "");
            String col2 = sharedPref.getString(COL2, "");

            if(col1.equals("Green"))
                color1 = new Item(R.drawable.green, "green");
            else if(col1.equals("Blue"))
                color1 = new Item(R.drawable.blue, "blue");
            else if(col1.equals("Purple"))
                color1 = new Item(R.drawable.purple, "purple");

            if(col2.equals("Red"))
                color2 = new Item(R.drawable.red, "red");
            else if(col2.equals("Orange"))
                color2 = new Item(R.drawable.orange, "orange");
            else if(col2.equals("Yellow"))
                color2 = new Item(R.drawable.yellow, "yellow");
        }
        else {
            color1 = new Item(R.drawable.green, "green");
            color2 = new Item(R.drawable.red, "red");
        }
    }

    /**
     * method to start a new game
     */
    public void startNewGame() {
        //hide new game button
        btnNewGame.setVisibility(View.GONE);

        //set next color
        setNext(1);

        //show the next color information
        iv.setVisibility(View.VISIBLE);
        nextColor.setVisibility(View.VISIBLE);

        //create a new game
        newGame = new Game(color1, color2);

        //get variables from new game object
        gridArray = newGame.getGridArray();
        wasClicked = newGame.getWasClicked();
        turn = newGame.getTurn();

        //passing the ImageAdapter a reference to the gridArray created in the new game
        iAdapter = new ImageAdapter(this, gridArray);
        grid.setAdapter(iAdapter);
    }
}