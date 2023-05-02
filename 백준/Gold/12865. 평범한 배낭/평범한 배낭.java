import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = bf.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int k = Integer.parseInt(strs[1]);

        int[] W = new int[N+1];
        int[] V = new int[N+1];
        for(int i=1; i<=N; i++) {
            strs = bf.readLine().split(" ");
            W[i] = Integer.parseInt(strs[0]);
            V[i] = Integer.parseInt(strs[1]);
        }

        int[][] dp = new int[N+1][k+1];

        for(int j=1; j<k+1; j++) {
            for(int i=1; i<N+1; i++) {
                if(j-W[i] >= 0) dp[i][j] = Math.max(dp[i-1][j], V[i] + dp[i-1][j-W[i]]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][k]);


    }
}