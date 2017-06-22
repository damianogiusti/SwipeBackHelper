package com.jude.swipbackhelper;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by Mr.Jude on 2015/8/3.
 */
public class SwipeBackPage {
    private boolean mEnable = true;
    private boolean mRelativeEnable = false;

    private WeakReference<SwipeBackHelper> swipeBackHelper;
    private Activity activity;
    private SwipeBackLayout swipeBackLayout;
    private RelateSlider slider;

    SwipeBackPage(Activity activity, SwipeBackHelper swipeBackHelper) {
        this.activity = activity;
        this.swipeBackHelper = new WeakReference<>(swipeBackHelper);
    }

    void onCreate() {
        activity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        activity.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        swipeBackLayout = new SwipeBackLayout(activity);
        swipeBackLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slider = new RelateSlider(this);
    }

    void onPostCreate() {
        handleLayout();
    }


    public SwipeBackPage setSwipeRelateEnable(boolean enable) {
        mRelativeEnable = enable;
        slider.setEnable(enable);
        return this;
    }

    public SwipeBackPage setSwipeRelateOffset(int offset) {
        slider.setOffset(offset);
        return this;
    }

    public SwipeBackPage setSwipeBackEnable(boolean enable) {
        mEnable = enable;
        swipeBackLayout.setEnableGesture(enable);
        handleLayout();
        return this;
    }

    private void handleLayout() {
        if (mEnable || mRelativeEnable) {
            swipeBackLayout.attachToActivity(activity);
        } else {
            swipeBackLayout.removeFromActivity(activity);
        }
    }

    public SwipeBackPage setSwipeEdge(int swipeEdge) {
        swipeBackLayout.setEdgeSize(swipeEdge);
        return this;
    }

    public SwipeBackPage setSwipeEdgePercent(float swipeEdgePercent) {
        swipeBackLayout.setEdgeSizePercent(swipeEdgePercent);
        return this;
    }

    public SwipeBackPage setSwipeSensitivity(float sensitivity) {
        swipeBackLayout.setSensitivity(activity, sensitivity);
        return this;
    }

    public SwipeBackPage setScrimColor(int color) {
        swipeBackLayout.setScrimColor(color);
        return this;
    }

    public SwipeBackPage setClosePercent(float percent) {
        swipeBackLayout.setScrollThreshold(percent);
        return this;
    }

    public SwipeBackPage setDisallowInterceptTouchEvent(boolean disallowIntercept) {
        swipeBackLayout.setDisallowInterceptTouchEvent(disallowIntercept);
        return this;
    }

    public SwipeBackPage addListener(SwipeListener listener) {
        swipeBackLayout.addSwipeListener(listener);
        return this;
    }

    public SwipeBackPage removeListener(SwipeListener listener) {
        swipeBackLayout.removeSwipeListener(listener);
        return this;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }

    public void scrollToFinishActivity() {
        swipeBackLayout.scrollToFinishActivity();
    }

    Activity getActivity() {
        return activity;
    }

    void setActivity(Activity activity) {
        this.activity = activity;
    }

    SwipeBackHelper getSwipeBackHelper() {
        return swipeBackHelper.get();
    }
}
