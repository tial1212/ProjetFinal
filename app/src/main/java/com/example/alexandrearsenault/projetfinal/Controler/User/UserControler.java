package com.example.alexandrearsenault.projetfinal.Controler.User;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;


/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class UserControler {

    public  static final String PREFS_USER      = "SavedUserFile";
    private static final String PREFS_USER_EMAIL = "name";
    private static final String PREFS_USER_PSWD = "pswd";


    DataManager         dataMgr;
    HomeActivity        activity;


    fgrCreate           fgrCreate;
    fgrConfirmCreate    fgrConfirmCreate;
    fgrLogin            fgrLogin;
    fgrProfile          fgrProfile;
    fgrStart            fgrStart;
    SharedPreferences   settings;

    public UserControler(HomeActivity pHomeActivity, DataManager pDataMgr , SharedPreferences pSharedPref) {
        activity = pHomeActivity;
        dataMgr =  pDataMgr;
        settings = pSharedPref;
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


    public void onCreateAnswer(Token token) {

        if (token.getEtat() ){
            activity.changeFragment( new fgrConfirmCreate() );
        }
        else {

            fgrCreate.setError( token.getAction() );
        }
    }



    public void sendLogin(String pCourriel, String pMotDePasse){
        Log.e("UserCtrl","sendLogin");
        dataMgr.login(pCourriel, pMotDePasse);
    }


    public void tryConnectFromLastSession(String pCourriel, String pMotDePasse){
        Log.e("UserCtrl","sendLogin");
        dataMgr.login(pCourriel, pMotDePasse);
    }


    public void onLoginAnswer(Token pToken) {
        Log.e("UserCtrl.onLoginAnswer",pToken.toString());

        if (pToken == null ){
            Log.e("onLoginAnswer","Token null");
        }else if (activity.action == HomeActivity.ACT_CONNECT_AUTO){
            activity.action = HomeActivity.ACT_NONE;
            if ( pToken.getEtat() ){
                Log.e("onLoginAnswer","auto connect ok");
                dataMgr.getUser();
            }else{
                activity.connect(false ,  null );
            }
        }else if (activity.action == HomeActivity.ACT_CONNECT){
            activity.action = HomeActivity.ACT_NONE;
            if (pToken.getEtat() ){
                Log.e("onLoginAnswer","connect ok");
                dataMgr.getUser();
            }else{
                fgrLogin.setError( pToken.getAction() );
            }
        }
    }


    public void onUserAnswer(Utilisateur pUser) {
        Log.e("UserCtrl.onUserAnswer",pUser.toString());
        activity.action = HomeActivity.ACT_NONE ;
        if (pUser == null ){
            Log.e("onUserAnswer","Token null");
        }else{
            activity.connect(true , pUser);
            activity.changeFragment( new fgrProfile() );
        }
    }



    public void onDoneSelectingAvatar(Avatar pAvatar){
        Log.e("UserControler","onDoneSelectingAvatar");
        switch (activity.action) {
            case HomeActivity.ACT_AVATAR_CREATE:
                fgrCreate.onDoneSelectingAvatar( pAvatar);
            case HomeActivity.ACT_AVATAR_MODIFY :
                fgrProfile.onDoneSelectingAvatar( pAvatar);
                break;
        }
    }

    public void savePreferences(){
        Log.e("UserControler","savePreferences()");
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREFS_USER_EMAIL, activity.user.getEMaill() );
        editor.putString(PREFS_USER_PSWD, activity.user.getPasowrd() );
        editor.commit();

    }

    public void loadPreferences(){
        Log.e("UserControler","loadPreferences()");
        activity.user.setEMaill( settings.getString(PREFS_USER_EMAIL, "") );
        activity.user.setPasowrd( settings.getString(PREFS_USER_PSWD,  "") );

    }






}
