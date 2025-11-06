import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 100 * 100000 + 5;

    static int n, m;
    static int[][] dis;
    static int[][] nxt;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        for(int k=1; k<=n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(i == j) continue;
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
                if(nxt[i][j] == 0) {
                    sb.append("0\n");
                    continue;
                }
                List<Integer> list = new ArrayList<>();
                int st = i;
                while(st != j) {
                    list.add(st);
                    st = nxt[st][j];
                }
                list.add(j);
                sb.append(list.size());
                for(int num : list) {
                    sb.append(" " + num);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());
            dis = new int[n+1][n+1];
            nxt = new int[n+1][n+1];
            for(int i=0; i<=n; i++) {
                Arrays.fill(dis[i], INF);
            }
            for(int i=0; i<m; i++) {
                String[] a_b_c = br.readLine().split(" ");
                int a = Integer.parseInt(a_b_c[0]);
                int b = Integer.parseInt(a_b_c[1]);
                int c = Integer.parseInt(a_b_c[2]);
                dis[a][b] = Math.min(dis[a][b], c);
                nxt[a][b] = b;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}
