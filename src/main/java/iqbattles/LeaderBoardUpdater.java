package iqbattles;

import java.util.Collections;
import java.util.Comparator;

import javafx.collections.ObservableList;



public class LeaderBoardUpdater {

    public void rankSort(ObservableList<Player> players) {
        // players.sort((x, y) -> Integer.compare(y.getRating(), x.getRating()));

        Collections.sort(players, new Comparator<Player>() {
            public int compare(Player player1, Player player2) {
                return player2.getRating() - player1.getRating();
            }
        });

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setRanking(i + 1);
        }
    }

}
