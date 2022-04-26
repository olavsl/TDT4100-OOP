package iqbattles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogInVerifier {
    
    private Filehandler filehandler = new Filehandler();

    public Player LogIn(String username, String password, String fileName) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        ArrayList<Player> players = this.filehandler.getPlayersFromFile(fileName);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String hashedPassword = ToHexString.toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));

        for (Player playerX : players) {
            if (username.equals(playerX.getUsername()) && hashedPassword.equals(playerX.getHashedPassword())) {
                return playerX;
            }
        }

        return null;
    }

}
