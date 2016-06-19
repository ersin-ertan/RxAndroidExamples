package com.nullcognitino.rxextras;

import com.github.davidmoten.rx.Transformers;

import rx.Observable;

/**
 * Created by mms on 6/19/16.
 */


// what is a transformer

public class ExTransformers {

    void ignoreElementsThen(){
        Transformers.ignoreElementsThen(Observable.just(1,2,3));
    }
}
