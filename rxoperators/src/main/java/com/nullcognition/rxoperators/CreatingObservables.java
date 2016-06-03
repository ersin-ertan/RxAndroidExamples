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


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatingObservables extends Fragment {

    public static final String TAG = CreatingObservables.class.getSimpleName();

    public CreatingObservables() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_creating_observables, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    public Observable<String> just() {
        Log.d(TAG, "just abc");
        return Observable.just("a", "b", "c");
    }

    public Observable<String> from() {
        Log.d(TAG, "from string[] abc");
        return Observable.from(new String[]{"a", "b", "c"});
    }

    public Observable<String> repeat() {
        Log.d(TAG, "repeat just a 3");
        return Observable.just("a").repeat(3L);
    }

    public Observable<String> repeatWhen() {
        return Observable.just("a", "b", "c").repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                Log.d(TAG, "observeable repeatWhen observable.delay 3");
                return observable.delay(3L, TimeUnit.SECONDS);
            }
        });
    }

    public Observable<String> create() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "create OnSubscribe");
                subscriber.onNext("subscriber.onNext");
            }
        });
    }

    public Observable<String> defer() {
        // create a new observable upon subscription
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("a", "b", "c", String.valueOf(this.hashCode()));
            }
        });
    }

    public Observable<Integer> range() {
        Log.d(TAG, "observable range 0-3");
        return Observable.range(0, 3);
    }

    public Observable<Long> interval() {
        Log.d(TAG, "initial delay 3 sec, interval 1 sec");
        return Observable.interval(3L, 1L, TimeUnit.SECONDS);
    }

    public Observable<Long> timer() {
        Log.d(TAG, " timer 3 sec initial delay");
        return Observable.timer(3L, TimeUnit.SECONDS);
    }

    public Observable<String> empty() {
        Log.d(TAG, "emits nothing then completes");
        return Observable.empty();
    }

    public Observable<String> error() {
        Log.d(TAG, "emit nothing then error");
        return Observable.error(new Throwable("Observable.error"));
    }

    public Observable<String> never() {
        Log.d(TAG, "emits nothing at all");
        return Observable.never();
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

    @OnClick(R.id.btnInterval)
    void h() {
        interval().subscribe(newSubscriberObj());
    }

    @OnClick(R.id.btnTimer)
    void i() {
        timer().subscribe(newSubscriberObj());
    }

    @OnClick(R.id.btnEmpty)
    void j() {
        empty().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnError)
    void k() {
        error().subscribe(newSubscriber());
    }

    @OnClick(R.id.btnNever)
    void l() {
        never().subscribe(newSubscriber());
    }

    public static Subscriber<String> newSubscriber() {
        return new Subscriber<String>() {
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
    }

    public static Subscriber<Object> newSubscriberObj() {
        return new Subscriber<Object>() {
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
    }
}
