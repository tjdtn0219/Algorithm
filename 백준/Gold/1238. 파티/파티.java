import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 10000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int x = Integer.parseInt(strings[2]);

        int[][] d = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i==j) continue;
                d[i][j] = INF;
            }
        }

        for(int i=0; i<m; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            int c = Integer.parseInt(strings[2]);

            d[a][b] = c;
        }

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                if(i==k) continue;
                for(int j=1; j<=n; j++) {
                    if(i==j || j==k) continue;
                    if(d[i][k] + d[k][j] < d[i][j])
                        d[i][j] = d[i][k] + d[k][j];
                }
            }
        }

        int max = 0;
        for(int i=1; i<=n; i++) {
            max = Math.max(max, d[i][x]+d[x][i]);
        }

        System.out.println(max);

    }
}
