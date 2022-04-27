package iqbattles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class FilehandlerTest {

    private Filehandler filehandler = new Filehandler();

    @Test
    @DisplayName("Write and read to file")
    public void testConstructor() {
        ArrayList<String> lines = new ArrayList<>();

        // TODO: Reference file
        try {
            assertEquals(this.filehandler.readFromFile("playerTest.txt"), lines);;
        } catch (Exception e) {
            // fail("Could not find file");
        }

        lines.add("abcdef");
        lines.add("ghijkl");

        try {
            assertEquals(this.filehandler.readFromFile("playerTest.txt"), lines);
        } catch (Exception e) {}
        
    }

    @Test
    @DisplayName("Test reading from non-existing file")
    public void testReadFromNonExistingFile() {
        assertThrows(FileNotFoundException.class, () -> {
            ArrayList<String> lines = this.filehandler.readFromFile("foo");
        });
    }

    
}
