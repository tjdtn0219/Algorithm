import java.io.*;
import java.util.*;

public class Main {

    int N;
    List<List<Node>> graph;
    Node maxNode;

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
            N = Integer.parseInt(br.readLine());
            graph = new ArrayList<>();
            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=1; i<=N; i++) {
                int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int a = arr[0];
                for(int j=1; j<arr.length && arr[j] > -1; j+=2) {
                    int b = arr[j];
                    int c = arr[j+1];
                    graph.get(a).add(new Node(b, c));
                }
            }
            maxNode = new Node(0, 0);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }


    public void solve() {
        int st = 1;
        dfs(st, new boolean[N+1], 0);
        // System.out.println("b, c : " + maxNode.b + ", " + maxNode.c);
        st = maxNode.b;
        maxNode = new Node(0, 0);
        dfs(st, new boolean[N+1], 0);
        System.out.println(maxNode.c);
    }

    public void dfs(int cur, boolean[] vis, int sum) {
        if(sum > maxNode.c) {
            maxNode.c = sum;
            maxNode.b = cur;
        }

        vis[cur] = true;
        for(Node adj : graph.get(cur)) {
            if(vis[adj.b]) continue;
            dfs(adj.b, vis, sum + adj.c);
        }
    }

}

class Node {
    int b;
    int c;
    public Node(int b, int c) {
        this.b = b;
        this.c = c;
    }
}


