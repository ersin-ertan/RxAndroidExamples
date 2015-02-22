package com.nullcognition.rxandroidexamples;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ersin on 22/02/15 at 2:16 AM
 */

// remember - retro lambda does work with Google Play Services

// Functional programming: transform and compose streams of immutable sequences by applying map, filter and reduce

// Reactive programming: focus on data flow and change propagation

public class RxJavaExamples {

   public static void basicSub(){
	  Observable<String> ob1 = Observable.create(new Observable.OnSubscribe<String>() {

		 @Override
		 public void call(Subscriber<? super String> sub){
			sub.onNext("sub.onNext() - Hello Subscriber");
			sub.onCompleted();
		 }
	  });

	  ob1.subscribe((String s) -> Log.e("subscribe", "Sub1 with lambda" + s));

	  // or, top is much cleaner but requires the one method only, and for the user to know what it method is

	  ob1.subscribe(new Action1<String>() {

		 @Override
		 public void call(String s){
			Log.e("subscribe", "Sub2 in full" + s);
		 }
	  });

   }

   // built using from, subscribers receive the item from the Iterable collection 1 by 1
   public static void usingList(){

	  List<String> list = Arrays.asList("a", "b", "c", "d");
	  Observable<String> obs = Observable.from(list);

	  obs.subscribe((String str) -> Log.e("list", "hello " + str));
   }

   public static void usingMap(){

	  List<String> list = Arrays.asList("a", "b", "c", "d");
	  Observable<String> obs = Observable.from(list);

	  obs.map(new Func1<String, String>() {

		 @Override
		 public String call(String s){
			return s.toUpperCase();
		 }
	  })
		 .subscribe(new Action1<String>() {

			@Override
			public void call(String s){
			   String greet = "Sub 1 on " + ": Hello " + s + "!";
			   Log.e("map", greet);

			}
		 });

	  // or

	  obs.map((String s) -> s.toUpperCase()) // the (String s) can be inferred to s
		 .subscribe(s -> Log.e("map lambda", ""));

   }

   public static void basicAction(final Integer... ints){

	  Observable.from(ints)
				.subscribe(new Action1<Integer>() {

				   @Override
				   public void call(Integer t1){
					  Log.e("basic action", ints.toString());
				   }
				});
   }

   public static void observableFromDataStruct(){

	  Observable<Integer> intOb = Observable.from(Arrays.asList(1, 3, 5));

	  List<Integer> list = Arrays.asList(2, 4, 6);
	  Observable<Integer> listOb = Observable.from(list);

	  Observable<String> stringOb = Observable.just("One Object");

	  // Will synchonously invoke onNext() of any subscriber, for each item to be emmitted by the observable, and the subscribers onCompeted() method when done

   }

   public static void createObservable(){

	  // for asynchronous io, computational operations and infinite streams, design your own observable

   }


}
