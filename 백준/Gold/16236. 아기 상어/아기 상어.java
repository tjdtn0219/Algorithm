import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {0,1,0,-1}; //동 남 서 북
    static final int[] DY = {1,0,-1,0};

    int n;
    int[][] board;
    Point sharkPoint;
    int sharkSize;

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
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    board[i][j] = Integer.parseInt(tmp[j]);
                    if(board[i][j] == 9) {
                        board[i][j] = 0;
                        sharkPoint = new Point(i, j);
                    }
                }
            }
            sharkSize = 2;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int time = 0;
        boolean flag = false;
        while(true) {
            for(int i=0; i<sharkSize; i++) {
                Fish target = getTargetFish();
                if(target == null) {
                    flag = true;
                    break;
                }
//                System.out.println("sharkPoint : " + sharkPoint.x + ", " + sharkPoint.y);
//                System.out.println("target : " + target.point.x + ", " + target.point.y + ", dis : " + target.distance);
                board[target.point.x][target.point.y] = 0;
                sharkPoint = target.point;
                time += target.distance;
            }
            if(flag) break;
            sharkSize++;
        }
        System.out.println(time);
    }

    public Fish getTargetFish() {
        boolean[][] vis = new boolean[n][n];
        Queue<Point> q = new LinkedList<>();
        q.add(sharkPoint);
        int dis = 0;
        boolean flag = false;

        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.x == o2.x) {
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                if(board[cur.x][cur.y] < sharkSize && 0 < board[cur.x][cur.y]) {
                    flag = true;
                    pq.add(new Point(cur.x, cur.y));
                }
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(sharkSize < board[nx][ny]) continue;
                    if(vis[nx][ny]) continue;
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
            if(flag) break;
            dis++;
        }

        if(pq.isEmpty()) return null;
        return new Fish(pq.poll(), dis);
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }

}

class Fish {
    Point point;
    int distance;

    public Fish(Point point, int distance) {
        this.point = point;
        this.distance = distance;
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