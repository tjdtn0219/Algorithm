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

        int n = Integer.parseInt(inputs[0]);    //노드의 개수
        int m = Integer.parseInt(inputs[1]);    //간선의 개수
        int v = Integer.parseInt(inputs[2]);    //시작하는 노드

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
        for(int i=1; i<=n; i++) {
            Collections.sort(vList[i]);
        }

        System.out.println(dfs(v));
        for(int i=1; i<vis.length; i++) vis[i] = false;
        System.out.println(bfs(v));

    }

    public static String bfs(int num) {
        //일반적인 bfs 탐색
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);
        vis[num] = true;

        while(!queue.isEmpty()) {
            int popped = queue.poll();
            sb.append(popped + " ");
            for(int adj : vList[popped]) {
                if(vis[adj]) continue;
                queue.add(adj);
                vis[adj] = true;
            }
        }
        return sb.toString();
    }

    public static String dfs(int num) {
        //일반적인 dfs 탐색
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.add(num);

        while(!stack.isEmpty()) {
            int popped = stack.pop();
            if(vis[popped]) continue;
            sb.append(popped + " ");
            vis[popped] = true;
            int len  = vList[popped].size();
            for(int i=len-1; i>=0; i--) {
                if(vis[vList[popped].get(i)]) continue;
                stack.add(vList[popped].get(i));
            }
        }
        return sb.toString();
    }
}