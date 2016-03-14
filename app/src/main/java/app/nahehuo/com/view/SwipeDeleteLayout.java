package app.nahehuo.com.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by WYB on 2016/2/19.
 */
public class SwipeDeleteLayout extends LinearLayout {

    private ViewDragHelper mViewDragHelper;
    private View contentView;
    private View deleteView;
    private int dragDistance;
    private final double AUTO_OPEN_SPEED_LIMIT = 800.0;
    private int draggedX;


    public SwipeDeleteLayout(Context context) {
        this(context, null);
    }


    public SwipeDeleteLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }


    public SwipeDeleteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, new DragHelpCallBack());
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        deleteView = getChildAt(1);
        deleteView.setVisibility(GONE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        dragDistance = deleteView.getMeasuredWidth();
    }


    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mViewDragHelper.shouldInterceptTouchEvent(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }


    @Override public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    private class DragHelpCallBack extends ViewDragHelper.Callback {

        @Override public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView || child == deleteView;
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            draggedX = left;
            if (changedView == contentView) {
                deleteView.offsetLeftAndRight(dx);
            }
            else {
                contentView.offsetLeftAndRight(dx);
            }
            if (deleteView.getVisibility() == View.GONE) {
                deleteView.setVisibility(View.VISIBLE);
            }
            invalidate();
        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentView) {
                final int leftBound = getPaddingLeft();
                final int minLeftBound = -leftBound - dragDistance;
                final int newLeft = Math.min(Math.max(minLeftBound, left), 0);
                return newLeft;
            }
            else {
                final int minLeftBound =
                        getPaddingLeft() + contentView.getMeasuredWidth() -
                                dragDistance;
                final int maxLeftBound = getPaddingLeft() +
                        contentView.getMeasuredWidth() + getPaddingRight();
                final int newLeft = Math.min(Math.max(left, minLeftBound),
                        maxLeftBound);
                return newLeft;
            }
        }


        @Override public int getViewHorizontalDragRange(View child) {
            return dragDistance;
        }


        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            boolean settleToOpen = false;
            if (xvel > AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = false;
            }
            else if (xvel < -AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = true;
            }
            else if (draggedX <= -dragDistance / 2) {
                settleToOpen = true;
            }
            else if (draggedX > -dragDistance / 2) {
                settleToOpen = false;
            }

            final int settleDestX = settleToOpen ? -dragDistance : 0;
            mViewDragHelper.smoothSlideViewTo(contentView, settleDestX, 0);
            ViewCompat.postInvalidateOnAnimation(SwipeDeleteLayout.this);
        }
    }
}
