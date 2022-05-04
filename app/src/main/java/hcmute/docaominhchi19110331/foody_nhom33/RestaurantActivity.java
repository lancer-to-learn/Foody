package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Adapter.NearRestaurantAdapter;

public class RestaurantActivity extends AppCompatActivity {

    ImageView img_restaurant;
    ImageView img_home, img_history, img_profile, img_notice;
    TextView txt_name, txt_address;
    ListView lv_food;
    FoodAdapter adapter;
    ArrayList<Food> listFood;
    RecyclerView nearResRecycle;
    ArrayList<Restaurant> nearList;
    NearRestaurantAdapter nearRestaurantAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.restaurant, container);

        map();

        adapter = new FoodAdapter(this, R.layout.search_activity, listFood);
        nearRestaurantAdapter = new NearRestaurantAdapter(this, nearList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        nearResRecycle.setLayoutManager(layoutManager);
        nearResRecycle.setAdapter(nearRestaurantAdapter);
        lv_food.setAdapter(adapter);

        lv_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int image = listFood.get(i).getImage();
                String name = listFood.get(i).getName();
                Intent intent1 = new Intent(getApplicationContext(), FoodActivity.class);

                    intent1.putExtra("name", name);
                    intent1.putExtra("image", image);
                    intent1.putExtra("price", listFood.get(i).getPrice());
                    intent1.putExtra("res", txt_name.getText().toString());
                    startActivity(intent1);

            }
        });

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
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        img_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });


    }


    private void map() {
        img_restaurant = (ImageView) findViewById(R.id.img_foody);
        txt_name = (TextView) findViewById(R.id.txt_name_restaurant);
        lv_food = (ListView) findViewById(R.id.lv_comment);
        txt_address = (TextView) findViewById(R.id.txt_restaurant_address);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);
        nearResRecycle = (RecyclerView) findViewById(R.id.near_restaurant_recycler);
        listFood = new ArrayList<>();
        nearList = new ArrayList<>();

        listFood.add(new Food("Kim chi", "100", R.drawable.kimchi));
        listFood.add(new Food("BeefSteak", "500", R.drawable.beefsteak));
        listFood.add(new Food("Bread", "200", R.drawable.bread));

        nearList.add(new Restaurant("Bún Chị Bảy", "123 Nguyễn Huệ", R.drawable.restaurant));
        nearList.add(new Restaurant("Cơm sườn bì chả", "456 Võ Văn Kiệt", R.drawable.restaurant1));
        nearList.add(new Restaurant("Ăn vặt cô 3", "789 Võ Văn Ngân", R.drawable.restaurant));

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int image = intent.getIntExtra("image", 0);
        String address = intent.getStringExtra("address");
        txt_name.setText(name);
        img_restaurant.setImageResource(image);
        txt_address.setText(address);



    }
}
