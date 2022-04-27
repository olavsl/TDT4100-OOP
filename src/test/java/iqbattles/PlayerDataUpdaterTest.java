package iqbattles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerDataUpdaterTest {

    @Test
    @DisplayName("Check that it returns an ArrayList of Player-s with the current players new statistics")
    void testGetPlayersFromFile() {
        PlayerDataUpdater pdu = new PlayerDataUpdater();
        Player player = new Player("abcd", "xxxx", 100, 0, 1);
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> checkPlayers = new ArrayList<>();

        Filehandler filehandler = new Filehandler();

        try {
            players = pdu.getPlayersFromFile("playersTest.txt", null);

            assertTrue(checkPlayers.equals(players));
        } catch (IOException e) {
            fail();
        }

        checkPlayers.add(player);

        try {
            filehandler.writePlayersToFile("playersTest.txt", checkPlayers);
        } catch (FileNotFoundException e) {}

        try {
            players = pdu.getPlayersFromFile("playersTest.txt", player);

            assertTrue(checkPlayers.equals(players));
        } catch (IOException e) {
            fail();
        }

        try {
            filehandler.clearFile("playersTest.txt");
        } catch (FileNotFoundException e) {}

        checkPlayers.add(new Player("efgh", "xxxx", 101, 0, 1));

        try {
            filehandler.writePlayersToFile("playersTest.txt", checkPlayers);
        } catch (FileNotFoundException e) {}

        try {
            players = pdu.getPlayersFromFile("playersTest.txt", player);

            ArrayList<String> checkPlayersData = new ArrayList<>();
            ArrayList<String> playersData = new ArrayList<>();
            Player tempPlayer;

            for (int i = 0; i < 2; i++) {
                tempPlayer = checkPlayers.get(i);
                checkPlayersData.add(tempPlayer.getUsername() + tempPlayer.getHashedPassword() + tempPlayer.getRating() + tempPlayer.getRanking() + tempPlayer.getGamesPlayed());
                tempPlayer = players.get(i);
                playersData.add(tempPlayer.getUsername() + tempPlayer.getHashedPassword() + tempPlayer.getRating() + tempPlayer.getRanking() + tempPlayer.getGamesPlayed());
            }

            assertTrue(checkPlayersData.equals(playersData));
        } catch (IOException e) {
            fail();
        }

        try {
            filehandler.clearFile("playersTest.txt");
        } catch (FileNotFoundException e) {}
    }

}
