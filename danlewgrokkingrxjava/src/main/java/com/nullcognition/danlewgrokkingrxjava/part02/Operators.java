package com.nullcognition.danlewgrokkingrxjava.part02;
// ersin 25/09/15 Copyright (c) 2015+ All rights reserved.


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import rx.Observable;

public class Operators{


	@DebugLog public void usage(){

		// what started with using the foreach loop to list each string in the list
		// is now emulated via the Observable.from("url1", "url2", "url3").subscr...
		// resulting in

		query("test").subscribe(urls -> {
			Observable.from(urls)
			          .subscribe(url -> Log.d("RXX", url));
		}); // nested subscribes not good
	}

	@DebugLog public void better(){

		// flatmap - takes emission of an observable and returns any another observable emissions

		// its important to understand what types the lambdas are filling in for
		query("test").flatMap(Observable::from) // was urls -> Observable.from(urls)
				// but the same and only param was used twice thus, the method reference
				.subscribe(url -> Log.d("RXX", url));
	}

	@DebugLog public void evenBetter(){

		// flatmap splits the urls into individual items, using getTitle in flatmap for each url
		// prior to subscribe

		query("test").flatMap(Observable::from) // object-then-function, the input is inferred
				.flatMap(this::getTitle) // was url -> getTitle(url)
				.subscribe(title -> Log.d("RXX", title));

	}

	Observable<List<String>> query(String input){return Observable.just(new ArrayList<>());}

	Observable<String> getTitle(String input){ return Observable.just("title"); }
}
