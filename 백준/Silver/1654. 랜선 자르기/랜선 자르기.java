import java.io.*;
import java.util.*;

public class Main {

    int n, k;
    int[] arr;
    int maxLen;

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
            String[] tmp = br.readLine().split(" ");
            k = Integer.parseInt(tmp[0]);
            n = Integer.parseInt(tmp[1]);
            maxLen = 0;
            arr = new int[k];
            for(int i=0; i<k; i++) {
                arr[i] = Integer.parseInt(br.readLine());
                maxLen = Math.max(maxLen, arr[i]);
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        long ans = 0;
        long st = 1;
        long en = maxLen;
        while(st <= en) {
            long mid = (st + en) / 2;
            int cnt = getCnt(mid);
            if(cnt >= n) {
                ans = Math.max(ans, mid);
                st = mid + 1;
            } else {
                en = mid - 1;
            }
        }
        System.out.println(ans);
    }

    public int getCnt(long len) {
        int cnt = 0;
        for(int num : arr) {
            cnt += num / len;
        }
        return cnt;
    }
}