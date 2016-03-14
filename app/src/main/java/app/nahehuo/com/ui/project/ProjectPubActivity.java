package app.nahehuo.com.ui.project;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.project.popup.InputPopupMore;
import app.nahehuo.com.ui.project.popup.SavePopup;
import app.nahehuo.com.util.ShowToast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by WYB on 2016/1/20.
 */
public class ProjectPubActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private Context mContext;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView iv_first_show, backdrop;
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
    private EditText et_pro_name;
    private TextView tv_pro_tag;
    private TagFlowLayout tfl_add_tag;
    private RelativeLayout rl_pro_values, rl_pro_intro, rl_pro_tags;
    private TextView tv_pro_values, tv_pro_intro;
    private static final int WRITEINTRO = 5;
    private InputPopupMore mInputPopupMore;
    private TextView tv_pub;
    private SavePopup mSavePopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_pub);
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
        et_pro_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    et_pro_name.setTextColor(
                            getResources().getColor(R.color.black));
                    et_pro_name.setTextSize(15);
                }
                else {
                    et_pro_name.setTextSize(13);
                }
                collapsingToolbarLayout.setTitle(s);
            }


            @Override public void afterTextChanged(Editable s) {

            }
        });

        tv_pro_tag = (TextView) findViewById(R.id.tv_pro_tag);
        tv_pro_tag.setOnClickListener(this);
        tfl_add_tag = (TagFlowLayout) findViewById(R.id.tfl_add_tag);
        rl_pro_values = (RelativeLayout) findViewById(R.id.rl_pro_values);
        rl_pro_values.setOnClickListener(this);
        tv_pro_values = (TextView) findViewById(R.id.tv_pro_values);
        rl_pro_intro = (RelativeLayout) findViewById(R.id.rl_pro_intro);
        rl_pro_intro.setOnClickListener(this);
        tv_pro_intro = (TextView) findViewById(R.id.tv_pro_intro);
        rl_pro_tags = (RelativeLayout) findViewById(R.id.rl_pro_tags);
        rl_pro_tags.setOnClickListener(this);
    }


    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(
                R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("发布项目");
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showMaterialDialog();
                break;
               /* if (tfl_add_tag.getChildCount() > 0 &&
                        TextUtil.isEmpty(tv_pro_values.getText().toString()) &&
                        TextUtil.isEmpty(tv_pro_intro.getText().toString())) {*/

               /* }*/
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pub:
                startActivity(
                        new Intent(mContext, ProjectProgressActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
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
            case R.id.rl_pro_tags:
                Intent intent = new Intent(mContext, ProjectTagActivity.class);
                startActivityForResult(intent, TAG);
                break;
            case R.id.rl_pro_values:
               /* new MaterialDialog.Builder(this).title("项目价值")
                                                .inputType(
                                                        InputType.TYPE_CLASS_TEXT |
                                                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                                                .inputRange(2, 50)
                                                .positiveText("保存")
                                                .negativeText("取消")
                                                .input("一句话描述项目的亮点，最多50字", "",
                                                        false,
                                                        new MaterialDialog.InputCallback() {
                                                            @Override
                                                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                                                tv_pro_values.setTextSize(
                                                                        15);
                                                                tv_pro_values.setTextColor(
                                                                        getResources()
                                                                                .getColor(
                                                                                        R.color.black));
                                                                tv_pro_values.setText(
                                                                        input);
                                                            }
                                                        })
                                                .show();*/
                mInputPopupMore = new InputPopupMore(this);
                mInputPopupMore.showPopupWindow();
                mInputPopupMore.setDataCallBac(
                        new InputPopupMore.DataCallBac() {
                            @Override public void getDataBack(String data) {
                                tv_pro_values.setTextSize(15);
                                tv_pro_values.setTextColor(
                                        getResources().getColor(R.color.black));
                                tv_pro_values.setText(data);
                            }
                        });
                break;
            case R.id.rl_pro_intro:
                Intent intent1 = new Intent(mContext,
                        ProjectWriteIntroActivity.class);
                startActivityForResult(intent1, WRITEINTRO);
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
                final ArrayList<String> tags = data.getStringArrayListExtra(
                        "Tags");
                tfl_add_tag.setAdapter(new TagAdapter(tags) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {

                        LayoutInflater inflater
                                = (LayoutInflater) getSystemService(
                                LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.item_tag, parent,
                                false);
                        TextView tv_tag = (TextView) v.findViewById(
                                R.id.tv_tag);
                        tv_tag.setText(tags.get(position));
                        return v;
                    }
                });
                tv_pro_tag.setVisibility(View.GONE);
                tfl_add_tag.setVisibility(View.VISIBLE);
            }
        }
        if (requestCode == WRITEINTRO) {
            if (data != null) {
                tv_pro_intro.setTextSize(15);
                tv_pro_intro.setTextColor(
                        getResources().getColor(R.color.black));
                tv_pro_intro.setText(data.getStringExtra("intro"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override public void onBackPressed() {
        showMaterialDialog();
    }


    private void showMaterialDialog() {
      /*  new MaterialDialog.Builder(this).title("项目尚未成功发布，确认退出")
                                        .positiveText("继续")
                                        .onPositive(
                                                new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull
                                                                        MaterialDialog dialog,
                                                                        @NonNull
                                                                        DialogAction which) {
                                                        dialog.cancel();
                                                    }
                                                })
                                        .negativeText("退出")
                                        .onNegative(
                                                new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull
                                                                        MaterialDialog dialog,
                                                                        @NonNull
                                                                        DialogAction which) {
                                                        finish();
                                                    }
                                                })
                                        .show();*/
        mSavePopup = new SavePopup(this);
        mSavePopup.showPopupWindow();
        mSavePopup.setCallBack(new SavePopup.SetOnClickCallBack() {
            @Override public void setSave() {
                mSavePopup.dismiss();
            }


            @Override public void setCancel() {
                finish();
            }
        });
    }
}
