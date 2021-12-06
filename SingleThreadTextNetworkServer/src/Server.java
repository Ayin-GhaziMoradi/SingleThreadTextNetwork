import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket welcomingSocket = new ServerSocket(7070)) {
            System.out.print("Server started");
            try (Socket connectionSocket = welcomingSocket.accept()) {
                System.out.println("client accepted ! waiting for user input ...");
                OutputStream out = connectionSocket.getOutputStream();
                InputStream in = connectionSocket.getInputStream();
                byte[] buffer = new byte[2048];
                String message , fullText = "";
                do {
                    int read = in.read(buffer);
                    message = new String(buffer, 0, read);
                    fullText += message;
                    out.write(fullText.getBytes());
                    System.out.println("SENT: " + fullText);
                }while (!message.equals("over"));
                System.out.print("Got 'Over' Command .\nClosing client ... ");
            } catch (IOException ex) {
                System.err.println(ex);
            }
            System.out.print("done.\nClosing server ... ");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("done.");
    }
}
