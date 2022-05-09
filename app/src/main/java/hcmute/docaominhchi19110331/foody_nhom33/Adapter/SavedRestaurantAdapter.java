package hcmute.docaominhchi19110331.foody_nhom33.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.R;
import hcmute.docaominhchi19110331.foody_nhom33.Restaurant;
import hcmute.docaominhchi19110331.foody_nhom33.RestaurantAdapter;

public class SavedRestaurantAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Restaurant> restaurantList;


    public SavedRestaurantAdapter(Context context, int layout, List<Restaurant> restaurantList) {
        this.context = context;
        this.layout = layout;
        this.restaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView img_image1;
        TextView txt_name1,  txt_address1;
        RatingBar rb;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        SavedRestaurantAdapter.ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);

            viewHolder = new SavedRestaurantAdapter.ViewHolder();

            viewHolder.txt_name1 = (TextView) view.findViewById(R.id.txt_name_saved_restaurant);
            viewHolder.txt_address1 = (TextView) view.findViewById(R.id.txt_address_saved_restaurant);
            //viewHolder.txt_address= (TextView) view.findViewById(R.id.txt_description);
            viewHolder.img_image1 = (ImageView) view.findViewById(R.id.img_saved_restaurant);
            viewHolder.rb = (RatingBar) view.findViewById(R.id.rb_saved_restaurant);
/*            viewHolder.txt_name2 = (TextView) view.findViewById(R.id.txt_restaurant_name2);
            viewHolder.txt_address2 = (TextView) view.findViewById(R.id.txt_address_restaurant2);
            viewHolder.img_image2 = (ImageView) view.findViewById(R.id.imgView_restaurant2);*/

            view.setTag(viewHolder);
        } else {
            viewHolder = (SavedRestaurantAdapter.ViewHolder) view.getTag();
        }
       /* for (int k=1; k<i;) {
            Restaurant restaurant = restaurantList.get(k);
            Restaurant restaurant2 = restaurantList.get(k+1);
            viewHolder.txt_name1.setText(restaurant.getName());
            viewHolder.img_image1.setImageResource(restaurant.getImage());
            viewHolder.txt_name2.setText(restaurant2.getName());
            viewHolder.img_image2.setImageResource(restaurant2.getImage());
            k+=2;
            return view;
        }*/

        Restaurant restaurant = restaurantList.get(i);
        viewHolder.txt_name1.setText(restaurant.getName());
        viewHolder.txt_address1.setText(restaurant.getAddress());
        viewHolder.img_image1.setImageResource(restaurant.getImage());
        viewHolder.rb.setRating(4);


        return view;
    }
}
