package com.nullcognition.rxandroidsample;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class MainActivity extends AppCompatActivity{

	public static final String TAG = "RxAndSamples";

	private Handler backgroundHandler;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		BackgroundThread backgroundThread = new BackgroundThread();
		backgroundThread.start();

		backgroundHandler = new Handler(backgroundThread.getLooper());


		// on rotation, triggers twice, i think once for the fragment that was created onCreate, and 2rd for the rotations onCreate
		getSupportFragmentManager().beginTransaction().add(new ReactiveFragment(), ReactiveFragment.TAG).commit();

		observeOnArbitraryThread(); // executed on a different thread
	}

	@OnClick(R.id.scheduler_example) void onTextViewClick(final View view){
		onRunSchedulerExampleButtonClicked();
	}

	private void onRunSchedulerExampleButtonClicked(){

		sampleObservable()
				// Run on a background thread
				.subscribeOn(HandlerScheduler.from(backgroundHandler))

						// Be notified on the main thread
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<String>(){
					@Override public void onCompleted(){Log.d(TAG, "onCompleted()");}
					@Override public void onError(Throwable e){ Log.e(TAG, "onError()", e);}
					@Override public void onNext(String string){ Log.d(TAG, "onNext(" + string + ")");}
				});
	}

	private void observeOnArbitraryThread(){
		new Thread(new Runnable(){
			@Override
			public void run(){
				Looper.prepare();
				final Handler handler = new Handler(); // bound to this thread
				Observable.just("one", "two", "three", "four", "five")
				          .subscribeOn(Schedulers.newThread())
				          .observeOn(HandlerScheduler.from(handler))
				          .subscribe(
						          new Subscriber<String>(){
							          @Override public void onCompleted(){ }
							          @Override public void onError(Throwable e){ }
							          @Override public void onNext(String string){ }
						          }
				          );
				try{Thread.sleep(TimeUnit.SECONDS.toMillis(2));}
				catch(InterruptedException e){throw OnErrorThrowable.from(e);}
			}
		}, "custom-thread-1").start();
	}


	static Observable<String> sampleObservable(){

		return Observable.defer(new Func0<Observable<String>>(){

			@Override public Observable<String> call(){

				try{Thread.sleep(TimeUnit.SECONDS.toMillis(2));}
				catch(InterruptedException e){throw OnErrorThrowable.from(e);}

				return Observable.just("one", "two", "three", "four", "five");
			}
		});
	}

	static class BackgroundThread extends HandlerThread{

		public BackgroundThread(){
			super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
		}
	}
}
