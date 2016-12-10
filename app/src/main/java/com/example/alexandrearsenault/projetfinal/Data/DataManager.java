package com.example.alexandrearsenault.projetfinal.Data;

import android.util.Log;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * Created by alexandrearsenault on 2016-09-13.
 */
public class DataManager {

    //keep requested object
    private static Token actionToken;

    private static final String SERVER_PATH = "http://207.162.80.103:8480/rest-example";
    private static final String SERVER_PATH_A1    = SERVER_PATH + "/service/token/getActionToken?";
    private static final String SERVER_PATH_A2    = SERVER_PATH + "/service/utilisateur/login?";
    private static final String SERVER_PATH_A3    = SERVER_PATH + "/service/utilisateur/logoff?";
    private static final String SERVER_PATH_P1_1  = SERVER_PATH + "/service/utilisateur/createUser?";
    private static final String SERVER_PATH_P1_2  = SERVER_PATH + "/service/utilisateur/confirmCreateUser?";
    private static final String SERVER_PATH_U2    = SERVER_PATH + "/service/utilisateur/getUser?";  //NEW
    private static final String SERVER_PATH_U1    = SERVER_PATH + "/service/utilisateur/modify?";
    private static final String SERVER_PATH_A10   = SERVER_PATH + "/service/utilisateur/nbSong?";//NEW
    private static final String SERVER_PATH_A11   = SERVER_PATH + "/service/utilisateur/nbPlaylist?";//NEW
    private static final String SERVER_PATH_U8    = SERVER_PATH + "/service/musique/createSong?";
    private static final String SERVER_PATH_U9_U10= SERVER_PATH + "/service/musique/getPrivateSong?";
    private static final String SERVER_PATH_P3_P4 = SERVER_PATH + "/service/musique/getPublicSong";
    private static final String SERVER_PATH_U11   = SERVER_PATH + "/service/musique/modify?";
    private static final String SERVER_PATH_U12   = SERVER_PATH + "/service/musique/setActive?";
    private static final String SERVER_PATH_U13   = SERVER_PATH + "/service/musique/setPublic?";
    private static final String SERVER_PATH_A6    = SERVER_PATH + "/service/musique/getMySongs?";   //TODO
    private static final String SERVER_PATH_A7    = SERVER_PATH + "/service/musique/getPublicSongsList?"; //TODO
    private static final String SERVER_PATH_U3    = SERVER_PATH + "/service/listeLecture/createPlaylist?";
    private static final String SERVER_PATH_P2    = SERVER_PATH + "/service/listeLecture/getPublicPlaylist?";
    private static final String SERVER_PATH_U4    = SERVER_PATH + "/service/listeLecture/getPrivatePlaylist?";
    private static final String SERVER_PATH_U5    = SERVER_PATH + "/service/listeLecture/modify?";
    private static final String SERVER_PATH_U5_1  = SERVER_PATH + "/service/listeLecture/setPlaylistName?";
    private static final String SERVER_PATH_U6    = SERVER_PATH + "/service/listeLecture/setPlaylistActive?";
    private static final String SERVER_PATH_U7    = SERVER_PATH + "/service/listeLecture/setPlaylistPublic?";
    private static final String SERVER_PATH_A4    = SERVER_PATH + "/service/listeLecture/getMyPlaylists?";
    private static final String SERVER_PATH_A5    = SERVER_PATH + "/service/listeLecture/getPublicPlaylistList?";
    private static final String SERVER_PATH_A12  = SERVER_PATH + "/service/listeLecture/getSongListForPlaylist?"; //TODO  List<Song>
    //private static final String SERVER_PATH_U14   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U15   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U16   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U17   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U18   = SERVER_PATH + "";
    private static final String SERVER_PATH_A8    = SERVER_PATH + "/service/avatar/getAvatar?";
    private static final String SERVER_PATH_A9    = SERVER_PATH + "/service/avatar/getAvatarList?";
    private static final String SERVER_PATH_A13    = SERVER_PATH + "/service/captcha/getCaptcha?";  //TODO




