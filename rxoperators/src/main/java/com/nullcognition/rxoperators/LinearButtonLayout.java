package com.nullcognition.rxoperators;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by mms on 6/5/16.
 */

public class LinearButtonLayout extends LinearLayout {
    public LinearButtonLayout(Context context) {
        super(context);
    }

    public void addButton(int id, String text){
        this.addView(new Button(super.getContext()));
    }


}
