package kr.yosee.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.IOException;
import kr.yosee.R;
import kr.yosee.presenter.UploadPresenter;
import kr.yosee.presenter.UploadPresenterImpl;

public class UploadActivity extends AppCompatActivity implements UploadPresenter.View {
    // CONSTANTS
    public static final int REQUEST_IMAGE_FROM_GALLERY = 2;
    public static final int REQUEST_IMAGE_CAPTURE = 1;

    // BIND VIEW
    @BindView(R.id.iv_main_img) ImageView ivMainImg;
    @BindView(R.id.et_main_title) EditText etMainTitle;
    @BindView(R.id.et_main_description) EditText etMainDescription;

    private UploadPresenter presenter;
    private Bitmap mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ButterKnife.bind(this);

        presenter = new UploadPresenterImpl(this);
    }

    @OnClick(R.id.iv_main_img)
    public void getPhoto() {
        final CharSequence[] items = { "사진 촬영하기", "갤러리에서 가져오기", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setItems(items, (dialog, item) -> {
            switch (item) {
                case 0:
                    presenter.takePicture();
                    break;
                case 1:
                    presenter.getPictureFromGallery();
                    break;
                case 2:
                    dialog.dismiss();
                    break;
            }
        });
        builder.show();
    }

    @OnClick(R.id.btn_next_step)
    public void nagivateToDetailRecipe() {
        presenter.navigateToDetailRecipe(mainImage,
                                         etMainTitle.getText().toString(),
                                         etMainDescription.getText().toString());
    }

    public void setImage(Bitmap imageBitmap) {
        ivMainImg.setImageBitmap(imageBitmap);
    }

    @Override
    public void showEmptyImage() {
        Toast.makeText(this, "표지 이미지를 선택해주세요.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyTitle() {
        Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDetailPage(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mainImage = null;
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            Bundle extras = data.getExtras();
            mainImage = (Bitmap) extras.get("data");
            setImage(mainImage);
        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_FROM_GALLERY) {
            Uri uri = data.getData();
            try {
                mainImage = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                setImage(mainImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
