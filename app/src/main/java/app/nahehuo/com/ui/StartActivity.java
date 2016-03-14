package app.nahehuo.com.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.service.SaveCommonDictService;
import app.nahehuo.com.util.GlobalUtil;

/**
 * Created by wyb on 2016/3/8.
 */
public class StartActivity extends Activity {

    private Handler mHandler;
    private Context mContext;
    private SharedPreferences sp;
    private long deadline;
    private long current_time;
    private boolean isLogin;
    private boolean isRegister;


    @Override protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mContext = this;
        MyApplication.getInstance().addActivity(this);

        sp = getSharedPreferences("user", MODE_PRIVATE);
        deadline = sp.getLong("dead_time", 0);
        current_time = System.currentTimeMillis();
        isLogin = sp.getBoolean("isLogin", false);
        isRegister = sp.getBoolean("isRegister", false);
        startService(new Intent(mContext, SaveCommonDictService.class));
    }


    @Override protected void onResume() {
        if (!GlobalUtil.isConn(StartActivity.this)) {
            GlobalUtil.setNetworkMethod(StartActivity.this);
        }
        else {
            mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override public void run() {

                    if (TextUtils.isEmpty(GlobalVariables.access_token)) {
                        if (isRegister) {
                            startActivity(
                                    new Intent(mContext, LoginActivity.class));
                        }
                        else {
                            startActivity(new Intent(mContext,
                                    RegisterActivity.class));
                        }
                    }
                    else {
                        startActivity(new Intent(mContext, MainActivity.class));
                    }
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);
                    finish();
                }
            }, 1000);
        }
        super.onResume();
    }
}
