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

        // Get the "Secure Hashing Algorithm-256" as the hashing function
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Getting the bytes of the written password to input to the hashing function. Then convert the returned byte array to a hex string
        String hashedPassword = ToHexString.toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));

        // Loops through all Player-objects from the database, and checks if both username and hashed password matches any of those Player-objects
        for (Player playerX : players) {
            if (username.equals(playerX.getUsername()) && hashedPassword.equals(playerX.getHashedPassword())) {
                return playerX;
            }
        }

        return null;
    }

}
