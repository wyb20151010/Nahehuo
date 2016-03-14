package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.project.popup.InputPopup;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;

/**
 * Created by WYB on 2016/2/29.
 */
public class PerInfoEditTagActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private ArrayList<String> items = new ArrayList<>();
    private TextView tv_add_tag;
    private TagFlowLayout tfl_add_tag;
    private TagAdapter mTagAdapter;
    private InputPopup mInputPopup;
    public static final int RESULT_OK = 1;
    private Context mContext;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_edit_tag);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("职业标签");
        tv_add_tag = (TextView) findViewById(R.id.tv_add_tag);
        tv_add_tag.setOnClickListener(this);
        tfl_add_tag = (TagFlowLayout) findViewById(R.id.tfl_add_tag);
        mTagAdapter = new TagAdapter(items) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag, parent, false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(items.get(position));
                return v;
            }
        };
        tfl_add_tag.setAdapter(mTagAdapter);
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


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save:
                if (items.size() != 0) {
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra("Tags", items);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(mContext, "标签不能为空", Toast.LENGTH_SHORT)
                         .show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_tag:
                mInputPopup = new InputPopup(this);
                mInputPopup.showPopupWindow();
                mInputPopup.setDataCallBac(new InputPopup.DataCallBac() {
                    @Override public void getDataBack(String data) {
                        items.add(data);
                        mTagAdapter.notifyDataChanged();
                    }
                });
                break;
        }
    }
}
