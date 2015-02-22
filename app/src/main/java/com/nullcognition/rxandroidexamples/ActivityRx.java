package com.nullcognition.rxandroidexamples;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.app.RxActivity;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;


public class ActivityRx extends RxActivity {

   @InjectView(R.id.etRx)
   EditText et;

   @InjectView(R.id.etRx2)
   EditText et2;

   @InjectView(R.id.etRx3)
   EditText et3;

   @InjectView(R.id.btRx)
   Button btRx;

   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);

	  setContentView(R.layout.activity_activity_rx);
	  ButterKnife.inject(this);

	  filterAndSubscribe();

	  mappingAndTransforming();
	  // we want the subscriber called when the validity changes. For this, we'd need to call distinctUntilChanged() method on our Observables
	  // not on every/any input, even when it is not important to the field that is being polled

   } // using this, can change color based on length, validity

   private void mappingAndTransforming(){

	  final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"); // ..... [1]

	  Observable<Boolean> userNameValid = WidgetObservable.text(et2) //  [2]
		.map(e -> e.text())
		.map(t -> t.length() > 4);

	  Observable<Boolean> emailValid = WidgetObservable.text(et3)
													   .map(e -> e.text())
													   .map(t -> emailPattern.matcher(t)
																			 .matches());

	  emailValid.distinctUntilChanged()
				.map(b -> b ? Color.BLACK : Color.RED)
				.subscribe(color -> et3.setTextColor(color));     // ... [3]

	  userNameValid.distinctUntilChanged()
				   .map(b -> b ? Color.BLACK : Color.RED)
				   .subscribe(color -> et2.setTextColor(color));

	  // branching with the button
	  Observable<Boolean> registerEnabled = Observable.combineLatest(userNameValid, emailValid, (a, b) -> a && b);
	  registerEnabled.distinctUntilChanged()
					 .subscribe(enabled -> btRx.setEnabled(enabled));

	  // distinctUntilChanged is a performance optimization
	  // since the change was occuring the subscriber is ca lled on every letter input regardless of the validity status change
   }

   private void filterAndSubscribe(){

	  Observable<OnTextChangeEvent> txtCh = WidgetObservable.text(et);

	  txtCh.filter(f -> f.text()
						 .length() > 3)
		   .subscribe(inOnTextChangeEvent -> {
			  Log.d("textChanged", inOnTextChangeEvent.text()
													  .toString());
			  et.setTextColor(R.color.primary_material_dark);
		   });

	  // shorter

//	  txtCh.filter(inOnTextChangeEvent -> inOnTextChangeEvent.text().length() > 3).subscribe(inOnTextChangeEvent -> et.setTextColor(R.color.highlighted_text_material_dark));
   }
   // or other params given what even you restriction you can imagine


   @Override
   public boolean onCreateOptionsMenu(Menu menu){
	  getMenuInflater().inflate(R.menu.menu_activity_rx, menu);
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
