package com.sapstern.track_my_race.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by matthias on 27/12/16.
 */

public class RunningTextView extends TextView {
    public RunningTextView(Context context) {
        super(context);
    }

    RunningTextView(Context context, AttributeSet attrs){super(context, attrs);}
    RunningTextView(Context context, AttributeSet attrs, int defStyleAttr){super(context, attrs, defStyleAttr);}

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if(focused)
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if(focused)
            super.onWindowFocusChanged(focused);
    }


    @Override
    public boolean isFocused() {
        return true;
    }
}
