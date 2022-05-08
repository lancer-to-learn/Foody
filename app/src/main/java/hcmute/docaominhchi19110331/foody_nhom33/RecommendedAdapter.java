package hcmute.docaominhchi19110331.foody_nhom33;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>{

    private Context context;
    private List<Food> recommendedList;

    public RecommendedAdapter(Context context, List<Food> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommended_recycle, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.recommendedName.setText(recommendedList.get(position).getName());
        holder.recommendedRating.setText("4.1");
        holder.recommendedCharges.setText("Free");
        holder.recommendedDeliveryTime.setText("30 mins");
        holder.recommendedPrice.setText(recommendedList.get(position).getPrice());
        holder.recommendedImage.setImageResource(recommendedList.get(position).getImage());

        //Glide.with(context).load(recommendedList.get(position).getImageUrl()).into(holder.recommendedImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodActivity.class);
                i.putExtra("name", recommendedList.get(position).getName());
                i.putExtra("price", recommendedList.get(position).getPrice());
                i.putExtra("rating", 4.1);
                i.putExtra("image", recommendedList.get(position).getImage());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public static class RecommendedViewHolder extends RecyclerView.ViewHolder{

        ImageView recommendedImage;
        TextView recommendedName, recommendedRating, recommendedDeliveryTime, recommendedCharges, recommendedPrice;

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendedImage = itemView.findViewById(R.id.img_nearRestaurant);
            recommendedName = itemView.findViewById(R.id.txt_name_near_restaurant);
            recommendedRating = itemView.findViewById(R.id.txt_nearRestaurant_address);
            recommendedDeliveryTime = itemView.findViewById(R.id.recommended_delivery_time);
            recommendedCharges = itemView.findViewById(R.id.delivery_type);
            recommendedCharges = itemView.findViewById(R.id.delivery_type);
            recommendedPrice = itemView.findViewById(R.id.txt_open_time);

        }
    }

}
