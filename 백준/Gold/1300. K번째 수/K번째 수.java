import java.io.*;

public class Main {

    int n, k;

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
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        bSearch();
    }

    public void bSearch() {
        long st = 1;
        long en = k;

        while(st < en) {
            long mid = (st + en) / 2;
            long cnt = getCnt(mid);
            if(cnt < k) {
                st = mid + 1;
            } else {
                en = mid;
            }

        }
        System.out.println(st);

    }

    public long getCnt(long num) {
        long cnt = 0;
        for(int i=1; i<=n; i++) {
            if(num / i == 0) break;
            cnt += Math.min(num / i, n);
        }
        return cnt;
    }

}