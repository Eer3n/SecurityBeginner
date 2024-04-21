import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Base64;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    Client(int port, String address)throws Exception{
        SecretKey secretKey = null;
        socket = new Socket(address, port);

        System.out.println("Client is Connected!");
        input = new DataInputStream(System.in);
        out = new DataOutputStream(socket.getOutputStream());

        secretKey = EncryptionUtility.generateSecretKey();
        out.writeUTF(Base64.getEncoder().encodeToString(secretKey.getEncoded()));

        String line = "";

        while(!line.equals("over")){
            line = input.readLine();
            byte[] line_encrypt = EncryptionUtility.encrypt(line,secretKey);
            out.writeUTF(Base64.getEncoder().encodeToString(line_encrypt));
        }
        System.out.println("Client System is Closed");
        out.close();
        input.close();
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        new Client(32456, "127.0.0.1");
    }

}
