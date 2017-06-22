package com.jude.swipbackhelper;

import android.os.Build;

import java.lang.ref.WeakReference;

/**
 * Created by Mr.Jude on 2015/8/26.
 */
class RelateSlider implements SwipeListener {

    private WeakReference<SwipeBackPage> currentPage;
    private static final int DEFAULT_OFFSET = 40;
    private int offset = 500;

    RelateSlider(SwipeBackPage currentPage) {
        this.currentPage = new WeakReference<>(currentPage);
        //currentPage.addListener(this);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setEnable(boolean enable) {
        SwipeBackPage currentPage = this.currentPage.get();
        if (currentPage == null) {
            return;
        }

        if (enable) {
            currentPage.addListener(this);
        } else {
            currentPage.removeListener(this);
        }
    }

    @Override
    public void onScroll(float percent, int px) {
        if (Build.VERSION.SDK_INT > 11) {
            SwipeBackPage page = getPageBeforeCurrent();
            if (page != null) {
                page.getSwipeBackLayout().setX(Math.min(-offset * Math.max(1 - percent, 0) + DEFAULT_OFFSET, 0));
                if (percent == 0) {
                    page.getSwipeBackLayout().setX(0);
                }
            }
        }
    }

    @Override
    public void onEdgeTouch() {

    }

    @Override
    public void onScrollToClose() {
        SwipeBackPage page = getPageBeforeCurrent();
        if (Build.VERSION.SDK_INT > 11) {
            if (page != null) page.getSwipeBackLayout().setX(0);
        }
    }

    private SwipeBackPage getPageBeforeCurrent() {
        SwipeBackPage currentPage = this.currentPage.get();
        if (currentPage == null) {
            return null;
        }
        SwipeBackHelper swipeBackHelper = currentPage.getSwipeBackHelper();
        return swipeBackHelper.getPageBefore(currentPage);
    }
}
