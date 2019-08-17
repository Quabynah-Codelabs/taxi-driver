package com.innomalist.taxi.common.events;

import android.os.CountDownTimer;

import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.Debugger;

import org.greenrobot.eventbus.EventBus;

public class BaseRequestEvent {
    private BaseResultEvent resultEvent;
    CountDownTimer timer;

    public BaseRequestEvent() {
    }

    public BaseRequestEvent(BaseResultEvent _resultEvent) {
        resultEvent = _resultEvent;
        timer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Debugger.logMessage("Time Passing = 1");
            }

            @Override
            public void onFinish() {
                EventBus.getDefault().post(resultEvent);
            }
        }.start();
        CommonUtils.currentTimer = timer;
    }
}
