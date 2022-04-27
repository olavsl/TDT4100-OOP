package iqbattles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerCreaterTest {
    
    private PlayerCreater playerCreater = new PlayerCreater();
    private Filehandler filehandler = new Filehandler();

    @Test
    @DisplayName("Testing creation of new player")
    public void testCreatePlayer() {
        try {
            assertEquals("createdPlayer", playerCreater.createPlayer("abcd", "abcd", "abcd", "playersTest.txt"));
            assertEquals("takenUsername", playerCreater.createPlayer("abcd", "abcd", "abcd", "playersTest.txt"));
            assertEquals("passwordsNotMatching", playerCreater.createPlayer("efgh", "abcd", "efgh", "playersTest.txt"));

            filehandler.clearFile("playersTest.txt");
        } catch (Exception e) {}
    }

    @Test
    @DisplayName("Test getting taken usernames from file")
    public void testGetTakenNamesFromFile() {
        try {
            ArrayList<String> usernames = new ArrayList<>();
            usernames.addAll(Arrays.asList("abcd", "efgh", "ijkl"));
            
            playerCreater.createPlayer("abcd", "abcd", "abcd", "playersTest.txt");
            playerCreater.createPlayer("efgh", "efgh", "efgh", "playersTest.txt");
            playerCreater.createPlayer("ijkl", "ijkl", "ijkl", "playersTest.txt");

            ArrayList<String> gottenUsernames = playerCreater.getTakenNamesFromFile("playersTest.txt");
            filehandler.clearFile("playersTest.txt");

            assertEquals(usernames, gottenUsernames);

        } catch (Exception e) {}
    }

    @Test
    @DisplayName("Test adding new player to file")
    public void testAddNewPlayerToFile() {
        try {
            Player player = new Player("abcd", "abcd", 100, 0, 0);
            String playerInfo = player.toString();

            playerCreater.addNewPlayerToFile(player, "playersTest.txt");

            ArrayList<String> players = filehandler.readFromFile("playersTest.txt");

            assertEquals(playerInfo, players.get(0));

            filehandler.clearFile("playersTest.txt");
        } catch (Exception e) {}
    }

}
