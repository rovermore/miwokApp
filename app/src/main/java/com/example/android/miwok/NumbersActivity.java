package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class NumbersActivity extends AppCompatActivity {


    Context context = this;

    MediaPlayer mediaPlayer;

    AudioManager am;
    AudioManager.OnAudioFocusChangeListener afChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        am = (AudioManager) this.getSystemService(this.AUDIO_SERVICE);

        afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // Permanent loss of audio focus
                    // Pause playback immediately
                    mediaPlayer.pause();
                    releaseMediaPlayer();


                } else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT) {
                    // Pause playback
                    mediaPlayer.pause();
                } else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // Lower the volume, keep playing

                    mediaPlayer.pause();

                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // Your app has been granted audio focus again
                    // Raise volume to normal, restart playback if necessary
                    mediaPlayer.start();
                }
            }
        };


        final ArrayList<WordClass> words = new ArrayList<WordClass>();

        words.add(new WordClass("lutti", "One", R.drawable.number_one, R.raw.number_one));
        words.add(new WordClass("otiiko", "Two", R.drawable.number_two, R.raw.number_two));
        words.add(new WordClass("tolookosu", "Three", R.drawable.number_three, R.raw.number_three));
        words.add(new WordClass("ojete", "Four", R.drawable.number_four, R.raw.number_four));
        words.add(new WordClass("calor", "Five", R.drawable.number_five, R.raw.number_five));
        words.add(new WordClass("willy", "Six", R.drawable.number_six, R.raw.number_six));
        words.add(new WordClass("wonka", "Seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new WordClass("humpa", "Eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new WordClass("lumpa", "Nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new WordClass("patata", "Ten", R.drawable.number_ten, R.raw.number_ten));


        final WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                WordClass word = words.get(i);

                Log.v("NumbersActivity", "current word" + words.toString());

                int musicSate = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (musicSate == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioId());

                    mediaPlayer.start();
                }

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        releaseMediaPlayer();


                    }
                });
            }
        });


    }

    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();


            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            //loose audio focus as music has been stopped
            am.abandonAudioFocus(afChangeListener);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}












