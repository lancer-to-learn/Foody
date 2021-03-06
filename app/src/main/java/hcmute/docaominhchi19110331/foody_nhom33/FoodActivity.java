package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class FoodActivity extends AppCompatActivity {

    Button btn_order;
    ImageView img_home, img_history, img_profile, img_notice;
    RatingBar rate_bar;
    RecyclerView in_food_recycle;
    ImageView img_food, btn_comment;
    TextView txt_food_name, txt_res_of_food, txt_description_food;
    ListView lv_comment;
    CommentAdapter adapter;
    RecommendedAdapter inFoodAdapter;
    ArrayList<Comment> commentList;
    ArrayList<Food> in_food_list;
    int REQUEST_CODE = 123;
    String name_food, res;
    int price;
    int image;
    int id;
    int id_res;
    Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.food_activity, container);

        map();
        dataInit();

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                    intent.putExtra("id_food", id);
                    intent.putExtra("id_user", getuserId());
                    startActivityForResult(intent, REQUEST_CODE);
                }
                else{
                    Toast.makeText(FoodActivity.this, "You have to login first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUser()) {
                    Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name_food);
                    intent.putExtra("image", image);
                    intent.putExtra("price", price);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(FoodActivity.this, "You have to login first", Toast.LENGTH_SHORT).show();
                }
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
                    Toast.makeText(FoodActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
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
                else{
                    Toast.makeText(FoodActivity.this, "You have to login first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    private void map() {
        rate_bar = (RatingBar) findViewById(R.id.rb_rate);
        btn_comment = (ImageView) findViewById(R.id.btn_comment_and_rate);
        in_food_recycle = (RecyclerView) findViewById(R.id.in_food_recycle);
        btn_order = (Button) findViewById(R.id.btn_ordering);
        img_food = (ImageView) findViewById(R.id.img_foody);
        lv_comment = (ListView) findViewById(R.id.lv_comment);
        txt_food_name = (TextView) findViewById(R.id.txt_name_food);
        txt_res_of_food = (TextView) findViewById(R.id.txt_res_of_food);
        txt_description_food = (TextView) findViewById(R.id.txt_description_food);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);
    }
    private void dataInit() {
        database = new Database(this, "foody.sqlite", null, 1);
        commentList = new ArrayList<>();
        in_food_list = new ArrayList<>();

        //Get food info
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        id_res = intent.getIntExtra("id_res", 1);
        image = intent.getIntExtra("image", 0);
        name_food = intent.getStringExtra("name");
        price = intent.getIntExtra("price", 0);
        res = intent.getStringExtra("res");

        img_food.setImageResource(image);
        txt_food_name.setText(name_food);
        btn_order.setText(String.valueOf(price));
        txt_res_of_food.setText(res);
        txt_description_food.setText("M??n ngon gi?? h???t d???");
        rate_bar.setRating(3);

        //Set comment info
        adapter = new CommentAdapter(this, R.layout.comment, commentList);
        getdataComments(id);
        lv_comment.setAdapter(adapter);

        //Set foods info
        inFoodAdapter = new RecommendedAdapter(this, in_food_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        getdataFoods();
        in_food_recycle.setLayoutManager(layoutManager);
        in_food_recycle.setAdapter(inFoodAdapter);
    }
    private void getdataFoods(){
        Cursor dataFoods = database.GetData("SELECT * FROM Foods WHERE Id <> "+id+" AND Id_res = "+id_res+"");
        in_food_list.clear();
        while (dataFoods.moveToNext()){
            int id = dataFoods.getInt(0);
            int id_res = dataFoods.getInt(1);
            String name = dataFoods.getString(2);
            int price = dataFoods.getInt(3);
            int image = dataFoods.getInt(4);

            in_food_list.add(new Food(id, id_res, name, price,image));
        }
        inFoodAdapter.notifyDataSetChanged();
    }
    private void getdataComments(int id_food){
        Cursor dataComments = database.GetData("SELECT * FROM Comments WHERE Id_food = "+id_food+"");
        String name = "";
        commentList.clear();
        while (dataComments.moveToNext()){
            int id = dataComments.getInt(0);
            int id_user = dataComments.getInt(2);

            Cursor datauserName = database.GetData("SELECT Name FROM Users WHERE Id = "+id_user+"");
            while (datauserName.moveToNext()){
                name = datauserName.getString(0);
            }
            String content = dataComments.getString(3);

            commentList.add(new Comment(id, id_food, id_user, content, name));
        }

        adapter.notifyDataSetChanged();
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
    private int getuserId(){
        return ((MyApplication) this.getApplication()).getuserId();
    }
}
