package com.nullcognition.rxoperators;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.btnCreatingObservables)
    void creatingObservables() {
        startFragment(new CreatingObservables());
    }

    @OnClick(R.id.btnStringObservables)
    void stringObservables() {
        startFragment(new StringObservables());
    }

    @OnClick(R.id.btnUtilityOperators)
    void utilityOperators() {
        startFragment(new UtilityOperators());
    }

    private void startFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(CreatingObservables.TAG) == null) {
            fm.beginTransaction()
              .replace(R.id.activity_main, fragment, CreatingObservables.TAG)
              .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        RxAndUtils.unsubscribeSubscriptions();
        super.onDestroy();
    }
}
