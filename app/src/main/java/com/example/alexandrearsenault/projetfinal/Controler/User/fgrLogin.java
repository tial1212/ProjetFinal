package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrLogin extends Fragment {

    private View view;

    String email;
    String psdw;
    private HomeActivity activity;

    public interface OnLoginListener {
        void onLoginSend(String pEMail , String pMotDePasse);
    }

    public void setError(String action) {
        view.findViewById(R.id.lbl_login_error);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_login, container, false);
        activity =((HomeActivity)getActivity());


        Button bntLogin = (Button) view.findViewById(R.id.btn_login_login);
        bntLogin.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            login();
        } });
        Button bntCreate = (Button) view.findViewById(R.id.btn_login_create);
        bntCreate.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            HomeActivity activity = ((HomeActivity)getActivity());
            activity.action = HomeActivity.ACT_CREATE;
            activity.userControler.setFragment( new fgrCreate() );
        } });

        return view ;
    }

    private void login(){
        ((HomeActivity)getActivity()).action = HomeActivity.ACT_CONNECT;
        email = ((AutoCompleteTextView) view.findViewById(R.id.lbl_login_email)).getText().toString();
        psdw = ((AutoCompleteTextView) view.findViewById(R.id.lbl_login_pswd)).getText().toString();
        Log.i(email , psdw);
        //TODO testing purpose ONLY remove
        email = activity.emailTest ;
        psdw = activity.pswdTest;
        //TODO testing purpose ONLY remove


        Utilisateur uTemp = new Utilisateur();
        uTemp.setEMaill(email);
        activity.user = uTemp;
        activity.action = HomeActivity.ACT_CONNECT;
        DataManager.getInstance().login(email , psdw);
    }

}
