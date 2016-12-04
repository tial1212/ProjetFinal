package com.example.alexandrearsenault.projetfinal.Controler.List;

import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class ListControler {

    private static ListControler instance = null;


    private static final int ACTION_MY_MUSIC        = 1;
    private static final int ACTION_PUBLIC_MUSIC    = 2;
    private static final int ACTION_MY_PLAYLIST     = 3;
    private static final int ACTION_PUBLIC_PLAYLIST = 4;

    private static int action;

    public ListControler(int pAction) {
        if (pAction < 1 || pAction > 4){  throw new RuntimeException("ListControler cannot be initiated w/ ation="+pAction);  }
        action = pAction;
    }


    public void onDoneSelectingSong(ListesDeLecture p) {
        //TODO
    }

    public void onDoneSelectingPlaylist(ListesDeLecture p) {
        //TODO
    }

    public void onDoneSelectingPlaylist(ListesDeLecture p) {
        //TODO
    }
}
