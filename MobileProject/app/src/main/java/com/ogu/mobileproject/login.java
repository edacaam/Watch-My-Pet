package com.ogu.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText txtEmail, txtPass;
    TextView lblRegister;
    Button btnLogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        btnLogin =findViewById(R.id.btnLogin);
        DB = new DBHelper(this);
        lblRegister=findViewById(R.id.lblRegister);
        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    int checkuserpass = DB.checkUsernamePassword(user, pass);
                    if(checkuserpass!=-1){
                        Toast.makeText(login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        int login=DB.checkLogin(checkuserpass);
                        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS", MODE_PRIVATE).edit();
                        editor.clear();
                        editor.putInt("id", checkuserpass);
                        editor.apply();
                        if(login==0){
                            Intent intent  = new Intent(getApplicationContext(), ProfileActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent  = new Intent(getApplicationContext(), MainPageActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtEmail.setText("");
            }
        });
        txtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPass.setText("");
            }
        });
    }
}

