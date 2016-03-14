package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import app.nahehuo.com.ui.job.CompanyFounderActivity;
import app.nahehuo.com.util.TextUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompHomePageFragment extends Fragment {

    private TagFlowLayout tfl_company_tag;
    private String[] tags = { "全职", "技术研发", "本科以上", "五险一金", "员工持股", "团队旅游",
            "班车接送", "按摩师", "鼓励师" };
    private Context mContext;
    private int[] a = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4 };
    private ViewPager vp_content;
    private TextView tv_open_tag, tv_introduce, tv_location;
    private LinearLayout ll_comp_product, ll_comp_introduce, ll_comp_team,
            ll_comp_tag, ll_comp_location;
    private String introduce = "哪合伙是国内最大的社会化电商平台，为国内广大年轻的时尚人群提供衣服," +
            "哪合伙是国内最大的社会化电商平台，为国内广大年轻的时尚人群提供衣服,哪合伙是国内最大的社会化电商平台，为国内广大年轻的时尚人群提供衣服。";
    private List<Fragment> mFragments = new ArrayList<>();
    private String location = "上海 松江区 九新公路341弄28号华西办公楼7层";
    private float startX, startY;
    private CompanyDetailActivity mCompanyDetailActivity;


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof CompanyDetailActivity) {
            mCompanyDetailActivity = (CompanyDetailActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a CompanyDetailActivity !");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comp_home, null);
        mContext = getActivity();
        tv_open_tag = (TextView) v.findViewById(R.id.tv_open_tag);
        tv_introduce = (TextView) v.findViewById(R.id.tv_introduce);
        tv_location = (TextView) v.findViewById(R.id.tv_location);
        ll_comp_product = (LinearLayout) v.findViewById(R.id.ll_comp_product);
        ll_comp_introduce = (LinearLayout) v.findViewById(
                R.id.ll_comp_introduce);
        ll_comp_team = (LinearLayout) v.findViewById(R.id.ll_comp_team);
        ll_comp_location = (LinearLayout) v.findViewById(R.id.ll_comp_location);
        ll_comp_tag = (LinearLayout) v.findViewById(R.id.ll_comp_tag);
        if (tags.length == 0) {
            ll_comp_tag.setVisibility(View.GONE);
        }

        if (a.length == 0) {
            ll_comp_product.setVisibility(View.GONE);
        }
        if (TextUtil.isEmpty(introduce)) {
            ll_comp_introduce.setVisibility(View.GONE);
        }
        else {
            tv_introduce.setText(introduce);
        }

        if (mFragments.size() == 0) {
            ll_comp_team.setVisibility(View.GONE);
        }

        if (TextUtil.isEmpty(location)) {
            ll_comp_location.setVisibility(View.GONE);
        }
        else {
            tv_location.setText(location);
        }
        initTagLayout(v);
        initViewpager(v);
        return v;
    }


    private void initViewpager(View v) {
        CirclePageIndicator indicator = (CirclePageIndicator) v.findViewById(
                R.id.indicator);
        vp_content = (ViewPager) v.findViewById(R.id.vp_content);
        if (a.length != 0) {
            List<ImageView> imageViews = new ArrayList<>();
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            ImageLoader.getInstance()
                       .displayImage(getResources().getString(a[0]), imageView);
            imageViews.add(imageView);
            ImageView imageView2 = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            ImageLoader.getInstance()
                       .displayImage(getResources().getString(a[1]),
                               imageView2);
            imageViews.add(imageView2);
            ImageView imageView3 = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            ImageLoader.getInstance()
                       .displayImage(getResources().getString(a[2]),
                               imageView3);
            imageViews.add(imageView3);
            ImageView imageView4 = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            ImageLoader.getInstance()
                       .displayImage(getResources().getString(a[3]),
                               imageView4);
            imageViews.add(imageView4);

            vp_content.setAdapter(new TestPagerAdapter(imageViews));

            indicator.setViewPager(vp_content);
            final float density = getResources().getDisplayMetrics().density;
            indicator.setBackgroundColor(0x00999999);
            indicator.setRadius(7 * density);
            indicator.setPageColor(0x88FFFFFF);
            indicator.setFillColor(0xFF000000);
            indicator.setStrokeColor(0xFFFFFFFF);
        }
    }


    private void initTagLayout(View v) {
        tfl_company_tag = (TagFlowLayout) v.findViewById(R.id.tfl_company_tag);
        if (tags.length != 0) {
            tfl_company_tag.setAdapter(new TagAdapter(tags) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    LayoutInflater inflater
                            = (LayoutInflater) mContext.getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
                    View v = inflater.inflate(R.layout.item_tag_green, parent,
                            false);
                    TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                    tv_tag.setText(tags[position]);
                    return v;
                }
            });
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doSomeEvent();
    }


    private void doSomeEvent() {
        final TagGestureDetector tagGestureDetector = new TagGestureDetector();
        vp_content.setOnTouchListener(new View.OnTouchListener() {
            float distanceX, distanceY;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        distanceX = Math.abs(event.getX() - startX);
                        distanceY = Math.abs(event.getY() - startY);
                        if (distanceX > distanceY) {
                            vp_content.getParent()
                                      .requestDisallowInterceptTouchEvent(true);
                        }
                        else if (distanceY > distanceX) {
                            vp_content.getParent()
                                      .requestDisallowInterceptTouchEvent(
                                              false);
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", distanceX + "");
                        if (distanceX < 0.01f && distanceY < 0.01f) {
                            tagGestureDetector.onSingleTapUp(event);
                            return false;
                        }
                        break;
                }
                return false;
            }
        });
    }


    private class TestPagerAdapter extends PagerAdapter {

        private List<ImageView> mImageViews = new ArrayList<>();


        public TestPagerAdapter(List<ImageView> imageViews) {
            mImageViews = imageViews;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(mImageViews.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(mImageViews.get(position));
            return mImageViews.get(position);
        }


        @Override public int getCount() {
            return a.length;
        }


        @Override public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private class TagGestureDetector
            extends GestureDetector.SimpleOnGestureListener {
        @Override public boolean onSingleTapUp(MotionEvent e) {
            startActivity(new Intent(mContext, CompanyFounderActivity.class));
            mCompanyDetailActivity.overridePendingTransition(
                    R.anim.push_left_in, R.anim.push_left_out);
            return super.onSingleTapUp(e);
        }
    }
}
