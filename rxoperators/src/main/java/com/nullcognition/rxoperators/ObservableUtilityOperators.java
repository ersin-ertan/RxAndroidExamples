package com.nullcognition.rxoperators;

import rx.Notification;
import rx.Observable;

/**
 * Created by mms on 6/19/16.
 */

public class ObservableUtilityOperators {

    void materialize(){
        Observable<Notification<Integer>> observableNotifications = Observable.just(1, 2, 3).materialize();

        Notification<Integer> notifications;
    }
}
