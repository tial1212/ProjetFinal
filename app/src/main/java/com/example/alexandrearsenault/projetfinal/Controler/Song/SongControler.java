package com.example.alexandrearsenault.projetfinal.Controler.Song;

import android.util.Log;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class SongControler {

    private final DataManager dataMgr;
    private final HomeActivity activity;

    public SongControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;

    }


    public void setFgr(Musique pSong) {
        Log.e("SongControler","setFgr");
        fgrSong fg = new fgrSong();
        fg.setMusic(pSong);
        activity.changeFragment( fg );
    }



    public void sendModifySong( int pIdSong, String pTitle, String pArtist, String pCoverArt, boolean pIsPublic, boolean pIsActive) {
        Token t = dataMgr.getActionToken(activity.email);
        dataMgr.modifySong(t.getId() ,t.getSalt() ,pIdSong,pTitle,pTitle,pCoverArt,pIsPublic,pIsActive);
    }

    public void onSongModifyAnswer( Token token) {

    }


    public void onDoneSelectingSong(Musique s) {
        //TODO
    }
}
