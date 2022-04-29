package iqbattles;

import java.io.IOException;
import java.util.TimerTask;

public class CountDownTimer extends TimerTask {

    private int initialTime;
    private int time;
    private InGameController igc;
    private int taskCount = 0;

    public CountDownTimer(int time) {
        this.initialTime = time;
        this.time = time;
    };

    @Override
    public void run() {
        this.time--;

        if (Thread.currentThread().getName().equals("Timer-0")) {
            this.cancel();
        }

        if (this.time == 0 && this.igc.getGame().getAnswers().size() == this.taskCount) {
            if (this.igc.getGame().getAnswers().size() == 3) {
                this.cancel();
                this.igc.showEndGameBtn();
            } else {
                try {
                    this.igc.setAnswer(0);
                    this.igc.showNewTask(null);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void reset() {
        this.time = initialTime;
    }

    public int getTime() {
        return this.time;
    }

    public void setInGameController(InGameController igc) {
        this.igc = igc;
    }

    public void addToTaskCount() {
        this.taskCount++;
    }

}
