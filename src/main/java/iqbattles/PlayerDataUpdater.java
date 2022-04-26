package iqbattles;

import java.util.ArrayList;
import java.io.IOException;

public class PlayerDataUpdater {
    
    private Filehandler filehandler = new Filehandler();

    public ArrayList<Player> getPlayersFromFile(String fileName, Player player) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> playerInfo = this.filehandler.readFromFile(fileName);

        for (int i = 0; i < playerInfo.size(); i++) {
            String[] playerData = playerInfo.get(i).split(" ");

            if (playerData[0].equals(player.getUsername())) {
                players.add(player);
            } else {
                players.add(new Player(playerData[0], playerData[1], Integer.valueOf(playerData[2]), 
                Integer.valueOf(playerData[3]), Integer.valueOf(playerData[4])));
            }
        }

        return players;
    }

}
