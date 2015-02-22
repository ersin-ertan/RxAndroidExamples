package com.nullcognition.rxandroidexamples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;


public class ActivityRx extends ActionBarActivity {

   @InjectView(R.id.etRx)
   EditText et;

   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_activity_rx);
	  ButterKnife.inject(this);

	  Observable<OnTextChangeEvent> txtCh = WidgetObservable.text(et);
	  txtCh.filter(f -> f.text()
						 .length() > 2)
		   .subscribe(inOnTextChangeEvent -> Log.d("[Rx]", inOnTextChangeEvent.text()
																			  .toString()));
   } // using this, can change color based on length, validity
   // or other params given what even you restriction you can imagine



   @Override
   public boolean onCreateOptionsMenu(Menu menu){
	  // Inflate the menu; this adds items to the action bar if it is present.
	  getMenuInflater().inflate(R.menu.menu_activity_rx, menu);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item){
	  // Handle action bar item clicks here. The action bar will
	  // automatically handle clicks on the Home/Up button, so long
	  // as you specify a parent activity in AndroidManifest.xml.
	  int id = item.getItemId();

	  //noinspection SimplifiableIfStatement
	  if(id == R.id.action_settings){
		 return true;
	  }

	  return super.onOptionsItemSelected(item);
   }
}
