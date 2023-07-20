import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static final int N = 100;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] dp = new long[N+1];
        dp[1] = 1; dp[2] = 1; dp[3] = 1; dp[4] = 2; dp[5] = 2;
        dp[6] = 3; dp[7] = 4; dp[8] = 5; dp[9] = 7; dp[10] = 9;

        for(int i=11; i<N+1; i++) {
            dp[i] = dp[i-1] + dp[i-5];
        }
        
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }


}
