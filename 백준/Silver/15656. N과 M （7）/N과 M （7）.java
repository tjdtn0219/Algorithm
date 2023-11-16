import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static boolean[] vis;
    public static int arr[];
    public static int ans[];
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        vis = new boolean[n+1];
        arr = new int[n];

        strings = br.readLine().split(" ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }
        ans = new int[m];

        Arrays.sort(arr);

        btk(0);

        System.out.println(sb);
    }

    public static void btk(int k) {
        if(m==k) {
            printArr();
            return ;
        }

        int temp = -1;
        for(int i=0; i<n; i++) {
            if(temp == arr[i]) continue;
            temp = arr[i];
            ans[k] = arr[i];
            btk(k+1);
        }
    }

    public static void printArr() {
        for(int num : ans) {
            sb.append(num+" ");
        }
        sb.append("\n");
    }
}
