package hcmute.docaominhchi19110331.foody_nhom33;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CommentActivity extends AppCompatActivity {
    Button btn_post;
    EditText edt_comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ScrollView container = (ScrollView) findViewById(R.id.container);
        getLayoutInflater().inflate(R.layout.comment_activity, container);

        map();

    }

    private void map() {
        btn_post = (Button) findViewById(R.id.btn_post);
        edt_comment = (EditText) findViewById(R.id.edt_comment);
    }
}
