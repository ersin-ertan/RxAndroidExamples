package com.nullcognition.danlewgrokkingrxjava.part01;
// ersin 25/09/15 Copyright (c) 2015+ All rights reserved.


import android.util.Log;

import rx.Observable;
import rx.Subscriber;

public class StandardObserverSub{

	public static void _(){
		// observable
		Observable<String> stringObservable = Observable.create(
				new Observable.OnSubscribe<String>(){
					@Override public void call(final Subscriber<? super String> subscriber){
						subscriber.onNext("onNext"); // imperative way
						subscriber.onCompleted();
					}
				}
		);

		// subscriber
		Subscriber<String> stringSubscriber = new Subscriber<String>(){
			@Override public void onCompleted(){Log.d("RX", "onCompleted");}
			@Override public void onError(final Throwable e){ }
			@Override public void onNext(final String s){ Log.d("RX", s);}
		};

		// connection
		stringObservable.subscribe(stringSubscriber);
	}
}
