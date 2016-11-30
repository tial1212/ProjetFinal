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
    private static String SERVER_PATH = "http://localhost:8080";
    private static String SERVER_PATH_A1    = SERVER_PATH + "/service/token/getActionToken?";
    private static String SERVER_PATH_A2    = SERVER_PATH + "/service/utilisateur/login?";
    private static String SERVER_PATH_A3    = SERVER_PATH + "/service/utilisateur/logoff?";
    private static String SERVER_PATH_P1    = SERVER_PATH + "/service/utilisateur/createUser?";
    private static String SERVER_PATH_U2    = SERVER_PATH + "/service/utilisateur/confirmCreateUser?";
    private static String SERVER_PATH_U1    = SERVER_PATH + "/service/utilisateur/modify?";
    private static String SERVER_PATH_U8    = SERVER_PATH + "/service/musique/createSong?";
    private static String SERVER_PATH_U9_U10= SERVER_PATH + "/service/musique/getPrivateSong?";
    private static String SERVER_PATH_P3_P4 = SERVER_PATH + "/service/musique/getPublicSong";
    private static String SERVER_PATH_U11   = SERVER_PATH + " /service/musique/modify?";
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
        Log.e("DataManager", "getToken("+pEMail+")");
        String request = SERVER_PATH +"getActionToken?email="+pEMail;  //FIXME
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
            Log.e("Film ERROR", " movie failled");
            if (e instanceof JSONException) {
                Log.e("ERROR", "JSON cast");
            } else if (e instanceof Exception) {
                Log.e("ERROR", "Unknown Error");
            }
            return null;
        }
    }

    public void getActionToken(String pEMail){
        Log.e("DataManager", "getActionToken("+pEMail+")");
        String request = SERVER_PATH_P1 ;
        new DownloadJSONAsyncTask(this , ACTION_P1 , request );
    }
    public void login(String pCourriel, String pMotDePasse) {

    }
    public void logoff(int pIdToken  ,String pKey ,String pCourriel) {

    }
    public void createUser (String pAalias ,String pMotDePasse ,String pCourriel ,int pIdAvatar) {

    }
    public void confirmCreateUser(int pIdToken ,String pCaptchaVal) {

    }
    public void modifierUser(int pIdToken,String pKey,String pEMaill,String pPasword,String pAlias,  int  pIdAvatar) {

    }
    public void createSong(int pIdToken ,String pKey ,String pTitle ,String pArtist ,String pMusic ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {

    }
    public void getPrivateSong(int pIdToken ,String pKey ,int pIdSong ) {

    }
    public void getPublicSong (int pIdToken ,String pKey ,int pIdSong ) {

    }
    public void modifySong (int pIdToken,String pKey ,int pIdSong,String pTitle ,String pArtist ,String pCoverArt ,boolean pIsPublic ,boolean pIsActive) {

    }
    public void setActiveSong(int pIdToken ,String pKey ,int pIdSong ,boolean pActive  ) {

    }
    public void setPublicSong(int pIdSong ,String pIdToken ,boolean pIsPublic ) {

    }
    public void createPlaylist(int pIdToken ,String pKey ,String pName ,boolean pIsPublic ,boolean pIsActive) {

    }
    public void getPrivatePlaylist(int pIdToken ,String pKey ,int pIdPlaylist ) {

    }
    public void modifyPlaylist(int pIdToken,String pKey,String pName ,boolean pIsPublic ,boolean pIsActive ) {

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


