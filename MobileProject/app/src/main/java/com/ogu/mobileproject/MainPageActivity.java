package com.ogu.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.VideoView;

import java.util.Calendar;

public class MainPageActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {

    VideoView videoView;
    Button btnAddress,btnStart,btnEnd,btnPet,btnHostBul,btnProfile,btnHostArayanlar,btnCikis;
    Spinner spAddress,spPet;
    int flag;

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
        setContentView(R.layout.activity_main_page);
        videoView=findViewById(R.id.videoView);
        btnAddress=findViewById(R.id.btnAddress);
        btnStart=findViewById(R.id.btnStartDate);
        btnEnd=findViewById(R.id.btnEndDate);
        btnPet=findViewById(R.id.btnPet);
        btnProfile=findViewById(R.id.btnProfile);
        btnHostBul=findViewById(R.id.btnHostBul);
        btnHostArayanlar=findViewById(R.id.btnHostAra);
        spAddress=findViewById(R.id.spAddress);
        spPet=findViewById(R.id.spPet);
        btnCikis=findViewById(R.id.btnCikis);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        ArrayAdapter<String> petAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, pets);
        spAddress.setAdapter(cityAdapter);
        spPet.setAdapter(petAdapter);

        btnStart.setBackgroundColor(Color.WHITE);
        btnEnd.setBackgroundColor(Color.WHITE);
        btnAddress.setBackgroundColor(Color.WHITE);
        btnPet.setBackgroundColor(Color.WHITE);

        btnHostBul.setBackgroundColor(Color.rgb(255,127,0));

        String str = "https://petsurfer.com/video.mp4";
        Uri uri = Uri.parse(str);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
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
        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), login.class);
                startActivity(intent);
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
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnHostArayanlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), HostArayanlar.class);
                startActivity(intent);
            }
        });
        btnHostBul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("Search", MODE_PRIVATE).edit();
                editor.clear();
                editor.putString("startDate",btnStart.getText().toString());
                editor.putString("endDate",btnEnd.getText().toString());
                editor.putString("address",btnAddress.getText().toString());
                editor.putString("animal",btnPet.getText().toString());
                editor.apply();
                Intent intent=new Intent(getApplicationContext(), Hostlar.class);
                startActivity(intent);
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
    }

    public void selectStartDate(View view) {
        flag=1;
        showDatePickerDialog();
    }

    public void selectEndDate(View view) {
        flag=2;
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
        if(flag==1){
            btnStart.setText(date);
        }
        else if(flag==2)
        {
            btnEnd.setText(date);
        }

    }
}