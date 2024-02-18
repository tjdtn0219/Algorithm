import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] arr;
    int[] dp;
    List<Integer> stk;

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
            dp = new int[n];
            Arrays.fill(dp, 1);
//            for(int i=0; i<n; i++) {
//                arr[i] = Integer.parseInt(br.readLine());
//            }
            String[] tmp = br.readLine().split(" ");
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(tmp[i]);
            }
            stk = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public int solve() {
//        return LTS();
        return LTS_DP();
    }

    public int LTS() {
        for(int num : arr) {
            if(stk.isEmpty()) {
                stk.add(num);
            } else if(stk.get(stk.size()-1) < num) {
                stk.add(num);
            } else {
                int idx = lower_idx(num);
                //size보다 클 경우
                if(stk.size() == idx) stk.add(num);
                else stk.set(idx, num);
            }
        }
        return stk.size();
    }

    public int LTS_DP() {
        for(int i=1; i<arr.length; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int result = 0;
        for(int i=0; i<dp.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public int lower_idx (int tg) {
        int st = 0;
        int en = stk.size();

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= arr[mid]) en = mid;
            else st = mid + 1;
        }
        return st;
    }
}
