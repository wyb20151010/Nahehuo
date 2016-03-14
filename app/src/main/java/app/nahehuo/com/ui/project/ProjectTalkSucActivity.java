package app.nahehuo.com.ui.project;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import app.nahehuo.com.R;

/**
 * Created by WYB on 2016/1/20.
 */
public class ProjectTalkSucActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private Button btn_back_list;
    private Context mContext;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_talk_suc);
        mContext = this;
        initToolbar();
        btn_back_list = (Button) findViewById(R.id.btn_back_list);
        btn_back_list.setOnClickListener(this);
    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back_list:
                finish();
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}
