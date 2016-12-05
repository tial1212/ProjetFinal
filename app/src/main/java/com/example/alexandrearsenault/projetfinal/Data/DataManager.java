package com.example.alexandrearsenault.projetfinal.Data;

import android.util.Log;

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
    private static final String SERVER_PATH = "http://207.162.80.103:8480/rest-example";
    private static final String SERVER_PATH_A1    = SERVER_PATH + "/service/token/getActionToken?";
    private static final String SERVER_PATH_A2    = SERVER_PATH + "/service/utilisateur/login?";
    private static final String SERVER_PATH_A3    = SERVER_PATH + "/service/utilisateur/logoff?";
    private static final String SERVER_PATH_P1_1  = SERVER_PATH + "/service/utilisateur/createUser?";
    private static final String SERVER_PATH_P1_2  = SERVER_PATH + "/service/utilisateur/confirmCreateUser?";
    private static final String SERVER_PATH_U2    = SERVER_PATH + "/service/utilisateur/?";
    private static final String SERVER_PATH_U1    = SERVER_PATH + "/service/utilisateur/modify?";
    private static final String SERVER_PATH_U8    = SERVER_PATH + "/service/musique/createSong?";
    private static final String SERVER_PATH_U9_U10= SERVER_PATH + "/service/musique/getPrivateSong?";
    private static final String SERVER_PATH_P3_P4 = SERVER_PATH + "/service/musique/getPublicSong";
    private static final String SERVER_PATH_U11   = SERVER_PATH + "/service/musique/modify?";
    private static final String SERVER_PATH_U12   = SERVER_PATH + "/service/musique/setActive?";
    private static final String SERVER_PATH_U13   = SERVER_PATH + "/service/musique/setPublic?";
    private static final String SERVER_PATH_A6    = SERVER_PATH + "/service/musique/getMySongs?";
    private static final String SERVER_PATH_A7    = SERVER_PATH + "/service/musique/getPublicSongsList?";
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
    private static final int ACTION_U1      = 7;
    private static final int ACTION_U8      = 8;
    private static final int ACTION_U9_U10  = 9;
    private static final int ACTION_P3_P4   = 10;
    private static final int ACTION_U11     = 11;
    private static final int ACTION_U12     = 12;
    private static final int ACTION_U13     = 13;
    private static final int ACTION_A6      = 14;
    private static final int ACTION_A7      = 15;
    private static final int ACTION_U3      = 16;
    private static final int ACTION_P2      = 17;
    private static final int ACTION_U4      = 18;
    private static final int ACTION_U5      = 19;
    private static final int ACTION_U5_1    = 20;
    private static final int ACTION_U6      = 21;
    private static final int ACTION_U7      = 22;
    private static final int ACTION_A4      = 23;
    private static final int ACTION_A5      = 24;
    //private static final int ACTION_U14     = 25;
    //private static final int ACTION_U15     = 26;
    //private static final int ACTION_U16     = 27;
    //private static final int ACTION_U17     = 28;
    //private static final int ACTION_U18     = 29;
    private static final int ACTION_A8      = 30;
    private static final int ACTION_A9      = 31;

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
            JSONObject  jsonObjFilm = new JSONObject(pJson);
            if (jsonObjFilm == null ){ throw  new JSONException("NULL recieved"); }
            Integer id          = (Integer) jsonObjFilm.get("id");
            //String  captchaStr  = (String)  jsonObjFilm.get("captchaStr");
            //String  action      = (String)  jsonObjFilm.get("action");
            //String  couriel      = (String)  jsonObjFilm.get("Courriel");
            Boolean etat        = (Boolean) jsonObjFilm.get("etat");
            //String  salt        = (String)  jsonObjFilm.get("salt");

            Token token =new Token();
            if (id != null )        { token.setId(id); }
            //if (captchaStr != null ){ token.setCaptchaStr(captchaStr); }
            //if (action != null )    { token.setAction(action); }
            //if (couriel != null )   { token.setEMail(couriel); }
            //if (salt != null )      { token.setSalt(salt); }
            if (etat != null )      { token.setEtat(etat); }
            return token;

        } catch (JSONException e) {
            Log.e("DataMgr.jsonToToken XX ","ERROR CASTING");
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
    public Token getActionToken(String pEMail) {
        Log.e("DataManager", "getActionToken("+pEMail+")");
        String request = SERVER_PATH_A1+"courriel="+pEMail ;
        String json = DownloadJSONAsyncTask.readJSONfromUrl(request);
        try {
            JSONObject jsonObjFilm = new JSONObject(json);//FIXME
            Token token = new Token();
            token.setId((int) jsonObjFilm.get("Id") );
            token.setEtat( (boolean) jsonObjFilm.get("Etat") );
            String salt =  (String) jsonObjFilm.get("Id");
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


    public boolean askToExecuteAction() {
        Token token = this.getActionToken(activity.email);
        if (token != null && token.getEtat() == true){
            activity.actionToken = token;
            return true;
        }
        else {
            activity.actionToken = null;
            //TODO toast error
            return false;
        }
    }


    //********************************************  USER  ********************************************//
    public void login(String pCourriel, String pMotDePasse) {
        Log.e("DataManager", "login("+pCourriel+","+pMotDePasse+")");
        String request = SERVER_PATH_A2+"courriel="+pCourriel+"&motDePasse="+pMotDePasse;
        new DownloadJSONAsyncTask(this , ACTION_A2 , request ).execute();
    }
    public void logoff(int pIdToken  ,String pKey ,String pCourriel) {;
        Log.e("DataManager", "login("+pIdToken+","+pKey+","+pCourriel+")");
        String request = SERVER_PATH_A3+"idToken="+pIdToken+"&cle="+pKey+"&courriel="+pCourriel;
        new DownloadJSONAsyncTask(this , ACTION_A3 , request ).execute();
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
        Log.e("DataManager", "confirmCreateUser("+pIdToken+","+pKey+")");
        String request = SERVER_PATH_U2+"idToken="+pIdToken+"&cle="+pKey;
        new DownloadJSONAsyncTask(this , ACTION_U2, request ).execute();
    }
    public void modifierUser(int pIdToken,String pKey,String pEMaill,String pPasword,String pAlias,  int  pIdAvatar) {
        Log.e("DataManager", "modifierUser("+pIdToken+","+pKey+","+pEMaill+","+pPasword+","+pAlias+","+pIdAvatar+")");
        String request = SERVER_PATH_U1+"idToken="+pIdToken+"&cle="+pKey+"&courriel="+pEMaill+"&motDePasse="+pPasword+"&alias="+pAlias+"&avatar="+pIdAvatar;
        new DownloadJSONAsyncTask(this , ACTION_U1 , request ).execute();
    }
    //********************************************  SONG  ********************************************//
    public void createSong(int pIdToken ,String pKey ,String pTitle ,String pArtist ,String pMusic ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "createSong("+pIdToken+","+pKey+","+pArtist+","+pMusic+","+pCoverArt+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U8+"idToken="+pIdToken+"&cle="+pKey+"&titre="+pTitle+"&artiste="+pArtist+"&musique="+pMusic+"&coverArt="+pCoverArt+"&public="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U8 , request ).execute();
    }
    public void getPrivateSong(int pIdToken ,String pKey ,int pIdSong ) {
        Log.e("DataManager", "getPrivateSong("+pIdToken+","+pKey+","+pIdSong+")");
        String request = SERVER_PATH_U9_U10+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong;
        new DownloadJSONAsyncTask(this , ACTION_U9_U10 , request ).execute();
    }
    public void getPublicSong (int pIdToken ,String pKey ,int pIdSong ) {
        Log.e("DataManager", "getPublicSong("+pIdToken+","+pKey+","+pIdSong+")");
        String request = SERVER_PATH_P3_P4+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong;
        new DownloadJSONAsyncTask(this , ACTION_P3_P4 , request ).execute();
    }
    public void modifySong (int pIdToken,String pKey ,int pIdSong,String pTitle ,String pArtist ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "modifySong("+pIdToken+","+pKey+","+pIdSong+","+pTitle+","+pArtist+","+pCoverArt+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U11+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong+"&titre="+pTitle+"&artiste="+pArtist+"&vignette="+pCoverArt+"&publique="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U11 , request ).execute();
    }
    public void setActiveSong(int pIdToken ,String pKey ,int pIdSong ,boolean pActive  ) {
        Log.e("DataManager", "setActiveSong("+pIdToken+","+pKey+","+pIdSong+","+pActive+")");
        String request = SERVER_PATH_U12+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong+"&active="+pActive;
        new DownloadJSONAsyncTask(this , ACTION_U12 , request ).execute();
    }
    public void setPublicSong(String pIdToken ,String pKey , int pIdSong ,boolean pIsPublic ) {
        Log.e("DataManager", "setPublicSong("+pIdToken+","+pKey+","+pIdSong+","+pIsPublic+")");
        String request = SERVER_PATH_U13+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong+"&publique="+pIsPublic;
        new DownloadJSONAsyncTask(this , ACTION_U13 , request ).execute();
    }
    public void getMySongs(int pIdToken,String pKey ,int pFirst ,int pLast){
        Log.e("DataManager", "getMySongs("+pIdToken+","+pKey+")");
        String request = SERVER_PATH_U13+"idToken="+pIdToken+"&cle="+pKey+"&premier="+pFirst+"&dernier="+pLast;
        new DownloadJSONAsyncTask(this , ACTION_U13 , request ).execute();
    }
    public void getPublicSongsList(int pIdToken,String pKey ,int pFirst ,int pLast){
        Log.e("DataManager", "getPublicSongsList("+pIdToken+","+pKey+","+pFirst+","+pLast+")");
        String request = SERVER_PATH_U13+"idToken="+pIdToken+"&cle="+pKey+"&premier="+pFirst+"&dernier="+pLast;
        new DownloadJSONAsyncTask(this , ACTION_U13 , request ).execute();
    }
    //********************************************  PLAYLIST  ********************************************//
    public void createPlaylist(int pIdToken ,String pKey ,String pName ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "createPlaylist("+pIdToken+","+pKey+","+pName+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U3+"idToken="+pIdToken+"&cle="+pKey+"&cle="+pKey+"&nom="+pName+"&publique="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U3 , request ).execute();
    }
    public void getPublicPlaylist(int pIdToken ,String pKey ,int pIdPlaylist ) {
        Log.e("DataManager", "getPublicPlaylist("+pIdToken+","+pKey+","+pIdPlaylist+")");
        String request = SERVER_PATH_P2+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist;
        new DownloadJSONAsyncTask(this , ACTION_P2 , request ).execute();
    }
    public void getPrivatePlaylist(int pIdToken ,String pKey ,int pIdPlaylist ) {
        Log.e("DataManager", "getPrivatePlaylist("+pIdToken+","+pKey+","+pIdPlaylist+")");
        String request = SERVER_PATH_U4+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist;
        new DownloadJSONAsyncTask(this , ACTION_U4 , request ).execute();
    }
    public void modifyPlaylist(int pIdToken ,String pKey ,int	pIdPlaylist ,String pName ,boolean pIsPublic ,boolean pIsActive ) {
        Log.e("DataManager", "modifyPlaylist("+pIdToken+","+pKey+","+pName+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U5+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist+"&nom="+pName+"&publique="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U5 , request ).execute();
    }
    public void setPlaylistName(int pIdToken ,String pKey ,int pIdPlaylist ,String pName ) {
        Log.e("DataManager", "setPlaylistName("+pIdToken+","+pKey+","+pName+")");
        String request = SERVER_PATH_U5_1+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist+"&nom="+pName ;
        new DownloadJSONAsyncTask(this , ACTION_U5_1 , request ).execute();
    }
    public void setPlaylistActive (int pIdToken ,String pKey ,int pIdPlaylist ,boolean pIsActive) {
        Log.e("DataManager", "setPlaylistActive("+pIdToken+","+pKey+","+pIsActive+")");
        String request = SERVER_PATH_U6+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U6 , request ).execute();
    }
    public void setPlaylistPublic (int pIdToken ,String pKey ,int pIdPlaylist ,boolean pIsPublic) {
        Log.e("DataManager", "setPlaylistPublic("+pIdToken+","+pKey+","+pIsPublic+")");
        String request = SERVER_PATH_U7+"idToken="+pIdToken+"&cle="+pKey+"&publique="+pIsPublic;
        new DownloadJSONAsyncTask(this , ACTION_U7 , request ).execute();
    }
    public void getMyPlaylists(int pIdToken  ,String pKey ,int pFirst ,int pLast) {
        Log.e("DataManager", "getMyPlaylists("+pIdToken+","+pKey+","+pFirst+","+pLast+")");
        String request = SERVER_PATH_A4+"idToken="+pIdToken+"&cle="+pKey+"&premier="+pFirst+"&dernier="+pLast;;
        new DownloadJSONAsyncTask(this , ACTION_A4 , request ).execute();
    }
    public void getPublicPlaylistList(int pIdToken ,String pKey ,int pFirst ,int pLast) {
        Log.e("DataManager", "getPublicPlaylistList("+pIdToken+","+pKey+","+pFirst+","+pLast+")");
        String request = SERVER_PATH_A5+"idToken="+pIdToken+"&cle="+pKey+"&premier="+pFirst+"&dernier="+pLast;;
        new DownloadJSONAsyncTask(this , ACTION_A5 , request ).execute();
    }
    //********************************************  AVATAR  ********************************************//
    public void getAvatar(int pIdToken ,String pKey ,int pIdAvatar ) {
        Log.e("DataManager", "getPublicPlaylistList("+pIdToken+","+pKey+","+pIdAvatar+")");
        String request = SERVER_PATH_A8+"idToken="+pIdToken+"&cle="+pKey+"&idAvatar="+pIdAvatar;;
        new DownloadJSONAsyncTask(this , ACTION_A8 , request ).execute();
    }
    public void getAvatarList(int pIdToken ,String pKey ,int pFirst ,int pLast) {
        Log.e("DataManager", "getPublicPlaylistList("+pIdToken+","+pKey+","+pFirst+","+pLast+")");
        String request = SERVER_PATH_A9+"idToken="+pIdToken+"&cle="+pKey+"&premier="+pFirst+"&dernier="+pLast;;
        new DownloadJSONAsyncTask(this , ACTION_A9 , request ).execute();
    }
    //********************************************  SONG PLAYLIST  ********************************************//

}


