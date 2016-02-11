package com.reginald.editablespinner;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AutoCompleteTextView;

/**
 * Created by baidu on 16/2/10.
 */
public class EditableSpinner extends AutoCompleteTextView {

    private static final boolean DEBUG = BuildConfig.DEBUG_LOG;
    private static final String TAG = "EditableSpinner";

    private boolean mPopupWindowShowed = false;
    private boolean mRightDrawableDown = false;

    public EditableSpinner(Context context) {
        super(context);
    }

    public EditableSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditableSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFromAttributes(context, attrs, defStyleAttr);
    }

    private void initFromAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
//        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PatternLockView, defStyleAttr, 0);
//
//        a.recycle();
    }


    @Override
    public boolean enoughToFilter() {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (DEBUG) {
            Log.d(TAG, "onTouchEvent() event = " + event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (DEBUG) {
                    Log.d(TAG, "isPopupShowing() = " + isPopupShowing() + " ,mRightDrawableDown = " + isPointInRightDrawable(event) + " ,mPopupWindowShowed = " + mPopupWindowShowed);
                }
                if (isPointInRightDrawable(event)) {
                    mRightDrawableDown = true;
                    return true;
                } else {
                    mRightDrawableDown = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (mRightDrawableDown && isPointInRightDrawable(event)) {
                    if (DEBUG) {
                        Log.d(TAG, "RIGHT DRAWABLE CLICKED!");

                        if (hasFocus()) {
                            clearFocus();
                        }
                        requestFocus();
                        showDropDown();
                        mPopupWindowShowed = true;
                        if (DEBUG) {
                            Log.d(TAG, "onRightDrawableClicked showDropDown");
                        }
                        return true;
                    }
                }
            }
        }

        return super.onTouchEvent(event);
    }

    private boolean isPointInRightDrawable(MotionEvent event) {
        if (DEBUG) {
            Log.d(TAG, String.format("getRight() = %d, getCompoundPaddingRight() = %d,", getRight(), getCompoundPaddingRight()));
        }
        int drawableLeft = getRight() - getCompoundPaddingRight();
        int drawableRight = getRight();
        int drawableTop = 0;
        int drawableBottom = getHeight();

        if (DEBUG) {
            Log.d(TAG, String.format("x = %d, y = %d, drawableLeft = %d, drawableRight = %d, drawableTop = %d, drawableBottom = %d",
                    (int) event.getX(), (int) event.getY(), drawableLeft, drawableRight, drawableTop, drawableBottom));
        }
        if (event.getX() > drawableLeft && event.getX() < drawableRight && event.getY() > drawableTop && event.getY() < drawableBottom) {
            return true;
        } else {
            return false;
        }
    }

    public void setDrawable(Drawable drawable) {
        setDrawable(drawable, -1, -1);
    }

    public void setDrawable(Drawable drawable, int width, int height) {
        if (width >= 0 && height >= 0) {
            drawable.setBounds(new Rect(0, 0, width, height));
            setCompoundDrawables(null, null, drawable, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
    }

    public void setRightDrawablePadding(int padding) {
        setCompoundDrawablePadding(padding);
    }

}
