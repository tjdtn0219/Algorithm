import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static ArrayList<Integer>[] vList;
    public static boolean[] vis;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());        //컴퓨터 수
        int m = Integer.parseInt(br.readLine());        //연결 간선 개수

        vList = new ArrayList[n+1];
        for(int i=1; i<n+1; i++) vList[i] = new ArrayList<>();
        vis = new boolean[n+1];

        for(int i=0; i<m; i++) {
            String[] strings = br.readLine().split(" ");
            int u = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);

            vList[u].add(v);
            vList[v].add(u);
        }

        System.out.println(bfs(1) - 1);

    }

    public static int bfs(int i) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        vis[i] = true;
        int cnt = 0;

        while(!q.isEmpty()) {
            int popped = q.poll();
            cnt++;
            for(int adj : vList[popped]) {
                if(vis[adj]) continue;
                q.add(adj);
                vis[adj] = true;
            }
        }
        return cnt;
    }

    public static int dfs(int i) {
        Stack<Integer> stk = new Stack<>();
        stk.add(i);
        int cnt = 0;

        while(!stk.isEmpty()) {
            int popped = stk.pop();
            if(vis[popped]) continue;
            vis[popped] = true;
            cnt++;
            for(int adj : vList[popped]) {
                if(vis[adj]) continue;
                stk.add(adj);
            }
        }

        return cnt;
    }
}
