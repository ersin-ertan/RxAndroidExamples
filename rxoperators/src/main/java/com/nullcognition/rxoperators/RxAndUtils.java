package com.nullcognition.rxoperators;

import android.util.Log;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mms on 6/3/16.
 */

public class RxAndUtils {

    public static final String TAG = RxAndUtils.class.getSimpleName();
    private static final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public static Subscriber<String> newSubscriber() {
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "----- onCompleted\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(String next) {
                Log.d(TAG, next);
            }
        };
        registerToComposite(subscriber);
        return subscriber;
    }

    public static Subscriber<Object> newSubscriberObj() {
        Subscriber subscriber = new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "----- onCompleted\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(Object next) {
                Log.d(TAG, next.toString());
            }
        };
        registerToComposite(subscriber);
        return subscriber;
    }

    private static void registerToComposite(Subscriber subscriber) {
        compositeSubscription.add(subscriber);
    }

    public static void unsubscribeSubscriptions() {
        Log.d(TAG, "*Subscriptions unsubscribed*");
        compositeSubscription.unsubscribe();
    }
}
