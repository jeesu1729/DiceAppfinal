package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    SoundPool soundpool;
    int soundid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundpool = new SoundPool.Builder()
                    .setMaxStreams(NR_OF_SIMULTANEOUS_SOUNDS)
                    .build();
        } else {

            soundpool = new SoundPool(NR_OF_SIMULTANEOUS_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        }
        soundid = soundpool.load(getApplicationContext(),R.raw.finalroll, 1);
        Button rollButton;
        rollButton = (Button) findViewById(R.id.rollButton);

        final ImageView leftDice= (ImageView) findViewById(R.id.image_leftDice);
        final ImageView rightDice= (ImageView) findViewById(R.id.image_rightDice);
        final int[] diceimages = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Dice","the botton");

                Random randomno1=new Random();
                int  no1=randomno1.nextInt(6);

                Random randomno2=new Random();
                int  no2=randomno2.nextInt(6);

                leftDice.setImageResource(diceimages[no1]);
                rightDice.setImageResource(diceimages[no2]);
                soundpool.play(soundid, LEFT_VOLUME, RIGHT_VOLUME, 0, NO_LOOP, NORMAL_PLAY_RATE);


                if(no1==no2){
                    openactivity2();
                }

            }
        });
    }
    public void openactivity2 (){
        Intent intent = new Intent( this , mainactivity2.class);
        startActivity(intent);
    }
}
