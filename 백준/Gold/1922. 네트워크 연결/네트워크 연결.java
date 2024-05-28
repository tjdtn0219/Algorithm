import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    List<Node> nodeList;
    int[] parent;


    public static void main(String[] args) throws IOException {

        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());
            nodeList = new ArrayList<>();
            parent = new int[n+1];
            for(int i=0; i<=n; i++) {
                parent[i] = i;
            }
            for(int i=0; i<m; i++) {
                String[] tmp = br.readLine().split(" ");
                int a = Integer.parseInt(tmp[0]);
                int b = Integer.parseInt(tmp[1]);
                int c = Integer.parseInt(tmp[2]);
                nodeList.add(new Node(a, b, c));
            }
            Collections.sort(nodeList, (o1, o2) -> {
                return o1.c - o2.c;
            });
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int ans = 0;
        for(Node node : nodeList) {
//            System.out.println(node.a + " " + node.b + " " + node.c);
            int a = node.a;
            int b = node.b;
            int c = node.c;
            if(isDiff(a, b)) {
                union(a, b);
                ans += c;
            }
        }
        System.out.println(ans);
    }

    public int find(int x) {
        if(x == parent[x]) return x;
        return find(parent[x]);
    }

    public void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u < v) parent[v] = u;
        else parent[u] = v;
    }

    public boolean isDiff(int u, int v) {
        u = find(u);
        v = find(v);

        if(u == v) return false;
        else return true;
    }

}

class Node{
    int a;
    int b;
    int c;
    public Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}