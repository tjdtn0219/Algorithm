import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 1_000_000_005;

    int n, m;
    int[] arr;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_m = br.readLine().split(" ");
            n = Integer.parseInt(n_m[0]);
            m = Integer.parseInt(n_m[1]);
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        bSearch();
    }

    public void bSearch() {
        int st = 0;
        int en = MAX;
        while(st <= en) {
            int mid = (st + en) / 2;
            // System.out.println("len : " + mid + ", isOk : " + isOk(mid));
            if(isOk(mid)) {
                st = mid + 1;
            } else {
                en = mid - 1;
            }
        }
        System.out.println(en);
    }

    public boolean isOk(int h) {
        long total = 0L;
        for(int num : arr) {
            if(num - h > 0) {
                total += num - h;
            }
        }
        if(total >= m) return true;
        else return false;
    }

}