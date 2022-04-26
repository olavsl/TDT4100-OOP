package iqbattles.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import iqbattles.LogInVerifier;
import iqbattles.Player;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class LogInController extends SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Label wrongLogInMessage;
    private LogInVerifier logInVerifier = new LogInVerifier();

    @FXML private TextField username; 
    @FXML private PasswordField password;

    // Method for logging in to user profile
    @FXML
    public void logIn(ActionEvent event) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        Player player = logInVerifier.LogIn(username, password, "players.txt");

        if (player != null) {
            switchToPlayerProfile(event, player);
        } else {
            wrongLogInMessage.setText("Username and password does not match.");
        }
    }

    @Override
    public void switchToPlayerProfile(ActionEvent event, Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/iqbattles/fxml/PlayerProfile.fxml"));
        root = loader.load();

        // Changing the data of the instance of PlayerProfileController belonging to UserProfile.fxml
        PlayerProfileController upc = loader.getController();
        upc.updateUserProfile(player);
        upc.setPlayer(player);

        // Changing the scene with current root to ensure the player data is transfered to the new scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
