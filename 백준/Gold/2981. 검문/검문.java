import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 1_000_000_000;

    int n;
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
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Arrays.sort(arr);
 
	    int gcdVal = arr[1] - arr[0];
 
        for(int i = 2; i < n; i++) {
            gcdVal = gcd(gcdVal, arr[i] - arr[i - 1]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=gcdVal; i++) {
            if(gcdVal % i == 0) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
        
	}

    int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }   

}