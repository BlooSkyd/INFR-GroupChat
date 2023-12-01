public class IOMesssageHandler {

    private String username; 
    private String user;

    public IOMesssageHandler(String username) {
        this.username = username;
        this.user = username;
    }

    public IOMesssageHandler() {}

    public String getUser() {
        return this.user;
    }
    public static void main(String[] args) {
        
        String res = "";
        for (String arg : args) {
            res += arg + " ";
        }
        System.out.println("arg: ["+res+"]");
        encode(res);
    }

    private String[] check(String msg) {
        try {
            String[] data = msg.split("\\\\n");
            //System.out.println("[DEBUG]: " + data[0] + ";" + data[1] + ";" + data[2] + ";"+data[3]);
            return Integer.parseInt(data[2]) == data[3].length() ? data : null;
        } catch (Exception e) {
            System.out.println("[ANALYSE EXCEPTION] : "+ e);
            return null;
        }
    }

    public String decode(String msg) {
        String[] data = check(msg);
        //System.out.println("[DECODE] : "+ String.valueOf(data));
        if (data != null) {
            return "[" + data[1] + "] " + data[0] + ": " + data[3];
        }
        return "Erreur";
    }

    public static void encode(String msg) {
        //String[] entry = msg.split("");
        String res = msg;
        res = res.replaceAll("[àâä]", "a");
        res = res.replace("éèêë", "e");
        res = res.replace("îï", "i");
        res = res.replace("ôö", "o");
        res = res.replace("ùûü", "u");
        res = res.replace("ÿ", "y");
        res = res.replace("ç", "c");
        res = res.replace("à", "a");
        /*
        for (String ch : entry) {
            switch (ch) {
                case "à":
                case "â":
                case "ä":
                    res += "a";
                    break;
                case "é":
                case "è":
                case "ê":
                case "ë":
                    res += "e";
                    break;
                case "î":
                case "ï":
                    res += "i";
                    break;
                case "ô":
                case "ö":
                    res += "o";
                    break;
                case "ù":
                case "û":
                case "ü":
                    res += "u";
                    break;
                case "ÿ":
                    res += "y";
                    break;
                case "ç":
                    res += "c";
                    break;
                default:
                    res += ch;
                    break;
            }
        }*/
        System.out.println(res);
        //String res = String. entry.
        //return entry;
    }
    
    public void analyse(String msg) {
        String[] cmd = msg.split(" ");
        switch (cmd[0]) {
            case "/color":
                color(cmd);
                break;
            case "/help":
                break;

            default:
                System.out.println("Commande non-reconnue");
                break;
        }
    }
    
    public void color(String[] cmd) {
        switch (cmd.length) {
            case 1:
                System.out.println("Afficher l'aide ici");
                break;
            case 2:
                switch (cmd[1]) {
                    case "noir":
                        this.user = "\u001B[90m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[30mnoir\u001B[0m");
                        break;
                    case "rouge":
                        user = "\u001B[31m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[31mrouge\u001B[0m");
                        break;
                    case "vert":
                        user = "\u001B[32m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[32mvert\u001B[0m");
                        break;
                    case "jaune":
                        user = "\u001B[33m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[33mjaune\u001B[0m");
                        break;
                    case "bleu":
                        user = "\u001B[34m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[34mbleu\u001B[0m");
                        break;
                    case "violet":
                        user = "\u001B[35m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[35mviolet\u001B[0m");
                        break;
                    case "cyan":
                        user = "\u001B[36m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[36mcyan\u001B[0m");
                        break;
                    case "blanc":
                        user = "\u001B[37m" + username + "\u001B[0m";
                        System.out.println("Couleur du pseudo basculé en \u001B[37mblanc\u001B[0m");
                        break;
                    case "reset":
                        user = "\u001B[0m" + username;
                        System.out.println("Couleur du pseudo réinitialisé");
                        break;

                    default:
                        System.out.println("Saisi non reconnue");
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
}
