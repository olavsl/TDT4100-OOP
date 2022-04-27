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

    public String createPlayer(String username, String password, String confirmedPassword) throws NoSuchAlgorithmException, IOException {

        // Check that the chosen password matches confirmation-password
        if (!password.equals(confirmedPassword)) {
            return "passwordsNotMatching";
        }

        // Getting taken usernames with filehandler
        this.players = getTakenNamesFromFile("players.txt");

        // Check if username is already taken
        for (String player : this.players) {
            if (player.matches(username)) {
                return "takenUsername";
            }
        }
        this.players.add(username);

        // CREATE INCAPSULATION OFF PASSWORD
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String hashedPassword = ToHexString.toHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));

        Player player = new Player(username, hashedPassword, 100, 0, 0);

        addNewPlayerToFile(player);

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

    public void addNewPlayerToFile(Player player) {
        // Writing username to file using filehandler
        String playerInfo = player.getUsername() + " " + player.getHashedPassword() + " " + player.getRating() + " 0 0";
        ArrayList<String> input = new ArrayList<>(Arrays.asList(playerInfo));
        filehandler.writeToFile("players.txt", input);
    }

}
