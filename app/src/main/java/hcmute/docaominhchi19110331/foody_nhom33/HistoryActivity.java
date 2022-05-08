package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;

public class HistoryActivity extends AppCompatActivity {
    ListView lv_order;
    ImageView img_home, img_history, img_profile, img_notice;
    Button btn_order;
    ArrayList<Order> orders;
    OrderAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.history_activity, container);
        map();
        adapter = new OrderAdapter(this, R.layout.order, orders);
        lv_order.setAdapter(adapter);

        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                btn_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                        intent.putExtra("name", orders.get(i).getFood().getName());
                        intent.putExtra("price", orders.get(i).getFood().getPrice());
                        intent.putExtra("image", orders.get(i).getFood().getImage());
                        intent.putExtra("quantity", orders.get(i).getQuantity());
                        startActivity(intent);

                    }
                });
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

        orders.add(new Order(new Food("BeefSteak", "500000", R.drawable.beefsteak), 1));
        orders.add(new Order(new Food("Bread", "10000", R.drawable.bread), 1));
    }
}
