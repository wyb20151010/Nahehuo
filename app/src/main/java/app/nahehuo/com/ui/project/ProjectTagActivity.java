package app.nahehuo.com.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.project.popup.InputPopup;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;

/**
 * Created by WYB on 2016/1/20.
 */
public class ProjectTagActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tv_add_tag;
    private ArrayList<String> items = new ArrayList<>();
    private TagFlowLayout tfl_add_tag;
    private TagAdapter mTagAdapter;
    protected Toolbar mToolbar;
    private TextView tv_title;
    private final static int RESULT_OK = 1;
    private InputPopup mInputPopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_tag);
        initToolBar();
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
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setOnClickListener(this);
    }


    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
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
            case R.id.tv_add_tag:
               /* new MaterialDialog.Builder(this).title("添加项目标签")
                                                .inputType(
                                                        InputType.TYPE_CLASS_TEXT |
                                                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                                                .inputRange(2, 16)
                                                .positiveText("确定")
                                                .negativeText("取消")
                                                .input("每个标签最多10字", "", false,
                                                        new MaterialDialog.InputCallback() {
                                                            @Override
                                                            public void onInput(MaterialDialog dialog, CharSequence input) {

                                                            }
                                                        })
                                                .show();*/

                mInputPopup = new InputPopup(this);
                mInputPopup.showPopupWindow();
                mInputPopup.setDataCallBac(new InputPopup.DataCallBac() {
                    @Override public void getDataBack(String data) {
                        items.add(data);
                        mTagAdapter.notifyDataChanged();
                    }
                });
                break;
            case R.id.tv_title:
                Intent intent = new Intent();
                intent.putStringArrayListExtra("Tags", items);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
