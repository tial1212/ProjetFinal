package com.example.alexandrearsenault.projetfinal.Controler.List;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.R;

import java.util.List;
import java.util.Objects;

import static java.lang.Math.E;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrList extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private HomeActivity activity;

    private static final int TYPE_AVATAR = 1;
    private static final int TYPE_SONG = 2;
    private static final int TYPE_PLAYLIST = 3;
    private int              type;

    private List<Musique> listSong;
    private List<ListesDeLecture> listPlaylist;
    private List<Avatar> listAvatar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = ((HomeActivity)getActivity());
        view = inflater.inflate(R.layout.lay_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rec_view_list);
        recyclerView.setLayoutManager( new LinearLayoutManager( activity.getApplicationContext()) );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        assert recyclerView != null;

        if  (type == TYPE_AVATAR) {
            recyclerView.setAdapter(new ListRecycler(  (List<Object>)(List<?>)listAvatar   , activity ));
        }
        else if (type == TYPE_PLAYLIST) {
            recyclerView.setAdapter(new ListRecycler(  (List<Object>)(List<?>)listPlaylist   , activity ));
        }
        else if (type == TYPE_SONG) {
            recyclerView.setAdapter(new ListRecycler(  (List<Object>)(List<?>)listSong   , activity ));
        }
        else{
            Log.e("fgrList.onCreateView()","NO CONTENT : set content before display fragment");
            return null;
        }
        return view ;
    }


    public void setListAvatar(List<Avatar> pList , HomeActivity pActivity){
        type = TYPE_AVATAR;
        listAvatar = pList;
    }

    public void setListPlaylist(List<ListesDeLecture> pList, HomeActivity pActivity){
        type = TYPE_PLAYLIST;
        listPlaylist = pList;
    }

    public void setListSong(List<Musique> pList , HomeActivity pActivity){
        type = TYPE_SONG;
        listSong= pList;
    }
}