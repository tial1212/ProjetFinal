package com.example.alexandrearsenault.projetfinal.Controler.List;

import android.util.Log;
import android.widget.Toast;

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
    private fgrList fgrList;


    public ListControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;
    }


    /****************
                       AVATAR
                                ****************/

    public void onAvatarAnswer(List<Avatar> pList) {

    }


    /****************
                        SONG
                                ****************/

    public void onMySongListAnswer(List<Musique> pListSong) {
        //TODO
        if (pListSong != null){
            fgrList.setListSong(pListSong , activity);
        }else {
            Toast.makeText(activity.getApplicationContext(), "Impossible de charger vos chansons", Toast.LENGTH_SHORT).show();
        }
    }
    public void onPublicSongListAnswer(List<Musique> pListSong) {
        //TODO
        if (pListSong != null){
            fgrList.setListSong(pListSong , activity );
        }else {
            Toast.makeText(activity.getApplicationContext(), "Impossible de charger les chansons publiques", Toast.LENGTH_SHORT).show();
        }
    }

    public void onDoneSelectingPlaylist(ListesDeLecture p) {
        Log.e("HERE","HERE");
        activity.playlistControler.setFgr( p );
    }


    /****************
                     PLAYLIST
                               ****************/

    public void onMyPlaylistListAnswer(List<ListesDeLecture> pListPlaylist) {
        if (pListPlaylist != null){
            fgrList = new fgrList();
            fgrList.setListPlaylist(pListPlaylist, activity);
            activity.changeFragment(fgrList);
        }else {
            Toast.makeText(activity.getApplicationContext(), "Impossible de charger vos liste de lecture", Toast.LENGTH_SHORT).show();
        }
    }

    public void onPublicPlaylistListAnswer(List<ListesDeLecture> pList) {
        //TODO
    }


}
