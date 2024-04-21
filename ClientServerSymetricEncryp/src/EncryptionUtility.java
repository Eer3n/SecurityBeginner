import javax.crypto.*;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class EncryptionUtility {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;

    public static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        keyGen.init(KEY_SIZE,secureRandom);
        return keyGen.generateKey();
    }

    public static byte[] encrypt(String plain_text, SecretKey secretKey) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plain_text.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] encrypted_text, SecretKey secretKey) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(encrypted_text));
    }
}
