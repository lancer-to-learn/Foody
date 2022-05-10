package hcmute.docaominhchi19110331.foody_nhom33.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import hcmute.docaominhchi19110331.foody_nhom33.Adapter.SavedRestaurantAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.HistoryActivity;
import hcmute.docaominhchi19110331.foody_nhom33.LoginActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MainActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MyApplication;
import hcmute.docaominhchi19110331.foody_nhom33.R;
import hcmute.docaominhchi19110331.foody_nhom33.Restaurant;
import hcmute.docaominhchi19110331.foody_nhom33.RestaurantActivity;

public class UserInforActivity extends AppCompatActivity {

    ImageView img_home, img_history, img_profile, img_notice;
    EditText edt_username, edt_email, edt_password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.user_infor, container);

        map();


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

        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_email = (EditText) findViewById(R.id.edt_email_infor);
        edt_password = (EditText) findViewById(R.id.edt_password_infor);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        edt_username.setText(username);
        edt_email.setText(email);
        edt_password.setText(password);

        edt_username.setEnabled(false);
        edt_email.setEnabled(false);
        edt_password.setEnabled(false);
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}