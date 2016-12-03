package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrLogin extends Fragment {

    private View view;

    String email;
    String psdw;

    public interface OnLoginListener {
        void onLoginSend(String pEMail , String pMotDePasse);
    }

    public void setError(String action) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_login, container, false);

        Button bntLogin = (Button) view.findViewById(R.id.btn_login_login);
        bntLogin.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            login();
        } });
        Button bntCreate = (Button) view.findViewById(R.id.btn_login_create);
        bntCreate.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {

        } });

        return view ;
    }


    private void login(){
        email = ((EditText) view.findViewById(R.id.lbl_login_email)).getText().toString();
        psdw = ((EditText) view.findViewById(R.id.lbl_login_pswd)).getText().toString();
        DataManager.getInstance().login(email , psdw);
    }



}
