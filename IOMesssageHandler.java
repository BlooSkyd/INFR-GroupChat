public class IOMesssageHandler {

    private String[] analyse(String msg) {
        try {
            String[] data = msg.split("\n");
            return Integer.parseInt(data[2]) == data[3].length() ? data : null;
        } catch (Exception e) {
            return null;
        }
    }

    public String decode(String msg) {
        String[] data;
        if ((data = analyse(msg)) != null) {
            return "[" + data[1] + "] " + data[0] + " : " + data[3];
        }
        return "Erreur";
    }
}
