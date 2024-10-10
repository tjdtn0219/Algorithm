import java.io.*;
import java.util.Arrays;

public class Main {

    static final int MAX = 1_000_000_000;

    int n, c;
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
            String[] n_c = br.readLine().split(" ");
            n = Integer.parseInt(n_c[0]);
            c = Integer.parseInt(n_c[1]);
            arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int st = 0;
        int en = MAX;
        int ans = 0;
        while(st <= en) {
            int mid = (st + en) / 2;
            // System.out.println("st : " + st + ", en : " + en + ", mid : " + mid);
            int cnt = getCanInstallCnt(mid);
            if(cnt >= c) {
                ans = mid;
                st = mid + 1;
            } else if(cnt < c) {
                en = mid - 1;
            } else {
                ans = Math.max(ans, mid);
                st = mid + 1;
            }
        }

        System.out.println(ans);

    }

    public int getCanInstallCnt(int gap) {
        int cnt = 1;
        int last = arr[0];

        for(int i=1; i<n; i++) {
            int cur = arr[i];
            if(cur - last >= gap) {
                cnt++;
                last = cur;
            }
        }

        return cnt;
    }

}