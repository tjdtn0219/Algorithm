import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] strings = br.readLine().split(" ");
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(strings[i]);

        //dp[i] = k : i번째 수열까지의 최대 증가수열의 길이 = k
        int[] dp = new int[n];
        int[] pre = new int[1005];
        Arrays.fill(dp, 1);
        Arrays.fill(pre, -1);
        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i] && dp[j]+1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j;
                }
            }
        }

        int maxi = 0; int maxValue = dp[0];
        for(int i=1; i<n; i++) {
            if(maxValue < dp[i]) {
                maxi = i;
                maxValue = dp[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        int cur = maxi;
        while(true) {
            stk.push(arr[cur]);
            cur = pre[cur];
            if(cur==-1) break;
        }
        while(!stk.isEmpty()) {
            sb.append(stk.pop() + " ");
        }
        System.out.println(maxValue);
        System.out.println(sb);

    }
}