package app.nahehuo.com.ui.job;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.JobPartCommentAdapter;
import app.nahehuo.com.bean.JobCommentDict;
import app.nahehuo.com.view.MyListView;
import com.makeramen.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/13.
 */
public class JobPartTimeDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private LinearLayout ll_contacts, ll_comment;
    private Button btn_contact;
    private List<String> picLinks = new ArrayList<>();
    private Context mContext;
    private TagFlowLayout tfl_like;
    private MyListView mlv_comment;
    private JobPartCommentAdapter mCommentAdapter;
    private List<JobCommentDict> mCommentDicts = new ArrayList<>();
    private TextView tv_close, tv_open;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_parttime_detail);
        mContext = this;
        ll_contacts = (LinearLayout) findViewById(R.id.ll_contacts);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        btn_contact = (Button) findViewById(R.id.btn_contact);
        btn_contact.setOnClickListener(this);
        tv_close = (TextView) findViewById(R.id.tv_close);
        tv_close.setOnClickListener(this);
        tv_open = (TextView) findViewById(R.id.tv_open);
        tv_open.setOnClickListener(this);
        initToolBar();
        initLike();
        initComment();
    }


    private void initComment() {
        initListData();
        mCommentAdapter = new JobPartCommentAdapter(mCommentDicts, mContext);
        mlv_comment = (MyListView) findViewById(R.id.mlv_comment);
        mlv_comment.setAdapter(mCommentAdapter);
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


    private void initLike() {
        initData();
        tfl_like = (TagFlowLayout) findViewById(R.id.tfl_like);
        tfl_like.setAdapter(new TagAdapter(picLinks) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_like_pic, parent,
                        false);
                RoundedImageView imageView = (RoundedImageView) v.findViewById(
                        R.id.iv_like_pic);
                ImageLoader.getInstance()
                           .displayImage(picLinks.get(position), imageView);
                return v;
            }
        });
    }


    private void initData() {
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        picLinks.add("http://www.nahehuo.com/thumb/1/9/86/32953_middle.jpg");
    }


    private void initToolBar() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tv_title.setText("兼职详情");
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_contact:
                ll_contacts.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_close:
                ll_comment.setVisibility(View.GONE);
                break;
            case R.id.tv_open:
                ll_comment.setVisibility(View.VISIBLE);
                break;
        }
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
}
