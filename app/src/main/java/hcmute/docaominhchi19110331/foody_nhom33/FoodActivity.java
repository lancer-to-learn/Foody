package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;

public class FoodActivity extends AppCompatActivity {

    Button btn_order;
    ImageView img_home, img_history, img_profile, img_notice;
    RecyclerView in_food_recycle;
    ImageView img_food, btn_comment;
    TextView txt_food_name, txt_res_of_food, txt_description_food;
    ListView lv_comment;
    CommentAdapter adapter;
    RecommendedAdapter inFoodAdapter;
    ArrayList<Comment> commentList;
    ArrayList<Food> in_food_list;
    int REQUEST_CODE = 123;
    String name_food, price, res;
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
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("name", name_food);
                intent.putExtra("image", image);
                intent.putExtra("price", price);
                intent.putExtra("res", res);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            //txt_name.setText(name);
            Toast.makeText(this, "Get", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void map() {
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
        price = intent.getStringExtra("price");
        res = intent.getStringExtra("res");
        img_food.setImageResource(image);
        txt_food_name.setText(name_food);
        btn_order.setText(price);
        txt_res_of_food.setText(res);
        txt_description_food.setText("Món ngon giá hạt dẻ");

        //Set comment info
//        commentList.add(new Comment(new User("Chi", ""), "Good"));
//        commentList.add(new Comment(new User("Tuan", ""), "Fantastic"));
        getdataComments(id);
        adapter = new CommentAdapter(this, R.layout.comment, commentList);
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
            String address = dataFoods.getString(3);
            int image = dataFoods.getInt(4);

            in_food_list.add(new Food(id, id_res, name, address,image));
        }
        inFoodAdapter.notifyDataSetChanged();
    }
    private void getdataComments(int id_food){
        Cursor dataComments = database.GetData("SELECT * FROM Comments WHERE Id_food = "+id_food+"");
        commentList.clear();
        while (dataComments.moveToNext()){
            int id = dataComments.getInt(0);
            int id_user = dataComments.getInt(2);
            String content = dataComments.getString(2);

            commentList.add(new Comment(id, id_food, id_user, content));
        }
        adapter.notifyDataSetChanged();
    }
}
