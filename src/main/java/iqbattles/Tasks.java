package iqbattles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tasks {
    
    private ArrayList<Task> tasks = new ArrayList<>();
    private int upperBound = 5;

    public Tasks(int n) throws IOException {

        ArrayList<Integer> taskIDs = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int randNum = rand.nextInt(this.upperBound);

            while (taskIDs.contains(randNum)) {
                randNum = rand.nextInt(this.upperBound);
            }

            taskIDs.add(randNum);

            try {
                // Get task data from file "tasks.txt"
                File taskFile = new File("src/main/resources/iqbattles/tasks.txt");
                Scanner reader = new Scanner(taskFile);
                for (int j = 0; j < randNum; j++) {
                    reader.nextLine();
                }
                String strTaskData = reader.nextLine();
                String[] taskData = strTaskData.split(" ", 4);
                reader.close();

                // Create new Task object
                int taskID = Integer.parseInt(taskData[0]);
                int difficulty = Integer.parseInt(taskData[1]);
                int expectedSolveTime = Integer.parseInt(taskData[2]);

                Task newTask = new Task(taskID, difficulty, expectedSolveTime);

                this.tasks.add(newTask);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Setter
    public void setTask(int n, Task task) {
        this.tasks.set(n, task);
    }

    // Getters
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int n) {
        return this.tasks.get(n);
    }
}
