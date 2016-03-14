package app.nahehuo.com.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.WorkExp;
import app.nahehuo.com.ui.personal.popup.DeleteWorkPopup;

/**
 * Created by WYB on 2016/3/1.
 */
public class PerInfoWkExpDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private WorkExp mWorkExp;
    private EditText et_company, et_position;
    private RelativeLayout rl_delete;
    private DeleteWorkPopup mDeleteWorkPopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_wk_detail);
        Intent intent = getIntent();
        mWorkExp = (WorkExp) intent.getSerializableExtra("data");
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("编辑工作经历");
        et_company = (EditText) findViewById(R.id.et_company);
        et_position = (EditText) findViewById(R.id.et_position);
        if (mWorkExp != null) {
            et_company.setText(mWorkExp.getCompany());
            et_position.setText(mWorkExp.getPosition());
        }
        rl_delete = (RelativeLayout) findViewById(R.id.rl_delete);
        rl_delete.setOnClickListener(this);
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


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_delete:
                mDeleteWorkPopup = new DeleteWorkPopup(this);
                mDeleteWorkPopup.showPopupWindow();
                mDeleteWorkPopup.setCallBack(
                        new DeleteWorkPopup.SetOnClickCallBack() {
                            @Override public void setSave() {
                                //TODO
                                mDeleteWorkPopup.dismiss();
                            }

                            @Override public void setCancel() {
                                mDeleteWorkPopup.dismiss();
                            }
                        });
        }
    }
}
