package iqbattles;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Game {
    
    private int opponentsRating;
    private Tasks tasks;
    private ArrayList<Integer> correctAnswers = new ArrayList<>();
    private ArrayList<Integer> answers = new ArrayList<>();
    private ArrayList<Integer> answerTimes = new ArrayList<>();
    private int numCorrectAnswers;
    private int gameScore;
    private int opponentsGameScore;
    private int gameLength;

    private CountDownTimer task = new CountDownTimer(60);
    private Timer timer = new Timer(false);

    public Game(Tasks tasks, int gameLength) {
        this.tasks = tasks;
        this.gameLength = gameLength;
    }

    public void startTimer() {
        this.timer.scheduleAtFixedRate(this.task, 0, 1000);
    }

    public void resetTimer() {
        this.task.reset();
    }

    public void stopTimer() {
        this.timer.cancel();
        Thread.currentThread().interrupt();
    }
    
    // Randomize order of correct answers
    public ArrayList<Integer> randomizeCorrectAnswers() {
        ArrayList<Integer> result = new ArrayList<>();
        
        this.correctAnswers.clear();

        Random rand = new Random();

        for (int i = 0; i < gameLength; i++) {
            int randTaskNum = rand.nextInt(gameLength);

            while(result.contains(randTaskNum)) {
                randTaskNum = rand.nextInt(gameLength);
            }

            result.add(randTaskNum);
        }
        
        return result;
    }

    public boolean checkAns(int correctAnswer, int answer) {
        if (correctAnswer == answer) {
            return true;
        }
        return false;
    }

    // CALCULATIONS

    // Returns a new random number chosen from a gaussian distribution
    public int randRating(int playerRating) {
        Random rand = new Random();
        int opponentsRating = (int) (rand.nextGaussian() * 10 + playerRating);
        this.opponentsRating = opponentsRating;
        return opponentsRating;
    }

    // Individual task score
    public double taskScore(int difficulty, int answerTime) {
        // Negative exponential to give players who answer fast a higher score
        double multiplier = -3.14 / (1 - 4.14 * Math.exp(0.02 * answerTime));

        return difficulty * multiplier;
    }

    // Total game score
    public int gameScore() {
        int gameScore = 0;

        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i) == 1) {
                gameScore += taskScore(this.tasks.getTask(i).getDifficulty(), this.answerTimes.get(i));
            }
        }

        this.gameScore = gameScore;
        return gameScore;
    };

    public int opponentGameScore(int opponentRating, Tasks tasks) {
        int opponentGameScore = 0;
        double probabilityOfBeingCorrect = 1 / (1 + Math.pow(10, (100 - opponentRating) / 40));
        int averageAnswerTime = (int) (60 - Math.round((1 / (1 + Math.pow(10, (100 - opponentRating) / 40))) * 60));

        Random rand = new Random();

        for (int i = 0; i < answers.size(); i++) {
            if ((double)rand.nextInt(100) < probabilityOfBeingCorrect * 100) {
                opponentGameScore += taskScore(this.tasks.getTask(i).getDifficulty(), averageAnswerTime);
            }
        }

        this.opponentsGameScore = opponentGameScore;
        return opponentGameScore;
    }

    public boolean victory(int playerScore, int opponentScore) {
        return playerScore > opponentScore;
    }

    // Update players rating using Elos formula
    public int newPlayerRating(int playerRating, int opponentRating, boolean victory) {
        int possibleGain = 6;
        int newPlayerRating;
        double expectedScore = 1 / (1 + Math.pow(10, (opponentRating - playerRating) / 40));

        if(victory) {
            newPlayerRating = (int) Math.round((playerRating + (possibleGain * (1 - expectedScore))));
        } else if (!victory) {
            newPlayerRating = (int) (playerRating + possibleGain * (0 - expectedScore));
        } else {
            newPlayerRating = (int) (playerRating);
        }

        // TODO: Fikse expected score (alltid 0.5)
        System.out.println("Expected score: " + expectedScore);
        System.out.println("Previous rating: " + playerRating);
        System.out.println("Gain: " + possibleGain * expectedScore);
        System.out.println("New rating: " + newPlayerRating);

        return newPlayerRating;
    }

    // Setters
    public void setNumCorrectAnswers(ArrayList<Integer> answers) {
        int result = 0;
        
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i) == 1) {
                result++;
            }
        }
        
        this.numCorrectAnswers = result;
    }

    public void setAnswer(int i) {
        this.answers.add(i);
    }

    public void setAnswerTime(int i) {
        this.answerTimes.add(60 - i);
    }

    // Getters
    public int getGameLength() {
        return this.gameLength;
    }

    public ArrayList<Integer> getAnswers() {
        return this.answers;
    }

    public int getNumCorrectAnswers() {
        return this.numCorrectAnswers;
    }

    public CountDownTimer getTimer() {
        return this.task;
    }

    public int getOpponentRating() {
        return this.opponentsRating;
    }

    public int getGameScore() {
        return this.gameScore;
    }

    public int getOpponentGameScore() {
        return this.opponentsGameScore;
    }

}
