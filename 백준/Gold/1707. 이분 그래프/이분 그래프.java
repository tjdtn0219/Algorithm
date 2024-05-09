import java.io.*;
import java.util.*;

public class Main {

    static final int RED = 1;
    static final int BLUE = 2;

    int K;
    int n, e;
    List<List<Integer>> graph;
    int[] colored;
    boolean answer;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            K = Integer.parseInt(br.readLine());
            for(int k=0; k<K; k++) {
                String[] tmp = br.readLine().split(" ");
                n = Integer.parseInt(tmp[0]);
                e = Integer.parseInt(tmp[1]);
                initGraph(n);
                for(int i=0; i<e; i++) {
                    tmp = br.readLine().split(" ");
                    int u = Integer.parseInt(tmp[0]);
                    int v = Integer.parseInt(tmp[1]);
                    graph.get(u).add(v);
                    graph.get(v).add(u);
                }
                colored = new int[n+1];
                answer = true;
                solve();
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void initGraph(int n) {
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void solve() {
        for(int node=1; node<=n; node++) {
            if(colored[node] == 0) {
                dfs(node, RED);
            }
        }
        if(answer) System.out.println("YES");
        else System.out.println("NO");
    }

    public void dfs(int node, int color) {
        colored[node] = color;

        for(int adj : graph.get(node)) {
            if(colored[adj] == color) {
                answer = false;
            }

            if(colored[adj] == 0 && color == RED) {
                dfs(adj, BLUE);
            }

            if(colored[adj] == 0 && color == BLUE) {
                dfs(adj, RED);
            }
        }
    }

}