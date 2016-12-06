package com.example.alexandrearsenault.projetfinal.Data;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;


/**
 * Created by alexandrearsenault on 2016-11-29.
 */

public class DownloadJSONAsyncTask extends AsyncTask<Void, Void, String> {
    private DataManager dataMgr;
    private String url;
    private int action;

    public DownloadJSONAsyncTask(DataManager pDataMgr,int pAction, String pUrl) {
        dataMgr = pDataMgr;
        url = pUrl;
        action = pAction;
    }

    @Override
    protected String doInBackground(Void... params) {
        String resultString;
        resultString = readJSONfromUrl(url);

        return resultString;
    }

    @Override
    protected void onPostExecute(String pResultJSON ) {
        dataMgr.onDoneDownloadingJson(pResultJSON  , action);
    }






    /**
     * Read a JSON file from url
     *
     * @return fluxJSON OR null
     */
    public static String readJSONfromUrl(String pUrl) {
        Log.e("readJSONfromUrl","pUrl = "+pUrl);
        String result="";
        try {


            //TODO jet json w/ PUT


            URL url = new URL(pUrl);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");


            StringBuffer sb = new StringBuffer();
            InputStream is = null;

            try {
                is = new BufferedInputStream(httpCon.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String inputLine = "";
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                result = sb.toString();
            }
            catch (Exception e) {
                Log.i(TAG, "Error reading InputStream");
                result = null;
            }
            finally {
                if (is != null) {
                    try {
                        is.close();
                    }
                    catch (IOException e) {
                        Log.i(TAG, "Error closing InputStream");
                    }
                }
            }





    } catch (Exception e) {
            Log.e("readJSONfromUrl","ERROR");
            e.printStackTrace();
        }

        Log.e("readJSONfromUrl","result :: "+result);

        return result;
        //return "{ \"action\": \"login failled\",   \"id\": 0,   \"etat\": false }";




    }



}