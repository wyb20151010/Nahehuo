package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.personal.popup.SetIdPopup;
import app.nahehuo.com.util.TextUtil;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerSetIdActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private Button btn_ok;
    private EditText et_id;
    private SetIdPopup mSetIdPopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_set_id);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("设置哪合伙ID");
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        et_id = (EditText) findViewById(R.id.et_id);
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
            case R.id.btn_ok:
                if (!TextUtil.isEmpty(et_id.getText().toString())) {
                    mSetIdPopup = new SetIdPopup(this,
                            et_id.getText().toString());
                    mSetIdPopup.showPopupWindow();
                    mSetIdPopup.setCallBack(
                            new SetIdPopup.SetOnClickCallBack() {
                                @Override public void setSave() {
                                    //TODO 保存
                                    mSetIdPopup.dismiss();
                                }


                                @Override public void setCancel() {
                                    mSetIdPopup.dismiss();
                                }
                            });
                }
                break;
        }
    }
}
