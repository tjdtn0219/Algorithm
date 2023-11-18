import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 100 * 100000 + 5;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dis = new int[n+1][n+1];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dis[i], INF);
        }

        for(int i=1; i<=n; i++) {
            dis[i][i] = 0;
        }

        int[][] nxt = new int[n+1][n+1];
        for(int i=0; i<m; i++) {
            String[] strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            int c = Integer.parseInt(strings[2]);
            dis[a][b] = Math.min(dis[a][b], c);
            nxt[a][b] = b;
        }

        for(int k=1; k<=n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(dis[i][j] > dis[i][k] + dis[k][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                        nxt[i][j] = nxt[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                sb.append(dis[i][j]==INF ? 0 : dis[i][j]).append(" ");
            }
            sb.append("\n");
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
//                if(dis[i][j]==0 || dis[i][j]==INF) {
//                    sb.append("0\n");
//                    continue;
//                }
                if(nxt[i][j]==0) {
                    sb.append("0\n");
                    continue;
                }
                ArrayList<Integer> list = new ArrayList<>();
                int st = i;
                while(st!=j) {
                    list.add(st);
                    st = nxt[st][j];
                }
                list.add(j);
                sb.append(list.size() + " ");
                for(Integer num : list)
                    sb.append(num + " ");
                sb.append("\n");
            }

        }

        System.out.print(sb.toString());

    }
}
