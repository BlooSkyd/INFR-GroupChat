import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class IOMesssageHandler {

    private String username;
    private String user;
    private static Boolean debugStatus = false;
    private static DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public IOMesssageHandler(String username) {
        this.username = username;
        this.user = username;
    }

    public IOMesssageHandler() {
    }

    public String getUser() {
        return this.user;
    }

    public void setDebug(Boolean mode) {
        debugStatus = mode;
        System.out.println("[IOMH] Debug mode set to: " + mode);
    }

    public void printDebug(String debugMessage) {
        if (debugStatus) {
            System.out.println(debugMessage);
        }
    }

    private Boolean checkDateFormat(String dateMsg) {
        try {
            dtFormat.parse(dateMsg);
        } catch (DateTimeParseException e) {
            System.out.println("[ioMH] Analyse Exception : date format invalid");
            return false;
        }
        return true;
    }

    private Boolean check(ArrayList<String> msg) {
        try {
            printDebug("[CHECK]: " + msg.get(0) + ";" + msg.get(1) + ";" + msg.get(2) + ";" + msg.get(3));
            return Integer.parseInt(msg.get(2)) == msg.get(3).length() == checkDateFormat(msg.get(1));
        } catch (Exception e) {
            System.out.println("[ioMH: ANALYSE EXCEPTION] : " + e);
            return false;
        }
    }

    public String decode(ArrayList<String> msg) {
        if (check(msg)) {
            printDebug("[DECODE]: " + String.valueOf(msg));
            return "[" + msg.get(1) + "] " + msg.get(0) + ": " + msg.get(3);
        } else {
            return "Erreur decodage: nombre de caractrèes erroné ou format reçu non-conforme";
        }
    }

    public void analyse(String msg) {
        String[] cmd = msg.split(" ");
        switch (cmd[0]) {
            case "/c":
            case "/color":
                color(cmd);
                break;
            case "/help":
                help(cmd);
                break;

            default:
                System.out.println("Commande non-reconnue");
                break;
        }
    }

    private void color(String[] cmd) {
        switch (cmd.length) {
            case 1:
                helpColorMsg();
                break;
            case 2:
                switch (cmd[1]) {
                    case "noir":
                        this.user = "\u001B[90m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[30mnoir\u001B[0m : ");
                        break;
                    case "rouge":
                        user = "\u001B[31m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[31mrouge\u001B[0m : ");
                        break;
                    case "vert":
                        user = "\u001B[32m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[32mvert\u001B[0m : ");
                        break;
                    case "jaune":
                        user = "\u001B[33m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[33mjaune\u001B[0m : ");
                        break;
                    case "bleu":
                        user = "\u001B[34m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[34mbleu\u001B[0m : ");
                        break;
                    case "violet":
                        user = "\u001B[35m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[35mviolet\u001B[0m : ");
                        break;
                    case "cyan":
                        user = "\u001B[36m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[36mcyan\u001B[0m : ");
                        break;
                    case "blanc":
                        user = "\u001B[37m" + username + "\u001B[0m";
                        System.out.print("Couleur du pseudo basculé en \u001B[37mblanc\u001B[0m : ");
                        break;
                    case "default":
                    case "reset":
                    case "clear":
                        user = username;
                        System.out.print("Couleur du pseudo réinitialisé : ");
                        break;

                    default:
                        System.out.print("Saisi non reconnue, utilisez [/help color] pour plus d'information");
                        break;
                }
                System.out.println(user);

            default:
                break;
        }
        if (cmd.length > 2) {
            System.out.println("Commande invalide, voir /help color pour plus d'information");
        }
    }

    private void help(String[] cmd) {
        switch (cmd.length) {
            case 1:
                System.out.println(
                        "La commande /help vous permet de se renseigner sur le fonctionnement des commandes existantes.");
                System.out.println("Usage: /help <command>");
                System.out.println("Commandes: [color | c]");
                break;
            case 2:
                switch (cmd[1]) {
                    case "c":
                    case "color":
                        helpColorMsg();
                        break;

                    default:
                        System.out.print("Saisi non reconnue, utilisez [/help color] pour plus d'information");
                        break;
                }
                System.out.println(user);

            default:
                break;
        }
        if (cmd.length > 2) {
            System.out.println("Commande invalide, voir /help color pour plus d'information");
        }
    }

    private void helpColorMsg() {
        System.out.println(
                "La fonction /color vous permet de changer la couleur de votre pseudo\nAttention, cela peut causer des problèmes à la réception pour d'autre utilisateur.");
        System.out.println("/color <couleur>|reset");
        System.out.println("Couleurs disponibles: " + "\u001B[30mnoir\u001B[0m, " + "\u001B[31mrouge\u001B[0m, "
                + "\u001B[32mvert\u001B[0m, " + "\u001B[33mjaune\u001B[0m, " + "\u001B[34mbleu\u001B[0m, "
                + "\u001B[35mviolet\u001B[0m, " + "\u001B[36mcyan\u001B[0m, " + "\u001B[37mblanc\u001B[0m, default");
    }
}
