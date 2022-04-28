package iqbattles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    private void switchScene(ActionEvent event, Stage stage, Scene scene, Parent root) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToCreateUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/CreatePlayer.fxml"));
        switchScene(event, stage, scene, root);
    }

    @FXML
    private void switchToLogIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        switchScene(event, stage, scene, root);
    }

    @FXML
    protected void switchToPlayerProfile(ActionEvent event, Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/PlayerProfile.fxml"));
        root = loader.load();

        PlayerProfileController upc = loader.getController();
        upc.updateUserProfile(player);
        upc.setPlayer(player);

        switchScene(event, stage, scene, root);
    }

    @FXML
    private void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/StartPage.fxml"));
        switchScene(event, stage, scene, root);
    }

    @FXML
    protected void switchToInGame(ActionEvent event, Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/InGame.fxml"));
        root = loader.load();

        InGameController igc = loader.getController();
        igc.setPlayer(player);
        igc.initialize();
        igc.getGame().randRating(player.getRating());

        switchScene(event, stage, scene, root);
    }

    @FXML
    protected void switchToLeaderboard(ActionEvent event, Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Leaderboard.fxml"));
        root = loader.load();

        LeaderboardController lbc = loader.getController();
        lbc.setPlayer(player);
        lbc.displayLeaderboard();

        switchScene(event, stage, scene, root);
    }

    @FXML
    protected void switchToGameSummary(ActionEvent event, Player player, Game game) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/GameSummary.fxml"));
        root = loader.load();

        GameSummaryController gsc = loader.getController();
        gsc.setPlayer(player);
        gsc.setGame(game);
        gsc.showSummary();

        switchScene(event, stage, scene, root);
    }
    
}
