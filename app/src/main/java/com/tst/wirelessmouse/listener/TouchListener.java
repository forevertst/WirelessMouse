package com.tst.wirelessmouse.listener;

import android.view.MotionEvent;
import android.view.View;

import com.tst.wirelessmouse.transform.TcpManager;

import java.util.ArrayList;
import java.util.List;

public class TouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            // 第一个手指按下
            case MotionEvent.ACTION_DOWN: {
                int pointerCount = event.getPointerCount();
                int pointerIndex = event.getActionIndex();
                int pointerId = event.getPointerId(pointerIndex);
                float x = event.getX();
                float y = event.getY();
                break;
            }
            // 额外的手指按下
            case MotionEvent.ACTION_POINTER_DOWN: {
                break;
            }

            // 手指移动
            case MotionEvent.ACTION_MOVE: {
                int pointerCount = event.getPointerCount();
//                List<Integer> pos = new ArrayList<>();
//                for (int i = 0; i < pointerCount; i++) {
//                    int x = (int) event.getX(i); // 获取手指的 x 坐标
//                    int y = (int) event.getY(i); // 获取手指的 y 坐标
//                }
                if (pointerCount == 2) {
                    float x = (event.getX(0) + event.getX(1)) / 2;
                    float y = (event.getY(0) + event.getY(1)) / 2;
                    TcpManager.tcpClientSend(x + "," + y);
                }

                break;
            }

            // 额外的手指抬起
            case MotionEvent.ACTION_POINTER_UP: {
                break;
            }
            // 最后一个手指抬起
            case MotionEvent.ACTION_UP: {
                break;
            }

        }

        return true; // 返回 true 表示事件已经处理
    }
}