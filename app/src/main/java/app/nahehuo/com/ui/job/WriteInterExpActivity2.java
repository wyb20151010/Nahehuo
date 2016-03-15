package app.nahehuo.com.ui.job;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.ui.PopupFillInfo;
import app.nahehuo.com.ui.PopupList;
import app.nahehuo.com.util.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import io.techery.properratingbar.ProperRatingBar;
import io.techery.properratingbar.RatingListener;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2016/1/15.
 */
public class WriteInterExpActivity2 extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title, tv_oneword;
    private Button btn_accept, btn_no_accept, btn_reject, btn_login;
    private Button temp;
    private CheckBox checkBox;
    private EditText et_descp;
    private RelativeLayout rl_one_word;

    private String jid, oneword, descp;
    private Context mContext;
    private int expType = 1;
    private int anony = 1;
    private ProperRatingBar upperRatingBar_inter, upperRatingBar_position;
    private int interRating=4, positionRating=4;
    private final static int CREATE_INTERVIEW_EXP = 0;
    private PopupList mPopupList;
    private PopupFillInfo mPopupFillInfo;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case CREATE_INTERVIEW_EXP:
                    createInterviewExp();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void createInterviewExp() {
        OkHttpUtils.post()
                   .url(GlobalVariables.JOB_INTERVIEW_CREATE)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("jid", jid)
                   .addParams("result", expType + "")
                   .addParams("jobmatch", positionRating + "")
                   .addParams("score", interRating + "")
                   .addParams("oneword", oneword)
                   .addParams("descp", descp)
                   .addParams("anony", anony + "")
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               if (jsonObject.getInt("code") == 200) {
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
        setContentView(R.layout.activity_write_exp2);
        mContext = this;
        mPopupList = new PopupList(this, Arrays.asList(
                new String[] { "面试官人很nice", "聊得很开心", "不怎么顺利", "完全HOLD住场面",
                        "气氛很轻松", "实际薪资不同", "面试官颜值真高", "办公环境超赞" }), "一句话总结");
        mPopupFillInfo = new PopupFillInfo(this, "面试经验发布成功", "看看我的发布");
        Intent intent = getIntent();
        jid = intent.getStringExtra("jid");
        initToolBar();
        initView();
        initBtn();
        initRatingBar();
    }


    private void initView() {
        rl_one_word = (RelativeLayout) findViewById(R.id.rl_one_word);
        et_descp = (EditText) findViewById(R.id.et_descp);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_oneword = (TextView) findViewById(R.id.tv_oneword);
        rl_one_word.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        anony = isChecked ? 2 : 1;
                    }
                });
    }


    private void initRatingBar() {

        upperRatingBar_position = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_position);
        upperRatingBar_inter = (ProperRatingBar) findViewById(
                R.id.upperRatingBar_inter);
        upperRatingBar_position.setListener(new RatingListener() {
            @Override public void onRatePicked(ProperRatingBar ratingBar) {
                positionRating = ratingBar.getRating();
            }
        });
        upperRatingBar_inter.setListener(new RatingListener() {
            @Override public void onRatePicked(ProperRatingBar ratingBar) {
                interRating = ratingBar.getRating();
            }
        });
    }


    private void initBtn() {
        temp = new Button(mContext);
        btn_accept = (Button) findViewById(R.id.btn_accept);
        btn_no_accept = (Button) findViewById(R.id.btn_no_accept);
        btn_reject = (Button) findViewById(R.id.btn_reject);
        changeFromTo(temp, btn_accept);
        btn_accept.setOnClickListener(this);
        btn_no_accept.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
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

    /*

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.job, menu);
        return super.onCreateOptionsMenu(menu);
    }
    */


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
            case R.id.btn_accept:
                changeFromTo(temp, v);
                expType = 1;
                break;
            case R.id.btn_no_accept:
                changeFromTo(temp, v);
                expType = 3;
                break;
            case R.id.btn_reject:
                changeFromTo(temp, v);
                expType = 2;
                break;
            case R.id.btn_login:
                if (!TextUtils.isEmpty(et_descp.getText().toString())
                        &&!TextUtils.isEmpty(tv_oneword.getText().toString())) {
                    descp = et_descp.getText().toString();
                    mHandler.sendEmptyMessage(CREATE_INTERVIEW_EXP);
                }
                else {
                    MyToast.showToast(mContext,"面试经验请填写完整");
                }
                break;
            case R.id.rl_one_word:
                mPopupList.showPopupWindow();
                mPopupList.setFindDataCallBack(
                        new PopupList.FindDataCallBack() {
                            @Override public void findEduCallBack(String edu) {
                                tv_oneword.setText(edu);
                                oneword = edu;
                                mPopupList.dismiss();
                            }
                        });
                break;
        }
    }


    private void changeFromTo(Button from, View v) {

        Button btn_to = (Button) v;
        if (temp != btn_to) {
            temp = btn_to;
            from.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.back_shape_round));
            from.setTextColor(getResources().getColor(R.color.textcolorgray));
            btn_to.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_shape_round));
            btn_to.setTextColor(getResources().getColor(R.color.white));
        }
    }
}
