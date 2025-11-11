import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int cnt;
    static HashSet<String> set;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
//        String s = "AA";
//        System.out.println(s.substring(1, s.length()));
        String s = String.valueOf(n);
        dfs(s, "");
//        System.out.println("cnt : " + cnt);
        System.out.println(set.size());
//        for(String tmp : set) {
//            System.out.println(tmp);
//        }
    }

    public static void dfs(String cur, String path) {
//        System.out.println("cur : " + cur);
        int len = cur.length();
        if(len == 1) {
            set.add(path);
            cnt++;
            return ;
        }

        dfs(cur.substring(0, len-1), path + " " + cur.substring(0, len - 1));
        dfs(cur.substring(1, len), path + " " + cur.substring(1, len));
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            cnt = 0;
            set = new HashSet<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}
