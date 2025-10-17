import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, h;
    static int[] up;
    static int[] down;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        Arrays.sort(down);
        Arrays.sort(up);

        int min = Integer.MAX_VALUE;
        int ansCnt = 0;
        for(int i=1; i<=h; i++) {
            int cnt = getLowerIdx(i, down) + getLowerIdx(h-i+1, up);

            if(min == cnt) {
                ansCnt++;
                continue;
            }

            if(cnt < min) {
                ansCnt = 1;
                min = cnt;
            }

        }

        System.out.println(min + " " + ansCnt);

    }

    public static int getLowerIdx(int tg, int[] arr) {
        int st = 0;
        int en = n / 2;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= arr[mid]) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }
        return n/2 - st;
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            h = Integer.parseInt(tmp[1]);
            up = new int[n/2];
            down = new int[n/2];
            for(int i=0; i<n/2; i++) {
                down[i] = Integer.parseInt(br.readLine());
                up[i] = Integer.parseInt(br.readLine());
            }
        } catch(Exception e) {
            throw new RuntimeException("INPUT ERROR");
        }
    }
}