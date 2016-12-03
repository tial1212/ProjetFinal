package com.example.alexandrearsenault.projetfinal.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

import com.example.alexandrearsenault.projetfinal.Controler.Song.SongControler;
import com.example.alexandrearsenault.projetfinal.Controler.User.UserControler;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrCreate;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrLogin;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrProfile;
import com.example.alexandrearsenault.projetfinal.Controler.User.fgrStart;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.Modele.Musique;
import com.example.alexandrearsenault.projetfinal.Modele.Token;
import com.example.alexandrearsenault.projetfinal.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener {


    //keep login identifiant
    public String email;
    public String pswd;
    private boolean isUserConnected;

    //keep requested object
    private Token actionToken;
    private Musique song;
    private ListesDeLecture playlist;
    private List<Musique> playlistContent;

    private DataManager dataMgr;
    public UserControler userControler;
    public SongControler songControler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //DRAWER
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout navHead = (LinearLayout) navigationView.getHeaderView(0);
        navHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                goToProfile();
            }
        });


        //   Controlers
        dataMgr = DataManager.getInstance(this);
        userControler = new UserControler(this, dataMgr);
        songControler = new SongControler(this,dataMgr);


        //try to connect
        isUserConnected = false;
        email = "tial1212@gmail.com";
        pswd = "Alex123@";
        userControler.onLoginSend(email, pswd);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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
                Log.e("menu", " my song");
                break;
            case R.id.nav_my_playlist:
                Log.e("menu", " my playlist");
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
                //
                break;
            default:
                Log.e("menu", " other");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void goToProfile() {
        Log.e("goToProfile", " connected" + isUserConnected);
        if (isUserConnected) {
            userControler.setFgr(new fgrProfile());
        } else {
            fgrStart fgr = new fgrStart();
            fgr.setActivity(this);
            userControler.setFgr(fgr);
        }
    }


    public void changeFragment(Fragment pFragment) {
        Log.e("HomeActivity", "changeFragment");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_content, pFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void connect(boolean pIsConnected) {

        this.isUserConnected = pIsConnected;
        if ( isUserConnected ) {

        } else {

        }
    }









    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}