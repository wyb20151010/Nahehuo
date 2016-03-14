package app.nahehuo.com.ui.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.AttentationAdapter;
import app.nahehuo.com.bean.ProjectPersonDict;
import app.nahehuo.com.ui.project.popup.EnsurePopup;
import app.nahehuo.com.ui.project.popup.PartnerPopup;
import app.nahehuo.com.view.MyListView;
import com.makeramen.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/18.
 */
public class ProjectDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private TagFlowLayout tfl_pro_indus, tfl_pro_tag, tfl_pro_like;
    private String[] tags = new String[] { "计算机系统通讯", "报纸杂志出版社", "移动互联网",
            "互联网金融", "建筑行业" };
    private String[] tag2s = new String[] { "城市合伙人众筹", "帅哥美女多", "移动互联网",
            "互联网金融", "一日三餐" };
    private MyListView plv_attention;
    private List<ProjectPersonDict> mPersonDicts = new ArrayList<>();
    private Context mContext;
    private List<String> picLinks = new ArrayList<>();
    private TextView tv_watch_all_atten;
    private Button btn_suc_app;
    private EnsurePopup mEnsurePopup;
    private PartnerPopup mPartnerPopup;
    private boolean isPartner = false;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        mContext = this;
        initView();
        initData();
        initToolBar();
        initTagFlow();
        initFlv();
    }


    private void initView() {
        tv_watch_all_atten = (TextView) findViewById(R.id.tv_watch_all_atten);
        tv_watch_all_atten.setOnClickListener(this);
        btn_suc_app = (Button) findViewById(R.id.btn_suc_app);
        btn_suc_app.setOnClickListener(this);
    }


    private void initFlv() {
        AttentationAdapter adapter = new AttentationAdapter(mPersonDicts,
                mContext);
        plv_attention = (MyListView) findViewById(R.id.plv_attention);
        plv_attention.setAdapter(adapter);
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
        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王浩", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
    }


    private void initTagFlow() {
        tfl_pro_indus = (TagFlowLayout) findViewById(R.id.tfl_pro_indus);
        tfl_pro_indus.setAdapter(new TagAdapter(tags) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_gray, parent,
                        false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(tags[position]);
                return v;
            }
        });
        tfl_pro_tag = (TagFlowLayout) findViewById(R.id.tfl_pro_tag);

        tfl_pro_tag.setAdapter(new TagAdapter(tag2s) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag, parent, false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(tag2s[position]);
                return v;
            }
        });
        tfl_pro_like = (TagFlowLayout) findViewById(R.id.tfl_pro_like);

        tfl_pro_like.setAdapter(new TagAdapter(picLinks) {
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


    private void initToolBar() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tv_title.setText("合伙项目详情");
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_detail, menu);
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
            case R.id.tv_watch_all_atten:
                startActivity(
                        new Intent(mContext, ProjectAllAttenActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.btn_suc_app:
                if (isPartner) {
                    mEnsurePopup = new EnsurePopup(this);
                    mEnsurePopup.showPopupWindow();
                    mEnsurePopup.setEnsureCallBack(
                            new EnsurePopup.EnsureCallBack() {
                                @Override public void setSave() {
                                    startActivity(new Intent(mContext,
                                            ProjectTalkSucActivity.class));
                                    overridePendingTransition(
                                            R.anim.push_left_in,
                                            R.anim.push_left_out);
                                }


                                @Override public void setCancel() {
                                    mEnsurePopup.dismiss();
                                }
                            });
                }
                else {
                    mPartnerPopup = new PartnerPopup(this);
                    mPartnerPopup.showPopupWindow();
                    mPartnerPopup.setEnsureCallBack(
                            new PartnerPopup.EnsureCallBack() {
                                @Override public void setSave() {

                                }


                                @Override public void setCancel() {
                                    mPartnerPopup.dismiss();
                                }
                            });
                }
                break;
        }
    }
}
