package iqbattles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankSorter {

    private Filehandler filehandler = new Filehandler();

    public ArrayList<Player> rankSort(String fileName) throws IOException {
        ArrayList<Player> sortedPlayers = new ArrayList<>();

        sortedPlayers = filehandler.getPlayersFromFile(fileName);

        // Using Comparator interface to sort Player-object by their rating...
        Collections.sort(sortedPlayers, new Comparator<Player>() {
            public int compare(Player player1, Player player2) {
                return player2.getRating() - player1.getRating();
            }
        });

        // ... then updating their rank based on their index in the new sorted ArrayList of Player-s
        for (int i = 0; i < sortedPlayers.size(); i++) {
            sortedPlayers.get(i).setRanking(i + 1);
        }

        filehandler.writePlayersToFile(fileName, sortedPlayers);

        return sortedPlayers;
    }

}
