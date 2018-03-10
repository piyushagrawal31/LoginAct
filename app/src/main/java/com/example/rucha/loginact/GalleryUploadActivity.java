package com.example.rucha.loginact;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by rucha on 04-03-2018.
 */

public class GalleryUploadActivity extends AppCompatActivity{

    ImageView lclImageView;
    Button lclbtnLoadImage;

  //  @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        lclImageView = findViewById(R.id.dispImg);
        lclbtnLoadImage = findViewById(R.id.btnImagefromGallery);

        lclbtnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data){
        super.onActivityResult(requestcode, resultCode, data);

        if (requestcode == 1){
            if (resultCode == Activity.RESULT_OK){
                Uri selectImageUri = data.getData();
                lclImageView.setImageURI(selectImageUri);
            }
            else if (resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Search was Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPath(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private class ImageLoaderTask extends AsyncTask<String, String, Bitmap> {

        private Context context;
        ProgressDialog dialog;

        ImageLoaderTask(Context context){
            this.context = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Loading Image...");
            dialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imgPath = strings[0];
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            if (dialog.isShowing()){
                dialog.cancel();
                lclImageView.setImageBitmap(bitmap);
            }
        }
    }
}
