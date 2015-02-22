package com.nullcognition.rxandroidexamples.RXAndroidExamples;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import rx.Subscription;
import rx.android.app.RxActivity;
import rx.android.lifecycle.LifecycleObservable;
import rx.android.view.OnClickEvent;
import rx.android.view.ViewObservable;
import rx.functions.Action1;
/**
 * Simple example of creating a Subscription that is bound to the lifecycle
 * (and thus automatically unsubscribed when the Activity is destroyed).
 */
public class LifecycleObservableActivity extends RxActivity {

   private static final String TAG = LifecycleObservableActivity.class.getSimpleName();

   private Button button;

   private Subscription subscription;

   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);

	  button = new Button(this);
	  button.setText("Click Me!");
	  setContentView(button);
   }

   @Override
   protected void onStart(){
	  super.onStart();

	  subscription = LifecycleObservable.bindActivityLifecycle(lifecycle(), ViewObservable.clicks(button))
										.subscribe(new Action1<OnClickEvent>() {

										   @Override
										   public void call(OnClickEvent onClickEvent){
											  Toast.makeText(LifecycleObservableActivity.this, "Clicked button!", Toast.LENGTH_SHORT)
												   .show();
										   }
										});
   }

   @Override
   protected void onPause(){
	  super.onPause();

	  // Should output "false"
	  Log.i(TAG, "onPause(), isUnsubscribed=" + subscription.isUnsubscribed());
   }

   @Override
   protected void onStop(){
	  super.onStop();

	  // Should output "true"
	  Log.i(TAG, "onStop(), isUnsubscribed=" + subscription.isUnsubscribed());
   }
}
