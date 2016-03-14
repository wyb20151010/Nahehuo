package app.nahehuo.com.ui.event;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.util.ShowToast;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.util.Calendar;

/**
 * Created by WYB on 2016/1/26.
 */
public class EventPubActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Context mContext;
    private Toolbar mToolbar;
    private TextView tv_title;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private View popView;
    private PopupWindow pop;
    private WindowManager.LayoutParams lp;
    private RelativeLayout ll_take_photo, ll_select_photo, ll_photo_cancel;
    private File tempFile;
    private String filePath;
    private FloatingActionButton fab_send;
    private final static int PHOTO = 1;
    private final static int GALLERY = 2;
    private final static int CUT = 3;
    private final static int TAG = 4;
    private ImageView backdrop;
    private EditText et_pro_name, et_event_num;
    private TextView tv_pub, tv_free, tv_no_free;
    private TextView temp;
    private LinearLayout ll_charge;
    private RelativeLayout ll_start_time, ll_end_time, rl_event_description;
    private SublimePickerFragment mFragment = new SublimePickerFragment();
    private TextView tv_start_time, tv_end_time, tv_event_description;
    private SavePopupEvent mSavePopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pub);
        mContext = this;
        lp = getWindow().getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView()
                  .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                          View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                          View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(
                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        initToolbar();
        initView();
    }


    private void initView() {
        tv_event_description = (TextView) findViewById(
                R.id.tv_event_description);
        rl_event_description = (RelativeLayout) findViewById(
                R.id.rl_event_description);
        rl_event_description.setOnClickListener(this);
        tv_end_time = (TextView) findViewById(R.id.tv_end_time);
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        tv_pub = (TextView) findViewById(R.id.tv_pub);
        tv_pub.setOnClickListener(this);
        fab_send = (FloatingActionButton) findViewById(R.id.fab_send);
        fab_send.setOnClickListener(this);
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
        backdrop = (ImageView) findViewById(R.id.backdrop);
        et_pro_name = (EditText) findViewById(R.id.et_pro_name);
        et_pro_name.addTextChangedListener(new MyTextWatch(et_pro_name));
        et_event_num = (EditText) findViewById(R.id.et_event_num);
        et_event_num.addTextChangedListener(new MyTextWatch(et_event_num));
        tv_free = (TextView) findViewById(R.id.tv_free);
        tv_free.setOnClickListener(this);
        tv_no_free = (TextView) findViewById(R.id.tv_no_free);
        tv_no_free.setOnClickListener(this);
        temp = new TextView(mContext);
        ll_charge = (LinearLayout) findViewById(R.id.ll_charge);
        ll_start_time = (RelativeLayout) findViewById(R.id.ll_start_time);
        ll_start_time.setOnClickListener(this);
        ll_end_time = (RelativeLayout) findViewById(R.id.ll_end_time);
        ll_end_time.setOnClickListener(this);
    }


    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(
                R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("发起活动");
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_send:
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
            case R.id.tv_pub:
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.tv_free:
                changeFromTo(temp, v);
                ll_charge.setVisibility(View.GONE);
                break;
            case R.id.tv_no_free:
                changeFromTo(temp, v);
                ll_charge.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_start_time:
                getDateTime(tv_start_time);
                break;
            case R.id.ll_end_time:
                getDateTime(tv_end_time);
                break;
            case R.id.rl_event_description:
                Intent intent = new Intent(mContext,
                        EventWriteIntroActivity.class);
                startActivityForResult(intent, TAG);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
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
                sb.append(hourOfDay + ":");
                sb.append(minute + "");
                tv.setText(sb.toString());
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setTextSize(15);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS",
                new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE,
                        options).second);
        if(!mFragment.isAdded()){
            mFragment.setArguments(bundle);
            mFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            mFragment.show(getSupportFragmentManager(), "SUBLIME_PICKER");
        }

    }


    private void changeFromTo(TextView from, View v) {
        TextView tv_to = (TextView) v;
        if (temp != tv_to) {
            temp = tv_to;
            from.setBackgroundDrawable(getResources().getDrawable(
                    R.drawable.normal_bg_blue_border));
            from.setTextColor(getResources().getColor(R.color.colorPrimary));
            tv_to.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.checked_bg_blue));
            tv_to.setTextColor(getResources().getColor(R.color.white));
        }
    }


    /* @Override public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.job, menu);
         return super.onCreateOptionsMenu(menu);
     }
 */
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showMaterialDialog();
                break;
            case R.id.refresh:
               /* if (tfl_add_tag.getChildCount() > 0 &&
                        TextUtil.isEmpty(tv_pro_values.getText().toString()) &&
                        TextUtil.isEmpty(tv_pro_intro.getText().toString())) {*/
               /* }*/
                break;
        }
        return super.onOptionsItemSelected(item);
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
                                   backdrop);
            }
            try {
                if (tempFile != null) {
                    tempFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == TAG) {
            if (data != null) {
                tv_event_description.setTextSize(15);
                tv_event_description.setTextColor(
                        getResources().getColor(R.color.black));
                tv_event_description.setText(data.getStringExtra("desc"));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override public void onBackPressed() {
        showMaterialDialog();
    }


    private void showMaterialDialog() {
        mSavePopup = new SavePopupEvent(this);
        mSavePopup.showPopupWindow();
        mSavePopup.setCallBack(new SavePopupEvent.SetOnClickCallBack() {
            @Override public void setSave() {
                mSavePopup.dismiss();
            }


            @Override public void setCancel() {
                finish();
            }
        });
    }


    class MyTextWatch implements TextWatcher {

        private TextView tv;


        public MyTextWatch(TextView tv) {
            this.tv = tv;
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0) {
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setTextSize(15);
            }
            else {
                tv.setTextSize(13);
            }
        }


        @Override public void afterTextChanged(Editable s) {

        }
    }
}
