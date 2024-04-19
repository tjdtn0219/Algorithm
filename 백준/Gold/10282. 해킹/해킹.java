import java.io.*;
import java.util.*;

public class Main {

    int t;
    int n, d, c;
    HashMap<Integer, List<Node>> inGraph;
    int[] dis;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                inGraph = new HashMap<>();
                String[] tmp = br.readLine().split(" ");
                n = Integer.parseInt(tmp[0]);
                d = Integer.parseInt(tmp[1]);
                c = Integer.parseInt(tmp[2]);
                dis = new int[n+1];
                Arrays.fill(dis, Integer.MAX_VALUE);
                for(int i=0; i<d; i++) {
                    tmp = br.readLine().split(" ");
                    int u = Integer.parseInt(tmp[0]);
                    int v = Integer.parseInt(tmp[1]);
                    int t = Integer.parseInt(tmp[2]);
                    List<Node> list = inGraph.getOrDefault(v, new ArrayList<>());
                    list.add(new Node(u, t));
                    inGraph.put(v, list);
                }
                solve();
            }
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void solve() {
        int size = dijkstra();
        int maxTime = 0;
        for(int time : dis) {
            if(time == Integer.MAX_VALUE) continue;
            maxTime = Math.max(maxTime, time);
        }
        System.out.println(size + " " + maxTime);
    }

    public int dijkstra() {
        HashSet<Integer> virusComputerSet = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        virusComputerSet.add(c);
        pq.add(new Node(c, 0));
        dis[c] = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.cost > dis[cur.computer]) continue;
            for(Node adj : inGraph.getOrDefault(cur.computer, new ArrayList<>())) {
                virusComputerSet.add(adj.computer);
                if(dis[adj.computer] > dis[cur.computer] + adj.cost) {
                    dis[adj.computer] = dis[cur.computer] + adj.cost;
                    pq.add(new Node(adj.computer, dis[adj.computer]));
                }
            }
        }
        return virusComputerSet.size();
    }
}

class Node {
    int computer;
    int cost;
    public Node(int computer, int cost) {
        this.computer = computer;
        this.cost = cost;
    }
}
