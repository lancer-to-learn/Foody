package hcmute.docaominhchi19110331.foody_nhom33.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.R;
import hcmute.docaominhchi19110331.foody_nhom33.Restaurant;

public class NearRestaurantAdapter extends RecyclerView.Adapter<NearRestaurantAdapter.NearRestaurantViewHolder>{

    private Context context;
    private List<Restaurant> nearList;

    public NearRestaurantAdapter(Context context, List<Restaurant> recommendedList) {
        this.context = context;
        this.nearList = recommendedList;
    }

    @NonNull
    @Override
    public NearRestaurantAdapter.NearRestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_near, parent, false);
        return new NearRestaurantAdapter.NearRestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NearRestaurantViewHolder holder, int position) {
        holder.txt_nearRestaurant_name.setText(nearList.get(position).getName());
        holder.txt_nearRestaurant_open.setText("Open 8:00 AM");
        holder.txt_nearRestaurant_address.setText(nearList.get(position).getAddress());
        holder.img_nearRestaurant.setImageResource(nearList.get(position).getImage());
    }


    @Override
    public int getItemCount() {
        return nearList.size();
    }

    public static class NearRestaurantViewHolder extends RecyclerView.ViewHolder{

        ImageView img_nearRestaurant;
        TextView txt_nearRestaurant_name, txt_nearRestaurant_address, txt_nearRestaurant_open;

        public NearRestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            img_nearRestaurant = itemView.findViewById(R.id.img_nearRestaurant);
            txt_nearRestaurant_name = itemView.findViewById(R.id.txt_name_near_restaurant);
            txt_nearRestaurant_address = itemView.findViewById(R.id.txt_nearRestaurant_address);
            txt_nearRestaurant_open = itemView.findViewById(R.id.txt_open_time);


        }
    }

}
