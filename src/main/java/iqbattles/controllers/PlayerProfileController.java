package iqbattles.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import iqbattles.Player;

public class PlayerProfileController extends SceneController {
    
    private Player player;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label displayUsername, displayRating, displayRanking, displayGamesPlayed;

    // Display player info on user profile
    public void updateUserProfile(Player player) {
        displayUsername.setText(player.getUsername());
        displayRating.setText(Integer.toString(player.getRating()));
        displayRanking.setText(Integer.toString(player.getRanking()));
        displayGamesPlayed.setText(Integer.toString(player.getGamesPlayed()));
    }

    // Update player object belonging to this instance
    public void setPlayer(Player player) {
        this.player = new Player(player.getUsername(), player.getHashedPassword(), 
        player.getRating(), player.getRanking(), player.getGamesPlayed());
    }

    // Start the game
    public void startGame(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/iqbattles/fxml/InGame.fxml"));
        root = loader.load();

        // Changing the data of the instance of InGameController belonging to InGame.fxml
        InGameController igc = loader.getController();
        igc.setPlayer(this.player);
        igc.initialize();
        igc.getGame().randRating(this.player.getRating());

        // Changing the scene with current root to ensure the player data is transfered to the new scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLeaderboard(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/iqbattles/fxml/Leaderboard.fxml"));
        root = loader.load();

        // Changing the data of the instance of GameSummaryController belonging to UserProfile.fxml
        LeaderboardController lbc = loader.getController();
        lbc.setPlayer(this.player);
        lbc.displayLeaderboard();

        // Changing the scene with current root to ensure the player data is transfered to the new scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
