package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Find the View that shows the numbers, family, colors and phrases categories
        TextView numbers = (TextView) findViewById(R.id.numbers);

        TextView family = (TextView) findViewById(R.id.family);

        TextView colors = (TextView) findViewById(R.id.colors);

        TextView phrases = (TextView) findViewById(R.id.phrases);



// Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        family.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

               Intent familyIntent= new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        colors.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent colorsIntent= new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        phrases.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent phrasesIntent= new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });

    }
}