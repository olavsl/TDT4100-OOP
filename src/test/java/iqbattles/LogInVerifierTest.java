package iqbattles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogInVerifierTest {
    
    @Test
    @DisplayName("Test log in function")
    void testLogIn() {
        LogInVerifier logInVerifier = new LogInVerifier();
        Filehandler filehandler = new Filehandler();

        String username = "abcd";
        String password = "abcd";

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            String hashedPassword = ToHexString.toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));

            Player player = new Player(username, hashedPassword, 100, 0, 0);

            String playerInfo = player.getUsername() + " " + player.getHashedPassword() + " " + player.getRating() + " 0 0";

            ArrayList<String> input = new ArrayList<>(Arrays.asList(playerInfo));
            filehandler.writeToFile("playersTest.txt", input);

            assertEquals(player.toString(), logInVerifier.LogIn(username, password, "playersTest.txt").toString());

            filehandler.clearFile("playersTest.txt");
        } catch (Exception e) {}
    }
}
