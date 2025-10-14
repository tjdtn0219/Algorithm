import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[] arr;
    static int[] cards;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        Arrays.sort(arr);
        int[] ans = new int[m];
        for(int i=0; i<m; i++) {
            int card = cards[i];
            ans[i] = getUpperIdx(card) - getLowerIdx(card);
        }
        StringBuilder sb = new StringBuilder();
        for(int num : ans) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    public static int getLowerIdx(int tg) {
        int st = 0;
        int en = n;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= arr[mid]) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }

        return st;
    }

    public static int getUpperIdx(int tg) {
        int st = 0;
        int en = n;

        while(st < en) {
            int mid = (st + en) / 2;
            if(tg < arr[mid]) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }

        return st;
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            String[] tmp = br.readLine().split(" ");
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(tmp[i]);
            }
            m = Integer.parseInt(br.readLine());
            cards = new int[m];
            tmp = br.readLine().split(" ");
            for(int i=0; i<m; i++) {
                cards[i] = Integer.parseInt(tmp[i]);
            }
        } catch(Exception e) {
            throw new RuntimeException("INPUT ERROR");
        }
    }
}