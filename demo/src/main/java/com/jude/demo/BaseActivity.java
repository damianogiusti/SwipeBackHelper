package com.jude.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jude.swipbackhelper.SwipeBackHelper;

/**
 * Created by Mr.Jude on 2015/9/7.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        swipeBackHelper().onCreate(this);
        swipeBackHelper().getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeSensitivity(0.5f)
                .setSwipeRelateEnable(true)
                .setSwipeRelateOffset(300);
        //ViewServer.get(this).addWindow(this);

    }

    @Override
    public DemoApp getApplicationContext() {
        return (DemoApp) super.getApplicationContext();
    }

    protected SwipeBackHelper swipeBackHelper() {
        return getApplicationContext().getSwipeBackHelper();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackHelper().onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        swipeBackHelper().onDestroy(this);
        //ViewServer.get(this).removeWindow(this);
    }

    public void onResume() {
        super.onResume();
        //ViewServer.get(this).setFocusedWindow(this);
    }





}
