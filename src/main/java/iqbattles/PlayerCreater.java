package iqbattles;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class PlayerCreater {
    
    private ArrayList<String> players = new ArrayList<>();
    private Filehandler filehandler = new Filehandler();

    public String createPlayer(String username, String password, String confirmedPassword, String fileName) throws NoSuchAlgorithmException, IOException {

        // Check that the chosen password matches confirmation-password
        if (!password.equals(confirmedPassword)) {
            return "passwordsNotMatching";
        }

        // Getting taken usernames with filehandler
        this.players = getTakenNamesFromFile(fileName);

        // Check if username is already taken
        for (String player : this.players) {
            if (player.matches(username)) {
                return "takenUsername";
            }
        }
        this.players.add(username);

        // Encrypting given password with "Secure Hashing Algorithm-256"
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Getting the bytes of the written password to input to the hashing function. Then convert the returned byte array to a hex string
        String hashedPassword = ToHexString.toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));

        Player player = new Player(username, hashedPassword, 100, 0, 0);

        addNewPlayerToFile(player, fileName);

        return "createdPlayer";
    }

    public ArrayList<String> getTakenNamesFromFile(String fileName) throws IOException {
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> playerInfo = this.filehandler.readFromFile(fileName);

        for (int i = 0; i < playerInfo.size(); i++) {
            String[] playerData = playerInfo.get(i).split(" ");

            usernames.add(playerData[0]);
        }

        return usernames;
    }

    public void addNewPlayerToFile(Player player, String fileName) {
        // Writing username to file using filehandler
        String playerInfo = player.getUsername() + " " + player.getHashedPassword() + " " + player.getRating() + " 0 0";
        ArrayList<String> input = new ArrayList<>(Arrays.asList(playerInfo));
        filehandler.writeToFile(fileName, input);
    }

}
