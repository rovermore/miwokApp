package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);


        final ArrayList<WordClass> words = new ArrayList<WordClass>();

        words.add(new WordClass("lutti","father",R.drawable.family_father,R.raw.family_father));
        words.add(new WordClass("otiiko","mother",R.drawable.family_mother,R.raw.family_mother));
        words.add(new WordClass("tolookosu","son",R.drawable.family_son, R.raw.family_son));
        words.add(new WordClass("ojete","daughter",R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new WordClass("calor","older brother",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new WordClass("willy","younger brother",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new WordClass("wonka","older sister",R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new WordClass("humpa","younger sister",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new WordClass("lumpa","grandmother",R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new WordClass("patata","grandfather",R.drawable.family_grandfather, R.raw.family_grandfather));




        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                WordClass word = words.get(i);

                mediaPlayer= MediaPlayer.create(FamilyActivity.this,word.getAudioId());

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
