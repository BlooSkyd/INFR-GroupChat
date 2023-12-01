import java.io.*;
import java.net.*;

public class Sender {
    public static void main(String[] args) {
        try {
            new Sender(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String getSenderName(String[] args) {
        String res = "";
        for (String arg : args) {
            res += arg;
        }
        return res;
    }
    
    public Sender(String[] args) throws Exception {

        IOMesssageHandler ioMH = new IOMesssageHandler(getSenderName(args));
        CarnetAdresse carnet = new CarnetAdresse();

        InputStreamReader inputReader = new InputStreamReader(System.in);
        BufferedReader bufferReader = new BufferedReader(inputReader);

        String entry = bufferReader.readLine();
        Boolean msgToSend = !entry.startsWith("/") && !entry.equals("");

        while (!entry.equals("/stop")) {

            if (msgToSend) {
                for (int i = 0; i < carnet.getSize(); i++) {
                Socket socket = new Socket(carnet.getIp(i), 2023);

                OutputStream os = socket.getOutputStream();
                OutputStreamWriter outputWriter = new OutputStreamWriter(os);
                BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);

                Message msg = new Message(ioMH.getUser(), entry);
                System.out.println("[Sender] : " + msg.send());

                bufferedWriter.write(msg.send());
                bufferedWriter.flush();

                socket.close();
                } 
            } else {
                ioMH.analyse(entry);
            }
            
            entry = bufferReader.readLine();
            msgToSend = !entry.startsWith("/") && !entry.equals("");

        }
    
    }
}
