import java.io.*;

public class Main {

    public static int[][] map;
    public static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        dp = new long[n][n];

        for(int i=0; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(strings[j]);
            }
        }

        dp[0][0] = 1;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
//                if(i==n-1 && j==n-1) break;
                if(map[i][j]==0) continue;
                int ni = i + map[i][j];
                if(ni<n) dp[ni][j] += dp[i][j];
                int nj = j + map[i][j];
                if(nj<n) dp[i][nj] += dp[i][j];
            }
        }

        System.out.println(dp[n-1][n-1]);
    }
}
