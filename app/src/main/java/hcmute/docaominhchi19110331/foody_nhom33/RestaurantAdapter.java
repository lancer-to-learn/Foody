package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RestaurantAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Restaurant> restaurantList;


    public RestaurantAdapter(Context context, int layout, List<Restaurant> restaurantList) {
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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();

            viewHolder.txt_name1 = (TextView) view.findViewById(R.id.txt_restaurant_name2);
            viewHolder.txt_address1 = (TextView) view.findViewById(R.id.txt_address_restaurant1);
            //viewHolder.txt_address= (TextView) view.findViewById(R.id.txt_description);
            viewHolder.img_image1 = (ImageView) view.findViewById(R.id.imgView_restaurant);
/*            viewHolder.txt_name2 = (TextView) view.findViewById(R.id.txt_restaurant_name2);
            viewHolder.txt_address2 = (TextView) view.findViewById(R.id.txt_address_restaurant2);
            viewHolder.img_image2 = (ImageView) view.findViewById(R.id.imgView_restaurant2);*/

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
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

        return view;
    }
}
