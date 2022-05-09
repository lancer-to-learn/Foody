package hcmute.docaominhchi19110331.foody_nhom33.Activity;

import android.content.Intent;
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
import hcmute.docaominhchi19110331.foody_nhom33.R;

public class ProfileActivity extends AppCompatActivity {

    ImageView img_home, img_history, img_profile, img_notice, img_user;
    TextView txt_username, txt_email;
    Button btn_infor, btn_address, btn_saved;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.profile_activity, container);

        Map();


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

        btn_infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserInforActivity.class);
                intent.putExtra("username", txt_username.getText().toString());
                intent.putExtra("email", txt_email.getText().toString());
                intent.putExtra("password", "123");
                startActivity(intent);
            }
        });

        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddressInforActivity.class);
                intent.putExtra("address", "123 Le Van Viet");
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
}