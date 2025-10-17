import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[] inDegree;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        boolean[] vis = new boolean[n+1];
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) ->o1 - o2);
        for(int i=1; i<=n; i++) {
            if(inDegree[i] == 0) {
                pq.add(i);
            }
        }


        List<Integer> ansList = new ArrayList<>();
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            if(vis[cur]) continue;
            vis[cur] = true;
            ansList.add(cur);
            for(int nxt : graph.get(cur)) {
                inDegree[nxt]--;
                if(inDegree[nxt] == 0) {
                    pq.add(nxt);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int num : ansList) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            inDegree = new int[n+1];
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<m; i++) {
                tmp = br.readLine().split(" ");
                int a = Integer.parseInt(tmp[0]);
                int b = Integer.parseInt(tmp[1]);
                graph.get(a).add(b);
                inDegree[b]++;
            }
        } catch(Exception e) {
            throw new RuntimeException("INPUT ERROR");
        }
    }
}