package app.nahehuo.com.ui.event;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.EventGalleryAdapter;
import app.nahehuo.com.bean.EventGalleryDict;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by WYB on 2016/1/30.
 */
public class EventGalleryActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private PullToRefreshGridView photo_album_gridview;
    private EventGalleryAdapter mAdapter;
    private Context mContext;
    private List<EventGalleryDict> photo_list
            = new ArrayList<EventGalleryDict>();
    private TextView tv_upload_photo;
    private File tempFile;
    private String filePath;
    private final static int GALLERY = 2;
    private final static int CUT = 3;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_gallery);
        mContext = this;
        initToolBar();
        initData();
        mAdapter = new EventGalleryAdapter(mContext, photo_list);
        photo_album_gridview = (PullToRefreshGridView) findViewById(
                R.id.photo_album_gridview);
        photo_album_gridview.setMode(PullToRefreshBase.Mode.BOTH);
        photo_album_gridview.setAdapter(mAdapter);
        tv_upload_photo = (TextView) findViewById(R.id.tv_upload_photo);
        tv_upload_photo.setOnClickListener(this);
        photo_album_gridview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(mContext,
                                EventGalleryDetailActivity.class));
                        overridePendingTransition(R.anim.push_left_in,
                                R.anim.push_left_out);
                    }
                });
    }


    private void initData() {
        photo_list.add(new EventGalleryDict("41101", "10", "1437619619",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg", "11"));
        photo_list.add(new EventGalleryDict("41101", "10", "1437619619",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg", "11"));
        photo_list.add(new EventGalleryDict("41101", "10", "1437619619",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg", "11"));
        photo_list.add(new EventGalleryDict("41101", "10", "1437619619",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg", "11"));
    }


    private void initToolBar() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tv_title.setText("活动相册");
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
            case R.id.tv_upload_photo:
                gallery();
                break;
        }
    }


    private void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY);
    }


    private void crop(Uri uri) {

        String sdCardRoot = Environment.getExternalStorageDirectory()
                                       .getAbsolutePath();
        File file = new File(
                Environment.getExternalStorageDirectory() + "/nahehuo/");
        if (!file.exists()) {
            file.mkdirs();
        }
        filePath = sdCardRoot + "/nahehuo/userpicture" +
                Calendar.getInstance().getTimeInMillis() + "" +
                ".png";
        Uri uri1 = Uri.fromFile(new File(filePath));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return_data", true);
        startActivityForResult(intent, CUT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                crop(uri);
            }
        }

        if (requestCode == CUT) {
            if (data != null) {
                photo_list.add(new EventGalleryDict("41101", "10", "1437619619",
                        ImageDownloader.Scheme.FILE.wrap(filePath), "11"));
                mAdapter.notifyDataSetChanged();
            }
            try {
                if (tempFile != null) {
                    tempFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
