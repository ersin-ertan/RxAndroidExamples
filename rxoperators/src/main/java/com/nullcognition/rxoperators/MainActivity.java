package com.nullcognition.rxoperators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.btnCreatingOperators)
    void creatingOperators() {

        if (getSupportFragmentManager().findFragmentByTag(CreatingObservables.TAG) == null) {

            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.activity_main, new CreatingObservables(), CreatingObservables.TAG)
                                       .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }
}
