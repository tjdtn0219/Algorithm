import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int idx;
        int x, y, z;
        public Point(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge {
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    static int n;
    static int[] parents;
    static List<Point> pointList;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {

//        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        //x좌표 순서대로 오름차순 정렬 후 작은 값부터 q에 edge정보 저장.
        Collections.sort(pointList, (o1, o2) -> o1.x - o2.x);
        for(int i = 0; i < n - 1; i++) {
            pq.offer(new Edge(pointList.get(i).idx, pointList.get(i+1).idx, Math.abs(pointList.get(i).x - pointList.get(i+1).x)));
        }
//        //y좌표 순서대로 오름차순 정렬 후 작은 값부터 q에 edge정보 저장.
        Collections.sort(pointList, (o1, o2) -> o1.y - o2.y);
        for(int i = 0; i < n - 1; i++) {
            pq.offer(new Edge(pointList.get(i).idx, pointList.get(i+1).idx, Math.abs(pointList.get(i).y - pointList.get(i+1).y)));
        }
//        //z좌표 순서대로 오름차순 정렬 후 작은 값부터 q에 edge정보 저장.
        Collections.sort(pointList, (o1, o2) -> o1.z - o2.z);
        for(int i = 0; i < n - 1; i++) {
            pq.offer(new Edge(pointList.get(i).idx, pointList.get(i+1).idx, Math.abs(pointList.get(i).z - pointList.get(i+1).z)));
        }

        int ans = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = find(edge.u);
            int v = find(edge.v);

            if(u != v) {
//                System.out.println(u + " -- " + v + ", cost : " + edge.cost);
                union(u, v);
                ans += edge.cost;
            }
        }

        System.out.println(ans);
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u < v) {
            parents[v] = u;
        } else {
            parents[u] = v;
        }
    }

    public static int find(int x) {
        if(parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            pointList = new ArrayList<>();
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                int x = Integer.parseInt(tmp[0]);
                int y = Integer.parseInt(tmp[1]);
                int z = Integer.parseInt(tmp[2]);
                pointList.add(new Point(i, x, y, z));
            }
            parents = new int[n];
            for(int i=0; i<n; i++) {
                parents[i] = i;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}