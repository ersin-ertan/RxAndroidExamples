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

		// others: behaviour subject, async subject, publish subject, replay subject
	}

	private void tip04(){

		// async operations that go out of the ui thread must often come back to it
		// change threads that are operating on with subscribeOn(...), thus making the call async
		// to go back use observeOn(AndroidSchedulers.mainThread())

		// do switch to main thread in data layer, right after the operation has completed
		// if the value is coming synchronously from the cache, the switch in the main thread
		// can cause rendering delay

		// custom Observable cannot be unsubscribed from until its onSubscribe function has finished
		// thus any blocking calls on the thread will keep the observable from being unsubbed
	}

	private void tip05(){

		/* read the rxjava docs - main ones being

		Observable.from() - synchronously converts iterable to observable, emitting all values then calling onComplete

		Observable.just(<Object>...) - like ^ but emits one object on onNext then onComplete

		Observable.create - create your own Observable if all logic fits within the single closure

		Observable.merge - combine 2+ observables

		Observable.error - emits an error

		.map - all values of a stream are mapped(operated on) and the products is another stream

		.filter - takes a function that evaluates to true or false, thus filtering the streams value based on the result

		.combineLatest - take two latest values, combined to a new one, triggers even if one value changes

		.zip - like ^ but both values must be new

		.manyMap/.flatMap - chaining async returns, appending one stream to another

		.cache - like onSubscribe, but provides the cached value - to all or just the subscriber?
		*/
	}

	private void tip06(){
		// subscribing with Observer(has onNext,onCompleted, onError) vs Action1(can override only one call per sub, create another action for 2/3 else use observable)
	}

	private void tip07(){
		// subscriptions leak memory, by subbing, you give a strong reference to the observer(a closure/anon inner class)
		// thus the observer will not be gc'd untill the Observable is.
		// call subscribe to return the subscription, then unsub, knowing when is difficult
		// create a custom WeakReference scheme in your application, or to make sure everything is properly unsubscribed from

		// ave activities and fragments handle all subscriptions to static instances and unsubscribe accordingly. This would include not letting views make
		// subscriptions to static instances at all. For this approach you can also check the AndroidObservable operation in the RxJava to tie the observable to an
		// activity or a fragment. It, for instance, deactivates the observable if the fragment is not added
	}

	public static class Subber extends Subscriber<Integer>{

		@Override public void onCompleted(){ }
		@Override public void onError(final Throwable e){ }
		@Override public void onNext(final Integer integer){ Log.d("logErr", Integer.toString(integer));}
	}
}



































