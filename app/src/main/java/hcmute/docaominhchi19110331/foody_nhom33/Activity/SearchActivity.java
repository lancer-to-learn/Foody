package hcmute.docaominhchi19110331.foody_nhom33.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Food;
import hcmute.docaominhchi19110331.foody_nhom33.FoodActivity;
import hcmute.docaominhchi19110331.foody_nhom33.FoodAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.HistoryActivity;
import hcmute.docaominhchi19110331.foody_nhom33.LoginActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MainActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MyApplication;
import hcmute.docaominhchi19110331.foody_nhom33.R;
import hcmute.docaominhchi19110331.foody_nhom33.RecommendedAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.Restaurant;
import hcmute.docaominhchi19110331.foody_nhom33.RestaurantActivity;
import hcmute.docaominhchi19110331.foody_nhom33.RestaurantAdapter;

public class SearchActivity extends AppCompatActivity {

    EditText edt_search;
    Spinner spinner;
    ImageView img_home, img_history, img_profile, img_notice;
    ListView lv_search;
    ArrayList<Food> searchList;
    ArrayList<String> spinList;
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.search, container);

        Map();
        edt_search.requestFocus();
        adapter = new FoodAdapter(this, R.layout.search_activity, searchList);
        lv_search.setAdapter(adapter);
        ArrayAdapter spinAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinList);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinAdapter);

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int image = searchList.get(i).getImage();
                String name = searchList.get(i).getName();
                String address = searchList.get(i).getPrice();
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                intent.putExtra("image", image);
                intent.putExtra("name", name);
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
                if (checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SearchActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Map() {
        edt_search = (EditText) findViewById(R.id.edt_search1);
        lv_search = (ListView) findViewById(R.id.lv_search);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);
        spinner = (Spinner) findViewById(R.id.spinner1);

        searchList = new ArrayList<>();
        spinList = new ArrayList<>();

//        searchList.add(new Food("Cơm gà", "20", R.drawable.asiafood2));
//        searchList.add(new Food("Beef Steak", "50", R.drawable.beefsteak));


        spinList.add("TP. HCM");
        spinList.add("Q.1");
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}