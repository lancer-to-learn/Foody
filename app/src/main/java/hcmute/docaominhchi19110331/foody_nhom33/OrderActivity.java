package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.ProfileActivity;
import hcmute.docaominhchi19110331.foody_nhom33.ViewPagerAdapter.OrderPagerAdapter;

public class OrderActivity extends AppCompatActivity {
/*    ImageView img_food, img_add, img_sub;
    TextView txt_name_food, txt_name_res, txt_price;
    EditText edt_name_cus, edt_sdt, edt_address, edt_quantity;
    Button btn_order;*/
    private TabLayout mTablayout;
    private ViewPager viewPager;
    ImageView img_home, img_history, img_profile, img_notice;
    Food thisFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.order_activity, container);
        mTablayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);

        thisFood = getFood();
        OrderPagerAdapter adapter = new OrderPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, thisFood);
        viewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(viewPager);

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        img_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(OrderActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUser()){
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        img_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(OrderActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
    private Food getFood(){
        Intent intent = getIntent();
        int id_food = intent.getIntExtra("id", 1);
        String food_name = intent.getStringExtra("name");
        int img = intent.getIntExtra("image", 1);
        int price = intent.getIntExtra("price", 1);
        return new Food(id_food, 0, food_name, price, img);
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
