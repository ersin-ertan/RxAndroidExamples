package com.nullcognition.rxoperators;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

import static com.nullcognition.rxoperators.RxAndUtils.newSubscriber;
import static com.nullcognition.rxoperators.RxAndUtils.newSubscriberObj;

/**
 * Created by mms on 6/3/16.
 */

public class StringObservables extends Fragment{

    public static final String TAG = StringObservables.class.getSimpleName();

    public StringObservables() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_creating_observables, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    public Observable<String> byLine() {
        Log.d(TAG, "byLine abc");
        return String.byLine("a", "b", "c");
    }

    public Observable<String> decode() {
        Log.d(TAG, "from string[] abc");
        return Observable.from(new String[]{"a", "b", "c"});
    }

    public Observable<String> encode() {
        Log.d(TAG, "repeat just a 3");
        return Observable.just("a").repeat(3L);
    }

    public Observable<String> from() {
        return Observable.just("a", "b", "c").repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                Log.d(TAG, "observeable repeatWhen observable.delay 3");
                return observable.delay(3L, TimeUnit.SECONDS);
            }
        });
    }

    public Observable<String> join() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "create OnSubscribe");
                subscriber.onNext("subscriber.onNext");
            }
        });
    }

    public Observable<String> split() {
        // create a new observable upon subscription
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("a", "b", "c", String.valueOf(this.hashCode()));
            }
        });
    }

    public Observable<Integer> stringConcat() {
        Log.d(TAG, "observable range 0-3");
        return Observable.range(0, 3);
    }

    @OnClick(R.id.btnJust)
    void a() {
        just().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnFrom)
    void b() {
        from().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnRepeat)
    void c() {
        repeat().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnRepeatWhen)
    void d() {
        repeatWhen().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnCreate)
    void e() {
        create().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnDefer)
    void f() {
        defer().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnRange)
    void g() {
        range().subscribe(newSubscriberObj());
    }
}