package output;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    KeyGenerator keygenerator;
    Cipher desCipher;

    {
        try {
            keygenerator = KeyGenerator.getInstance("DES");

            desCipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    SecretKey myDesKey = keygenerator.generateKey();
    public void encrypt(String pass) {
        try{

            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");


            byte[] text = "No body can see me.".getBytes("UTF8");


            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);

            String s = new String(textEncrypted);
            System.out.println(s);

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);

            s = new String(textDecrypted);
            System.out.println(s);
        }catch(Exception e)
        {
            System.out.println("Exception");
        }
    }
    public void decrypt(String pass) throws UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        try {
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] pw = pass.getBytes("UTF8");
        byte[] textDecrypted = desCipher.doFinal(pw);

        String s = new String(textDecrypted);
        System.out.println(s);
    }
}
