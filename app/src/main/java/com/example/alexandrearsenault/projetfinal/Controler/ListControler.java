package com.example.alexandrearsenault.projetfinal.Controler;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;

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
        action = pAction;
    }


    public static ListControler getInstance(int pAction) {
        if (instance == null) {
            //instance = new ListControler(pAction);
        } else {
            action = pAction; //TODO not safe
        }
        return instance;
    }




    private void onElementSelected(int pAction) {
        if (action == 1 || action == 2){

        }
        else if (action == 3 || action == 4){

        }
    }





}
