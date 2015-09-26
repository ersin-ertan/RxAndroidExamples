package com.nullcognition.danlewgrokkingrxjava.part03;
// ersin 26/09/15 Copyright (c) 2015+ All rights reserved.


import android.graphics.Bitmap;
import android.util.Log;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SchedulersAndSubscriptions{

	// class exception occurs when you modify a view off the main thread

	// tell observer which thread to run on via subscribeOn()
	// tell you Subscriber which thread to run on via observeOn()

	public void obsService(){
		Observable.just("someUrl") // uses the string method reference due to this input

				// ^ ^ ^ anything before me runs on the io thread
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
						// anything after runs on the main thread
				.subscribe(this::setImageBitmap);

		// attachable to any observable
	}

	public void subscriptions(){

		// .subscribe returns the subscription, which is the link between observable and subscriber
		// the subscription can be used to .unsubscribe

		Subscription subscription = Observable.just("a")
		                                      .subscribe(s -> Log.d("RXX", s));

		subscription.unsubscribe();
	}

	private void setImageBitmap(Bitmap bitmap){ }

	private void setImageBitmap(String url){ }
}
