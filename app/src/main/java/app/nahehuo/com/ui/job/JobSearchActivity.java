package app.nahehuo.com.ui.job;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.MyViewPagerAdapter;
import app.nahehuo.com.ui.job.fragment.JobSearchComFragment;
import app.nahehuo.com.ui.job.fragment.JobSearchPosFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/5.
 */
public class JobSearchActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView cancelTextView;
    private Context mContext;
    private PullToRefreshListView plv_search;
    private EditText searchJobEditText;
    private String[] titles = { "职位", "公司" };
    private String keyWord;
    private TabLayout mTabLayout;
    private ViewPager vp_content;
    private MyViewPagerAdapter mMyViewPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private JobSearchPosFragment mJobSearchPosFragment;
    private JobSearchComFragment mJobSearchComFragment;
    private ConvertData mConvertData;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);
        mContext = this;
        initData();
        initView();
        initTabLayout();

    }


    private void initData() {
        mJobSearchPosFragment = new JobSearchPosFragment();
        mJobSearchComFragment = new JobSearchComFragment();
        mFragments.add(mJobSearchPosFragment);
        mFragments.add(mJobSearchComFragment);
    }


    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        mMyViewPagerAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager(), mFragments);
        for (int i = 0; i < mFragments.size(); i++) {
            mMyViewPagerAdapter.addItem(titles[i]);
            mTabLayout.addTab(mTabLayout.newTab());
        }
        vp_content.setAdapter(mMyViewPagerAdapter);
        mTabLayout.setupWithViewPager(vp_content);
        vp_content.setOffscreenPageLimit(2);
        mTabLayout.setTabsFromPagerAdapter(mMyViewPagerAdapter);
    }


    private void initView() {
        cancelTextView = (TextView) findViewById(R.id.cancelTextView);
        cancelTextView.setOnClickListener(this);
        searchJobEditText = (EditText) findViewById(R.id.searchJobEditText);
        searchJobEditText.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                (event != null && event.getKeyCode() ==
                                        KeyEvent.KEYCODE_SEARCH)) {
                            if (!TextUtils.isEmpty(
                                    searchJobEditText.getText().toString())) {
                                keyWord = searchJobEditText.getText()
                                                           .toString();
                                mConvertData.convertData(keyWord);
                            }
                            return true;
                        }
                        return false;
                    }
                });
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelTextView:
                searchJobEditText.setText("");
                break;
        }
    }


    public void setConvertData(ConvertData convertData) {
        mConvertData = convertData;
    }


    public interface ConvertData{
        void convertData(String data);
    }
}
