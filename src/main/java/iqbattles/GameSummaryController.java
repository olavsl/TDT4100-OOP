package iqbattles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.io.IOException;
import javafx.event.ActionEvent;

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

    @FXML
    private void switchToPlayerProfile(ActionEvent event) throws IOException {
        switchToPlayerProfile(event, this.player);
    }

}
