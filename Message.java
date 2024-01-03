import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String nom;
    private String ldt;
    private String taille;
    private String message;

    private static DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Message(String nom, String message) {
        LocalDateTime localDT = LocalDateTime.now();
        this.nom = nom;
        this.ldt = localDT.format(dtFormat);
        this.taille = String.valueOf(message.length());
        this.message = message;
    }

    public String send() {
        return (this.nom + "\n" + this.ldt + "\n" + this.taille + "\n" + this.message);
    }

    @Override
    public String toString() {
        return this.nom + ";" + this.ldt + ";" + this.taille + ";" + this.message;
    }
}
