package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.event.SublimePickerFragment;
import app.nahehuo.com.ui.partner.popup.SelectSexPopup;
import app.nahehuo.com.util.ShowToast;
import app.nahehuo.com.util.TextUtil;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.makeramen.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.util.Calendar;

/**
 * Created by wyb on 2016/2/19.
 */
public class PartnerBaseInfoActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title, tv_sex, tv_time;
    private Context mContext;
    private LinearLayout ll_switch_sex, ll_switch_time, ll_switch_city;
    private EditText et_name;
    private Button btn_save;
    private SelectSexPopup mSelectSexPopup;
    private RoundedImageView iv_upload;
    private View popView;
    private PopupWindow pop;
    private WindowManager.LayoutParams lp;
    private RelativeLayout ll_take_photo, ll_select_photo, ll_photo_cancel;
    private SublimePickerFragment mFragment = new SublimePickerFragment();

    private final static int PHOTO = 1;
    private final static int GALLERY = 2;
    private final static int CUT = 3;
    private String name;
    private File tempFile;
    private String filePath;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_par_base_info);
        mContext = this;
        lp = getWindow().getAttributes();
        initToolbar();
        initView();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("完善基本信息");
        ll_switch_sex = (LinearLayout) findViewById(R.id.ll_switch_sex);
        ll_switch_time = (LinearLayout) findViewById(R.id.ll_switch_time);
        ll_switch_city = (LinearLayout) findViewById(R.id.ll_switch_city);
        et_name = (EditText) findViewById(R.id.et_name);
        ll_switch_sex.setOnClickListener(this);
        ll_switch_time.setOnClickListener(this);
        ll_switch_city.setOnClickListener(this);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        iv_upload = (RoundedImageView) findViewById(R.id.iv_upload);
        iv_upload.setOnClickListener(this);
        tv_time = (TextView) findViewById(R.id.tv_time);

        initPhoto();
    }


    private void initPhoto() {
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
    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_switch_sex:
                mSelectSexPopup = new SelectSexPopup(this);
                mSelectSexPopup.setCallBack(
                        new SelectSexPopup.SetOnClickCallBack() {
                            @Override public void setMale(String male) {
                                tv_sex.setText(male);
                                tv_sex.setTextColor(getResources().getColor(
                                        R.color.textcolorgray));
                                mSelectSexPopup.dismiss();
                            }
                            @Override public void setFemale(String female) {
                                tv_sex.setText(female);
                                tv_sex.setTextColor(getResources().getColor(
                                        R.color.textcolorgray));
                                mSelectSexPopup.dismiss();
                            }
                        });
                mSelectSexPopup.showPopupWindow();
                break;
            case R.id.ll_switch_time:
                getDateTime(tv_time);
                break;
            case R.id.ll_switch_city:
                break;
            case R.id.btn_save:
                if (!TextUtil.isEmpty(et_name.getText().toString())) {
                    name = et_name.getText().toString();
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


    private void getDateTime(final TextView tv) {
        int displayOptions = 0;
        SublimeOptions options = new SublimeOptions();
        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
        displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;
        options.setDisplayOptions(displayOptions);
        mFragment.setCallback(new SublimePickerFragment.Callback() {
            @Override public void onCancelled() {

            }


            @Override
            public void onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
                StringBuffer sb = new StringBuffer();
                sb.append(year + "年");
                sb.append(monthOfYear + 1 + "月");
                sb.append(dayOfMonth + "日 ");
                tv.setText(sb.toString());
                tv.setTextColor(getResources().getColor(R.color.textcolorgray));
                tv.setTextSize(15);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS",
                new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE,
                        options).second);
        if (!mFragment.isAdded()) {
            mFragment.setArguments(bundle);
            mFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            mFragment.show(getSupportFragmentManager(), "SUBLIME_PICKER");
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
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 720);
        intent.putExtra("outputY", 360);
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
                                   iv_upload);
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
