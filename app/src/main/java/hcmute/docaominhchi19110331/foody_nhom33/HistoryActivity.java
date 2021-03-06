package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.ProfileActivity;

public class HistoryActivity extends AppCompatActivity {
    ListView lv_order;
    ImageView img_home, img_history, img_profile, img_notice;
    Button btn_order;
    ArrayList<Order> orders;
    OrderAdapter adapter;
    Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.history_activity, container);

        database = new Database(this, "foody.sqlite", null, 1);
        map();

        adapter = new OrderAdapter(this, R.layout.order, orders);
        getdataReceipt();
        lv_order.setAdapter(adapter);

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

    public void map() {
        lv_order = (ListView) findViewById(R.id.lv_order);
        btn_order = (Button) findViewById(R.id.btn_reorder);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);

        //Set icon for button in menu
        img_home.setImageResource(R.drawable.home_icon);
        img_history.setImageResource(R.drawable.history_active_icon);
        img_notice.setImageResource(R.drawable.icon_notice);
        img_profile.setImageResource(R.drawable.profile_icon);
        orders = new ArrayList<>();

//        orders.add(new Order(new Food("BeefSteak", "500000", R.drawable.beefsteak), 1));
//        orders.add(new Order(new Food("Bread", "10000", R.drawable.bread), 1));
    }
    private void getdataReceipt(){
        int userId = ((MyApplication) this.getApplication()).getuserId();
        Cursor dataReceitps = database.GetData("SELECT * FROM Receipts_detail WHERE Id_user = "+ userId+" ORDER BY Id DESC");
//         WHERE Id_user = "+userId+"
        orders.clear();
        while (dataReceitps.moveToNext()){
            int id_food = dataReceitps.getInt(1);
            int quantity = dataReceitps.getInt(2);
            int price_total = dataReceitps.getInt(3);

            orders.add(new Order(0, id_food, quantity, price_total, userId));
        }
        adapter.notifyDataSetChanged();
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}
