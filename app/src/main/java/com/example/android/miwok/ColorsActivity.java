package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {


    MediaPlayer mediaPlayer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.words_list);


            final ArrayList<WordClass> words = new ArrayList<WordClass>();

            words.add(new WordClass("lutti","red",R.drawable.color_red,R.raw.color_red));
            words.add(new WordClass("otiiko","green",R.drawable.color_green,R.raw.color_green));
            words.add(new WordClass("tolookosu","brown",R.drawable.color_brown,R.raw.color_brown));
            words.add(new WordClass("ojete","gray",R.drawable.color_gray,R.raw.color_gray));
            words.add(new WordClass("calor","black",R.drawable.color_black, R.raw.color_black));
            words.add(new WordClass("willy","white",R.drawable.color_white, R.raw.color_white));
            words.add(new WordClass("wonka","dusty yellow",R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
            words.add(new WordClass("humpa","mustard yellow",R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));





            WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_colors);

            ListView listView = (ListView) findViewById(R.id.list);

            listView.setAdapter(itemsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    WordClass word = words.get(i);

                    mediaPlayer= MediaPlayer.create(ColorsActivity.this,word.getAudioId());

                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {

                            releaseMediaPlayer();

                        }
                    });
                }
            });

            Log.v("NumbersActivity", "The array words[0] contains the data: " + words.get(0));
            Log.v("NumbersActivity", "The array words[5] contains the data: " + words.get(5));


            //    LinearLayout myRootView = (LinearLayout) findViewById(R.id.myRootView);



//        for (int index = 0;index < words.size(); index++) {
//
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(index));
//            myRootView.addView(wordView);
//
//
//
//        }


        }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    }

