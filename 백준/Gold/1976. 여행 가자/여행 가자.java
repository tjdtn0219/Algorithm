import java.io.*;
import java.util.*;

public class Main {

    int N, M;
    int[] path;
    int[] parent;

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
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            for(int i=0; i<=N; i++) parent[i] = i;
            for(int i=1; i<=N; i++) {
                String[] tmp = br.readLine().split(" ");
                for(int j=1; j<=N; j++) {
                    int num = Integer.parseInt(tmp[j-1]);
                    if(num == 1) {
                        union(i, j);
                    }
                }
            }
            path = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        boolean ans = true;
        for(int i=0; i<M-1; i++) {
            int st = path[i];
            int en = path[i+1];
            // System.out.println("st : " + i + ", " + isGroup(st, en));
            if(!isGroup(st, en)) {
                ans = false;
                break;
            }
        }
        if(ans) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public int find(int x) {
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    public void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u < v) {
            parent[v] = u;    
        } else {
            parent[u] = v;
        }
    }

    public boolean isGroup(int u, int v) {
        u = find(u);
        v = find(v);
        
        if(u == v) return true;
        else return false;
    }

}
