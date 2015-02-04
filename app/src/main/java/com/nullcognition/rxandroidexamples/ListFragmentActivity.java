package com.nullcognition.rxandroidexamples;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import rx.Observable;
import rx.Subscriber;
import rx.android.app.AppObservable;
import rx.android.widget.OnListViewScrollEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;


/**
 * Problem:
 * You have an asynchronous sequence that emits items to be displayed in a list. You want the data
 * to survive rotation changes.
 * <p/>
 * Solution:
 * Combine {@link android.app.Fragment#setRetainInstance(boolean)} in a ListFragment with
 * {@link rx.android.schedulers.AndroidSchedulers#mainThread()} and an {@link rx.Observable.Operator}
 * that binds to the list adapter.
 */

public class ListFragmentActivity extends ActionBarActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_list_fragment);
   }


   @Override
   public boolean onCreateOptionsMenu(Menu menu){
	  // Inflate the menu; this adds items to the action bar if it is present.
	  getMenuInflater().inflate(R.menu.menu_list, menu);
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


   @SuppressWarnings("ConstantConditions")
   public static class RetainedListFragment extends Fragment {

	  private ArrayAdapter<String> adapter;

	  public RetainedListFragment(){
		 setRetainInstance(true);
	  }

	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		 View view = inflater.inflate(R.layout.list_fragment, container, false);

		 adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
		 ListView listView = (ListView)view.findViewById(android.R.id.list);
		 listView.setAdapter(adapter);

		 AppObservable.bindFragment(this, SampleObservables.numberStrings(1, 500, 100))
					  .observeOn(mainThread())
					  .lift(new BindAdapter())
					  .subscribe();

		 final ProgressBar progressBar = (ProgressBar)view.findViewById(android.R.id.progress);
		 AppObservable.bindFragment(this, WidgetObservable.listScrollEvents(listView))
					  .subscribe(new Action1<OnListViewScrollEvent>() {

						 @Override
						 public void call(OnListViewScrollEvent event){
							if(event.totalItemCount() == 0){
							   return;
							}

							int progress = (int)((100.0 * (event.firstVisibleItem() + event.visibleItemCount())) / event.totalItemCount());
							progressBar.setProgress(progress);
						 }
					  });

		 return view;
	  }


	  private final class BindAdapter implements Observable.Operator<String, String> {

		 @Override
		 public Subscriber<? super String> call(Subscriber<? super String> subscriber){
			return new Subscriber<String>() {

			   @Override
			   public void onCompleted(){
				  adapter.notifyDataSetChanged();
			   }

			   @Override
			   public void onError(Throwable throwable){

			   }

			   @Override
			   public void onNext(String strings){
				  adapter.add(strings);
			   }
			};
		 }
	  }
   }


}
