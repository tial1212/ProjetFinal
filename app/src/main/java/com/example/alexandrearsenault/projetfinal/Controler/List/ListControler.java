package com.example.alexandrearsenault.projetfinal.Controler.List;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;

import java.util.List;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class ListControler {


    private static final int ACTION_MY_MUSIC        = 1;
    private static final int ACTION_PUBLIC_MUSIC    = 2;
    private static final int ACTION_MY_PLAYLIST     = 3;
    private static final int ACTION_PUBLIC_PLAYLIST = 4;

    private static int action;
    private final DataManager dataMgr;
    private final HomeActivity activity;

    public fgrList fgrList;

    public ListControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;
        fgrList = new fgrList();
    }



    public void onAvatarAnswer(List<Avatar> pList) {
        fgrList.showListAvatar(pList);
    }
    public void onPlaylistAnswer(List<ListesDeLecture> pList) {
        fgrList.showListPlaylist(pList);
    }
    public void onSongAnswer(List<Musique> pList) {
        fgrList.showListSong(pList);
    }



    public void onDoneSelectingSong(Musique s) {
        activity.songControler.setFgr( s );
    }

    public void onDoneSelectingPlaylist(ListesDeLecture p) {
        activity.playlistControler.setFgr( p );
    }
}
