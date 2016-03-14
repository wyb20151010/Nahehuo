package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.event.SublimePickerFragment;
import app.nahehuo.com.ui.partner.popup.SelectSexPopup;
import app.nahehuo.com.ui.personal.popup.BinderPhonePopup;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.makeramen.RoundedImageView;

/**
 * Created by wyb on 2016/2/29.
 */
public class PerInfoEditActivity extends AppCompatActivity
        implements View.OnClickListener {

    private static final int INTRO_YOU = 0;
    private static final int WORK_POS = 1;

    private Toolbar mToolbar;
    private TextView tv_title;
    private RoundedImageView iv_avater;
    private RelativeLayout rl_sex, rl_bir_day, rl_edu_his, rl_work_time,
            rl_city, rl_hometown, rl_intro_myself, rl_email, rl_phone, rl_id,
            rl_position, rl_work, rl_edu, rl_supply, rl_want;
    private EditText et_name, et_email, et_id;
    private TextView tv_sex, tv_bir_day, tv_edu_his, tv_work_time, tv_city,
            tv_hometown, tv_intro, tv_position, tv_work, tv_edu, tv_supply,
            tv_want, tv_photo;
    private SelectSexPopup mSelectSexPopup;
    private SublimePickerFragment mFragment = new SublimePickerFragment();
    private Context mContext;
    private boolean isBinderPhone;
    private BinderPhonePopup mBinderPhonePopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_edit_info);
        mContext = this;
        isBinderPhone = true;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_bir_day = (TextView) findViewById(R.id.tv_bir_day);
        tv_edu_his = (TextView) findViewById(R.id.tv_edu_his);
        tv_work_time = (TextView) findViewById(R.id.tv_work_time);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_hometown = (TextView) findViewById(R.id.tv_hometown);
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        tv_position = (TextView) findViewById(R.id.tv_position);
        tv_work = (TextView) findViewById(R.id.tv_work);
        tv_edu = (TextView) findViewById(R.id.tv_edu);
        tv_supply = (TextView) findViewById(R.id.tv_supply);
        tv_want = (TextView) findViewById(R.id.tv_want);
        tv_photo = (TextView) findViewById(R.id.tv_photo);

        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_id = (EditText) findViewById(R.id.et_id);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("编辑个人信息");
        iv_avater = (RoundedImageView) findViewById(R.id.iv_avater);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_bir_day = (RelativeLayout) findViewById(R.id.rl_bir_day);
        rl_edu_his = (RelativeLayout) findViewById(R.id.rl_edu_his);
        rl_work_time = (RelativeLayout) findViewById(R.id.rl_work_time);
        rl_city = (RelativeLayout) findViewById(R.id.rl_city);
        rl_hometown = (RelativeLayout) findViewById(R.id.rl_hometown);
        rl_intro_myself = (RelativeLayout) findViewById(R.id.rl_intro_myself);
        rl_email = (RelativeLayout) findViewById(R.id.rl_email);
        rl_phone = (RelativeLayout) findViewById(R.id.rl_phone);
        rl_id = (RelativeLayout) findViewById(R.id.rl_id);
        rl_position = (RelativeLayout) findViewById(R.id.rl_position);
        rl_work = (RelativeLayout) findViewById(R.id.rl_work);
        rl_edu = (RelativeLayout) findViewById(R.id.rl_edu);
        rl_supply = (RelativeLayout) findViewById(R.id.rl_supply);
        rl_want = (RelativeLayout) findViewById(R.id.rl_want);
        rl_sex.setOnClickListener(this);
        rl_bir_day.setOnClickListener(this);
        rl_edu_his.setOnClickListener(this);
        rl_work_time.setOnClickListener(this);
        rl_city.setOnClickListener(this);
        rl_hometown.setOnClickListener(this);
        rl_intro_myself.setOnClickListener(this);
        rl_email.setOnClickListener(this);
        rl_phone.setOnClickListener(this);
        rl_id.setOnClickListener(this);
        rl_position.setOnClickListener(this);
        rl_work.setOnClickListener(this);
        rl_edu.setOnClickListener(this);
        rl_supply.setOnClickListener(this);
        rl_want.setOnClickListener(this);
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
            case R.id.rl_sex:
                mSelectSexPopup = new SelectSexPopup(this);
                mSelectSexPopup.showPopupWindow();
                mSelectSexPopup.setCallBack(
                        new SelectSexPopup.SetOnClickCallBack() {
                            @Override public void setMale(String male) {
                                tv_sex.setText(male);
                                mSelectSexPopup.dismiss();
                            }


                            @Override public void setFemale(String female) {
                                tv_sex.setText(female);
                                mSelectSexPopup.dismiss();
                            }
                        });
                break;
            case R.id.rl_bir_day:
                getDateTime(tv_bir_day);
                break;
            case R.id.rl_edu_his:
                break;
            case R.id.rl_work_time:
                break;
            case R.id.rl_city:
                break;
            case R.id.rl_hometown:
                break;
            case R.id.rl_intro_myself:
                startActivityForResult(
                        new Intent(mContext, PerInfoIntroActivity.class),
                        INTRO_YOU);
                break;
            case R.id.rl_email:
                break;
            case R.id.rl_phone:
                if (isBinderPhone) {
                    mBinderPhonePopup = new BinderPhonePopup(this);
                    mBinderPhonePopup.showPopupWindow();
                    mBinderPhonePopup.setCallBack(
                            new BinderPhonePopup.SetOnClickCallBack() {
                                @Override public void setSave() {

                                    startActivity(new Intent(mContext,
                                            PerInfoCgPhoneActivity.class));
                                    overridePendingTransition(
                                            R.anim.push_left_in,
                                            R.anim.push_left_out);
                                    mBinderPhonePopup.dismiss();
                                }


                                @Override public void setCancel() {
                                    mBinderPhonePopup.dismiss();
                                }
                            });
                }
                break;
            case R.id.rl_id:
                break;
            case R.id.rl_position:
                startActivityForResult(
                        new Intent(mContext, PerInfoWorkPosActivity.class),
                        WORK_POS);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_work:
                Intent intent = new Intent(mContext,
                        PerInfoWkExpListActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_edu:
                Intent intent1 = new Intent(mContext,
                        PerInfoWkExpListActivity.class);
                intent1.putExtra("type", "2");
                startActivity(intent1);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_supply:
                startActivity(
                        new Intent(mContext, PerInfoSupplyActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_want:
                startActivity(new Intent(mContext, PerInfoWantActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }


    private void getDateTime(final TextView tv) {
        int displayOptions = 0;
        SublimeOptions options = new SublimeOptions();
        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
        displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;
        options.setDisplayOptions(displayOptions);
        mFragment.setCallback(new SublimePickerFragment.Callback() {
            @Override public void onCancelled() {

            }


            @Override
            public void onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
                StringBuffer sb = new StringBuffer();
                sb.append(year + "年");
                sb.append(monthOfYear + 1 + "月");
                sb.append(dayOfMonth + "日 ");
                tv.setText(sb.toString());
            }
        });
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS",
                new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE,
                        options).second);
        if (!mFragment.isAdded()) {
            mFragment.setArguments(bundle);
            mFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            mFragment.show(getSupportFragmentManager(), "SUBLIME_PICKER");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case INTRO_YOU:
                if (resultCode == PerInfoIntroActivity.RESULT_OK) {
                    Log.d("TAG", data.getStringExtra("data_return"));
                    tv_intro.setText(data.getStringExtra("data_return"));
                }
                break;
            case WORK_POS:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
