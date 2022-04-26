package iqbattles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;

public interface Filehandling {
    
    public ArrayList<String> readFromFile(String fileName) throws FileNotFoundException, IOException;

    public void writeToFile(String fileName, ArrayList<String> lines) throws FileNotFoundException;

    public void clearFile(String fileName) throws FileNotFoundException;

    public Image getImage(String fileName) throws FileNotFoundException, IOException;

    public ArrayList<Player> getPlayersFromFile(String fileName) throws IOException;

    public void writePlayersToFile(String fileName, ArrayList<Player> players) throws FileNotFoundException;

}
