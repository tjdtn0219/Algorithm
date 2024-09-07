import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    int[] inDegree;
    List<List<Integer>> graph;

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
            n = Integer.parseInt(N_M[0]);
            m = Integer.parseInt(N_M[1]);
            inDegree = new int[n+1];
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<m; i++) {
                int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int size = arr[0];
                for(int j=1; j<size; j++) {
                    int a = arr[j];
                    int b = arr[j+1];
                    graph.get(a).add(b);
                    inDegree[b]++;
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append("\n");
            for(int nxt : graph.get(cur)) {
                inDegree[nxt]--;
                if(inDegree[nxt] == 0) {
                    q.add(nxt);
                }
            }
        }

        boolean flag = false;
        for(int i=1; i<=n; i++) {
            if(inDegree[i] > 0) {
                flag = true;
            }
        }
        if(flag) System.out.println(0);
        System.out.println(sb);
    }

}