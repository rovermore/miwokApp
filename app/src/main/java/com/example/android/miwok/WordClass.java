package com.example.android.miwok;

/**
 * Created by robertomoreno on 18/4/17.
 */

public class WordClass {

    private String miwokWord;

    private String localWord;

    private int imageResourceId=NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED=-1;

    private int audioId;




    public WordClass (String miwok, String local, int audio){


        miwokWord = miwok;

        localWord = local;

        audioId = audio;
    }

    public WordClass (String miwok, String local, int iD, int audio){


        miwokWord = miwok;

        localWord = local;

        imageResourceId = iD;

        audioId = audio;

    }

    public void setLocalWord(String local) {
        this.localWord = local;
    }

    public void setMiwokWord(String miwok){

        this.miwokWord = miwok;

    }



    public String getMiwokWord(){

        return miwokWord;
    }

    public String getLocalWord(){

        return localWord;

    }

    public int getImageresourceId(){

        return imageResourceId;

    }

    //checks if the imageResourceId has been setted, if not means there is no image
    //returns false if has no image and true if has image (imageResourceId has been setted)
    public boolean hasImage(){

        return imageResourceId != NO_IMAGE_PROVIDED;

    }


    public int getAudioId(){

        return audioId;
    }

    @Override
    public String toString() {
        return "WordClass{" +
                "miwokWord='" + miwokWord + '\'' +
                ", localWord='" + localWord + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", audioId=" + audioId +
                '}';
    }
}
