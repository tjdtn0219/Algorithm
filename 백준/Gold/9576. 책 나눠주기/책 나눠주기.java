import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 1_005;

    int T;
    int n, m;
    int[][] arr;
    int[] parents;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        // solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++ ){
                String[] n_m = br.readLine().split(" ");
                n = Integer.parseInt(n_m[0]);
                m = Integer.parseInt(n_m[1]);
                arr = new int[m][2];
                parents = new int[MAX];
                for(int i=0; i<MAX; i++) {
                    parents[i] = i;
                }
                for(int i=0; i<m; i++) {
                    String[] a_b = br.readLine().split(" ");
                    int a = Integer.parseInt(a_b[0]);
                    int b = Integer.parseInt(a_b[1]);
                    arr[i][0] = a;
                    arr[i][1] = b;
                }
                solve();

            }
            
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {

        Arrays.sort(arr, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        // printArr();


        boolean[] isRent = new boolean[n+1];
        int cnt = 0;
        for(int[] ab : arr) {
            int a = ab[0];
            int b = ab[1];
            for(int i=a; i<=b; i++) {
                if(isRent[i]) continue;
                isRent[i] = true;
                cnt++;
                break;
            }
            
        }
        System.out.println(cnt);
    }

    private void printArr() {
        for(int[] tmp : arr) {
            System.out.println("a , b : " + tmp[0] + ", " + tmp[1]);
        }
    }

    public int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public void union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u < v) {
            parents[u] = v;
        } else {
            parents[v] = u;
        }
    }
}