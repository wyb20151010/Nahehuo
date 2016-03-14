package app.nahehuo.com.ui.partner;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.partner.popup.DeleteFriPopup;

/**
 * Created by WYB on 2016/2/26.
 */
public class PartnerMoreSetActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Button btn_delete_fri;
    private DeleteFriPopup mDeleteFriPopup;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_more_set);
        initToolbar();
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("更多设置");
        btn_delete_fri = (Button) findViewById(R.id.btn_delete_fri);
        btn_delete_fri.setOnClickListener(this);
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
            case R.id.btn_delete_fri:
                mDeleteFriPopup=new DeleteFriPopup(this);
                mDeleteFriPopup.showPopupWindow();
                mDeleteFriPopup.setCallBack(new DeleteFriPopup.SetOnClickCallBack() {
                    @Override public void setSave() {
                        //TODO 删除好友代码

                    }
                    @Override public void setCancel() {
                        mDeleteFriPopup.dismiss();
                    }
                });
                break;
        }
    }
}
