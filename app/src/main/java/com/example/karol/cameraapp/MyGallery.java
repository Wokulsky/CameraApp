package com.example.karol.cameraapp;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MyGallery extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;
    private String PREFERENCES_NAME = "my_preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gallery);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(MyGallery.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());
                //Start details activity
                startActivity(intent);
            }
        });
    }
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        //TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("AppPictureGallery", this.MODE_PRIVATE);
        int imgCounter = preferences.getInt("imgCounter",0);
        Toast.makeText(this,"ImgCounter:"+imgCounter,Toast.LENGTH_SHORT).show();
        for (int i = 0; i < imgCounter; i++) {
            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            String file_name ="picture_"+i;
            File photoPath = new File(directory, file_name);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath.getAbsolutePath(), options);
            imageItems.add(new ImageItem(bitmap, file_name));
        }
        return imageItems;
    }
    @Override
    public void onBackPressed() {
            startActivity(new Intent(this,MainActivity.class));
            finish();
    }
}
