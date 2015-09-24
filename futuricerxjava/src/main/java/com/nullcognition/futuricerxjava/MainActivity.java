package com.nullcognition.futuricerxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

// http://futurice.com/blog/top-7-tips-for-rxjava-on-android


public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tip03();
	}

	private void tip01(){
		// by default Rx java is synchronous

		Observable<Integer> observable = Observable.from(new Integer[]{ 1, 2, 3, 4, 5, 6 });

		observable.subscribe(new Action1<Integer>(){
			@Override public void call(final Integer integer){
				Log.d("logErr", Integer.toString(integer));
			}
		});
	}

	private void tip02(){
		// hot and cold observables, cold will wait until a subscriber subs, then will
		// emit or do an operation, while hot will always emit and do operations regardless
		// of who has subbed

		// .cache will save values allow subscribers to receive the same value

		Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>(){

			private int counter = 0;

			@Override public void call(final Subscriber<? super Integer> subscriber){
				subscriber.onNext(counter++);
			}
		});

		observable.subscribe(new Subber());
		observable.subscribe(new Subber());
		observable.subscribe(new Subber());
	}

	private void tip03(){
		// Subjects for being both observable and observer
		// Operations are meant to be atomic and encapsulated, but a subject is exposed to external calls.
		// Cast it into an Observable when made public

		// PublishSubject is has multiple subscribers, to which it broadcasts onNext events, and terminates
		// the connection when onError, or onCompletion is called

	}

	public static class Subber extends Subscriber<Integer>{

		@Override public void onCompleted(){ }
		@Override public void onError(final Throwable e){ }
		@Override public void onNext(final Integer integer){ Log.d("logErr", Integer.toString(integer));}
	}
}
