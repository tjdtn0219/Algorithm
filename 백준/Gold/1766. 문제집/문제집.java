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
        System.out.println(solve());
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            inDegree = new int[n+1];
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<m; i++) {
                //n1 ----> n2
                tmp = br.readLine().split(" ");
                int n1 = Integer.parseInt(tmp[0]);
                int n2 = Integer.parseInt(tmp[1]);
                graph.get(n1).add(n2);
                inDegree[n2]++;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public String solve() {
        //topologicalSort 위상 정렬
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=n; i++) {
            if(inDegree[i] == 0) {
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur + " ");
            for(int nxt : graph.get(cur)) {
                inDegree[nxt]--;
                if(inDegree[nxt] == 0) pq.add(nxt);
            }
        }

        return sb.toString();

    }

}