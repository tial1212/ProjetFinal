package com.example.alexandrearsenault.projetfinal.Controler.Playlist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrPlaylist extends Fragment {


    private ListesDeLecture playlist;
    private View view;

    private boolean isEditing;

    public void setPlaylist(ListesDeLecture pPlaylist) {
        playlist = pPlaylist;
        //TODO fill fields
    }

    public void setEditOnOff(boolean pEdit){

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



    private void validateBeforeSend(){
        String name  = (String) ((TextView) view.findViewById(R.id.lbl_song_title)).getText();
        boolean active = ((Switch) view.findViewById(R.id.swt_song_active)).isChecked();
        boolean publiq = ((Switch) view.findViewById(R.id.swt_song_public)).isChecked();


        if ( true ){
            //send request
        } else {
            //error message
        }

    }

}
