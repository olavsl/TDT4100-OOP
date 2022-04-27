package iqbattles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ToHexStringTest {
    
    @Test
    @DisplayName("Test toHexString metode")
    public void testToHexString() {
        String input = "abc";

        String hashedInput = ToHexString.toHexString(input.getBytes());

        assertEquals("00000000000000000000000000616263", hashedInput);
    }
}
