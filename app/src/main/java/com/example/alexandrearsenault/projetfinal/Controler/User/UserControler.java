package com.example.alexandrearsenault.projetfinal.Controler.User;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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

    public boolean isUserConnected(){
        return isUserConnected;
    }


    


    public void setFragment(Fragment pFragment) {
        Log.e("UserControler","setFragment()"+pFragment.getClass().toString() );
        if (pFragment instanceof fgrCreate){
            fgrCreate = (fgrCreate) pFragment;
            activity.setFragment( pFragment );
        }
        else if (pFragment instanceof fgrCreate){
            fgrConfirmCreate = (fgrConfirmCreate) pFragment;
            activity.setFragment( pFragment );
        }
        else if (pFragment instanceof fgrLogin){
            fgrLogin = (fgrLogin) pFragment;
            activity.setFragment( pFragment );
        }
        else if (pFragment instanceof fgrStart){
            fgrStart = (fgrStart) pFragment;
            activity.setFragment( pFragment );
        }
        else if (pFragment instanceof fgrProfile){
            fgrProfile = (fgrProfile) pFragment;
            activity.setFragment( pFragment );
        }
        else{
            Log.e("UserControler.setFgr()","Fragment type invalid");
        }
    }
    // -----------------------LOGIN / LOGOFF-----------------------//
    public void onLoginAnswer(Token pToken) {
        if (pToken == null ){
            Log.e("UserControler","onLoginAnswer() -> Token null");
            return;
        }
        if (activity.action == HomeActivity.ACT_CONNECT_AUTO){
            activity.action = HomeActivity.ACT_NONE;
            if ( pToken.getEtat() ){
                Log.e("UserControler","onLoginAnswer() -> auto connect OK");
                dataMgr.getUser();
            }else{
                this.connect(false ,  null );
                Log.e("UserControler","onLoginAnswer() -> auto connect FAILLED");
            }
        }else if (activity.action == HomeActivity.ACT_CONNECT){
            activity.action = HomeActivity.ACT_NONE;
            if (pToken.getEtat() ){
                Log.e("UserControler","onLoginAnswer() -> connect OK");
                dataMgr.getUser();
            }else{
                fgrLogin.setError( pToken.getAction() );
                Log.e("UserControler","onLoginAnswer() -> connect FAILLED");
            }
        }
    }
    public void onLogoffAnswer(Token pToken) {
        if (pToken == null){
            Log.e("UsrCtl.onLogoffAnswer()","pToken = null");
            activity.toaster.errorRequest();
            return;
        }

        if ( !pToken.getEtat() ) {    //TODO reverse to "pToken.getEtat()"
            this.connect(false, null);
        }
        else {
            Log.e("UsrCtl.onLogoffAnswer()","ERREUR "+pToken.getAction() );
            activity.toaster.message( "Échec de déconection ");
        }
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
        Log.e("UserControler.connect", "("+connected+" , "+(pUser!=null?pUser.toString():"")+")" );
        if (connected && pUser != null){
            activity.user = pUser;
            activity.drawer.setInfo( activity.user.getAlias(),activity.user.getEMaill()  );
            dataMgr.getAvatar(activity.user.getAvatar() );
            activity.action = HomeActivity.ACT_NONE;
            activity.drawer.showPrivateOption(true);
            isUserConnected = true;
            this.setFragment( new fgrProfile() );
        }
        else if (!connected ) {
            this.setFragment( new fgrStart() );
            this.isUserConnected = false;
            activity.drawer.setDefaultInfo();
            activity.user =null;
            activity.action = HomeActivity.ACT_START;
            activity.drawer.showPrivateOption(false);
        }
    }
    // -----------------------PROFILE-----------------------//
    public void onCreateUserAnswer(Token pToken) {
        if (pToken == null){
            Log.e("UserControler","onCreateUserAnswer() -> Token null");
            activity.toaster.errorRequest();
            return;
        }
        if (pToken.getEtat() ){
            fgrConfirmCreate = new fgrConfirmCreate() ;
            fgrConfirmCreate.setIdToken(pToken.getId() );
            dataMgr.getCaptcha( pToken.getCaptchaStr() );
        }
        else {
            fgrCreate.setError( pToken.getAction() );
        }
    }

    public void onGetCaptchaAnswer(Bitmap pCaptcha64) {
        if (pCaptcha64 == null){
            Log.e("UserControler","onGetCaptchaAnswer() -> pCaptcha64 null");
            activity.toaster.errorRequest();
            return;
        }
        fgrConfirmCreate.setCaptcha(pCaptcha64);
        this.setFragment(fgrConfirmCreate);
    }

    public void onConfirmCreateUser(Token pToken) {
        if (pToken == null){
            Log.e("UserControler","onConfirmCreateUser() -> Token null");
            activity.toaster.errorRequest();
            return;
        }
        if (pToken.getEtat() ){
            dataMgr.getUser();
        }
        else {
            fgrConfirmCreate.setError( pToken.getAction() );
        }

    }
    public void onGetUserAnswer(Utilisateur pUser) {
        Log.e("UserCtrl.onUserAnswer(", (pUser!= null?pUser.toString() : "null" ) +")" );
        activity.action = HomeActivity.ACT_NONE ;
        if (pUser == null ){
            Log.e("onUserAnswer","Token null");
            activity.toaster.errorRequest();
            this.connect(false , null);
            return;
        }
        this.connect(true , pUser);

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


    // -----------------------AVATAR-----------------------//
    public void onGetAvatarAnswer(Avatar pAvatar){
        if (pAvatar == null){
            activity.toaster.message("impossible de charger l'avatar");
            return;
        }
        //activity.drawer.setAvatar(pAvatar.setAvatar() ); TODO convert B64 to bitmap
    }
    public void onGetAvatarListAnswer(List<Avatar> pAvatarList){
        Log.e("UserControler","onDoneSelectingAvatar");
        if (pAvatarList == null){
            Log.e("UsrCtl.GetAvLstAnswer()","pAvatarList = null");
            activity.toaster.message( "Aucun Avatar a sélectionner ");
            return;
        }
        switch (activity.action) {
            case HomeActivity.ACT_AVATAR_CREATE:  ///TODO ?????? not the good action
                fgrList fgList = new fgrList();
                fgList.setListAvatar(pAvatarList);
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
    // -----------------------OTHER-----------------------//
    public void goToProfile() {
        Log.e("goToProfile", " connected : " + isUserConnected);
        if (isUserConnected) {
            this.setFragment(new fgrProfile());
        } else {
            fgrStart fgr = new fgrStart();
            this.setFragment(fgr);
        }
    }
    public void tryInitialConnection() {
        activity.user = new Utilisateur();
        activity.userControler.loadPreferences();
        activity.action = HomeActivity.ACT_CONNECT_AUTO;
        dataMgr.login( activity.user.getEMaill() , activity.user.getPasowrd() );
    }

    // -----------------------SAVING DATA-----------------------//
    public void savePreferences(){
        Log.e("UserControler","savePreferences()");
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREFS_USER_EMAIL, (isUserConnected?activity.user.getEMaill():"") );
        editor.putString(PREFS_USER_PSWD, (isUserConnected?activity.user.getPasowrd():"") );
        editor.commit();
    }
    public void loadPreferences(){
        Log.e("UserControler","loadPreferences()");
        activity.user.setEMaill( settings.getString(PREFS_USER_EMAIL, "") );
        activity.user.setPasowrd( settings.getString(PREFS_USER_PSWD,  "") );

    }
}
