package com.developes.photofiesta;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int READ_EXTERNAl = 101;
    ArrayList<File> list;
    ArrayList<File> b;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.image_grid);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAl);
            }
        }


        try {
            list = imageReader(Environment.getExternalStorageDirectory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        gridView.setAdapter(new GridAdapter(this, list));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, FullmageActivity.class);
                intent.putExtra("img", list.get(position).toString());

                startActivity(intent);

            }
        });


    }

    private ArrayList<File> imageReader(File externalStorageDirectory) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permision not grated", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<File> b = new ArrayList<>();

            File[] files = externalStorageDirectory.listFiles();
            if (files != null) {

                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {

                        b.addAll(imageReader(files[i]));
                    } else {

                        if (files[i].getName().endsWith(".jpg")) {

                            b.add(files[i]);
                        }
                    }
                }
            }
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();

        }
        return b;

    }
}
