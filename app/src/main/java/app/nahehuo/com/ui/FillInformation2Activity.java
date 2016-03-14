package app.nahehuo.com.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.DBHelp;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.DictCity;
import app.nahehuo.com.bean.DictEduLevel;
import app.nahehuo.com.bean.DictWorkYear;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.view.MyUtilDatePickerDialog;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2015/12/30.
 */
public class FillInformation2Activity extends AppCompatActivity
        implements View.OnClickListener {

    private DbUtils mDbUtils;
    private Context mContext;
    private TextView tv_birthday, tv_mostedu, tv_fill_workyear, tv_fill_city,
            tv_fill_home, tv_save;
    private PopupFillInfo mPopupFillInfo;

    private int locate_pro, locate_city, home_pro, home_city;
    private int gender;
    private String sex, name, email, phone;
    private Intent mIntent;
    private int byear, bmonth, bday;
    private String workyear;
    private Calendar calendar = Calendar.getInstance();
    private int year_in, month_in, day_in;
    private List<DictEduLevel> mEduLevels = new ArrayList<>();
    private List<String> mEduStrings = new ArrayList<>();
    private List<DictWorkYear> mWorkYears = new ArrayList<>();
    private List<String> mWorkYearStrings = new ArrayList<>();
    private List<DictCity> mCities = new ArrayList<>();
    private List<DictCity> cities_one_level = new ArrayList<>();
    private List<DictCity> cities_two_level = new ArrayList<>();
    private PopupList mPopupList;
    private PopupList mPopupList_wy;
    private Popup2List mPopup2List;
    private Popup2List mPopup2List_home;
    private final static int POSTREGISTERINFO = 0;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case POSTREGISTERINFO:
                    postRegisterInfo();
                    break;
            }

            super.handleMessage(msg);
        }
    };


    private void postRegisterInfo() {
        OkHttpUtils.post()
                   .url(GlobalVariables.REGISTER_PERFECTINFO)
                   .addParams("access_token",
                           "w3qtP4cNIjGh9FvvnQvMWkym3OT3m6uYaCFLcMB2")
                   .addParams("device", GlobalVariables.device)
                   .addParams("realname", name)
                   .addParams("gender", gender + "")
                   .addParams("mobile", phone)
                   .addParams("email", email)
                   .addParams("byear", byear + "")
                   .addParams("bmonth", bmonth + "")
                   .addParams("bday", bday + "")
                   .addParams("workyear", workyear)
                   .addParams("curprovid", locate_pro + "")
                   .addParams("curcityid", locate_city + "")
                   .addParams("bprovid", home_pro + "")
                   .addParams("bcityid", home_city + "")
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           Log.d("TAG", response);
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               int status = jsonObject.getInt("code");
                               if (status == 200) {
                                   mPopupFillInfo = new PopupFillInfo(
                                           FillInformation2Activity.this,
                                           "保存成功", "可到“我-我的档案”中修改");
                                   mPopupFillInfo.showPopupWindow();
                                   mPopupFillInfo.setSetOnSelect(
                                           new PopupFillInfo.SetOnSelect() {
                                               @Override
                                               public void onSelect() {
                                                   startActivity(
                                                           new Intent(mContext,
                                                                   MainActivity.class));
                                                   overridePendingTransition(
                                                           R.anim.push_left_in,
                                                           R.anim.push_left_out);
                                               }
                                           });
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
        setContentView(R.layout.activity_fill_information2);
        MyApplication.getInstance().addActivity(this);
        mContext = this;
        mDbUtils = DBHelp.getInstance(this);
        year_in = calendar.get(Calendar.YEAR);
        month_in = calendar.get(Calendar.MONTH);
        day_in = calendar.get(Calendar.DAY_OF_MONTH);
        mIntent = getIntent();
        sex = mIntent.getStringExtra("sex");
        name = mIntent.getStringExtra("name");
        email = mIntent.getStringExtra("email");
        phone = mIntent.getStringExtra("phone");
        if ("男生".equals(sex)) {
            gender = 1;
        }
        else if ("女生".equals(sex)) {
            gender = 2;
        }
        initView();
        initToolbar();
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    mEduLevels = mDbUtils.findAll(
                            Selector.from(DictEduLevel.class));
                   mWorkYears = mDbUtils.findAll(
                            Selector.from(DictWorkYear.class));
                    mCities = mDbUtils.findAll(Selector.from(DictCity.class));

                    for (int i = 0; i < mEduLevels.size(); i++) {
                        mEduStrings.add(mEduLevels.get(i).getName());
                    }
                    for (int i = 0; i < mWorkYears.size(); i++) {
                        mWorkYearStrings.add(mWorkYears.get(i).getName());
                    }
                    for (int i = 0; i < mCities.size(); i++) {
                        if (mCities.get(i).getLevel() == 1) {
                            cities_one_level.add(mCities.get(i));
                        }
                        else if (mCities.get(i).getLevel() == 2) {
                            cities_two_level.add(mCities.get(i));
                        }
                    }

                    mPopupList = new PopupList(FillInformation2Activity.this,
                            mEduStrings, "请选择学历");
                    mPopupList_wy = new PopupList(FillInformation2Activity
                            .this, mWorkYearStrings, "请输入工作年限");
                    mPopup2List = new Popup2List(FillInformation2Activity.this,
                            cities_one_level, cities_two_level, "请选择所在城市");
                    mPopup2List_home = new Popup2List(FillInformation2Activity
                            .this, cities_one_level, cities_two_level, "请选择家乡");
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void initView() {
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_mostedu = (TextView) findViewById(R.id.tv_mostedu);
        tv_fill_workyear = (TextView) findViewById(R.id.tv_fill_workyear);
        tv_fill_city = (TextView) findViewById(R.id.tv_fill_city);
        tv_fill_home = (TextView) findViewById(R.id.tv_fill_home);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_birthday.setOnClickListener(this);
        tv_mostedu.setOnClickListener(this);
        tv_fill_workyear.setOnClickListener(this);
        tv_fill_city.setOnClickListener(this);
        tv_fill_home.setOnClickListener(this);
        tv_save.setOnClickListener(this);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fill_home:
                mPopup2List_home.showPopupWindow();
                mPopup2List_home.setFindDataCallBack(
                        new Popup2List.FindDataCallBack() {
                            @Override
                            public void findEduCallBack(String edu, int one, int two) {
                                tv_fill_home.setText(edu);
                                mPopup2List_home.dismiss();
                                home_pro = one;
                                home_city = two;
                            }
                        });
                break;
            case R.id.tv_fill_city:
                mPopup2List.showPopupWindow();
                mPopup2List.setFindDataCallBack(
                        new Popup2List.FindDataCallBack() {
                            @Override
                            public void findEduCallBack(String edu, int one, int two) {
                                tv_fill_city.setText(edu);
                                mPopup2List.dismiss();
                                Log.d("TAG", one + ":" + two);
                                locate_pro = one;
                                locate_city = two;
                            }
                        });
                break;
            case R.id.tv_fill_workyear:
                mPopupList_wy.showPopupWindow();
                mPopupList_wy.setFindDataCallBack(
                        new PopupList.FindDataCallBack() {
                            @Override public void findEduCallBack(String edu) {
                                tv_fill_workyear.setText(edu);
                                mPopupList_wy.dismiss();
                                for (int i = 0; i < mWorkYears.size(); i++) {
                                    if (mWorkYears.get(i)
                                                  .getName()
                                                  .equals(edu)) {
                                        workyear = mWorkYears.get(i).getValue();
                                        Log.d("TAG", "wy:" + workyear + "");
                                    }
                                }
                            }
                        });
                break;
            case R.id.tv_mostedu:
                mPopupList.showPopupWindow();
                mPopupList.setFindDataCallBack(
                        new PopupList.FindDataCallBack() {
                            @Override public void findEduCallBack(String edu) {
                                tv_mostedu.setText(edu);
                                mPopupList.dismiss();
                            }
                        });
                break;
            case R.id.tv_birthday:

                MyUtilDatePickerDialog dialog = new MyUtilDatePickerDialog(this,
                        3, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        System.currentTimeMillis();
                        if (calendar.getTimeInMillis() >
                                System.currentTimeMillis()) {
                            MyToast.showToast(mContext, "生日不能超过当前时间");
                            return;
                        }
                        int monthOfYears = month + 1;
                        tv_birthday.setText(
                                year + "-" + monthOfYears + "-" + dayOfMonth);
                        byear = year;
                        bmonth = monthOfYears;
                        bday = dayOfMonth;
                        year_in = year;
                        month_in = month;
                        day_in = dayOfMonth;
                    }
                }, year_in, // 传入年份
                        month_in, // 传入月份
                        day_in // 传入天数
                );
                dialog.show();
                break;
            case R.id.tv_save:
                if (!TextUtils.isEmpty(tv_birthday.getText().toString()) &&
                        !TextUtils.isEmpty(tv_fill_home.getText().toString()) &&
                        !TextUtils.isEmpty(tv_fill_city.getText().toString()) &&
                        !TextUtils.isEmpty(
                                tv_fill_workyear.getText().toString()) &&
                        !TextUtils.isEmpty(tv_mostedu.getText().toString())) {
                    mHandler.sendEmptyMessage(POSTREGISTERINFO);
                }
                break;
        }
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
