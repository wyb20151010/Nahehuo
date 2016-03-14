package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerInterDetailAdapter;
import app.nahehuo.com.bean.PerInterDetail;
import app.nahehuo.com.ui.personal.popup.GoPopup;
import app.nahehuo.com.ui.personal.popup.NotGoPopup;
import app.nahehuo.com.view.MyListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/2.
 */
public class PerInterviewDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private MyListView plv_progress;
    private List<PerInterDetail> mPerInterDetails = new ArrayList<>();
    private PerInterDetailAdapter mAdapter;
    private Button btn_not_go, btn_go;
    private NotGoPopup mNotGoPopup;
    private GoPopup mGoPopup;
    private LinearLayout ll_choose, ll_give_up, ll_ok;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_iv_detail);
        mContext = this;
        initData();
        initView();
        initToolbar();
    }


    private void initData() {

        mPerInterDetails.add(
                new PerInterDetail("1", "HR发来面试通知函", "2015-12-08 19:12:23",
                        "2014-06-05 10:30",
                        "上海市松江区九新公路341弄28号华西大楼7层(地铁:9号线九亭5号出口；公交:43路)",
                        "张科(021-52214121)"));
        mPerInterDetails.add(
                new PerInterDetail("2", "HR转发了你的简历", "2015-12-08 19:12:23", "",
                        "", ""));
        mPerInterDetails.add(
                new PerInterDetail("2", "HR转发了你的简历", "2015-12-08 19:12:23",
                        "2014-06-05 10:30",
                        "上海市松江区九新公路341弄28号华西大楼7层(地铁:9号线九亭5号出口；公交:43路)",
                        "张科(021-52214121)"));
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


    private void initView() {
        mAdapter = new PerInterDetailAdapter(mPerInterDetails, mContext);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的面试详情");
        plv_progress = (MyListView) findViewById(R.id.plv_progress);
        plv_progress.setAdapter(mAdapter);
        btn_not_go = (Button) findViewById(R.id.btn_not_go);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_not_go.setOnClickListener(this);
        btn_go.setOnClickListener(this);

        ll_choose = (LinearLayout) findViewById(R.id.ll_choose);
        ll_give_up = (LinearLayout) findViewById(R.id.ll_give_up);
        ll_ok = (LinearLayout) findViewById(R.id.ll_ok);
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
            case R.id.btn_not_go:
                mNotGoPopup = new NotGoPopup(this);
                mNotGoPopup.showPopupWindow();
                mNotGoPopup.setCallBack(new NotGoPopup.SetOnClickCallBack() {
                    @Override public void setSave() {
                        mNotGoPopup.dismiss();
                        ll_choose.setVisibility(View.INVISIBLE);
                        ll_give_up.setVisibility(View.VISIBLE);
                    }


                    @Override public void setCancel() {
                        mNotGoPopup.dismiss();
                    }
                });
                break;
            case R.id.btn_go:
                mGoPopup = new GoPopup(this);
                mGoPopup.showPopupWindow();
                mGoPopup.setCallBack(new GoPopup.SetOnClickCallBack() {
                    @Override public void setSave() {
                        mGoPopup.dismiss();
                        ll_choose.setVisibility(View.INVISIBLE);
                        ll_ok.setVisibility(View.VISIBLE);
                    }


                    @Override public void setCancel() {
                        mGoPopup.dismiss();
                    }
                });
                break;
        }
    }
}
