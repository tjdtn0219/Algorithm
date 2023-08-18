import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Pair>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
        int[] dis = new int[n+1];
        for(int i=1; i<=n; i++) dis[i] = Integer.MAX_VALUE;
        int[] pre = new int[n+1];

        String[] strings;
        for(int i=0; i<m; i++) {
            strings = br.readLine().split(" ");
            int st = Integer.parseInt(strings[0]);
            int en = Integer.parseInt(strings[1]);
            int cost = Integer.parseInt(strings[2]);

            graph[st].add(new Pair(en, cost));
        }
        strings = br.readLine().split(" ");
        int st = Integer.parseInt(strings[0]);
        int en = Integer.parseInt(strings[1]);

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2)->o1.cost - o2.cost);
        dis[st] = 0;
        pq.add(new Pair(st, dis[st]));

        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            if(dis[cur.dest] != cur.cost) continue;
            for(Pair adj : graph[cur.dest]) {
                if(dis[cur.dest] + adj.cost < dis[adj.dest]) {
                    dis[adj.dest] = dis[cur.dest] + adj.cost;
                    pre[adj.dest] = cur.dest;
                    pq.add(new Pair(adj.dest, dis[adj.dest]));
                }
            }
        }

        LinkedList<Integer> path = new LinkedList<>();
        int cur = en;
        while(cur != st) {
            path.addFirst(cur);
            cur = pre[cur];
        }
        path.addFirst(cur);

        StringBuilder sb = new StringBuilder();
        sb.append(dis[en] + "\n");
        sb.append(path.size() + "\n");
        for(int v : path) sb.append(v + " ");

        System.out.println(sb);
    }
}

class Pair {
    int dest;
    int cost;

    Pair(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}