import java.io.*;
import java.util.*;

public class Main {

    public static final int[] DX = {1, 0, -1, 0};
    public static final int[] DY = {0, 1, 0, -1};

    int N, M;
    int[][] board;

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
            board = new int[N][M];
            for(int i=0; i<N; i++) {
                board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        // printBoard();
        int time = 0;
        int cnt = 0;
        while(true) {
            time++;
            boolean[][] isAirArr = getAir();
            List<Point> sidePoints = getSidePoints(isAirArr);
            // printSide(sidePoints);
            melt(sidePoints);
            cnt = sidePoints.size();
            if(isClear()) break;
        }
        System.out.println(time);
        System.out.println(cnt);

    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.out.println("==================================");
    }

    public void printSide(List<Point> sidePoints) {
        int[][] board = new int[N][M];
        for(Point point : sidePoints) {
            board[point.x][point.y] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.out.println("==================================");
    }

    public boolean isClear() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j] == 1) return false;
            }
        }
        return true;
    }

    public void melt(List<Point> sidePoints) {
        for(Point point : sidePoints) {
            // System.out.println("side point x, y : " + point.x + ", " + point.y);
            board[point.x][point.y] = 0;
        }
    }

    public List<Point> getSidePoints(boolean[][] isAirArr) {
        List<Point> sidePoints = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                // System.out.println("i, j : " + i + ", " + j);
                if(board[i][j] == 1) {
                    // System.out.println("TAG");
                    for(int dir=0; dir<4; dir++) {
                        int nx = i + DX[dir];
                        int ny = j + DY[dir];
                        // System.out.println("isArr[nx][ny] : " + isAirArr[nx][ny]);
                        if(isAirArr[nx][ny]) {
                            // System.out.println("sidePoint.add x, y : " + i + ", " + j);
                            sidePoints.add(new Point(i, j));
                            break;
                        }
                    }
                }
            }
        }
        return sidePoints;
    }

    public boolean[][] getAir() {
        boolean[][] isAirArr = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        isAirArr[0][0] = true;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(board[nx][ny] == 1 || isAirArr[nx][ny]) continue;
                isAirArr[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
        return isAirArr;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<M;
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