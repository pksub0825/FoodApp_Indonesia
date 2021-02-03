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
import com.sub.foodapp.model.Recommended;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommenededViewHolder> {

    private Context context;
    private List<Recommended> recommendedList;

    public RecommendedAdapter(Context context, List<Recommended> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecommenededViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recommended_recycle_items, parent,false);

        return new RecommenededViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommenededViewHolder holder, final int position) {
        holder.recommandedName.setText(recommendedList.get(position).getName());
        holder.recommandedRating.setText(recommendedList.get(position).getRating());
        holder.recommandedCharges.setText(recommendedList.get(position).getDeliveryCharges());
        holder.recommandedDeliveryTime.setText(recommendedList.get(position).getDeliveryTime());
        holder.recommandedPrice.setText("$"+recommendedList.get(position).getPrice());

        Glide.with(context).load(recommendedList.get(position).getImageUrl()).into(holder.recommandedImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name",recommendedList.get(position).getName());
                i.putExtra("price",recommendedList.get(position).getPrice());
                i.putExtra("rating",recommendedList.get(position).getRating());
                i.putExtra("image",recommendedList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public static class RecommenededViewHolder extends RecyclerView.ViewHolder{

        ImageView recommandedImage;
        TextView recommandedName, recommandedRating, recommandedDeliveryTime,
                recommandedCharges, recommandedPrice;
        public RecommenededViewHolder(@NonNull View itemView) {
            super(itemView);

            recommandedImage=itemView.findViewById(R.id.recommanded_images);
            recommandedName=itemView.findViewById(R.id.recommanded_name);
            recommandedRating=itemView.findViewById(R.id.recommanded_rating);
            recommandedDeliveryTime=itemView.findViewById(R.id.recommanded_delivery_time);
            recommandedCharges=itemView.findViewById(R.id.delivery_type);
            recommandedPrice=itemView.findViewById(R.id.recommanded_price);
        }
    }


}
