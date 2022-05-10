package hcmute.docaominhchi19110331.foody_nhom33.Activity;

import android.content.Intent;
import android.database.Cursor;
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

import hcmute.docaominhchi19110331.foody_nhom33.Adapter.NearRestaurantAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.Adapter.SavedRestaurantAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.Food;
import hcmute.docaominhchi19110331.foody_nhom33.FoodActivity;
import hcmute.docaominhchi19110331.foody_nhom33.FoodAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.HistoryActivity;
import hcmute.docaominhchi19110331.foody_nhom33.LoginActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MainActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MyApplication;
import hcmute.docaominhchi19110331.foody_nhom33.R;
import hcmute.docaominhchi19110331.foody_nhom33.Restaurant;
import hcmute.docaominhchi19110331.foody_nhom33.RestaurantActivity;

public class SavedRestaurantActivity extends AppCompatActivity {

    ImageView img_home, img_history, img_profile, img_notice;
    ListView lv_saved;
    ArrayList<Restaurant> savedList;
    SavedRestaurantAdapter adapter;
    float rating = 4;
    Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.saved_restaurant, container);

        map();

        savedList = new ArrayList<>();
        adapter = new SavedRestaurantAdapter(this, R.layout.restaurant_save, savedList);
        dataInit();
        lv_saved.setAdapter(adapter);

        lv_saved.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int image = savedList.get(i).getImage();
                String name = savedList.get(i).getName();
                Intent intent1 = new Intent(getApplicationContext(), RestaurantActivity.class);

                intent1.putExtra("id", savedList.get(i).getId());
                intent1.putExtra("name", name);
                intent1.putExtra("image", image);
                intent1.putExtra("address", savedList.get(i).getAddress());
                intent1.putExtra("rating", rating);
                intent1.putExtra("saved", true);
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
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });


    }

    private void map() {
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);

        lv_saved = (ListView) findViewById(R.id.lv_saved_restaurant);
    }
    private void dataInit(){
        database = new Database(this, "foody.sqlite", null, 1);
        int userId = ((MyApplication) this.getApplication()).getuserId();
        Cursor datasavedRestaurants = database.GetData("SELECT Id_res FROM Saved_ress WHERE Id_user = "+ userId+"");
        savedList.clear();
        while (datasavedRestaurants.moveToNext()){
            int id_res = datasavedRestaurants.getInt(0);

            Cursor dataRestaurants = database.GetData("SELECT * FROM Restaurants WHERE Id = "+ id_res+"");
            while (dataRestaurants.moveToNext()){
                int id = dataRestaurants.getInt(0);
                String name = dataRestaurants.getString(1);
                String address = dataRestaurants.getString(2);
                int image = dataRestaurants.getInt(3);

                savedList.add(new Restaurant(id, name, address,image));
            }
        }
        adapter.notifyDataSetChanged();
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}

