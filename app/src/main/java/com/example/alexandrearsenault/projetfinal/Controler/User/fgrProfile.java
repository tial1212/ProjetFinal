package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrProfile extends Fragment {


    private boolean isEditing;

    private View view;


     EditText avatarEdit ;
     ImageButton btnImgAvatar;



    public interface OnCreateListener {
        void onCreateSend(String pAlias, String pMotDePasse, String pCourriel, int pIdAvatar);
    }

    public void setError(String action) {
        // see if avatar invalid or alias invalid

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_profile, container, false);
        avatarEdit = (EditText)view.findViewById(R.id.lbl_profile_alias);
        btnImgAvatar = (ImageButton) view.findViewById(R.id.img_profile_avatar);

        isEditing = false;


        btnImgAvatar.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {

        } });


        Button bntEdit = (Button) view.findViewById(R.id.btn_profile_edit);
        bntEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            isEditing = !isEditing;

            btnImgAvatar.setActivated(isEditing);
            avatarEdit.setActivated(isEditing);


        } });

        return view ;
    }


    private void activate() {
        btnImgAvatar.setActivated(isEditing);
        avatarEdit.setActivated(isEditing);
    }
}
