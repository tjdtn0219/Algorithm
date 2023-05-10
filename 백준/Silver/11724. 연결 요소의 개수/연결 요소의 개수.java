import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static List<Integer> vList[];
    public static boolean[] vis;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = bf.readLine().split(" ");

        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        vis = new boolean[n+1];
        vList = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            vList[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<m; i++) {
            inputs = bf.readLine().split(" ");

            int s = Integer.parseInt(inputs[0]);
            int e = Integer.parseInt(inputs[1]);

            vList[s].add(e);
            vList[e].add(s);
        }

        int cnt = 0;
        for(int i=1; i<=n; i++) {
            if(!vis[i]) {
                cnt++;
                bfs(i);
            }
        }
        System.out.println(cnt);

    }

    public static void bfs(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);

        while(!queue.isEmpty()) {
            int popped = queue.poll();
            for(int adj : vList[popped]) {
                if(vis[adj]) continue;
                queue.add(adj);
                vis[adj] = true;
            }
        }
    }
}