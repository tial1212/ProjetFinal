package com.example.alexandrearsenault.projetfinal.Data;

import android.util.Log;

import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
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
 * Created by alexandrearsenault on 2016-12-07.
 */

public class ObjectConvertor {



    public static Integer jsonToInteger(String pJson){
        try {
            JSONObject jsonObjInteger = new JSONObject(pJson);
            Integer igr=null;
            try {  igr   = (Integer) jsonObjInteger.get("nb")  ; } catch (Exception e){ }

            return igr;

        } catch (JSONException e) {

            return  null;
        }
    }


    public static Token jsonToToken(String pJson){
        try {
            JSONObject jsonObjToken = new JSONObject(pJson);
            if (jsonObjToken == null ){ throw  new JSONException("NULL recieved"); }

            Token token = new Token();
            try {token.setId(           (Integer) jsonObjToken.get("id")        ); } catch (Exception e){ }
            try {token.setCaptchaStr(   (String)  jsonObjToken.get("captchaStr")); } catch (Exception e){ }
            try {token.setAction(       (String)  jsonObjToken.get("action")   ) ; } catch (Exception e){ }
            try {token.setEMail(        (String)  jsonObjToken.get("Courriel") ) ; } catch (Exception e){ }
            try {token.setEtat(         (Boolean) jsonObjToken.get("etat")     ) ; } catch (Exception e){ }
            try {token.setSalt(         (String)  jsonObjToken.get("salt")     ) ; } catch (Exception e){ }

            return token;

        } catch (JSONException e) {
            Log.e("DataMgr.jsonToToken() ","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }


    public static Utilisateur jsonToUser(String pJson){
        try {
            JSONObject  jsonObjUser = new JSONObject(pJson);
            if (jsonObjUser == null ){ throw  new JSONException("NULL recieved"); }

            Utilisateur user =new Utilisateur();
            try {user.setId(     (Integer) jsonObjUser.get("id")         ) ; } catch (Exception e){ }
            try {user.setAvatar( (Integer) jsonObjUser.get("Avatar")     ) ; } catch (Exception e){ }
            try {user.setDate(   (Date)    jsonObjUser.get("Date")       ) ; } catch (Exception e){ }
            try {user.setEMaill( (String)  jsonObjUser.get("Courriel")   ) ; } catch (Exception e){ }
            try {user.setActive( (Boolean) jsonObjUser.get("Actif")      ) ; } catch (Exception e){ }
            try {user.setPasowrd((String)  jsonObjUser.get("MotDePasse") ) ; } catch (Exception e){ }

            return user;
        } catch (JSONException e) {
            Log.e("DataManager.jsonToSong","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }
    public static Musique jsonToSong(String pJson){
        try {
            JSONObject  jsonObjSong = new JSONObject(pJson);
            if (jsonObjSong == null ){ throw  new JSONException("NULL recieved"); }

            Musique song =new Musique();
            try {song.setId(     (Integer) jsonObjSong.get("Id")         ) ; } catch (Exception e){ }
            try {song.setDate(    (Date)   jsonObjSong.get("Date")       ) ; } catch (Exception e){ }
            try {song.setOwner(   (Integer)jsonObjSong.get("Proprietaire")   ); ; } catch (Exception e){ }
            try {song.setTitle(   (String) jsonObjSong.get("Titre")      ) ; } catch (Exception e){ }
            try {song.setArtist( (String)  jsonObjSong.get("Artiste")    ) ; } catch (Exception e){ }
            try {song.setMusic(  (String)  jsonObjSong.get("Musique")    ) ; } catch (Exception e){ }
            try {song.setActive( (boolean) jsonObjSong.get("Publique")   ) ; } catch (Exception e){ }
            try {song.setPublic( (boolean) jsonObjSong.get("Active")     ) ; } catch (Exception e){ }

            return song;
        } catch (JSONException e) {
            Log.e("DataManager.jsonToSong","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }
    public static List<Musique> jsonToListSong(String pJson){

        try {
            JSONArray jsonArraySong =   new JSONArray(pJson);
            if (jsonArraySong == null || jsonArraySong.length() == 0 ){ throw  new JSONException("NULL recieved OR empty"); }
            List<Musique> listSong =  new ArrayList<Musique>();

            for (int i=0;i<jsonArraySong.length() ;i++){
                JSONObject  jsonObjSong = new JSONObject((String) jsonArraySong.get(i));
                Musique song =new Musique();
                try {song.setId(     (Integer) jsonObjSong.get("Id")         ) ; } catch (Exception e){ }
                try {song.setDate(    (Date)   jsonObjSong.get("Date")       ) ; } catch (Exception e){ }
                try {song.setOwner(   (Integer)jsonObjSong.get("Proprietaire")); } catch (Exception e){ }
                try {song.setTitle(   (String) jsonObjSong.get("Titre")      ) ; } catch (Exception e){ }
                try {song.setArtist( (String)  jsonObjSong.get("Artiste")    ) ; } catch (Exception e){ }
                try {song.setMusic(  (String)  jsonObjSong.get("Musique")    ) ; } catch (Exception e){ }
                try {song.setActive( (boolean) jsonObjSong.get("Publique")   ) ; } catch (Exception e){ }
                try {song.setPublic( (boolean) jsonObjSong.get("Active")     ) ; } catch (Exception e){ }
                listSong.add(song );
            }
            return listSong;
        } catch (JSONException e) {
            Log.e("DataMgr.jsonToListSong","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }
    public static ListesDeLecture jsonToPlaylist(String pJson){
        try {
            JSONObject  jsonObjPlaylist = new JSONObject(pJson);
            if (jsonObjPlaylist == null ){ throw  new JSONException("NULL recieved"); }

            ListesDeLecture playlist =new ListesDeLecture();
            try {playlist.setId(     (Integer) jsonObjPlaylist.get("Id")         ) ; } catch (Exception e){ }
            try {playlist.setDate(   (Date)    jsonObjPlaylist.get("Date")       ) ; } catch (Exception e){ }
            try {playlist.setName(   (String)  jsonObjPlaylist.get("Titre")      ) ; } catch (Exception e){ }
            try {playlist.setOwner(  (Integer) jsonObjPlaylist.get("Proprietaire")); } catch (Exception e){ }
            try {playlist.setActive( (boolean) jsonObjPlaylist.get("Publique")   ) ; } catch (Exception e){ }
            try {playlist.setPublic( (boolean) jsonObjPlaylist.get("Active")     ) ; } catch (Exception e){ }

            return playlist;
        } catch (JSONException e) {
            Log.e("DataMgr.jsonToPlaylist","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }

    }
    public static List<ListesDeLecture> jsonToListPlaylist(String pJson){
        try {
            JSONArray jsonArrayPlaylist =   new JSONArray(pJson);
            if (jsonArrayPlaylist == null || jsonArrayPlaylist.length() == 0 ){ throw  new JSONException("NULL recieved OR empty"); }
            List<ListesDeLecture> listPlaylist =  new ArrayList<ListesDeLecture>();

            for (int i=0;i<jsonArrayPlaylist.length() ;i++) {
                JSONObject jsonObjPlaylist = new JSONObject((String) jsonArrayPlaylist.get(i));
                ListesDeLecture playlist =new ListesDeLecture();
                try {playlist.setId(     (Integer) jsonObjPlaylist.get("Id")         ) ; } catch (Exception e){ }
                try {playlist.setDate(   (Date)    jsonObjPlaylist.get("Date")       ) ; } catch (Exception e){ }
                try {playlist.setName(   (String)  jsonObjPlaylist.get("Titre")      ) ; } catch (Exception e){ }
                try {playlist.setOwner(  (Integer) jsonObjPlaylist.get("Proprietaire")); } catch (Exception e){ }
                try {playlist.setActive( (boolean) jsonObjPlaylist.get("Publique")   ) ; } catch (Exception e){ }
                try {playlist.setPublic( (boolean) jsonObjPlaylist.get("Active")     ) ; } catch (Exception e){ }

                listPlaylist.add( playlist );
            }
            return listPlaylist;
        } catch (JSONException e) {
            Log.e("DataMgr.jsonToListPlayl","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }


    public static Avatar jsonToAvatar(String pJson){
        try {
            JSONObject jsonObjAvatar = new JSONObject(pJson);
            if (jsonObjAvatar == null ){ throw  new JSONException("NULL recieved"); }

            Avatar avatar = new Avatar();
            try {avatar.setId(           (Integer) jsonObjAvatar.get("id")       ) ; } catch (Exception e){ }
            try {avatar.setName(         (String)  jsonObjAvatar.get("Nom")      ) ; } catch (Exception e){ }
            try {avatar.setAvatar(       (String)  jsonObjAvatar.get("Avatar")   ) ; } catch (Exception e){ }

            return avatar;

        } catch (JSONException e) {
            Log.e("DataMgr.jsonToAvatar() ","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }

    public static List<Avatar> jsonToListAvatar(String pJson){
        try {
            JSONArray jsonArrayPlaylist =   new JSONArray(pJson);
            if (jsonArrayPlaylist == null || jsonArrayPlaylist.length() == 0 ){ throw  new JSONException("NULL recieved OR empty"); }
            List<Avatar> listAvatar =  new ArrayList<Avatar>();

            for (int i=0;i<jsonArrayPlaylist.length() ;i++) {
                JSONObject jsonObjAvatar = new JSONObject((String) jsonArrayPlaylist.get(i));
                Avatar avatar = new Avatar();
                try {avatar.setId(           (Integer) jsonObjAvatar.get("id")       ) ; } catch (Exception e){ }
                try {avatar.setName(         (String)  jsonObjAvatar.get("Nom")      ) ; } catch (Exception e){ }
                try {avatar.setAvatar(       (String)  jsonObjAvatar.get("Avatar")   ) ; } catch (Exception e){ }

                listAvatar.add( avatar );
            }
            return listAvatar;
        } catch (JSONException e) {
            Log.e("DataMgr.jsonToListAvata","ERROR CASTING");
            e.printStackTrace();
            return  null;
        }
    }


}