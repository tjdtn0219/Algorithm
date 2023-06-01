import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int n, m;
    public static int[] a1;
    public static int[] a2;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = bf.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        strings = bf.readLine().split(" ");
        a1 = new int[n];
        a2 = new int[n];
        for(int i=0; i<n; i++) {
            a1[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(a1);

        func(0,0);


    }

    public static void func(int k, int last_i) {
        if(k == m) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<k; i++) {
                sb.append(a2[i] + " ");
            }
            System.out.println(sb);
            return ;
        }
        int temp = 0;
        for(int i=last_i; i<n; i++) {
            if(temp != a1[i]) {
                a2[k] = a1[i];
                temp = a1[i];
                func(k + 1, i+1);
            }
        }
    }
}
