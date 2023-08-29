import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 1000000001;
    public static ArrayList<Pair>[] graph;
    public static int[] dis;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       int n = Integer.parseInt(br.readLine());
       int m = Integer.parseInt(br.readLine());

       graph = new ArrayList[n+1];
       for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
       dis = new int[n+1];
       for(int i=1; i<=n; i++) dis[i] = INF;

       for(int i=0; i<m; i++) {
           String[] strings = br.readLine().split(" ");

           int a = Integer.parseInt(strings[0]);
           int b = Integer.parseInt(strings[1]);
           int c = Integer.parseInt(strings[2]);

           graph[a].add(new Pair(b, c));
       }

       String[] strings = br.readLine().split(" ");
       int st = Integer.parseInt(strings[0]);
       int en = Integer.parseInt(strings[1]);

       PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)->o1.cost-o2.cost);
       pq.add(new Pair(st, 0));
       dis[st] = 0;

       while(!pq.isEmpty()) {
           Pair polled = pq.poll();
           if(dis[polled.dest] != polled.cost) continue;
           for(Pair adj : graph[polled.dest]) {
               if(dis[adj.dest] > dis[polled.dest] + adj.cost) {
                   dis[adj.dest] = dis[polled.dest] + adj.cost;
                   pq.add(new Pair(adj.dest, dis[adj.dest]));
               }
           }
       }

       System.out.println(dis[en]);

    }
}

class Pair {
    int dest;
    int cost;

    public Pair(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}