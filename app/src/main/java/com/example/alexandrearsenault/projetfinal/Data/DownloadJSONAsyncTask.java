package com.example.alexandrearsenault.projetfinal.Data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by alexandrearsenault on 2016-11-29.
 */

public class DownloadJSONAsyncTask extends AsyncTask<Void, Void, String> {

    public static final int REQ_GET = 1;
    public static final int REQ_PUT = 2;

    private DataManager dataMgr;
    private String url;
    private int action;

    public DownloadJSONAsyncTask(DataManager pDataMgr,int pAction, String pUrl ) {
        dataMgr = pDataMgr;
        url = pUrl;
        action = pAction;
    }
    @Override
    protected String doInBackground(Void... params) {
        String resultString;
        resultString = readJSONfromUrl(url , "PUT" );

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
    public static String readJSONfromUrl(String pUrl, String pRequestType) {
        Log.e("readJSONfromUrl","pUrl = "+pUrl);
        String result="";
        try {
            URL url = new URL(pUrl);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            //httpCon.setDoOutput(true);
            httpCon.setRequestMethod( pRequestType );

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
                Log.e("catch", "Error reading InputStream");
                Log.i("catch", ""+e.getLocalizedMessage()  );
                result = null;
            }
            finally {
                if (is != null) {
                    try {
                        is.close();
                    }
                    catch (IOException e) {
                        Log.e("catch", "Error closing InputStream");
                    }
                }
            }
        } catch (Exception e) {
            Log.e("readJSONfromUrl","ERROR");
            e.printStackTrace();
        }

        Log.e("readJSONfromUrl","result = "+result);
        return result;




    }



}