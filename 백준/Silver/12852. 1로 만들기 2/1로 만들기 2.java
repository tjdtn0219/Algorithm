import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        int[] d = new int[n+1];
        int[] prev = new int[n+1];

        d[1] = 0;

        for(int i=2; i<=n; i++) {
            d[i] = d[i-1] + 1;
            prev[i] = i-1;
            if(i%2==0 && d[i] > d[i/2] + 1) {
                d[i] = d[i/2] + 1;
                prev[i] = i/2;
            }
            if(i%3==0 && d[i] > d[i/3] + 1) {
                d[i] = d[i/3] + 1;
                prev[i] = i/3;
            }
        }

        System.out.println(d[n]);
        while(prev[n] > 0) {
            System.out.print(n + " ");
            n = prev[n];
        }
        System.out.print(1);

    }
}
