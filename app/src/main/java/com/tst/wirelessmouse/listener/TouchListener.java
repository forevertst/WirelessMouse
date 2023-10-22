package com.tst.wirelessmouse.listener;

import android.view.MotionEvent;
import android.view.View;

import com.tst.wirelessmouse.transform.TcpManager;

public class TouchListener implements View.OnTouchListener {
    long leftMouseClickTime;
    long rightMouseClickTime;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
//        int pointerCount = event.getPointerCount();
//        int pointerIndex = event.getActionIndex();
//        int pointerId = event.getPointerId(pointerIndex);
        switch (action) {
            // 第一个手指按下
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            // 额外的手指按下
            case MotionEvent.ACTION_POINTER_DOWN: {
                int pointerCount = event.getPointerCount();
                boolean isLeft = false;
                if (pointerCount == 2) {
                    int pointerIndex = event.getActionIndex();
                    isLeft = (event.getX(pointerIndex) - event.getX(pointerIndex == 0 ? 1 : 0)) < 0;
                }
                if(isLeft)
                {
                    if (System.currentTimeMillis() - leftMouseClickTime < 300) {
                        TcpManager.tcpClientSend("mouseLeftClick");
                    }
                }else{
                    if (System.currentTimeMillis() - rightMouseClickTime < 300) {
                        TcpManager.tcpClientSend("mouseRightClick");
                    }
                }

                if (pointerCount == 2) {
                    TcpManager.tcpClientSend("mousePosInit");
                }
                break;
            }

            // 手指移动
            case MotionEvent.ACTION_MOVE: {
                int pointerCount = event.getPointerCount();
                if (pointerCount == 2) {
                    float x = (event.getX(0) + event.getX(1)) / 2;
                    float y = (event.getY(0) + event.getY(1)) / 2;
                    TcpManager.tcpClientSend("mouseMove," + x + "," + y);
                }
                break;
            }

            // 额外的手指抬起
            case MotionEvent.ACTION_POINTER_UP: {
                int pointerCount = event.getPointerCount();
                boolean isLeft = false;
                if (pointerCount == 2) {
                    int pointerIndex = event.getActionIndex();
                    isLeft = (event.getX(pointerIndex) - event.getX(pointerIndex == 0 ? 1 : 0)) < 0;
                }
                if(isLeft)
                {
                    leftMouseClickTime = System.currentTimeMillis();
                }else{
                    rightMouseClickTime = System.currentTimeMillis();
                }
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