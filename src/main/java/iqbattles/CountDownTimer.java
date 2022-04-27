package iqbattles;

import java.util.TimerTask;

public class CountDownTimer extends TimerTask {

    private int initialTime;
    private int time;

    public CountDownTimer(int time) {
        this.initialTime = time;
        this.time = time;
    };

    @Override
    public void run() {
        this.time--;
    }

    public void reset() {
        this.time = initialTime;
    }

    public int getTime() {
        return this.time;
    }

}
