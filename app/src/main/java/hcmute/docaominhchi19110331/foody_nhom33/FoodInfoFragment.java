package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.Calendar;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Date;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.ViewPagerAdapter.OrderPagerAdapter;


public class FoodInfoFragment extends Fragment {
    Context context;
    ImageView img_food, img_add, img_sub;
    TextView txt_nameoffood, txt_price, txt_star, txt_quantity, txt_total;
    Button btn_order;
    View view;
    Food thisFood;
    Database database;
    int userId;

    public FoodInfoFragment(Food getFood) {
        // Required empty public constructor
        thisFood = getFood;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food_info, container, false);
        userId = ((MyApplication) getActivity().getApplication()).getuserId();
        database = new Database(getActivity(), "foody.sqlite", null, 1);

        map();
        dataInit();

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(txt_quantity.getText().toString().trim());
                int money = Integer.parseInt(txt_price.getText().toString());
                if (quantity<50) {
                    quantity++;
                    txt_quantity.setText(String.valueOf(quantity));
                    txt_total.setText(String.valueOf(money*quantity));
                }
            }
        });

        img_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int money = Integer.parseInt(txt_price.getText().toString());
                int quantity = Integer.parseInt(txt_quantity.getText().toString().trim());
                if (quantity>1) {
                    quantity--;
                    txt_quantity.setText(String.valueOf(quantity));
                    txt_total.setText(String.valueOf(money*quantity));
                }
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_food = thisFood.getId();
                int quantity = Integer.parseInt(txt_quantity.getText().toString().trim());
                int price_total = Integer.parseInt(txt_total.getText().toString().trim());

                String currentTime = Calendar.getInstance().getTime().toString();
                database.QueryData("INSERT INTO Receipts_detail VALUES(null, "+ id_food+", "+ quantity+", "+ price_total+", 1, "+ userId+", \'"+ currentTime+"\')");

                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void map() {
        img_food = (ImageView) view.findViewById(R.id.img_food);
        img_add = (ImageView) view.findViewById(R.id.img_add);
        img_sub = (ImageView) view.findViewById(R.id.img_sub);

        txt_nameoffood = (TextView) view.findViewById(R.id.txt_food);
        txt_star = (TextView) view.findViewById(R.id.txt_rating);
        txt_price = (TextView) view.findViewById(R.id.txt_price_food);
        txt_quantity = (TextView) view.findViewById(R.id.txt_quantity_order);
        txt_total = (TextView) view.findViewById(R.id.txt_total_money);

        btn_order = (Button) view.findViewById(R.id.btn_order_food);
    }
    private void dataInit() {
        img_food.setImageResource(thisFood.getImage());
        txt_price.setText(thisFood.getPrice().toString());
        txt_nameoffood.setText(thisFood.getName().toString());
        txt_total.setText(txt_price.getText().toString());
    }
}