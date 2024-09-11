import java.io.*;

public class Main {

    int G;
    int P;
    int[] arr;
    int[] parents;

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
            G = Integer.parseInt(br.readLine());
            P = Integer.parseInt(br.readLine());
            arr = new int[P];
            for(int i=0; i<P; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            parents = new int[G+1];
            for(int i=0; i<=G; i++) {
                parents[i] = i;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int cnt = 0;
        for(int g : arr) {
            if(g == parents[g]) {
                union(g-1, g);
                cnt++;
            } else if(find(g) == 0) {
                break;
            } else {
                int root = find(g);
                union(root-1, g);
                cnt++;
            }

        }

        System.out.println(cnt);

    }

    public int find(int x) {
        if(parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    public void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u < v) {
            parents[v] = u;
        } else {
            parents[u] = v;
        }
    }
}