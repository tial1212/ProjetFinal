package com.example.alexandrearsenault.projetfinal.Controler.Song;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrSong extends Fragment {


    private Musique song;
    private View view;


    private boolean isEditing;

    public void setMusic(Musique pSong) {
        song = pSong;
        //TODO fill fields
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        isEditing= false;


        view = inflater.inflate(R.layout.lay_playlist, container, false);

        Button bntEdit = (Button) view.findViewById(R.id.btn_song_edit);
        bntEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            if (isEditing){
                //save
            } else {
                //start edit
            }
            isEditing = !isEditing;

        } });

        return view ;
    }



    private void validate(){
        String name  = (String) ((TextView) view.findViewById(R.id.lbl_song_title)).getText();
        boolean active = ((Switch) view.findViewById(R.id.swt_song_active)).isChecked();
        boolean publiq = ((Switch) view.findViewById(R.id.swt_song_public)).isChecked();


        if ( true ){
            //send request
        } else {

        }

    }

    public void onEditingAnswer(boolean pEdit){

    }


}
