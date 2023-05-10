import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int MAX = 100002;
    public static List<Integer> tree[] = new ArrayList[MAX];
    public static int parent[] = new int[MAX];

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        for(int i=1; i<MAX; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n-1; i++) {
            String[] strings = bf.readLine().split(" ");
            int u = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);

            tree[u].add(v);
            tree[v].add(u);

        }
        dfs(1);
        for(int i=2; i<=n; i++) {
            System.out.println(parent[i]);
        }
    }

    public static void dfs(int cur) {
        for(int nxt : tree[cur]) {
            if(parent[cur] == nxt) continue;
            parent[nxt] = cur;
            dfs(nxt);
        }
    }
}