package com.ys_production.jsonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String a ;
    ListView listView;
    private ArrayList<Json_data> jsonDataArrayList;
    private final String url = "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=5d93fd907b9a14b524ae005f64897d7a&text=apple&format=json&nojsoncallback=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView1);
        GetRawData getRawData = new GetRawData();
        getRawData.execute(url);
        findViewById(R.id.t1).setOnClickListener(v -> ((TextView)findViewById(R.id.t1)).setText(a));
    }
    public void downloadCallBack(String data){
        Log.e(TAG, "downloadCallBack: " );
        a = data;
    }
    private class jsonParsing{
        public jsonParsing(String s){
            try {
                JSONObject jsonObject1 = new JSONObject(s);
                JSONObject jsonObject2 = jsonObject1.getJSONObject("photos");
                JSONArray jsonArray = jsonObject2.getJSONArray("photo");
                for(int i = 0; jsonArray.length() > i ; i++ ){
                    Json_data jsonData = new Json_data();
                    JSONObject j = jsonArray.getJSONObject(i);
                    jsonData.setId(j.getString("id"));
                    jsonData.setSecret(j.getString("secret"));
                    jsonData.setServer(j.getString("server"));
                    jsonData.setFarm(j.getString("farm"));
                    jsonDataArrayList.add(jsonData);
                }
                listView.setAdapter(new CustomAdepter(MainActivity.this,R.layout.adepter_layout,jsonDataArrayList));

            } catch (JSONException | NumberFormatException e) {
                e.printStackTrace();
            }

        }
    }
}