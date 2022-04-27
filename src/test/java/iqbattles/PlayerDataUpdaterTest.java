package iqbattles;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerDataUpdaterTest {

    @Test
    @DisplayName("Check that it returns an ArrayList of Strings with the current players new statistics")
    void testGetPlayersFromFile() {
        final PlayerDataUpdater pdu = new PlayerDataUpdater();
        Player player = new Player("abcd", "xxxxxxxxxxxxxxxx", 103, 0, 1);
        ArrayList<Player> players = new ArrayList<>();

        try {
            assertEquals(pdu.getPlayersFromFile("playerTest.txt", player), players);
        } catch (IOException e) {
            fail();
        }

        players.add(player);

        try {
            assertEquals(pdu.getPlayersFromFile("playerTest.txt", player), players);
        } catch (IOException e) {
            fail();
        }
    }

}
