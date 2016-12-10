package com.example.alexandrearsenault.projetfinal.Controler.List;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.R;

import java.util.List;

/**
 * Created by alexandrearsenault on 2016-12-03.
 */


public class ListRecycler extends RecyclerView.Adapter<ListRecycler.ViewHolder> {

    private List<Avatar> listAvatar;
    private List<ListesDeLecture> lisPlaylist;
    private List<Musique> listSong;

    private static final int ACT_AVATAR   = 1;
    private static final int ACT_PLAYLIST = 2;
    private static final int ACT_SONG     = 3;
    private static final int ACT_PLAYLIST_SONG     = 4;

    private int action;
    private HomeActivity activity;


    public ListRecycler(List<Object> pList , HomeActivity pActivity ) {
        this.activity = pActivity;

        if (!pList.isEmpty() && pList.get(0) instanceof Avatar ){
            this.action = ACT_AVATAR;
            this.listAvatar = (List<Avatar>)(List<?>) pList;
        }
        else if (!pList.isEmpty() && pList.get(0) instanceof ListesDeLecture ) {
            this.action = ACT_PLAYLIST;
            this.lisPlaylist = (List<ListesDeLecture>)(List<?>) pList;
        }
        else if (!pList.isEmpty() && pList.get(0) instanceof Musique){
             this.action = ACT_SONG;
             this.listSong = (List<Musique>)(List<?>) pList;
        }
        else{
            Log.e("ListRecycler.con(pList)", "pList == null || pList type invalid");
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id = 0;
        switch (action) {
            case ACT_AVATAR :
                id = R.layout.lay_avatar_content;
                break;
            case ACT_PLAYLIST :
                id = R.layout.lay_playlist_content;
                break;
            case  ACT_SONG :
                id = R.layout.lay_song_content;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(id, parent, false);
        return new ViewHolder(view , action);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        switch (action) {
            case ACT_AVATAR :
                final Avatar a = listAvatar.get(position);
                holder.avatar = a;
                holder.avatar_name.setText(a.getName() );
                //holder.avatar_image.setImageBitmap( a.getAvatar() );
                holder.view.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) {
                        activity.listControler.onDoneSelectingAvatar( a );
                } } );
                break;
            case ACT_PLAYLIST :
                final ListesDeLecture p= lisPlaylist.get(position);
                holder.playlist = p;
                holder.playlist_name.setText(p.getName() );
                holder.view.setOnClickListener(new View.OnClickListener() { @Override  public void onClick(View v) {
                    activity.listControler.onDoneSelectingPlaylist( p );
                } } );
                break;
            case  ACT_SONG :
                final Musique s = listSong.get(position);
                holder.song = s;
                holder.song_title.setText(s.getTitle() );
                holder.song_artist.setText(s.getArtist() );
                //holder.song_image.setImageBitmap( s.getCoverArt() );
                holder.view.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) {
                    activity.listControler.onDoneSelectingSong( s );
                } } );
        }
    }

    @Override
    public int getItemCount() {
        int count = -1;
        switch (action) {
            case ACT_AVATAR :
                count = listAvatar.size();
                break;
            case ACT_PLAYLIST :
                count = lisPlaylist.size();
                break;
            case  ACT_SONG :
                count = listSong.size();
        }
        return count;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public Avatar avatar;
        public TextView avatar_name;
        public ImageView avatar_image;

        public ListesDeLecture playlist;
        public TextView playlist_name;

        public Musique song;
        public TextView song_title;
        public TextView song_artist;
        public ImageView song_image;


        public ViewHolder(View view , int type) {
            super(view);
            this.view = view;

            switch (type) {
                case ACT_AVATAR :
                    avatar_name  = (TextView)  view.findViewById(R.id.txt_avatar_content_name);
                    avatar_image = (ImageView) view.findViewById(R.id.img_avatar_content_image);
                    break;
                case ACT_PLAYLIST :
                    playlist_name = (TextView) view.findViewById(R.id.txt_playlist_content_name);
                    break;
                case  ACT_SONG :
                    song_title = (TextView) view.findViewById(R.id.txt_song_content_title);
                    song_artist = (TextView) view.findViewById(R.id. txt_song_content_artist);
                    song_image = (ImageView) view.findViewById(R.id.img_song_content_image);
            }
        }
    }
}
