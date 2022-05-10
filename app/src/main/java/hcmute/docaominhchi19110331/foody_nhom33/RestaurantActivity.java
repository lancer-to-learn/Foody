package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.ProfileActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Adapter.NearRestaurantAdapter;

public class RestaurantActivity extends AppCompatActivity {

    ImageView img_restaurant, img_saved;
    ImageView img_home, img_history, img_profile, img_notice;
    TextView txt_name, txt_address;
    RatingBar rb;
    ListView lv_food;
    FoodAdapter adapter;
    ArrayList<Food> listFood;
    RecyclerView nearResRecycle;
    ArrayList<Restaurant> nearList;
    NearRestaurantAdapter nearRestaurantAdapter;
    Database database;
    int id;
    int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.restaurant, container);

        userId = ((MyApplication) this.getApplication()).getuserId();
        map();
        dataInit();

        lv_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = listFood.get(i).getId();
                int id_res = listFood.get(i).getId_res();
                int image = listFood.get(i).getImage();
                String name = listFood.get(i).getName();
                Intent intent1 = new Intent(getApplicationContext(), FoodActivity.class);

                    intent1.putExtra("id", id);
                    intent1.putExtra("id_res", id_res);
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
                if(checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RestaurantActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
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
                if(checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RestaurantActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_saved.setImageResource(R.drawable.ic_saved_icon);

                Cursor savedRestaurants = database.GetData("SELECT * FROM Saved_ress WHERE Id_user = "+ userId+" AND Id_res = "+ id+"");
                if(savedRestaurants.getCount() == 0){
                    database.QueryData("INSERT INTO Saved_ress VALUES(null, "+ id+", "+ userId+")");
                    Toast.makeText(RestaurantActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(RestaurantActivity.this, "This restaurant has already been saved!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void map() {
        img_restaurant = (ImageView) findViewById(R.id.img_foody);
        txt_name = (TextView) findViewById(R.id.txt_name_restaurant);
        lv_food = (ListView) findViewById(R.id.lv_comment);
        rb = (RatingBar) findViewById(R.id.ratingBar3);
        img_saved = (ImageView) findViewById(R.id.img_save_location);
        txt_address = (TextView) findViewById(R.id.txt_restaurant_address);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);
        nearResRecycle = (RecyclerView) findViewById(R.id.near_restaurant_recycler);
    }
    private void getdataFoods(int this_res){
        Cursor dataFoods = database.GetData("SELECT * FROM Foods WHERE id_res = "+this_res+"");
        listFood.clear();
        while (dataFoods.moveToNext()){
            int id = dataFoods.getInt(0);
            int id_res = dataFoods.getInt(1);
            String name = dataFoods.getString(2);
            int price = dataFoods.getInt(3);
            int image = dataFoods.getInt(4);

            listFood.add(new Food(id, id_res, name, price,image));
        }
        adapter.notifyDataSetChanged();
    }
    private void getdataRestaurants(int this_res){
        Cursor dataRestaurants = database.GetData("SELECT * FROM Restaurants WHERE id <> "+this_res+" ORDER BY Id DESC LIMIT 5");
        nearList.clear();
        while (dataRestaurants.moveToNext()){
            int id = dataRestaurants.getInt(0);
            String name = dataRestaurants.getString(1);
            String address = dataRestaurants.getString(2);
            int image = dataRestaurants.getInt(3);

            nearList.add(new Restaurant(id, name, address,image));
        }
        nearRestaurantAdapter.notifyDataSetChanged();
    }
    private void dataInit() {
        database = new Database(this, "foody.sqlite", null, 1);
        listFood = new ArrayList<>();
        nearList = new ArrayList<>();

        //Get restaurant info
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        int image = intent.getIntExtra("image", 0);
        String address = intent.getStringExtra("address");

        //Set restaurant info
        txt_name.setText(name);
        img_restaurant.setImageResource(image);
        txt_address.setText(address);

        //Set food info
        adapter = new FoodAdapter(this, R.layout.search_activity, listFood);
        getdataFoods(id);
        lv_food.setAdapter(adapter);

        //Set other restaurants info
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        nearResRecycle.setLayoutManager(layoutManager);
        nearRestaurantAdapter = new NearRestaurantAdapter(this, nearList);
        getdataRestaurants(id);
        nearResRecycle.setAdapter(nearRestaurantAdapter);

        Float rating = intent.getFloatExtra("rating", 3);
        Boolean saved = intent.getBooleanExtra("saved", false);
        if (saved==true){
            img_saved.setImageResource(R.drawable.ic_saved_icon);
        }
        else {
            img_saved.setImageResource(R.drawable.ic_rectangle_344);
        }
        txt_name.setText(name);
        img_restaurant.setImageResource(image);
        txt_address.setText(address);
        rb.setRating(rating);

    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}
