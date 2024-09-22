import java.io.*;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    int n;
    int[] parents;
    List<List<Integer>> graph;
    int deleted;

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
            n = Integer.parseInt(br.readLine());
            parents = new int[n];
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph = new ArrayList<>();
            for(int i=0; i<n; i++) graph.add(new ArrayList<>());

            for(int i=0; i<n; i++) {
                int parent = arr[i];
                parents[i] = parent;
                if(parent >= 0) {
                    graph.get(parent).add(i);
                }
            }
            deleted = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int parent = parents[deleted];
        if(parent >= 0) {
            int root = getRoot(deleted);
            // System.out.println("root : " + root);
            System.out.println(bfs(root));
        } else {
            System.out.println(0);
        }
    }

    public int bfs(int root) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            boolean flag = false;
            for(int child : graph.get(cur)) {
                if(child == deleted) continue;
                q.add(child);
                flag = true;
            }
            if(!flag) cnt++;
        }
        // System.out.println("cnt : " + cnt);
        return cnt;
    }

    public int getRoot(int x) {
        if(parents[x] == -1) return x;
        return getRoot(parents[x]);
    }
}