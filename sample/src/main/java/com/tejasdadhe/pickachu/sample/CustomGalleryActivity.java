package com.tejasdadhe.pickachu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tejasdadhe.pickachu.CustomGallery;
import com.tejasdadhe.pickachu.MediaData;

public class CustomGalleryActivity extends AppCompatActivity {

    String TAG = " Custom GAllery Activity ";
    CustomGallery customGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gallery);
        customGalleryView = new CustomGallery(this);
        RelativeLayout root =  findViewById(R.id.root);
        root.addView(customGalleryView);
    }

    public void selectionHandler(View view)
    {
        MediaData[] a=customGalleryView.doneSelection();
        Log.d(CustomGallery.class.getSimpleName(), "Selecteion Method Called: Number of Items selected " + a.length);
        if (a.length > 0)
        {
            Toast.makeText(CustomGalleryActivity.this,  a.length + " items selected ", Toast.LENGTH_SHORT).show();
            Log.d(CustomGallery.class.getSimpleName(), "Selected Items are as follows ");
            for (MediaData anA : a) {
                Log.d(TAG, "File Id : " + anA.getMediaId() + " | URL : " + anA.getUrlHD() + " | File type : " + anA.getMediaType() + " | Date : " + anA.getDate());
            }
        }
    }

}
