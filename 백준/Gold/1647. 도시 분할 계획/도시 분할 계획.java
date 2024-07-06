import java.io.*;
import java.util.*;

public class Main {

    int N, M;
    List<Node> pathList;
    int[] parents;

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
            String[] N_M = br.readLine().split(" ");
            N = Integer.parseInt(N_M[0]);
            M = Integer.parseInt(N_M[1]);

            pathList = new ArrayList<>();

            for(int i=0; i<M; i++) {
                int[] A_B_C = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int A = A_B_C[0];
                int B = A_B_C[1];
                int C = A_B_C[2];
                // System.out.println(A + " " + B + " " + C);
                pathList.add(new Node(A, B, C));
            }

            Collections.sort(pathList, (o1, o2) -> o1.c - o2.c);

            parents = new int[N+1];
            for(int i=0; i<=N; i++) {
                parents[i] = i;
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int ans = 0;
        int maxCost = 0;
        for(Node node : pathList) {
            // System.out.println(node.a + " " + node.b + " " + node.c);
            int u = node.a;
            int v = node.b;
            if(find(u) != find(v)) {
                // System.out.println("find[u] : " + find(u) + ", find[v] : " + find(v));
                // System.out.println("union : " + u + ", " + v);
                union(u, v);
                ans += node.c;
                maxCost = Math.max(maxCost, node.c);
            }
        }
        // System.out.println(ans + ", " + maxCost);
        ans -= maxCost;
        System.out.println(ans);
    }


    public int find(int x) {
        if(parents[x] == x) return x;
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

class Node {

    int a;
    int b;
    int c;

    public Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}