package com.ogu.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Reservations extends AppCompatActivity implements CustomAdapter.OnCardListener{
    RecyclerView recyclerView;
    DBHelper2 DB2;
    DBHelper3 DB3;
    ArrayList<String> startDate,endDate,price,address,animal,name,email;
    int CardId,User;
    ArrayList<Integer> id,userID,rozet1,rozet2,rozet3,rozet4,rozet5,rozet6,rozet7,rozet8,rozet9,rozet10,active,resUserID;
    CustomAdapter customAdapter;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        recyclerView=findViewById(R.id.recyclerView);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        User= prefs.getInt("id", 0);
        DB2 = new DBHelper2(this);
        DB3 = new DBHelper3(this);
        startDate = new ArrayList<>();
        endDate = new ArrayList<>();
        price = new ArrayList<>();
        address = new ArrayList<>();
        animal = new ArrayList<>();
        name = new ArrayList<>();
        email = new ArrayList<>();
        userID = new ArrayList<>();
        id = new ArrayList<>();
        rozet1 = new ArrayList<>();
        rozet2 = new ArrayList<>();
        rozet3 = new ArrayList<>();
        rozet4 = new ArrayList<>();
        rozet5 = new ArrayList<>();
        rozet6 = new ArrayList<>();
        rozet7 = new ArrayList<>();
        rozet8 = new ArrayList<>();
        rozet9 = new ArrayList<>();
        rozet10 = new ArrayList<>();
        active=new ArrayList<>();
        resUserID=new ArrayList<>();
        storeDataInArrays();
        storeProfileInArrays();
        customAdapter = new CustomAdapter(Reservations.this,startDate,endDate,price,address,animal,name,email,
                id,rozet1,rozet2,rozet3,rozet4,rozet5,rozet6,rozet7,rozet8,rozet9,rozet10,active,resUserID,this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Reservations.this));

        dialog=new Dialog(Reservations.this);
        dialog.setContentView(R.layout.reservation);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.reservation_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;

        Button btnYes=dialog.findViewById(R.id.btnYes);
        Button btnNo=dialog.findViewById(R.id.btnNo);
        TextView lblReservation=dialog.findViewById(R.id.lblReservation);
        lblReservation.setText("Rezervasyon ??ptal ????lemini Onayl??yor Musunuz?");
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB2.cancelRequest(CardId,User);
                Toast.makeText(Reservations.this, "Rezervasyon ??ptal ????leminiz Ger??ekle??tirildi!", Toast.LENGTH_SHORT).show();
                Intent mainPageIntent=new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(mainPageIntent);
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    void storeDataInArrays(){
        Cursor cursor=DB2.readReservation(User);
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()) {
                id.add(cursor.getInt(0));
                userID.add(cursor.getInt(1));
                startDate.add(cursor.getString(2));
                endDate.add(cursor.getString(3));
                animal.add(cursor.getString(4));
                address.add(cursor.getString(5));
                price.add(cursor.getString(7));
                active.add(cursor.getInt(8));
                resUserID.add(cursor.getInt(9));
            }
        }
    }
    void storeProfileInArrays(){
        for(int i=0;i<userID.size();i++){
            Cursor c=DB3.readProfile(userID.get(i));
            name.add(c.getString(1));
            email.add(c.getString(2));
            rozet1.add(c.getInt(5));
            rozet2.add(c.getInt(6));
            rozet3.add(c.getInt(7));
            rozet4.add(c.getInt(8));
            rozet5.add(c.getInt(9));
            rozet6.add(c.getInt(10));
            rozet7.add(c.getInt(11));
            rozet8.add(c.getInt(12));
            rozet9.add(c.getInt(13));
            rozet10.add(c.getInt(14));
        }
    }

    @Override
    public void onCardClick(int position) {
        dialog.show();
        CardId=id.get(position);
    }
}