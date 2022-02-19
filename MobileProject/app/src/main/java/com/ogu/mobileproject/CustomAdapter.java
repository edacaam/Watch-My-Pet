package com.ogu.mobileproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    Context context;
    ArrayList<Integer> images;
    private OnCardListener mOnCardListener;

    ArrayList startDate,endDate,price,address,animal,name,email;
    ArrayList<Integer> id,userID,rozet1,rozet2,rozet3,rozet4,rozet5,rozet6,rozet7,rozet8,rozet9,rozet10,active,resUserID;
    CustomAdapter(Context context, ArrayList startDate, ArrayList endDate, ArrayList price, ArrayList address, ArrayList animal,
    ArrayList name,ArrayList email,ArrayList<Integer> id,ArrayList<Integer> rozet1,ArrayList<Integer> rozet2,ArrayList<Integer> rozet3,ArrayList<Integer> rozet4,ArrayList<Integer> rozet5,ArrayList<Integer> rozet6,ArrayList<Integer> rozet7,
                  ArrayList<Integer> rozet8,ArrayList<Integer> rozet9,ArrayList<Integer> rozet10,ArrayList<Integer> active,ArrayList<Integer> resUserID,OnCardListener onCardListener){
        this.context=context;
        this.startDate=startDate;
        this.endDate=endDate;
        this.price=price;
        this.address=address;
        this.animal=animal;
        this.name=name;
        this.email=email;
        this.rozet1=rozet1;
        this.rozet2=rozet2;
        this.rozet3=rozet3;
        this.rozet4=rozet4;
        this.rozet5=rozet5;
        this.rozet6=rozet6;
        this.rozet7=rozet7;
        this.rozet8=rozet8;
        this.rozet9=rozet9;
        this.rozet10=rozet10;
        this.id=id;
        this.active=active;
        this.resUserID=resUserID;
        images = new ArrayList<>();
        this.mOnCardListener = onCardListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row,parent,false);
        return new MyViewHolder(view,mOnCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int in=0;
        images.clear();
        if(rozet1.get(position)==1){images.add(R.drawable.bowl);}
        if(rozet2.get(position)==1){images.add(R.drawable.car);}
        if(rozet3.get(position)==1){images.add(R.drawable.paw);}
        if(rozet4.get(position)==1){images.add(R.drawable.vet);}
        if(rozet5.get(position)==1){images.add(R.drawable.garden);}
        if(rozet6.get(position)==1){images.add(R.drawable.first);}
        if(rozet7.get(position)==1){images.add(R.drawable.second);}
        if(rozet8.get(position)==1){images.add(R.drawable.third);}
        if(rozet9.get(position)==1){images.add(R.drawable.nosmoking);}
        if(rozet10.get(position)==1){images.add(R.drawable.poop);}

        holder.lblAnimalv.setText(String.valueOf(animal.get(position)));
        holder.lblNamev.setText(String.valueOf(name.get(position)));
        holder.lblEmailv.setText(String.valueOf(email.get(position)));
        holder.lblDatev.setText(String.valueOf(startDate.get(position))+" - "+String.valueOf(endDate.get(position)));
        holder.lblPriceV.setText(String.valueOf(price.get(position)));
        holder.lblCityv.setText(String.valueOf(address.get(position)));
        for(int i=0;i<images.size();i++){
            if(in==0){
                holder.imgrozet1.setImageResource(images.get(i));
                in++;
            }
            else if(in==1){
                holder.imgrozet2.setImageResource(images.get(i));
                in++;
            }
            else if(in==2){
                holder.imgrozet3.setImageResource(images.get(i));
                in++;
            }
            else if(in==3){
                holder.imgrozet4.setImageResource(images.get(i));
                in++;
            }
            else if(in==4){
                holder.imgrozet5.setImageResource(images.get(i));
                in++;
            }
            else if(in==5){
                holder.imgrozet6.setImageResource(images.get(i));
                in++;
            }
            else if(in==6){
                holder.imgrozet7.setImageResource(images.get(i));
                in++;
            }
            else if(in==7){
                holder.imgrozet8.setImageResource(images.get(i));
                in++;
            }
            else if(in==8){
                holder.imgrozet9.setImageResource(images.get(i));
                in++;
            }
            else if(in==9){
                holder.imgrozet10.setImageResource(images.get(i));
                in++;
            }
        }
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lblNamev,lblCityv,lblPriceV,lblDatev,lblAnimalv,lblEmailv;
        ImageView imgrozet1,imgrozet2,imgrozet3,imgrozet4,imgrozet5,imgrozet6,imgrozet7,imgrozet8,imgrozet9,imgrozet10;
        OnCardListener onCardListener;
        MyViewHolder(@NonNull View itemView,OnCardListener onCardListener) {
            super(itemView);
            lblNamev=itemView.findViewById(R.id.lblNamev);
            lblEmailv=itemView.findViewById(R.id.lblEmailv);
            lblCityv=itemView.findViewById(R.id.lblCityv);
            lblPriceV=itemView.findViewById(R.id.lblPriceV);
            lblDatev=itemView.findViewById(R.id.lblDatev);
            lblAnimalv=itemView.findViewById(R.id.lblAnimalv);
            imgrozet1=itemView.findViewById(R.id.imgrozet1);
            imgrozet2=itemView.findViewById(R.id.imgrozet2);
            imgrozet3=itemView.findViewById(R.id.imgrozet3);
            imgrozet4=itemView.findViewById(R.id.imgrozet4);
            imgrozet5=itemView.findViewById(R.id.imgrozet5);
            imgrozet6=itemView.findViewById(R.id.imgrozet6);
            imgrozet7=itemView.findViewById(R.id.imgrozet7);
            imgrozet8=itemView.findViewById(R.id.imgrozet8);
            imgrozet9=itemView.findViewById(R.id.imgrozet9);
            imgrozet10=itemView.findViewById(R.id.imgrozet10);
            this.onCardListener=onCardListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }
    public interface OnCardListener {
        void onCardClick(int position);
    }

}