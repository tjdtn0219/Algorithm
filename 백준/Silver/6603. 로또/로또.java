import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static final int M = 6;
    public static int[] arr;
    public static int[] res;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String[] strings = br.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);

            if(n == 0) break;

            arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(strings[i+1]);
            }
            res = new int[n];

            Arrays.sort(arr);
            btk(0, n, 0);
            System.out.println("");
        }

    }

    public static void btk(int k, int n, int li) {
        if(k==M) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<M; i++) sb.append(res[i] + " ");
            System.out.println(sb.toString());
            return ;
        }

        for(int i=li; i<n; i++) {
            res[k] = arr[i];
            btk(k+1, n, i+1);
        }
    }

}
