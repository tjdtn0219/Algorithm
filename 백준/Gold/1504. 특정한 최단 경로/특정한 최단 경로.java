import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Pair>[] graph;
    public static final int INF = 200000000;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();


        for(int i=0; i<m; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            int c = Integer.parseInt(strings[2]);

            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        strings = br.readLine().split(" ");
        int v1 = Integer.parseInt(strings[0]);
        int v2 = Integer.parseInt(strings[1]);

        long ans1 = func(1,v1) + func(v1,v2) + func(v2,n);
        long ans2 = func(1,v2) + func(v2,v1) + func(v1,n);

        long ans = Math.min(ans1, ans2);
        if(ans>=INF) ans = -1;
        System.out.println(ans);
    }

    public static int func(int st, int en) {
        int[] dis = new int[n+1];
        for(int i=1; i<=n; i++) dis[i] = INF;
        dis[st] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
        pq.add(new Pair(st, 0));
        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            if(dis[cur.node] != cur.cost) continue;
            for(Pair adj : graph[cur.node]) {
                if(dis[cur.node] + adj.cost < dis[adj.node]) {
                    dis[adj.node] = dis[cur.node] + adj.cost;
                    pq.add(new Pair(adj.node, dis[adj.node]));
                }
            }
        }
        return dis[en];
    }

}

class Pair{
    int node;
    int cost;

    public Pair(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}