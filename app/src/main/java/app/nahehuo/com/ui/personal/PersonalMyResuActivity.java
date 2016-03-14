package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerMyResAdapter;
import app.nahehuo.com.bean.PersonWorkExp;
import app.nahehuo.com.ui.job.JobSubscribeActivity;
import app.nahehuo.com.ui.partner.PartnerContactWayActivity;
import app.nahehuo.com.view.MyListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/2.
 */
public class PersonalMyResuActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private MyListView mlv_work_exp, mlv_edu_exp;
    private List<PersonWorkExp> mPersonWorkExps = new ArrayList<>();
    private PerMyResAdapter mPerMyResAdapter;
    private RelativeLayout rl_edit_base_info, rl_contact_way, rl_want_type,
            rl_work_exp, rl_edu_exp;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_my_resu);
        mContext = this;
        initData();
        initView();
        initToolbar();
    }


    private void initData() {
        mPersonWorkExps.add(
                new PersonWorkExp("2014.3-至今", "高级产品经理", "今翌信息科技有限"));
        mPersonWorkExps.add(
                new PersonWorkExp("2014.3-至今", "高级产品经理", "今翌信息科技有限"));
        mPersonWorkExps.add(
                new PersonWorkExp("2014.3-至今", "高级产品经理", "今翌信息科技有限"));
    }


    private void initView() {

        mPerMyResAdapter = new PerMyResAdapter(mPersonWorkExps, mContext);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("编辑简历");
        mlv_work_exp = (MyListView) findViewById(R.id.mlv_work_exp);
        mlv_edu_exp = (MyListView) findViewById(R.id.mlv_edu_exp);
        mlv_work_exp.setAdapter(mPerMyResAdapter);
        mlv_edu_exp.setAdapter(mPerMyResAdapter);

        rl_edit_base_info = (RelativeLayout) findViewById(
                R.id.rl_edit_base_info);
        rl_contact_way = (RelativeLayout) findViewById(R.id.rl_contact_way);
        rl_want_type = (RelativeLayout) findViewById(R.id.rl_want_type);
        rl_work_exp = (RelativeLayout) findViewById(R.id.rl_work_exp);
        rl_edu_exp = (RelativeLayout) findViewById(R.id.rl_edu_exp);
        rl_edit_base_info.setOnClickListener(this);
        rl_contact_way.setOnClickListener(this);
        rl_want_type.setOnClickListener(this);
        rl_work_exp.setOnClickListener(this);
        rl_edu_exp.setOnClickListener(this);
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
        getMenuInflater().inflate(R.menu.preview, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.preview:
                startActivity(
                        new Intent(mContext, PerResumePreviewActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_edit_base_info:
                startActivity(new Intent(mContext, PerInfoEditActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_contact_way:
                startActivity(
                        new Intent(mContext, PartnerContactWayActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_want_type:
                startActivity(new Intent(mContext, JobSubscribeActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_work_exp:
                Intent intent = new Intent(mContext,
                        PerInfoWkExpListActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.rl_edu_exp:
                Intent intent1 = new Intent(mContext,
                        PerInfoWkExpListActivity.class);
                intent1.putExtra("type", "2");
                startActivity(intent1);
                break;
        }
    }
}
