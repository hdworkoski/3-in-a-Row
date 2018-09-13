package au.edu.holmesglen.hdworkoski.assignment;

/**
 * File: MainActivity.java
 * Author: Hillary Dworkoski
 * Last Updated: 12/9/18
 * Description: Home Screen activity for 3 in a row game app
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //name of keys and file
    public static final String SETTINGS = "Settings";
    public static final String BG = "bgKey";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up preferences
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        //set background color
        setBG();
    }

    /**
     * onResume method for when the activity is resumed from a pause
     */
    @Override
    protected void onResume() {
        super.onResume();
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);

        //set the background color again in case it was changed
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.menu_main_hs:
                i = new Intent(this, ScoresActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_main_help:
                i = new Intent(this, HelpActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_main_settings:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
        }
        return false;
    }

    /**
     * method for changing activities to start a new game
     * @param view view
     */
    public void newGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    /**
     * method for changing activities to go to settings
     * @param view view
     */
    public void toSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * method for changing activities to go to High Scores page
     * @param view view
     */
    public void toHS(View view) {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }

    /**
     * method for changing activities to go to the Help page
     * @param view view
     */
    public void toHelp(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    /**
     * method to set the background color
     */
    public void setBG() {
        if(sharedPref.contains(BG)) {
            String bg = sharedPref.getString(BG, "");
            if(bg.equals("White")) {
                findViewById(R.id.main_layout).setBackgroundColor(this.getResources().getColor(R.color.White));
            }
            else if(bg.equals("Grey")) {
                findViewById(R.id.main_layout).setBackgroundColor(this.getResources().getColor(R.color.Grey));
            }
            else if(bg.equals("Black")) {
                findViewById(R.id.main_layout).setBackgroundColor(this.getResources().getColor(R.color.Black));
            }
        }
    }
}
