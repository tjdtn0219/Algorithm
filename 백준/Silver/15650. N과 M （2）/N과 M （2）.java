import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static boolean[] vis;
    public static int arr[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        vis = new boolean[n+1];
        arr = new int[m];

        btk(0, 1);

    }

    public static void btk(int k, int lastIdx) {
        if(m==k) {
            printArr();
            return ;
        }

        for(int i=lastIdx; i<=n; i++) {
            arr[k] = i;
            btk(k+1, i+1);
        }
    }

    public static void printArr() {
        StringBuilder sb = new StringBuilder();
        for(int num : arr) {
            sb.append(num+" ");
        }
        System.out.println(sb);
    }
}
