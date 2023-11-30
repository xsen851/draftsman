package com.example.draftsman;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity
{
    DatabaseHelper db;
    EditText imageDetails;
    Button view;
    private ImageView objectImageView;
    private static final int PICK_IMAGE_REGUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    DatabaseHelper objectDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        view = findViewById(R.id.open);
        db = new DatabaseHelper(this);

        view.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Cursor res = db.getData();
                if (res.getCount() == 0)
                {
                    Toast.makeText(ImageActivity.this, "Данных о рисунках нет", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("image:"+res.getString(1)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Сохраненные рисунки");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        try {
            imageDetails = findViewById(R.id.nameImageET);
            objectImageView = findViewById(R.id.image);

            objectDatabaseHelper = new DatabaseHelper(this);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void chooseImage(View objectView) {
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_IMAGE_REGUEST);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REGUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);

                objectImageView.setImageBitmap(imageToStore);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void storeImage(View view) {
        try {
            if (!imageDetails.getText().toString().isEmpty() && objectImageView.getDrawable() != null && imageToStore != null) {
                objectDatabaseHelper.storeImage(new Model(imageDetails.getText().toString(), imageToStore));
            } else {
                Toast.makeText(this, "Не удалось сохранить изображение", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

