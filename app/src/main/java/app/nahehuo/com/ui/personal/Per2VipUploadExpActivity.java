package app.nahehuo.com.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerWorkExp;
import app.nahehuo.com.ui.personal.popup.UploadPicPopup;

/**
 * Created by wyb on 2016/2/29.
 */
public class Per2VipUploadExpActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private PerWorkExp mPerWorkExp;
    private TextView tv_subtitle, tv_time, tv_position, tv_company, tv_word_one,
            tv_word_two, tv_word_three;
    private Button btn_certificate;
    private UploadPicPopup mUploadPicPopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_upload_exp);
        Intent intent = getIntent();
        mPerWorkExp = (PerWorkExp) intent.getSerializableExtra("type");

        initToolbar();
        initView();
    }


    private void initView() {
        btn_certificate = (Button) findViewById(R.id.btn_certificate);
        tv_subtitle = (TextView) findViewById(R.id.tv_subtitle);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_position = (TextView) findViewById(R.id.tv_position);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_word_one = (TextView) findViewById(R.id.tv_word_one);
        tv_word_two = (TextView) findViewById(R.id.tv_word_two);
        tv_word_three = (TextView) findViewById(R.id.tv_word_three);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("上传认证资料");
        if (mPerWorkExp.getEducation()) {
            tv_subtitle.setText("认证教育经历，提高身份信用度，获得更多优质机会");
            tv_word_one.setText("上传教育经历证明");
            tv_word_two.setText("学位证，毕业证任选一种");
            tv_word_three.setText("确保学校，姓名，证书编号清晰可见，如含有隐含信息，上传或拍摄前注意局部遮挡");
        }
        tv_time.setText(mPerWorkExp.getTime());
        tv_position.setText(mPerWorkExp.getPosition());
        tv_company.setText(mPerWorkExp.getCompany());
        if ("1".equals(mPerWorkExp.getType())) {
            btn_certificate.setText("已认证");
            btn_certificate.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.checked_bg_green));
        }
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


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.uploadexp, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.done:
                mUploadPicPopup = new UploadPicPopup(this);
                mUploadPicPopup.showPopupWindow();
                mUploadPicPopup.setConfirmCallBack(
                        new UploadPicPopup.ConfirmCallBack() {
                            @Override public void getConfirmCallback(View v) {
                                finish();
                            }
                        });
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
