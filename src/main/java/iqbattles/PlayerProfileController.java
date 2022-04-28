package iqbattles;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class PlayerProfileController extends SceneController {
    
    private Player player;

    @FXML
    private Label displayUsername, displayRating, displayRanking, displayGamesPlayed;

    /**
     * Display player info on user profile
     * 
     * @param player
     */
    public void updateUserProfile(Player player) {
        displayUsername.setText(player.getUsername());
        displayRating.setText(Integer.toString(player.getRating()));
        displayRanking.setText(Integer.toString(player.getRanking()));
        displayGamesPlayed.setText(Integer.toString(player.getGamesPlayed()));
    }

    // Start the game
    @FXML
    private void startGame(ActionEvent event) throws IOException {
        switchToInGame(event, this.player);
    }

    @FXML
    private void switchToLeaderboard(ActionEvent event) throws IOException {
        switchToLeaderboard(event, this.player);
    }

    // Setter
    public void setPlayer(Player player) {
        this.player = new Player(player.getUsername(), player.getHashedPassword(), 
        player.getRating(), player.getRanking(), player.getGamesPlayed());
    }
    
}
