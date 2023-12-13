import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class stringToDate {
    private String message;
    public stringToDate(String message) {
        String[] split = new String[5];
        String date;
        split = message.split(" ");
        date = split[0].substring(1) + " " + split[1].substring(0,split[1].indexOf("]"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        date = "[" + dateTime.format(dtFormat) + "]";
        

        this.message = date + " " + split[2] + " " + split[3] + " " + split[4];
    }
    public String getMessage(){
        return message;        
    }
    public static void main(String[] args) {
        String message = "[2023-12-13 19:26:21] John : tt";
        String[] split = new String[5];
        String date;
        split = message.split(" ");
        date = split[0].substring(1) + " " + split[1].substring(0,split[1].indexOf("]"));
        System.out.println(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        date = "[" + dateTime.format(dtFormat) + "]";
        
        System.out.print(date + " " + split[2] + " " + split[3] + " " + split[4]);
    }
}