package com.jude.swipbackhelper;

import android.app.Activity;

import java.util.Stack;

public class SwipeBackHelper {

    private final Stack<SwipeBackPage> pageStack = new Stack<>();

    private SwipeBackPage findHelperByActivity(Activity activity) {
        for (SwipeBackPage swipeBackPage : pageStack) {
            if (swipeBackPage.getActivity() == activity) {
                return swipeBackPage;
            }
        }
        return null;
    }

    public SwipeBackPage getCurrentPage(Activity activity) {
        SwipeBackPage page = findHelperByActivity(activity);
        if (page == null) {
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        return page;
    }

    public void onCreate(Activity activity) {
        SwipeBackPage page = findHelperByActivity(activity);
        if (page == null) {
            page = pageStack.push(new SwipeBackPage(activity, this));
        }
        page.onCreate();
    }

    public void onPostCreate(Activity activity) {
        SwipeBackPage page = findHelperByActivity(activity);
        if (page == null) {
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        page.onPostCreate();
    }

    public void onDestroy(Activity activity) {
        SwipeBackPage page = findHelperByActivity(activity);
        if (page == null) {
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        pageStack.remove(page);
        page.setActivity(null);
    }

    public void finish(Activity activity) {
        SwipeBackPage page = findHelperByActivity(activity);
        if (page == null) {
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        page.scrollToFinishActivity();
    }

    SwipeBackPage getPageBefore(SwipeBackPage activity) {
        int index = pageStack.indexOf(activity);
        if (index > 0) {
            return pageStack.get(index - 1);
        } else {
            return null;
        }
    }
}
