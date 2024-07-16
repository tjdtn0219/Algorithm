import java.io.*;
import java.util.*;

public class Main {

    int N;
    long[] arr;
    long min;
    long[] ans;

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
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            Arrays.sort(arr);
            min = Long.MAX_VALUE;
            ans = new long[3];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        
        for(int st = 0; st<N-2; st++){
            int mid = st + 1;
            int en = N-1;
            while(mid < en) {
                long sum = arr[st] + arr[mid] + arr[en];
                if(Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    ans[0] = arr[st];
                    ans[1] = arr[mid];
                    ans[2] = arr[en];
                }
                if(sum < 0) mid++;
                else en--;
            }
        }

        for(long num : ans) {
            System.out.print(num + " ");
        }
    }

}