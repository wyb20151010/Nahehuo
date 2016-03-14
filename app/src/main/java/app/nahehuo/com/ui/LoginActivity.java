package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.Login;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.util.CheckTextFormatUtil;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.TextUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by WYB on 2015/12/31.
 */
public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener, TextWatcher {

    private Toolbar mToolbar;
    private TextView tv_title, tv_forget_password, tv_back_register;
    private Button btn_login;
    private MaterialEditText met_name, met_pwd;
    private PopupRefresh mPopupRefresh;
    private SharedPreferences sp;
    private String username;
    private String pwd;
    private Context mContext;
    private static final int LOGIN_GET = 0;
    private Handler mHandler = new Handler() {

        @Override public void handleMessage(Message msg) {
            if (msg.what == LOGIN_GET) {
                loginGet();
            }
            super.handleMessage(msg);
        }
    };
    private RelativeLayout rl_view_fir;

    /**
     * 后缀字符串
     */
    private String mAddedText = "";
    /**
     * 记录是否为空
     */
    private boolean mFlag;

    /**
     * 常用的邮箱
     */
    private HashMap<String, String> mAutoData = new HashMap<String, String>();

    /**
     * 画笔
     */
    private Paint mPaint;

    private Bitmap mBitmap;

    /**
     * 画布
     */
    private Canvas canvas;

    private Drawable drawable;

    /**
     * 邮箱editText的高度
     */
    private int mHeight;

    /**
     * 邮箱editText的宽度
     */
    private int mWidth;

    /**
     * 输入框中字符的baseLine
     */
    private int baseLine;


    private void loginGet() {
        OkHttpUtils.post()
                   .url(GlobalVariables.LOGIN_GET)
                   .addParams("username", username)
                   .addParams("password", pwd)
                   .build()
                   .execute(new GsonCallBack<Login>(Login.class) {
                       @Override public void onResponse(Login response) {
                           if (response.getCode() == 200) {
                               sp.edit()
                                 .putString("uid",
                                         response.getData().getUser().getId() +
                                                 "")
                                 .putString("access_token",
                                         response.getData().getAccessToken())
                                 .putString("email", response.getData()
                                                             .getUser()
                                                             .getEmail())
                                 .putString("username", username)
                                 .putString("password", pwd)
                                 .putString("refresh_token",
                                         response.getData().getRefreshToken())
                                 .putLong("dead_time",
                                         System.currentTimeMillis() +
                                                 response.getData()
                                                         .getAccessTokenExpiration() *
                                                         1000)
                                 .putBoolean("isLogin", true)
                                 .commit();
                               Log.d("TAG", System.currentTimeMillis() +
                                       response.getData()
                                               .getAccessTokenExpiration() *
                                               1000 + "");
                               GlobalVariables.access_token = response.getData()
                                                                      .getAccessToken();
                               GlobalVariables.UID =
                                       response.getData().getUser().getId() +
                                               "";
                               mPopupRefresh.dismiss();
                               startActivity(new Intent(mContext,
                                       MainActivity.class));
                               overridePendingTransition(R.anim.push_left_in,
                                       R.anim.push_left_out);
                           }
                           else {
                               mPopupRefresh.dismiss();
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyApplication.getInstance().addActivity(this);
        mContext = this;
        mPopupRefresh = new PopupRefresh(this);
        sp = getSharedPreferences("user", MODE_PRIVATE);
        initEmail();
        initView();
        initToolbar();
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


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("登录");
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        tv_forget_password.setOnClickListener(this);
        tv_back_register = (TextView) findViewById(R.id.tv_back_register);
        tv_back_register.setOnClickListener(this);
        met_name = (MaterialEditText) findViewById(R.id.met_name);
        met_pwd = (MaterialEditText) findViewById(R.id.met_pwd);
        met_name.addTextChangedListener(this);
        met_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    met_name.append(mAddedText);
                }
            }
        });
        rl_view_fir = (RelativeLayout) findViewById(R.id.rl_view_fir);
        rl_view_fir.setOnClickListener(this);
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
                if (!TextUtil.isEmpty(met_name.getText().toString()) &&
                        !TextUtil.isEmpty(met_pwd.getText().toString())) {
                    if (CheckTextFormatUtil.isMobile(
                            met_name.getText().toString()) ||
                            CheckTextFormatUtil.checkEmail(
                                    met_name.getText().toString())) {
                        username = met_name.getText().toString();
                        pwd = met_pwd.getText().toString();
                        mHandler.sendEmptyMessage(LOGIN_GET);
                        mPopupRefresh.showPopupWindow();
                    }
                    else {
                        MyToast.showToast(mContext, "请填写正确的账号格式");
                    }
                }
                else {
                    MyToast.showToast(mContext, "请填写账号，密码");
                }

                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(mContext, FindPasswordActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.tv_back_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_view_fir:
                startActivity(new Intent(mContext, MainActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override public void afterTextChanged(Editable s) {
        String text = s.toString();
        mFlag = true;
        Iterator<Map.Entry<String, String>> iterator = mAutoData.entrySet()
                                                                .iterator();
        // 遍历常用邮箱
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (text.endsWith(entry.getKey())) {
                drawAddedText(entry.getValue());
                mAddedText = entry.getValue();
                mFlag = false;
                break;
            }
        }

        // 如果没有匹配，就画一个空
        if (mFlag) {
            drawAddedText("");
            mAddedText = "";
        }
    }


    /**
     * 画出后缀字符串
     */
    private void drawAddedText(String addedText) {
        // 如果字符串为空，画空
        if (addedText.equals("")) {
            met_name.setCompoundDrawables(null, null, null, null);
            return;
        }
        // 只需要初始化一次
        if (mBitmap == null) {
            mHeight = met_name.getHeight();
            mWidth = met_name.getWidth();

            // 初始化画笔
            mPaint = new Paint();
            mPaint.setColor(Color.GRAY);
            mPaint.setAntiAlias(true);// 去除锯齿
            mPaint.setFilterBitmap(true);// 对位图进行滤波处理
            mPaint.setTextSize(met_name.getTextSize());
        }

        // 计算baseLine
        Rect rect = new Rect();
        int baseLineLocation = met_name.getLineBounds(0, rect);
        baseLine = baseLineLocation - rect.top;

        // 添加的字符窜的长度
        int addedTextWidth = (int) (mPaint.measureText(addedText) + 1);

        // 创建bitmap
        mBitmap = Bitmap.createBitmap(addedTextWidth, mHeight,
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(mBitmap);

        // 绘制后缀字符串
        canvas.drawText(addedText, 0, baseLine, mPaint);
        // bitmap转化为Drawable
        drawable = new BitmapDrawable(mBitmap);

        String text = met_name.getText().toString();
        // 计算后缀字符串在输入框中的位置
        int addedTextLeft = (int) (mPaint.measureText(text) - mWidth +
                addedTextWidth);
        int addedTextRight = addedTextLeft + addedTextWidth;
        int addedTextTop = 0;
        int addedTextBottom = addedTextTop + mHeight;
        // 设置后缀字符串位置
        drawable.setBounds(addedTextLeft + 100, addedTextTop + 20,
                addedTextRight + 100, addedTextBottom + 20);
        // 显示后缀字符串
        met_name.setCompoundDrawables(null, null, drawable, null);
    }


    /**
     * 初始化常用的邮箱
     */
    private void initEmail() {

        mAutoData.put("@q", "q.com");
        mAutoData.put("@qq", ".com");
        mAutoData.put("@qq.", "com");
        mAutoData.put("@qq.c", "om");
        mAutoData.put("@qq.co", "m");
        mAutoData.put("@1", "63.com");
        mAutoData.put("@16", "3.com");
        mAutoData.put("@163", ".com");
        mAutoData.put("@163.", "com");
        mAutoData.put("@163.c", "om");
        mAutoData.put("@163.co", "m");
        mAutoData.put("@s", "ina.cn");
        mAutoData.put("@si", "na.cn");
        mAutoData.put("@sin", "a.cn");
        mAutoData.put("@sina", ".cn");
        mAutoData.put("@sina.", "cn");
        mAutoData.put("@sina.c", "n");
        mAutoData.put("@s", "ina.com");
        mAutoData.put("@si", "na.com");
        mAutoData.put("@sin", "a.com");
        mAutoData.put("@sina", ".com");
        mAutoData.put("@sina.", "com");
        mAutoData.put("@sina.c", "om");
        mAutoData.put("@sina.co", "m");
        mAutoData.put("@1", "26.com");
        mAutoData.put("@12", "6.com");
        mAutoData.put("@126", ".com");
        mAutoData.put("@126.", "com");
        mAutoData.put("@126.c", "om");
        mAutoData.put("@126.co", "m");
        mAutoData.put("@1", "39.com");
        mAutoData.put("@13", "9.com");
        mAutoData.put("@139", ".com");
        mAutoData.put("@139.", "com");
        mAutoData.put("@139.c", "om");
        mAutoData.put("@139.co", "m");
        mAutoData.put("@s", "ohu.com");
        mAutoData.put("@so", "hu.com");
        mAutoData.put("@soh", "u.com");
        mAutoData.put("@sohu", ".com");
        mAutoData.put("@sohu.", "com");
        mAutoData.put("@sohu.c", "om");
        mAutoData.put("@sohu.co", "m");
        mAutoData.put("@t", "om.com");
        mAutoData.put("@to", "m.com");
        mAutoData.put("@tom", ".com");
        mAutoData.put("@tom.", "com");
        mAutoData.put("@tom.c", "om");
        mAutoData.put("@tom.co", "m");
    }
}
