import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    List<List<Integer>> graph;
    int[] inDegree;

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
                int u = Integer.parseInt(tmp[0]);
                int v = Integer.parseInt(tmp[1]);
                graph.get(u).add(v);
                inDegree[v]++;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        List<Integer> list = new ArrayList<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=n; i++) {
            if(inDegree[i] == 0) {
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            list.add(cur);
            for(int nxt : graph.get(cur)) {
                inDegree[nxt]--;
                if(inDegree[nxt] == 0) {
                    pq.add(nxt);
                }
            }
        }

        printAnswer(list);
    }

    private static void printAnswer(List<Integer> answerList) {
        StringBuilder sb = new StringBuilder();
        for(int num : answerList) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }
}
