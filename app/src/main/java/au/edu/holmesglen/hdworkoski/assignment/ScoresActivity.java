package au.edu.holmesglen.hdworkoski.assignment;

/**
 * File: ScoresActivity.java
 * Author: Hillary Dworkoski
 * Last Updated: 12/9/18
 * Description: Activity to display high scores of 3 in a row game
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ScoresActivity extends AppCompatActivity {

    //name of keys and file
    public static final String SETTINGS = "Settings";
    public static final String BG = "bgKey";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up preferences
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        //set background color
        setBG();
    }

    /**
     * method for when activity is resumed from pause
     */
    @Override
    protected void onResume() {
        super.onResume();
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);

        //set background color in case it was changed
        setBG();
    }

    /**
     * creates menu
     * @param menu main menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scores, menu);
        return true;
    }

    /**
     * menu options
     * @param item selected menu item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent i;

        switch(item.getItemId())
        {
            case R.id.menu_hs_main:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_hs_help:
                i = new Intent(this, HelpActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_hs_settings:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
        }
        return false;
    }

    /**
     * method to change background color
     */
    public void setBG() {
        if(sharedPref.contains(BG)) {
            String bg = sharedPref.getString(BG, "");
            if(bg.equals("White")) {
                findViewById(R.id.scores_layout).setBackgroundColor(this.getResources().getColor(R.color.White));
            }
            else if(bg.equals("Grey")) {
                findViewById(R.id.scores_layout).setBackgroundColor(this.getResources().getColor(R.color.Grey));
            }
            else if(bg.equals("Black")) {
                findViewById(R.id.scores_layout).setBackgroundColor(this.getResources().getColor(R.color.Black));
            }
        }
    }
}