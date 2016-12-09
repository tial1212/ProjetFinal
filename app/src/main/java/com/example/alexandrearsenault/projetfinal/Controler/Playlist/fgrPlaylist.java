package com.example.alexandrearsenault.projetfinal.Controler.Playlist;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.List.ListRecycler;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.R;

import java.util.List;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrPlaylist extends Fragment {


    private ListesDeLecture playlist;

    private View view;
    private boolean isEditing;
    private List<Musique> listSong;
    private RecyclerView recyclerView;
    private HomeActivity activity;
    private TextView errorLbl ;
    private EditText name;
    private Button bntEdit;
    private EditText edTextName;
    private LinearLayout layEdit;

    private Boolean canEdit;

    public void setPlaylist(ListesDeLecture pPlaylist) {
        playlist = pPlaylist;
    }

    public void setListSong(List<Musique> pList){
        listSong= pList;
    }
    public void setCanEdit(boolean pCanEdit){
        canEdit= pCanEdit;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if  (listSong != null && playlist != null && canEdit!= null) {
            view = inflater.inflate(R.layout.lay_playlist, container, false);
            activity = (HomeActivity) getActivity();

            recyclerView = (RecyclerView) view.findViewById(R.id.rec_playlist_list);
            recyclerView.setLayoutManager( new LinearLayoutManager( activity.getApplicationContext()) );
            recyclerView.setItemAnimator( new DefaultItemAnimator() );
            recyclerView.setAdapter(new ListRecycler(  (List<Object>)(List<?>) listSong   , activity  ) );




            if(canEdit){


                bntEdit = (Button) view.findViewById(R.id.btn_playlist_edit);
                bntEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
                    isEditing= !isEditing;
                    edit();
                } });
                name = (EditText) view.findViewById(R.id.txt_playlist_name);
                errorLbl = (TextView)   view.findViewById(R.id.lbl_playlist_error);

                isEditing= false;

                name.setText(playlist.getName() );
                name.setEnabled(true);

            }else {
                layEdit = (LinearLayout) view.findViewById(R.id.lay_playlist_edit);
                layEdit.setVisibility(LinearLayout.GONE);
            }

            return view ;
        }
        else{
            Log.e("fgrPlaylist.onCrtView()","NO CONTENT : set listSong && playlist before display fragment");
            return null;
        }


    }

    public void edit(){
        bntEdit.setText(  (isEditing?"enregistrer":"modifier")  );  //TODO use res.String   ->>  lbl_playlist_btn_done lbl_playlist_btn_edit


        if (!isEditing){
            //try saving
            String name  = this.name.getText().toString();
            boolean publiqe = ((Switch) view.findViewById(R.id.swt_playlist_public)).isChecked();
            boolean active = ((Switch) view.findViewById(R.id.swt_playlist_active)).isChecked();

            if ( validate( name ) ){
                DataManager.getInstance().modifyPlaylist(playlist.getId() ,name , publiqe , active );
            }
        }
    }

    private boolean validate(String pName){
        boolean okName = ListesDeLecture.validateName(pName);
        boolean okFinal = okName;
        errorLbl.setText( (okFinal ? "" : ((okName ?"":"nom invalide " )  )));
        errorLbl.setTextColor(  ( okFinal ? Color.BLACK : Color.RED) );
        return  okFinal;
    }

}
