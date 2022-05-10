package hcmute.docaominhchi19110331.foody_nhom33.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.Adapter.NoticeAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.Comment;
import hcmute.docaominhchi19110331.foody_nhom33.CommentActivity;
import hcmute.docaominhchi19110331.foody_nhom33.CommentAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.Food;
import hcmute.docaominhchi19110331.foody_nhom33.HistoryActivity;
import hcmute.docaominhchi19110331.foody_nhom33.LoginActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MainActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Model.Notice;
import hcmute.docaominhchi19110331.foody_nhom33.MyApplication;
import hcmute.docaominhchi19110331.foody_nhom33.OrderActivity;
import hcmute.docaominhchi19110331.foody_nhom33.R;
import hcmute.docaominhchi19110331.foody_nhom33.RecommendedAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.User;

public class NoticeActivity extends AppCompatActivity {
    ListView lv_notice;
    ImageView img_home, img_history, img_profile, img_notice;
    List<Notice> list;
    NoticeAdapter adapter;
    Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.notice_activity, container);
        map();
        adapter = new NoticeAdapter(this, R.layout.notice, list);
        lv_notice.setAdapter(adapter);

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
                else
                    Toast.makeText(NoticeActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(NoticeActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void map() {
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);
        lv_notice = (ListView) findViewById(R.id.lv_notice);

        //Set icon for button in menu
        img_home.setImageResource(R.drawable.home_icon);
        img_history.setImageResource(R.drawable.history_icon);
        img_notice.setImageResource(R.drawable.notice_active_icon);
        img_profile.setImageResource(R.drawable.profile_icon);

        dataInit();
    }
    private void dataInit(){
        database = new Database(this, "foody.sqlite", null, 1);
        list = new ArrayList<>();
        int userId = ((MyApplication) this.getApplication()).getuserId();

        Cursor dataReceipt = database.GetData("SELECT Id_food, Time FROM Receipts_detail WHERE Id_user = "+ userId+" ORDER BY Id DESC");
        while (dataReceipt.moveToNext()){
            int id = dataReceipt.getInt(0);
            String time = dataReceipt.getString(1);
            String food_name = "";

            Cursor dataFood = database.GetData("SELECT Name FROM Foods WHERE Id = "+ id+"");
            while (dataFood.moveToNext()){
                food_name = dataFood.getString(0);
            }
            Log.d("name", ""+food_name);
            list.add(new Notice("Đặt thành công " + food_name, time));
        }
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}

