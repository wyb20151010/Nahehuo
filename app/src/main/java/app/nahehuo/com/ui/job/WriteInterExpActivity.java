package app.nahehuo.com.ui.job;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.TagItem;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.techery.properratingbar.ProperRatingBar;
import io.techery.properratingbar.RatingListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/12.
 */
public class WriteInterExpActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private TagFlowLayout tfl_comment_tag;
    private String[] tags = { "五险一金", "年度旅游", "弹性工作时间", "免费班车", "公司班车", "家属日",
            "季度体检", "可以带宠物", "鼓励师" };
    private List<TagItem> mTagItems = new ArrayList<>();
    private ProperRatingBar upperRatingBar_des, upperRatingBar_env,
            upperRatingBar_space;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_inter_exp);
        initToolBar();
        initTagFlowLayout();
        initRatingBar();
    }


    private void initRatingBar() {
        upperRatingBar_des = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_des);
        upperRatingBar_des.setListener(new RatingListener() {
            @Override public void onRatePicked(ProperRatingBar ratingBar) {
                Log.d("TAG",ratingBar.getRating()+"");
            }
        });
        upperRatingBar_env = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_env);
        upperRatingBar_space = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_space);
    }


    private void initTagFlowLayout() {
        initTagDatas();
        tfl_comment_tag = (TagFlowLayout) findViewById(R.id.tfl_comment_tag);
        tfl_comment_tag.setAdapter(new TagAdapter(mTagItems) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TagItem item = mTagItems.get(position);
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_border, parent,
                        false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(item.getContent());
                return v;
            }
        });
        tfl_comment_tag.setMaxSelectCount(3);
    }


    private void initTagDatas() {
        mTagItems.add(new TagItem("五险一金", 1, true));
        mTagItems.add(new TagItem("年度旅游", 1, true));
        mTagItems.add(new TagItem("弹性工作时间", 1, true));
        mTagItems.add(new TagItem("免费班车", 1, true));
        mTagItems.add(new TagItem("公司班车", 1, true));
        mTagItems.add(new TagItem("可以带宠物", 1, true));
        mTagItems.add(new TagItem("鼓励师", 1, true));
        mTagItems.add(new TagItem("季度体检", 1, true));
        mTagItems.add(new TagItem("家属日", 1, true));
    }


    private void initToolBar() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tv_title.setText("写面试经验");
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.job, menu);
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
