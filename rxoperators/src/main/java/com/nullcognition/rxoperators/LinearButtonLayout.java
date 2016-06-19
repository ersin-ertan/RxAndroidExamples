package com.nullcognition.rxoperators;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by mms on 6/5/16.
 */

public class LinearButtonLayout extends LinearLayout {
    public LinearButtonLayout(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void addButton(int id, String text){
        Button button = new Button(super.getContext());
        button.setId(id);
        button.setText(text);
        this.addView(button, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }


}
