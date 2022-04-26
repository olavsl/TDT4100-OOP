package iqbattles.controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import iqbattles.Player;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchScene(ActionEvent event, Stage stage, Scene scene, Parent root) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCreateUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../../resources/iqbattles/fxml/CreatePlayer.fxml"));
        switchScene(event, stage, scene, root);
    }

    public void switchToLogIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../../resources/iqbattles/fxml/LogIn.fxml"));
        switchScene(event, stage, scene, root);
    }

    public void switchToPlayerProfile(ActionEvent event, Player player) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../../resources/iqbattles/fxml/PlayerProfile.fxml"));
        switchScene(event, stage, scene, root);
    }

    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../../resources/iqbattles/fxml/StartPage.fxml"));
        switchScene(event, stage, scene, root);
    }

    public void switchToInGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../../resources/iqbattles/fxml/InGame.fxml"));
        switchScene(event, stage, scene, root);
    }

    public void switchToLeaderboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../../resources/iqbattles/fxml/Leaderboard.fxml"));
        switchScene(event, stage, scene, root);
    }

    public void switchToGameSummary(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../../resources/iqbattles/fxml/GameSummary.fxml"));
        switchScene(event, stage, scene, root);
    }
}
