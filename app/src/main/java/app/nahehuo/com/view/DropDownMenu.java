package app.nahehuo.com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.bean.HeadItem;
import java.util.List;

/**
 * Created by dongjunkun on 2015/6/17.
 */
public class DropDownMenu extends LinearLayout {

    //顶部菜单布局
    private LinearLayout tabMenuView;
    //底部容器，包含popupMenuViews，maskView
    private FrameLayout containerView;
    //弹出菜单父布局
    private FrameLayout popupMenuViews;
    //遮罩半透明View，点击可关闭DropDownMenu
    private View maskView;
    //tabMenuView里面选中的tab位置，-1表示未选中
    private int current_tab_position = -1;

    //分割线颜色
    private int dividerColor = 0xffcccccc;
    //tab选中颜色
    private int textSelectedColor = 0xff008ff3;
    //tab未选中颜色
    private int textUnselectedColor = 0xff595959;
    //遮罩颜色
    private int maskColor = 0x8f000000;
    //tab字体大小
    private int menuTextSize = 15;

    //tab选中图标
    private int menuSelectedIcon;
    //tab未选中图标
    private int menuUnselectedIcon;
    private List<HeadItem> tabTexts;


    public DropDownMenu(Context context) {
        super(context, null);
    }


    public DropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //牛逼，我要入道啦啦啦啦啦啦啦啦
        setOrientation(VERTICAL);

        //为DropDownMenu添加自定义属性
        int menuBackgroundColor = 0xffffffff;
        int underlineColor = 0xffcccccc;
        TypedArray a = context.obtainStyledAttributes(attrs,
                com.yyydjk.library.R.styleable.DropDownMenu);
        underlineColor = a.getColor(
                com.yyydjk.library.R.styleable.DropDownMenu_ddunderlineColor,
                underlineColor);
        dividerColor = a.getColor(
                com.yyydjk.library.R.styleable.DropDownMenu_dddividerColor,
                dividerColor);
        textSelectedColor = a.getColor(
                com.yyydjk.library.R.styleable.DropDownMenu_ddtextSelectedColor,
                textSelectedColor);
        textUnselectedColor = a.getColor(
                com.yyydjk.library.R.styleable.DropDownMenu_ddtextUnselectedColor,
                textUnselectedColor);
        menuBackgroundColor = a.getColor(
                com.yyydjk.library.R.styleable.DropDownMenu_ddmenuBackgroundColor,
                menuBackgroundColor);
        maskColor = a.getColor(
                com.yyydjk.library.R.styleable.DropDownMenu_ddmaskColor,
                maskColor);
        menuTextSize = a.getDimensionPixelSize(
                com.yyydjk.library.R.styleable.DropDownMenu_ddmenuTextSize,
                menuTextSize);
        menuSelectedIcon = a.getResourceId(
                com.yyydjk.library.R.styleable.DropDownMenu_ddmenuSelectedIcon,
                menuSelectedIcon);
        menuUnselectedIcon = a.getResourceId(
                com.yyydjk.library.R.styleable.DropDownMenu_ddmenuUnselectedIcon,
                menuUnselectedIcon);
        a.recycle();

        //初始化tabMenuView并添加到tabMenuView
        tabMenuView = new LinearLayout(context);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tabMenuView.setOrientation(HORIZONTAL);
        tabMenuView.setBackgroundColor(menuBackgroundColor);
        tabMenuView.setLayoutParams(params);
        addView(tabMenuView, 0);

