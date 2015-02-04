//package com.nullcognition.rxandroidexamples;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import rx.Subscription;
//import rx.android.lifecycle.LifecycleObservable;
//import rx.android.view.OnClickEvent;
//import rx.android.view.ViewObservable;
//import rx.functions.Action1;
//


//import rx.android.app.RxActivity;

//public class MainActivity extends RxActivity { // RxActivity is not importing ...
//
//   Subscription sub;
//   Button       but;
//
//
//   @Override
//   protected void onCreate(Bundle savedInstanceState){
//	  super.onCreate(savedInstanceState);
//	  setContentView(R.layout.activity_main);
//   }
//
//   @Override
//   protected void onPostCreate(Bundle savedInstanceState){
//	  super.onPostCreate(savedInstanceState);
//
//	  LinearLayout layout = (LinearLayout)findViewById(R.id.list_layout);
//
//	  //set the properties for button
//	  but = new Button(this);
//	  but.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//	  but.setText("Button");
//	  but.setId(View.NO_ID);
//
//	  //add button to the layout
//	  layout.addView(but);
//   }
//
//   @Override
//   protected void onStart(){
//	  super.onStart();
//
//	  sub = LifecycleObservable.bindActivityLifecycle(lifecycle(), ViewObservable.clicks(but))
//							   .subscribe(new Action1<OnClickEvent>() {
//
//								  @Override
//								  public void call(OnClickEvent t1){
//									 Toast.makeText(MainActivity.this, "button clicked", Toast.LENGTH_SHORT)
//										  .show();
//
//								  }
//							   });
//
//
//   }
//
//   @Override
//   protected void onPause(){
//	  super.onPause();
//
//	  // Should output "false"
//	  Log.i("onP", "onPause(), isUnsubscribed=" + sub.isUnsubscribed());
//   }
//
//   @Override
//   public boolean onCreateOptionsMenu(Menu menu){
//	  // Inflate the menu; this adds items to the action bar if it is present.
//	  getMenuInflater().inflate(R.menu.menu_main, menu);
//	  return true;
//   }
//
//   @Override
//   public boolean onOptionsItemSelected(MenuItem item){
//	  // Handle action bar item clicks here. The action bar will
//	  // automatically handle clicks on the Home/Up button, so long
//	  // as you specify a parent activity in AndroidManifest.xml.
//	  int id = item.getItemId();
//
//	  //noinspection SimplifiableIfStatement
//	  if(id == R.id.action_settings){
//		 return true;
//	  }
//
//	  return super.onOptionsItemSelected(item);
//   }
//}
