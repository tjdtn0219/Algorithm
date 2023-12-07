import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, k;
    public static int totalCombCnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        m = Integer.parseInt(br.readLine());

        String[] strings = br.readLine().split(" ");
        int[] arr = new int[m];

        k = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++) {
            arr[i] = Integer.parseInt(strings[i]);
            n += arr[i];
        }

        double total = getComb(n, k);

        double comb = 0;
        for(int num : arr) {
            if(num < k) continue;
            comb += getComb(num, k);
        }

        double ans = comb / total;
        System.out.println(ans);
    }

    public static double getComb(int n, int k) {
        double cnt = 1;
        while(k>0) {
            cnt *= n--;
            k--;
        }
        return cnt;
    }
}
