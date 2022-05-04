package com.ys_production.jsonapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdepter extends ArrayAdapter {
    private ArrayList<Json_data> dataArrayList;
    private int resourse;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;
    Json_data jsonData;
    Context context;
    StringBuilder url ;
    public CustomAdepter(@NonNull Context context, int resource, ArrayList<Json_data> dataArrayList) {
        super(context, resource);
        this.dataArrayList = dataArrayList;
        this.resourse = resource;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return dataArrayList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = layoutInflater.inflate(resource,parent,false);
        if (convertView == null) {
            convertView = layoutInflater.inflate(resourse, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();
        jsonData = dataArrayList.get(position);
        url = new StringBuilder();
        url.append( "https://farm").append(jsonData.getFarm()).append(".staticflickr.com/")
                .append(jsonData.server).append("/").append(jsonData.getSecret()).append(".jpg");


        com.bumptech.glide.Glide.with(context).load(url).into(viewHolder.imageView);

        return convertView; //super.getView(position, convertView, parent)
    }
    private class ViewHolder{
        final ImageView imageView;

        private ViewHolder(View view) {
            this.imageView = view.findViewById(R.id.imageView_adepter);
        }
    }
}
