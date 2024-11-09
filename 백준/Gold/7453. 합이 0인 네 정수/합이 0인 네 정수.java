import java.io.*;
import java.util.Arrays;

public class Main {

    static final int N = 4;
    
    int n;
    long[] A;
    long[] B;
    long[] C;
    long[] D;
    long[] AB;
    long[] CD;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[i * n + j] = A[i] + B[j];
                CD[i * n + j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long ans = 0;
        for(long ab : AB) {
            long cd = -ab;
            ans += getUpperIdx(cd, CD) - getLowerIdx(cd, CD);
        }
        System.out.println(ans);
    }

    public int getLowerIdx(long tg, long[] arr) {
        int st = 0;
        int en = n*n;
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

    public int getUpperIdx(long tg, long[] arr) {
        int st = 0;
        int en = n*n;
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

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            A = new long[n];
            B = new long[n];
            C = new long[n];
            D = new long[n];

            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().split(" ");
                A[i] = Long.parseLong(s[0]);
                B[i] = Long.parseLong(s[1]);
                C[i] = Long.parseLong(s[2]);
                D[i] = Long.parseLong(s[3]);
            }
            
            AB = new long[n * n];
            CD = new long[n * n];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

}