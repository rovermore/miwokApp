package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);


        final ArrayList<WordClass> words = new ArrayList<WordClass>();

        words.add(new WordClass("lutti","One",R.raw.phrase_are_you_coming));
        words.add(new WordClass("otiiko","Two",R.raw.phrase_what_is_your_name));
        words.add(new WordClass("tolookosu","Three",R.raw.phrase_my_name_is));
        words.add(new WordClass("ojete","Four",R.raw.phrase_how_are_you_feeling));
        words.add(new WordClass("calor","Five",R.raw.phrase_lets_go));
        words.add(new WordClass("willy","Six",R.raw.phrase_where_are_you_going));
        words.add(new WordClass("wonka","Seven",R.raw.phrase_yes_im_coming));
        words.add(new WordClass("humpa","Eight",R.raw.phrase_come_here));
        words.add(new WordClass("lumpa","Nine",R.raw.phrase_come_here));
        words.add(new WordClass("patata","Ten",R.raw.phrase_im_feeling_good));




        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                WordClass word = words.get(i);

                mediaPlayer= MediaPlayer.create(PhrasesActivity.this,word.getAudioId());

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
    private void releaseMediaPlayer(){
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
