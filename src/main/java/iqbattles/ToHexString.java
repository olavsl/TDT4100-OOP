package iqbattles;

import java.math.BigInteger;

public class ToHexString {
    
    // Method for converting byte array to hexString, which makes comparison of password hashes easier
    public static String toHexString(byte[] hash) {

        // Using big integer, because creating a hex string of such a large number needs more bytes to be stored.
        BigInteger num = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(num.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        
        return hexString.toString();
    }
    
}
