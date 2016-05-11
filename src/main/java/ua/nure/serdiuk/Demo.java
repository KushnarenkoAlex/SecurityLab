package ua.nure.serdiuk;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Demo {

    public static void main(String[] args) throws Exception {
        String data = "asdf";
        KeyPair keys = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keys.getPublic());

        byte[] encrypted = cipher.doFinal(data.getBytes());
//        BigInteger integer = new BigInteger(encrypted);
        System.out.println(new String(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
        byte[] decrypted= cipher.doFinal(encrypted);
//        BigInteger integer = new BigInteger(encrypted);
        System.out.println(new String(decrypted));

    }
}
