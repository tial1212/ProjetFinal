package com.example.alexandrearsenault.projetfinal.Data;

import android.util.Log;

import com.example.alexandrearsenault.projetfinal.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Modele.Token;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by alexandrearsenault on 2016-09-13.
 */
public class DataManager {
    private static DataManager instance = null;
    private static String SERVER_PATH = "http://424t.cgodin.qc.ca:8480//rest-example/";
    private static String SERVER_PATH_A1    = SERVER_PATH + "/service/token/getActionToken?";
    private static String SERVER_PATH_A2    = SERVER_PATH + "/service/utilisateur/login?";
    private static String SERVER_PATH_A3    = SERVER_PATH + "/service/utilisateur/logoff?";
    private static String SERVER_PATH_P1    = SERVER_PATH + "/service/utilisateur/createUser?";
    private static String SERVER_PATH_U2    = SERVER_PATH + "/service/utilisateur/confirmCreateUser?";
    private static String SERVER_PATH_U1    = SERVER_PATH + "/service/utilisateur/modify?";
    private static String SERVER_PATH_U8    = SERVER_PATH + "/service/musique/createSong?";
    private static String SERVER_PATH_U9_U10= SERVER_PATH + "/service/musique/getPrivateSong?";
    private static String SERVER_PATH_P3_P4 = SERVER_PATH + "/service/musique/getPublicSong";
    private static String SERVER_PATH_U11   = SERVER_PATH + "/service/musique/modify?";
    private static String SERVER_PATH_U12   = SERVER_PATH + "/service/musique/setActive?";
    private static String SERVER_PATH_U13   = SERVER_PATH + "/service/musique/setPublic?";
    private static String SERVER_PATH_U3    = SERVER_PATH + "/service/listeLecture/createPlaylist?";
    private static String SERVER_PATH_P2    = SERVER_PATH + "/service/listeLecture/getPublicPlaylist?";
    private static String SERVER_PATH_U4    = SERVER_PATH + "/service/listeLecture/getPrivatePlaylist?";
    private static String SERVER_PATH_U5    = SERVER_PATH + "/service/listeLecture/modify?";
    private static String SERVER_PATH_U5_1  = SERVER_PATH + "/service/listeLecture/setPlaylistName?";
    private static String SERVER_PATH_U6    = SERVER_PATH + "/service/listeLecture/setPlaylistActive?";
    private static String SERVER_PATH_U7    = SERVER_PATH + "/service/listeLecture/setPlaylistName?";
    private static String SERVER_PATH_A4    = SERVER_PATH + "/service/listeLecture/getMyPlaylists?";
    private static String SERVER_PATH_A5    = SERVER_PATH + "/service/listeLecture/getPublicPlaylistList?";
    private static String SERVER_PATH_A6    = SERVER_PATH + "/service/musiqu/getMySongs?";
    private static String SERVER_PATH_A7    = SERVER_PATH + "/service/musiqu/getPublicSongsList?";
    //private static String SERVER_PATH_U14   = SERVER_PATH + "";
    //private static String SERVER_PATH_U15   = SERVER_PATH + "";


    private static final int ACTION_A1      = 1;
    private static final int ACTION_A2      = 2;
    private static final int ACTION_A3      = 3;
    private static final int ACTION_P1      = 4;
    private static final int ACTION_U2      = 5;
    private static final int ACTION_U1      = 6;
    private static final int ACTION_U8      = 7;
    private static final int ACTION_U9_U10  = 8;
    private static final int ACTION_P3_P4   = 9;
    private static final int ACTION_U11     = 10;
    private static final int ACTION_U12     = 11;
    private static final int ACTION_U13     = 12;
    private static final int ACTION_U3      = 13;
    private static final int ACTION_P2      = 14;
    private static final int ACTION_U4      = 15;
    private static final int ACTION_U5      = 16;
    private static final int ACTION_U5_1    = 17;
    private static final int ACTION_U6      = 18;
    private static final int ACTION_U7      = 19;
    private static final int ACTION_A4      = 20;
    private static final int ACTION_A5      = 21;
    private static final int ACTION_A6      = 22;
    private static final int ACTION_A7      = 23;
    //private static final int ACTION_U14     = 24;
    //private static final int ACTION_U15     = 25;


