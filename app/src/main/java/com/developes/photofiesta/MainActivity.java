package com.developes.photofiesta;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<File> list;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     gridView = (GridView)findViewById(R.id.image_grid);

     list = imageReader(Environment.getExternalStorageDirectory());

     gridView.setAdapter(new gridAdapter());

     gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             Intent intent = new Intent(MainActivity.this,FullmageActivity.class);
             intent.putExtra("img",list.get(position).toString());

             startActivity(intent);

         }
     });


    }

    public class gridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
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
                convertVeiw = getLayoutInflater().inflate(R.layout.row_layout, parent, false);
                ImageView myImage = (ImageView) convertVeiw.findViewById(R.id.my_image);
                myImage.setImageURI(Uri.parse(list.get(position).toString()));
            }
            return convertVeiw;
        }
    }

    private ArrayList<File> imageReader(File externalStorageDirectory) {

        ArrayList<File> b = new ArrayList<>();

        File[] files = externalStorageDirectory.listFiles();

        for(int i = 0; i<files.length;i++)
        {
            if(files[i].isDirectory()){

                b.addAll(imageReader(files[i]));
            }
            else{

                if(files[i].getName().endsWith(".jpg")){

                    b.add(files[i]);
                }
            }
        }

     return b;
    }
}
