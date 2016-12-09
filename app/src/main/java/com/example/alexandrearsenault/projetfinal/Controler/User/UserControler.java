package com.example.alexandrearsenault.projetfinal.Controler.User;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.List.fgrList;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;

import java.util.List;


/**
 * Created by alexandrearsenault on 2016-12-01.
 */

public class UserControler {

    public  static final String PREFS_USER      = "SavedUserFile";
    private static final String PREFS_USER_EMAIL = "name";
    private static final String PREFS_USER_PSWD = "pswd";

    private boolean isUserConnected;

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
        isUserConnected = false;
    }


    /**
     *
     * CASE 1  t , user  --> show profile
     * CASE 2  f , null  --> disconect, show start
     *
     * @param connected
     * @param pUser
     */
    public void connect(boolean connected , Utilisateur pUser) {
        Log.e("HomeActivity.connect", "("+connected+" , "+(pUser!=null?pUser.toString():"")+")" );
        if (connected && pUser != null){
            activity.user = pUser;
            activity.drawer.setInfo( activity.user.getAlias(),activity.user.getEMaill() );
            activity.action = HomeActivity.ACT_NONE;
            isUserConnected = true;
            this.setFgr( new fgrProfile() );
        }
        else if (!connected ) {
            this.isUserConnected = false;
            activity.user =null;
            activity.action = HomeActivity.ACT_START;
            activity.userControler.setFgr( new fgrStart() );
            activity.drawer.setDefaultInfo();
        }
    }

    public boolean isUserConnected(){
        return isUserConnected;
    }




    public void setFgr(Fragment pFragment) {
        Log.e("UserControler","setFgr = "+pFragment.getClass().toString() );
        if (pFragment instanceof fgrCreate){
            fgrCreate = (fgrCreate) pFragment;
            activity.changeFragment( pFragment );
        }
        if (pFragment instanceof fgrConfirmCreate){
            fgrConfirmCreate = (fgrConfirmCreate) pFragment;
            //activity.action = HomeActivity.ACT_.....
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
                this.connect(false ,  null );
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

    public void onLogoffAnswer(Token pToken) {
        if (pToken != null){
            if ( pToken.getEtat() ){
                this.connect(false, null);
            }else{
                Log.e("UsrCtl.onLogoffAnswer()","ERREUR "+pToken.getAction() );
                Toast.makeText(activity.getApplicationContext(), "Échec de déconection " + pToken.getAction() , Toast.LENGTH_SHORT).show();
            }
        }else {
            Log.e("UsrCtl.onLogoffAnswer()","pToken = null");
            Toast.makeText(activity.getApplicationContext(), "Aucune réponce du derveur ", Toast.LENGTH_SHORT).show();
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

    public void onUserAnswer(Utilisateur pUser) {
        Log.e("UserCtrl.onUserAnswer(", (pUser!= null?pUser.toString() : "null" ) +")" );
        activity.action = HomeActivity.ACT_NONE ;
        if (pUser == null ){
            Log.e("onUserAnswer","Token null");
        }else{
            this.connect(true , pUser);
        }
    }

    public void onUserNbPlaylistAnswer(Integer pNbPlaylist) {
        Log.e("UserCtrl.onUserAnswer", "("+pNbPlaylist +")" );
        fgrProfile.onNbPlaylistAnswer(pNbPlaylist);
    }

    public void onUserNbSongAnswer(Integer pNbSong) {
        Log.e("UserCtrl.onUserNbSgAns", "("+pNbSong +")" );
        fgrProfile.onNbSongAnswer(pNbSong);
    }

    public void onModifyUserAnswer(Token pToken) {
        fgrProfile.onEditAnswer(pToken);
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

    public void onGetAvatarListAnswer(List<Avatar> pAvatarList){
        Log.e("UserControler","onDoneSelectingAvatar");
        switch (activity.action) {
            case HomeActivity.ACT_AVATAR_LOAD:
                if (pAvatarList != null){
                    fgrList fgrListAvatar = new fgrList();
                    fgrListAvatar.setListAvatar(pAvatarList , activity);
                    activity.changeFragment( new fgrList());
                }
                else {
                    Log.e("UsrCtl.GetAvLstAnswer()","pAvatarList = null");
                    Toast.makeText(activity.getApplicationContext(), "Aucun Avatar a sélectionner ", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void tryInitialConnection() {
        activity.user = new Utilisateur();
        activity.userControler.loadPreferences();
        activity.action = HomeActivity.ACT_CONNECT_AUTO;
        dataMgr.login( activity.user.getEMaill() , activity.user.getPasowrd() );
    }

    public void goToProfile() {
        Log.e("goToProfile", " connected : " + isUserConnected);
        if (isUserConnected) {
            this.setFgr(new fgrProfile());
        } else {
            fgrStart fgr = new fgrStart();
            this.setFgr(fgr);
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
