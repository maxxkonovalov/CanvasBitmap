package ru.syomka.canvasbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener {

    MyButton black1, black2;
    Ring2 ring1, ring2;

    SoundPool sp;
    int soundIdfrog;
    int streamIDfrog;

    //

    SeekBar seekBar;
    MediaPlayer mp;
    AudioManager am;


    //



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*    //
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (mp!=null) {mp.release();}
        mp = MediaPlayer.create(this,R.raw.frog);
        mp.setLooping(true);

    //*/


        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(MainActivity.this);

        //sp.load(this, R.raw.frog,1);
        soundIdfrog = sp.load(this, R.raw.frog, 1);
        //Log.d(LOG_TAG, "soundIdShot = " + soundIdShot);

        try {
            soundIdfrog = sp.load(getAssets().openFd("frog.ogg"), 1);
            //sp.load(getAssets().openFd("frog.ogg"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d(LOG_TAG, "soundIdExplosion = " + soundIdExplosion);




    black1 = (MyButton)findViewById(R.id.black1);
    black2 = (MyButton)findViewById(R.id.black2);
    ring1 = (Ring2) findViewById(R.id.ring1);
    ring2 = (Ring2) findViewById(R.id.ring2);

    black1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            float ang = ring1.getNewAngle();
            if (ang == 360)
            {ring1.setNewAngle(20);}
            else
            ring1.setNewAngle(ang+20);

            Toast.makeText(MainActivity.this, "ang+20 = " + ang+20, Toast.LENGTH_SHORT).show();
            ring1.invalidate();
            sp.play(soundIdfrog, 1, 1, 0, 0, 1);
        }
    });


        black2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(MainActivity.this, R.raw.sova);
                mp.setLooping(true);

                if (mp.isPlaying() &&  mp != null){
                    Toast.makeText(MainActivity.this, "is playing", Toast.LENGTH_SHORT).show();
                } else {



                    seekBar = (SeekBar) findViewById(R.id.seekBar);
                    seekBar.setMax(mp.getDuration());

                    mp.setLooping(false);
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                            startPlayProgressUpdater();
                        }
                    });
                    //
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });

                }





                float ang = ring2.getNewAngle();
                if (ang == 360)
                {ring2.setNewAngle(20);}
                else
                    ring2.setNewAngle(ang+20);

                Toast.makeText(MainActivity.this, "ang+20 = " + ang+20, Toast.LENGTH_SHORT).show();
                ring2.invalidate();
            }
        });

    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {

    }







/*    private void initViews() {
        //buttonPlayStop = (Button) findViewById(R.id.ButtonPlayStop);
        mp = MediaPlayer.create(this, R.raw.sova);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(mp.getDuration());
        *//*seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });*//*
    }*/


    private final Handler handler = new Handler();
    public void startPlayProgressUpdater() {
        if (mp != null) {
            seekBar.setProgress(mp.getCurrentPosition());
            Log.d("123", "!= null");


            if (mp.isPlaying()) {
                Runnable notification = new Runnable() {
                    public void run() {
                        startPlayProgressUpdater();
                    }
                };
                handler.postDelayed(notification, 1000);
            } else {
                mp.pause();
                //buttonPlayStop.setText(getString(R.string.play_str));
                seekBar.setProgress(0);
            }
        }
    }

}
