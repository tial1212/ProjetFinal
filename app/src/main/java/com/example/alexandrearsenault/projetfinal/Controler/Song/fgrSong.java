package com.example.alexandrearsenault.projetfinal.Controler.Song;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrStart;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrSong extends Fragment {

    HomeActivity activity;

    private Musique song;
    private View view;
    TextView errorLbl;


    private boolean isEditing;

    public void setMusic(Musique pSong) {
        song = pSong;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = ((HomeActivity)getActivity());


        if (song != null){

        }
        else {
            Log.e("fgrProfile.onCreateView","Coud NOT load fgrProfile because !isUserConnected ");
            activity.changeFragment( new fgrStart() );
            return null;
        }
        view = inflater.inflate(R.layout.lay_song, container, false);
        isEditing= false;

        Button bntEdit = (Button) view.findViewById(R.id.btn_song_edit);

        errorLbl = (TextView) view.findViewById(R.id.lbl_song_error);
        bntEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            if (isEditing){
                String artist  = (String) ((TextView) view.findViewById(R.id.lbl_song_artist)).getText();
                String titre  = (String) ((TextView) view.findViewById(R.id.lbl_song_title)).getText();
                boolean publiqe = ((Switch) view.findViewById(R.id.swt_song_public)).isChecked();
                boolean active = ((Switch) view.findViewById(R.id.swt_song_active)).isChecked();

                if ( validate( artist , titre ) ){
                    DataManager.getInstance().modifySong(song.getId() ,titre , artist ,song.getCoverArt(),publiqe,active);
                }
                //disenable shit
            } else {
                //disenable shit
            }
            isEditing = !isEditing;

        }



        });

        return view ;
    }

    private boolean validate(String pArtist , String pTitle){
        boolean okArtist = Musique.validateArtist(pArtist);
        boolean okTitle = Musique.validateTitle(pTitle);
        boolean okFinal = okArtist && okTitle;
        errorLbl.setText( (okFinal ? "" : (okArtist ?"":"artiste invalide " ) + (okTitle ?"":"titre invalide " )  ) );
        errorLbl.setTextColor(  ( okFinal ? Color.BLACK : Color.RED) );
        return  okFinal;
    }




    public void onSongModifyAnswer( Token token) {
        if (token != null && token.getEtat() ){

        }
        else{

        }

    }



    public void onEditingAnswer(boolean pEdit){

    }


}
