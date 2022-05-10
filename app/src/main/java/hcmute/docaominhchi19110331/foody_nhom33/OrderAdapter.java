package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Context;
import android.view.LayoutInflater;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Order> orderList;
    Database database;
    Food thisFood;

    public OrderAdapter(Context context, int layout, List<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
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
        TextView txt_food,  txt_price, txt_quantity;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        OrderAdapter.ViewHolder viewHolder;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);

            viewHolder = new OrderAdapter.ViewHolder();

            viewHolder.txt_food = (TextView) view.findViewById(R.id.txt_ordered_food);
            //viewHolder.txt_address= (TextView) view.findViewById(R.id.txt_description);
            viewHolder.img_food = (ImageView) view.findViewById(R.id.img_ordered_food);
            viewHolder.txt_price = (TextView) view.findViewById(R.id.txt_ordered_price);
            viewHolder.txt_quantity = (TextView) view.findViewById(R.id.txt_ordered_quantity);

            view.setTag(viewHolder);
        } else {
            viewHolder = (OrderAdapter.ViewHolder) view.getTag();
        }

        Order order = orderList.get(i);
        dataInit(order.getId_food());

        viewHolder.txt_food.setText(thisFood.getName());
        viewHolder.txt_price.setText(String.valueOf(order.getPrice_total()));
        viewHolder.img_food.setImageResource(thisFood.getImage());
        viewHolder.txt_quantity.setText(String.valueOf(order.getQuantity()));

        return view;

    }
    private void dataInit(int id_food){
        database = new Database(context, "foody.sqlite", null, 1);
        Cursor dataFood = database.GetData("SELECT Name, Image FROM Foods WHERE Id = "+ id_food +"");
        while (dataFood.moveToNext()){
            String name = dataFood.getString(0);
            int image = dataFood.getInt(1);

            thisFood = new Food(0, 0, name, 0, image);
        }
    }
}
