package au.edu.holmesglen.hdworkoski.assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        setBG();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void toSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void toHS(View view) {
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }

    public void toHelp(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

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
