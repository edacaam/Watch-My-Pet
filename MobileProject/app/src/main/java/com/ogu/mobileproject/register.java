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

public class register extends AppCompatActivity {
    TextView lblLogin;
    EditText txtEmail, txtPass, txtPassv;
    Button btnRegister;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtPassv = findViewById(R.id.txtPassv);
        btnRegister = findViewById(R.id.btnRegister);
        DB = new DBHelper(this);
        lblLogin=findViewById(R.id.lblLogin);
        lblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();
                String repass = txtPassv.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS", MODE_PRIVATE).edit();
                                editor.clear();
                                editor.putInt("id", DB.getID(user));
                                editor.apply();
                                Intent intent  = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(register.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
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
        txtPassv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPassv.setText("");
            }
        });

    }
}


