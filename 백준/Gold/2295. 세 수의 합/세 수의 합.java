import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] arr;
    HashSet<Integer> combSum;

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
            arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            combSum = new HashSet<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Arrays.sort(arr);
        // x + y + z = k <=> x + y = k - z;
        makeCombSum();
        for(int i=n-1; i>=1; i--) {
            for(int j=i-1; j>=0; j--) {
                if(combSum.contains(arr[i] - arr[j])) {
                    System.out.println(arr[i]);
                    return ;
                }
            }
        }
    }



    public void makeCombSum() {
        for(int i=0; i<n-1; i++) {
            for(int j=i; j<n; j++) {
                combSum.add(arr[i] + arr[j]);
            }
        }
    }

}