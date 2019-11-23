package output;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryption {
    public byte[] produceHash(String value, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        return md.digest(value.getBytes());
    }
}
