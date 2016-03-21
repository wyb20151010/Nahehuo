package app.nahehuo.com.ui.job;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.TagItem;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.ui.PopupFillInfo;
import app.nahehuo.com.ui.PopupList;
import app.nahehuo.com.util.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.techery.properratingbar.ProperRatingBar;
import io.techery.properratingbar.RatingListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2016/1/12.
 */
public class WriteInterExpActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Context mContext;
    private Button btn_login;
    private EditText et_comment;
    private Toolbar mToolbar;
    private TextView tv_title, tv_select;
    private TagFlowLayout tfl_comment_tag;
    private PopupList mPopupList;
    private PopupFillInfo mPopupFillInfo;
    private String[] tags = { "五险一金", "年度旅游", "团建丰富", "弹性工作时间", "免费班车", "公司班车",
            "家属日", "下午茶", "节日礼物", "生日关怀", "下午茶", "老板红包", "季度体检", "可以带宠物",
            "鼓励师" };
    private List<TagItem> mTagItems = new ArrayList<>();
    private List<String> checkTags = new ArrayList<>();

    private String[] items = { "合作伙伴", "公司客户", "员工好友", "关注者" };
    private ProperRatingBar upperRatingBar_des, upperRatingBar_env,
            upperRatingBar_space;
    private String comDesc = "4";
    private String comEnv = "4";
    private String workSpace = "4";

    private String mCid;
    private String content;
    private String releid;
    private final static int COMPANY_COMMENT_CREATE = 0;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMPANY_COMMENT_CREATE:
                    createCompanyCreate();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void createCompanyCreate() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < checkTags.size(); i++) {
            sb.append(checkTags.get(i));
        }
        String temp = sb.deleteCharAt(sb.length() - 1).toString();
        Log.d("TAG", temp);
        OkHttpUtils.post()
                   .url(GlobalVariables.COMPANY_CREATE)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .addParams("eidstr", temp)
                   .addParams("matchindex", comDesc)
                   .addParams("envindex", comEnv)
                   .addParams("auraindex", workSpace)
                   .addParams("releid", releid)
                   .addParams("content", content)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               int code = jsonObject.getInt("code");
                               if (code == 200) {
                                   mPopupFillInfo.showPopupWindow();
                                   mPopupFillInfo.setSetOnSelect(
                                           new PopupFillInfo.SetOnSelect() {
                                               @Override
                                               public void onSelect() {
                                                   finish();
                                               }
                                           });
                               }
                               else {
                                   MyToast.showToast(mContext,jsonObject
                                           .getString("message"));
                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_inter_exp);
        mContext = this;
        mPopupList = new PopupList(WriteInterExpActivity.this,
                Arrays.asList(items), "你与企业的关系");
        mPopupFillInfo = new PopupFillInfo(this, "公司评价已发布", "看看我的发布");
        Bundle bundle = getIntent().getExtras();
        mCid = bundle.getString("cid");
        tags=bundle.getStringArray("tags");
        initToolBar();
        initTagFlowLayout();
        initRatingBar();
    }


    private void initRatingBar() {
        tv_select = (TextView) findViewById(R.id.tv_select);
        tv_select.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        et_comment = (EditText) findViewById(R.id.et_comment);
        upperRatingBar_des = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_des);
        upperRatingBar_des.setListener(new RatingListener() {
            @Override public void onRatePicked(ProperRatingBar ratingBar) {
                comDesc = ratingBar.getRating() + "";
            }
        });
        upperRatingBar_env = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_env);
        upperRatingBar_env.setListener(new RatingListener() {
            @Override public void onRatePicked(ProperRatingBar ratingBar) {
                comEnv = ratingBar.getRating() + "";
            }
        });
        upperRatingBar_space = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_space);
        upperRatingBar_space.setListener(new RatingListener() {
            @Override public void onRatePicked(ProperRatingBar ratingBar) {
                workSpace = ratingBar.getRating() + "";
            }
        });
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
        tfl_comment_tag.setOnSelectListener(
                new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        checkTags.clear();
                        for (Integer integer : selectPosSet) {
                            checkTags.add(tags[integer] + ",");
                        }
                    }
                });
    }


    private void initTagDatas() {
        for (int i = 0; i < tags.length; i++) {
            TagItem item = new TagItem();
            item.setContent(tags[i]);
            item.setUnselected(true);
            item.setNumber(0);
            mTagItems.add(item);
        }
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


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (!TextUtils.isEmpty(et_comment.getText().toString()) &&
                        !TextUtils.isEmpty(tv_select.getText().toString()) &&
                        checkTags.size() > 0) {
                    content = et_comment.getText().toString();
                    mHandler.sendEmptyMessage(COMPANY_COMMENT_CREATE);
                }
                else {
                    MyToast.showToast(mContext, "请填写信息完整");
                }
                break;
            case R.id.tv_select:
                mPopupList.showPopupWindow();
                mPopupList.setFindDataCallBack(
                        new PopupList.FindDataCallBack() {
                            @Override public void findEduCallBack(String edu) {
                                if (edu.equals("合作伙伴")) {
                                    releid = 1 + "";
                                }
                                else if (edu.equals("公司客户")) {
                                    releid = 2 + "";
                                }
                                else if (edu.equals("员工好友")) {
                                    releid = 3 + "";
                                }
                                else {
                                    releid = 4 + "";
                                }

                                tv_select.setText(edu);
                                mPopupList.dismiss();
                            }
                        });
                break;
        }
    }
}
