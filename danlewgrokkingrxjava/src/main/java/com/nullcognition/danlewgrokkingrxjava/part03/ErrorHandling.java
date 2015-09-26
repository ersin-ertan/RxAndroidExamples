package com.nullcognition.danlewgrokkingrxjava.part03;
// ersin 26/09/15 Copyright (c) 2015+ All rights reserved.


import android.util.Log;

import rx.Observable;
import rx.Subscriber;

public class ErrorHandling{

	public void errorH(){
		Observable.just("test")
		          .map(this::potentialException)
		          .subscribe(new Subscriber<String>(){
			          @Override public void onCompleted(){
				          Log.d("RXX", "onCompleted"); // know when the subscription is done
			          }
			          @Override public void onError(final Throwable e){
				          Log.d("RXX", e.toString());
				          // called if an exception is thrown at anytime
				          // operators do not handle the exception, subscribers do
			          }
			          @Override public void onNext(final String s){
				          Log.d("RXX", s);
			          }
		          });
	}
	private String potentialException(final String s){
		return null;
	}
}
