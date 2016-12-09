package com.example.alexandrearsenault.projetfinal.Controler.Playlist;

import android.util.Log;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.Song.fgrSong;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrConfirmCreate;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;

import java.util.List;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class PlaylistControler {


    private final DataManager dataMgr;
    private final HomeActivity activity;
    private fgrSong fgSong;
    private fgrPlaylist fgPlaylist;

    public PlaylistControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;
    }


    public void setFgr(ListesDeLecture pPlaylist) {
        fgPlaylist = new fgrPlaylist();
        fgPlaylist.setPlaylist(pPlaylist);
        dataMgr.getSongListForPlaylist(pPlaylist.getId());
    }


    public void onModifyPlaylistAnswer(Token token) {
        if (token.getEtat() ){

        }
        else {

        }
    }

    public void onGetSongListForPlaylistAnswer(List<Musique> pListSong) {
        if (pListSong != null){
            fgPlaylist.setListSong(pListSong);
            activity.changeFragment( fgPlaylist );

        }else {
            Toast.makeText(activity.getApplicationContext(), "Impossible de charger les chansons de la Liste de lecture", Toast.LENGTH_SHORT).show();
        }
    }


    public void onDoneSelectingSong(Musique pSong) {
        fgSong = new fgrSong();
        fgSong.setMusic(pSong);
        activity.changeFragment( fgSong );
    }
}
