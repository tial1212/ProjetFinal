package com.example.alexandrearsenault.projetfinal.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.R;

import static com.example.alexandrearsenault.projetfinal.R.id.nav_alias;
import static com.example.alexandrearsenault.projetfinal.R.id.txt_avatar_content_name;

/**
 * Created by alexandrearsenault on 2016-12-06.
 */

public class Drawer {

    private final ImageView imgAvatar;
    private final TextView txtAlias;
    private final TextView txtEmail;
    private final LinearLayout layout;

    public Drawer(LinearLayout pLayout ){
        layout = pLayout;
         imgAvatar  = (ImageView)layout.findViewById(R.id.nav_avatar);
         txtAlias  = (TextView)layout.findViewById(R.id.nav_alias);
         txtEmail  = (TextView)layout.findViewById(R.id.nav_email);
    }


    public void setInfo(String pAlias , String pEmail){
        txtAlias.setText(pAlias);
        txtEmail.setText(pEmail);
    }

    public void setAvatar(Bitmap pImage){
        imgAvatar.setImageBitmap(pImage);
    }

    public void setDefaultInfo(){
        txtAlias.setText("Votre profile");
        txtEmail.setText("cliquer p@ur connection");
    }

}
