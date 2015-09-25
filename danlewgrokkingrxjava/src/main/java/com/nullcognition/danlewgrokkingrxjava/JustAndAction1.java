package com.nullcognition.danlewgrokkingrxjava;
// ersin 25/09/15 Copyright (c) 2015+ All rights reserved.


import android.util.Log;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class JustAndAction1{

	public static void _(){
		Observable<String> observable = Observable.just("observable just");

		Action1<String> onNext = new Action1<String>(){
			@Override public void call(final String s){ Log.d("RX", s); }
		};
		Action1<Throwable> onError = new Action1<Throwable>(){
			@Override public void call(final Throwable s){ Log.d("RX", s.toString()); }
		};
		Action0 onComplete = new Action0(){
			@Override public void call(){ Log.d("RX", "onCompleted"); }
		};

		observable.subscribe(onNext, onError, onComplete);

		// or

		Observable.just("test")
		          .subscribe(new Action1<String>(){
			          @Override public void call(final String s){ Log.d("RX", s);}
		          });

		// use java lambdas to consolidate
	}
}
