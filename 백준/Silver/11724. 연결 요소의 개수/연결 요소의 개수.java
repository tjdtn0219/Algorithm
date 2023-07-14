import java.util.*;
import java.io.*;

public class Main {

    public static List<Integer>[] vList;
    public static Queue<Integer> q = new LinkedList<>();
    public static boolean[] vis;

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        vis = new boolean[n+1];
        vList = new ArrayList[n+1];
        for(int i=1; i<n+1; i++) {
            vList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            strings = bf.readLine().split(" ");
            int v1 = Integer.parseInt(strings[0]);
            int v2 = Integer.parseInt(strings[1]);
            vList[v1].add(v2);
            vList[v2].add(v1);
        }

        int ans = 0;

        for(int i=1; i<=n; i++) {
            if (!vis[i]) {
                q.add(i);
                vis[i] = true;
                bfs();
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static void bfs() {

        while(!q.isEmpty()) {
            int popped = q.poll();
            for(Integer adj : vList[popped]) {
                if(vis[adj]) continue;
                q.add(adj);
                vis[adj] = true;
            }
        }
    }

}
