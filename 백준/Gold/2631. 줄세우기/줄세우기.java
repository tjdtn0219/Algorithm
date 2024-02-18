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
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            stk = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public int solve() {
        return arr.length - LTS();
    }

    public int LTS() {
        for(int num : arr) {
            if(stk.isEmpty()) {
                stk.add(num);
            } else if(stk.get(stk.size()-1) < num) {
                stk.add(num);
            } else {
//                int idx = binarySearch(stk, num);
//                int idx = getLowerIdx(num);
                int idx = lowerBound(num);
                //size보다 클 경우
//                if(idx < 0) idx = 0;
//                if(idx >= stk.size()) idx = stk.size()-1;
                stk.set(idx, num);
            }
        }
        return stk.size();
    }

    public int lowerBound(int target) {
        int left = 0;
        int right = stk.size() - 1;

        while(left < right) {
            int mid = (left+right) / 2;

            if(target < stk.get(mid)) right = mid;
            else left = mid + 1;          //tg >= arr[mid]
        }
        return left;
    }

    int binarySearch(List<Integer> list, int target) {
        int output = 0;

        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (list.get(mid) >= target) {
                output = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return output;
    }

    public int getLowerIdx(int tg) {
        int lo = Collections.binarySearch(stk, tg);
        int upper, lower;
        if(lo < 0) {
            upper = lower = (lo + 1)*(-1);
        } else {
            lower = lo;
            upper = lo + 1;
        }
        return lower;
    }
}
