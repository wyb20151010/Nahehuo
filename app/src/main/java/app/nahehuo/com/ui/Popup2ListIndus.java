package app.nahehuo.com.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import app.nahehuo.com.bean.DictIndustry;
import com.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/29.
 */
public class Popup2ListIndus extends BasePopupWindow {

    private ListView lv_city_one;
    private ListView lv_city_two;
    private EduAdapter mEduAdapter_one;
    private EduAdapter mEduAdapter_two;
    FindDataCallBack mFindDataCallBack;
    private TextView tv_title;
    private List<DictIndustry> mCities_one;
    private List<DictIndustry> mCities_two;
    private List<DictIndustry> mCities_three = new ArrayList<>();
    private String name_one;
    private int city_one, city_two;


    public Popup2ListIndus(final Activity context, final List<DictIndustry> mCities_one, final List<DictIndustry> mCities_two, String title) {
        super(context);
        this.mCities_one = mCities_one;
        this.mCities_two = mCities_two;
        mEduAdapter_one = new EduAdapter(mCities_one, context);
        mEduAdapter_two = new EduAdapter(mCities_three, context);
        lv_city_one = (ListView) mPopupView.findViewById(R.id.lv_city_one);
        lv_city_two = (ListView) mPopupView.findViewById(R.id.lv_city_two);
        tv_title = (TextView) mPopupView.findViewById(R.id.tv_title);
        tv_title.setText(title);
        lv_city_one.setAdapter(mEduAdapter_one);
        lv_city_two.setAdapter(mEduAdapter_two);
        lv_city_one.setSelection(0);
        lv_city_one.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mCities_three.clear();
                        for (int i = 0; i < mCities_two.size(); i++) {
                            if (mCities_two.get(i).getUpid() ==
                                    mCities_one.get(position).getId()) {
                                mCities_three.add(mCities_two.get(i));
                            }
                        }
                        mEduAdapter_two.notifyDataSetChanged();
                    }
                });
        lv_city_two.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        for (int i = 0; i < mCities_one.size(); i++) {
                            if (mCities_one.get(i).getId() ==
                                    mCities_three.get(position).getUpid()) {
                                name_one = mCities_one.get(i).getName();
                                city_one = mCities_one.get(i).getId();
                            }
                        }
                        mFindDataCallBack.findEduCallBack(name_one + "-" +
                                        mCities_three.get(position).getName(),
                                city_one, mCities_three.get(position).getId());
                    }
                });
        mDismissView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });
    }


    public void setFindDataCallBack(FindDataCallBack findDataCallBack) {
        mFindDataCallBack = findDataCallBack;
    }


    @Override protected Animation getAnimation() {
        return null;
    }


    @Override protected Animator getAnimator() {
        ViewHelper.setPivotX(getAnimaView(),
                getAnimaView().getMeasuredWidth() / 2);
        ViewHelper.setPivotY(getAnimaView(),
                getAnimaView().getMeasuredHeight() / 2);
        return getDefaultSlideFromBottomAnimationSet();
    }


    @Override protected View getInputView() {

        return null;
    }


    @Override public Animator getExitAnimator() {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();
            if (getAnimaView() != null) {
                set.playTogether(
                        ObjectAnimator.ofFloat(getAnimaView(), "translationY",
                                0, 250).setDuration(400),
                        ObjectAnimator.ofFloat(getAnimaView(), "alpha", 1, 0.4f)
                                      .setDuration(250 * 3 / 2));
            }
        }
        return set;
    }


    @Override protected View getDismissView() {
        return mPopupView.findViewById(R.id.rl_dismiss);
    }


    @Override public View getPopupView() {
        return LayoutInflater.from(mContext)
                             .inflate(R.layout.popup_two_list, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }


    class EduAdapter extends BaseAdapter {

        private List<DictIndustry> mEduLevels;
        private Context mContext;


        public EduAdapter(List<DictIndustry> eduLevels, Context context) {
            mEduLevels = eduLevels;
            mContext = context;
        }


        @Override public int getCount() {
            return mEduLevels.size();
        }


        @Override public Object getItem(int position) {
            return mEduLevels.get(position);
        }


        @Override public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View v;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                v = LayoutInflater.from(mContext)
                                  .inflate(R.layout.item_city, null);
                viewHolder.tv_city = (TextView) v.findViewById(R.id.tv_city);
                v.setTag(viewHolder);
            }
            else {
                v = convertView;
                viewHolder = (ViewHolder) v.getTag();
            }
            viewHolder.tv_city.setText(mEduLevels.get(position).getName());

            return v;
        }


        class ViewHolder {
            TextView tv_city;
        }
    }

    public interface FindDataCallBack {
        void findEduCallBack(String edu, int indus_one_id, int indus_two_id);
    }
}
