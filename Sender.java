public class Sender {
    public static void main(String[] args) {
        System.out.println(Sender.getSenderName(args));
    }

    private static String getSenderName(String[] args) {
        String res = "";
        for (String arg : args) {
            res += arg;
        }
        return res;
    }
}