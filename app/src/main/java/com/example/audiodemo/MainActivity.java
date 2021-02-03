package com.example.audiodemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    AudioManager audioManager;

    public void play(View view){
        mediaPlayer=MediaPlayer.create(this,R.raw.airhorn);

        mediaPlayer.start();
    }

    public void pause(View view){
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);

        mediaPlayer=MediaPlayer.create(this,R.raw.airhorn);

        SeekBar volumeControl=findViewById(R.id.volumeSeekBar);

        final int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int currentVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeControl.setMax(maxVolume);

        volumeControl.setProgress(currentVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar audioSeekBar=(SeekBar) findViewById(R.id.audioSeekBar);

        audioSeekBar.setMax(mediaPlayer.getDuration());

        audioSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                audioSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,500);
    }


}