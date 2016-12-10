package com.example.alexandrearsenault.projetfinal.Controler.List;

import android.util.Log;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Data.ObjectConvertor;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;

import java.util.List;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class ListControler {


    public static final int ACTION_NULL            =-1;

    public static final int ACTION_AVATAR          = 1;

    public static final int ACTION_MY_PLAYLIST     = 2;
    public static final int ACTION_PUBLIC_PLAYLIST = 3;

    public static final int ACTION_SONG            = 4;


    public int action;
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
    public void onDoneSelectingAvatar(Avatar pAvatar) {
        switch (action) {
            case ACTION_AVATAR:
                activity.userControler.onDoneSelectingAvatar( pAvatar );
                action = ACTION_NULL;
                break;
            default:
                Log.e("LstCtrl", "onDoneSelectingAvatar() : action inconnue");
                break;
        }
    }


    /****************
                        SONG
                                ****************/

    public void onMySongListAnswer(List<Musique> pListSong) {
        //TODO
        if (pListSong != null){
            fgrList.setListSong(pListSong );
        }else {
            activity.toaster.message( "Impossible de charger vos chansons" );
        }
    }
    public void onPublicSongListAnswer(List<Musique> pListSong) {
        //TODO
        if (pListSong != null){
            fgrList.setListSong(pListSong  );
        }else {
            activity.toaster.message( "Impossible de charger les chansons publiques" );
        }
    }
    public void onDoneSelectingPlaylist(ListesDeLecture pPlaylist) {
        switch (action) {
            case ACTION_MY_PLAYLIST:
                activity.playlistControler.onDoneSelectingMyPlaylist( pPlaylist );
                action = ACTION_NULL;
                break;
            case ACTION_PUBLIC_PLAYLIST:
                activity.playlistControler.onDoneSelectingPublicPlaylist( pPlaylist );
                action = ACTION_NULL;
                break;
            default:
                Log.e("ListControler", "onDoneSelectingPlaylist() : action  inconnue");
                break;
        }


    }
    public void onDoneSelectingSong(Musique pSong) {
        switch (action) {
            case ACTION_SONG:
                activity.songControler.onDoneSelectingSong( pSong );
                action = ACTION_NULL;
                break;
            default:
                Log.e("ListControler", "onDoneSelectingSong() : action inconnue");
                break;
        }
    }


    /****************
                     PLAYLIST
                               ****************/

    public void onMyPlaylistListAnswer(List<ListesDeLecture> pListPlaylist) {
        if (pListPlaylist != null){
            action = ACTION_MY_PLAYLIST;
            fgrList = new fgrList();
            fgrList.setListPlaylist(pListPlaylist );
            activity.setFragment(fgrList);
        }else {
            activity.toaster.message( "Impossible de charger vos liste de lecture" );
        }
    }

    public void onPublicPlaylistListAnswer(List<ListesDeLecture> pList) {
        //TODO
    }


}
