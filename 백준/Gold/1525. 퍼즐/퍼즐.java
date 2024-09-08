import java.io.*;
import java.util.*;

public class Main {

    static final int LEN = 3;
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    static final String sorted = "123456780";

    Map<String, Integer> map;
    String init;

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
            String s = "";
            for(int i=0; i<LEN; i++) {
                String[] tmp = br.readLine().split(" ");
                for(int j=0; j<LEN; j++) {
                    s += Integer.parseInt(tmp[j]);
                }
            }
            map = new HashMap<>();
            map.put(s, 0);
            init = s;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        System.out.println(bfs());
    }

    public int bfs() {
        Queue<String> q = new LinkedList<>();
        q.add(init);

        while(!q.isEmpty()) {
            String cur = q.poll();
            int cnt = map.get(cur);
            int empty = cur.indexOf('0');
            int x = empty / 3;
            int y = empty % 3;

            if(cur.equals(sorted)) {
                return cnt;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + DX[dir];
                int ny = y + DY[dir];

                if(!isInner(nx, ny)) continue;

                String nxt = swap(x, y, nx, ny, cur);
                // System.out.println("After Swap : " + nxt);

                if(!map.containsKey(nxt)) {
                    q.add(nxt);
                    map.put(nxt, cnt+1);
                }

            }

        }

        return -1;
    }

    public String swap(int x, int y, int nx, int ny, String s) {
        int idx1 = LEN*x + y;
        char c1 = s.charAt(idx1);

        int idx2 = LEN*nx + ny;
        char c2 = s.charAt(idx2);

        String tmp = s.replace(c1, 'c');
        tmp = tmp.replace(c2, c1);
        tmp = tmp.replace('c', c2);
        
        return tmp;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<LEN && y<LEN;
    }
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}