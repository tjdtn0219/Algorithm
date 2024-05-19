import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    int[] lectures;
    int[] sums;
    int max;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        init();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            lectures = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void init() {
        sums = new int[n];
        max = lectures[0];
        sums[0] = lectures[0];
        for(int i=1; i<n; i++) {
            sums[i] = sums[i-1] + lectures[i];
            max = Math.max(max, lectures[i]);
        }
    }

    public void solve() {
        System.out.println(findMinSize());
    }

    public int findMinSize() {
        int st = max;
        int en = sums[n-1];

        while(st < en) {
            int mid = (st + en) / 2;
            if(getCount(mid) <= m) en = mid;
            else st = mid + 1;
        }

        return st;
    }

    public int getCount(int size) {
        int cnt = 1;
        int minusSum = 0;
        for(int i=0; i<n; i++) {
            if(size < sums[i] - minusSum) {
                minusSum = sums[--i];
                cnt++;
            }
        }
        return cnt;
    }
}
