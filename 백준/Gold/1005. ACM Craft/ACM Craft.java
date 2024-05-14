import java.io.*;
import java.util.*;

public class Main {

    int n, k;
    int[] times;
    int[] inDegree;
    List<List<Integer>> graph;
    int target;
    int[] dis;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++) {
                String[] tmp = br.readLine().split(" ");
                n = Integer.parseInt(tmp[0]);
                k = Integer.parseInt(tmp[1]);
                times = new int[n+1];
                inDegree = new int[n+1];
                graph = new ArrayList<>();
                for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
                tmp = br.readLine().split(" ");
                for(int i=0; i<n; i++) {
                    times[i+1] = Integer.parseInt(tmp[i]);
                }
                for(int i=0; i<k; i++) {
                    tmp = br.readLine().split(" ");
                    int u = Integer.parseInt(tmp[0]);
                    int v = Integer.parseInt(tmp[1]);
                    inDegree[v]++;
                    graph.get(u).add(v);
                }
                target = Integer.parseInt(br.readLine());
                dis = new int[n+1];
//                Arrays.fill(dis, Integer.MAX_VALUE);
                solve();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        bfs();
        System.out.println(dis[target]);
    }

    public void bfs() {
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(inDegree[i] == 0) {
                dis[i] = times[i];
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                for(int nxt : graph.get(cur)) {
                    if(dis[cur] + times[nxt] > dis[nxt]) {
                        dis[nxt] = dis[cur] + times[nxt];
                    }
                    inDegree[nxt]--;
                    if(inDegree[nxt] == 0) {
                        if(nxt == target) return;
                        q.add(nxt);
                    }
                }
            }
        }
    }

}