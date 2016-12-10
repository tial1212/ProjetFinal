package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrStart extends Fragment {

    HomeActivity        activity;

    private View view;
    private Button bntConnect;
    private Button bntCreate;
    private Button bntWatch;


    public interface OnPageLoginListener {
        void onCreateSend(String pAlias, String pMotDePasse, String pCourriel, int pIdAvatar);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_start, container, false);
        activity = (HomeActivity) getActivity();

        bntConnect = (Button) view.findViewById(R.id.btn_start_connect);
        bntConnect.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            ((HomeActivity) getActivity()).userControler.setFragment(new fgrLogin() );
        } });
        bntCreate = (Button) view.findViewById(R.id.btn_start_create);
        bntCreate.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            activity.userControler.setFragment(new fgrCreate() );
        } });
        bntWatch = (Button) view.findViewById(R.id.btn_start_watch);
        bntWatch.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            activity.toaster.message("Sélectioner une action depuis le tiroir pour commencer");
            bntWatch.setVisibility(LinearLayout.GONE);

        } });

        return view ;
    }
}
