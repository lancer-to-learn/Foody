package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
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
        ImageView img_food;
        TextView txt_food,  txt_price;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        FoodAdapter.ViewHolder viewHolder;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);

            viewHolder = new FoodAdapter.ViewHolder();

            viewHolder.txt_food = (TextView) view.findViewById(R.id.txt_name_food);
            //viewHolder.txt_address= (TextView) view.findViewById(R.id.txt_description);
            viewHolder.img_food = (ImageView) view.findViewById(R.id.img_food);
            viewHolder.txt_price = (TextView) view.findViewById(R.id.txt_food_price);

            view.setTag(viewHolder);
        } else {
            viewHolder = (FoodAdapter.ViewHolder) view.getTag();
        }

        Food food = foodList.get(i);
        viewHolder.txt_food.setText(food.getName());
        viewHolder.txt_price.setText(food.getPrice());
        viewHolder.img_food.setImageResource(food.getImage());

        return view;

    }
}
