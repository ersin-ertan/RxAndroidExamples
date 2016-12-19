package com.nullcognition.loaders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.philosophicalhacker.lib.RxLoader;
import java.util.List;
import me.tatarka.loadie.Loader;
import me.tatarka.loadie.LoaderManager;
import me.tatarka.loadie.component.LoaderManagerProvider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  public static final String API_URL = "https://api.github.com";

  Button button;

  public static void out(String login, int contributions) {
    System.out.println(login + " (" + contributions + ")");
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    // Create an instance of our GitHub API interface.
    GithubService.GitHub github = retrofit.create(GithubService.GitHub.class);

    // Create a call instance for looking up Retrofit contributors.

    //arturValsilov(github);

    //philoHacker(github);

    loadie(github);
  }

  private void loadie(GithubService.GitHub github) {

    LoaderManager loaderManager = LoaderManagerProvider.forActivity(this);

    //loaderManager.init(0,
    final me.tatarka.loadie.RxLoader<List<GithubService.Contributor>> loader =
        new me.tatarka.loadie.RxLoader<>(github.contributors("square", "retrofit"));

    loader.setCallbacks(new Loader.Callbacks<List<GithubService.Contributor>>() {
      @Override public void onLoaderStart() {
        Toast.makeText(MainActivity.this, "Starting loader", Toast.LENGTH_SHORT).show();
        Log.v("lstrt", "starting loader");
      }

      @Override public void onLoaderResult(List<GithubService.Contributor> contributors) {
        Toast.makeText(MainActivity.this, "Result loader", Toast.LENGTH_SHORT).show();
        for (GithubService.Contributor contributor : contributors) {
          out(contributor.login, contributor.contributions);
        }
      }

      @Override public void onLoaderError(Throwable throwable) {
        Toast.makeText(MainActivity.this, "Error loader", Toast.LENGTH_SHORT).show();
        Log.e("lodie", throwable.getMessage());
      }

      @Override public void onLoaderSuccess() {
        Toast.makeText(MainActivity.this, "Success loader", Toast.LENGTH_SHORT).show();
        Log.v("lsucces", "success loader");
      }
    });

    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        loader.start();
      }
    });
  }

  void arturValsilov(GithubService.GitHub github) {

    LifecycleHandler handler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());

    github.contributors("square", "retrofit")
        .compose(handler.load(R.id.some_request))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Object>() {
          @Override public void call(Object contributors) {
            for (GithubService.Contributor contributor : (List<GithubService.Contributor>) contributors) {
              out(contributor.login, contributor.contributions);
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            Log.e("art", throwable.getMessage());
          }
        });

    //handler.clear(R.id.some_request); // to clear the data
    //handler.reload(R.id.some_request); // reload
  }

  void philoHacker(GithubService.GitHub github) {

    github.contributors("square", "retrofit")
        .compose(RxLoader.<List<GithubService.Contributor>>from(this))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<List<GithubService.Contributor>>() {
          @Override public void call(List<GithubService.Contributor> contributors) {
            for (GithubService.Contributor contributor : contributors) {
              out(contributor.login, contributor.contributions);
            }
          }
        });
  }
}
