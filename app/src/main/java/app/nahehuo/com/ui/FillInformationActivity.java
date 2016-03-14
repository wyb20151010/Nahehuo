package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.ui.partner.popup.SelectSexPopup;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.ShowToast;
import app.nahehuo.com.util.TextUtil;
import com.makeramen.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.File;
import java.util.Calendar;

/**
 * Created by WYB on 2015/12/30.
 */
public class FillInformationActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tv_title, tv_fill_sex;
    private Context mContext;
    private RoundedImageView iv_upload;
    private View popView;
    private PopupWindow pop;
    private WindowManager.LayoutParams lp;
    private RelativeLayout ll_take_photo, ll_select_photo, ll_photo_cancel;
    private File tempFile;
    private String filePath;
    private final static int PHOTO = 1;
    private final static int GALLERY = 2;
    private final static int CUT = 3;
    private SelectSexPopup mSelectSexPopup;
    private EditText et_name, et_phone, et_email;
    private String sex;
    private final static int UPLOADAVATER = 4;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLOADAVATER:
                    uploadAvater();
                    break;
            }

            super.handleMessage(msg);
        }
    };


    private void uploadAvater() {
        OkHttpUtils.postFile()
                   .url(GlobalVariables.AVATER)
                   .addParams("access_token",
                           "w3qtP4cNIjGh9FvvnQvMWkym3OT3m6uYaCFLcMB2")
                   .addParams("device", GlobalVariables.device)
                   .file(new File(filePath))
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           Log.d("TAG", response);
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_information);
        MyApplication.getInstance().addActivity(this);
        mContext = this;
        mSelectSexPopup = new SelectSexPopup(this);
        lp = getWindow().getAttributes();
        initView();
        initToolbar();
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setOnClickListener(this);
        tv_fill_sex = (TextView) findViewById(R.id.tv_fill_sex);
        tv_fill_sex.setOnClickListener(this);
        iv_upload = (RoundedImageView) findViewById(R.id.iv_upload);
        iv_upload.setOnClickListener(this);
        popView = getLayoutInflater().inflate(R.layout.add_picture_popwindow,
                null);
        ll_take_photo = (RelativeLayout) popView.findViewById(R.id.take_photo);
        ll_take_photo.setOnClickListener(this);
        ll_select_photo = (RelativeLayout) popView.findViewById(
                R.id.select_picture);
        ll_select_photo.setOnClickListener(this);
        ll_photo_cancel = (RelativeLayout) popView.findViewById(
                R.id.photo_cancel);
        ll_photo_cancel.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.tv_fill_sex:
                mSelectSexPopup.showPopupWindow();
                mSelectSexPopup.setCallBack(
                        new SelectSexPopup.SetOnClickCallBack() {
                            @Override public void setMale(String male) {
                                tv_fill_sex.setText(male);
                                mSelectSexPopup.dismiss();
                                sex = male;
                            }


                            @Override public void setFemale(String female) {
                                tv_fill_sex.setText(female);
                                mSelectSexPopup.dismiss();
                                sex = female;
                            }
                        });
                break;
            case R.id.tv_title:
                Intent intent = new Intent(mContext,
                        FillInformation2Activity.class);
                if (!TextUtil.isEmpty(sex) &&
                        !TextUtil.isEmpty(et_email.getText().toString()) &&
                        !TextUtil.isEmpty(et_name.getText().toString()) &&
                        !TextUtil.isEmpty(et_phone.getText().toString())) {
                    intent.putExtra("sex", sex);
                    intent.putExtra("email", et_email.getText().toString());
                    intent.putExtra("name", et_name.getText().toString());
                    intent.putExtra("phone", et_phone.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);
                }
                else {
                    MyToast.showToast(mContext, "请将信息填写完善");
                }

                break;
            case R.id.iv_upload:

                pop = new PopupWindow(popView);
                pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                pop.setOutsideTouchable(false);
                pop.setBackgroundDrawable(new ColorDrawable(00000));
                pop.setFocusable(true);
                lp.alpha = 0.4f;
                getWindow().setAttributes(lp);
                pop.showAtLocation(popView, Gravity.BOTTOM, 0, 95);
                pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override public void onDismiss() {
                        lp.alpha = 1.0f;
                        getWindow().setAttributes(lp);
                    }
                });
                pop.setAnimationStyle(R.style.popwin_anim_style);
                pop.update();
                break;
            case R.id.take_photo:
                pop.dismiss();
                camera();
                break;
            case R.id.select_picture:
                pop.dismiss();
                gallery();
                break;
            case R.id.photo_cancel:
                pop.dismiss();
                break;
        }
    }


    private void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {
            File file = new File(
                    Environment.getExternalStorageDirectory() + "/nahehuo/");
            if (!file.exists()) {
                file.mkdirs();
            }
            tempFile = new File(
                    Environment.getExternalStorageDirectory() + "/naehehuo/",
                    "userPicture.png");
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent, PHOTO);
    }


    private void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY);
    }


    private void crop(Uri uri) {

        String sdCardRoot = Environment.getExternalStorageDirectory()
                                       .getAbsolutePath();
        File file = new File(
                Environment.getExternalStorageDirectory() + "/nahehuo/");
        if (!file.exists()) {
            file.mkdirs();
        }
        filePath = sdCardRoot + "/nahehuo/userpicture" +
                Calendar.getInstance().getTimeInMillis() + "" +
                ".png";
        Uri uri1 = Uri.fromFile(new File(filePath));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return_data", true);
        startActivityForResult(intent, CUT);
    }


    public static boolean hasSdcard() {
        return Environment.MEDIA_MOUNTED.equals(
                Environment.getExternalStorageState());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO) {
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            }
            else {
                ShowToast.Short("没有内存卡");
            }
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                crop(uri);
            }
        }

        if (requestCode == CUT) {
            if (data != null) {
                ImageLoader.getInstance()
                           .displayImage(
                                   ImageDownloader.Scheme.FILE.wrap(filePath),
                                   iv_upload, MyApplication.getDisplayOption());
                mHandler.sendEmptyMessage(UPLOADAVATER);
            }
            try {
                if (tempFile != null) {
                    tempFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
