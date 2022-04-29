package iqbattles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankSorter {

    public void rankSort(ArrayList<Player> players) {

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
