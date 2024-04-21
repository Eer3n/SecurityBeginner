package Ders;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class AsymmetricEncryption {
    public static String printBytes(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02X:", b));
        }
        return sb.substring(0,sb.toString().length()-1);
    }

    public static void main(String[] args) throws Exception{
        String msg = "This is SE375 SYSTEM PROGRAMMING.";
        byte[] plain_text = msg.getBytes("UTF-8");
        byte[] encrypted_text;
        byte[] decrypted_text;
        Cipher cipher;

        System.out.println("\n\nOriginal Text: " + msg);
        System.out.println("Original Byte Array: " + printBytes(plain_text));

        // STEP-1 Generate the Keys
        byte[] keyBytes = Files.readAllBytes(new File("KeyStore/privateKey").toPath());
        PKCS8EncodedKeySpec spec1 = new PKCS8EncodedKeySpec(keyBytes);
        // KeyFactory generates keys with given specs
        // While KeyGenerator generates keys from scratch
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec1);

        keyBytes = Files.readAllBytes(new File("KeyStore/publicKey").toPath());
        X509EncodedKeySpec spec2 = new X509EncodedKeySpec(keyBytes);
        PublicKey publicKey = kf.generatePublic(spec2);

        // STEP-2 Prepare Cipher
        cipher = Cipher.getInstance("RSA");

        // STEP-3 Encrypt!
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        encrypted_text = cipher.doFinal(plain_text);
        System.out.println("\n\nEncyrpted Text: " + new String(encrypted_text));
        System.out.println("Encyrpted Byte Array: " + printBytes(encrypted_text));

        // STEP-4 Decrypt!
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        decrypted_text = cipher.doFinal(encrypted_text);
        System.out.println("\n\nDecrypted Text: " + new String(decrypted_text));
        System.out.println("Decrypted Byte Array: " + printBytes(decrypted_text) + "\n");


    }
}
