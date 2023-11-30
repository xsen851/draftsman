package com.example.draftsman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.UUID;


public class   painting extends AppCompatActivity implements OnClickListener
{
    private PaintView paintView;
    private ImageButton saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);
        paintView = findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        saveBtn = (ImageButton) findViewById (R.id.save_Btn);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal:
                paintView.normal();
                return true;
            case R.id.emboss:
                paintView.emboss();
                return true;
            case R.id.blur:
                paintView.blur();
                return true;
            case R.id.clear:
                paintView.clear();
                return true;
            case R.id.size_small:
                paintView.size_small();
                return true;
            case R.id.size_normal:
                paintView.size_normal();
                return true;
            case R.id.size_big:
                paintView.size_big();
                return true;
            case R.id.color_green:
                paintView.color_green();
                return true;
            case R.id.color_red:
                paintView.color_red();
                return true;
            case R.id.color_black:
                paintView.color_black();
                return true;
            case R.id.color_yellow:
                paintView.color_yellow();
                return true;
            case R.id.color_white:
                paintView.color_white();
                return true;
            case R.id.color_blur:
                paintView.color_blur();
                return true;
            case R.id.color_brown:
                paintView.color_brown();

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view)
    {
        if(view.getId()==R.id.save_Btn){
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Сохранить?");
            saveDialog.setPositiveButton("Да", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    paintView.setDrawingCacheEnabled(true);
                    String imgSaved= MediaStore.Images.Media.insertImage(
                        getContentResolver(),paintView.getDrawingCache(),
                                UUID.randomUUID().toString()+".png","paint");
                        if (imgSaved!=null){
                            Toast unSaved=Toast.makeText(getApplicationContext(),
                                    "Сохранено", Toast.LENGTH_LONG);
                            unSaved.show();
                        } else {
                            Toast unSaved = Toast.makeText(getApplicationContext(),"Ошибка",Toast.LENGTH_LONG);
                            unSaved.show();
                        }
                        paintView.destroyDrawingCache();
                    }
            });
            saveDialog.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }
    }
    public void Activity (View v) {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }
}
