import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
            
            IOMesssageHandler iomHandler = new IOMesssageHandler();
            iomHandler.printDebug("Connexion reçue "+ socket.toString());

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            ArrayList<String> entry = new ArrayList<String>();//reader.readLine();
            String line = "";

            // Tant que la saisie n'est pas "stop", on l'affiche
            while ((line = reader.readLine()) != null) { //&& !entry.equals("$/stop")
                entry.add(line);
                iomHandler.printDebug("Ligne reçue : " + line);
            }
            iomHandler.printDebug("Fin de reception : "+entry.size()+" message(s) réceptionné(s)");
            if (entry.size() != 4) {
                writer.write("Erreur récéption: nombre d'arguments incorrect");
                System.out.println("Erreur récéption: nombre d'arguments incorrect");
            } else {
                iomHandler.printDebug("Décodage en cours...");
                String result = iomHandler.decode(entry);
                writer.write(result);
                System.out.println(result);

            }
            
            writer.flush();
            writer.close();
            output.close();
            socket.close();
        }

        System.out.println("[Server] Client down, closing server...");
        server.close();
    }
}
