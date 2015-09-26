package com.nullcognition.danlewgrokkingrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.nullcognition.danlewgrokkingrxjava.part02.Operators;

// basic building blocks are observables and subscribers
// for N subscribers, call onNext when a new value is emitted


public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toast.makeText(MainActivity.this, "Running", Toast.LENGTH_SHORT).show();


	}


}
