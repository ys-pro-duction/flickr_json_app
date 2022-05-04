package com.ys_production.jsonapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

enum Dstatus {OK,INPROGRESS,ERROR}

public class GetRawData extends AsyncTask<String,Object,String> {
    private static final String TAG = "GetRawData";
    private Dstatus dstatus ;
    @Override
    protected void onPostExecute(String s) {
        Log.e(TAG, "onPostExecute: "+s );
        new MainActivity().downloadCallBack(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder jsonData = new StringBuilder();
        try {
            dstatus = Dstatus.INPROGRESS;
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while (null != (line = reader.readLine())){
                jsonData.append(line).append("\n");
                Log.d(TAG, " "+line);
            }
            dstatus = Dstatus.OK;
            return jsonData.toString();
        }catch (MalformedURLException e){
            dstatus = Dstatus.ERROR;
            Log.e(TAG, "doInBackground: "+"MalformedURLException" );
            e.printStackTrace();
        }catch (IOException e){
            dstatus = Dstatus.ERROR;
            Log.e(TAG, "doInBackground: " +"IOException");
            e.printStackTrace();
        }catch (SecurityException e){
            dstatus = Dstatus.ERROR;
            Log.e(TAG, "doInBackground: " +"SecurityException");
            e.printStackTrace();
        }
        return null;
    }
}