    private HomeActivity activity;

    private int movieCount;


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



    public void login(String pCourriel, String pMotDePasse) {
        Log.e("DataManager", "login("+pCourriel+","+pMotDePasse+")");
        String request = SERVER_PATH_A2+"courriel="+pCourriel+"&motDePasse="+pMotDePasse;
        new DownloadJSONAsyncTask(this , ACTION_A2 , request );
    }
    public void logoff(int pIdToken  ,String pKey ,String pCourriel) {;
        Log.e("DataManager", "login("+pIdToken+","+pKey+","+pCourriel+")");
        String request = SERVER_PATH_A3+"idToken="+pIdToken+"&cle="+pKey+"&courriel="+pCourriel;
        new DownloadJSONAsyncTask(this , ACTION_A3 , request );
    }
    public void createUser (String pAalias ,String pMotDePasse ,String pCourriel ,int pIdAvatar) {
        Log.e("DataManager", "createUser("+pAalias+","+pMotDePasse+","+pCourriel+","+pIdAvatar+")");
        String request = SERVER_PATH_P1+"alias="+pAalias+"&motDePasse="+pMotDePasse+"&courriel="+pCourriel+"&avatar="+pIdAvatar;
        new DownloadJSONAsyncTask(this , ACTION_P1 , request );

    }
    public void confirmCreateUser(int pIdToken ,String pCaptchaVal) {
        Log.e("DataManager", "confirmCreateUser("+pIdToken+","+pCaptchaVal+")");
        String request = SERVER_PATH_U2+"idToken="+pIdToken+"&captchaVal="+pCaptchaVal;
        new DownloadJSONAsyncTask(this , ACTION_U2 , request );
    }
    public void modifierUser(int pIdToken,String pKey,String pEMaill,String pPasword,String pAlias,  int  pIdAvatar) {
        Log.e("DataManager", "modifierUser("+pIdToken+","+pKey+","+pEMaill+","+pPasword+","+pAlias+","+pIdAvatar+")");
        String request = SERVER_PATH_U1+"idToken="+pIdToken+"&cle="+pKey+"&courriel="+pEMaill+"&motDePasse="+pPasword+"&alias="+pAlias+"&avatar="+pIdAvatar;
        new DownloadJSONAsyncTask(this , ACTION_U1 , request );
    }
    public void createSong(int pIdToken ,String pKey ,String pTitle ,String pArtist ,String pMusic ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "createSong("+pIdToken+","+pKey+","+pArtist+","+pMusic+","+pCoverArt+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U8+"idToken="+pIdToken+"&cle="+pKey+"&titre="+pTitle+"&artiste="+pArtist+"&musique="+pMusic+"&coverArt="+pCoverArt+"&public="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U8 , request );
    }
    public void getPrivateSong(int pIdToken ,String pKey ,int pIdSong ) {
        Log.e("DataManager", "getPrivateSong("+pIdToken+","+pKey+","+pIdSong+")");
        String request = SERVER_PATH_U9_U10+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong;
        new DownloadJSONAsyncTask(this , ACTION_U9_U10 , request );

    }
    public void getPublicSong (int pIdToken ,String pKey ,int pIdSong ) {
        Log.e("DataManager", "getPublicSong("+pIdToken+","+pKey+","+pIdSong+")");
        String request = SERVER_PATH_P3_P4+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong;
        new DownloadJSONAsyncTask(this , ACTION_P3_P4 , request );

    }
    public void modifySong (int pIdToken,String pKey ,int pIdSong,String pTitle ,String pArtist ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "modifySong("+pIdToken+","+pKey+","+pIdSong+","+pTitle+","+pArtist+","+pCoverArt+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U11+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong+"&titre="+pTitle+"&artiste="+pArtist+"&vignette="+pCoverArt+"&publique="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U11 , request );

    }
    public void setActiveSong(int pIdToken ,String pKey ,int pIdSong ,boolean pActive  ) {
        Log.e("DataManager", "setActiveSong("+pIdToken+","+pKey+","+pIdSong+","+pActive+")");
        String request = SERVER_PATH_U12+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong+"&active="+pActive;
        new DownloadJSONAsyncTask(this , ACTION_U12 , request );

    }
    public void setPublicSong(String pIdToken ,String pKey , int pIdSong ,boolean pIsPublic ) {
        Log.e("DataManager", "setPublicSong("+pIdToken+","+pKey+","+pIdSong+","+pIsPublic+")");
        String request = SERVER_PATH_U13+"idToken="+pIdToken+"&cle="+pKey+"&idSong="+pIdSong+"&publique="+pIsPublic;
        new DownloadJSONAsyncTask(this , ACTION_U13 , request );
    }
    public void createPlaylist(int pIdToken ,String pKey ,String pName ,boolean pIsPublic ,boolean pIsActive) {
        Log.e("DataManager", "createPlaylist("+pIdToken+","+pKey+","+pName+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U3+"idToken="+pIdToken+"&cle="+pKey+"&cle="+pKey+"&nom="+pName+"&publique="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U3 , request );
    }
    public void getPublicPlaylist(int pIdToken ,String pKey ,int pIdPlaylist ) {
        Log.e("DataManager", "getPublicPlaylist("+pIdToken+","+pKey+","+pIdPlaylist+")");
        String request = SERVER_PATH_P2+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist;
        new DownloadJSONAsyncTask(this , ACTION_P2 , request );

    }
    public void getPrivatePlaylist(int pIdToken ,String pKey ,int pIdPlaylist ) {
        Log.e("DataManager", "getPrivatePlaylist("+pIdToken+","+pKey+","+pIdPlaylist+")");
        String request = SERVER_PATH_U4+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist;
        new DownloadJSONAsyncTask(this , ACTION_U4 , request );
    }
    public void modifyPlaylist(int pIdToken ,String pKey ,int	pIdPlaylist ,String pName ,boolean pIsPublic ,boolean pIsActive ) {
        Log.e("DataManager", "modifyPlaylist("+pIdToken+","+pKey+","+pName+","+pIsPublic+","+pIsActive+")");
        String request = SERVER_PATH_U5+"idToken="+pIdToken+"&cle="+pKey+"&idPlaylist="+pIdPlaylist+"&nom="+pName+"&publique="+pIsPublic+"&active="+pIsActive;
        new DownloadJSONAsyncTask(this , ACTION_U5 , request );
    }
    public void setPlaylistName(int pIdToken ,String pKey ,String pName ) {

    }
    public void setPlaylistActive (int pIdToken ,String pKey ,boolean pIsActive) {

    }
    public void setPlaylistPublic (int pIdToken ,String pKey ,boolean pIsPublic) {

    }
    public void getMyPlaylists(int idToken  ,String pKey ,int pFirst ,int pLast) {

    }
    public void getPublicPlaylistList(int pIdToken ,String pKey ,int pFirst ,int pLast) {

    }
    public void getMySongs(int pIdToken,String pKey ){

    }
    public void getPublicSongsList(int pIdToken,String pKey ,int pFirst ,int pLast){

    }


    getPublicPlaylistList()
    public void onDoneDownloadingJson(String pResultJSON, int pAction) {


        switch (pAction){
            case ACTION_A1:
                //getActionToken()
            break;
            case ACTION_A2:
                //login()
                break;
            case ACTION_A3:
                //logoff()
                break;
            case ACTION_P1:
                //createUser()
                break;
            case ACTION_U2:
                //confirmCreateUser()
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
                //getPrivatePlaylist()
                break;
            case ACTION_U5:
                //modifyPlaylist()
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
}


