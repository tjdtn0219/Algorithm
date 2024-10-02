import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1, 0, -1, 0};  //남 동 북 서
    static final int[] DY = {0, 1, 0, -1};

    int n, m;
    char[][] board;
    List<Point> cPoints;
    int[][][] dp;

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
            String[] m_n = br.readLine().split(" ");
            n = Integer.parseInt(m_n[1]);    
            m = Integer.parseInt(m_n[0]);
            board = new char[n][m];
            cPoints = new ArrayList<>();
            for(int i=0; i<n; i++) {
                String tmp = br.readLine();
                for(int j=0; j<m; j++) {
                    char c = tmp.charAt(j);
                    board[i][j] = c;
                    if(c == 'C') {
                        cPoints.add(new Point(i, j));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dp = new int[n][m][4];
        initDP(dp);
        
        bfs();
        Point p = cPoints.get(1);
        int ans = Integer.MAX_VALUE;
        for(int dir=0; dir<4; dir++) {
            // 목적지 지점의 4방향 중 최소값 출력
            ans = Math.min(ans, dp[p.x][p.y][dir]);
        }
        System.out.println(ans);

    }

    public void bfs() {
        Point c1 = cPoints.get(0);
        Point c2 = cPoints.get(1);
        
        Queue<Node> q = new LinkedList<>();
        for(int dir=0; dir<4; dir++) {
            int nx = c1.x + DX[dir];
            int ny = c1.y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(board[nx][ny] == '*') continue;
            dp[c1.x][c1.y][dir] = 0;
            dp[nx][ny][dir] = 0;
            q.add(new Node(new Point(nx, ny), dir, 0));
        }


        while(!q.isEmpty()) {
            Node cur = q.poll();
            // System.out.println("cur : x, y, d : " + cur.p.x + ", " + cur.p.y + ", " + cur.dir + ", dp : " + dp[cur.p.x][cur.p.y][cur.dir]);
            if(board[cur.p.x][cur.p.y] == 'C') {
                continue;
            }
            int nx, ny;
            int nDir;

            // Turn Left
            nDir = getLeftDir(cur.dir);
            nx = cur.p.x + DX[nDir];
            ny = cur.p.y + DY[nDir];
            if(isInner(nx, ny) && board[nx][ny] != '*' && dp[nx][ny][nDir] > cur.cnt + 1) {
                dp[nx][ny][nDir] = cur.cnt + 1;
                q.add(new Node(new Point(nx, ny), nDir, dp[nx][ny][nDir]));
                // System.out.println("nx, ny, nDir : " + nx + ", " + ny + ", " + nDir + ", dp : " + dp[nx][ny][nDir]);
            }

            // Turn Right
            nDir = getRightDir(cur.dir);
            nx = cur.p.x + DX[nDir];
            ny = cur.p.y + DY[nDir];
            if(isInner(nx, ny) && board[nx][ny] != '*' && dp[nx][ny][nDir] > cur.cnt + 1) {
                dp[nx][ny][nDir] = cur.cnt + 1;
                q.add(new Node(new Point(nx, ny), nDir, dp[nx][ny][nDir]));
                // System.out.println("nx, ny, nDir : " + nx + ", " + ny + ", " + nDir + ", dp : " + dp[nx][ny][nDir]);
            }

            // Go Straight
            nDir = cur.dir;
            nx = cur.p.x + DX[nDir];
            ny = cur.p.y + DY[nDir];
            if(isInner(nx, ny)) {
                // System.out.println("dp[nx][ny][nDir] : " + dp[nx][ny][nDir]);
            }
            if(isInner(nx, ny) && board[nx][ny] != '*' && dp[nx][ny][nDir] > cur.cnt) {
                dp[nx][ny][nDir] = cur.cnt;
                q.add(new Node(new Point(nx, ny), nDir, dp[nx][ny][nDir]));
                // System.out.println("nx, ny, nDir : " + nx + ", " + ny + ", " + nDir + ", dp : " + dp[nx][ny][nDir]);
            }

        }

    }

    public int getLeftDir(int dir) {
        return (dir + 1) % 4;
    }

    public int getRightDir(int dir) {
        return (dir + 3) % 4;
    }

    public boolean isInner(int x, int y) {
        return x>=0 && y>=0 && x<n && y<m;
    }

    public void initDP(int[][][] dp) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int k=0; k<4; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    }
}

class Node {
    Point p;
    int dir;
    int cnt;
    public Node(Point p, int dir, int cnt) {
        this.p = p;
        this.dir = dir;
        this.cnt = cnt;
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