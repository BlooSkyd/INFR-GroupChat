import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Receiver {
    public static void main(String[] args) {
        System.out.println("Starting Receiver.java");
        //final long client_pid = Long.parseLong(args[0]);
        try {
            ServerSocket server = new ServerSocket(2023);
            System.out.println("[Server] A server is open: " + server.toString());
            Boolean run = true;

            //while (ProcessHandle.of(client_pid).isPresent()) {
            while(run) {
                // TODO : checker le statut de l'appli client
                try {
                    Socket socket = server.accept();

                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    OutputStream output = socket.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

                    String entry = reader.readLine();

                    // Tant que la saisie n'est pas "stop", on l'affiche
                    while (!entry.equals("$/stop")) {
                        System.out.println("[Server] Received: " + entry);
                        writer.write(entry);
                        writer.newLine();
                        System.out.println(entry);
                        writer.flush();
                        entry = reader.readLine();
                    }

                    writer.close();
                    output.close();
                    System.out.println("[Server] Command prompt, closing server...");
                    run = false;

                } catch (SocketException e) {
                    // TODO: handle exception
                }

                
            }
            
            System.out.println("[Server] Client down, closing server...");
            server.close();
            
        } catch (Exception e) {
            System.out.println(">>> Exception: "+e);
        }
    }
}
