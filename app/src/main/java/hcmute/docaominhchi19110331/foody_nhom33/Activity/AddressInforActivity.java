package hcmute.docaominhchi19110331.foody_nhom33.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.docaominhchi19110331.foody_nhom33.HistoryActivity;
import hcmute.docaominhchi19110331.foody_nhom33.LoginActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MainActivity;
import hcmute.docaominhchi19110331.foody_nhom33.MyApplication;
import hcmute.docaominhchi19110331.foody_nhom33.R;

public class AddressInforActivity extends AppCompatActivity {

    ImageView img_home, img_history, img_profile, img_notice;
    EditText edt_address;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.address_infor, container);

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
        edt_address = (EditText) findViewById(R.id.edt_address_infor);

        Intent intent = getIntent();
        if (intent!=null) {
            String address = intent.getStringExtra("address");
            edt_address.setText(address);
        }

    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}