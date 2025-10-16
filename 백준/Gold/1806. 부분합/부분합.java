import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = Integer.MAX_VALUE;

        while(left <= right && right < n) {
            // System.out.println("left : " + left + " , right : " + right + ", sum : " + sum + ", len : " + len);
            if(sum >= s) {
                len = Math.min(len, right - left + 1);
                sum -= arr[left++];
            } else {
                if(right < n-1) {
                    sum += arr[++right];
                } else {
                    right++;
                }
            }
        }

        int answer = len == Integer.MAX_VALUE ? 0 : len;

        System.out.println(answer);
    }


    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            s = Integer.parseInt(tmp[1]);
            arr = new int[n+1];
            tmp = br.readLine().split(" ");
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(tmp[i]);
            }
        } catch(Exception e) {
            throw new RuntimeException("INPUT ERROR");
        }
    }
}