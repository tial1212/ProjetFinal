package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrProfile extends Fragment {



    private View view;


     EditText aliasEdit ;
     TextView emailText ;

     ImageButton btnImgAvatar;
     Button       btnEdit;



    public interface OnCreateListener {
        void onCreateSend(String pAlias, String pMotDePasse, String pCourriel, int pIdAvatar);
    }

    public void setError(String action) {
        // see if avatar invalid or alias invalid

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_profile, container, false);


        aliasEdit = (EditText)view.findViewById(R.id.lbl_profile_alias);
        emailText = (TextView)view.findViewById(R.id.txt_profile_email);



        btnEdit = (Button) view.findViewById(R.id.btn_profile_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {

        } });
        btnImgAvatar = (ImageButton) view.findViewById(R.id.btnImg_profile_avatar);
        btnImgAvatar.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            //select avatar
        } });

        edit(false);

        return view ;
    }


    private void edit(boolean isEditing ) {

        if (isEditing){
            btnImgAvatar.setActivated(true);
            btnImgAvatar.setActivated(isEditing);
        }
        else{
            //send for modification
        }

    }
}
