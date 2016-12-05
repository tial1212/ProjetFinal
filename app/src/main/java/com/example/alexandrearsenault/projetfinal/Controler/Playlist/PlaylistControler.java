package com.example.alexandrearsenault.projetfinal.Controler.Playlist;

import android.util.Log;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrConfirmCreate;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class PlaylistControler {


    private final DataManager dataMgr;
    private final HomeActivity activity;

    public PlaylistControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;
    }


    public void setFgr(ListesDeLecture pPlaylist) {
        fgrPlaylist fg = new fgrPlaylist();
        fg.setPlaylist(pPlaylist);
        activity.changeFragment( fg );
    }


    public void sendModifyPlaylist(int	pIdPlaylist ,String pName ,boolean pIsPublic ,boolean pIsActive ) {
        if ( dataMgr.askToExecuteAction() ){
            dataMgr.modifyPlaylist(activity.actionToken.getId(), activity.pswd ,  pIdPlaylist , pName , pIsPublic , pIsActive ); //TODO fix internal method
        }

    }

    public void onModifyPlaylistAnswer(Token token) {
        if (token.getEtat() ){

        }
        else {

        }
    }

}
