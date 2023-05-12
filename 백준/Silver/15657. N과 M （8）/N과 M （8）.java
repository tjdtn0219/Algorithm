import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] arr;
    public static int[] ans;
    public static int n,m;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = bf.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        strings = bf.readLine().split(" ");
        arr = new int[n];
        ans = new int[m];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(arr);

        func(0,0);

    }

    public static void func(int k, int last) {
        if(k==m) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<m; i++) {
                sb.append(ans[i] + " ");
            }
            System.out.println(sb.toString());
            return;
        }

        for(int i=0; i<n; i++) {
            if(arr[i] >= last) {
                ans[k] = arr[i];
                func(k+1, arr[i]);
            }
        }
    }
}