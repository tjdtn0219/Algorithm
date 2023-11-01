import java.util.*;
import java.io.*;

public class Main {

    public static final int INF = 10000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dis = new int[n][n];
        for(int i=0; i<n; i++) Arrays.fill(dis[i], INF);
        for(int i=0; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                int val = Integer.parseInt(strings[j]);
                if(val==1) dis[i][j] = val;
            }
        }

//        for(int i=0; i<n; i++) {
//            for(int j=0; j<n; j++) {
//                System.out.print(dis[i][j] + " ");
//            }
//            System.out.println();
//        }

        for(int k=0; k<n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(dis[i][j] > dis[i][k] + dis[k][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(dis[i][j]>=INF) sb.append(0 + " ");
                else sb.append(1 + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}