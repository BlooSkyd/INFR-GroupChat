import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Sender {
    
    private static String getSenderName(String[] args) {
        String res = "";
        for (String arg : args) {
            res += arg;
        }
        return res;
    }

    public static void main(String[] args) {
        
        final String user = getSenderName(args); 

        try {
            CarnetAdresse ca = new CarnetAdresse();


            String message = new String();

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            for (int i = 0; i < ca.getSize(); i++ ) {
                Socket so = new Socket(ca.getIp(i),2023);

                OutputStream os = so.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                message = br.readLine();
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                Message m = new Message(user, ldt.format(dtf), String.valueOf(message.length()), message);
                bw.write(m.send());
                bw.flush();
                System.out.println(m.send());
                so.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}