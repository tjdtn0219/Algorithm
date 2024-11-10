import java.io.*;
import java.util.*;

public class Main {

    int h, w;
    int[] arr;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void solve() {
        int ans = 0;
        for(int i=1; i<w-1; i++) {
            int left = getMaxHighLeft(i);
            int right = getMaxHighRight(i);
            int minH = Math.min(left, right);
            if(minH - arr[i] > 0) {
                ans += minH - arr[i];
            }
        }
        System.out.println(ans);
    }

    public int getMaxHighLeft(int idx) {
        int maxH = 0;
        for(int i=0; i<idx; i++) {
            maxH = Math.max(maxH, arr[i]);
        }
        return maxH;
    }

    public int getMaxHighRight(int idx) {
        int maxH = 0;
        for(int i=idx+1; i<w; i++) {
            maxH = Math.max(maxH, arr[i]);
        }
        return maxH;
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] h_w = br.readLine().split(" ");
            h = Integer.parseInt(h_w[0]);
            w = Integer.parseInt(h_w[1]);
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

}