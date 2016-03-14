package app.nahehuo.com.ui.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.FindPartnerAdapter;
import app.nahehuo.com.bean.ProjectListDict;
import app.nahehuo.com.util.TextUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/21.
 */
public class ProjectSearchActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TagFlowLayout tfl_search_tag;
    private List<String> tags = new ArrayList<>();
    private EditText et_pro_search;
    private PullToRefreshListView flv_show_content;
    private FindPartnerAdapter mPartnerAdapter;
    private List<ProjectListDict> mProjectListDicts = new ArrayList<>();
    private Context mContext;
    private LinearLayout ll_tags, ll_content;
    private TextView tv_cancel;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_search);
        mContext = this;
        initView();
        initData();
        initPlv();
        initTfl();
    }


    private void initPlv() {
        mPartnerAdapter = new FindPartnerAdapter(mProjectListDicts, mContext);
        flv_show_content = (PullToRefreshListView) findViewById(
                R.id.flv_show_content);
        flv_show_content.setMode(PullToRefreshBase.Mode.BOTH);
        flv_show_content.setAdapter(mPartnerAdapter);
        flv_show_content.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(mContext,
                                ProjectDetailActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.push_left_in,
                                R.anim.push_left_out);
                    }
                });
    }


    private void initView() {
        et_pro_search = (EditText) findViewById(R.id.et_pro_search);
        ll_tags = (LinearLayout) findViewById(R.id.ll_tags);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        et_pro_search.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH &&
                                !TextUtil.isEmpty(
                                        et_pro_search.getText().toString())) {
                            hideSoftInput();
                            showPlv();
                            return true;
                        }
                        return false;
                    }
                });
    }


    private void initData() {
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "就差写安卓的了"));
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "寻求合伙人"));
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "就差写PHP的了"));
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "就差写代码的了"));
        tags.add("上海");
        tags.add("北京");
        tags.add("哈尔滨");
        tags.add("人力资源");
        tags.add("哪合伙");
        tags.add("成都");
        tags.add("美女");
        tags.add("大长腿");
    }


    private void initTfl() {

        tfl_search_tag = (TagFlowLayout) findViewById(R.id.tfl_search_tag);
        tfl_search_tag.setAdapter(new TagAdapter(tags) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_border, parent,
                        false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(tags.get(position));
                return v;
            }
        });
        tfl_search_tag.setOnTagClickListener(
                new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        et_pro_search.setText(tags.get(position));

                        showPlv();
                        return false;
                    }
                });
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                et_pro_search.setText("");
                break;
        }
    }


    private void showPlv() {
        ll_tags.setVisibility(View.GONE);
        ll_content.setVisibility(View.VISIBLE);
    }


    private void hideSoftInput() {
        InputMethodManager inputMethodManager
                = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), 0);
    }
}