    private static final int ACTION_A1      = 1;
    private static final int ACTION_A2      = 2;
    private static final int ACTION_A3      = 3;
    private static final int ACTION_P1_1    = 4;
    private static final int ACTION_P1_2    = 5;
    private static final int ACTION_U2      = 6;
    private static final int ACTION_A10     = 7;
    private static final int ACTION_A11     = 8;
    private static final int ACTION_U1      = 9;
    private static final int ACTION_U8      = 10;
    private static final int ACTION_U9_U10  = 11;
    private static final int ACTION_P3_P4   = 12;
    private static final int ACTION_U11     = 13;
    private static final int ACTION_U12     = 14;
    private static final int ACTION_U13     = 15;
    private static final int ACTION_A6      = 16;
    private static final int ACTION_A7      = 17;
    private static final int ACTION_U3      = 18;
    private static final int ACTION_P2      = 19;
    private static final int ACTION_U4      = 20;
    private static final int ACTION_U5      = 21;
    private static final int ACTION_U5_1    = 22;
    private static final int ACTION_U6      = 23;
    private static final int ACTION_U7      = 24;
    private static final int ACTION_A4      = 25;
    private static final int ACTION_A5      = 26;
    private static final int ACTION_A12     = 27;
    //private static final int ACTION_U14     = 28;
    //private static final int ACTION_U15     = 29;
    //private static final int ACTION_U16     = 30;
    //private static final int ACTION_U17     = 31;
    //private static final int ACTION_U18     = 32;
    private static final int ACTION_A8      = 33;
    private static final int ACTION_A9      = 34;
    private static final int ACTION_A13     = 35;

    private static  DataManager instance = null;
    private HomeActivity activity;


    public static DataManager getInstance(HomeActivity pHomeActivity) {
        if (instance == null) {
            instance = new DataManager(pHomeActivity);
        }
        return instance;
    }
    public static DataManager getInstance() {
        return instance;
    }

    private DataManager(HomeActivity pHomeActivity) {
        activity = pHomeActivity;
    }




