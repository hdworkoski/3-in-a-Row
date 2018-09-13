package au.edu.holmesglen.hdworkoski.assignment;

/**
 * File: SettingsActivity.java
 * Author: Hillary Dworkoski
 * Last Updated: 12/9/18
 * Description: Activity to choose settings for 3 in a row game
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    //initialize variables
    RadioGroup rgBg;
    RadioGroup rgCol1;
    RadioGroup rgCol2;
    RadioGroup rgSize;
    RadioGroup rgDifficulty;
    RadioButton rbBg1;
    RadioButton rbBg2;
    RadioButton rbBg3;
    RadioButton rbCol11;
    RadioButton rbCol12;
    RadioButton rbCol13;
    RadioButton rbCol21;
    RadioButton rbCol22;
    RadioButton rbCol23;
    RadioButton rb4;
    RadioButton rbNotime;

    //name of keys and file
    public static final String SETTINGS = "Settings";
    public static final String BG = "bgKey";
    public static final String COL1 = "col1Key";
    public static final String COL2 = "col2Key";
    SharedPreferences sharedPref;

    /**
     * method for when activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //set up preferences
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);

        //get references to radio groups and buttons
        rgBg = (RadioGroup) findViewById(R.id.rg_Bg);
        rgCol1 = (RadioGroup) findViewById(R.id.rg_Col1);
        rgCol2 = (RadioGroup) findViewById(R.id.rg_Col2);
        rgSize = (RadioGroup) findViewById(R.id.rg_Size);
        rgDifficulty = (RadioGroup) findViewById(R.id.rg_Difficulty);
        rbBg1 = (RadioButton) findViewById(R.id.rb_White);
        rbBg2 = (RadioButton) findViewById(R.id.rb_Grey);
        rbBg3 = (RadioButton) findViewById(R.id.rb_Black);
        rbCol11 = (RadioButton) findViewById(R.id.rb_Green);
        rbCol12 = (RadioButton) findViewById(R.id.rb_Blue);
        rbCol13 = (RadioButton) findViewById(R.id.rb_Purple);
        rbCol21 = (RadioButton) findViewById(R.id.rb_Red);
        rbCol22 = (RadioButton) findViewById(R.id.rb_Orange);
        rbCol23 = (RadioButton) findViewById(R.id.rb_Yellow);
        rb4 = (RadioButton) findViewById(R.id.rb_4);
        rbNotime = (RadioButton) findViewById(R.id.rb_Notime);

        //call method to load saved preferences
        setPrefs();

        //call method to set bg color
        setBG();

        //set button listener
        Button saveBtn = (Button) findViewById(R.id.btn_save_settings);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePrefs(view);
            }
        });
    }

    /**
     * creates menu
     * @param menu main menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
            case R.id.menu_settings_main:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_settings_hs:
                i = new Intent(this, ScoresActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_settings_help:
                i = new Intent(this, HelpActivity.class);
                startActivity(i);
                return true;
        }
        return false;
    }

    /**
     * method to save preferences
     * @param view view
     */
    public void savePrefs(View view) {
        int selectedIDBg = rgBg.getCheckedRadioButtonId();
        RadioButton selectedBg = (RadioButton) findViewById(selectedIDBg);
        String bg = selectedBg.getText().toString();

        int selectedIDCol1 = rgCol1.getCheckedRadioButtonId();
        RadioButton selectedCol1 = (RadioButton) findViewById(selectedIDCol1);
        String col1 = selectedCol1.getText().toString();

        int selectedIDCol2 = rgCol2.getCheckedRadioButtonId();
        RadioButton selectedCol2 = (RadioButton) findViewById(selectedIDCol2);
        String col2 = selectedCol2.getText().toString();

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(BG, bg);
        editor.commit();
        editor.putString(COL1, col1);
        editor.commit();
        editor.putString(COL2, col2);
        editor.commit();

        setBG();
    }

    /**
     * method to set preferences in activity from saved sharedPref
     */
    public void setPrefs() {
        if(sharedPref.contains(BG)) {
            String bg = sharedPref.getString(BG, "");
            String col1 = sharedPref.getString(COL1, "");
            String col2 = sharedPref.getString(COL2, "");

            if(bg.equals(rbBg1.getText().toString())) {
                rbBg1.setChecked(true);
            }
            else if(bg.equals(rbBg2.getText().toString())) {
                rbBg2.setChecked(true);
            }
            else if(bg.equals(rbBg3.getText().toString())) {
                rbBg3.setChecked(true);
            }

            if(col1.equals(rbCol11.getText().toString())) {
                rbCol11.setChecked(true);
            }
            else if(col1.equals(rbCol12.getText().toString())) {
                rbCol12.setChecked(true);
            }
            else if(col1.equals(rbCol13.getText().toString())) {
                rbCol13.setChecked(true);
            }

            if(col2.equals(rbCol21.getText().toString())) {
                rbCol21.setChecked(true);
            }
            else if(col2.equals(rbCol22.getText().toString())) {
                rbCol22.setChecked(true);
            }
            else if(col2.equals(rbCol23.getText().toString())) {
                rbCol23.setChecked(true);
            }

        }
        else {
            rbBg1.setChecked(true);
            rbCol11.setChecked(true);
            rbCol21.setChecked(true);
        }

        //set 4x4 and no timer radio buttons as selected
        rb4.setChecked(true);
        rbNotime.setChecked(true);
    }

    /**
     * method to set the background color based on the saved sharedPref
     */
    public void setBG() {
        if(sharedPref.contains(BG)) {
            String bg = sharedPref.getString(BG, "");
            if(bg.equals("White")) {
                findViewById(R.id.settings_layout).setBackgroundColor(this.getResources().getColor(R.color.White));
            }
            else if(bg.equals("Grey")) {
                findViewById(R.id.settings_layout).setBackgroundColor(this.getResources().getColor(R.color.Grey));
            }
            else if(bg.equals("Black")) {
                findViewById(R.id.settings_layout).setBackgroundColor(this.getResources().getColor(R.color.Black));
            }
        }
    }
}
