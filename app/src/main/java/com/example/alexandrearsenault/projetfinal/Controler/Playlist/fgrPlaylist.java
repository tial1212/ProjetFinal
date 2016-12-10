package com.example.alexandrearsenault.projetfinal.Controler.Playlist;

import android.app.Fragment;
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
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.List.ListControler;
import com.example.alexandrearsenault.projetfinal.Controler.List.ListRecycler;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
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
    private Button btnEdit;
    private LinearLayout layEdit;

    private Boolean canEdit;
    private Switch swtPublic;
    private Switch swtActive;

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
        if  (playlist != null && listSong != null && canEdit!= null) {

            view = inflater.inflate(R.layout.lay_playlist, container, false);
            activity = (HomeActivity) getActivity();

            activity.listControler.action = (canEdit? ListControler.ACTION_MY_PLAYLIST:ListControler.ACTION_PUBLIC_PLAYLIST);
            recyclerView = (RecyclerView) view.findViewById(R.id.rec_playlist_list);
            recyclerView.setLayoutManager( new LinearLayoutManager( activity.getApplicationContext()) );
            recyclerView.setItemAnimator( new DefaultItemAnimator() );
            recyclerView.setAdapter(new ListRecycler(  (List<Object>)(List<?>) listSong   , activity  ) );

            errorLbl = (TextView)   view.findViewById(R.id.lbl_playlist_error);
            name = (EditText) view.findViewById(R.id.txt_playlist_name);
            name.setClickable(false);
            name.setText(playlist.getName() );
            swtPublic = ((Switch) view.findViewById(R.id.swt_playlist_public));
            swtPublic.setChecked(playlist.isPublic() );
            swtPublic.setClickable(false);
            swtActive = ((Switch) view.findViewById(R.id.swt_playlist_active));
            swtActive.setChecked(playlist.isActive() );
            swtActive.setClickable(false);

            activity.listControler.action = ListControler.ACTION_SONG;

            if(canEdit){
                btnEdit = (Button) view.findViewById(R.id.btn_playlist_edit);
                btnEdit.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
                    isEditing= !isEditing;
                    edit();
                } });
                isEditing= false;
            }else {
                layEdit = (LinearLayout) view.findViewById(R.id.lay_playlist_edit);
                layEdit.setVisibility(LinearLayout.GONE);
            }

            return view ;
        }
        else{
            Log.e("fgrPlaylist.onCrtView()","these MUST be set before displaying fragment --> playlist="+(playlist == null ?"UNSET":"SET" )+" , listSong="+(listSong == null ?"UNSET":"SET" )+" , canEdit="+(canEdit == null ?"UNSET":"SET" ) );
            return null;
        }
    }

    public void edit(){
        btnEdit.setText(  (isEditing?  R.string.lbl_playlist_btn_done : R.string.lbl_playlist_btn_edit)  );
        name.setClickable(false);
        swtPublic.setClickable(isEditing);
        swtActive.setClickable(isEditing);

        if (!isEditing){
            //try saving
            String name  = this.name.getText().toString();
            if ( validate( name ) ){
                DataManager.getInstance().modifyPlaylist(playlist.getId() ,name , swtPublic.isChecked() , swtActive.isChecked() );
            }
        }
    }

    private boolean validate(String pName){
        boolean okName = ListesDeLecture.validateName(pName);
        boolean okFinal = okName;
        errorLbl.setText( (okFinal ? "" : ((okName ?"":"nom invalide " )  )));
        return  okFinal;
    }


    public void onModifyPlaylistAnswer( Token pToken) {
        if ( pToken != null && pToken.getEtat() ) {
            activity.toaster.okModify( ListesDeLecture.class.toString() );
            return;
        }
        else {
            //show error message & revert
            if (pToken == null ){
                activity.toaster.errorRequest();
            }
            else{
                activity.toaster.errorModify( pToken );
            }
            name.setClickable(false);
            swtPublic.setChecked(playlist.isPublic());
            swtPublic.setChecked(playlist.isPublic());
            name.setText(playlist.getName() );
        }
    }

}
