package app.nahehuo.com.ui.job;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import io.techery.properratingbar.ProperRatingBar;
import io.techery.properratingbar.RatingListener;

/**
 * Created by WYB on 2016/1/15.
 */
public class WriteInterExpActivity2 extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Button btn_accept, btn_no_accept, btn_reject;
    private Button temp;
    private Context mContext;
    private int expType = 0;
    private ProperRatingBar upperRatingBar_inter, upperRatingBar_position;
    private int interRating, positionRating;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_exp2);
        mContext = this;
        initToolBar();
        initBtn();
        initRatingBar();
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
                expType = 2;
                break;
            case R.id.btn_reject:
                changeFromTo(temp, v);
                expType = 3;
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
