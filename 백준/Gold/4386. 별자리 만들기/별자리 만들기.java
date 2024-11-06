import java.io.*;
import java.util.*;

public class Main {

    int n;
    Point[] points;
    List<Edge> edgeList;
    int[] parents;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        initEdgeList();
        solve();
    }

    public void initEdgeList() {
        edgeList = new ArrayList<>();
        for(int i=0; i<n-1; i++) {
            for(int j=i+1; j<n; j++) {
                double cost = getDis(i, j);
                edgeList.add(new Edge(i, j, cost));
            }
        }
    }

    public double getDis(int i, int j) {
        Point p1 = points[i];
        Point p2 = points[j];
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            points = new Point[n];
            for(int i=0; i<n; i++) {
                String[] x_y = br.readLine().split(" ");
                double x = Double.parseDouble(x_y[0]);
                double y = Double.parseDouble(x_y[1]);
                points[i] = new Point(x, y);
            }
            parents = new int[n];
            for(int i=0; i<n; i++) {
                parents[i] = i;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.c > o2.c) {
                return 1;
            } else if(o1.c < o2.c) {
                return -1;
            } else {
                return 0;
            }
        });

        for(Edge edge : edgeList) {
            pq.add(edge);
        }
        
        double total = 0.0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(find(cur.a) == find(cur.b)) continue;
            total += cur.c;
            union(cur.a, cur.b);
        }

        System.out.println(total);
    }

    public int find(int x) {
        if(x == parents[x]) return x;
        else return parents[x] = find(parents[x]);
    }

    public void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u < v) {
            parents[v] = u;
        } else {
            parents[u] = v;
        }
    }

}

class Edge {
    int a;
    int b;
    double c;
    public Edge(int a, int b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

class Point {
    double x;
    double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}