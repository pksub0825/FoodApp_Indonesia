package com.sub.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sub.foodapp.FoodDetails;
import com.sub.foodapp.R;
import com.sub.foodapp.model.Allmenu;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

    Context context;
    List<Allmenu> allmenusList;

    public AllMenuAdapter(Context context, List<Allmenu> allmenusList) {
        this.context = context;
        this.allmenusList = allmenusList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.allmenu_recycle_items, parent,false);

        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, final int position) {

        holder.popularName.setText(allmenusList.get(position).getName());
        holder.popularPrice.setText(allmenusList.get(position).getPrice());
        holder.popularCharges.setText(allmenusList.get(position).getDeliveryCharges());
        holder.popularTime.setText(allmenusList.get(position).getDeliveryTime());
        holder.popularRating.setText(allmenusList.get(position).getRating());
        holder.popularNote.setText(allmenusList.get(position).getNote());

        Glide.with(context).load(allmenusList.get(position).getImageUrl()).into(holder.popularImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name",allmenusList.get(position).getName());
                i.putExtra("price",allmenusList.get(position).getPrice());
                i.putExtra("rating",allmenusList.get(position).getRating());
                i.putExtra("image",allmenusList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allmenusList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView popularName, popularNote, popularRating, popularTime, popularCharges, popularPrice;
        ImageView popularImage;


        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            popularName=itemView.findViewById(R.id.popular_name);
            popularNote=itemView.findViewById(R.id.popular_note);
            popularRating=itemView.findViewById(R.id.popular_rating);
            popularTime=itemView.findViewById(R.id.popular_deliverytime);
            popularCharges=itemView.findViewById(R.id.popular_delivery_charge);
            popularPrice=itemView.findViewById(R.id.popular_price);
            popularImage=itemView.findViewById(R.id.popular_images);


        }
    }
}
