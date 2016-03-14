package app.nahehuo.com.ui.partner;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PartnerInfoWorkInfoAdapter;
import app.nahehuo.com.bean.WorkInfo;
import app.nahehuo.com.ui.personal.PerInfoEditTagActivity;
import app.nahehuo.com.ui.personal.PerInfoEditActivity;
import app.nahehuo.com.view.MyListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/24.
 */
public class PartnerInfoActivity extends AppCompatActivity
        implements View.OnClickListener {

    protected Toolbar mToolbar;
    private Context mContext;
    private TagFlowLayout tfl_person_tag, tfl_person_want, tfl_person_need;
    private List<String> mStrings = new ArrayList<>();
    private List<String> wants = new ArrayList<>();
    private List<String> needs = new ArrayList<>();
    private ArrayList<WorkInfo> mWorkInfos = new ArrayList<>();
    private ArrayList<WorkInfo> mWorkInfos2 = new ArrayList<>();
    private ArrayList<WorkInfo> mWorkInfosLike = new ArrayList<>();
    private ArrayList<WorkInfo> mWorkInfos2Like = new ArrayList<>();

    private MyListView lv_work_exp, lv_edu_exp;
    private PartnerInfoWorkInfoAdapter mInfoAdapter;
    private PartnerInfoWorkInfoAdapter mInfoAdapter2;
    private LinearLayout ll_wall_badge, ll_same_fri;
    private RelativeLayout rl_add_fri, rl_expand_edu, rl_expand_work;
    private boolean isClicked = false;
    private boolean isClicked2 = false;
    private boolean isHomepage;
    private LinearLayout ll_who_see_me, ll_bottom;
    private static final int TAGS = 1;
    private TagAdapter<String> mTagAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_info);
        mContext = this;
        Intent intent = getIntent();
        isHomepage = intent.getBooleanExtra("Homepage", false);
        initToolbar();
        initData();
        initView();
    }


    private void initData() {
        mStrings.add("新媒体 9");
        mStrings.add("程序员 5");
        mStrings.add("酷爱产品 9");
        mStrings.add("大水哥 9");
        mStrings.add("一个无下限的程序员 9");
        if (isHomepage) {
            mStrings.add("+ 添加标签");
        }
        wants.add("10年开发经验 技术合伙");
        wants.add("项目场地");
        wants.add("政府关系");
        needs.add("彼此信任");
        needs.add("城市合伙加盟");
        needs.add("技术，市场，运营合伙人");
        mWorkInfos.add(
                new WorkInfo("高级产品经理", "今翌信息科技（上海）有限公司", "2014.3-至今 1年8个月"));
        mWorkInfos.add(new WorkInfo("高级产品经理", "腾讯科技有限公司", "2014.3-至今 1年8个月"));
        mWorkInfos.add(
                new WorkInfo("高级产品经理", "今翌信息科技（上海）有限公司", "2014.3-至今 1年8个月"));
        mWorkInfos.add(
                new WorkInfo("高级产品经理", "今翌信息科技（上海）有限公司", "2014.3-至今 1年8个月"));
        mWorkInfos.add(new WorkInfo("高级产品经理", "腾讯科技有限公司", "2014.3-至今 1年8个月"));
        mWorkInfos.add(
                new WorkInfo("高级产品经理", "今翌信息科技（上海）有限公司", "2014.3-至今 1年8个月"));
        mWorkInfos2.add(new WorkInfo("黑龙江大学", "计算机科学与技术（硕士）", "2005.9-2009.7"));
        mWorkInfos2.add(new WorkInfo("黑龙江大学", "计算机科学与技术（本科）", "2005.9-2009.7"));
        mWorkInfos2.add(new WorkInfo("黑龙江大学", "计算机科学与技术（大专）", "2005.9-2009.7"));
        mWorkInfos2.add(new WorkInfo("黑龙江大学", "计算机科学与技术（硕士）", "2005.9-2009.7"));
        mWorkInfos2.add(new WorkInfo("黑龙江大学", "计算机科学与技术（本科）", "2005.9-2009.7"));
        mWorkInfos2.add(new WorkInfo("黑龙江大学", "计算机科学与技术（大专）", "2005.9-2009.7"));

        for (int i = 0; i < mWorkInfos.size(); i++) {
            mWorkInfosLike.add(i, mWorkInfos.get(i));
        }
        for (int i = 0; i < mWorkInfos2.size(); i++) {
            mWorkInfos2Like.add(i, mWorkInfos2.get(i));
        }
        copyListFromTo(mWorkInfosLike, mWorkInfos);
        copyListFromTo(mWorkInfos2Like, mWorkInfos2);
    }


    private void initView() {
        mTagAdapter = new TagAdapter(mStrings) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                TextView v = (TextView) inflater.inflate(R.layout.item_tag_bule,
                        parent, false);
                v.setText(mStrings.get(position));
                return v;
            }
        };
        tfl_person_tag = (TagFlowLayout) findViewById(R.id.tfl_person_tag);
        tfl_person_tag.setAdapter(mTagAdapter);
        tfl_person_tag.setOnTagClickListener(
                new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        if (isHomepage && position == mStrings.size() - 1) {
                            startActivityForResult(new Intent(mContext,
                                    PerInfoEditTagActivity.class), TAGS);
                            overridePendingTransition(R.anim.push_left_in,
                                    R.anim.push_left_out);
                        }
                        return false;
                    }
                });
        tfl_person_want = (TagFlowLayout) findViewById(R.id.tfl_person_want);
        tfl_person_want.setAdapter(new TagAdapter(wants) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                TextView v = (TextView) inflater.inflate(
                        R.layout.item_tag_border_black, parent, false);
                v.setText(wants.get(position));
                return v;
            }
        });
        tfl_person_need = (TagFlowLayout) findViewById(R.id.tfl_person_need);
        tfl_person_need.setAdapter(new TagAdapter(needs) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                TextView v = (TextView) inflater.inflate(
                        R.layout.item_tag_border_black, parent, false);
                v.setText(needs.get(position));
                return v;
            }
        });

        mInfoAdapter = new PartnerInfoWorkInfoAdapter(mWorkInfos, mContext);
        lv_work_exp = (MyListView) findViewById(R.id.lv_work_exp);
        lv_work_exp.setAdapter(mInfoAdapter);
        mInfoAdapter2 = new PartnerInfoWorkInfoAdapter(mWorkInfos2, mContext);
        lv_edu_exp = (MyListView) findViewById(R.id.lv_edu_exp);
        lv_edu_exp.setAdapter(mInfoAdapter2);

        ll_wall_badge = (LinearLayout) findViewById(R.id.ll_wall_badge);
        ll_wall_badge.setOnClickListener(this);
        rl_add_fri = (RelativeLayout) findViewById(R.id.rl_add_fri);
        rl_add_fri.setOnClickListener(this);

        rl_expand_work = (RelativeLayout) findViewById(R.id.rl_expand_work);
        rl_expand_edu = (RelativeLayout) findViewById(R.id.rl_expand_edu);
        rl_expand_work.setOnClickListener(this);
        rl_expand_edu.setOnClickListener(this);
        ll_same_fri = (LinearLayout) findViewById(R.id.ll_same_fri);
        ll_who_see_me = (LinearLayout) findViewById(R.id.ll_who_see_me);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        if (isHomepage) {
            ll_same_fri.setVisibility(View.GONE);
            ll_who_see_me.setVisibility(View.VISIBLE);
            ll_bottom.setVisibility(View.GONE);
        }
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


    private void copyListFromTo(ArrayList<WorkInfo> from, ArrayList<WorkInfo> to) {
        to.clear();
        for (int i = 0; i < from.size(); i++) {
            if (i <= 1) {
                to.add(i, from.get(i));
            }
        }
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {

        if (isHomepage) {
            getMenuInflater().inflate(R.menu.partneredit, menu);
        }
        else {
            getMenuInflater().inflate(R.menu.partnerinfo, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.more:
                startActivity(
                        new Intent(mContext, PartnerMoreSetActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.edit:
                startActivity(
                        new Intent(mContext, PerInfoEditActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_wall_badge:
                startActivity(
                        new Intent(mContext, PartnerWallBadgeActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;

            case R.id.rl_add_fri:
                startActivity(
                        new Intent(mContext, PartnerAddFriActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_expand_work:
                if (!isClicked) {
                    for (int i = 0; i < mWorkInfosLike.size(); i++) {
                        mWorkInfos.add(i, mWorkInfosLike.get(i));
                    }
                    mInfoAdapter.notifyDataSetChanged();
                    isClicked = true;
                }
                else {
                    copyListFromTo(mWorkInfosLike, mWorkInfos);
                    mInfoAdapter.notifyDataSetChanged();
                    isClicked = false;
                }

                break;
            case R.id.rl_expand_edu:
                if (!isClicked2) {
                    for (int i = 0; i < mWorkInfos2Like.size(); i++) {
                        mWorkInfos2.add(i, mWorkInfos2Like.get(i));
                    }
                    mInfoAdapter2.notifyDataSetChanged();
                    isClicked2 = true;
                }
                else {
                    copyListFromTo(mWorkInfos2Like, mWorkInfos2);
                    mInfoAdapter2.notifyDataSetChanged();
                    isClicked2 = false;
                }

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAGS:
                if (PerInfoEditTagActivity.RESULT_OK == resultCode) {
                    List<String> tags = data.getStringArrayListExtra("Tags");
                    mStrings.remove(mStrings.size() - 1);
                    for (String each : tags) {
                        mStrings.add(each);
                    }
                    mStrings.add("+ 添加标签");
                    mTagAdapter.notifyDataChanged();
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
