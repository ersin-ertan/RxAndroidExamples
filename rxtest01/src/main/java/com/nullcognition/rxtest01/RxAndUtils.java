package com.nullcognition.rxtest01;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by mms on 6/2/16.
 */

public class RxAndUtils {

    public static final String TAG = RxAndUtils.class.getSimpleName();

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())// AndroidSchedulers.from(backgroundLooper)
                                 .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static Observable<String> justObservable() {

        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(4));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    public static <T> Subscriber<T> newSubscriber() {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError()", e);

            }

            @Override
            public void onNext(T t) {
                Log.d(TAG, "onNext(" + t.toString() + ")");
            }
        };
    }

}
