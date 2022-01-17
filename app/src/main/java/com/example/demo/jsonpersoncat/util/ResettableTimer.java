package com.example.demo.jsonpersoncat.util;

import java.util.Timer;
import java.util.TimerTask;

public class ResettableTimer extends Timer {
    private final Runnable  task;
    private TimerTask timerTask;

    public ResettableTimer(Runnable task) {
        this.task = task;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        };
    }

    public void schedule(long delay) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        };

        this.schedule(timerTask, delay);
    }

    public void reschedule(long delay) {
        if (timerTask != null) {
            timerTask.cancel();
        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        };

        this.schedule(timerTask, delay);
    }
}
