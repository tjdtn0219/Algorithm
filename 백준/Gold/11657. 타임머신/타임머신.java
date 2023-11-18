import java.io.*;
import java.util.*;

public class Main {

//    public static final int INF = 500 * 100000;
    public static final int INF = Integer.MAX_VALUE;
//    public static List<List<Pair>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        List<Edge> list = new ArrayList<>();

//        int[] dis = new int[n + 1];
        long[] dis = new long[n + 1];
        Arrays.fill(dis, INF);

        for (int i = 0; i < m; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            int c = Integer.parseInt(strings[2]);
            list.add(new Edge(a,b,c));
        }

        dis[1] = 0;

        for(int i=0; i<n-1; i++) {
            for(int j=0; j<m; j++) {
                Edge edge = list.get(j);
                if(dis[edge.a] != INF && dis[edge.b] > dis[edge.a] + edge.c) {
                    dis[edge.b] = dis[edge.a] + edge.c;
                }
            }
        }

        boolean isCycle = false;

        for(int i=0; i<m; i++) {
            Edge edge = list.get(i);
            if(dis[edge.a] != INF && dis[edge.b] > dis[edge.a] + edge.c) {
                isCycle = true;
                break;
            }
        }

        if(isCycle) {
            System.out.println(-1);
            return ;
        } else {
            StringBuilder sb = new StringBuilder();
            for(int i=2; i<=n; i++) {
                if(dis[i] == INF) sb.append(-1).append("\n");
                else sb.append(dis[i]).append("\n");
            }
            System.out.println(sb);
        }

    }

}

class Edge {
    int a;
    int b;
    int c;
    public Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
