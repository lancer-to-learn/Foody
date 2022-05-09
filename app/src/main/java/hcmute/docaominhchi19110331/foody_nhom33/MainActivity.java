package hcmute.docaominhchi19110331.foody_nhom33;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.SearchActivity;

public class MainActivity extends AppCompatActivity {

    EditText edt_search;
    ImageView img_home, img_history, img_profile, img_notice;
    RecyclerView recommended;
    ListView lv_restaurant, lv_restaurant1;
    ArrayList<Restaurant> restaurants;
    ArrayList<Restaurant> restaurants2;
    ArrayList<Food> recommededList;
    ArrayList<String> spinList;
    RestaurantAdapter adapter, adapter1;
    RecommendedAdapter recommendedAdapter;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Map();
        dataInit();

        edt_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                return true;
            }
        });
        edt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        lv_restaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int id = restaurants.get(i).getId();
                int image = restaurants.get(i).getImage();
                String name = restaurants.get(i).getName();
                String address = restaurants.get(i).getAddress();

                Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("image", image);
                intent.putExtra("address", address);
                intent.putExtra("rating", 3);
                startActivity(intent);
            }
        });

        lv_restaurant1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int image = restaurants2.get(i).getImage();
                String name = restaurants2.get(i).getName();
                String address = restaurants2.get(i).getAddress();
                Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("image", image);
                intent.putExtra("address", address);
                intent.putExtra("rating", 3);
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
        lv_restaurant1 = (ListView) findViewById(R.id.lv_restaurant1);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);

        //Set icon for button in menu
        img_home.setImageResource(R.drawable.home_active_icon);
        img_history.setImageResource(R.drawable.history_icon);
        img_notice.setImageResource(R.drawable.icon_notice);
        img_profile.setImageResource(R.drawable.profile_icon);
        recommended = (RecyclerView) findViewById(R.id.category_recycler);
    }
    private void dataInit(){
        //array init
        restaurants = new ArrayList<>();
        restaurants2 = new ArrayList<>();
        recommededList = new ArrayList<>();
        spinList = new ArrayList<>();

        //adapter init
        adapter = new RestaurantAdapter(this, R.layout.list_home_activity, restaurants);
        adapter1 = new RestaurantAdapter(this, R.layout.list_home_activity, restaurants2);
        recommendedAdapter = new RecommendedAdapter(this, recommededList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommended.setLayoutManager(layoutManager);

        databaseInit();
        dataDelete();
        dataInsert();

        getdataRestaurants();
        getdataFoods();

        spinList.add("TP. HCM");
        spinList.add("Q.1");

        //set adapter for view
        lv_restaurant.setAdapter(adapter);
        lv_restaurant1.setAdapter(adapter1);
        recommended.setAdapter(recommendedAdapter);
    }
    private void databaseInit() {
        //init database
        database = new Database(this, "foody.sqlite", null, 1);
        //init Users table
        database.QueryData("CREATE TABLE IF NOT EXISTS Users(" +
                "Id INTEGER PRIMARY KEY," +
                "Email VARCHAR(200)," +
                "Pass VARCHAR(30)," +
                "Adress VARCHAR(300)," +
                "Name VARCHAR(200)," +
                "SDT VARCHAR(20))");
        //init Restaurants table
        database.QueryData("CREATE TABLE IF NOT EXISTS Restaurants(" +
                "Id INTEGER PRIMARY KEY," +
                "Name VARCHAR(200)," +
                "Adress VARCHAR(300)," +
                "Image INTEGER)");
        //init Foods table
        database.QueryData("CREATE TABLE IF NOT EXISTS Foods(" +
                "Id INTEGER PRIMARY KEY," +
                "Id_res INTEGER," +
                "Name VARCHAR(200)," +
                "Price INTEGER," +
                "Image INTEGER)");
        //init Comments table
        database.QueryData("CREATE TABLE IF NOT EXISTS Comments(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_food INTEGER," +
                "Id_user INTEGER," +
                "Content VARCHAR(500))");
        //init Saved_res table
        database.QueryData("CREATE TABLE IF NOT EXISTS Saved_ress(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_res INTEGER," +
                "Id_user INTEGER)");
        //init Receipts_detail table
        database.QueryData("CREATE TABLE IF NOT EXISTS Receipts_detail(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_food INTEGER," +
                "Quantity INTEGER," +
                "Price_total INTEGER," +
                "Id_profile INTEGER," +
                "Id_user INTEGER)");
        //init Profile table
        database.QueryData("CREATE TABLE IF NOT EXISTS Profiles(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR(200)," +
                "SDT VARCHAR(20)," +
                "Adress VARCHAR(300))");

    }
    private void dataInsert() {
        //Restaurants

//        nearList.add(new Restaurant("Bún Chị Bảy", "123 Nguyễn Huệ", R.drawable.restaurant));
//        nearList.add(new Restaurant("Cơm sườn bì chả", "456 Võ Văn Kiệt", R.drawable.restaurant1));
//        nearList.add(new Restaurant("Ăn vặt cô 3", "789 Võ Văn Ngân", R.drawable.restaurant));
//        restaurants.add(new Restaurant("BBQ Best Restaurant", "123 Dong Da, Ha Noi", R.drawable.restaurant1));
//        restaurants2.add(new Restaurant("BBQ Good Restaurant", "456 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));
//        restaurants.add(new Restaurant("TeaMilk Best Restaurant", "789 Pham Van Dong, Q9, TP. HCM", R.drawable.restaurant1));
//        restaurants2.add(new Restaurant("TeaMilk Good Restaurant", "1 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));
//        restaurants.add(new Restaurant("BBQ Best Restaurant", "123 Dong Da, Ha Noi", R.drawable.restaurant1));
//        restaurants2.add(new Restaurant("BBQ Good Restaurant", "456 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));
//        restaurants.add(new Restaurant("TeaMilk Best Restaurant", "789 Pham Van Dong, Q9, TP. HCM", R.drawable.restaurant1));
//        restaurants2.add(new Restaurant("TeaMilk Good Restaurant", "1 Le Van Viet, Q9, TP. HCM", R.drawable.restaurant1));
        database.QueryData("INSERT INTO Restaurants VALUES(1, 'BBQ Best Restaurant', '123 Dong Da, Ha Noi', "+ R.drawable.restaurant1 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(2, 'BBQ Best Restaurant', '123 Dong Da, Ha Noi', "+ R.drawable.restaurant1 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(3, 'BBQ Best Restaurant', '123 Dong Da, Ha Noi', "+ R.drawable.restaurant1 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(4, 'BBQ Best Restaurant', '123 Dong Da, Ha Noi', "+ R.drawable.restaurant1 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(5, 'BBQ Best Restaurant', '123 Dong Da, Ha Noi', "+ R.drawable.restaurant1 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(6, 'BBQ Best Restaurant', '123 Dong Da, Ha Noi', "+ R.drawable.restaurant1 +" )");

        //Foods
//        listFood.add(new Food("Kim chi", "100", R.drawable.kimchi));
//        listFood.add(new Food("BeefSteak", "500", R.drawable.beefsteak));
//        listFood.add(new Food("Bread", "200", R.drawable.bread));
        database.QueryData("INSERT INTO Foods VALUES(1, 2, 'Cơm gà', 20, "+R.drawable.asiafood2+")");
        database.QueryData("INSERT INTO Foods VALUES(2, 2, 'Beef Steak', 50, "+R.drawable.beefsteak+")");

        //User
        database.QueryData("INSERT INTO Users VALUES(1, 'tuan@gmail.com', '123', 'Q12, Ho Chi Minh', 'Nguyen Anh Tuan', '0333912392')");

        //Comment
        database.QueryData("INSERT INTO Comments VALUES(null, 1, 1, 'Delicious')");
        database.QueryData("INSERT INTO Comments VALUES(null, 1, 1, 'Fanstactic')");
    }
    private void dataDelete() {
        //Restaurants
        database.QueryData("DELETE FROM Restaurants");
        //Foods
        database.QueryData("DELETE FROM Foods");
        //Users
        database.QueryData("DELETE FROM Users");
    }
    private void getdataRestaurants(){
        //LIMIT row_count OFFSET offset;
        Cursor dataRestaurants = database.GetData("SELECT * FROM Restaurants LIMIT 5");
        restaurants.clear();
        while (dataRestaurants.moveToNext()){
            int id = dataRestaurants.getInt(0);
            String name = dataRestaurants.getString(1);
            String address = dataRestaurants.getString(2);
            int image = dataRestaurants.getInt(3);

            restaurants.add(new Restaurant(id, name, address,image));
        }
        adapter.notifyDataSetChanged();
    }
    private void getdataFoods(){
        Cursor dataFoods = database.GetData("SELECT * FROM Foods LIMIT 5");
        recommededList.clear();
        while (dataFoods.moveToNext()){
            int id = dataFoods.getInt(0);
            int id_res = dataFoods.getInt(1);
            String name = dataFoods.getString(2);
            String address = dataFoods.getString(3);
            int image = dataFoods.getInt(4);

            recommededList.add(new Food(id, id_res, name, address,image));
        }
        recommendedAdapter.notifyDataSetChanged();
    }
}