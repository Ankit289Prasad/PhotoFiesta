package com.developes.photofiesta;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter
{
    ArrayList<File> list;
    Context mContext;
    public GridAdapter(Context context, ArrayList<File> list)
    {
        this.mContext=context;
        this.list=list;
    }
    @Override
    public int getCount() {

        Log.d("len=",String.valueOf(list.size()));
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View convertVeiw=null;

        if( convertVeiw == null) {
            convertVeiw = LayoutInflater.from(mContext).inflate(R.layout.row_layout, parent, false);
            ImageView myImage = (ImageView) convertVeiw.findViewById(R.id.my_image);
            myImage.setImageURI(Uri.parse(list.get(position).toString()));
        }
        return convertVeiw;
    }
}
