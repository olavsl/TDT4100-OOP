package iqbattles;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer extends TimerTask {

    private int initialTime;
    private int time;

    private static Timer timer;

    public CountDownTimer(int time) {
        this.initialTime = time;
        this.time = time;
    };

    @Override
    public void run() {
        this.time--;

        if (this.time == 0) {
            timer.cancel();
        }
    }

    public void reset() {
        this.time = initialTime;
    }

    public int getTime() {
        return this.time;
    }

}
