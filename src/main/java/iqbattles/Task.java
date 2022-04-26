package iqbattles;

public class Task {
    
    private int id;
    private int difficulty;
    private int score;

    public Task(int id, int difficulty, int score) {
        this.id = id;

        if (id < 0) {
            throw new IllegalArgumentException();
        }

        if (difficulty <= 0 || difficulty > 100) {
            throw new IllegalArgumentException();
        }

        if (score <= 0 || score > 100) {
            throw new IllegalArgumentException();
        }

        this.difficulty = difficulty;
        this.score = score;
    }

    // Getters
    public int getID() {
        return this.id;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public int getScore() {
        return this.score;
    }
}
