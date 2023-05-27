import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        String[] strings = bf.readLine().split(" ");
        int[] arr = new int[n];
        int[] dp = new int[n];     //max_sum[i] : i번째 요소를 포함한 수열 중 합이 가장 큰 값
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
            dp[i] = arr[i];
        }

        dp[0] = arr[0];
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j] + arr[i]);
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);

    }
}