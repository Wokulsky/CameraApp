package com.example.karol.cameraapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Administrator on 07.06.2017.
 */
public class DetailsActivity extends ActionBarActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;
    private String PREFERENCES_NAME = "my_preferences";
    private RatingBar ratingBar;
    private TextView titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        String title = getIntent().getStringExtra("title");
        Bitmap bitmap = getIntent().getParcelableExtra("image");

        titleTextView = (TextView) findViewById(R.id.img_Name);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);

        ratingBar = (RatingBar) findViewById(R.id.ratingBarDetails);
        int star_num = preferences.getInt(titleTextView.getText().toString(),0);

        ratingBar.setNumStars(star_num);

        /*ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratingBar.setRating(rating);
                preferencesEditor.putInt(titleTextView.getText().toString(),ratingBar.getNumStars());
                preferencesEditor.commit();
                preferencesEditor.apply();
            }
        });*/
    }
}
