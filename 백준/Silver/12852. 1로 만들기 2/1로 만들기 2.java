import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        int[][] d = new int[n+1][2];

        d[1][0] = 0;

        for(int i=2; i<=n; i++) {
            d[i][0] = d[i-1][0] + 1;
            d[i][1] = i-1;
            if(i%2==0 && d[i][0] > d[i/2][0] + 1) {
                d[i][0] = d[i/2][0] + 1;
                d[i][1] = i/2;
            }
            if(i%3==0 && d[i][0] > d[i/3][0] + 1) {
                d[i][0] = d[i/3][0] + 1;
                d[i][1] = i/3;
            }
        }

        System.out.println(d[n][0]);
        while(d[n][1] > 0) {
            System.out.print(n + " ");
            n = d[n][1];
        }
        System.out.print(1);

    }
}
