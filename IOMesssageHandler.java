public class IOMesssageHandler {

    public static void main(String[] args) {
        
        String res = "";
        for (String arg : args) {
            res += arg + " ";
        }
        System.out.println("arg: ["+res+"]");
        encode(res);
    }

    private String[] analyse(String msg) {
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
        String[] data = analyse(msg);
        //System.out.println("[DECODE] : "+ String.valueOf(data));
        if (data != null) {
            return "[" + data[1] + "] " + data[0] + ": " + data[3];
        }
        return "Erreur";
    }

    public static void encode(String msg) {
        //String[] entry = msg.split("");
        String res = msg;
        res = res.replaceAll("[àâä]","a");
        res = res.replace("éèêë","e");
        res = res.replace("îï","i");
        res = res.replace("ôö","o");
        res = res.replace("ùûü","u");
        res = res.replace("ÿ","y");
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
}
