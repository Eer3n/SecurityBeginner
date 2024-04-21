package Ders;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKeys {
    public static void writeToFile(String path, byte[] key) throws Exception{
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f); // file veri yazan stream stream = akis
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public static void main(String[] args) throws Exception{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);

        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        writeToFile("KeyStore/publicKey", publicKey.getEncoded());
        writeToFile("KeyStore/privateKey", privateKey.getEncoded());
    }
}
