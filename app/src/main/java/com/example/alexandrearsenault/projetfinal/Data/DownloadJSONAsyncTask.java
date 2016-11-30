package com.example.alexandrearsenault.projetfinal.Data;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        HttpURLConnection c = null;
        try {
            URL u = new URL(pUrl);
            c = (HttpURLConnection) u.openConnection();
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }
        } catch (Exception ex) {  return ex.toString(); }
        finally {
            if (c != null) {
                try { c.disconnect(); }
                catch (Exception ex) {   /*disconnect error */  }
            }
        }
        return null;
    }


}