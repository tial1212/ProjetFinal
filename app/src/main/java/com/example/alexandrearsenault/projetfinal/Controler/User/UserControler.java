package com.example.alexandrearsenault.projetfinal.Controler.User;


import android.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.Token;

import static android.R.attr.id;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class UserControler  {


    DataManager         dataMgr;
    HomeActivity        activity;


    fgrCreate           fgrCreate;
    fgrConfirmCreate    fgrConfirmCreate;
    fgrLogin            fgrLogin;
    fgrProfile          fgrProfile;
    fgrStart            fgrStart;

    public UserControler(HomeActivity pHomeActivity, DataManager pDataMgr) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;

    }


    public void setFgr(Fragment pFragment) {
        Log.e("UserControler","setFgr");
        if (pFragment instanceof fgrCreate){
            fgrCreate = (fgrCreate) pFragment;
            activity.changeFragment( pFragment );
        }
        if (pFragment instanceof fgrConfirmCreate){
            fgrConfirmCreate = (fgrConfirmCreate) pFragment;
            activity.changeFragment( pFragment );
        }
        if (pFragment instanceof fgrLogin){
            fgrLogin = (fgrLogin) pFragment;
            activity.changeFragment( pFragment );
        }
        if (pFragment instanceof fgrStart){
            fgrStart = (fgrStart) pFragment;
            activity.changeFragment( pFragment );
        }
        if (pFragment instanceof fgrProfile){
            fgrProfile = (fgrProfile) pFragment;
            activity.changeFragment( pFragment );
        }

    }


    public void sendCreateUser(String pAlias ,String pMotDePasse ,String pCourriel ,int pIdAvatar){
        dataMgr.createUser( pAlias , pMotDePasse , pCourriel , pIdAvatar);
    }

    public void onCreateAnswer(Token token) {
        if (token.getEtat() ){
            activity.changeFragment( new fgrConfirmCreate() );
        }
        else {
            fgrCreate.setError( token.getAction() );
        }
    }



    public void sendLogin(String pCourriel, String pMotDePasse){
        Log.e("UserControler","sendLogin");
        dataMgr.login(pCourriel, pMotDePasse);
    }


    public void onLoginAnswer(Token token) {
        Log.e("onLoginAnswer",token.toString());

        if (token == null ){
            Log.e("onLoginAnswer","Token null");
        }else if (activity.action == activity.ACT_CONNECT_INIT){
            if (token.getEtat() ){
                activity.changeFragment( new fgrProfile() );
            }else{
                activity.email=null;
                activity.pswd=null;
                activity.changeFragment( new fgrStart() );
                activity.action = activity.ACT_START;
            }


        }else if (activity.action == activity.ACT_CONNECT){
            if (token.getEtat() ){
                activity.changeFragment( new fgrProfile() );
            }else{
                fgrLogin.setError( token.getAction() );
            }
        }
    }


    public void onDoneSelectingAvatar(Avatar pAvatar){
        Log.e("UserControler","onDoneSelectingAvatar");
        switch (activity.action) {
            case activity.ACT_AVATAR_CREATE :
                fgrCreate.
            case activity.ACT_AVATAR_MODIFY :
                id = R.layout.lay_avatar_content;
                break;
        }
    }


}
