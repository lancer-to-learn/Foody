package hcmute.docaominhchi19110331.foody_nhom33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;

public class MainActivity extends AppCompatActivity {

    EditText edt_search;
    ImageView img_home, img_history, img_profile, img_notice;
    RecyclerView recommended;
    ListView lv_restaurant;
    ArrayList<Restaurant> restaurants;
    ArrayList<Restaurant> restaurants2;
    ArrayList<Food> recommededList;
    RestaurantAdapter adapter;
    RecommendedAdapter recommendedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Map();
        adapter = new RestaurantAdapter(this, R.layout.list_home_activity, restaurants, restaurants2);
        recommendedAdapter = new RecommendedAdapter(this, recommededList);
        lv_restaurant.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommended.setLayoutManager(layoutManager);
        recommended.setAdapter(recommendedAdapter);

        lv_restaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int image = restaurants.get(i).getImage();
                String name = restaurants.get(i).getName();
                String address = restaurants.get(i).getAddress();
                Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("image", image);
                intent.putExtra("address", address);
                startActivity(intent);
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

    private void Map() {
        edt_search = (EditText) findViewById(R.id.edt_search);
        lv_restaurant = (ListView) findViewById(R.id.lv_restaurant);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);
        recommended = (RecyclerView) findViewById(R.id.category_recycler);
        restaurants = new ArrayList<>();
        restaurants2 = new ArrayList<>();
        recommededList = new ArrayList<>();

        recommededList.add(new Food("Cơm gà", "20", R.drawable.asiafood2));
        recommededList.add(new Food("Beef Steak", "50", R.drawable.beefsteak));

        restaurants.add(new Restaurant("BBQ Best Restaurant", "123 Dong Da, Ha Noi", R.drawable.restaurant1));
        restaurants2.add(new Restaurant("BBQ Good Restaurant", "456 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));
        restaurants.add(new Restaurant("TeaMilk Best Restaurant", "789 Pham Van Dong, Q9, TP. HCM", R.drawable.restaurant1));
        restaurants2.add(new Restaurant("TeaMilk Good Restaurant", "1 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));
        restaurants.add(new Restaurant("BBQ Best Restaurant", "123 Dong Da, Ha Noi", R.drawable.restaurant1));
        restaurants2.add(new Restaurant("BBQ Good Restaurant", "456 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));
        restaurants.add(new Restaurant("TeaMilk Best Restaurant", "789 Pham Van Dong, Q9, TP. HCM", R.drawable.restaurant1));
        restaurants2.add(new Restaurant("TeaMilk Good Restaurant", "1 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));

    }
}