    public void onDoneDownloadingJson(String pResultJSON, int pAction) {
        switch (pAction){
            case ACTION_A2:
                //login()
                activity.userControler.onLoginAnswer( ObjectConvertor.jsonToToken(pResultJSON) );
                break;
            case ACTION_A3:
                //logoff()
                activity.userControler.onLogoffAnswer( ObjectConvertor.jsonToToken(pResultJSON) );
                break;
            case ACTION_P1_1:
                //createUser()
                //activity.userControler.onCreateUserAnswer( ObjectConvertor.jsonToToken(pResultJSON));
                //TODO ERASE testing purpose only
                Token t = new Token();
                t.setAction("création ok ");
                t.setEtat(true);
                t.setCaptchaStr("c4ptchA");
                activity.userControler.onCreateUserAnswer( t );
                //TODO ERASE testing purpose only
                break;
            case ACTION_P1_2:
                //confirmCreateUser()
                activity.userControler.onConfirmCreateUser(ObjectConvertor.jsonToToken(pResultJSON));
                //TODO ERASE testing purpose only
                Token t2 = new Token();
                t2.setAction("création ok ");
                t2.setEtat(true);
                t2.setCaptchaStr("c4ptchA");
                activity.userControler.onConfirmCreateUser( t2 );
                //TODO ERASE testing purpose only
                break;
            case ACTION_U2:
                //getUser()
                //activity.userControler.onGetUserAnswer( ObjectConvertor.jsonToUser(pResultJSON) );
                //TODO ERASE testing purpose only
                Log.e("DataManager", "getUser()");
                Utilisateur alex = new Utilisateur();
                alex.setEMaill(HomeActivity.emailTest);
                alex.setPasowrd(HomeActivity.pswdTest);
                alex.setAlias("tial");
                alex.setId(1);
                activity.userControler.onGetUserAnswer( alex );
                //TODO ERASE testing purpose only
                break;
            case ACTION_U1:
                //modifierUser()
                activity.userControler.onModifyUserAnswer( ObjectConvertor.jsonToToken(pResultJSON)  );
                break;
            case ACTION_A10:
                //getUserNbSong()
                activity.userControler.onUserNbSongAnswer(  ObjectConvertor.jsonToInteger(pResultJSON) );
                break;
            case ACTION_A11:
                //getUserNbPlaylist()
                activity.userControler.onUserNbPlaylistAnswer(  ObjectConvertor.jsonToInteger(pResultJSON) );
                break;
            case ACTION_U8:
                //createSong()
                activity.songControler.onCreateSongAnswer( ObjectConvertor.jsonToToken(pResultJSON) );
                break;
            case ACTION_U9_U10:
                //getPrivateSong()
                break;
            case ACTION_P3_P4:
                //getPublicSong()
                break;
            case ACTION_U11:
                //modifySong()
                activity.songControler.fgSong.onSongModifyAnswer(ObjectConvertor.jsonToToken(pResultJSON));
                break;
            case ACTION_U12:
                //setActiveSong()
                break;
            case ACTION_U13:
                //setPublicSong()
                break;
            case ACTION_U3:
                //createPlaylist()
                break;
            case ACTION_P2:
                //getPublicPlaylist()
                break;
            case ACTION_U4:
                //getPrivatePlaylist()
                break;
            case ACTION_U5:
                //modifyPlaylist()
                activity.playlistControler.fgPlaylist.onModifyPlaylistAnswer( ObjectConvertor.jsonToToken(pResultJSON) );
                break;
            case ACTION_A12:
                //getSongListForPlaylist()
                //activity.playlistControler.onGetSongForPlaylistAnswer( ObjectConvertor.jsonToListSong(pResultJSON) );
                //TODO ERASE testing purpose only
                ArrayList<Musique> list2 = new ArrayList<>();
                list2.add(new Musique( 1 ,"Show me How to Burlesque" ,"Christina Aguilera" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,true, true ));
                list2.add(new Musique( 1 ,"Hey Devil" ,"Toby Mac" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,true, false ));
                list2.add(new Musique( 2 ,"Love on the Brain" ,"Rihanna" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,true, false ));
                list2.add(new Musique( 2 ,"Juju on That Beat (TZ Anthem)" ,"Zay Hilfigerrr & Zayion McCall" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,false, true ));
                list2.add(new Musique( 2 ,"Alcohol Remix (feat. SkyBlu)" ,"The Cataracs" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,"dsfhagwtebdfbwdsfbzxsgbdsegbsf" ,false, false ));
                activity.playlistControler.onGetSongListForPlaylistAnswer( list2 );

                break;
            case ACTION_U5_1:
                //setPlaylistName()
                break;
            case ACTION_U6:
                //setPlaylistActive()
                break;
            case ACTION_U7:
                //setPlaylistPublic()
                break;
            case ACTION_A4:
                //getMyPlaylists()
                //activity.listControler.onMyPlaylistListAnswer( ObjectConvertor.jsonToListPlaylist(pResultJSON) );
                //TODO ERASE testing purpose only
                ArrayList<ListesDeLecture> list = new ArrayList<>();
                list.add(new ListesDeLecture(1,"Best of Rihanna",true , true )   );
                list.add(new ListesDeLecture(1,"420 party Time",true , false )   );
                list.add(new ListesDeLecture(1,"The A List : POP",false , true )   );
                list.add(new ListesDeLecture(1,"Chrismast time",false , false )   );
                activity.listControler.onMyPlaylistListAnswer( list );

                break;
            case ACTION_A5:
                //getPublicPlaylistList()
                break;
            case ACTION_A6:
                //getMySongs()
                activity.songControler.onSongAnswer(ObjectConvertor.jsonToSong(pResultJSON));
                break;
            case ACTION_A7:
                //getPublicSongsList()
                activity.listControler.onPublicPlaylistListAnswer( ObjectConvertor.jsonToListPlaylist(pResultJSON));
                break;
            case ACTION_A8:
                //getAvatar()
                break;
            case ACTION_A9:
                //getAvatarList()
                activity.userControler.onGetAvatarListAnswer( ObjectConvertor.jsonToListAvatar(pResultJSON) );
                break;
            case ACTION_A13:
                //getCaptcha()
                //activity.userControler.onGetCaptchaAnswer( ObjectConvertor.stringBas64ToBitMap( pResultJSON ) );
                break;
        }
    }


