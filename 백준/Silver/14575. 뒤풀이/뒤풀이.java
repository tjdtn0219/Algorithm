import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int n, t;
    static int[][] arr;
    // 1 000 000 000

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        int left = 0;
        int right = 1_000_000;
        int ans = Integer.MAX_VALUE;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(can(mid) == 0) {
                right = mid - 1;
                ans = Math.min(ans, mid);
            } else if(can(mid) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if(ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);

    }

    public static int can(int num) {
        int lower = 0;
        int upper = 0;

        for(int i=0; i<n; i++) {
            if(arr[i][0] > num) return 1;
            lower += arr[i][0];
            upper += Math.min(num, arr[i][1]);
        }

        if(lower <= t && t <= upper) {
            return 0;
        }

        if(lower > t) return -1;
        else return 1;
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_t = br.readLine().split(" ");
            n = Integer.parseInt(n_t[0]);
            t = Integer.parseInt(n_t[1]);
            arr = new int[n][2];
            for(int i = 0; i< n; i++) {
                String[] l_r = br.readLine().split(" ");
                int l = Integer.parseInt(l_r[0]);
                int r = Integer.parseInt(l_r[1]);
                arr[i][0] = l;
                arr[i][1] = r;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}
