import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

    
    public static void main(String[] args) {
        try {
            run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private static Boolean running = true;
    private static ServerSocket server;

    public static void setRunning(Boolean status) {
        Receiver.running = status;
    }

    public static void run(String[] arg) throws Exception {
        System.out.println("[Server] Starting Receiver.java");
        //final long client_pid = Long.parseLong(args[0]);

        Receiver.server = new ServerSocket(2023);
        System.out.println("[Server] A server is open: " + server.toString());

        //while (ProcessHandle.of(client_pid).isPresent()) {
        while (running) {
            // IDEA : checker le statut de l'appli client

            Socket socket = server.accept();

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            String entry = "";//reader.readLine();
            IOMesssageHandler iomHandler = new IOMesssageHandler();

            // Tant que la saisie n'est pas "stop", on l'affiche
            while ((entry = reader.readLine()) != null) { //&& !entry.equals("$/stop")
                //System.out.println("[Server] Received: " + entry);
                //System.out.println(entry);
                String result = iomHandler.decode(entry);
                //System.out.println(result);
                writer.write(result);
                writer.newLine();
                writer.flush();
            }

            writer.close();
            output.close();
            socket.close();
            //System.out.println("[Server] Command prompt, closing server...");
            //running = false;

        }

        System.out.println("[Server] Client down, closing server...");
        server.close();
    }
}
