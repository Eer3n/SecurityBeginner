import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.sound.sampled.Port;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream inputStream;

    Server(int port) throws Exception {
        server = new ServerSocket(port);
        System.out.println("Server Started!");

        socket = server.accept();
        System.out.println("Client Accepted!");

        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        String encodedKey = inputStream.readUTF();
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey secretKey = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");

        String line = "";

        while(!line.toLowerCase().equals("over")){
            line = inputStream.readUTF();
            byte[] decoded_message = Base64.getDecoder().decode(line);
            String decryptedMessage = EncryptionUtility.decrypt(decoded_message, secretKey);
            System.out.println("Client says: "+ decryptedMessage);
        }
        System.out.println("System Closing!");
        inputStream.close();
        socket.close();
        server.close();
    }

    public static void main(String[] args) throws Exception {
        new Server(32456);
    }
}
