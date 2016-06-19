package com.nullcognition.rxoperators;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Created by mms on 6/6/16.
 */

public class UtilityOperators extends Fragment {

    public static final String TAG = UtilityOperators.class.getSimpleName();

    public UtilityOperators() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View fragmentView = inflater.inflate(R.layout.fragment_string_observables, container, false);
        LinearLayout linearLayout = new LinearButtonLayout(getContext());

        ButterKnife.bind(this, linearLayout);
        return linearLayout;
    }

//    public String sentence = "Line 0\nline 1 \n line 2. More words and lon\nger word.";
//    public Observable<String> byLine() {
//        Log.d(TAG, "byLine" + sentence);
//        return StringObservable.byLine(Observable.just(sentence));
//    }
//
//    // not sure how this is suppose to work
//    public Observable<String> decode() {
//        Log.d(TAG, "1,0b0010_0101, 0b0000_0101");
//        return StringObservable.decode(Observable.just(new byte[]{1, 0b110_0101, 0b111_0101}), Charset.defaultCharset());
//    }
//
////    public Observable<String> encode() {
////        Log.d(TAG, "repeat just a 3");
////        return StringObservable.encode();
////    }
//
//    // not working
//    public Observable<String> fromS() {
//        Log.d(TAG, "from stringobservable");
//        return StringObservable.from(new StringReader("line0\nline1 more characters\nline2"));
//    }
//
//    public Observable<String> join() {
//        Log.d(TAG, "join with byline observable and |seperator|");
//        return StringObservable.join(RxAndUtils.newObservableString(), "|seperator|");
//    }
//
//    public Observable<String> split() {
//        Log.d(TAG, "split at newline");
//        return StringObservable.split(Observable.just("line0\nline1 more characters\nline2"), Pattern.compile("\\n"));
//    }
//
//    public Observable<String> stringConcat() {
//        Log.d(TAG, "stringConcat ");
//        return StringObservable.stringConcat(Observable.just("line0","line1 more characters","line2"));
//    }
//
//    @OnClick(R.id.btnByLine)
//    void a() {
//        byLine().subscribe(newSubscriber());
//    }
//
//    @OnClick(R.id.btnDecode)
//    void b() {
//        decode().subscribe(newSubscriber());
//    }
//
//    //    @OnClick(R.id.btnEncode)
////    void c() {
////        encode().subscribe(newSubscriber());
////    }
////
//    @OnClick(R.id.btnFromS)
//    void d() {
//        fromS().subscribe(newSubscriber());
//    }
//
//    @OnClick(R.id.btnJoin)
//    void e() {
//        join().subscribe(newSubscriber());
//    }
//
//    @OnClick(R.id.btnSplit)
//    void f() {
//        split().subscribe(newSubscriber());
//    }
//
//    @OnClick(R.id.btnStringConcat)
//    void g() {
//        stringConcat().subscribe(newSubscriberObj());
//    }
}
