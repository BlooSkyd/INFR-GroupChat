import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static void main(String[] args) {
        System.out.println("Starting Receiver.java");

        try {
            ServerSocket server = new ServerSocket(2023);
            System.out.println("[Server] A server is open: " + server.toString());
            Socket socket = server.accept();

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            String entry = reader.readLine();

            // Tant que la saisie n'est pas "stop", on l'affiche
            while (!entry.equals("stop")) {
                System.out.println("[Server] Received: " + entry);
                writer.write(entry);
                writer.newLine();
                System.out.println(entry);
                writer.flush();

                System.out.println("[Server] Stopping server...");

                Thread.sleep(100);

                entry = reader.readLine();

            }
         
            System.out.println("[Server] Stopping server...");
        
            writer.close();
            output.close();
            server.close();

        } catch (Exception e) {
            //System.out.println(">>> Exception: "+e);
        }
    }
}
