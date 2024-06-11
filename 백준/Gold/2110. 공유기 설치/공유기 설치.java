import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 1_000_000_000;

    int n, m;
    int[] locations;

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
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            locations = new int[n];
            for(int i=0; i<n; i++) {
                locations[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(locations);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
//        System.out.println(isLocated(1));
//        System.out.println(isLocated(2));
//        System.out.println(isLocated(3));
//        System.out.println(isLocated(4));
        bSearch();
    }

    public void bSearch() {
        int st = 1;
        int en = MAX + 1;
        while(st < en) {
            int mid = (st + en) / 2;
            if(canInstall(mid) < m) en = mid;
            else st = mid + 1;
        }
        System.out.println(st - 1);
    }


    public int canInstall(int gap) {
        int count = 1;
        int lastLocate = locations[0];

        for(int i = 1; i < n; i++) {
            int locate = locations[i];
            if(locate - lastLocate >= gap) {
                count++;
                lastLocate = locate;
            }
        }
        return count;

    }


}
