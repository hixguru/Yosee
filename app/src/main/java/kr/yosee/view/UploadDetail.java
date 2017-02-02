package kr.yosee.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.yosee.R;
import android.graphics.Bitmap;
import org.w3c.dom.Text;

public class UploadDetail extends AppCompatActivity {

    @BindView(R.id.t1) ImageView imageView;
    @BindView(R.id.t2) TextView textView;
    @BindView(R.id.t3) TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        byte[] byteToBitmap = intent.getByteArrayExtra("main_image");
        Bitmap image = BitmapFactory.decodeByteArray(byteToBitmap, 0, byteToBitmap.length);
        String title = intent.getStringExtra("main_title");
        String description = intent.getStringExtra("main_description");

        imageView.setImageBitmap(image);
        textView.setText(title);
        textView1.setText(description);
    }
}
