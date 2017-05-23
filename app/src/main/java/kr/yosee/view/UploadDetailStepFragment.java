package kr.yosee.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.yosee.R;
import kr.yosee.presenter.UploadDetailStepPresenter;
import kr.yosee.presenter.UploadDetailStepPresenterImpl;

public class UploadDetailStepFragment extends Fragment implements UploadDetailStepPresenter.View {
    public static final int REQUEST_STEP_IMAGE_CAPTURE = 100;
    public static final int REQUEST_STEP_IMAGE_FROM_GALLERY = 101;

    @BindView(R.id.iv_step_image) public ImageView stepImage;
    @BindView(R.id.et_step_description) public EditText stepDescription;

    private UploadDetailStepPresenter presenter;
    public Bitmap stepBitmapImage;

    public UploadDetailStepFragment() {
    }

    public static UploadDetailStepFragment newInstance() {
        return new UploadDetailStepFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new UploadDetailStepPresenterImpl(this);
        UploadDetailCoverActivity activity = (UploadDetailCoverActivity) getActivity();
        activity.subject
            .subscribe(res -> presenter.onActivityResult(res));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_detail_step, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.iv_step_image)
    void getPhoto() {
        final CharSequence[] items = {"사진 촬영하기", "갤러리에서 가져오기", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

    @Override
    public void setStepImage(Bitmap bitmap) {
        stepBitmapImage = bitmap;
        stepImage.setImageBitmap(bitmap);
    }

    public boolean isStepEmpty() {
        return stepBitmapImage == null || TextUtils.isEmpty(stepDescription.getText().toString());
    }

    @OnClick(R.id.btn_next_step)
    void addNextStep() {
        if (isStepEmpty()) {
            Toast.makeText(getContext(), "이미지와 설명을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        UploadDetailCoverActivity activity = (UploadDetailCoverActivity) getActivity();
        activity.getPresenter().addNextStep(newInstance());
    }
}
