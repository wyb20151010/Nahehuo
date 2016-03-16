package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
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
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.CompanyProduct;
import app.nahehuo.com.bean.CompanyTeam;
import app.nahehuo.com.bean.NetCompanyProduct;
import app.nahehuo.com.bean.NetCompanyTag;
import app.nahehuo.com.bean.NetCompanyTeam;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import app.nahehuo.com.ui.job.CompanyFounderActivity;
import app.nahehuo.com.util.MyToast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompHomePageFragment extends Fragment {

    private Context mContext;
    private ViewPager vp_content, vp_content_team;
    private TextView tv_open_tag, tv_introduce, tv_location, tv_name;
    private LinearLayout ll_comp_product, ll_comp_introduce, ll_comp_team,
            ll_comp_tag, ll_comp_location;
    private TagFlowLayout tfl_company_tag;
    private CirclePageIndicator indicator,indicator_team;
    private List<ImageView> imageViews = new ArrayList<>();

    private TagAdapter<List<String>> tagAdapter;
    private CompanyDetailActivity mCompanyDetailActivity;

    private List<CompanyProduct> mProducts = new ArrayList<>();
    private List<CompanyTeam> mTeams = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    private float startX, startY;
    private String mCid;
    private final static int COMPANY_TAG = 0;
    private final static int COMPANY_PRODUCT = 1;
    private final static int COMPANY_TEAM = 2;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMPANY_TAG:
                    findCompanyTag();
                    break;
                case COMPANY_PRODUCT:
                    findCompanyProduct();
                    break;
                case COMPANY_TEAM:
                    findCompanyTeam();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void findCompanyTeam() {
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_TEAM)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .build()
                   .execute(new GsonCallBack<NetCompanyTeam>(
                           NetCompanyTeam.class) {
                       @Override
                       public void onResponse(NetCompanyTeam response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   CompanyTeam team = new CompanyTeam();
                                   team.setName(
                                           response.getData().get(i).getName());
                                   team.setDesp(
                                           response.getData().get(i).getDesp());
                                   team.setPic(
                                           response.getData().get(i).getPic());
                                   team.setPosition(response.getData()
                                                            .get(i)
                                                            .getPosition());
                                   mTeams.add(team);
                               }
                               vp_content_team.setAdapter(
                                       new CompanyTeamAdapter(
                                               getChildFragmentManager(), mTeams));
                               indicator_team.setViewPager(vp_content_team);
                           }
                           else {
                               ll_comp_team.setVisibility(View.GONE);
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findCompanyProduct() {
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_PRODUCT)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .build()
                   .execute(new GsonCallBack<NetCompanyProduct>(
                           NetCompanyProduct.class) {
                       @Override
                       public void onResponse(NetCompanyProduct response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   CompanyProduct product
                                           = new CompanyProduct();
                                   product.setDesp(
                                           response.getData().get(i).getDesp());
                                   product.setName(
                                           response.getData().get(i).getName());
                                   product.setThumb(response.getData()
                                                            .get(i)
                                                            .getPic()
                                                            .getThumb());
                                   product.setFile(response.getData()
                                                           .get(i)
                                                           .getPic()
                                                           .getFile());
                                   mProducts.add(product);
                               }
                               if (!TextUtils.isEmpty(
                                       mProducts.get(0).getName())) {
                                   tv_name.setText(mProducts.get(0).getName());
                               }

                               for (int i = 0; i < mProducts.size(); i++) {
                                   ImageView iv = new ImageView(mContext);
                                   iv.setScaleType(
                                           ImageView.ScaleType.CENTER_CROP);
                                   iv.setLayoutParams(
                                           new ViewGroup.LayoutParams(
                                                   ViewGroup.LayoutParams.MATCH_PARENT,
                                                   ViewGroup.LayoutParams.MATCH_PARENT));
                                   ImageLoader.getInstance()
                                              .displayImage(mProducts.get(i)
                                                                     .getThumb(),
                                                      iv,
                                                      MyApplication.getDisplayDefaultOption());
                                   imageViews.add(iv);
                                   vp_content.setAdapter(
                                           new TestPagerAdapter(imageViews));

                                   indicator.setViewPager(vp_content);
                               }
                           }
                           else {

                               ll_comp_product.setVisibility(View.GONE);
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           mHandler.sendEmptyMessage(COMPANY_TEAM);
                           super.onResponse(response);
                       }
                   });
    }


    private void findCompanyTag() {
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_TAG)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .build()
                   .execute(new GsonCallBack<NetCompanyTag>(
                           NetCompanyTag.class) {
                       @Override
                       public void onResponse(NetCompanyTag response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   tags.add(
                                           response.getData().get(i).getName());
                               }
                               tagAdapter.notifyDataChanged();
                           }
                           else {
                               ll_comp_tag.setVisibility(View.GONE);
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           mHandler.sendEmptyMessage(COMPANY_PRODUCT);
                           super.onResponse(response);
                       }
                   });
    }


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

        indicator_team = (CirclePageIndicator) v.findViewById(
                R.id.indicator_team);
        vp_content_team = (ViewPager) v.findViewById(R.id.vp_content_team);
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        tv_open_tag = (TextView) v.findViewById(R.id.tv_open_tag);
        tv_introduce = (TextView) v.findViewById(R.id.tv_introduce);
        tv_location = (TextView) v.findViewById(R.id.tv_location);
        ll_comp_product = (LinearLayout) v.findViewById(R.id.ll_comp_product);
        ll_comp_introduce = (LinearLayout) v.findViewById(
                R.id.ll_comp_introduce);
        ll_comp_team = (LinearLayout) v.findViewById(R.id.ll_comp_team);
        ll_comp_location = (LinearLayout) v.findViewById(R.id.ll_comp_location);
        ll_comp_tag = (LinearLayout) v.findViewById(R.id.ll_comp_tag);
        mCompanyDetailActivity.setConvertToHomepage(
                new CompanyDetailActivity.ConvertToHomepage() {
                    @Override
                    public void convertToHomepage(String cid, String content, String address) {
                        mCid = cid;
                        if (TextUtils.isEmpty(content)) {
                            ll_comp_introduce.setVisibility(View.GONE);
                        }
                        else {
                            tv_introduce.setText(content);
                        }
                        if (TextUtils.isEmpty(address)) {
                            ll_comp_location.setVisibility(View.GONE);
                        }
                        else {
                            tv_location.setText(address);
                        }
                        mHandler.sendEmptyMessage(COMPANY_TAG);
                        Log.d("TAG", cid);
                    }
                });


        initTagLayout(v);
        initViewpager(v);
        return v;
    }


    private void initViewpager(View v) {
        indicator = (CirclePageIndicator) v.findViewById(R.id.indicator);
        vp_content = (ViewPager) v.findViewById(R.id.vp_content);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0x00999999);
        indicator.setRadius(4 * density);
        indicator.setPageColor(0x88FFFFFF);
        indicator.setFillColor(0xFF000000);
        indicator.setStrokeColor(0xFFFFFFFF);

        indicator_team.setBackgroundColor(0x00999999);
        indicator_team.setRadius(4 * density);
        indicator_team.setPageColor(0x88FFFFFF);
        indicator_team.setFillColor(0xFF000000);
        indicator_team.setStrokeColor(0xFFFFFFFF);
    }


    private void initTagLayout(View v) {
        tfl_company_tag = (TagFlowLayout) v.findViewById(R.id.tfl_company_tag);
        tagAdapter = new TagAdapter(tags) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_green2, parent,
                        false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(tags.get(position));
                return v;
            }
        };
        tfl_company_tag.setAdapter(tagAdapter);
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


    private class CompanyTeamAdapter extends FragmentPagerAdapter {

        private List<CompanyTeam> items;


        public CompanyTeamAdapter(FragmentManager fm, List<CompanyTeam> items) {
            super(fm);
            this.items = items;
        }


        @Override public Fragment getItem(int position) {
            return CompanyTeamFragment.getInstance(items.get(position));
        }


        @Override public int getCount() {
            return items.size();
        }
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
            return mProducts.size();
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
