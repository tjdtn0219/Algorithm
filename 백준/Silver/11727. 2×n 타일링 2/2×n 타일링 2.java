import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        int[] dp = new int[n+1];

        if(n==1) {
            System.out.println(1);
            return;
        }
        if(n==2) {
            System.out.println(3);
            return;
        }

        dp[1] = 1;
        dp[2] = 3;
        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1] + 2*dp[i-2]) % 10007;
        }

        System.out.println(dp[n]);

    }
}