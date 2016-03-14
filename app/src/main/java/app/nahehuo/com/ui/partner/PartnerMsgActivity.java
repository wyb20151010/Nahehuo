package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PartnerMsgAdapter;
import app.nahehuo.com.bean.PartnerMsg;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/23.
 */
public class PartnerMsgActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected TextView tv_title;
    private Context mContext;
    private PullToRefreshListView plv_content;
    private List<PartnerMsg> mMsgs = new ArrayList<>();
    private PartnerMsgAdapter mAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_msg);
        mContext = this;
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("哪合伙小秘书");
        initToolbar();
        initData();
        initView();
    }


    private void initData() {
        mMsgs.add(new PartnerMsg("你是疯子，我是傻子家人焦急万分，遍寻无果，求好心人提供线索", false));
        mMsgs.add(new PartnerMsg("缠缠绵绵到天涯家人焦急万分，遍寻无果，求好心人提供线索", true));
        mMsgs.add(new PartnerMsg("缠缠绵绵到天涯家人焦急万分，遍寻无果，求好心人提供线索", true));
        mMsgs.add(new PartnerMsg("你是疯子，我是傻子家人焦急万分，遍寻无果，求好心人提供线索", false));
    }


    private void initView() {
        mAdapter = new PartnerMsgAdapter(mMsgs, mContext);
        plv_content = (PullToRefreshListView) findViewById(R.id.plv_content);
        plv_content.setAdapter(mAdapter);
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
        getMenuInflater().inflate(R.menu.partnermsg, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.setting:
                startActivity(
                        new Intent(mContext, PartnerMsgSetActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
