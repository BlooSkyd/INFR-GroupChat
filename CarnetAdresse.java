import java.io.*;
import java.util.ArrayList;

public class CarnetAdresse {
    private ArrayList<String> ip_list;

    public CarnetAdresse() throws IOException {
        ArrayList<String> ip_list = new ArrayList<String>();

                FileInputStream list_fis = new FileInputStream("IP.txt");
                InputStreamReader list_isr = new InputStreamReader(list_fis);
                BufferedReader list_br = new BufferedReader(list_isr);

                while(list_br.readLine() != null) {
                    ip_list.add(list_br.readLine());
                }

                list_br.close();
                this.ip_list = ip_list;
        }

    public String getIp(int i) {
        return this.ip_list.get(i);
    }

    public int getSize() {
        return ip_list.size();
    }
}
