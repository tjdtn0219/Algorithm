import java.io.*;
import java.util.*;

public class Main{

    static final int MAX = 200_005;

    BufferedReader br;
    int T;
    int N;
    HashMap<String, Integer> friendMap;
    int[] parents;
    int[] sizes;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        inputT();
        for(int t=0; t<T; t++) {
            input();
            solve();
        }   
    }

    public void inputT() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void input() {
        try {
            N = Integer.parseInt(br.readLine());
            friendMap = new HashMap<>();
            parents = new int[MAX];
            for(int i=0; i<MAX; i++) {
                parents[i] = i;
            }
            sizes = new int[MAX];
            Arrays.fill(sizes, 1);
            int idx = 0;
            for(int i=0; i<N; i++) {
                String[] friends = br.readLine().split(" ");
                int idx1;
                int idx2;

                if (friendMap.containsKey(friends[0])) {
                    idx1 = friendMap.get(friends[0]);
                } else {
                    idx1 = idx++;
                    friendMap.put(friends[0], idx1);
                }

                if (friendMap.containsKey(friends[1])) {
                    idx2 = friendMap.get(friends[1]);
                } else {
                    idx2 = idx++;
                    friendMap.put(friends[1], idx2);
                }

                int size = union(idx1, idx2);
                System.out.println(size);
                // System.out.println(getCnt(idx1, idx2, idx));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public int getCnt(int u, int v, int idx) {
        int root = parents[u];
        int cnt = 0;
        for(int i=0; i<idx; i++) {
            if(find(i) == root) {
                cnt++;
            }
        }
        return cnt;
    }

    public void solve() {
    }

    public int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public int union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u < v) {
            parents[v] = u;
            sizes[u] += sizes[v];
            return sizes[u];
        }
        else if(u > v) {
            parents[u] = v;
            sizes[v] += sizes[u];
            return sizes[v];
        }
        return sizes[u];
    }

}