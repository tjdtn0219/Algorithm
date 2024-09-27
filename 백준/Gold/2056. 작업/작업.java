import java.io.*;
import java.util.*;

public class Main {

    int n;
    List<List<Integer>> graph;
    int[] timeArr;
    int[] inDegree;
    int[] result;

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
            timeArr = new int[n+1];
            graph = new ArrayList<>();
            inDegree = new int[n+1];
            result = new int[n+1];
            for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
            for(int i=1; i<=n; i++) {
                int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
                timeArr[i] = arr[0];
                for(int j=0; j<arr[1]; j++) {
                    int pre = arr[2+j];
                    graph.get(pre).add(i);
                    inDegree[i]++;
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
            result[i] = timeArr[i];
            if(inDegree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                for(int nxt : graph.get(cur)) {
                    inDegree[nxt]--;
                    result[nxt] = Math.max(result[nxt], result[cur] + timeArr[nxt]);
                    if(inDegree[nxt] == 0) {
                        q.add(nxt);
                    }
                }
            }
        }

        int ans = 0;
        for(int i=1; i<=n; i++) {
            ans = Math.max(ans, result[i]);
        }
        System.out.println(ans);
    }

}