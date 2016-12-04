package com.example.alexandrearsenault.projetfinal.Controler.List;

import android.app.Fragment;
import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_list, container, false);
        return view ;
    }

    private void showList(List<Objects> pList){
        Log.e("fgrList","showList()");

        if ( !pList.isEmpty() && ( ((Object) pList.get(0)) instanceof Avatar
                || ((Object) pList.get(0)) instanceof ListesDeLecture
                || ((Object) pList.get(0)) instanceof Musique ) ) {
            Log.e("fgrList","showList()->type de liste inconue");
            return;
        }

        getActivity().setContentView(R.layout.lay_list);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.rec_view_list); //FIXME
        assert recyclerView != null;
        if(!pList.isEmpty() && ((Object) pList.get(0)) instanceof Avatar){
            recyclerView.setAdapter(new ListRecycler(  (List<Avatar>)((List<?>) pList ) , ((HomeActivity)getActivity()) ));
        }
        else if(!pList.isEmpty() && ((Object) pList.get(0)) instanceof ListesDeLecture){
            recyclerView.setAdapter(new ListRecycler(  (List<ListesDeLecture>)((List<?>) pList ) , ((HomeActivity)getActivity()) , false ));
        }
        else if(!pList.isEmpty() && ((Object) pList.get(0)) instanceof Musique ){
            recyclerView.setAdapter(new ListRecycler(  (List<Musique>)((List<?>) pList ) , ((HomeActivity)getActivity()) , 1 ));
        }
    }
}