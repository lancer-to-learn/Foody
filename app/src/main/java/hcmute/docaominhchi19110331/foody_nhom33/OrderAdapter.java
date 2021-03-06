package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Order> orderList;

    String name_res;
    int image, id_res;
    Database database;

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
        TextView txt_food,  txt_price, txt_quantity, txt_address;
        Button btn_order;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        OrderAdapter.ViewHolder viewHolder;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);

            viewHolder = new OrderAdapter.ViewHolder();

            viewHolder.txt_food = (TextView) view.findViewById(R.id.txt_ordered_food);
            viewHolder.txt_address= (TextView) view.findViewById(R.id.txtRes);
            viewHolder.img_food = (ImageView) view.findViewById(R.id.img_ordered_food);
            viewHolder.txt_price = (TextView) view.findViewById(R.id.txt_ordered_price);
            viewHolder.txt_quantity = (TextView) view.findViewById(R.id.txt_ordered_quantity);
            viewHolder.btn_order = (Button) view.findViewById(R.id.btn_reorder);

            view.setTag(viewHolder);
        } else {
            viewHolder = (OrderAdapter.ViewHolder) view.getTag();
        }

        Order order = orderList.get(i);
//        Log.d("mytag", ""+ i + " " + order.getId_food());
        Food thisFood = dataInit(order.getId_food());

        viewHolder.txt_food.setText(thisFood.getName());
        viewHolder.txt_price.setText(String.valueOf(order.getPrice_total()));
        viewHolder.txt_address.setText(name_res);
        viewHolder.img_food.setImageResource(thisFood.getImage());
        viewHolder.txt_quantity.setText(String.valueOf(order.getQuantity()));

        viewHolder.btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra("id", thisFood.getId());
                intent.putExtra("name", thisFood.getName());
                intent.putExtra("price", thisFood.getPrice());
                intent.putExtra("image", thisFood.getImage());
                context.startActivity(intent);
            }
        });
        return view;

    }
    private Food dataInit(int id_food){
//        Log.d("id", "" + id_food);
        Food this_Food = new Food(0, 0, null, 0, 0);
        database = new Database(context, "foody.sqlite", null, 1);
        Cursor dataFood = database.GetData("SELECT Name, Image, Price, Id_res FROM Foods WHERE Id = "+ id_food +"");
        while (dataFood.moveToNext()){
            String name = dataFood.getString(0);
//            Log.d("name", ""+name);
            int image = dataFood.getInt(1);
            int price = dataFood.getInt(2);
            int id_res = dataFood.getInt(3);

            Cursor dataRes = database.GetData("SELECT Name FROM Restaurants WHERE Id = "+ id_res +"");
            while (dataRes.moveToNext()){
                 name_res = dataRes.getString(0);
            }

            this_Food = new Food(id_food, 0, name, price, image);
        }
        return this_Food;
    }
}
