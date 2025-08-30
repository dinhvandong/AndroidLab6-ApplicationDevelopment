package com.app_mobile.eventhandlingdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] names;
    int[] images;
    LayoutInflater inflater;

    public CustomAdapter(Context context, String[] names, int[] images) {
        this.context = context;
        this.names = names;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.custom_list_item, null);
        ImageView imgIcon = row.findViewById(R.id.imgIcon);
        TextView txtName = row.findViewById(R.id.txtName);

        imgIcon.setImageResource(images[position]);
        txtName.setText(names[position]);

        return row;
    }
}