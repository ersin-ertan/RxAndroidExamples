package com.nullcognition.danlewgrokkingrxjava.part01;
// ersin 25/09/15 Copyright (c) 2015+ All rights reserved.


// Subscribers to be as lightweight as possible because I might be  running
// them on the main thread. On a more conceptual level, Subscribers are
// supposed to be the thing that reacts, not the thing that mutates

import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class TransformationOperators{

	public static void keepSubsLightweight(){
		Observable.just(1, 2, 3, 4, 5)
		          .subscribe(new Action1<Integer>(){
			          @Override public void call(final Integer integer){
				          Log.d("RX", integer.toString() + " is the int");
				          // try not to mutate the state here
			          }
		          });
	}

	public static void _(){
		Observable.just("one", "two")

		          .map(new Func1<String, String>(){
			          @Override public String call(final String s){return s + " and ";}
		          })
		          .subscribe(new Action1<String>(){
			          @Override public void call(final String s){Log.d("RX", s);}
		          });
	}

	public static void __(){

		Observable.just("Hello, world!")
		          .map(new Func1<String, String>(){
			          @Override public String call(final String s){return s + "an addition";}
		          })
		          .map(new Func1<String, Integer>(){
			          @Override public Integer call(String s){ return s.hashCode();}
		          })
		          .subscribe(new Action1<Integer>(){
			          @Override public void call(final Integer integer){Log.d("RX", Integer.toString(integer)); }
		          });

		// which could be could when trying to go from object to json to database or other such flows
	}
}
