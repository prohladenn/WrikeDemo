package wrike;

public class Util {

    public static String generateLogin() {
        StringBuilder ans = new StringBuilder();
        int len = (int) (1 + Math.random() * 20);
        for (int i = 0; i < len; i++) {
            ans.append((char) ('a' + Math.random() * 26));
        }
        return ans.append("wpt@wriketask.qaa").toString();
    }

}
