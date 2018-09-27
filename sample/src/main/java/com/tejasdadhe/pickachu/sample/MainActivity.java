package com.tejasdadhe.pickachu.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button galleryButton = (Button) findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CustomGalleryActivity.class);
                if (!PermissionProviderMarshmellow.checkPermissionForExternalStorage(MainActivity.this)) {
                    PermissionProviderMarshmellow.requestPermissionForExternalStorage(MainActivity.this);
                    if (!PermissionProviderMarshmellow.checkPermissionForExternalStorage(MainActivity.this)) {
                        Toast.makeText(MainActivity.this,"Storage Permission Denied",Toast.LENGTH_LONG).show();
                    }
                    else {
                        startActivity(i);
                    }
                }
                else {
                     startActivity(i);
                     //Tejas
                }
            }
        });

    }
}