package com.example.alexandrearsenault.projetfinal.Activity;

import android.content.Context;
import android.widget.Toast;

import com.example.alexandrearsenault.projetfinal.Modele.Token;

/**
 * Created by alexandrearsenault on 2016-12-08.
 */

public class ToastInfo {


    private Context context;

    public  ToastInfo(HomeActivity pHomeActivity){
        context = pHomeActivity.getApplicationContext();
    }

    public void errorRequest() {
        Toast.makeText(context, "Erreur de réception de la requête", Toast.LENGTH_SHORT).show();
    }

    public void errorModify(Token pToken) {
        Toast.makeText(context, "Erreur de modification ->"+pToken.getAction() , Toast.LENGTH_SHORT).show();
    }

    public void okModify(String pClass) {
        Toast.makeText(context, "Modification de "+ pClass + " réussie", Toast.LENGTH_SHORT).show();
    }

    public void message(String pMessage) {
        Toast.makeText(context, pMessage, Toast.LENGTH_SHORT).show();
    }
}
