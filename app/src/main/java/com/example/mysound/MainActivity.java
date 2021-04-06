package com.example.mysound;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SoundPool mSoundPool;
    private int mSoundCollision=1;
    private int mStreamId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewSoundPool();

        mSoundPool.load(this,R.raw.collision,1);
        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(onPlayButtonClickListener);

    }
    Button.OnClickListener onPlayButtonClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            mStreamId = mSoundPool.play(mSoundCollision,1,1,1,1,1);
            Toast.makeText(getApplicationContext(), "soundPool.play()", Toast.LENGTH_LONG).show();
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) //вызов класса SoundPool.Builder
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }
}