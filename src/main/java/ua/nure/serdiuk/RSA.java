package ua.nure.serdiuk;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public final class RSA {

    private static final String KEYS_SESSION_ATTR = "rsaKeys";
    private static final String PUBLIC_KEY_REQUEST_ATTR = "serverPublicKey";

    private KeyPair keyPair;

    public RSA() {
        keyPair = generateNew();
    }

//    public static void saveKeysInfo(KeyPair keyPair, HttpServletRequest req) {
//        req.setAttribute(PUBLIC_KEY_REQUEST_ATTR, getModule(keyPair.getPublic()));
//        HttpSession session = req.getSession();
//        session.setAttribute(KEYS_SESSION_ATTR, keyPair);
//    }
//
//    public static void removeKeysInfo(HttpServletRequest req) {
//        req.removeAttribute(PUBLIC_KEY_REQUEST_ATTR);
//        HttpSession session = req.getSession();
//        session.removeAttribute(KEYS_SESSION_ATTR);
//    }
//
//    public static Key extractPrivateKey(HttpServletRequest req) {
//        HttpSession httpSession = req.getSession();
//        KeyPair keyPair = (KeyPair) httpSession.getAttribute(KEYS_SESSION_ATTR);
//        return keyPair.getPrivate();
//    }

    // Utility

    public String getModule() {
        Key publicKey = keyPair.getPublic();
        KeyFactory kF;
        RSAPublicKeySpec pKS;

        try {
            kF = KeyFactory.getInstance("RSA");
            pKS = kF.getKeySpec(publicKey, RSAPublicKeySpec.class);
            return pKS.getModulus().toString(16);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String input) {
        if (input.isEmpty()) {
            return input;
        }

        Key privateKey = keyPair.getPrivate();

        Cipher cipher;
        BigInteger passwordInt = new BigInteger(input, 16);
        byte[] decryptedText;
        try {
            cipher = Cipher.getInstance("RSA");
            byte[] passwordBytes = passwordInt.toByteArray();

            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedText = cipher.doFinal(passwordBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(decryptedText);
    }

    private static KeyPair generateNew() {
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGen.initialize(1024);
        return keyGen.genKeyPair();
    }
}

