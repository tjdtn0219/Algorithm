import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static List<Integer> adj[];
    public static int[] deg;
    public static List<Integer> result = new ArrayList<>();
    public static int n,m;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = bf.readLine().split(" ");
        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        adj = new ArrayList[n+1];
        deg = new int[n+1];

        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            strings = bf.readLine().split(" ");
            int s1 = Integer.parseInt(strings[0]);
            int s2 = Integer.parseInt(strings[1]);
            adj[s1].add(s2);
            deg[s2]++;
        }


        order();

        if(result.size() != n) System.out.println("Cycle Exists");
        else {
            for(int n : result) System.out.print(n + " ");
        }

    }
    public static void order() {
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(deg[i]==0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int popped = queue.poll();
            result.add(popped);
            for(int nxt : adj[popped]) {
                deg[nxt]--;
                if(deg[nxt]==0) queue.add(nxt);
            }
        }
    }
}