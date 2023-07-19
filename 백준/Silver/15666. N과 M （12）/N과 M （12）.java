import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int n, m;
    public static int[] arr;
    public static int[] res;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        strings = br.readLine().split(" ");

        arr = new int[n];
        res = new int[m];

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(arr);
        btk(0, 0);
    }

    public static void btk(int k, int li) {
        if(k==m) {
            StringBuilder sb = new StringBuilder();
            for(int num : res) sb.append(num + " ");
            System.out.println(sb.toString());
            return;
        }

        int tmp = -1;
        for(int i=li; i<n; i++) {
            if(arr[i] == tmp) continue;
            tmp = arr[i];
            res[k] = arr[i];
            btk(k+1, i);
        }
    }

}
