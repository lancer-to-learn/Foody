package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hcmute.docaominhchi19110331.foody_nhom33.ViewPagerAdapter.OrderPagerAdapter;

public class OrderActivity extends AppCompatActivity {
/*    ImageView img_food, img_add, img_sub;
    TextView txt_name_food, txt_name_res, txt_price;
    EditText edt_name_cus, edt_sdt, edt_address, edt_quantity;
    Button btn_order;*/
    private TabLayout mTablayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.order_activity, container);
        mTablayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        OrderPagerAdapter adapter = new OrderPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(viewPager);

        //map();

        /*img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(edt_quantity.getText().toString().trim());
                if (quantity>=1) {
                    quantity++;
                    edt_quantity.setText(String.valueOf(quantity));
                }
            }
        });*/

        /*img_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(edt_quantity.getText().toString().trim());
                if (quantity>1) {
                    quantity--;
                    edt_quantity.setText(String.valueOf(quantity));
                }
            }
        });*/


    }

    /*private void map() {
        img_food = (ImageView) findViewById(R.id.img_food);
        img_add = (ImageView) findViewById(R.id.img_add);
        img_sub = (ImageView) findViewById(R.id.img_sub);
        txt_name_food = (TextView) findViewById(R.id.txt_food_name);
        txt_name_res = (TextView) findViewById(R.id.txt_retaurant);
        txt_price = (TextView) findViewById(R.id.txt_price);
        edt_name_cus = (EditText) findViewById(R.id.edt_customer_name);
        edt_sdt = (EditText) findViewById(R.id.edt_sdt);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_quantity = (EditText) findViewById(R.id.edt_quantity);

        Intent intent = getIntent();
        String restaurant = intent.getStringExtra("res");

        img_food.setImageResource(intent.getIntExtra("image", 0));
        txt_name_food.setText(intent.getStringExtra("name"));
        txt_price.setText(intent.getStringExtra("price"));
        if (restaurant!="" && restaurant!=null)
            txt_name_res.setText(restaurant);
        else
            txt_name_res.setText("Minh Chi");
        edt_quantity.setText(String.valueOf(intent.getIntExtra("quantity", 1)));

    }*/
}
