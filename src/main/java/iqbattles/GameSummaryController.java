package iqbattles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.ArrayList;
import java.io.IOException;
import javafx.event.ActionEvent;

public class GameSummaryController extends SceneController {
    
    private Player player;
    private ArrayList<Player> players = new ArrayList<>();
    private Game game;
    private PlayerDataUpdater playerDataUpdater = new PlayerDataUpdater();
    private Filehandler filehandler = new Filehandler();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label numCorrectAnswers;
    @FXML private Label gameScore;
    @FXML private Label opponentScore;
    @FXML private Label victory;
    @FXML private Label newPlayerRating;
    @FXML private Label addedRating;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void showSummary() throws IOException {
        numCorrectAnswers.setText(String.valueOf(this.game.getNumCorrectAnswers()));
        gameScore.setText(String.valueOf(this.game.gameScore()));
        opponentScore.setText(String.valueOf(this.game.getOpponentGameScore()));
        if (this.game.victory(this.game.gameScore(), this.game.getOpponentGameScore()).equals("victory")) {
            victory.setText("Victory!");
            victory.setStyle("-fx-text-fill: green;");
        } else if (this.game.victory(this.game.gameScore(), this.game.getOpponentGameScore()).equals("defeat")) {
            victory.setText("Defeat!");
            victory.setStyle("-fx-text-fill: red;");
        } else if (this.game.victory(this.game.gameScore(), this.game.getOpponentGameScore()).equals("draw")) {
            victory.setText("Draw!");
            victory.setStyle("-fx-text-fill: yellow");
        }

        this.player.setRating(this.game.newPlayerRating(this.player.getRating(), this.game.getOpponentRating(), 
        this.game.victory(this.game.gameScore(), this.game.getOpponentGameScore())));

        newPlayerRating.setText(String.valueOf(this.player.getRating()));

        this.player.addToGamesPlayed();
        this.players = playerDataUpdater.getPlayersFromFile("players.txt", this.player);
        filehandler.writePlayersToFile("players.txt", this.players);
    }

    public void switchToPlayerProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/PlayerProfile.fxml"));
        root = loader.load();

        // Changing the data of the instance of UserProfileController belonging to UserProfile.fxml
        PlayerProfileController upc = loader.getController();
        upc.updateUserProfile(this.player);
        upc.setPlayer(this.player);

        // Changing the scene with current root to ensure the player data is transfered to the new scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
