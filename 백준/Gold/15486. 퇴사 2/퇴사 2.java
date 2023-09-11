import java.io.*;

public class Main {

    public static final int MAX = 1600000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[MAX][2];

        for(int i=0; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            int t = Integer.parseInt(strings[0]);
            int p = Integer.parseInt(strings[1]);
            arr[i+1][0] = t;
            arr[i+1][1] = p;
        }

        int[] dp = new int[MAX];        //dp[i] : i가 끝나는 날 갖고 받은 최대 총액
        int cur_max = 0;
        for(int i=1; i<=n; i++) {
            cur_max = Math.max(cur_max, dp[i-1]);
            int t = arr[i][0];
            int p = arr[i][1];
            dp[i+t-1] = Math.max(cur_max + p, dp[i+t-1]);
            dp[i] = Math.max(dp[i], cur_max);
        }
//        for(int i=1; i<=n; i++) System.out.print(dp[i] + " ");
//        System.out.println();

        System.out.println(dp[n]);
    }
}

