package com.example.andyyy.touristapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;




public class PhotoActivity extends AppCompatActivity {

    DatabaseHelper db;
    ImageView imageView;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");
        Button buttonTakePhoto = (Button) findViewById(R.id.buttonTakePic);
        imageView = (ImageView) findViewById(R.id.imageView);
        final Cursor cursor = db.findByID(ID);
        cursor.moveToFirst();

        String nameOfPic = cursor.getString(3);
        final String path = "sdcard/TouristApp/"+nameOfPic+".jpg";
        try {
            imageView.setImageDrawable(Drawable.createFromPath(path));
        } catch (Exception e) {

        }


        buttonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile(ID);
                db.updatePhoto(ID, ID);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, CAM_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String path = "sdcard/TouristApp/test.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));

     /*  super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);*/
    }

    public File getFile(String ID) {
        File folder = new File("sdcard/TouristApp");
        if(!folder.exists()){
            folder.mkdir();
        }
        String id = ID;
        File image_file = new File (folder, id+".jpg");
        return image_file;
    }
}
