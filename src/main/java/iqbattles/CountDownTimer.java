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

    // Clock
    @Override
    public void run() {
        this.time--;

        // Stops main-thread from interfering with the conditional functions of the parallell Thread
        if (Thread.currentThread().getName().equals("Timer-0")) {
            this.cancel();
        }

        // Checks if the player didn't answer in 60 seconds, and if so moves on to the next task 
        if (this.time == 0 && this.igc.getGame().getAnswers().size() == this.taskCount) {
            // Stops thread
            if (this.igc.getGame().getAnswers().size() == 3) {
                this.cancel();
                this.igc.showEndGameBtn();
            } else {
                try {
                    // Show new task and set empty answer
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
