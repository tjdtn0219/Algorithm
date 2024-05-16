import java.io.*;
import java.util.*;

public class Main {

    int n1, n2;
    String s1;
    String s2;
    int[][] LCS;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        init();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s1 = br.readLine();
            s2 = br.readLine();
            n1 = s1.length();
            n2 = s2.length();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void init() {
        LCS = new int[n1+1][n2+1];
    }

    public void solve() {
        for(int i=1; i<=n1; i++) {
            char c1 = s1.charAt(i-1);
            for(int j=1; j<=n2; j++) {
                char c2 = s2.charAt(j-1);
                if(c1 == c2) {
                    LCS[i][j] = LCS[i-1][j-1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);
                }
            }
        }
        System.out.println(LCS[n1][n2]);
    }

}