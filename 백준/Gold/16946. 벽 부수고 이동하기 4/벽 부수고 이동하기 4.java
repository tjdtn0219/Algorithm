import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int n, m;
    int[][] map;
    int[][] idxMap;
    boolean[][] vis;
    HashMap<Integer, Integer> cntMap;

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
            String[] N_M = br.readLine().split(" ");
            n = Integer.parseInt(N_M[0]);
            m = Integer.parseInt(N_M[1]);
            map = new int[n][m];
            idxMap = new int[n][m];
            vis = new boolean[n][m];
            for(int i=0; i<n; i++) {
                String tmp = br.readLine();
                for(int j=0; j<m; j++) {
                    map[i][j] = tmp.charAt(j) - '0';
                }
            }
            cntMap = new HashMap<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 0 && !vis[i][j]) {
                    bfs(i, j, ++idx);
                }
            }
        }
        // for(int key : cntMap.keySet()) {
        //     System.out.println("idx : " + key + ", cnt : " + cntMap.get(idx));
        // }
        // printArr(idxMap);
        // System.out.println("======\n");

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 1) {
                    map[i][j] = getCount(i, j);
                }
            }
        }

        printArr(map);
    }

    public int getCount(int x, int y) {
        HashSet<Integer> set = new HashSet<>();
        int cnt = 1;
        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
           int idx = idxMap[nx][ny];
           set.add(idx);
        }
        for(int idx : set) {
            cnt += cntMap.getOrDefault(idx, 0);
        }
        return cnt % 10;
    }

    private void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void bfs(int x, int y, int idx) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        vis[x][y] = true;

        List<Point> list = new ArrayList<>();
        int cnt = 0;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            list.add(cur);
            cnt++;
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis[nx][ny] || map[nx][ny] == 1) continue;
                q.add(new Point(nx, ny));
                vis[nx][ny] = true;
            }
        }

        for(Point point : list) {
            idxMap[point.x][point.y] = idx;
        }

        cntMap.put(idx, cnt);

    }

    

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
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