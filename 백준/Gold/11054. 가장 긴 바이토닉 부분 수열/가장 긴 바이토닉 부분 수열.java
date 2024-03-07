import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] arr;
    int[] lis_dp;
    int[] lds_dp;


    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        System.out.println(solve());
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            lis_dp = new int[n];
            lds_dp = new int[n];
            String[] tmp = br.readLine().split(" ");
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(tmp[i]);
            }
            Arrays.fill(lis_dp, 1);
            Arrays.fill(lds_dp, 1);
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }


    public int solve() {
        int answer = 0;
        fillLIS();
        fillLDS();
//        printArr(lis_dp);
//        printArr(lds_dp);
        for(int i=0; i<n; i++) {
            answer = Math.max(answer, lis_dp[i] + lds_dp[i] - 1);
        }
        return answer;
    }

    public void printArr(int[] arr) {
        for(int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public void fillLIS() {
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) {
                    lis_dp[i] = Math.max(lis_dp[i], lis_dp[j] + 1);
                }
            }
        }
    }

    public void fillLDS() {
        for(int i=n-2; i>=0; i--) {
            for(int j=n-1; j>i; j--) {
                if(arr[j] < arr[i]) {
                    lds_dp[i] = Math.max(lds_dp[i], lds_dp[j] + 1);
                }
            }
        }
    }

}