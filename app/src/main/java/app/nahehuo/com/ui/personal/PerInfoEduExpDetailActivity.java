package app.nahehuo.com.ui.personal;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.personal.popup.DeleteEduPopup;

/**
 * Created by WYB on 2016/3/1.
 */
public class PerInfoEduExpDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private RelativeLayout rl_delete;
    private DeleteEduPopup mDeleteEduPopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_edu_detail);
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("编辑教育经历");
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
                mDeleteEduPopup = new DeleteEduPopup(this);
                mDeleteEduPopup.showPopupWindow();
                mDeleteEduPopup.setCallBack(
                        new DeleteEduPopup.SetOnClickCallBack() {
                            @Override public void setSave() {
                                //TODO
                                mDeleteEduPopup.dismiss();
                            }


                            @Override public void setCancel() {
                                mDeleteEduPopup.dismiss();
                            }
                        });
                break;
        }
    }
}
