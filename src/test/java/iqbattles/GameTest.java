package iqbattles;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {
    
    private boolean checkGameState(Game game, Tasks tasks, int gameLength) {
        return game.getGameLength() == gameLength;
    }

    @Test
    @DisplayName("Constructor")
    public void testConstructor() {
        Tasks tasks;
        try {
            tasks = new Tasks(5);
        } catch (IOException e) {
            return;
        }
        Game game = new Game(tasks);

        assertTrue(checkGameState(game, tasks, 5));
    }

    @Test
    @DisplayName("Test randomizing order of answer options")
    public void testRandomizingOrderOfAnswerOptions() {
        try {
            Game game = new Game(new Tasks(5));

            ArrayList<Integer> randIntegers = game.randomizeCorrectAnswers();

            for (int i = 0; i < 6; i++) {
                int count = 0;

                for (int j = 0; i < 6; i++) {
                    if (randIntegers.get(i) == randIntegers.get(j)) {
                        count++;
                    }
                }

                if (count != 1) {
                    fail("Repeating or non-existing answer options!");
                }
            }

        } catch (IOException e) {}
    }

    @Test
    @DisplayName("Test checking if answer is correct")
    public void testCheckAns() {
        try {
            Game game = new Game(new Tasks(5));

            assertTrue(game.checkAns(1, 1));
            assertFalse(game.checkAns(0, 1));

        } catch (IOException e) {}
    }

    @Test
    @DisplayName("Test incapsulation of random opponent rating")
    public void testAllowedRating() {
        try {
            Game game = new Game(new Tasks(5));

            assertTrue(game.allowedRating(100, 100));
            assertFalse(game.allowedRating(0, -1));
            assertFalse(game.allowedRating(200, 201));
            assertFalse(game.allowedRating(0, 51));

        } catch (IOException e) {}
    }

    @Test
    @DisplayName("Test calculation of task score")
    public void testTaskScore() {
        try {
            Game game = new Game(new Tasks(5));

            assertEquals(0, game.taskScore(0, 1));

            double expectedTaskScore = 25;

            assertEquals(Math.round(game.taskScore(100, 60)), expectedTaskScore);

            assertThrows(IllegalArgumentException.class, () -> {
                game.taskScore(1, -1);
            });
        } catch (IOException e) {}
    }

    @Test
    @DisplayName("Test calculation of game score for player")
    public void testGameScores() {
        try {
            Task task0 = new Task(0, 100, 100);
            Task task1 = new Task(1, 100, 100);
            Task task2 = new Task(2, 100, 100);
            Task task3 = new Task(3, 100, 100);
            Task task4 = new Task(4, 100, 100);

            Tasks tasks = new Tasks(5);

            tasks.setTask(0, task0);
            tasks.setTask(1, task1);
            tasks.setTask(2, task2);
            tasks.setTask(3, task3);
            tasks.setTask(4, task4);

            Game game = new Game(new Tasks(5));

            game.setTasks(tasks);

            game.setAnswer(1);
            game.setAnswer(1);
            game.setAnswer(1);
            game.setAnswer(1);

            game.setAnswerTime(0);
            game.setAnswerTime(0);
            game.setAnswerTime(0);
            game.setAnswerTime(0);

            assertEquals(100, game.gameScore());

        } catch (IOException e) {}
    }

    @Test
    @DisplayName("Test possible end game states")
    public void testVictory() {
        try {
            Game game = new Game(new Tasks(5));

            assertEquals(game.victory(0, 0), "draw");
            assertEquals(game.victory(0, 1), "defeat");
            assertEquals(game.victory(1, 0), "victory");

        } catch (IOException e) {}
    }

    @Test
    @DisplayName("Test calculation of new player rating")
    public void testPlayerRating() {
        try {
            Game game = new Game(new Tasks(5));

            assertEquals(100, game.newPlayerRating(100, 100, "draw"));
            assertEquals(103, game.newPlayerRating(100, 100, "victory"));
            assertEquals(97, game.newPlayerRating(100, 100, "defeat"));
            assertEquals(150, game.newPlayerRating(150, 100, "victory"));
            assertEquals(144, game.newPlayerRating(150, 100, "defeat"));
            assertEquals(106, game.newPlayerRating(100, 150, "victory"));
            assertEquals(99, game.newPlayerRating(100, 150, "defeat"));

        } catch (Exception e) {}
    }

}
