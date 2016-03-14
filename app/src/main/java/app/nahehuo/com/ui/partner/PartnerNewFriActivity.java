package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PartnerNewFriAdapter;
import app.nahehuo.com.bean.PartnerNewFriend;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/18.
 */
public class PartnerNewFriActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private ListView lv_new_friend;
    private PartnerNewFriAdapter mAdapter;
    private Context mContext;
    private List<PartnerNewFriend> mNewFriends = new ArrayList<>();


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_new_fri);
        mContext = this;
        initToolbar();
        initData();
        initView();
    }


    private void initData() {
        mNewFriends.add(new PartnerNewFriend("安安", "创始人CEO",
                "今翌信息科技(上海)有限公司今翌信息科技(上海)有限公司今翌信息科技(上海)有限公司今翌信息科技(上海"));
        mNewFriends.add(new PartnerNewFriend("安安", "创始人CEO",
                "聪明的老板和有钱的员工谈理想，和没钱的员工谈钱"));
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
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("新的好友");
        lv_new_friend = (ListView) findViewById(R.id.lv_new_friend);
        mAdapter = new PartnerNewFriAdapter(mNewFriends, mContext);
        lv_new_friend.setAdapter(mAdapter);
        mAdapter.setDeleteItemCallBack(
                new PartnerNewFriAdapter.DeleteItemCallBack() {
                    @Override
                    public void onDeletePositon(View v, int position) {
                        mNewFriends.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
