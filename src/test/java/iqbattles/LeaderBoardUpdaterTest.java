package iqbattles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeaderBoardUpdaterTest {
    
    private LeaderBoardUpdater lbu = new LeaderBoardUpdater();

    @Test
    void testRankSort() {
        
        ObservableList<Player> players = FXCollections.observableArrayList();

        assertEquals(players, FXCollections.observableArrayList());

        Player p1 = new Player("player1", "xxxx", 100, 0, 0);
        Player p2 = new Player("player2", "xxxx", 101, 0, 0);
        
        players.add(p1);
        players.add(p2);

        ObservableList<Player> preSortedPlayers = FXCollections.observableArrayList();

        preSortedPlayers.add(p2);
        preSortedPlayers.add(p1);

        assertNotEquals(players, preSortedPlayers);

        lbu.rankSort(players);

        assertEquals(players, preSortedPlayers);

    }
}
