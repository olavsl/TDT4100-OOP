package iqbattles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LogInController extends SceneController {
    
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
    
}
