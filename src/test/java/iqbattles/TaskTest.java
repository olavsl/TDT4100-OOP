package iqbattles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTest {

    private boolean checkTaskState(Task task, int id, int difficulty, int score) {
        return task.getID() == id && task.getDifficulty() == difficulty && task.getScore() == score;
    }

    @Test
    @DisplayName("Constructor")
    public void testConstructor() {
        assertTrue(checkTaskState(new Task(0, 1, 15), 0, 1, 15));

        assertThrows(IllegalArgumentException.class, () -> {
            new Task(-1, 1, 15);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task(0, 0, 15);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task(0, 101, 15);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task(0, 1, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task(0, 1, 101);
        });
    }

}
