package com.example.alexandrearsenault.projetfinal.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.R;

import static android.R.attr.id;
import static com.example.alexandrearsenault.projetfinal.R.id.nav_add_playlist;
import static com.example.alexandrearsenault.projetfinal.R.id.nav_add_song;
import static com.example.alexandrearsenault.projetfinal.R.id.nav_alias;
import static com.example.alexandrearsenault.projetfinal.R.id.nav_my_playlist;
import static com.example.alexandrearsenault.projetfinal.R.id.nav_my_song;
import static com.example.alexandrearsenault.projetfinal.R.id.txt_avatar_content_name;

/**
 * Created by alexandrearsenault on 2016-12-06.
 */

public class Drawer {

    private final ImageView imgAvatar;
    private final TextView txtAlias;
    private final TextView txtEmail;
    private final LinearLayout layout;
    private final Menu menu;
    private final MenuItem itemMySong;
    private final MenuItem itemMyPlaylist;
    private final MenuItem itemAddSong;
    private final MenuItem itemAddPlaylist;
    private final Context context;

    public Drawer(LinearLayout pLayout , Menu pMenu , Context pContext){
        layout = pLayout;
        menu = pMenu;
        context = pContext;
         imgAvatar  = (ImageView)layout.findViewById(R.id.nav_avatar);
         txtAlias  = (TextView)layout.findViewById(R.id.nav_alias);
         txtEmail  = (TextView)layout.findViewById(R.id.nav_email);

        itemMySong = menu.findItem(nav_my_song);
        itemMyPlaylist = menu.findItem(nav_my_playlist);
        itemAddSong = menu.findItem(nav_add_song);
        itemAddPlaylist = menu.findItem(nav_add_playlist);
    }


    public void setInfo(String pAlias , String pEmail){
        txtAlias.setText(pAlias);
        txtEmail.setText(pEmail);
    }

    public void setAvatar(Bitmap pImage){
        //imgAvatar.setImageBitmap(pImage); TODO remove
    }


    public void setDefaultInfo(){
        txtAlias.setText("Votre profile");
        txtEmail.setText("cliquer p@ur connection");
        //imgAvatar.setImageDrawable( context.getResources().getDrawable(R.drawable.ic_menu_default_avatar ) ); FIXME
    }

    public void showPrivateOption(boolean pShow){
        itemMySong.setVisible(pShow);
        itemMyPlaylist.setVisible(pShow);
        itemAddSong.setVisible(pShow);
        itemAddPlaylist.setVisible(pShow);
    }




}
