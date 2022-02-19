package com.ogu.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class requests extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btnAddress,btnStart,btnEnd,btnPet,btnTalep,btnMainPage;
    Switch swHost;
    DBHelper2 DB2;
    TextView txtPrice;
    int User,flag,dateFlag;
    Spinner spAddress,spPet;
    String[] cities ={"Adana","Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir","Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum","Denizli","Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum ", "Eskişehir",
            "Gaziantep", "Giresun","Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri","Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya ", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon  ", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt ", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük ", "Kilis", "Osmaniye ", "Düzce"};
    String[] pets ={"Köpek", "Kedi","Kuş", "Balık"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        User= prefs.getInt("id", 0);
        DB2 = new DBHelper2(this);
        btnAddress=findViewById(R.id.btnAddress);
        btnStart=findViewById(R.id.btnStartDate);
        btnEnd=findViewById(R.id.btnEndDate);
        btnPet=findViewById(R.id.btnPet);
        btnTalep=findViewById(R.id.btnTalep);
        txtPrice=findViewById(R.id.txtPrice);
        btnMainPage=findViewById(R.id.btnMainPage);
        swHost=findViewById(R.id.swHost);
        spAddress=findViewById(R.id.spAddress);
        swHost=findViewById(R.id.swHost);
        spAddress=findViewById(R.id.spAddress);
        spPet=findViewById(R.id.spPet);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        ArrayAdapter<String> petAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, pets);
        spAddress.setAdapter(cityAdapter);
        spPet.setAdapter(petAdapter);

        btnTalep.setBackgroundColor(Color.rgb(255,127,0));
        btnStart.setBackgroundColor(Color.WHITE);
        btnEnd.setBackgroundColor(Color.WHITE);
        btnAddress.setBackgroundColor(Color.WHITE);
        btnPet.setBackgroundColor(Color.WHITE);

        btnTalep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start=btnStart.getText().toString();
                String end=btnEnd.getText().toString();
                String city=btnAddress.getText().toString();
                String pet=btnPet.getText().toString();
                String price=txtPrice.getText().toString();
                int host=0;
                if(swHost.isChecked()){host=1;}
                Boolean insert = DB2.insertData(User,start,end,pet,city,host,price,1,User);
                if(insert){
                    Toast.makeText(requests.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), MainPageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requests.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spAddress.performClick();
            }
        });
        btnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spPet.performClick();
            }
        });
        spAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnAddress.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spPet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnPet.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void selectStartDate(View view) {
        dateFlag=1;
        showDatePickerDialog();
    }

    public void selectEndDate(View view) {
        dateFlag=2;
        showDatePickerDialog();
    }
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String monthParse=String.valueOf(month+1);
        String dayParse=String.valueOf(dayOfMonth);
        if(month+1<10){
            monthParse="0"+String.valueOf(month+1);
        }
        if(dayOfMonth<10){
            dayParse="0"+String.valueOf(dayOfMonth);
        }
        String date =  year + "-" +monthParse  + "-" + dayParse;
        if(dateFlag==1){
            btnStart.setText(date);
        }
        else if(dateFlag==2)
        {
            btnEnd.setText(date);
        }
    }
}