package iqbattles;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class InGameController {

    private Player player;
    private Tasks tasks;
    private Game game;
    private Filehandler filehandler = new Filehandler();
    private int gameLength = 5;
    private int currentTask = 0;

    private Stage stage;
    private Scene scene;
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
        // Get image
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
                    } catch (FileNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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
                this.game.setAnswerTime(this.game.getTimer().getTime());
                this.game.stopTimer();
                this.game.gameScore();
                this.game.opponentGameScore(this.game.getOpponentRating(), this.tasks);
                switchToGameSummary(event);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showTask(tasks.getTask(this.currentTask).getID());
        
        this.game.setAnswerTime(this.game.getTimer().getTime());
        this.game.resetTimer();
    }

    // Show summary of the just-played match
    public void switchToGameSummary(ActionEvent event) throws IOException {

        this.game.setNumCorrectAnswers(this.game.getAnswers());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/GameSummary.fxml"));
        root = loader.load();

        // Changing the data of the instance of GameSummaryController belonging to UserProfile.fxml
        GameSummaryController gsc = loader.getController();
        gsc.setPlayer(this.player);
        gsc.setGame(this.game);
        gsc.showSummary();

        // Changing the scene with current root to ensure the player data is transfered to the new scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
