package Elif;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class GenerateKeys {
    public static void fileWriter(String path,byte[] key) throws Exception{
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public static void main(String[] args) throws Exception{
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
        pairGenerator.initialize(1024);

        KeyPair pair = pairGenerator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        fileWriter("EEKeyStore/privateKey", privateKey.getEncoded());
        fileWriter("EEKeyStore/publicKey", publicKey.getEncoded());
    }
}
