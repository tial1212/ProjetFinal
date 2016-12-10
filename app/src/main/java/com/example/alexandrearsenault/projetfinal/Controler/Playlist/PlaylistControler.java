package com.example.alexandrearsenault.projetfinal.Controler.Playlist;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.Song.fgrSong;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrConfirmCreate;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrProfile;
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
    public fgrPlaylist fgPlaylist;

    public PlaylistControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;
    }




    public void onGetSongListForPlaylistAnswer(List<Musique> pListSong) {
        if (pListSong == null){
            activity.toaster.message("Impossible de charger les chansons de la Liste de lecture" );
            Log.e("PlaylistControler", "onGetSongListForPlaylistAnswer() : action  inconnue");
        }
        fgPlaylist.setListSong(pListSong);
        this.setFragment( fgPlaylist );
    }


    public void onDoneSelectingMyPlaylist(ListesDeLecture pPlaylist) {
        fgPlaylist = new fgrPlaylist();
        fgPlaylist.setPlaylist(pPlaylist);
        fgPlaylist.setCanEdit(true);
        dataMgr.getSongListForPlaylist( pPlaylist.getId() );
    }
    public void onDoneSelectingPublicPlaylist(ListesDeLecture pPlaylist) {
        fgPlaylist = new fgrPlaylist();
        fgPlaylist.setPlaylist(pPlaylist);
        fgPlaylist.setCanEdit(false);
        dataMgr.getSongListForPlaylist( pPlaylist.getId() );
    }


    public void setFragment(Fragment pFragment) {
        Log.e("UserControler","setFragment()"+pFragment.getClass().toString() );
        if (pFragment instanceof fgrPlaylist){
            fgPlaylist = (fgrPlaylist) pFragment;
            activity.setFragment(pFragment);
        }
        else{
            Log.e("UserControler.setFgr()","Fragment type invalid");
        }
    }
}
