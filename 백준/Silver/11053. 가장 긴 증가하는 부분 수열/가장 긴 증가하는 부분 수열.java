import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int[] arr;
    public static int[] res;
    public static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(bf.readLine());
        String[] strings = bf.readLine().split(" ");
        int[] arr = new int[n];
        int[] dp = new int[n];         //dp[i] : i번째를 포함하는 증가하는 수열의 최대 길이

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
            dp[i] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = -1;
        for(int i=0; i<n; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);

    }
}
