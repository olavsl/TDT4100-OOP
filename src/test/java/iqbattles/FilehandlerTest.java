package iqbattles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FilehandlerTest {

    private Filehandler filehandler = new Filehandler();

    @Test
    @DisplayName("Test reading from non-existing file")
    public void testReadFromNonExistingFile() {
        assertThrows(FileNotFoundException.class, () -> {
            this.filehandler.readFromFile("foo");
        });
    }

    @Test
    @DisplayName("Test writing to, reading from and clearing file")
    public void testConstructor() {
        ArrayList<String> lines = new ArrayList<>();

        try {
            assertEquals(this.filehandler.readFromFile("playersTest.txt"), lines);;
        } catch (Exception e) {
            fail("Could not find file");
        }

        lines.add("abcdef");
        lines.add("ghijkl");

        this.filehandler.writeToFile("playersTest.txt", lines);

        try {
            assertEquals(this.filehandler.readFromFile("playersTest.txt"), lines);
        } catch (Exception e) {
            fail("Could not find file");
        }

        lines.clear();

        try {
            this.filehandler.clearFile("playersTest.txt");
            assertEquals(lines, this.filehandler.readFromFile("playersTest.txt"));
        } catch (Exception e) {}
    }

    // @Test
    // @DisplayName("Test getting Images from directory")
    // public void testGetImage() {
    //     String path = System.getProperty("user.dir");
    //     Image image = new Image(path + "src/main/resources/testImage.jpg");
    //     // Image image = new Image(getClass().getResource("testImage.jpg").toString());

    //     String imageString = image.toString();

    //     try {
    //         assertEquals(imageString, this.filehandler.getImage("testImage.jpg").toString());
    //     } catch (IOException e) {}
    // }

    @Test
    @DisplayName("Test reading and writing Player-s from/to file")
    public void testReadAndWritePlayersToFile() {
        ArrayList<String> playerData = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();

        try {
            assertEquals(players, this.filehandler.getPlayersFromFile("playersTest.txt"));
        } catch (Exception e) {}

        players.add(new Player("abcd", "xxxx", 100, 0 ,0));
        playerData.add(players.get(0).toString());

        try {
            this.filehandler.writePlayersToFile("playersTest.txt", players);
        } catch (Exception e) {}

        try {
            ArrayList<Player> testPlayers = new ArrayList<>();
            ArrayList<String> testPlayerData = new ArrayList<>();

            testPlayers = this.filehandler.getPlayersFromFile("playersTest.txt");

            Player player = testPlayers.get(0);

            testPlayerData.add(player.toString());

            assertEquals(playerData, testPlayerData);
        } catch (Exception e) {}

        try {
            this.filehandler.clearFile("playersTest.txt");
        } catch (FileNotFoundException e) {}
    }

    
}
