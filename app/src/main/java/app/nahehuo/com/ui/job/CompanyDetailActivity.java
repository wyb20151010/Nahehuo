package app.nahehuo.com.ui.job;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.MyViewPagerAdapter;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.NetCompanyDetail;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.fragment.CompCommentFragment;
import app.nahehuo.com.ui.job.fragment.CompHomePageFragment;
import app.nahehuo.com.ui.job.fragment.CompJobFragment;
import app.nahehuo.com.ui.job.fragment.CompProgressFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompanyDetailActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private ImageView iv_logo;
    private TextView tv_name;
    private TextView tv_title;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager vp_content;
    private Context mContext;

    private String[] titles = { "主页", "职位", "历程", "点评" };
    private MyViewPagerAdapter mMyViewPagerAdapter;
    private TagAdapter<List<String>> tagAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private TagFlowLayout tfl_company_tag;
    private List<String> tags = new ArrayList<>();
    private final static int SHOW_COMPANY_DETAIL = 0;
    private int cid;
    private ConvertToHomepage mConvertToHomepage;
    private ConvertToComJob mConvertToComJob;
    private ConvertToComHistory mComHistory;
    private ConvertToComComment mComComment;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_COMPANY_DETAIL:
                    showCompanyDetail();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void showCompanyDetail() {
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_DETAIL)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", cid + "")
                   .build()
                   .execute(new GsonCallBack<NetCompanyDetail>(
                           NetCompanyDetail.class) {
                       @Override
                       public void onResponse(NetCompanyDetail response) {
                           if (response.getCode() == 200) {
                               ratingBar.setRating(
                                       response.getData().getAvgcomment());
                               tv_name.setText(response.getData().getCompany());
                               ImageLoader.getInstance()
                                          .displayImage(
                                                  response.getData().getLogo(),
                                                  iv_logo,
                                                  MyApplication.getDisplayDefaultOption());
                               tags.add(response.getData()
                                                .getFinancle()
                                                .equalsIgnoreCase("0")
                                        ? "未融资"
                                        : response.getData().getFinancle());
                               if (!TextUtils.isEmpty(
                                       response.getData().getProv()) ||
                                       !TextUtils.isEmpty(
                                               response.getData().getCity())) {
                                   tags.add(response.getData().getProv() +
                                           response.getData().getCity());
                               }
                               tags.add(response.getData()
                                                .getIndustry()
                                                .equals("0")
                                        ? "行业未知"
                                        : response.getData().getIndustry());
                               tags.add(response.getData().getSize().equals("0")
                                        ? "0-10人"
                                        : response.getData().getSize());
                               if (!TextUtils.isEmpty(
                                       response.getData().getWebsite())) {
                                   tags.add(response.getData().getWebsite());
                               }
                               tagAdapter.notifyDataChanged();
                               mConvertToHomepage.convertToHomepage(cid + "",
                                       response.getData().getContent(),
                                       response.getData().getAddress());
                               mConvertToComJob.convertToComJob(cid + "");
                               mComHistory.convertToComHistory(cid + "");
                               mComComment.convertToComComment(cid+"");
                           }
                           else {
                               /*MyToast.showToast(mContext,
                                       response.getMessage());*/
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        mContext = this;
        Intent intent = getIntent();
        cid = intent.getIntExtra("cid", 0);
        initToolBar();
        initFragment();
        initTabLayout();
        initView();
        mHandler.sendEmptyMessage(SHOW_COMPANY_DETAIL);
    }


    private void initView() {
        tfl_company_tag = (TagFlowLayout) findViewById(R.id.tfl_company_tag);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tagAdapter = new TagAdapter(tags) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag, parent, false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(tags.get(position));
                return v;
            }
        };

        tfl_company_tag.setAdapter(tagAdapter);
    }


    private void initFragment() {
        mFragments.add(new CompHomePageFragment());
        mFragments.add(new CompJobFragment());
        mFragments.add(new CompProgressFragment());
        mFragments.add(new CompCommentFragment());
    }


    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        mMyViewPagerAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager(), mFragments);
        for (int i = 0; i < titles.length; i++) {
            mMyViewPagerAdapter.addItem(titles[i]);
            mTabLayout.addTab(mTabLayout.newTab());
        }
        vp_content.setAdapter(mMyViewPagerAdapter);
        mTabLayout.setupWithViewPager(vp_content);
        vp_content.setOffscreenPageLimit(4);
        mTabLayout.setTabsFromPagerAdapter(mMyViewPagerAdapter);
    }


    private void initToolBar() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("企业主页");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setConvertToHomepage(ConvertToHomepage convertToHomepage) {
        mConvertToHomepage = convertToHomepage;
    }


    public void setConvertToComJob(ConvertToComJob convertToComJob) {
        mConvertToComJob = convertToComJob;
    }


    public void setComHistory(ConvertToComHistory comHistory) {
        mComHistory = comHistory;
    }


    public void setComComment(ConvertToComComment comComment) {
        mComComment = comComment;
    }


    public interface ConvertToHomepage {
        void convertToHomepage(String cid, String content, String address);
    }

    public interface ConvertToComJob {
        void convertToComJob(String cid);
    }

    public interface ConvertToComHistory {
        void convertToComHistory(String cid);
    }

    public interface ConvertToComComment {
        void convertToComComment(String cid);
    }
}
