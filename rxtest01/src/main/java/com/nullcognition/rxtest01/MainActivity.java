package com.nullcognition.rxtest01;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Process;

import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends LightCycleAppCompatActivity<MainActivity> {
    @LightCycle
    SubscriptionUnsubscriber subscriptionUnsubscriber = new SubscriptionUnsubscriber();

    public static String TAG = MainActivity.class.getSimpleName();
    CompositeSubExample compositeSubExample = new CompositeSubExample();

    @OnClick(R.id.onSchedule)
    public void onSchedule() {
        compositeSubExample.onSchedule(subscriptionUnsubscriber.getCompositeSubscription());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //usingBackgroundThread();
    }

    private void usingBackgroundThread() {
        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        compositeSubExample = new CompositeSubExample(backgroundThread.getLooper());
    }


    @Override
    protected void setActivityContentView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public static class BackgroundThread extends HandlerThread {

        public BackgroundThread() {
            super("BG-Thread", Process.THREAD_PRIORITY_BACKGROUND);
        }
    }
}
