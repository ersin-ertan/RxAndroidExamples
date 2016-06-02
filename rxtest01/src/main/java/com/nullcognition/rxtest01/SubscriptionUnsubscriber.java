package com.nullcognition.rxtest01;

import android.util.Log;

import com.soundcloud.lightcycle.DefaultActivityLightCycle;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by mms on 6/2/16.
 */

public class SubscriptionUnsubscriber extends DefaultActivityLightCycle<MainActivity> {

    public static final String TAG = SubscriptionUnsubscriber
            .class.getSimpleName();

    public CompositeSubscription getCompositeSubscription() {
        return compositeSubscription;
    }

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void onStart(MainActivity activity) {
        super.onStart(activity);
    }

    @Override
    public void onPause(MainActivity activity) {
        // MainActivity was paused
    }

    @Override
    public void onResume(MainActivity activity) {
        // MainActivity was resumed
    }

    @Override
    public void onDestroy(MainActivity activity) {
        Log.d(TAG, "Composite pre-unsubscribes, has subs: " + compositeSubscription.hasSubscriptions());
        compositeSubscription.unsubscribe();
        Log.d(TAG, "Composite unsubscribes, has subs: " + compositeSubscription.hasSubscriptions());
        super.onDestroy(activity);
    }
}
