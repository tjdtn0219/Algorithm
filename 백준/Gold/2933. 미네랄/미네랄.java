import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int n, m;
    char[][] map;
    int k;
    int[] shootRows;

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
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            map = new char[n][m];
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                for(int j=0; j<m; j++) {
                    map[i][j] = str.charAt(j);
                }
            }
            k = Integer.parseInt(br.readLine());
            shootRows = new int[k];
            tmp = br.readLine().split(" ");
            for(int i=0; i<k; i++) {
                shootRows[i] = n - Integer.parseInt(tmp[i]);
            }
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int i=0; i<shootRows.length; i++) {
            int row = shootRows[i];
            if(i % 2 == 0) {
                shoot(row, true);
            } else {
                shoot(row, false);
            }
//            printMap();
            moveCluster();
        }
        printMap();
    }

    public void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void moveCluster() {
        boolean[][] vis = new boolean[n][m];

        for(int j=0; j<m; j++) {
            //바닥에 고정된 클러스터 찾기
            if(vis[n-1][j] || map[n-1][j] == '.') continue;
            dfs1(n-1, j, vis);
        }


        for(int i=0; i<n; i++) {
            //공중 클러스터 찾기
            for(int j=0; j<m; j++) {
                if(vis[i][j] || map[i][j] == '.') continue;
                List<Point> cluster = dfs2(i, j);
                setDownCluster(cluster, vis);
            }
        }

    }

    public void setDownCluster(List<Point> cluster, boolean[][] vis) {
        deleteClusterInMap(cluster);
        while(isEnableDown(cluster)) {
            for (Point point : cluster) {
                point.x++;
            }
        }
        fillClusterInMap(cluster, vis);
    }

    public void fillClusterInMap(List<Point> cluster, boolean[][] vis) {
        for(Point point : cluster) {
            map[point.x][point.y] = 'x';
            vis[point.x][point.y] = true;
        }
    }

    public boolean isEnableDown(List<Point> cluster) {
        for(Point point : cluster) {
            int nx = point.x + 1;
            int ny = point.y;
            if(!isInner(nx, ny)) return false;
            if(map[nx][ny] == 'x') return false;
        }
        return true;
    }

    public void deleteClusterInMap(List<Point> cluster) {
        for(Point point : cluster) {
            map[point.x][point.y] = '.';
        }
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }

    public void dfs1(int i, int j, boolean[][] vis) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        vis[i][j] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(map[nx][ny] == '.' || vis[nx][ny]) continue;
                q.add(new Point(nx, ny));
                vis[nx][ny] = true;
            }
        }
    }

    public List<Point> dfs2(int i, int j) {
        boolean[][] vis = new boolean[n][m];
        List<Point> cluster = new ArrayList<>();

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        vis[i][j] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            cluster.add(cur);
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(map[nx][ny] == '.' || vis[nx][ny]) continue;
                q.add(new Point(nx, ny));
                vis[nx][ny] = true;
            }
        }

        return cluster;
    }

    public void shoot(int r, boolean isLeft) {
        if(isLeft) {
            for(int j=0; j<m; j++) {
                if(map[r][j] == 'x') {
                    map[r][j] = '.';
                    return ;
                }
            }
        } else {
            for(int j=m-1; j>=0; j--) {
                if(map[r][j] == 'x') {
                    map[r][j] = '.';
                    return ;
                }
            }
        }
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