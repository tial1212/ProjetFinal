package com.example.alexandrearsenault.projetfinal.Data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
    public String readJSONfromUrl(String pUrl, String pRequestType) {
        Log.e("readJSONfromUrl","pUrl = "+pUrl);
        String result = "";

        InputStream is = null;
        // Only display the first 102400 characters of the retrieved
        // web page content.
        int len = 1024 * 8;

        try {
            URL url = new URL(pUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod(pRequestType);
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            result = readIt(is, len);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e("readJSONfromUrl","result = "+result);
        return result;


    }


    // Reads an InputStream and converts it to a String.
    public static  String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            String result = "";
            InputStreamReader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            while(reader.read(buffer) >= 0)
            {
            result = result + (new String(buffer));
            buffer = new char[len];
            }
            return result;
            }



}