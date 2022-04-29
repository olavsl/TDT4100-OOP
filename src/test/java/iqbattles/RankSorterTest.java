package iqbattles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class RankSorterTest {
    
    private RankSorter lbu = new RankSorter();

    @Test
    void testRankSort() {
        
        ArrayList<Player> players = new ArrayList<>();

        assertEquals(players, new ArrayList<>());

        Player p1 = new Player("player1", "xxxx", 100, 0, 0);
        Player p2 = new Player("player2", "xxxx", 101, 0, 0);
        
        players.add(p1);
        players.add(p2);

        ArrayList<Player> preSortedPlayers = new ArrayList<>();

        preSortedPlayers.add(p2);
        preSortedPlayers.add(p1);

        assertNotEquals(preSortedPlayers, players);

        lbu.rankSort(players);

        assertEquals(preSortedPlayers, players);

    }
}
