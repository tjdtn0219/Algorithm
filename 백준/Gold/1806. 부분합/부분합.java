import java.io.*;
import java.util.*;

public class Main {

    public static List<List<Integer>> tree = new ArrayList<>();
    public static int[] parent;
    public static int[] depth;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int s = Integer.parseInt(strings[1]);

        strings = br.readLine().split(" ");
        int[] arr = new int[n+1];
        int[] dp = new int[n+1];
        for(int i=0; i<n; i++) {
            arr[i+1] = Integer.parseInt(strings[i]);
            dp[i+1] = arr[i+1];
        }

        for(int i=2; i<=n; i++) {
            dp[i] = arr[i] + dp[i-1];
        }

        int left = 1;
        int right = 1;

        int ans = Integer.MAX_VALUE;
        while(left <= right && right<=n) {
            int partSum = dp[right] - dp[left-1];
            if(partSum < s) right++;
            else {
                ans = Math.min(ans, right-left+1);
                left++;
            }
        }

        if(ans == Integer.MAX_VALUE) ans = 0;

        System.out.println(ans);

    }
}
