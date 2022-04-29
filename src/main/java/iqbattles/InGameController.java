package iqbattles;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class InGameController extends SceneController {

    private Player player;
    private Tasks tasks;
    private Game game;
    private Filehandler filehandler = new Filehandler();
    private int gameLength = 5;
    private int currentTask = 0;

    private Parent root;

    @FXML private ImageView taskImage;

    // Takes in ImageView elements from fxml doc as an ArrayList. The ArrayList is created in the fxml file.
    @FXML private ArrayList<ImageView> ansImageList;

    // Initialize game with Player and Tasks, and update Scene
    public void initialize() throws IOException {

        // Creating new set of tasks
        this.tasks = new Tasks(this.gameLength);

        // Creating game object
        this.game = new Game(this.tasks);

        showTask(this.tasks.getTask(this.currentTask).getID());

        // Start timer
        this.game.startTimer();
    }

    // Update player object belonging to this instance
    public void setPlayer(Player player) {
        this.player = player;
    }

    // Display the task with the current index
    public void showTask(int id) throws IOException {
        Image imageTask = this.filehandler.getImage("tasks/task" + id + ".jpg");

        taskImage.setImage(imageTask);

        // Display images for each of the answer options in random order. Correct answers have id "1".
        ArrayList<Integer> randAnswerOptionOrder = this.game.randomizeCorrectAnswers();

        for (int i = 1; i < 7; i++) {
            Image imageAns = this.filehandler.getImage("answers/ans" + id + "-" + randAnswerOptionOrder.get(i - 1) + ".jpg");

            final int n = randAnswerOptionOrder.get(i - 1);

            ansImageList.get(i - 1).setImage(imageAns);

            // Get the players answer and show new task og summary
            ansImageList.get(i - 1).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setAnswer(n);
                    // Converting MouseEvent to ActionEvent, as showNewTask() only takes ActionEvent as parameter
                    ActionEvent actionEvent = new ActionEvent(event.getSource(), root);
                    try {
                        showNewTask(actionEvent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        this.currentTask++;
    }

    public void showNewTask(ActionEvent event) throws InterruptedException, IOException {
        // Check if the game should end and show Game Summary on next page instead
        if (this.currentTask == this.game.getGameLength()) {
            try {
                this.game.endGame();
                switchToGameSummary(event);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showTask(tasks.getTask(this.currentTask).getID());
        
        this.game.newTask();
    }

    // Show summary of the just-played match
    @FXML
    private void switchToGameSummary(ActionEvent event) throws IOException {
        this.game.setNumCorrectAnswers(this.game.getAnswers());
        switchToGameSummary(event, this.player, this.game);
    }

    // Setters
    public void setAnswer(int i) {
        this.game.setAnswer(i);
    }

    public InGameController getInGameController() {
        return this;
    }

    // Getters
    public Game getGame() {
        return this.game;
    }
    
}
