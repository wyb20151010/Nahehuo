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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.DBHelp;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.DictCity;
import app.nahehuo.com.bean.DictIndustry;
import app.nahehuo.com.bean.DictIntention;
import app.nahehuo.com.bean.DictPositionCate;
import app.nahehuo.com.bean.DictSalary;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.ui.Popup2List;
import app.nahehuo.com.ui.Popup2ListIndus;
import app.nahehuo.com.ui.PopupList;
import app.nahehuo.com.ui.PopupListFixed;
import app.nahehuo.com.util.CheckTextFormatUtil;
import app.nahehuo.com.util.MyToast;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2015/12/25.
 */
public class JobSubscribeActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText et_email;
    private Toolbar mToolbar;
    private TextView tv_title;
    private Button btn_save, btn_cancel;
    private Context mContext;
    private LinearLayout ll_fulltime, ll_study;
    private TextView tv_fulltime, tv_study, tv_intention, tv_salary, tv_city,
            tv_cate, tv_ind;

    private List<DictIndustry> mIndustries = new ArrayList<>();
    private List<DictIndustry> industries_one_level = new ArrayList<>();
    private List<DictIndustry> industries_two_level = new ArrayList<>();
    private List<DictPositionCate> mPositionCates = new ArrayList<>();
    private List<String> mPositionStrings = new ArrayList<>();
    private List<DictCity> mCities = new ArrayList<>();
    private List<DictCity> cities_one_level = new ArrayList<>();
    private List<DictCity> cities_two_level = new ArrayList<>();
    private List<DictSalary> mSalaries = new ArrayList<>();
    private List<String> mSalaryString = new ArrayList<>();
    private List<DictIntention> mIntentions = new ArrayList<>();
    private List<String> mIntentStrings = new ArrayList<>();
    private DbUtils mDbUtils;
    private PopupList mPopupIntention, mPopupSalary;
    private PopupListFixed mPopupPosition;
    private Popup2List mPopupCity;
    private Popup2ListIndus mPopupIndus;

    private int jobtype = 1;
    private String current;
    private String mWage;
    private String wantProId, wantCityId;
    private String email;
    private String positiontype;
    private String indus;
    private final static int JOB_SUBSCRIPTION = 0;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            if (msg.what == JOB_SUBSCRIPTION) {
                jobSubscription();
            }
            super.handleMessage(msg);
        }
    };


    private void jobSubscription() {
        Log.d("TAG",
                GlobalVariables.access_token + ":" + jobtype + ":" + indus +
                        ":" + positiontype + ":" + wantProId + ":" +
                        wantCityId + ":" + mWage + ":" + current);
        OkHttpUtils.post()
                   .url(GlobalVariables.JOB_SUBSCRIPTION)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("jobtype", jobtype + "")
                   .addParams("indid", indus)
                   .addParams("positiontype", positiontype)
                   .addParams("provid", wantProId)
                   .addParams("cityid", wantCityId)
                   .addParams("wage", mWage)
                   .addParams("current", current)
                   .addParams("email", email)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               Log.d("TAG",
                                       jsonObject.getString("message") + ":" +
                                               jsonObject.getInt("code"));
                               if (jsonObject.getInt("code") == 200) {
                                   finish();
                               }
                               else {
                                   MyToast.showToast(mContext,
                                           jsonObject.getString("message"));
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
        setContentView(R.layout.activity_jobsubscribe);
        mContext = this;
        mDbUtils = DBHelp.getInstance(mContext);
        initActionBar();
        initView();
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        tv_title.setText("职位订阅");
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    mIndustries = mDbUtils.findAll(
                            Selector.from(DictIndustry.class));
                    mPositionCates = mDbUtils.findAll(
                            Selector.from(DictPositionCate.class));
                    mCities = mDbUtils.findAll(Selector.from(DictCity.class));
                    mSalaries = mDbUtils.findAll(
                            Selector.from(DictSalary.class));
                    mIntentions = mDbUtils.findAll(
                            Selector.from(DictIntention.class));
                    for (int i = 0; i < mIntentions.size(); i++) {
                        mIntentStrings.add(mIntentions.get(i).getName());
                    }
                    for (int i = 0; i < mSalaries.size(); i++) {
                        mSalaryString.add(mSalaries.get(i).getName());
                    }
                    for (int i = 0; i < mCities.size(); i++) {
                        if (mCities.get(i).getLevel() == 1) {
                            cities_one_level.add(mCities.get(i));
                        }
                        else if (mCities.get(i).getLevel() == 2) {
                            cities_two_level.add(mCities.get(i));
                        }
                    }
                    for (int i = 0; i < mPositionCates.size(); i++) {
                        mPositionStrings.add(mPositionCates.get(i).getName());
                    }
                    for (int i = 0; i < mIndustries.size(); i++) {
                        if (mIndustries.get(i).getLevel() == 1) {
                            industries_one_level.add(mIndustries.get(i));
                        }
                        else if (mIndustries.get(i).getLevel() == 2) {
                            industries_two_level.add(mIndustries.get(i));
                        }
                    }
                    mPopupIndus = new Popup2ListIndus(JobSubscribeActivity
                            .this, industries_one_level, industries_two_level,
                            "请选择行业类别");
                    mPopupPosition = new PopupListFixed(JobSubscribeActivity
                            .this, mPositionStrings, "请选择职位类别");
                    mPopupIntention = new PopupList(JobSubscribeActivity.this,
                            mIntentStrings, "请选择个人状态");
                    mPopupSalary = new PopupList(JobSubscribeActivity.this,
                            mSalaryString, "请选择期望薪水");
                    mPopupCity = new Popup2List(JobSubscribeActivity.this,
                            cities_one_level, cities_two_level, "请选择期望城市");
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void initView() {
        tv_ind = (TextView) findViewById(R.id.tv_ind);
        tv_ind.setOnClickListener(this);
        tv_cate = (TextView) findViewById(R.id.tv_cate);
        tv_cate.setOnClickListener(this);
        et_email = (EditText) findViewById(R.id.et_email);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_city.setOnClickListener(this);
        tv_salary = (TextView) findViewById(R.id.tv_salary);
        tv_salary.setOnClickListener(this);
        tv_intention = (TextView) findViewById(R.id.tv_intention);
        tv_intention.setOnClickListener(this);
        ll_fulltime = (LinearLayout) findViewById(R.id.ll_fulltime);
        ll_fulltime.setOnClickListener(this);
        ll_study = (LinearLayout) findViewById(R.id.ll_study);
        ll_study.setOnClickListener(this);
        tv_fulltime = (TextView) findViewById(R.id.tv_fulltime);
        tv_study = (TextView) findViewById(R.id.tv_study);
        changeFromTo(tv_fulltime, ll_fulltime, tv_study, ll_study);
    }


    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
            case R.id.ll_fulltime:
                changeFromTo(tv_fulltime, ll_fulltime, tv_study, ll_study);
                jobtype = 1;
                break;
            case R.id.ll_study:
                changeFromTo(tv_study, ll_study, tv_fulltime, ll_fulltime);
                jobtype = 2;
                break;
            case R.id.btn_save:
                if (!TextUtils.isEmpty(et_email.getText().toString()) &&
                        CheckTextFormatUtil.checkEmail(
                                et_email.getText().toString()) &&
                        !TextUtils.isEmpty(tv_intention.getText().toString()) &&
                        !TextUtils.isEmpty(tv_salary.getText().toString()) &&
                        !TextUtils.isEmpty(tv_city.getText().toString()) &&
                        !TextUtils.isEmpty(tv_cate.getText().toString()) &&
                        !TextUtils.isEmpty(tv_ind.getText().toString())) {
                    email = et_email.getText().toString();
                    mHandler.sendEmptyMessage(JOB_SUBSCRIPTION);
                }
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.tv_intention:
                mPopupIntention.showPopupWindow();
                mPopupIntention.setFindDataCallBack(
                        new PopupList.FindDataCallBack() {
                            @Override public void findEduCallBack(String edu) {
                                tv_intention.setText(edu);
                                for (int i = 0; i < mIntentions.size(); i++) {
                                    if (edu.equals(
                                            mIntentions.get(i).getName())) {
                                        current = mIntentions.get(i).getValue();
                                    }
                                }
                                mPopupIntention.dismiss();
                            }
                        });
                break;
            case R.id.tv_salary:
                mPopupSalary.showPopupWindow();
                mPopupSalary.setFindDataCallBack(
                        new PopupList.FindDataCallBack() {
                            @Override public void findEduCallBack(String edu) {
                                tv_salary.setText(edu);
                                for (int i = 0; i < mSalaries.size(); i++) {
                                    if (edu.equals(
                                            mSalaries.get(i).getName())) {
                                        mWage = mSalaries.get(i).getValue();
                                    }
                                }
                                mPopupSalary.dismiss();
                            }
                        });
                break;
            case R.id.tv_city:
                mPopupCity.showPopupWindow();
                mPopupCity.setFindDataCallBack(
                        new Popup2List.FindDataCallBack() {
                            @Override
                            public void findEduCallBack(String edu, int city_one_id, int city_two_id) {
                                tv_city.setText(edu);
                                mPopupCity.dismiss();
                                wantProId = city_one_id + "";
                                wantCityId = city_two_id + "";
                            }
                        });
                break;
            case R.id.tv_cate:
                mPopupPosition.showPopupWindow();
                mPopupPosition.setFindDataCallBack(
                        new PopupListFixed.FindDataCallBack() {
                            @Override public void findEduCallBack(String edu) {
                                tv_cate.setText(edu);
                                mPopupPosition.dismiss();
                                for (int i = 0;
                                     i < mPositionCates.size();
                                     i++) {
                                    if (edu.equals(
                                            mPositionCates.get(i).getName())) {
                                        positiontype =
                                                mPositionCates.get(i).getId() +
                                                        "";
                                    }
                                }
                            }
                        });
                break;
            case R.id.tv_ind:
                mPopupIndus.showPopupWindow();
                mPopupIndus.setFindDataCallBack(
                        new Popup2ListIndus.FindDataCallBack() {
                            @Override
                            public void findEduCallBack(String edu, int indus_one_id, int indus_two_id) {
                                tv_ind.setText(edu);
                                indus = indus_two_id + "";
                                mPopupIndus.dismiss();
                            }
                        });
                break;
        }
    }


    private void changeFromTo(TextView tv_fulltime, LinearLayout ll_fulltime, TextView tv_study, LinearLayout ll_study) {

        tv_fulltime.setTextColor(getResources().getColor(R.color.white));
        ll_fulltime.setBackgroundDrawable(
                getResources().getDrawable(R.drawable.checked_bg_blue3));
        tv_study.setTextColor(getResources().getColor(R.color.colorPrimary));
        ll_study.setBackgroundDrawable(
                getResources().getDrawable(R.drawable.checked_bg_blue_border2));
    }
}
