package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.event.SublimePickerFragment;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;

/**
 * Created by WYB on 2016/2/22.
 */
public class PartnerEduExpActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private LinearLayout ll_entry_date, ll_grad_date;
    private TextView tv_entry_date, tv_grad_date;
    private SublimePickerFragment mFragment = new SublimePickerFragment();


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_edu_exp);
        mContext = this;
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
        tv_title.setText("新增教育经历");
        ll_entry_date = (LinearLayout) findViewById(R.id.ll_entry_date);
        ll_entry_date.setOnClickListener(this);
        ll_grad_date = (LinearLayout) findViewById(R.id.ll_grad_date);
        ll_grad_date.setOnClickListener(this);
        tv_entry_date = (TextView) findViewById(R.id.tv_entry_date);
        tv_grad_date = (TextView) findViewById(R.id.tv_grad_date);
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
            case R.id.ll_entry_date:
                getDateTime(tv_entry_date);
                break;
            case R.id.ll_grad_date:
                getDateTime(tv_grad_date);
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
                tv.setTextColor(getResources().getColor(R.color.textcolorgray));
                tv.setTextSize(15);
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
}
