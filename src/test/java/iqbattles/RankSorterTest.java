package iqbattles;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class RankSorterTest {
    
    private RankSorter rankSorter = new RankSorter();
    private Filehandler filehandler = new Filehandler();

    @Test
    void testRankSort() {
        
        ArrayList<Player> players = new ArrayList<>();

        Player p1 = new Player("player1", "xxxx", 100, 0, 0);
        Player p2 = new Player("player2", "xxxx", 101, 0, 0);
        Player p3 = new Player("player3", "xxxx", 102, 0, 0);
        
        players.add(p1);
        players.add(p2);
        players.add(p3);

        ArrayList<Player> preSortedPlayers = new ArrayList<>();

        p3.setRanking(1);
        p2.setRanking(2);
        p1.setRanking(3);

        preSortedPlayers.add(p3);
        preSortedPlayers.add(p2);
        preSortedPlayers.add(p1);

        for (int i = 0; i < players.size(); i++) {
            int count = 0;

            if (players.get(i).getRating() == preSortedPlayers.get(i).getRating()) {
                count++;
            }

            assertTrue(count != players.size());
        }
 
        try {
            filehandler.writePlayersToFile("playersTest.txt", players);

            players = rankSorter.rankSort("playersTest.txt");
        } catch (Exception e) {
            fail();
        }

        for (int i = 0; i < players.size(); i++) {
            assertTrue(players.get(i).toString().equals(preSortedPlayers.get(i).toString()));
        }

        try {
            filehandler.clearFile("playersTest.txt");
        } catch (FileNotFoundException e) {}

    }
}
