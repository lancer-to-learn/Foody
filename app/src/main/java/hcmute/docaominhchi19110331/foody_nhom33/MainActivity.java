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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.ProfileActivity;
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
                if (checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
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
                else {
                    Toast.makeText(MainActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
                }
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

        dataDelete(); //ch???y 1 l???n
        dataInsert(); //ch???y 1 l???n

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
//        database.QueryData("DROP TABLE IF EXISTS Receipts_detail");
        database.QueryData("CREATE TABLE IF NOT EXISTS Receipts_detail(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_food INTEGER," +
                "Quantity INTEGER," +
                "Price_total INTEGER," +
                "Id_profile INTEGER," +
                "Id_user INTEGER," +
                "Time VARCHAR(50))");
        //init Profile table
        database.QueryData("CREATE TABLE IF NOT EXISTS Profiles(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR(200)," +
                "SDT VARCHAR(20)," +
                "Adress VARCHAR(300))");

    }
    private void dataInsert() {
        //Restaurants
        database.QueryData("INSERT INTO Restaurants VALUES(1, 'BBQ Best Restaurant', '123 Dong Da, Ha Noi', "+ R.drawable.bbqres +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(2, 'TeaMilk Best Restaurant', '1 Le Van Viet, Q9, TP. HCM', "+ R.drawable.restaurant6 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(3, 'B??n Ch??? B???y', '89 Ph???m V??n ?????ng, Q9, TP. HCM', "+ R.drawable.restaurant10+" )");
        database.QueryData("INSERT INTO Restaurants VALUES(4, 'C??m s?????n b?? ch???', '456 V?? V??n Ki???t, Q10, TP. HCM', "+ R.drawable.restaurant7 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(5, '??n v???t c?? 3', '256 V?? V??n Ng??n, Thu Duc, TP. HCM', "+ R.drawable.restaurant9 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(6, 'C??m t???m', '341A QL 1A, Thu Duc, TP. HCM', "+ R.drawable.restaurant8 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(7, 'Ph??? Ngon', '25 V?? V??n Ng??n, Thu Duc, TP. HCM', "+ R.drawable.restaurant +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(8, 'G?? r??n PopEye', '12 V?? V??n Ng??n, Thu Duc, TP. HCM', "+ R.drawable.restaurant2 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(9, 'Ch?? 4 m??a', '10 Tr?????ng Chinh, Q12, TP. HCM', "+ R.drawable.restaurant3 +" )");
        database.QueryData("INSERT INTO Restaurants VALUES(10, 'B??nh m???', '14 Ph???m V??n ?????ng TP. HCM', "+ R.drawable.restaurant4 +" )");

        //Foods
//        listFood.add(new Food("Kim chi", "100", R.drawable.kimchi));
//        listFood.add(new Food("BeefSteak", "500", R.drawable.beefsteak));
//        listFood.add(new Food("Bread", "200", R.drawable.bread));
        database.QueryData("INSERT INTO Foods VALUES(1, 1, 'C??m g??', 10, "+R.drawable.asiafood2+")");
        database.QueryData("INSERT INTO Foods VALUES(2, 1, 'Beef Steak', 20, "+R.drawable.beefsteak+")");
        database.QueryData("INSERT INTO Foods VALUES(3, 1, 'C??m s?????n', 30, "+R.drawable.food_com_suon+")");

        database.QueryData("INSERT INTO Foods VALUES(4, 2, 'Pizza', 40, "+R.drawable.asiafood1+")");
        database.QueryData("INSERT INTO Foods VALUES(5, 2, 'B??nh m??? k???p', 50, "+R.drawable.bread+")");
        database.QueryData("INSERT INTO Foods VALUES(6, 2, 'Pizza vi???t qu???t', 60, "+R.drawable.food1+")");

        database.QueryData("INSERT INTO Foods VALUES(7, 3, 'Ph??? b??', 70, "+R.drawable.food_noodle+")");
        database.QueryData("INSERT INTO Foods VALUES(8, 3, 'Sallad', 80, "+R.drawable.food_salad+")");
        database.QueryData("INSERT INTO Foods VALUES(9, 3, 'C?? h???i chi??n', 90, "+R.drawable.food_salmon+")");


        //User
        database.QueryData("INSERT INTO Users VALUES(1, 'tuan@gmail.com', '123', 'Q12, Ho Chi Minh', 'Nguyen Anh Tuan', '0333912392')");

        //Comment
        database.QueryData("INSERT INTO Comments VALUES(null, 1, 1, 'Delicious')");
        database.QueryData("INSERT INTO Comments VALUES(null, 1, 1, 'Fanstactic')");

        //Receipt
        database.QueryData("INSERT INTO Receipts_detail VALUES(null, 2, 2, 80, 1, 1, '10:00 AM 5/3/2022')");
        database.QueryData("INSERT INTO Receipts_detail VALUES(null, 2, 3, 150, 2, 2, '10:00 AM 5/3/2022')");

        //Saved res
        database.QueryData("INSERT INTO Saved_ress VALUES(null, 1, 1)");
        database.QueryData("INSERT INTO Saved_ress VALUES(null, 2, 1)");
    }
    private void dataDelete() {
        //Restaurants
        database.QueryData("DELETE FROM Restaurants");
        //Foods
        database.QueryData("DELETE FROM Foods");
        //Users
        database.QueryData("DELETE FROM Users");
        //Comments
        database.QueryData("DELETE FROM Comments");
        //Receipts
        database.QueryData("DELETE FROM Receipts_detail");
        //Saved res
        database.QueryData("DELETE FROM Saved_ress");
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

        dataRestaurants = database.GetData("SELECT * FROM Restaurants ORDER BY Id DESC LIMIT 5");
        restaurants2.clear();
        while (dataRestaurants.moveToNext()){
            int id = dataRestaurants.getInt(0);
            String name = dataRestaurants.getString(1);
            String address = dataRestaurants.getString(2);
            int image = dataRestaurants.getInt(3);

            restaurants2.add(new Restaurant(id, name, address,image));
        }
        adapter1.notifyDataSetChanged();
    }
    private void getdataFoods(){
        Cursor dataFoods = database.GetData("SELECT * FROM Foods LIMIT 5");
        recommededList.clear();
        while (dataFoods.moveToNext()){
            int id = dataFoods.getInt(0);
            int id_res = dataFoods.getInt(1);
            String name = dataFoods.getString(2);
            int price = dataFoods.getInt(3);
            int image = dataFoods.getInt(4);

            recommededList.add(new Food(id, id_res, name, price,image));
        }
        recommendedAdapter.notifyDataSetChanged();
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}