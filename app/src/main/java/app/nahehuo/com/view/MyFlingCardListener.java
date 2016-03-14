package app.nahehuo.com.view;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/12/28.
 */
public class MyFlingCardListener implements View.OnTouchListener {
    private float aPosX;
    private float aPosY;
    private float aDownTouchX, aDownTouchY;
    private final float halfWidth;
    private View frame = null;
    private final float objectX;
    private final float objectY;
    private final int objectH;
    private final int objectW;
    private final FlingListener mFlingListener;
    private final float parentWidth;
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = INVALID_POINTER_ID;


    public MyFlingCardListener(View frame, FlingListener flingListener) {
        super();
        this.frame = frame;
        this.objectX = frame.getX();
        this.objectY = frame.getY();
        this.objectH = frame.getHeight();
        this.objectW = frame.getWidth();
        this.halfWidth = objectW / 2f;
        this.parentWidth = ((ViewGroup) frame.getParent()).getWidth();
        this.mFlingListener = flingListener;
    }


    @Override public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mActivePointerId = event.getPointerId(0);
                float x = 0;
                float y = 0;
                boolean success = false;
                try {
                    x = event.getX(mActivePointerId);
                    y = event.getY(mActivePointerId);
                    success = true;
                } catch (IllegalArgumentException e) {
                    Log.w("TAG", "Exception in onTouch(view, event) : " +
                            mActivePointerId, e);
                }
                if (success) {
                    aDownTouchX = x;
                    aDownTouchY = y;
                    if (aPosX == 0) {
                        aPosX = frame.getX();
                    }
                    if (aPosY == 0) {
                        aPosY = frame.getY();
                    }
                }
                v.getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                // Find the index of the active pointer and fetch its position
                final int pointerIndexMove = event.findPointerIndex(
                        mActivePointerId);
                final float xMove = event.getX(pointerIndexMove);
                final float yMove = event.getY(pointerIndexMove);

                //from http://android-developers.blogspot.com/2010/06/making-sense-of-multitouch.html
                // Calculate the distance moved
                final float dx = xMove - aDownTouchX;
                final float dy = yMove - aDownTouchY;

                // Move the frame
                aPosX += dx;
                aPosY += dy;

                float distobjectX = aPosX - objectX;

                //in this area would be code for doing something with the view as the frame moves.
                frame.setX(aPosX);
                frame.setY(aPosY);
                mFlingListener.onScroll(getScrollProgressPercent());
        }
        return false;
    }


    private float getScrollProgressPercent() {
        if (movedBeyondLeftBorder()) {
            return -1f;
        }
        else if (movedBeyondRightBorder()) {
            return 1f;
        }
        else {
            float zeroToOneValue = (aPosX + halfWidth - leftBorder()) /
                    (rightBorder() - leftBorder());
            return zeroToOneValue * 2f - 1f;
        }
    }


    private float rightBorder() {
        return 3 * parentWidth / 4.f;
    }


    private float leftBorder() {
        return parentWidth / 4.f;
    }


    private boolean movedBeyondRightBorder() {
        return aPosX + halfWidth > rightBorder();
    }


    private boolean movedBeyondLeftBorder() {
        return aPosX + halfWidth < leftBorder();
    }


    protected interface FlingListener {
        void onCardExited();

        void leftExit(Object dataObject);

        void rightExit(Object dataObject);

        void onClick(Object dataObject);

        void onScroll(float scrollProgressPercent);
    }
}
