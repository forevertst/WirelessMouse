package com.tst.wirelessmouse;

import android.app.Activity;

public class Global {
    static Activity g_mainActivity;

    public static Activity getMainActivity() {
        return g_mainActivity;
    }

    public static void setMainActivity(Activity mainActivity) {
        g_mainActivity = mainActivity;
    }


}
