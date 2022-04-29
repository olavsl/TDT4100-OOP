package iqbattles;

import java.util.ArrayList;
import java.io.IOException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameSummaryController extends SceneController {
    
    private Player player;
    private ArrayList<Player> players = new ArrayList<>();
    private Game game;
    private PlayerDataUpdater playerDataUpdater = new PlayerDataUpdater();
    private Filehandler filehandler = new Filehandler();

    @FXML private Label numCorrectAnswers;
    @FXML private Label gameScore;
    @FXML private Label opponentScore;
    @FXML private Label victory;
    @FXML private Label newPlayerRating;
    @FXML private Label opponentRating;
    @FXML private Label addedRating;

    // Fill all Labels with information about the just-played game
    public void showSummary() throws IOException {
        numCorrectAnswers.setText(String.valueOf(this.game.getNumCorrectAnswers()));
        gameScore.setText(String.valueOf(this.game.gameScore()));
        opponentScore.setText(String.valueOf(this.game.getOpponentGameScore()));
        int newPlayerRatingNum = this.game.newPlayerRating(this.player.getRating(), this.game.getOpponentRating(), 
        this.game.victory(this.game.gameScore(), this.game.getOpponentGameScore()));
        String victoryString = this.game.victory(this.game.gameScore(), this.game.getOpponentGameScore());
        if (victoryString.equals("victory")) {
            victory.setText("Victory!");
            victory.setStyle("-fx-text-fill: green;");
            addedRating.setText("+" + (newPlayerRatingNum - this.player.getRating()));
        } else if (victoryString.equals("defeat")) {
            victory.setText("Defeat!");
            victory.setStyle("-fx-text-fill: red;");
            addedRating.setText(String.valueOf(newPlayerRatingNum - this.player.getRating()));
        } else if (victoryString.equals("draw")) {
            victory.setText("Draw!");
            victory.setStyle("-fx-text-fill: yellow");
            addedRating.setText("+0");
        }

        this.player.setRating(newPlayerRatingNum);
        this.player.addToGamesPlayed();

        opponentRating.setText(String.valueOf(this.game.getOpponentRating()));
        newPlayerRating.setText(String.valueOf(this.player.getRating()));

        this.players = playerDataUpdater.getPlayersFromFile("players.txt", this.player);
        filehandler.writePlayersToFile("players.txt", this.players);
    }

    @FXML
    private void switchToPlayerProfile(ActionEvent event) throws IOException {
        switchToPlayerProfile(event, this.player);
    }

    // Setters
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
