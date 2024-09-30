import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] inDegree;
    int[] times;
    List<List<Integer>> graph;
    int[] ans;

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
            times = new int[n+1];
            inDegree = new int[n+1];
            ans = new int[n+1];
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
            for(int i=1; i<=n; i++) {
                int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                times[i] = arr[0];
                for(int j=1; j<arr.length-1; j++) {
                    int pre = arr[j];
                    inDegree[i]++;
                    graph.get(pre).add(i);
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
            ans[i] = times[i];
            if(inDegree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                // System.out.println("cur: " + cur + ", ans[cur] :" + ans[cur]);
                for(int nxt : graph.get(cur)) {
                    inDegree[nxt]--;
                    ans[nxt] = Math.max(ans[nxt], ans[cur] + times[nxt]);
                    // System.out.println("nxt: " + nxt + ", ans[nxt]: " + ans[nxt]);
                    if(inDegree[nxt] == 0) {
                        q.add(nxt);
                    }
                }
            }
        }

        for(int i=1; i<=n; i++) {
            System.out.println(ans[i]);
        }
    }

}
