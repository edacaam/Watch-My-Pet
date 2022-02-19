package com.ogu.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    CheckBox cbBowl,cbCar,cbPet,cbVet,cbGarden,cbFirst,cbSecond,cbThird,cbNoSmoking,cbPoop;
    TextView txtName,txtPhone,txtEmail,txtAddress;
    Button btnEdit,btnHome,btnRequest,btnReservation;
    DBHelper DB;
    DBHelper3 DB3;
    int User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        User= prefs.getInt("id", 0);
        DB = new DBHelper(this);
        DB3= new DBHelper3(this);
        cbBowl= findViewById(R.id.cbBowl);
        txtName=findViewById(R.id.txtName);
        txtAddress=findViewById(R.id.txtAddress);
        txtEmail=findViewById(R.id.txtEMail);
        txtPhone=findViewById(R.id.txtPhone);
        cbCar=findViewById(R.id.cbCar);
        cbPet=findViewById(R.id.cbPet);
        cbVet=findViewById(R.id.cbVet);
        cbGarden=findViewById(R.id.cbGarden);
        cbFirst=findViewById(R.id.cbFirst);
        cbSecond=findViewById(R.id.cbSecond);
        cbThird=findViewById(R.id.cbThird);
        cbNoSmoking=findViewById(R.id.cbNoSmoking);
        cbPoop=findViewById(R.id.cbPoop);
        btnEdit=findViewById(R.id.btnEdit);
        btnHome=findViewById(R.id.btnHome);
        btnRequest=findViewById(R.id.btnRequest);
        btnReservation=findViewById(R.id.btnReservation);

        btnEdit.setBackgroundColor(Color.rgb(255,127,0));
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainPageIntent=new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(mainPageIntent);
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), requests.class);
                startActivity(intent);
            }
        });
        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Reservations.class);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                int rozet1 = 0, rozet2 = 0, rozet3 = 0, rozet4 = 0, rozet5 = 0, rozet6 = 0, rozet7 = 0, rozet8 = 0, rozet9 = 0, rozet10 = 0;
                if (cbBowl.isChecked()) {
                    rozet1 = 1;
                }
                if (cbCar.isChecked()) {
                    rozet2 = 1;
                }
                if (cbPet.isChecked()) {
                    rozet3 = 1;
                }
                if (cbVet.isChecked()) {
                    rozet4 = 1;
                }
                if (cbGarden.isChecked()) {
                    rozet5 = 1;
                }
                if (cbFirst.isChecked()) {
                    rozet6 = 1;
                }
                if (cbSecond.isChecked()) {
                    rozet7 = 1;
                }
                if (cbThird.isChecked()) {
                    rozet8 = 1;
                }
                if (cbNoSmoking.isChecked()) {
                    rozet9 = 1;
                }
                if (cbPoop.isChecked()) {
                    rozet10 = 1;
                }
                if(DB.checkLogin(User)==0) {
                    Boolean insert = DB3.insertData(User, name, email, phone, address, rozet1, rozet2, rozet3, rozet4, rozet5, rozet6, rozet7, rozet8, rozet9, rozet10);
                    if (insert) {
                        Toast.makeText(ProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        DB.updateLogin(User);
                        Intent intent  = new Intent(getApplicationContext(), MainPageActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    DB3.deleteOneRow(User);
                    Boolean insert = DB3.insertData(User, name, email, phone, address, rozet1, rozet2, rozet3, rozet4, rozet5, rozet6, rozet7, rozet8, rozet9, rozet10);
                    if (insert) {
                        Toast.makeText(ProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        DB.updateLogin(User);
                        Intent intent  = new Intent(getApplicationContext(), MainPageActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        if(DB.checkLogin(User)!=0){
            Cursor c=DB3.readProfile(User);
            txtName.setText(c.getString(1));
            txtEmail.setText(c.getString(2));
            txtPhone.setText(c.getString(3));
            txtAddress.setText(c.getString(4));
            if(c.getInt(5)==1){cbBowl.setChecked(true);}
            if(c.getInt(6)==1){cbCar.setChecked(true);}
            if(c.getInt(7)==1){cbPet.setChecked(true);}
            if(c.getInt(8)==1){cbVet.setChecked(true);}
            if(c.getInt(9)==1){cbGarden.setChecked(true);}
            if(c.getInt(10)==1){cbFirst.setChecked(true);}
            if(c.getInt(11)==1){cbSecond.setChecked(true);}
            if(c.getInt(12)==1){cbThird.setChecked(true);}
            if(c.getInt(13)==1){cbNoSmoking.setChecked(true);}
            if(c.getInt(14)==1){cbPoop.setChecked(true);}
        }

    }
}


