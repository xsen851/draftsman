package com.example.draftsman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //вызов диалогового окна
        dialog=new Dialog(this); //создаем новое д.окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.dialog); //путь к д.окну
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон д.окна
        dialog.setCancelable(false);

        //кнопка закрытия д.о
        TextView btn = (TextView) dialog.findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        dialog.dismiss();

                    }
                });

        dialog.show(); //вызов д.окна

    }
    public void StartNewActivity (View v)
    {
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }
    public void StarActivity (View v)
    {
        Intent intent = new Intent (this,  RegisterActivity.class);
        startActivity(intent);
    }

}