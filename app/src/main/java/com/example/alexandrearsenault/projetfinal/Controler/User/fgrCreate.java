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
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrCreate
        extends
            Fragment {


    private View view;
    private OnCreateListener listener;


    public interface OnCreateListener {
        void onCreateSend(String pAlias , String pMotDePasse , String pCourriel , int pIdAvatar);
    }


    public void setError(String pError) {
        ((TextView) view.findViewById(R.id.lbl_create1_error)).setText(pError);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_create1, container, false);

        Button bntCreer = (Button) view.findViewById(R.id.btn_create1_create);
        bntCreer.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            validateBeforeSend();
        } });
        Button bntAvatar = (Button) view.findViewById(R.id.btn_create1_avatar);
        bntAvatar.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {

        } });

        CheckBox chk = (CheckBox) view.findViewById(R.id.chk_create1_avatar);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        } }  );

        return view ;
    }



    public void validateBeforeSend(){
        String email = ((EditText) view.findViewById(R.id.txt_create1_email)).getText().toString();
        String alias = ((EditText) view.findViewById(R.id.txt_create1_alias)).getText().toString();
        String psdw1 = ((EditText) view.findViewById(R.id.txt_create1_pswd1)).getText().toString();
        String psdw2 = ((EditText) view.findViewById(R.id.txt_create1_pswd2)).getText().toString();

        //TODO remove testing only
        alias = "alexqqq";
        psdw1 = psdw2 = "Alex123@";
        email = "tial1212@gmail.com";

        boolean okEmail = Utilisateur.validateEMaill(email);
        boolean okAlias = Utilisateur.validateAlias(alias);
        boolean okPswd1 = Utilisateur.validatePasowrd(psdw1);
        boolean okPswdMatch = psdw1.equals(psdw2 );
        if (okEmail && okAlias && okPswd1 && okPswdMatch ){
            DataManager.getInstance().createUser(alias,psdw1,email,1);//FIXME
        }else{
            String error = (okEmail     ?"":" Email non valide");
            error+=        (okAlias     ?"":" Alias non valide");
            error+=        (okPswd1     ?"":" Mot de passe non valide");
            error+=        (okPswd1?   (okPswdMatch ?"":" Mot de passe non identique"):"" );
            ((TextView) view.findViewById(R.id.lbl_create1_error)).setText(error);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCreateListener) {
            listener = (OnCreateListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement 'OnCreateListener' ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
