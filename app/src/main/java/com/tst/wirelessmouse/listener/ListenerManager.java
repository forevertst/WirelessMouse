package com.tst.wirelessmouse.listener;

import android.view.View;

import com.tst.wirelessmouse.Global;

public class ListenerManager {
    public static void startListener(){
        View rootView = Global.getMainActivity().getWindow().getDecorView();
        rootView.setOnTouchListener(new TouchListener());
    }
}
