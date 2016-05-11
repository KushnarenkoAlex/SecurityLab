package ua.nure.serdiuk.keygen;

import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Keygen {

    private KeyPair keys;

    private Cipher cipher;

    public Keygen(String algorithm) {
        try {
            keys = KeyPairGenerator.getInstance(algorithm).generateKeyPair();
            cipher = init(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private Cipher init(String algorithm){
        try {
            return Cipher.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException();
        }
    }

    public Map<String, BigInteger> getPublicKeys() {
        RSAPublicKeyImpl key = (RSAPublicKeyImpl) keys.getPublic();

        Map<String, BigInteger> map = new HashMap<>();
        map.put("n", key.getModulus());
        map.put("e", key.getPublicExponent());

        return map;
    }

    public String encrypt(byte[] bytes) {

        try {
            cipher = init("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keys.getPublic());
            bytes = cipher.doFinal(bytes);

            for(byte b : bytes){
                System.out.print(b + " ");
            }
            System.out.println();

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(bytes);
    }

    public String decrypt(String data) {
        byte[] bytes = new byte[]{};

        try {
            cipher = init("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
            bytes = cipher.doFinal(data.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(bytes);
    }
}
