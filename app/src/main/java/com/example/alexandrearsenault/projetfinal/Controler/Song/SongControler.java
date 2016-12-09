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

    public fgrSong fg;

    public SongControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;

    }


    private void setFgr(Musique pSong) {
        fg = new fgrSong();
        fg.setMusic(pSong);
        activity.changeFragment( fg );
    }

    public void onDoneSelectingSong(Musique s) {
        this.setFgr( s );
    }

    public void onCreateSongAnswer(Token pToken) {
        //TODO
    }

    public void onMySongAnswer(Musique pMusique) {
        if (pMusique != null){

        }else {

        }

    }
}
