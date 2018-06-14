package com.hieupham.absolutecleanarchitecture.utils.common;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

/**
 * Created by hieupham on 6/5/18.
 */

public class IntervalScheduler {

    private long delay;
    private long period;
    private Timer timer;
    private List<SchedulerListener> schedulerListeners = new ArrayList<>();
    private boolean isCancelled;
    private boolean triggerOnMain;

    public interface SchedulerListener {
        void onSchedule();
    }

    @Inject
    public IntervalScheduler() {
    }

    public IntervalScheduler triggerOnMain() {
        triggerOnMain = true;
        return this;
    }

    public IntervalScheduler delay(Duration duration) {
        this.delay = duration.duration();
        return this;
    }

    public IntervalScheduler period(Duration duration) {
        this.period = duration.duration();
        return this;
    }

    public Duration period() {
        return Duration.from(period);
    }

    public void schedule() {
        cancel();
        timer = new Timer();
        isCancelled = false;
        if (period > 0) {
            timer.schedule(task(), delay, period);
        } else {
            timer.schedule(task(), delay);
        }
    }

    public void cancel() {
        if (timer == null || isCancelled) return;
        timer.cancel();
        timer = null;
        isCancelled = true;
    }

    public void observe(SchedulerListener listener) {
        if (!schedulerListeners.contains(listener)) {
            schedulerListeners.add(listener);
        }
    }

    public void unObserve(SchedulerListener listener) {
        schedulerListeners.remove(listener);
        if (schedulerListeners.isEmpty() && timer != null) {
            cancel();
        }
    }

    private void onSchedule() {
        for (SchedulerListener listener : schedulerListeners) {
            listener.onSchedule();
        }
    }

    private TimerTask task() {
        return new TimerTask() {
            @Override
            public void run() {
                if (triggerOnMain) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            onSchedule();
                        }
                    });
                } else {
                    onSchedule();
                }
            }
        };
    }
}