    private Token getActionToken(String pEMail) {
        Log.e("DataManager", "getActionToken("+pEMail+")");
        String request = SERVER_PATH_A1+"courriel="+pEMail ;
        String json = null;
        try {
            json = new DownloadJSONAsyncTask(this,ACTION_A1,request).execute().get();
            return ObjectConvertor.jsonToToken(json);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //String json = downJson.readJSONfromUrl(request, "PUT");

        return null;
    }

    private boolean askToExecuteAction() {
        this.actionToken = this.getActionToken(activity.user.getEMaill() );
        if (this.actionToken != null && this.actionToken.getEtat() == true){
            return true;
        }
        else {
            this.actionToken = null;
            activity.toaster.message("Requête impossible : ActionToken invalide");
            return false;
        }
    }


    private String getTokenPath() {
        return  "idToken="+this.actionToken.getId()+"&cle="+activity.user.getPasowrd()+this.actionToken.getSalt();
    }



    //********************************************  USER  ********************************************//
    public void login(String pCourriel, String pMotDePasse) {
        Log.e("DataManager", "login("+pCourriel+","+pMotDePasse+")");
        String request = SERVER_PATH_A2+"courriel="+pCourriel+"&motDePasse="+pMotDePasse;
        new DownloadJSONAsyncTask(this , ACTION_A2 , request ).execute();
    }
    public void logoff(String pCourriel) {;
        Log.e("DataManager", "login("+pCourriel+")");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_A3+this.getTokenPath()+"&courriel="+pCourriel;
            new DownloadJSONAsyncTask(this , ACTION_A3 , request ).execute();
        }
    }
    public void createUser (String pAlias ,String pMotDePasse ,String pCourriel ,int pIdAvatar) {
        Log.e("DataManager", "createUser("+pAlias+","+pMotDePasse+","+pCourriel+","+pIdAvatar+")");
        String request = SERVER_PATH_P1_1+"alias="+pAlias+"&motDePasse="+pMotDePasse+"&courriel="+pCourriel+"&avatar="+pIdAvatar;
        new DownloadJSONAsyncTask(this , ACTION_P1_1 , request ).execute();
    }
    public void confirmCreateUser(int pIdToken ,String pCaptchaVal) {
        Log.e("DataManager", "confirmCreateUser("+pIdToken+","+pCaptchaVal+")");
        String request = SERVER_PATH_P1_2+"idToken="+pIdToken+"&captchaVal="+pCaptchaVal;
        new DownloadJSONAsyncTask(this , ACTION_P1_2 , request ).execute();
    }
    public void getUser() {
        Log.e("DataManager", "getUser()");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_U2+this.getTokenPath();
            new DownloadJSONAsyncTask(this , ACTION_U2, request ).execute();
        }
    }
    public void modifierUser(String pEMaill,String pPasword,String pAlias,  int  pIdAvatar) {
        Log.e("DataManager", "modifierUser("+pEMaill+","+pPasword+","+pAlias+","+pIdAvatar+")");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_U1+ this.getTokenPath() +"&courriel="+pEMaill+"&motDePasse="+pPasword+"&alias="+pAlias+"&avatar="+pIdAvatar;
            new DownloadJSONAsyncTask(this , ACTION_U1 , request ).execute();
        }
    }
    public void getUserNbSong() {
        Log.e("DataManager", "getUserNbSong()");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_A10+ this.getTokenPath();
            new DownloadJSONAsyncTask(this , ACTION_A10 , request ).execute();
        }
    }
    public void getUserNbPlaylist() {
        Log.e("DataManager", "getUserNbPlaylist()");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_A11+ this.getTokenPath();
            new DownloadJSONAsyncTask(this , ACTION_A11 , request ).execute();
        }
    }
    //********************************************  SONG  ********************************************//
    public void createSong(String pTitle ,String pArtist ,String pMusic ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "createSong("+pArtist+","+pMusic+","+pCoverArt+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U8+this.getTokenPath()+"&titre="+pTitle+"&artiste="+pArtist+"&musique="+pMusic+"&coverArt="+pCoverArt+"&public="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U8 , request ).execute();
    }
    public void getPrivateSong(int pIdSong ) {
        Log.e("DataManager", "getPrivateSong("+pIdSong+")");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_U9_U10+this.getTokenPath()+"&idSong="+pIdSong;
            new DownloadJSONAsyncTask(this , ACTION_U9_U10 , request ).execute();
        }
    }
    public void getPublicSong (int pIdSong ) {
        Log.e("DataManager", "getPublicSong("+pIdSong+")");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_P3_P4 +this.getTokenPath()+ "&idSong="+pIdSong;
            new DownloadJSONAsyncTask(this , ACTION_P3_P4 , request ).execute();
        }
    }
    public void modifySong (int pIdSong,String pTitle ,String pArtist ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "modifySong("+pIdSong+","+pTitle+","+pArtist+","+pCoverArt+","+pIsPublic+","+pIsActive+")");
        if ( this.askToExecuteAction() ){
            String request = SERVER_PATH_U11+ this.getTokenPath() +"&idSong="+pIdSong+"&titre="+pTitle+"&artiste="+pArtist+"&vignette="+pCoverArt+"&publique="+pIsPublic+"&active="+pIsActive;
            new DownloadJSONAsyncTask(this , ACTION_U11 , request ).execute();
        }
    }
    public void setActiveSong(int pIdSong ,boolean pActive  ) {
        Log.e("DataManager", "setActiveSong("+pIdSong+","+pActive+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U12 + this.getTokenPath() + "&idSong=" + pIdSong + "&active=" + pActive;
            new DownloadJSONAsyncTask(this, ACTION_U12, request).execute();
        }
    }
    public void setPublicSong(String pIdToken ,String pKey , int pIdSong ,boolean pIsPublic ) {
        Log.e("DataManager", "setPublicSong("+pIdToken+","+pKey+","+pIdSong+","+pIsPublic+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U13+this.getTokenPath()+"&idSong="+pIdSong+"&publique="+pIsPublic;
            new DownloadJSONAsyncTask(this , ACTION_U13 , request ).execute();
        }
    }
    public void getMySongs(int pFirst ,int pLast){
        Log.e("DataManager", "getMySongs("+pFirst+","+pLast+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A6+this.getTokenPath()+"&premier="+pFirst+"&dernier="+pLast;
            new DownloadJSONAsyncTask(this , ACTION_A6 , request ).execute();
        }
    }
    public void getPublicSongsList(int pFirst ,int pLast){
        Log.e("DataManager", "getPublicSongsList("+pFirst+","+pLast+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A7+this.getTokenPath()+"&premier="+pFirst+"&dernier="+pLast;
            new DownloadJSONAsyncTask(this , ACTION_A7 , request ).execute();
        }
    }
    // ********************************************  PLAYLIST  ******************************************** //
    public void createPlaylist(String pName ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "createPlaylist("+pName+","+pIsPublic+","+pIsActive+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U3+this.getTokenPath()+"&nom="+pName+"&publique="+pIsPublic+"&active="+pIsActive;
            new DownloadJSONAsyncTask(this , ACTION_U3 , request ).execute();
        }
    }
    public void getPublicPlaylist(int pIdPlaylist ) {
        Log.e("DataManager", "getPublicPlaylist("+pIdPlaylist+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_P2+this.getTokenPath()+"&idPlaylist="+pIdPlaylist;
            new DownloadJSONAsyncTask(this , ACTION_P2 , request ).execute();
        }
    }
    public void getPrivatePlaylist(int pIdPlaylist ) {
        Log.e("DataManager", "getPrivatePlaylist("+","+pIdPlaylist+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U4+this.getTokenPath()+"&idPlaylist="+pIdPlaylist;
            new DownloadJSONAsyncTask(this , ACTION_U4 , request ).execute();
        }
    }
    public void modifyPlaylist(int	pIdPlaylist ,String pName ,boolean pIsPublic ,boolean pIsActive ) {
        Log.e("DataManager", "modifyPlaylist("+pName+","+pIsPublic+","+pIsActive+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U5+this.getTokenPath()+"&idPlaylist="+pIdPlaylist+"&nom="+pName+"&publique="+pIsPublic+"&active="+pIsActive;
            new DownloadJSONAsyncTask(this , ACTION_U5 , request ).execute();
        }
    }
    public void setPlaylistName(int pIdPlaylist ,String pName ) {
        Log.e("DataManager", "setPlaylistName("+pIdPlaylist+","+pName+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U5_1+this.getTokenPath()+"&idPlaylist="+pIdPlaylist+"&nom="+pName ;
            new DownloadJSONAsyncTask(this , ACTION_U5_1 , request ).execute();
        }
    }
    public void setPlaylistActive (int pIdPlaylist ,boolean pIsActive) {
        Log.e("DataManager", "setPlaylistActive("+pIdPlaylist+","+pIsActive+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U6+this.getTokenPath()+"&idPlaylist="+pIdPlaylist+"&active="+pIsActive;
            new DownloadJSONAsyncTask(this , ACTION_U6 , request ).execute();
        }
    }
    public void setPlaylistPublic (int pIdPlaylist ,boolean pIsPublic) {
        Log.e("DataManager", "setPlaylistPublic("+pIdPlaylist+","+pIsPublic+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U7+this.getTokenPath()+"&publique="+pIsPublic;
            new DownloadJSONAsyncTask(this , ACTION_U7 , request ).execute();
        }
    }
    public void getMyPlaylists(int pFirst ,int pLast) {
        Log.e("DataManager", "getMyPlaylists("+pFirst+","+pLast+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A4+this.getTokenPath()+"&premier="+pFirst+"&dernier="+pLast;;
            new DownloadJSONAsyncTask(this , ACTION_A4 , request ).execute();
        }
    }
    public void getPublicPlaylistList(int pFirst ,int pLast) {
        Log.e("DataManager", "getPublicPlaylistList("+pFirst+","+pLast+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A5+this.getTokenPath()+"&premier="+pFirst+"&dernier="+pLast;;
            new DownloadJSONAsyncTask(this , ACTION_A5 , request ).execute();
        }
    }
    public void getSongListForPlaylist(int pIdPlaylist) { //TODO
        Log.e("DataManager", "getSongListForPlaylist("+pIdPlaylist+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A12+this.getTokenPath()+"&idPlaylist="+pIdPlaylist;
            new DownloadJSONAsyncTask(this , ACTION_A12 , request ).execute();
        }
    }

    //********************************************  AVATAR  ********************************************//
    public void getAvatar(int pIdAvatar ) {
        Log.e("DataManager", "getPublicPlaylistList("+pIdAvatar+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A8+this.getTokenPath()+"&idAvatar="+pIdAvatar;;
            new DownloadJSONAsyncTask(this , ACTION_A8 , request ).execute();
        }
    }
    public void getAvatarList(int pFirst ,int pLast) {
        Log.e("DataManager", "getPublicPlaylistList("+pFirst+","+pLast+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A9+"idToken="+this.getTokenPath()+"&premier="+pFirst+"&dernier="+pLast;;
            new DownloadJSONAsyncTask(this , ACTION_A9 , request ).execute();
        }
    }
    //********************************************  AVATAR  ********************************************//
    public void getCaptcha(String pCaptchaStr ) {
        Log.e("DataManager", "getPublicPlaylistList("+pCaptchaStr+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_A13+"captchaStr="+pCaptchaStr;
            new DownloadJSONAsyncTask(this , ACTION_A13 , request ).execute();
        }
    }
    //********************************************  SONG PLAYLIST  ********************************************//

}


