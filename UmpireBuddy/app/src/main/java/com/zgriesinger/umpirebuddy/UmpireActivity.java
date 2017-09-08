package com.zgriesinger.umpirebuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

import org.w3c.dom.Text;

public class UmpireActivity extends AppCompatActivity {
    private Button mStrikeButton;
    private Button mBallButton;



    public static final String MY_PREFS_NAME = "DataFile";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void showAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if(item.getItemId() == R.id.restart) {
            resetCount();
            return true;
        } else if (item.getItemId() == R.id.about) {
            showAbout();
            return true;
        }
        return true;
    }

    public void resetCount() {
        final TextView strikeCount = (TextView) findViewById(R.id.strike_counter);
        final TextView ballCount = (TextView) findViewById(R.id.ball_counter);

        ballCount.setText("0");
        strikeCount.setText("0");

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("balls", "0");
        editor.putString("strikes", "0");
        editor.apply();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Basic setup to initialize the app, as well as global TextViews to update the text from
        // the anonymous inner classes
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umpire);
        final TextView strikeCount = (TextView) findViewById(R.id.strike_counter);
        final TextView ballCount = (TextView) findViewById(R.id.ball_counter);
        final TextView outCount = (TextView) findViewById(R.id.out_counter);
        SharedPreferences countData = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String outs = countData.getString("outs", "0");
        outCount.setText(outs);
        String balls = countData.getString("balls", "0");
        ballCount.setText(balls);
        String strikes = countData.getString("strikes", "0");
        strikeCount.setText(strikes);





        mBallButton = (Button) findViewById(R.id.ball_btn);
        mBallButton.setOnClickListener(new View.OnClickListener() {
            // Simply an anon inner class to handle the only function we need, onClick
            @Override
            public void onClick(View v) {
                // Parse the data in the textView as int to increment
                int ballInt = Integer.parseInt((String) ballCount.getText());
                if(ballInt != 3){
                    //If less than 3 increment
                    ballInt++;
                    String newCount = Integer.toString(ballInt);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("balls", newCount);
                    editor.apply();
                    ballCount.setText(newCount);
                } else {
                    //Else toast the message
                    Toast.makeText(UmpireActivity.this, R.string.walk_toast, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    SharedPreferences audio = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    Boolean TTS = audio.getBoolean("audioOpt", false);
                    if(TTS){

                    }
                    editor.putString("balls", "0");
                    editor.putString("outs", "0");
                    editor.apply();
                    ballCount.setText("0");
                    strikeCount.setText("0");
                }
            }
        });

        mStrikeButton = (Button) findViewById(R.id.strike_btn);
        mStrikeButton.setOnClickListener(new View.OnClickListener() {
            //Identical code just for the strike button because we have to check for less than 2
            @Override
            public void onClick(View v) {
                int strikeInt = Integer.parseInt((String) strikeCount.getText());
                if(strikeInt != 2){
                    strikeInt++;
                    String newCount = Integer.toString(strikeInt);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("strikes", newCount);
                    editor.apply();
                    strikeCount.setText(newCount);
                } else {
                    int outInt = Integer.parseInt((String) outCount.getText());
                    outInt++;
                    String newOuts = Integer.toString(outInt);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    SharedPreferences audio = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    Boolean TTS = audio.getBoolean("audioOpt", false);
                    if(TTS){

                    }
                    editor.putString("outs", newOuts);
                    editor.putString("balls", "0");
                    editor.putString("strikes", "0");
                    editor.apply();
                    outCount.setText(newOuts);
                    Toast.makeText(UmpireActivity.this, R.string.strikeout_toast, Toast.LENGTH_SHORT).show();
                    strikeCount.setText("0");
                    ballCount.setText("0");
                }

            }
        });


    }

}
