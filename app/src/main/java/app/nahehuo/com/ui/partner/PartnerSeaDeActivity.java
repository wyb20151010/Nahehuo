package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PartSearchAdapter;
import app.nahehuo.com.bean.PartSearch;
import app.nahehuo.com.util.TextUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/17.
 */
public class PartnerSeaDeActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TagFlowLayout tfl_search_tag;
    private LinearLayout ll_tags, ll_content;
    private List<String> tags = new ArrayList<>();
    private EditText et_pro_search;
    private TextView tv_cancel;
    private PullToRefreshListView flv_show_content;
    private List<PartSearch> mPartSearches = new ArrayList<>();
    private PartSearchAdapter mPartSearchAdapter;
    private Context mContext;
    private String search;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_sea_detail);
        mContext = this;
        Intent intent = getIntent();
        search = intent.getStringExtra("searchdata");

        initView();
        initData();
        initFlv();
        initTfl();
    }


    private void initFlv() {
        mPartSearchAdapter = new PartSearchAdapter(mPartSearches, mContext);
        flv_show_content = (PullToRefreshListView) findViewById(
                R.id.flv_show_content);
        flv_show_content.setAdapter(mPartSearchAdapter);
    }


    private void initData() {
        mPartSearches.add(new PartSearch("上海师范", "上海师范大学社会主义科学教育系"));
        mPartSearches.add(new PartSearch("上海师范", "上海师范大学社会主义科学教育系"));
        mPartSearches.add(new PartSearch("上海师范", "上海师范大学社会主义科学教育系"));
        mPartSearches.add(new PartSearch("上海师范", "上海师范大学社会主义科学教育系"));
        mPartSearches.add(new PartSearch("上海师范", "上海师范大学社会主义科学教育系"));
        tags.add("上海");
        tags.add("北京");
        tags.add("哈尔滨");
        tags.add("人力资源");
        tags.add("哪合伙");
        tags.add("成都");
        tags.add("美女");
        tags.add("大长腿");
    }


    private void initView() {
        et_pro_search = (EditText) findViewById(R.id.searchJobEditText);
        et_pro_search.setText(search);
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
                            showPlv();
                            return true;
                        }
                        return false;
                    }
                });
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


    private void showPlv() {
        ll_tags.setVisibility(View.GONE);
        ll_content.setVisibility(View.VISIBLE);
    }


    private void showTag() {
        ll_content.setVisibility(View.GONE);
        ll_tags.setVisibility(View.VISIBLE);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                et_pro_search.setText("");
                showTag();
                break;
        }
    }
}
