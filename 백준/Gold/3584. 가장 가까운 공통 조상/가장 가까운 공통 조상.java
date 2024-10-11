import java.io.*;
import java.util.*;

public class Main {

    int T;
    int n;
    int[] parents;
    List<List<Integer>> graph;
    int[] nodes;
    int[] depths;
    int root;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        // solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++) {
                n = Integer.parseInt(br.readLine());
                parents = new int[n+1];
                depths = new int[n+1];
                graph = new ArrayList<>();
                for(int i=0; i<=n; i++) {
                    graph.add(new ArrayList<>());
                }
                for(int i=0; i<n-1; i++) {
                    String[] u_v = br.readLine().split(" ");
                    int u = Integer.parseInt(u_v[0]);
                    int v = Integer.parseInt(u_v[1]);
                    parents[v] = u;
                    graph.get(u).add(v);
                }
                nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                solve();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        findRoot();
        findDepths();
        int[] newNodes = makeSameDepth();
        System.out.println(getCommonParent(newNodes));
    }

    public int getCommonParent(int[] newNodes) {
        int n1 = newNodes[0];
        int n2 = newNodes[1];

        while(n1 != n2) {
            n1 = parents[n1];
            n2 = parents[n2];
        }

        return n1;
    }

    public int[] makeSameDepth() {
        int n1 = nodes[0];
        int n2 = nodes[1];
        int diff = Math.abs(depths[n1] - depths[n2]);
        
        if(diff > 0 && depths[n1] > depths[n2]) {
            n1 = getParent(n1, diff);
        } else if(diff > 0 && depths[n1] < depths[n2]) {
            n2 = getParent(n2, diff);
        }

        int[] res = new int[2];
        res[0] = n1;
        res[1] = n2;
        return res;
    }

    public int getParent(int node, int cnt) {
        for(int i=0; i<cnt; i++) {
            node = parents[node];
        }
        return node;
    }

    public void findDepths() {
        int dep = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                for(int nxt : graph.get(cur)) {
                    depths[nxt] = dep;
                    q.add(nxt);
                }
            }
            dep++;
        }

        // for(int i=1; i<=n; i++) {
        //     System.out.println("dep of " + i + " : " + depths[i]);
        // }
    }

    public void findRoot() {
        for(int i=1; i<=n; i++) {
            if(parents[i] == 0) {
                root = i;
            }
        }
    }

}