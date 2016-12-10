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

    public fgrSong fgSong;

    public SongControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;

    }



    public void onDoneSelectingSong(Musique pSong) {
        fgSong = new fgrSong();
        fgSong.setSong(pSong);
        fgSong.setCanEdit(activity.user.getId() == pSong.getOwner());
        activity.setFragment( fgSong );
    }

    public void onCreateSongAnswer(Token pToken) {
        if (pToken == null){
            activity.toaster.errorRequest();
            return;
        }
        if (pToken.getEtat()){
            // TODO
        }
        else {

        }
    }

    public void onSongAnswer(Musique pMusique) {
        if (pMusique == null){
            activity.toaster.errorRequest();
            return;
        }
        fgSong = new fgrSong();
        fgSong.setSong(pMusique);
        fgSong.setCanEdit( pMusique.getOwner() == activity.user.getId()  );
    }
}
