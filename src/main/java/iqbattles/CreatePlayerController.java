package iqbattles;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CreatePlayerController extends SceneController {

    private PlayerCreater playerCreater = new PlayerCreater();

    @FXML private TextField username, password, confirmedPassword;
    @FXML private Label output;

    // Method for creating a user profile
    @FXML
    public void createPlayer() throws NoSuchAlgorithmException, IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        String confirmedPassword = this.confirmedPassword.getText();

        if (playerCreater.createPlayer(username, password, confirmedPassword)) {
            output.setText("A user with the name \"" + username + "\" was created!");
            output.setStyle("-fx-text-fill: green;");
        } else {
            output.setText("That username is already taken!");
            output.setStyle("-fx-text-fill: red;");
        }
    }
}