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

    ImageView img_food;
    TextView txt_name_food, txt_price, txt_star;
    TextView txt_quantity;
    Button btn_order, btn_add, btn_sub;
    View view;
    public FoodInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food_info, container, false);
        map();
        // Inflate the layout for this fragment
        return view;
    }

    public void SetInfor() {

    }

    private void map() {
        img_food = (ImageView) view.findViewById(R.id.img_food);
        txt_star = (TextView) view.findViewById(R.id.txt_star);
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_sub = (Button) view.findViewById(R.id.btn_sub);
        txt_name_food = (TextView) view.findViewById(R.id.txt_food_name);
        txt_price = (TextView) view.findViewById(R.id.txt_price);
        txt_quantity = (TextView) view.findViewById(R.id.txt_quantity);
        btn_order = (Button) view.findViewById(R.id.btn_order2);
    }
}