package hcmute.docaominhchi19110331.foody_nhom33;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.NoticeActivity;
import hcmute.docaominhchi19110331.foody_nhom33.Activity.ProfileActivity;

public class CommentActivity extends AppCompatActivity {
    Button btn_post;
    EditText edt_comment;
    ImageView img_home, img_history, img_profile, img_notice;
    Database database;
    int id_food, id_user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.comment_activity, container);

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

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = edt_comment.getText().toString().trim();
                if (content != null){
                    database = new Database(CommentActivity.this, "foody.sqlite", null, 1);
                    dataInti();
                    database.QueryData("INSERT INTO Comments VALUES(null, "+id_food+", "+id_user+", \'"+ content +"\')");

                    setResult(Activity.RESULT_OK);
                    finish();
                }
                else
                    Toast.makeText(CommentActivity.this, "Comment field is currently empty", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void map() {
        btn_post = (Button) findViewById(R.id.btn_post);
        edt_comment = (EditText) findViewById(R.id.edt_comment);
        img_home = (ImageView) findViewById(R.id.home_icon);
        img_history = (ImageView) findViewById(R.id.history_icon);
        img_profile = (ImageView) findViewById(R.id.profile_icon);
        img_notice = (ImageView) findViewById(R.id.img_notice);
    }
    private void dataInti(){
        Intent intent = getIntent();
        id_food = intent.getIntExtra("id_food", 1);
        id_user = intent.getIntExtra("id_user", 1);
    }
    private boolean checkUser(){
        return ((MyApplication) this.getApplication()).checkUser();
    }
}
