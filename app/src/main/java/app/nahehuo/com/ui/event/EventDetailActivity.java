package app.nahehuo.com.ui.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.AttentationAdapter;
import app.nahehuo.com.adapter.EventDetailPicAdapter;
import app.nahehuo.com.adapter.JobPartCommentAdapter;
import app.nahehuo.com.bean.JobCommentDict;
import app.nahehuo.com.bean.ProjectPersonDict;
import app.nahehuo.com.bean.TagItemColor;
import app.nahehuo.com.view.MyListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/28.
 */
public class EventDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar mToolbar;
    private TagFlowLayout tfl_event_tag;
    private List<TagItemColor> mItemColorList = new ArrayList<>();
    private RecyclerView rv_pic;
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private List<ProjectPersonDict> mPersonDicts = new ArrayList<>();
    private MyListView plv_attention;
    private TextView tv_title_one, tv_title_two;
    private String title = "中国游戏商务大会(CGBC)---全球数字娱乐IP合作大会免费听课";
    private MyListView mlv_comment;
    private JobPartCommentAdapter mCommentAdapter;
    private List<JobCommentDict> mCommentDicts = new ArrayList<>();
    private Button btn_contact;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        mContext = this;
        initListData();
        initTitle();
        initToolbar();
        initView();
        initTag();
    }


    private void initTitle() {
        tv_title_one = (TextView) findViewById(R.id.tv_title_one);
        tv_title_two = (TextView) findViewById(R.id.tv_title_two);
        if (title.length() > 18) {
            String one = title.substring(0, 17);
            tv_title_one.setText(one);
            String two = title.substring(17, title.length());
            tv_title_two.setText(two);
        }
        else {
            tv_title_one.setText(title);
            tv_title_two.setVisibility(View.GONE);
        }
    }


    private void initTag() {
        mItemColorList.add(new TagItemColor(0, "创业"));
        mItemColorList.add(new TagItemColor(1, "会议"));
        mItemColorList.add(new TagItemColor(1, "牛逼"));
        tfl_event_tag = (TagFlowLayout) findViewById(R.id.tfl_event_tag);
        tfl_event_tag.setAdapter(new TagAdapter(mItemColorList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_green, parent,
                        false);
                LinearLayout ll_tag = (LinearLayout) v.findViewById(
                        R.id.ll_tag);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                if (mItemColorList.get(position).getColor() == 0) {
                    ll_tag.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.checked_bg_green));
                }
                else {
                    ll_tag.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.checked_bg_red));
                }
                tv_tag.setText(mItemColorList.get(position).getContent());
                return v;
            }
        });
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
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mList.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王浩", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        EventDetailPicAdapter adapter = new EventDetailPicAdapter(mContext,
                mList);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("活动详情");
        rv_pic = (RecyclerView) findViewById(R.id.rv_pic);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_pic.setLayoutManager(manager);
        rv_pic.setAdapter(adapter);

        adapter.setCallBack(new EventDetailPicAdapter.OnclickCallBack() {
            @Override public void setItemOnclick(View v, int position) {
                startActivity(new Intent(mContext, EventGalleryActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        });

        AttentationAdapter madapter = new AttentationAdapter(mPersonDicts,
                mContext);
        plv_attention = (MyListView) findViewById(R.id.plv_attention);
        plv_attention.setAdapter(madapter);

        mlv_comment = (MyListView) findViewById(R.id.mlv_comment);
        mCommentAdapter = new JobPartCommentAdapter(mCommentDicts, mContext);
        mlv_comment = (MyListView) findViewById(R.id.mlv_comment);
        mlv_comment.setAdapter(mCommentAdapter);

        btn_contact = (Button) findViewById(R.id.btn_contact);
        btn_contact.setOnClickListener(this);
    }


    private void initListData() {
        mCommentDicts.add(new JobCommentDict("张天爱", "太子妃升职记真好看"));
        mCommentDicts.add(new JobCommentDict("张天爱", "盛一伦", "太子妃升职记烂片"));
        mCommentDicts.add(new JobCommentDict("盛一伦", "盛一伦", "太子妃升职记"));
        mCommentDicts.add(new JobCommentDict("王爱华", "盛一伦", "太子妃升职记"));
        mCommentDicts.add(
                new JobCommentDict("张天爱", "太子妃升职记真好看升职记真好看升职记真好看升职记真好看升职记真好看"));
        mCommentDicts.add(new JobCommentDict("盛一伦", "盛一伦", "太子妃升职记"));
        mCommentDicts.add(new JobCommentDict("张天爱", "盛一伦", "太子妃升职记"));
        mCommentDicts.add(new JobCommentDict("盛一伦", "太子妃升职记真好看"));
        mCommentDicts.add(new JobCommentDict("王塬鑫", "盛一伦", "太子妃升职记"));
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_detail, menu);
        return super.onCreateOptionsMenu(menu);
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
            case R.id.btn_contact:
                startActivity(new Intent(mContext, EventPayActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}
