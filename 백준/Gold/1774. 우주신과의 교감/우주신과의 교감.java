import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    int n, m;
    List<Point> pointList;
    List<Edge> edgeList;
    List<Edge> startEdgeList;
    int[] comb;
    int[] parents;
    List<List<Integer>> graph;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
            String[] n_m = br.readLine().split(" ");
            n = Integer.parseInt(n_m[0]);
            m = Integer.parseInt(n_m[1]);
            pointList = new ArrayList<>();
            edgeList = new ArrayList<>();
            startEdgeList = new ArrayList<>();
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
            for(int i=0; i<n; i++) {
                String[] x_y = br.readLine().split(" ");
                int x = Integer.parseInt(x_y[0]);
                int y = Integer.parseInt(x_y[1]);
                pointList.add(new Point(x, y));
            }
            for(int i=0; i<m; i++) {
                String[] u_v = br.readLine().split(" ");
                int u = Integer.parseInt(u_v[0]);
                int v = Integer.parseInt(u_v[1]);
                double dis = getDis(u, v);
                startEdgeList.add(new Edge(u, v, dis));
            }
            comb = new int[2];
            parents = new int[n+1];
            for(int i=0; i<=n; i++) {
                parents[i] = i;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public double getDis(int i1, int i2) {
        Point p1 = pointList.get(i1-1);
        Point p2 = pointList.get(i2-1);
        double dis = Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
        return dis;
    }

    public void solve() {
        makeComb(0, 1);
        double ans = mst();
        System.out.println(String.format("%.2f", ans));

    }

    public double mst() {
        Collections.sort(edgeList, (o1, o2) -> {
            if(o1.dis < o2.dis) return -1;
            else return 1;
        });

        for(Edge edge : startEdgeList) {
            union(edge.u, edge.v);
        }

        double res = 0.0;
        for(Edge edge : edgeList) {
            // System.out.println("u, v : " + edge.u + ", " + edge.v + ", dis : " + edge.dis);
            if(!isUnion(edge.u, edge.v)) {
                union(edge.u, edge.v);
                // System.out.println("union : u, v : " + edge.u + ", " + edge.v);
                res += edge.dis;
            }
        }

        return res;

    }

    public int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
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

    public boolean isUnion(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }

    public void makeComb(int k, int li) {
        if(k == 2) {
            double dis = getDis(comb[0], comb[1]);
            edgeList.add(new Edge(comb[0], comb[1], dis));
            // System.out.println("u, v : " + comb[0] + ", " + comb[1] + ", dis : " + dis);
            return ;
        }

        for(int i=li; i<=n; i++) {
            comb[k] = i;
            makeComb(k+1, i+1);
        }
    }
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Edge {
    int u, v;
    double dis;
    public Edge(int u, int v, double dis) {
        this.u = u;
        this.v = v;
        this.dis = dis;
    }
}