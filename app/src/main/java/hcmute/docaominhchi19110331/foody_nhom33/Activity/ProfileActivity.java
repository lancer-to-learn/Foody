package hcmute.docaominhchi19110331.foody_nhom33.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Food;
import hcmute.docaominhchi19110331.foody_nhom33.FoodAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.HistoryActivity;
import hcmute.docaominhchi19110331.foody_nhom33.LoginActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MainActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MyApplication;
import hcmute.docaominhchi19110331.foody_nhom33.R;
import hcmute.docaominhchi19110331.foody_nhom33.User;

public class ProfileActivity extends AppCompatActivity {

    ImageView img_home, img_history, img_profile, img_notice, img_user;
    TextView txt_username, txt_email;
    Button btn_infor, btn_address, btn_saved;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.profile_activity, container);

        Map();
        User user_this = dataInit();

        txt_username.setText(user_this.getName().toString());
        txt_email.setText(user_this.getEmail().toString());

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

        btn_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserInforActivity.class);
                intent.putExtra("username", user_this.getName().toString());
                intent.putExtra("email", user_this.getEmail().toString());
                intent.putExtra("password", user_this.getPassword().toString());
                startActivity(intent);
            }
        });

        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddressInforActivity.class);
                intent.putExtra("address", user_this.getAddress());
                startActivity(intent);
            }
        });

        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SavedRestaurantActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Map() {

        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);

        //Set icon for button in menu
        img_home.setImageResource(R.drawable.home_icon);
        img_history.setImageResource(R.drawable.history_icon);
        img_notice.setImageResource(R.drawable.icon_notice);
        img_profile.setImageResource(R.drawable.profile_active_icon);

        img_user = (ImageView) findViewById(R.id.img_user);
        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_email = (TextView) findViewById(R.id.txt_email);
        btn_infor = (Button) findViewById(R.id.btn_infor);
        btn_address = (Button) findViewById(R.id.btn_address);
        btn_saved = (Button) findViewById(R.id.btn_saved);

    }
    private User dataInit(){
        database = new Database(this, "foody.sqlite", null, 1);
        int idUser = ((MyApplication) this.getApplication()).getuserId();
        Cursor dataUser = database.GetData("SELECT * FROM Users WHERE Id = "+ idUser+"");
        User thisUser = new User(0, null, null, null, null, null);

        while (dataUser.moveToNext()){
            String email = dataUser.getString(1);
            String pass = dataUser.getString(2);
            String address = dataUser.getString(3);
            String name = dataUser.getString(4);
            String sdt = dataUser.getString(5);

            thisUser = new User(idUser, email, pass, address, name, sdt);
        }
        return thisUser;
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}