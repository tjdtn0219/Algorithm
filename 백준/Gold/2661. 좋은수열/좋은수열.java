import java.io.*;
import java.util.*;

public class Main {

    static boolean flag;
    int n;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            n = Integer.parseInt(br.readLine());
            flag = false;
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dfs("");
    }

    public void dfs(String string) {
        if(string.length() == n) {
            System.out.println(string);
            System.exit(0);
        }

        for(int i=1; i<=3; i++) {
            if(isEnable(string + i)) dfs(string + i);
        }

    }

    public boolean isEnable(String string) {
//        System.out.println("String : " + string);
        for(int i = 1; i <= string.length() / 2; i++) {
            String front = string.substring(string.length() -i * 2, string.length() - i);
            String back = string.substring(string.length() - i);
//            System.out.println("front : " + front + ", back : " + back);
            if(front.equals(back)) return false;
        }
        return true;
    }
}