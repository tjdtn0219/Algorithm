import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 100_000_000;

    int T;
    int n, m, k;
    int s, g, h;
    List<List<Edge>> graph;
    List<Integer> destList;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        // solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++) {
                String[] n_m_k = br.readLine().split(" ");
                n = Integer.parseInt(n_m_k[0]);
                m = Integer.parseInt(n_m_k[1]);
                k = Integer.parseInt(n_m_k[2]);
                String[] s_g_h = br.readLine().split(" ");
                s = Integer.parseInt(s_g_h[0]);
                g = Integer.parseInt(s_g_h[1]);
                h = Integer.parseInt(s_g_h[2]);
                graph = new ArrayList<>();
                for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
                for(int i=0; i<m; i++) {
                    String[] a_b_c = br.readLine().split(" ");
                    int a = Integer.parseInt(a_b_c[0]);
                    int b = Integer.parseInt(a_b_c[1]);
                    int c;
                    if((a==g && b==h) || (a==h && b==g)) {
                        c = Integer.parseInt(a_b_c[2]) * 2 - 1;
                    } else {
                        c = Integer.parseInt(a_b_c[2]) * 2;
                    }
                    graph.get(a).add(new Edge(b, c));
                    graph.get(b).add(new Edge(a, c));
                }
                destList = new ArrayList<>();
                for(int i=0; i<k; i++) {
                    int x = Integer.parseInt(br.readLine());
                    destList.add(x);
                }
                solve();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int[] dist = dijkstra();
        List<Integer> ansList = new ArrayList<>();
        for(int dest : destList) {
            if(dist[dest] % 2 == 0) continue;
            ansList.add(dest);
        }

        Collections.sort(ansList);
        StringBuilder sb = new StringBuilder();
        for(int num : ansList) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);

    }

    public int[] dijkstra(){
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.c - o2.c);
        pq.add(new Edge(s, 0));
        dist[s] = 0;

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            // if(cur.c > dist[cur.b]) continue;

            if(check[cur.b]) continue;
            check[cur.b] = true;

            for(Edge nxt : graph.get(cur.b)){
                if(!check[nxt.b] && dist[nxt.b] > dist[cur.b] + nxt.c){
                    dist[nxt.b] = dist[cur.b] + nxt.c;
                    pq.add(new Edge(nxt.b, dist[nxt.b]));
                }
            }
            // for(Edge nxt : graph.get(cur.b)) {
            //     if(dist[nxt.b] > cur.c + nxt.c) {
            //         dist[nxt.b] = cur.c + nxt.c;
            //         pq.add(new Edge(nxt.b, dist[nxt.b]));
            //     }
            // }
        }

        return dist;
    }
}

class Edge {
    int b, c;
    public Edge(int b, int c) {
        this.b = b;
        this.c = c;
    }

}