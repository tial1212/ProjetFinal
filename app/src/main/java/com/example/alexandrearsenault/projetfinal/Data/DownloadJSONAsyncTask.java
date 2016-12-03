package com.example.alexandrearsenault.projetfinal.Data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

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


        URL url = null;
        try {
            url = new URL(pUrl);

            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("Resource content");
            out.close();
            httpCon.getInputStream();
            return  httpCon.getResponseMessage();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return  null;

    /*
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
                catch (Exception ex) {    }
            }
        }
        */

        Log.e("DownloadJSONAsyncTask","ERROR read json");
        return null;

    }



}