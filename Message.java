import java.time.LocalDateTime;

public class Message {
    private String nom;
    private String ldt;
    private String taille;
    private String message;

    public Message(String nom, String ldt, String taille, String message) {
        this.nom = nom;
        this.ldt = ldt;
        this.taille = taille;
        this.message = message;
    }

    public String send(){
            return (this.nom + "\n" + this.ldt + "\n" + this.taille + "\n" + this.message);
    }

    public String get(){
        if (Integer.parseInt(this.taille) == this.message.length()) {
            return (this.nom + "\n" + this.ldt + "\n" + this.taille + "\n" + this.message);
        }
        else {
            return "Erreur";
        }
    }
}
