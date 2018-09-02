package au.edu.holmesglen.hdworkoski.assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    //name of keys and file
    public static final String SETTINGS = "Settings";
    public static final String BG = "bgKey";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
