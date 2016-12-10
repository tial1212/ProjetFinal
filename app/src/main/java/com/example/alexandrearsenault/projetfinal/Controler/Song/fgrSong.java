package com.example.alexandrearsenault.projetfinal.Controler.Song;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrSong extends Fragment {


    private HomeActivity activity;
    private Musique song;
    private View view;
    private TextView errorLbl;
    private boolean isEditing;
    private ImageView imgCoverArt;
    private Button btnEdit;
    private Switch swtPublic;
    private Switch swtActive;
    private EditText title;
    private EditText artist;
    private Boolean canEdit;


    public void setSong(Musique pSong) {
        song = pSong;
    }
    public void setCanEdit(boolean pCanEdit){
        canEdit= pCanEdit;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (song != null  && canEdit != null ){
            activity = ((HomeActivity)getActivity());
            view = inflater.inflate(R.layout.lay_song, container, false);

            imgCoverArt = (ImageView) view.findViewById(R.id.img_song_cover_art);
            //imgCoverArt.setImageBitmap(  song.getCoverArt()  );  //TODO convert img  B64->BitMap
            title = ((EditText) view.findViewById(R.id.lbl_song_title));
            title.setText(song.getTitle());
            title.setClickable(false);
            artist  = ((EditText) view.findViewById(R.id.lbl_song_artist));
            artist.setText(song.getArtist());
            artist.setClickable(false);
            swtPublic = ((Switch) view.findViewById(R.id.swt_song_public));
            swtPublic.setClickable(false);
            swtActive = ((Switch) view.findViewById(R.id.swt_song_active));
            swtActive.setClickable(false);
            btnEdit = (Button) view.findViewById(R.id.btn_song_edit);

            isEditing= false;

            if(canEdit){
                errorLbl = (TextView) view.findViewById(R.id.lbl_song_error);

                btnEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
                    isEditing = !isEditing;
                    edit();
                } });
            }else {
                btnEdit.setVisibility(LinearLayout.GONE);
            }
            return view ;
        }
        else{
            Log.e("fgrSong.onCrtView()","these MUST be set before displaying fragment --> song="+(song == null ?"UNSET":"SET" )+" , canEdit:"+(canEdit == null ?"UNSET":"SET" ) );
            return null;
        }
    }

    public void edit(){
        swtPublic.setClickable(isEditing);
        swtActive.setClickable(isEditing);
        artist.setClickable(isEditing);
        title.setClickable(isEditing);

        if (!isEditing){
            String artiste  = artist.getText().toString();
            String titre  = title.getText().toString();

            if ( validate(artiste , titre ) ){
                DataManager.getInstance().modifySong(song.getId() ,titre , artiste ,song.getCoverArt(),swtPublic.isChecked(),swtActive.isChecked());
            }
        }

    }
    private boolean validate(String pArtist , String pTitle){
        boolean okArtist = Musique.validateArtist(pArtist);
        boolean okTitle = Musique.validateTitle(pTitle);
        boolean okFinal = okArtist && okTitle;
        errorLbl.setText( (okFinal ? "" : (okArtist ?"":"artiste invalide " ) + (okTitle ?"":"titre invalide " )  ) );
        return  okFinal;
    }


    public void onSongModifyAnswer( Token pToken) {
        if (pToken == null ) {
            activity.toaster.errorRequest();
        }

        if (pToken.getEtat()) {
            activity.toaster.okModify( Musique.class.toString() );
        }
        else {
            activity.toaster.errorModify( pToken );
        }
    }

}
