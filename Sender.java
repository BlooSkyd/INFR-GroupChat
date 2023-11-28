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
        System.out.println(Sender.getSenderName(args));
        try {
            ArrayList<String> ip = new ArrayList<String>();
            String message = new String();

            FileInputStream list_fis = new FileInputStream("IP.txt");
            InputStreamReader list_isr = new InputStreamReader(list_fis);
            BufferedReader list_br = new BufferedReader(list_isr);

            while(list_br.readLine() != null) {
                ip.add(list_br.readLine());
            }

            list_br.close();

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            for (String s : ip) {
                Socket so = new Socket(s,2023);

                OutputStream os = so.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                message = br.readLine();

                bw.write(args[0]);
                bw.newLine();
                bw.flush();
                System.out.println(args[0]);

                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                bw.write(ldt.format(dtf));
                bw.newLine();
                bw.flush();
                System.out.println(ldt.format(dtf));

                bw.write(message.length());
                bw.newLine();
                bw.flush();
                System.out.println(message.length());

                bw.write(message);
                bw.flush();
                System.out.println(message);

                bw.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}