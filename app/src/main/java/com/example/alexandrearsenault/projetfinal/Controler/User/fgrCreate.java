package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.List.fgrList;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Modele.Avatar;
import com.example.alexandrearsenault.projetfinal.Modele.Utilisateur;
import com.example.alexandrearsenault.projetfinal.R;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrCreate
        extends
        Fragment {

    private static final int ID_AVATAR_DEFAULT      = -4;    //FIXME
    private static final String NAME_AVATAR_DEFAULT = "TITI"; //FIXME

    private View view;
    private int selectedAvatar = ID_AVATAR_DEFAULT;
    private boolean chkChecked = false;

    Button bntAvatar;
    private TextView errorText;
    private HomeActivity activity;
    private EditText email;
    private EditText psdw1;
    private EditText psdw2;
    private EditText alias;


    public void setError(String pError) {
        Log.e("setError",pError );
        ((TextView) view.findViewById(R.id.lbl_create1_error)).setText(pError);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_create1, container, false);
        activity = ((HomeActivity)getActivity());

        errorText = ((TextView) view.findViewById(R.id.lbl_create1_error));
        email = (EditText) this.view.findViewById(R.id.txt_create1_email);
        alias =(EditText) view.findViewById(R.id.txt_create1_alias);
        psdw1 =(EditText) view.findViewById(R.id.txt_create1_pswd1);
        psdw2 =(EditText) view.findViewById(R.id.txt_create1_pswd2);

        final Button bntCreer = (Button) view.findViewById(R.id.btn_create1_create);
        bntCreer.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {

            String emailStr =  email.getText().toString();
            String aliasStr = alias.getText().toString();
            String psdw1Str = psdw2.getText().toString();
            String psdw2Str = psdw2.getText().toString();
            int idAvatar = (chkChecked?selectedAvatar:ID_AVATAR_DEFAULT);
            //TODO remove testing only
            //aliasStr = HomeActivity.aliasTest;
            psdw1Str= psdw2Str = HomeActivity.pswdTest;
            //emailStr = HomeActivity.emailTest;

            if (validate(emailStr,aliasStr,psdw1Str,psdw2Str,idAvatar ) ) {
                DataManager.getInstance().createUser( emailStr,aliasStr,psdw1Str,idAvatar );
            }
        } });
        bntAvatar = (Button) view.findViewById(R.id.btn_create1_avatar);
        bntAvatar.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
            // send avatar request
            fgrList fgList  = new fgrList();
            //fgList.set
            activity.action = HomeActivity.ACT_AVATAR_CREATE;
            DataManager.getInstance().getAvatarList(0,100);
        } });

        CheckBox chk = (CheckBox) view.findViewById(R.id.chk_create1_avatar);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            chkChecked = isChecked;
            bntAvatar.setEnabled(chkChecked);
            if (chkChecked ){
                bntAvatar.setText(NAME_AVATAR_DEFAULT);
                selectedAvatar = ID_AVATAR_DEFAULT;
            }
            else{
                selectedAvatar = ID_AVATAR_DEFAULT;
                bntAvatar.setText(NAME_AVATAR_DEFAULT);
            }
        } }  );

        return view ;
    }

    public boolean validate( String pEmail , String pAlias , String pPswd1, String pPswd2 , int pIdAvatar){

        boolean okEmail = Utilisateur.validateEMaill(pEmail);
        boolean okAlias = Utilisateur.validateAlias(pAlias);
        boolean okPswd1 = Utilisateur.validatePasowrd(pPswd1);
        boolean okPswdMatch = pPswd1.equals(pPswd2 );
        boolean okFinal = okEmail && okAlias && okPswd1 && okPswdMatch;

        String error = (okEmail     ?"":" Email non valide");
        error+=        (okAlias     ?"":" Alias non valide");
        error+=        (okPswd1     ?"":" Mot de passe non valide");
        error+=        (okPswd1?   (okPswdMatch ?"":" Mot de passe non identique"):"" );
        errorText.setText(error);

        return okFinal;
    }


    public void onDoneSelectingAvatar(Avatar pAvatar) {
        if (pAvatar == null){
            activity.toaster.message("Erreur sélection avatar");
            return;
        }
        selectedAvatar = pAvatar.getId();
        bntAvatar.setText( pAvatar.getName() );
        activity.userControler.setFragment(this);
    }
}

