package com.example.draftsman;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    Button delete, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        delete = findViewById (R.id.btnDelete);
        update=findViewById(R.id.btnUpdate);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if(pwd.equals(cnf_pwd))
                {
                    long val = db.addUser(user,pwd);
                    if(val > 0){
                        Toast.makeText(RegisterActivity.this,"Регистрация прошла успешно!",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Ошибка регистрации",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"Пароль не совпадает",Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener()  {
        @Override
        public void onClick(View view) {
            String nameTXT = mTextUsername.getText().toString();
            String passwordTXT = mTextPassword.getText().toString();
            Boolean checkupdatedata = db.updatedata(nameTXT,passwordTXT);
            if (checkupdatedata==true)
                Toast.makeText(RegisterActivity.this, "Пароль изменен", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(RegisterActivity.this, "Ошибка",Toast.LENGTH_SHORT).show();
        }
        });

        delete.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick (View view)  {
                String nameTXT = mTextUsername.getText().toString();
                Boolean checkudeletedata = db.deletedata(nameTXT);
                if (checkudeletedata==true)
                    Toast.makeText(RegisterActivity.this, "Пользователь удален", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RegisterActivity.this, "Такого пользователя не существует", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
