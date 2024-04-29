import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1, 0, -1, 0}; //남 동 북 서
    static final int[] DY = {0, 1, 0, -1};
    static final int REMOVED = -1000;

    int n, m;
    int[][] board;
    boolean[][] vis;
    int score;

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
            board = new int[n][n];
            vis = new boolean[n][n];
            for(int i=0; i<n; i++) {
                tmp = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    board[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            score = 0;
        } catch (Exception e) {
            System.out.println("InPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void initVis() {
        vis = new boolean[n][n];
    }

    void updateScore(Group group) {
        score += group.points.size() * group.points.size();
        for (Point p : group.points) {
            board[p.x][p.y] = REMOVED;
        }
    }

    public Group getMaxGroup() {
        PriorityQueue<Group> heap = new PriorityQueue<>((g1, g2) -> {
            if(g1.points.size() == g2.points.size()) {
                if(g1.rainBowCnt == g2.rainBowCnt) {
                    if (g1.stdPoint.x == g2.stdPoint.x) return g2.stdPoint.y - g1.stdPoint.y;
                    return g2.stdPoint.x - g1.stdPoint.x;
                }
                return g2.rainBowCnt - g1.rainBowCnt;
            }
            return g2.points.size() - g1.points.size();
        });
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0 && !visited[i][j]) {
                    Point head = new Point(i, j);
                    Group group = new Group(head);
                    int color = board[i][j];
                    visited[i][j] = true;
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(head);
                    while (!queue.isEmpty()) {
                        Point p = queue.remove();
                        group.addPoint(p);
                        if (board[p.x][p.y] == 0) {
                            group.addRainBowCnt();
                        }
                        for (int d = 0; d < 4; d++) {
                            int newX = p.x + DX[d];
                            int newY = p.y + DY[d];
                            if (isInner(newX, newY) && (board[newX][newY] == 0 || board[newX][newY] == color) && !visited[newX][newY]) {
                                queue.add(new Point(newX, newY));
                                visited[newX][newY] = true;
                            }
                        }
                    }
                    if (group.points.size() >= 2) {
                        heap.add(group);
                    }
                    for (Point p : group.points) {
                        if (board[p.x][p.y] == 0) {
                            visited[p.x][p.y] = false;
                        }
                    }
                }
            }
        }
        return heap.poll();
    }

    public void solve() {
        while(true) {
            initVis();
//            Point stdPoint = findMaxGroup();
//            if(stdPoint == null) break;
//            removeMaxGroup(stdPoint);
//            printBoard();
            Group group = getMaxGroup();
            if (group == null) {
                break;
            }
            updateScore(group);
            doGravity();
            gravity();
//            printBoard();
            rotate();
//            printBoard();
            doGravity();

//            printBoard();
//            System.out.println("point : " + point);
        }
        System.out.println(score);
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append("===========================");
        System.out.println(sb);
    }

    public void doGravity() {
        for(int i=n-1; i>=0; i--) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == REMOVED || board[i][j] == -1) continue;
                fallOneBlock(i, j);
            }
        }
    }

    public void fallOneBlock(int x, int y) {
        int tmpX = x;
        int tmpY = y;
        boolean flag = false;
        while(true) {
            int nx = x + DX[0];
            int ny = y + DY[0];
            if(!isInner(nx, ny) || board[nx][ny] != REMOVED) break;
            flag = true;
            x = nx;
            y = ny;
        }
//        System.out.println("ori : " + tmpX + "," + tmpY + " nxt : " + x + "," + y + " flag : " + flag);
        if(!flag) return;
        board[x][y] = board[tmpX][tmpY];
        board[tmpX][tmpY] = REMOVED;
    }

    boolean canSwap(int x, int y) {
        return isInner(x + 1, y) && board[x + 1][y] == REMOVED;
    }

    void gravity() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i > -1; i--) {
                if (board[i][j] >= 0) {
                    int current = i;
                    while (canSwap(current, j)) {
                        board[current + 1][j] = board[current][j];
                        board[current][j] = REMOVED;
                        current += 1;
                    }
                }
            }
        }

    }

    public void rotate() {
        int[][] rotatedBoard = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                rotatedBoard[i][j] = board[j][n-1-i];
            }
        }
        board = rotatedBoard;
    }

    public void removeMaxGroup(Point stdPoint) {
        int x = stdPoint.x;
        int y = stdPoint.y;
        int num = board[x][y];
        int cnt = 1;
        boolean[][] vis2 = new boolean[n][n];
        vis2[x][y] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        board[x][y] = REMOVED;
        while(!q.isEmpty()) {
            Point polled = q.poll();
            x = polled.x;
            y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + DX[dir];
                int ny = y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis2[nx][ny]) continue;
                if(board[nx][ny] == num || board[nx][ny] == 0) {
                    q.add(new Point(nx, ny));
                    vis2[nx][ny] = true;
                    board[nx][ny] = REMOVED;
                    cnt++;
                }
            }
        }
        score += cnt*cnt;
    }

    public Point findMaxGroup() {
        int maxArea = 0;
        int maxRainBowCnt = 0;
        int stdI = 0;
        int stdJ = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] < 1) continue;
                if(vis[i][j]) continue;
                int[] arr = getArea(i, j);
                int area = arr[0];
                int rainBowCnt = arr[1];
                if(maxArea <= area) {
                    if(maxRainBowCnt <= rainBowCnt) {
                        maxArea = area;
                        maxRainBowCnt = rainBowCnt;
                        stdI = i;
                        stdJ = j;
                    }
                }
            }
        }
        if(maxArea <= 1) return null;
        return new Point(stdI, stdJ);
    }

    public int[] getArea(int x, int y) {
        boolean[][] vis2 = new boolean[n][n];
        int num = board[x][y];
        int cnt = 1;
        int rainBowCnt = 0;
        vis[x][y] = true;
        vis2[x][y] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        while(!q.isEmpty()) {
            Point polled = q.poll();
            x = polled.x;
            y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + DX[dir];
                int ny = y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis2[nx][ny]) continue;
                if(board[nx][ny] == num || board[nx][ny] == 0) {
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                    vis2[nx][ny] = true;
                    if(board[nx][ny] == 0) rainBowCnt++;
                    cnt++;
                }
            }
        }
        int[] arr = new int[2];
        arr[0] = cnt;
        arr[1] = rainBowCnt;
        return arr;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }

}

class Group {
    Point stdPoint;
    List<Point> points;
    int rainBowCnt;
    public Group(Point point) {
        this.stdPoint = point;
        this.rainBowCnt = 0;
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void addRainBowCnt() {
        rainBowCnt++;
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