package com.jude.demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.utils.JUtils;

/**
 * Created by Damiano Giusti on 22/06/17.
 */
public class DemoApp extends Application {

    private SwipeBackHelper swipeBackHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        swipeBackHelper = new SwipeBackHelper();

        Fresco.initialize(this);
        JUtils.initialize(this);
    }

    public SwipeBackHelper getSwipeBackHelper() {
        return swipeBackHelper;
    }
}
