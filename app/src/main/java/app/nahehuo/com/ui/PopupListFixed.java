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
import com.nineoldandroids.view.ViewHelper;
import java.util.List;

/**
 * Created by WYB on 2016/1/29.
 */
public class PopupListFixed extends BasePopupWindow {

    private List<String> mEduLevels;
    private ListView lv_edu;
    private EduAdapter mEduAdapter;
    FindDataCallBack mFindDataCallBack;
    private TextView tv_title;

    public PopupListFixed(Activity context, List<String> eduLevels, String title) {
        super(context);
        this.mEduLevels = eduLevels;
        mEduAdapter = new EduAdapter(mEduLevels, context);
        lv_edu = (ListView) mPopupView.findViewById(R.id.lv_edu);
        tv_title= (TextView) mPopupView.findViewById(R.id.tv_title);
        tv_title.setText(title);
        lv_edu.setAdapter(mEduAdapter);
        lv_edu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mFindDataCallBack.findEduCallBack(
                        mEduLevels.get(position));
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
        return LayoutInflater.from(mContext).inflate(R.layout.popup_list_fixed,
                null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }


    class EduAdapter extends BaseAdapter {

        private List<String> mEduLevels;
        private Context mContext;


        public EduAdapter(List<String> eduLevels, Context context) {
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
            viewHolder.tv_city.setText(mEduLevels.get(position));

            return v;
        }


        class ViewHolder {
            TextView tv_city;
        }
    }

    public interface FindDataCallBack {
        void findEduCallBack(String edu);
    }
}
