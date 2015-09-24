package com.nullcognition.rxandroidsample;
// ersin 24/09/15 Copyright (c) 2015+ All rights reserved.


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReactiveFragment extends Fragment{

	public static final String TAG = ReactiveFragment.class.getSimpleName();

	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Observable.just("one", "two", "three")
		          .subscribeOn(Schedulers.newThread())
		          .observeOn(AndroidSchedulers.mainThread())
		          .subscribe(new Subscriber<String>(){
			          @Override public void onCompleted(){ Toast.makeText(getActivity(), "onCompleted", Toast.LENGTH_SHORT).show(); }
			          @Override public void onError(Throwable e){ Toast.makeText(getActivity(), "onError", Toast.LENGTH_SHORT).show();}
			          @Override public void onNext(String string){ Toast.makeText(getActivity(), "onNext:" + string, Toast.LENGTH_SHORT).show(); }
		          });
	}
}
