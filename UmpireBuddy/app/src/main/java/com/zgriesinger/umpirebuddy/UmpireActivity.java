package com.zgriesinger.umpirebuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UmpireActivity extends AppCompatActivity {
    private Button mStrikeButton;
    private Button mBallButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Basic setup to initialize the app, as well as global TextViews to update the text from
        // the anonymous inner classes
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umpire);
        final TextView strikeCount = (TextView) findViewById(R.id.strike_counter);
        final TextView ballCount = (TextView) findViewById(R.id.ball_counter);

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
                    ballCount.setText(newCount);
                } else {
                    //Else toast the message
                    Toast.makeText(UmpireActivity.this, R.string.walk_toast, Toast.LENGTH_SHORT).show();
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
                    strikeCount.setText(newCount);
                } else {
                    Toast.makeText(UmpireActivity.this, R.string.strikeout_toast, Toast.LENGTH_SHORT).show();
                    strikeCount.setText("0");
                    ballCount.setText("0");
                }

            }
        });


    }
}
