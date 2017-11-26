package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by robertomoreno on 18/4/17.
 */

public class WordAdapter extends ArrayAdapter<WordClass> {




    private int fColor;

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    public WordAdapter(Activity context, ArrayList<WordClass> words,int colorBK) {
        super(context, 0, words);

        fColor=colorBK;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        WordClass currentWordClass = getItem(position);

        TextView miwokView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokView.setText(currentWordClass.getMiwokWord());


        TextView localView = (TextView) listItemView.findViewById(R.id.local_text_view);
        localView.setText(currentWordClass.getLocalWord());


        ImageView vImage = (ImageView) listItemView.findViewById(R.id.miwok_image_view);

        //If the object Wordclass has image displays the ImageView
        //Else sets the InmageView visibility to gone
        if (currentWordClass.hasImage()) {

            vImage.setImageResource(currentWordClass.getImageresourceId());

        } else {
            vImage.setVisibility(View.GONE);

        }
            return listItemView;



    }

}
