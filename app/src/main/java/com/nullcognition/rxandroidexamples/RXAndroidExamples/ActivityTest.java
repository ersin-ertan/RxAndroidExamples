package com.nullcognition.rxandroidexamples.RXAndroidExamples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nullcognition.rxandroidexamples.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hugo.weaving.DebugLog;
import rx.Observable;
import rx.functions.Action1;

// the RX Design - create observables(data emitters), transform data via observable operators, observe/react to the sequences(via observer or subscriber)

public class ActivityTest extends ActionBarActivity {

   List<String> strings = new ArrayList<>();
	String[] input = {"hi", "hello", "hey"};


   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_test);

//	  stream(input);
   }

   /* Creating an Observable from an Existing Data Structure

    converted Observables will synchronously invoke the onNext( ) method of any subscriber that subscribes to them,
    for each item to be emitted by the Observable, and will then invoke the subscriber’s onCompleted( ) method.
   */
   public void createObservableFromDataStruct(){

	  Observable<String> o = Observable.from(input);

	  List<Integer> list = Arrays.asList(2,5,6);
	  Observable<Integer> intOb = Observable.from(list);

	  Observable<String> StringOb = Observable.just("one object");

   }

   /* Creating an Observable via the create( ) method

	implement asynchronous i/o, computational operations, or even “infinite” streams of data by designing your own Observable and implementing it with the create( )
   */
   public void createObservableCreate(){

   }

   public void stream(final String... inStrings){

	  Observable.from(inStrings)
				.subscribe(new Action1<String>() {

				   @DebugLog
				   @Override
				   public void call(String t1){
					  strings.add(t1);
				   }
				});

	  Log.e(getClass().getSimpleName(), strings.toString());

   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu){
	  getMenuInflater().inflate(R.menu.activity_test_menu, menu);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item){
	  int id = item.getItemId();

	  if(id == R.id.action_settings){
		 return true;
	  }

	  return super.onOptionsItemSelected(item);
   }
}
