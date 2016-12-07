package com.nullcognitino.rxextras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Api api = new Api();

    api.getKey().map(new Func1<String, Single<String>>() {

      @Override public Single<String> call(String key) {

        return Single.create(new Single.OnSubscribe<String>() {

          @Override public void call(final SingleSubscriber<? super String> subscriber) {
            Foo foo = new Foo();
            foo.setAwesomeCallback(new AwesomeCallback() {
              @Override public void onAwesomeReady(String awesome) {
                subscriber.onSuccess(awesome);
              }
            });
            foo.makeAwesome();
          }
        });
      }
    }).flatMap(new Func1<Single<String>, Completable>() {
      @Override public Completable call(Single<String> stringSingle) {
        return null;
      }

      //    .subscribe(new Action1<Completable>() {
      //  @Override public void call(Completable completable) {
      //    handleAwesomeSent();
      //  }
      //}, new Action1<Throwable>() {
      //  @Override public void call(Throwable throwable) {
      //    handleAwesomeFail();
      //  }
      //});
    }
  }
  public void handleAwesomeSent() {
  }

  public void handleAwesomeFail() {
  }

  public void sendAwesome(String s) {
  }

  public class Api {
    public Single<String> getKey() {
      return Single.just("api");
    }
  }

  public class Foo {
    private AwesomeCallback awesomeCallback;

    public String makeAwesome() {
      return awesomeCallback.onAwesomeReady();
    }

    public void setAwesomeCallback(AwesomeCallback ac) {
      awesomeCallback = ac;
    }
  }

  interface AwesomeCallback {
    public void onAwesomeReady(String awesome);
  }
}
