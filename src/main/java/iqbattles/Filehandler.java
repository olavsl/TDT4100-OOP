package iqbattles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Filehandler implements Filehandling {

    public ArrayList<String> readFromFile(String fileName) throws FileNotFoundException, IOException {
        ArrayList<String> readLines = new ArrayList<>();
        File file = new File("src/main/resources/iqbattles/" + fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String tempLine;

        while ((tempLine = reader.readLine()) != null) {
            readLines.add(tempLine);
        }

        reader.close();
        return readLines;
    }

    public void writeToFile(String fileName, ArrayList<String> lines) {
        try {
            FileWriter fw = new FileWriter("src/main/resources/iqbattles/" + fileName, true);
            BufferedWriter writer = new BufferedWriter(fw);

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile(String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/main/resources/iqbattles/" + fileName);
        pw.print("");
        pw.close();
    }

    public Image getImage(String fileName) throws IOException {
        return new Image(getClass().getResource(fileName).toString());
    }

    public ArrayList<Player> getPlayersFromFile(String fileName) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> playerInfo = readFromFile(fileName);

        for (int i = 0; i < playerInfo.size(); i++) {
            String[] playerData = playerInfo.get(i).split(" ");

            players.add(new Player(playerData[0], playerData[1], Integer.valueOf(playerData[2]), 
            Integer.valueOf(playerData[3]), Integer.valueOf(playerData[4])));
        }

        return players;
    }

    public void writePlayersToFile(String fileName, ArrayList<Player> players) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        for (Player player : players) {
            String tempLine = player.getUsername() + " " + player.getHashedPassword() + " " + player.getRating() 
            + " " + player.getRanking() + " " + player.getGamesPlayed();
            lines.add(tempLine);
        }

        clearFile(fileName);
        writeToFile(fileName, lines);
    }

}
