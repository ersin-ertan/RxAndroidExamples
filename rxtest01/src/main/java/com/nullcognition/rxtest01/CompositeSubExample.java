package com.nullcognition.rxtest01;

import android.os.Looper;
import android.util.Log;

import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by mms on 6/2/16.
 */

// this example showcases the composite subscription that does mass unsubscribes, subscriber with
// its three methods,

public class CompositeSubExample {

    public static final String TAG = CompositeSubExample.class.getSimpleName();
    Looper backgroundLooper;

    public CompositeSubExample(Looper looper) {
        backgroundLooper = looper;
    }

    public CompositeSubExample() {
    }

    public void onSchedule(final CompositeSubscription compositeSubscription) {

        final Subscriber subscriber = RxAndUtils.<String>newSubscriber();

        RxAndUtils.justObservable()
                  .compose(RxAndUtils.<String>applySchedulers())
                  .doOnSubscribe(new Action0() {
                      @Override
                      public void call() {
                          Log.d(TAG, "doOnSubscribe this class is:" + this.getClass().getSimpleName());
                          compositeSubscription.add(subscriber);
                      }
                  })
                  .doOnUnsubscribe(new Action0() {
                      @Override
                      public void call() {
                          Log.d(TAG, "doOn-UN-Subscribe this class is:" + this.getClass().getSimpleName());
                      }
                  })
                  .subscribe(subscriber);

    }


}
