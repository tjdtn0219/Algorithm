import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main{

    static final int INF = 99999;
    static final int[] DX = {1,0,-1,0};     //남 동 북 서
    static final int[] DY = {0,1,0,-1};

    int N;
    int M;
    int[][] map;
    int[][] bridges;
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
            String[] N_M = br.readLine().split(" ");
            N = Integer.parseInt(N_M[0]);            
            M = Integer.parseInt(N_M[1]);            
            map = new int[N][M];
            // bridges = new int[N+1][M+1];
            for(int i=0; i<N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int cnt = getIslandCnt();
        // System.out.println("cnt : " + cnt);
        
        // printMap();
        initBridges(cnt);
        setBridges();
        // printBridges(cnt);
        initParents(cnt);
        int ans = getMinDis(cnt);
        if(!isAllConnected(cnt)) ans = -1;
        System.out.println(ans);
        
    }

    public void initParents(int cnt) {
        parents = new int[cnt+1];
        for(int i=0; i<=cnt; i++) {
            parents[i] = i;
        }
    }

    public boolean isAllConnected(int cnt) {
        int root = find(1);
        // System.out.println("root : " + root);
        for(int i=2; i<=cnt; i++) {
            // System.out.println("find[" + i + "] : " + find(i));
            if(find(i) != root) return false;
        }
        return true;
    }

    public int getMinDis(int cnt) {  
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.c - o2.c);
        initPqEdges(pq, cnt);
        // System.out.println("pq size : " + pq.size());
        if(pq.isEmpty()) return -1;

        int ans = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int a = cur.a;
            int b = cur.b;
            if(find(a) != find(b)) {
                union(a, b);
                ans += cur.c;
            }
        }

        return ans;
    }

    public void initPqEdges(PriorityQueue<Edge> pq, int cnt) {

        for(int i=1; i<=cnt; i++) {
            for(int j=1; j<i; j++) {
                // System.out.println("i, j : " + i + ", " + j + " : " + bridges[i][j]);
                if(bridges[i][j] < INF) {
                    // System.out.println("map[" + i + "][" + j + "] : " + map[i][j]);
                    pq.add(new Edge(i, j, bridges[i][j]));
                }
            }
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
            parents[v] = u;
        } else {
            parents[u] = v;
        }
    }

    private void printBridges(int cnt) {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=cnt; i++) {
            for(int j=1; j<=cnt; j++) {
                sb.append(i).append(" to ").append(j).append(" len : ").append(bridges[i][j]).append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public int getIslandCnt() {
        boolean[][] vis = new boolean[N][M];
        int idx = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(vis[i][j] || map[i][j] == 0) continue;
                setIslandIdx(i, j, ++idx, vis);
            }
        }
        return idx;
    }

    public void setIslandIdx(int x, int y, int idx, boolean[][] vis) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        vis[x][y] = true;
        map[x][y] = idx;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis[nx][ny] || map[nx][ny] == 0) continue;
                q.add(new Point(nx, ny));
                vis[nx][ny] = true;
                map[nx][ny] = idx;
            }
        }
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<M;
    }

    public void initBridges(int cnt) {
        bridges = new int[cnt+1][cnt+1];
        for(int i=0; i<=cnt; i++) {
            Arrays.fill(bridges[i], INF);
        }
    }

    public void setBridges() {
        boolean[][] vis = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] > 0 && !vis[i][j]) {
                    int idx = map[i][j];
                    setBridgeLenFromSource(idx, i, j, vis);
                }
            }
        }
    }

    public void setBridgeLenFromSource(int sourceIdx, int x, int y, boolean[][] vis) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        vis[x][y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            setBridgeLenFromPoint(cur.x, cur.y, sourceIdx);
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis[nx][ny] || map[nx][ny] != sourceIdx) continue;
                vis[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
    }

    public void setBridgeLenFromPoint(int x, int y, int sourceIdx) {
        for(int dir=0; dir<4; dir++) {
            int cx = x;
            int cy = y;
            int len = 0;
            while (true) { 
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];
                if(!isInner(nx, ny)) break;
                if(map[nx][ny] == sourceIdx) break;
                // if(x == 1 && y== 6) {
                //     System.out.println("nx, ny : " + nx + ", " + ny + ", map[nx][ny] : " + map[nx][ny]);
                //     System.out.println("len : " + len);
                // }
                if(len < 2 && map[nx][ny] != sourceIdx && map[nx][ny] > 0) {
                    break;
                }
                if(len >= 2 && map[nx][ny] != sourceIdx && map[nx][ny] > 0) {
                    int endIdx = map[nx][ny];
                    bridges[sourceIdx][endIdx] = Math.min(bridges[sourceIdx][endIdx], len);
                    // if (sourceIdx == 1 && endIdx == 3) {
                    //     System.out.println("X, Y : " + x + ", " + y + ", NX, NY : " + nx + ", " + ny);
                    // }
                    // System.out.println(sourceIdx + " to " + endIdx + " : len : " + len);
                    break;
                }
                len++;
                cx = nx;
                cy = ny;
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

class Edge {
    int a;
    int b;
    int c;
    public Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}