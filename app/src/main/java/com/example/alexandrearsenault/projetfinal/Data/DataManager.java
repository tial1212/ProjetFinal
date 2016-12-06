package com.example.alexandrearsenault.projetfinal.Data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private static final String SERVER_PATH_U2    = SERVER_PATH + "/service/utilisateur/getUser";  //NEW
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
    //private static final String SERVER_PATH_U14   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U15   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U16   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U17   = SERVER_PATH + "";
    //private static final String SERVER_PATH_U18   = SERVER_PATH + "";
    private static final String SERVER_PATH_A8    = SERVER_PATH + "/service/avatar/getAvatar?";
    private static final String SERVER_PATH_A9    = SERVER_PATH + "/service/avatar/getAvatarList?";





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
    //private static final int ACTION_U14     = 27;
    //private static final int ACTION_U15     = 28;
    //private static final int ACTION_U16     = 29;
    //private static final int ACTION_U17     = 20;
    //private static final int ACTION_U18     = 31;
    private static final int ACTION_A8      = 32;
    private static final int ACTION_A9      = 33;

    private static  DataManager instance = null;
    private HomeActivity activity;


    public static DataManager getInstance(HomeActivity pHomeActivity) {
        if (instance == null) {
            instance = new DataManager(pHomeActivity);
        }
        return instance;
    }

    /**
     * BE CAREFUL
     * <p>
     * make sure it has been initiare or return null
     *
     * @return instance
     */
    public static DataManager getInstance() {
        return instance;
    }

    private DataManager(HomeActivity pHomeActivity) {
        activity = pHomeActivity;
    }




    public void onDoneDownloadingJson(String pResultJSON, int pAction) {
        Log.e("DataManager", "onDoneDownloadingJson("+pAction+")");
        Log.e("DataManager", "onDoneDownloadingJson("+pResultJSON+")");
        switch (pAction){
            case ACTION_A2:
                //login()
                activity.userControler.onLoginAnswer( this.jsonToToken(pResultJSON) );
                break;
            case ACTION_A3:
                //logoff()
                break;
            case ACTION_P1_1:
                //createUser()
                //activity.userControler.onCreateAnswer( this.jsonToToken(pResultJSON));
                break;
            case ACTION_P1_2:
                //confirmCreateUser()
                break;
            case ACTION_U2:
                //getUser()
                break;
            case ACTION_U1:
                //modifierUser()
                break;
            case ACTION_A10:
                //getUserNbSong()
                break;
            case ACTION_A11:
                //getUserNbPlaylist()
                break;
            case ACTION_U8:
                //createSong()
                break;
            case ACTION_U9_U10:
                //getPrivateSong()
                break;
            case ACTION_P3_P4:
                //getPublicSong()
                break;
            case ACTION_U11:
                //modifySong()
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
                activity.playlistControler.onModifyPlaylistAnswer( this.jsonToToken(pResultJSON) );
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
                break;
            case ACTION_A5:
                //getPublicPlaylistList()
                break;
            case ACTION_A6:
                //getMySongs()
                break;
            case ACTION_A7:
                //getPublicSongsList()
                break;
        }
    }


    public Token jsonToToken(String pJson){
        try {
            JSONObject  jsonObjToken = new JSONObject(pJson);
            if (jsonObjToken == null ){ throw  new JSONException("NULL recieved"); }
            Integer id          = (Integer) jsonObjToken.get("id");
            //String  captchaStr  = (String)  jsonObjToken.get("captchaStr");
            //String  action      = (String)  jsonObjToken.get("action");
            //String  couriel      = (String)  jsonObjToken.get("Courriel");
            Boolean etat        = (Boolean) jsonObjToken.get("etat");
            //String  salt        = (String)  jsonObjToken.get("salt");

            Token token =new Token();
            if (id != null )        { token.setId(id); }
            //if (captchaStr != null ){ token.setCaptchaStr(captchaStr); }
            //if (action != null )    { token.setAction(action); }
            //if (couriel != null )   { token.setEMail(couriel); }
            //if (salt != null )      { token.setSalt(salt); }
            if (etat != null )      { token.setEtat(etat); }
            return token;

        } catch (JSONException e) {
            Log.e("DataMgr.jsonToToken() ","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }


    public Utilisateur jsonToUser(String pJson){
        try {
            JSONObject  jsonObjUser = new JSONObject(pJson);
            if (jsonObjUser == null ){ throw  new JSONException("NULL recieved"); }

            Integer id          = (Integer) jsonObjUser.get("Id");
            Date    date        = (Date)    jsonObjUser.get("Date");
            String  eMaill      = (String)  jsonObjUser.get("Etat");
            String  pasword     = (String)  jsonObjUser.get("Etat");
            Integer avatar      = (Integer) jsonObjUser.get("Id");
            Boolean active      = (Boolean) jsonObjUser.get("Etat");

            Utilisateur user =new Utilisateur();
            if (id != null )         { user.setId(id); }
            if (date != null )       { user.setDate(date); }
            if (eMaill!= null )      { user.setEMaill(eMaill); }
            if (pasword!= null )     { user.setPasowrd(pasword); }
            if (avatar != null )     { user.setAvatar(avatar); }
            if (active != null )     { user.setActive(active); }

            return user;
        } catch (JSONException e) {
            Log.e("DataManager.jsonToSong","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }
    public Musique jsonToSong(String pJson){
        try {
            JSONObject  jsonObjSong = new JSONObject(pJson);
            if (jsonObjSong == null ){ throw  new JSONException("NULL recieved"); }

            Integer id          = (Integer) jsonObjSong.get("Id");
            Date    date        = (Date)    jsonObjSong.get("Date");
            Integer owner       = (Integer) jsonObjSong.get("Etat");
            String  title       = (String)  jsonObjSong.get("Etat");
            String  artist      = (String)  jsonObjSong.get("Etat");
            String  music       = (String)  jsonObjSong.get("Etat");
            Boolean isActive    = (Boolean) jsonObjSong.get("Etat");
            Boolean isPublic    = (Boolean) jsonObjSong.get("Etat");

            Musique song =new Musique();
            if (id != null )         { song.setId(id); }
            if (date != null )       { song.setDate(date); }
            if (owner != null )      { song.setOwner(owner); }
            if (title != null )      { song.setTitle(title); }
            if (artist != null )     { song.setArtist(artist); }
            if (music != null )      { song.setMusic(music); }
            if (isActive != null )   { song.setActive(isActive); }
            if (isPublic != null )   { song.setPublic(isPublic); }

            return song;
        } catch (JSONException e) {
            Log.e("DataManager.jsonToSong","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }
    public List<Musique> jsonToListSong(String pJson){

        try {
            JSONArray jsonArraySong =   new JSONArray(pJson);
            if (jsonArraySong == null || jsonArraySong.length() == 0 ){ throw  new JSONException("NULL recieved OR empty"); }
            List<Musique> listSong =  new ArrayList<Musique>();

            for (int i=0;i<jsonArraySong.length() ;i++){
                JSONObject  jsonObjSong = new JSONObject((String) jsonArraySong.get(i));
                Musique song =new Musique();

                Integer id          = (Integer) jsonObjSong.get("Id");
                Date    date        = (Date)    jsonObjSong.get("Date");
                Integer owner       = (Integer) jsonObjSong.get("Proprietaire");
                String  title       = (String)  jsonObjSong.get("Titre");
                String  artist      = (String)  jsonObjSong.get("Artiste");
                String  music       = (String)  jsonObjSong.get("Musique");
                Boolean isActive    = (Boolean) jsonObjSong.get("Publique");
                Boolean isPublic    = (Boolean) jsonObjSong.get("Active");

                if (id != null )         { song.setId(id); }
                if (date != null )       { song.setDate(date); }
                if (owner != null )      { song.setOwner(owner); }
                if (title != null )      { song.setTitle(title); }
                if (artist != null )     { song.setArtist(artist); }
                if (music != null )      { song.setMusic(music); }
                if (isActive != null )   { song.setActive(isActive); }
                if (isPublic != null )   { song.setPublic(isPublic); }
                listSong.add(song );
            }
            return listSong;
        } catch (JSONException e) {
            Log.e("DataMgr.jsonToListSong","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }
    public ListesDeLecture jsonToPlaylist(String pJson){
        try {
            JSONObject  jsonObjPlaylist = new JSONObject(pJson);
            if (jsonObjPlaylist == null ){ throw  new JSONException("NULL recieved"); }

            Integer id          = (Integer) jsonObjPlaylist.get("Id");
            Date    date        = (Date)    jsonObjPlaylist.get("Date");
            String  name        = (String)  jsonObjPlaylist.get("Nom");
            Integer owner       = (Integer) jsonObjPlaylist.get("Proprietaire");
            Boolean isActive    = (Boolean) jsonObjPlaylist.get("Publique");
            Boolean isPublic    = (Boolean) jsonObjPlaylist.get("Active");

            ListesDeLecture playlist =new ListesDeLecture();
            if (id != null )         { playlist.setId(id); }
            if (date != null )       { playlist.setDate(date); }
            if (owner != null )      { playlist.setOwner(owner); }
            if (name != null )       { playlist.setName(name); }
            if (isActive != null )   { playlist.setActive(isActive); }
            if (isPublic != null )   { playlist.setPublic(isPublic); }

            return playlist;
        } catch (JSONException e) {
            Log.e("DataMgr.jsonToPlaylist","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }

    }
    public List<ListesDeLecture> jsonToListPlaylist(String pJson){
        try {
            JSONArray jsonArrayPlaylist =   new JSONArray(pJson);
            if (jsonArrayPlaylist == null || jsonArrayPlaylist.length() == 0 ){ throw  new JSONException("NULL recieved OR empty"); }
            List<ListesDeLecture> listPlaylist =  new ArrayList<ListesDeLecture>();

            for (int i=0;i<jsonArrayPlaylist.length() ;i++) {
                JSONObject jsonObjPlaylist = new JSONObject((String) jsonArrayPlaylist.get(i));
                ListesDeLecture playlist =new ListesDeLecture();

                Integer id          = (Integer) jsonObjPlaylist.get("Id");
                Date    date        = (Date)    jsonObjPlaylist.get("Date");
                String  name        = (String)  jsonObjPlaylist.get("Nom");
                Integer owner       = (Integer) jsonObjPlaylist.get("Proprietaire");
                Boolean isActive    = (Boolean) jsonObjPlaylist.get("Publique");
                Boolean isPublic    = (Boolean) jsonObjPlaylist.get("Active");

                if (id != null )         { playlist.setId(id); }
                if (date != null )       { playlist.setDate(date); }
                if (owner != null )      { playlist.setOwner(owner); }
                if (name != null )       { playlist.setName(name); }
                if (isActive != null )   { playlist.setActive(isActive); }
                if (isPublic != null )   { playlist.setPublic(isPublic); }

                listPlaylist.add( playlist );
            }
            return listPlaylist;
        } catch (JSONException e) {
            Log.e("DataMgr.jsonToPlaylist","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }


    /**
     * Get an action token.
     *
     * @param pEMail
     * @return token OR null
     */
    private Token getActionToken(String pEMail) {
        Log.e("DataManager", "getActionToken("+pEMail+")");
        try {
            String request = SERVER_PATH_A1+"courriel="+pEMail ;
            String json = DownloadJSONAsyncTask.readJSONfromUrl(request);
            JSONObject jsonObjFilm = new JSONObject(json);//FIXME
            Token token = new Token();
            token.setId((int) jsonObjFilm.get("Id") );
            token.setEtat( (boolean) jsonObjFilm.get("Etat") );
            String salt =  (String) jsonObjFilm.get("salt");
            if (salt != null ){token.setSalt(salt);}
            return token;
        } catch (Exception e) {
            if (e instanceof JSONException) {
                Log.e("DataManager.getAcToken", "ERROR JSON cast");
            } else if (e instanceof Exception) {
                Log.e("DataManager.getAcToken", "ERROR Unknown");
            }
            return null;
        }
    }


    private boolean askToExecuteAction() {
        this.actionToken = this.getActionToken(activity.user.getEMaill() );
        if (this.actionToken != null && this.actionToken.getEtat() == true){
            return true;
        }
        else {
            this.actionToken = null;
            Toast.makeText(activity.getApplicationContext(), "Requête impossible : ActionToken invalide", Toast.LENGTH_SHORT).show();
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
    public void getUser(int pIdToken ,String pKey) {
        Log.e("DataManager", "getUser("+pIdToken+","+pKey+")");
        String request = SERVER_PATH_U2+"idToken="+pIdToken+"&cle="+pKey;
        new DownloadJSONAsyncTask(this , ACTION_U2, request ).execute();
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
            String request = SERVER_PATH_U13+this.getTokenPath()+"&premier="+pFirst+"&dernier="+pLast;
            new DownloadJSONAsyncTask(this , ACTION_U13 , request ).execute();
        }
    }
    public void getPublicSongsList(int pFirst ,int pLast){
        Log.e("DataManager", "getPublicSongsList("+pFirst+","+pLast+")");
        if ( this.askToExecuteAction() ) {
            String request = SERVER_PATH_U13+this.getTokenPath()+"&premier="+pFirst+"&dernier="+pLast;
            new DownloadJSONAsyncTask(this , ACTION_U13 , request ).execute();
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
    //********************************************  SONG PLAYLIST  ********************************************//

}


