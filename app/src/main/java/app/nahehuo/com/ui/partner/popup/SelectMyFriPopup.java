package app.nahehuo.com.ui.partner.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import com.nineoldandroids.view.ViewHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/18.
 */
public class SelectMyFriPopup extends BasePopupWindow
        implements View.OnClickListener {

    Button btn_cancel;
    SetOnClickCallBack mCallBack;
    TagFlowLayout tfl_add_tag;
    List<String> alphabet = new ArrayList<>();


    public SelectMyFriPopup(Activity context) {
        super(context);
        btn_cancel = (Button) mPopupView.findViewById(R.id.btn_cancel);
        tfl_add_tag = (TagFlowLayout) mPopupView.findViewById(R.id.tfl_add_tag);

        findAlphabet();
        tfl_add_tag.setAdapter(new TagAdapter(alphabet) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_border_zf, parent,
                        false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(alphabet.get(position));
                return v;
            }
        });
        tfl_add_tag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mCallBack.setSave(alphabet.get(position));
                return false;
            }
        });
        bindEvent();
    }


    private void findAlphabet() {
        char first = 'A';
        char last = 'Z';
        for (int i = first; i <= last; i++) {
            alphabet.add(String.valueOf((char) i));
        }
    }


    public void setCallBack(SetOnClickCallBack callBack) {
        mCallBack = callBack;
    }


    private void bindEvent() {
        btn_cancel.setOnClickListener(this);
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


    @Override protected View getInputView() {
        return null;
    }


    @Override protected View getDismissView() {
        return null;
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                mCallBack.setCancel();
                break;
        }
    }


    @Override public View getPopupView() {

        return LayoutInflater.from(mContext)
                             .inflate(R.layout.popup_select_fri, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }


    public interface SetOnClickCallBack {
        void setSave(String letter);

        void setCancel();
    }
}
