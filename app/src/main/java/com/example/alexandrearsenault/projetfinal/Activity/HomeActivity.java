package com.example.alexandrearsenault.projetfinal.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Controler.List.ListControler;
import com.example.alexandrearsenault.projetfinal.Controler.List.fgrList;
import com.example.alexandrearsenault.projetfinal.Controler.Playlist.PlaylistControler;
import com.example.alexandrearsenault.projetfinal.Controler.Song.SongControler;
import com.example.alexandrearsenault.projetfinal.Controler.User.UserControler;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrProfile;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrStart;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;
import com.example.alexandrearsenault.projetfinal.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener {

    public static final int ACT_NONE          =-1;
    public static final int ACT_CONNECT_AUTO  = 1;
    public static final int ACT_CONNECT       = 2;
    public static final int ACT_CREATE        = 3;
    public static final int ACT_START         = 4;
    public static final int ACT_PROFILE       = 5;
    public static final int ACT_AVATAR_LOAD   = 6;
    public static final int ACT_AVATAR_CREATE = 7;
    public static final int ACT_AVATAR_MODIFY = 8;
    public static final int ACT_PLAYLIST_MY = 9;
    public static final int ACT_SONG_MY = 10;
    public int action;




    //keep login identifiant
    public Utilisateur user;

    private DataManager dataMgr;
    public Drawer drawer ;

    public UserControler userControler;
    public SongControler songControler;
    public ListControler listControler;
    public PlaylistControler playlistControler;

    private GoogleApiClient client;


    //temporary for testing
    public static String emailTest = "tial1212@gmail.com";
    public static String pswdTest = "Alex123@";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //DRAWER
        DrawerLayout drawerLay = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawerLay, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLay.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout drawerLayout = (LinearLayout) navigationView.getHeaderView(0);
        drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                userControler.goToProfile();
            }
        });
        TextView alias = (TextView) drawerLayout.findViewById(R.id.nav_alias);
        alias.setText("AGDZ");
        drawer = new Drawer(drawerLayout );
        drawer.setDefaultInfo();


        //   Controlers
        dataMgr = DataManager.getInstance(this);
        userControler = new UserControler(this, dataMgr ,getSharedPreferences(UserControler.PREFS_USER,0) );
        songControler = new SongControler(this,dataMgr);
        listControler = new ListControler(this,dataMgr);
        playlistControler = new PlaylistControler(this,dataMgr);


        //try to connect

       userControler.tryInitialConnection();




        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_my_song:
                dataMgr.getMySongs(0,20);
                break;
            case R.id.nav_my_playlist:
                action = ACT_PLAYLIST_MY;
                dataMgr.getMyPlaylists(0,20);
                break;
            case R.id.nav_add_song:
                Log.e("menu", " add song");
                break;
            case R.id.nav_add_playlist:
                Log.e("menu", " add playlist");
                break;
            case R.id.nav_public_song:
                Log.e("menu", " public song");
                break;
            case R.id.nav_public_playlist:
                Log.e("menu", " public playlist");
                //dataMgr.getPublicPlaylist( ........ ........ .......... ........);
                break;
            default:
                Log.e("menu", " action undefined");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    public void changeFragment(Fragment pFragment) {
        Log.e("HomeActivity", "changeFragment");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_content, pFragment);
        ft.addToBackStack(null);
        ft.commit();
    }






    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        userControler.savePreferences();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();


    }
}
