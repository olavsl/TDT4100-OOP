package test.java.iqbattles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.java.iqbattles.Player;

public class PlayerTest {

    private boolean checkPlayerState(Player player, String username, String hashedPassword, int rating, int ranking, int gamesPlayed) {
        return player.getUsername() == username && player.getHashedPassword() == hashedPassword && player.getRating() == rating && 
        player.getRanking() == ranking && player.getGamesPlayed() == gamesPlayed;
    }

    @Test
    @DisplayName("Constructor")
    public void testConstructor() {
        assertTrue(checkPlayerState(new Player("player", "xxxxxxxxxxxxxxxx", 100, 0, 0), 
        "player", "xxxxxxxxxxxxxxxx", 100, 0, 0));

        assertThrows(IllegalArgumentException.class, () -> {
            new Player("abc", "xxxxxxxxxxxxxxxx", 100, 0, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Player("abcdefghijklmnopq", "xxxxxxxxxxxxxxxx", 100, 0, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Player(".abc", "xxxxxxxxxxxxxxxx", 100, 0, 0);
        });
    }

    @Test
    @DisplayName("Add to number of games played")
    void testAddToGamesPlayed() {
        Player player = new Player("abcd", "xxxxxxxxxxxxxxxx", 100, 0 ,0);
        assertEquals(player.getGamesPlayed(), 0);
        player.addToGamesPlayed();
        assertEquals(player.getGamesPlayed(), 1);
    }
}
