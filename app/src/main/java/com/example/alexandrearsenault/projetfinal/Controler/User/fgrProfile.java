package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */
public class fgrProfile extends Fragment {



    private View view;

    private boolean editing;

     HomeActivity   activity;

     EditText       aliasEdit ;
     TextView       emailText ;
     TextView       nbPlaylistText ;
     TextView       nbSongText ;
     TextView       errorText ;

     ImageButton    btnImgAvatar;
     Button         btnEdit;
     Button         btnLogoff;

    String alias;
    int idAvatar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = ((HomeActivity)getActivity());

        if ( activity.userControler.isUserConnected()  ){
            view = inflater.inflate(R.layout.lay_profile, container, false);
            aliasEdit = (EditText)view.findViewById(R.id.lbl_profile_alias);
            emailText = (TextView)view.findViewById(R.id.txt_profile_email);
            nbPlaylistText =(TextView)view.findViewById(R.id.txt_profile_nb_playlist);
            nbSongText = (TextView)view.findViewById(R.id.txt_profile_nb_song);
            errorText = (TextView)view.findViewById(R.id.txt_profile_error);
            editing = false;
            idAvatar = activity.user.getAvatar();

            //set known info
            this.aliasEdit.setText( activity.user.getAlias() );
            this.emailText.setText(activity.user.getEMaill() );
            this.nbPlaylistText.setText( "X");
            this.nbSongText.setText( "X" );

            this.btnEdit = (Button) view.findViewById(R.id.btn_profile_edit);
            this.btnEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
                edit( !editing );
            } });
            this.btnImgAvatar = (ImageButton) view.findViewById(R.id.btnImg_profile_avatar);
            this.btnImgAvatar.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
                //select avatar
                activity.action = HomeActivity.ACT_AVATAR_LOAD;
                DataManager.getInstance().getAvatarList(0,20);

            } });

            this.btnLogoff = (Button) view.findViewById(R.id.btn_profile_logoff);
            this.btnLogoff.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
                activity.action = HomeActivity.ACT_AVATAR_LOAD;
                DataManager.getInstance().logoff( activity.user.getEMaill() );
            } });

            this.btnImgAvatar.setClickable(false);
            this.aliasEdit.setEnabled(false);


            this.btnEdit.setText( getString(R.string.lbl_profile_btn_edit ) );

            //DataManager.getInstance().getUserNbSong();
            //DataManager.getInstance().getUserNbPlaylist();

            return view ;
        }
        else {
            Log.e("fgrProfile.onCreateView","Coud NOT load fgrProfile because !isUserConnected ");
            activity.changeFragment( new fgrStart() );
            return null;
        }
    }


    /**
     * Save on false
     *
     * @param isEditing
     */
    private void edit(boolean isEditing ) {
        Log.e("fgrProfile","edit("+isEditing+")");
        editing = isEditing;

            btnImgAvatar.setClickable(editing);
            aliasEdit.setEnabled(editing);
            btnEdit.setText(   (editing? getString(R.string.lbl_profile_btn_save ) : getString(R.string.lbl_profile_btn_edit ) ) );

        if (!editing ) { //save
            alias = String.valueOf(aliasEdit.getText());
           if (validateProfile( alias ) ){
               DataManager.getInstance().modifierUser(activity.user.getEMaill() , activity.user.getPasowrd() , alias,this.idAvatar );
           }
        }
    }

    public boolean validateProfile(String pAllias) {
        boolean ok = Utilisateur.validateAlias( pAllias ) ;
        errorText.setText( (ok?"" : "Allias invalide ") );
        return ok;
    }



    public void onEditAnswer(Token pToken) {
        if (pToken != null &&  pToken.getEtat() == true ){
            Toast.makeText(activity.getApplicationContext(), "modification OK", Toast.LENGTH_SHORT).show();
            activity.user.setAvatar(idAvatar);
            activity.user.setAlias(alias);
        } else {
            Toast.makeText(activity.getApplicationContext(), "ERREUR : modification annulé : ", Toast.LENGTH_SHORT).show();
            aliasEdit.setText(activity.user.getAlias() );
        }
    }

    public void onNbPlaylistAnswer(Integer pNbPlaylist) {
        boolean ok = pNbPlaylist != null;
        this.nbPlaylistText.setText( (ok ? "?": ""+pNbPlaylist) );
    }

    public void onNbSongAnswer(Integer pNbSong) {
        boolean ok = pNbSong != null;
        this.nbSongText.setText( (ok ? "?": ""+pNbSong) );

    }


    public void onDoneSelectingAvatar(Avatar pAvatar) {

        if (pAvatar != null){
            //btnImgAvatar.setImageBitmap( pAvatar.getAvatar() ); FIXME
            this.idAvatar = pAvatar.getId();
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "Erreur sélection avatar", Toast.LENGTH_SHORT).show();
        }

    }

}
