package hcmute.docaominhchi19110331.foody_nhom33;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class FoodInfoFragment extends Fragment {

    ImageView img_food, img_add, img_sub;
    TextView txt_name_food, txt_price, txt_star;
    TextView txt_quantity, txt_total;
    Button btn_order;
    View view;
    public FoodInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food_info, container, false);
        map();

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
        // Inflate the layout for this fragment
        return view;
    }

    public void SetInfor() {

    }

    private void map() {
        img_food = (ImageView) view.findViewById(R.id.img_food);
        txt_star = (TextView) view.findViewById(R.id.txt_rating);
        img_add = (ImageView) view.findViewById(R.id.img_add);
        img_sub = (ImageView) view.findViewById(R.id.img_sub);
        txt_name_food = (TextView) view.findViewById(R.id.txt_food_name);
        txt_price = (TextView) view.findViewById(R.id.txt_price_food);
        txt_quantity = (TextView) view.findViewById(R.id.txt_quantity_order);
        txt_total = (TextView) view.findViewById(R.id.txt_total_money);
        btn_order = (Button) view.findViewById(R.id.btn_order2);

        txt_total.setText(txt_price.getText().toString());
    }
}