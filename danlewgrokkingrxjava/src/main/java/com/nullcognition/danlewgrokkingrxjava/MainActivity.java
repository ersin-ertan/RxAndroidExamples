package com.nullcognition.danlewgrokkingrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

// basic building blocks are observables and subscribers
// for N subscribers, call onNext when a new value is emitted


public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void createObservable(){ StandardObserverSub._();}




}
