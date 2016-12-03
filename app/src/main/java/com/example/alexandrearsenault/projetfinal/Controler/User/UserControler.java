package com.example.alexandrearsenault.projetfinal.Controler.User;


import android.app.Fragment;
import android.util.Log;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Token;

/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class UserControler
        implements
            fgrCreate.OnCreateListener,
            fgrLogin.OnLoginListener{


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


    @Override
    public void onCreateSend(String pAlias , String pMotDePasse , String pCourriel , int pIdAvatar){
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

    @Override
    public void onLoginSend(String pEMail, String pMotDePasse) {
        dataMgr.login(pEMail,pMotDePasse);
    }



    public void onLoginAnswer(Token token) {
        Log.e("onLoginAnswer",token.toString());
        if (token == null ){
            Log.e("onLoginAnswer","Token null");
        } else if (token.getEtat() ){
            activity.isUserConnected = true;
            activity.changeFragment( new fgrProfile() );
        }
        else {
            fgrLogin.setError( token.getAction() );
        }
    }


}