        //为tabMenuView添加下划线
        View underLine = new View(getContext());
        underLine.setLayoutParams(
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        dpTpPx(1.0f)));
        underLine.setBackgroundColor(underlineColor);
        addView(underLine, 1);

        //初始化containerView并将其添加到DropDownMenu
        containerView = new FrameLayout(context);
        containerView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        addView(containerView, 2);
    }


    /**
     * 初始化DropDownMenu
     */
    public void setDropDownMenu(
            @NonNull final List<HeadItem> tabTexts,
            @NonNull List<View> popupViews, @NonNull View contentView) {
        if (tabTexts.size() != popupViews.size()) {
            throw new IllegalArgumentException(
                    "params not match, tabTexts.size() should be equal popupViews.size()");
        }
        this.tabTexts = tabTexts;
        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts, i);
        }
        containerView.addView(contentView, 0);

        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                closeMenu();
            }
        });
        containerView.addView(maskView, 1);
        maskView.setVisibility(GONE);

        popupMenuViews = new FrameLayout(getContext());
        popupMenuViews.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, dpTpPx(300)));
        popupMenuViews.setVisibility(GONE);
        containerView.addView(popupMenuViews, 2);

        for (int i = 0; i < popupViews.size(); i++) {
            popupViews.get(i)
                      .setLayoutParams(new ViewGroup.LayoutParams(
                              ViewGroup.LayoutParams.MATCH_PARENT,
                              ViewGroup.LayoutParams.WRAP_CONTENT));
            popupMenuViews.addView(popupViews.get(i), i);
        }
    }


    private void addTab(@NonNull final List<HeadItem> tabTexts, int i) {
        final TextView tab = new TextView(getContext());
        tab.setSingleLine();
        tab.setEllipsize(TextUtils.TruncateAt.END);
        tab.setGravity(Gravity.CENTER);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        tab.setLayoutParams(
                new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        tab.setTextColor(textUnselectedColor);
        if (tabTexts.get(i).isOpen()) {
            tab.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(menuUnselectedIcon), null);
        }
        tab.setText(tabTexts.get(i).getTitle());
        tab.setPadding(dpTpPx(5), dpTpPx(12), dpTpPx(5), dpTpPx(12));
        //添加点击事件
        tab.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                switchMenu(tabTexts, tab);
            }
        });
        tabMenuView.addView(tab);
        //添加分割线
        if (i < tabTexts.size() - 1) {
            View view = new View(getContext());
            LayoutParams layoutParams = new LayoutParams(dpTpPx(0.5f),
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, dpTpPx(10), 0, dpTpPx(10));
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(dividerColor);
            tabMenuView.addView(view);
        }
    }


    /**
     * 改变tab文字
     */
    public void setTabText(String text) {
        if (current_tab_position != -1) {
            ((TextView) tabMenuView.getChildAt(current_tab_position)).setText(
                    text);
        }
    }


    public void setTabClickable(boolean clickable) {
        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {
            tabMenuView.getChildAt(i).setClickable(clickable);
        }
    }


    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (current_tab_position != -1) {
            ((TextView) tabMenuView.getChildAt(
                    current_tab_position)).setTextColor(textUnselectedColor);
            if (tabTexts.get(current_tab_position / 2).isOpen()) {
                ((TextView) tabMenuView.getChildAt(
                        current_tab_position)).setCompoundDrawablesWithIntrinsicBounds(
                        null, null,
                        getResources().getDrawable(menuUnselectedIcon), null);
            }
            popupMenuViews.setVisibility(View.GONE);
            popupMenuViews.setAnimation(
                    AnimationUtils.loadAnimation(getContext(),
                            com.yyydjk.library.R.anim.dd_menu_out));
            if (tabTexts.get(current_tab_position / 2).isOpen()) {
                maskView.setVisibility(GONE);
                maskView.setAnimation(AnimationUtils.loadAnimation(getContext(),
                        com.yyydjk.library.R.anim.dd_mask_out));
            }
            current_tab_position = -1;
        }
    }


    /**
     * DropDownMenu是否处于可见状态
     */
    public boolean isShowing() {
        return current_tab_position != -1;
    }


    /**
     * 切换菜单
     */
    private void switchMenu(List<HeadItem> tabTexts, View target) {

        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {
            if (target == tabMenuView.getChildAt(i)) {
                //当前的textview所在父view的位置索引，-1为不存在
                //相等，就找到点击就关闭
                if (current_tab_position == i) {
                    closeMenu();
                }
                else {
                    if (current_tab_position == -1) {
                        popupMenuViews.setVisibility(View.VISIBLE);
                        popupMenuViews.setAnimation(
                                AnimationUtils.loadAnimation(getContext(),
                                        com.yyydjk.library.R.anim.dd_menu_in));

                    }

                    if (tabTexts.get(i / 2).isOpen()) {
                        maskView.setVisibility(VISIBLE);
                        maskView.setAnimation(
                                AnimationUtils.loadAnimation(getContext(),
                                        com.yyydjk.library.R.anim.dd_mask_in));
                    }
                    popupMenuViews.getChildAt(i / 2)
                                  .setVisibility(View.VISIBLE);
                    current_tab_position = i;
                    ((TextView) tabMenuView.getChildAt(i)).setTextColor(
                            textSelectedColor);
                    if (tabTexts.get(i / 2).isOpen()) {
                        ((TextView) tabMenuView.getChildAt(
                                i)).setCompoundDrawablesWithIntrinsicBounds(
                                null, null,
                                getResources().getDrawable(menuSelectedIcon),
                                null);
                    }
                }
            }
            else {
                //非所点子textview全置为初始状态
                ((TextView) tabMenuView.getChildAt(i)).setTextColor(
                        textUnselectedColor);
                if (tabTexts.get(i / 2).isOpen()) {
                    ((TextView) tabMenuView.getChildAt(
                            i)).setCompoundDrawablesWithIntrinsicBounds(null,
                            null,
                            getResources().getDrawable(menuUnselectedIcon),
                            null);
                }
                if (!tabTexts.get(i / 2).isOpen()) {
                    maskView.setVisibility(GONE);
                    maskView.setAnimation(
                            AnimationUtils.loadAnimation(getContext(),
                                    com.yyydjk.library.R.anim.dd_mask_out));
                }
                popupMenuViews.getChildAt(i / 2).setVisibility(View.GONE);
            }
        }
    }


    public int dpTpPx(float value) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                        dm) + 0.5);
    }
}
