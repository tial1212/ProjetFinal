package com.example.alexandrearsenault.projetfinal.Controler.User;

import android.app.Fragment;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexandrearsenault.projetfinal.Activity.HomeActivity;
import com.example.alexandrearsenault.projetfinal.Controler.List.ListControler;
import com.example.alexandrearsenault.projetfinal.Controler.List.ListRecycler;
import com.example.alexandrearsenault.projetfinal.Data.DataManager;
import com.example.alexandrearsenault.projetfinal.Data.ObjectConvertor;
import com.example.alexandrearsenault.projetfinal.Modele.ListesDeLecture;
import com.example.alexandrearsenault.projetfinal.R;

import java.util.List;

import static android.R.attr.name;
import static com.example.alexandrearsenault.projetfinal.R.id.txt_create2_captcha;

/**
 * Created by alexandrearsenault on 2016-12-02.
 */

public class fgrConfirmCreate extends Fragment {


    private Bitmap captcha64;

    private View view;
    private EditText captchaEdText;
    private HomeActivity activity;
    private ImageView captchaImg;
    private Button btnSend;
    private Integer idToken;
    private TextView error;

    public void setCaptcha(Bitmap pCaptcha) {
        captcha64 = pCaptcha;
    }
    public void setIdToken(int pIdToken) {
        idToken = pIdToken;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if  (captcha64 != null && idToken != null) {

            view = inflater.inflate(R.layout.lay_playlist, container, false);
            activity = (HomeActivity) getActivity();



            captchaEdText = (EditText)   view.findViewById(txt_create2_captcha);
            captchaImg = (ImageView) view.findViewById(R.id.img_create2_captcha);
            captchaImg.setImageBitmap(captcha64);
            error = (TextView) view.findViewById(R.id.lbl_create2_error);
            //captchaImg.setImageBitmap(  ObjectConvertor.stringBas64ToBitMap(captcha64 )  );  TODO B64 to BitMap
            btnSend = (Button) view.findViewById(R.id.btn_create2_send);
            btnSend.setOnClickListener(new View.OnClickListener() { @Override   public void onClick(View view) {
                DataManager.getInstance().confirmCreateUser(idToken , captchaEdText.getText().toString()  );
            } });



            return view ;
        }
        else{
            Log.e("fgrConfirmCreate","onCrtView() -> these MUST be set before displaying fragment --> playlist="+(captcha64 == null ?"UNSET":"SET" )+" , idToken="+(idToken == null ?"UNSET":"SET" ) );
            return null;
        }
    }

    public void setError(String pError) {
        this.error.setText(pError);
    }
}
