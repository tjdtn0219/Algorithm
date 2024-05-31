import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {0, 1, 0, -1};  //동 남 서 북
    static final int[] DY = {1, 0, -1, 0};

    int[] dice1 = {1, 3, 6, 4};    //위 동 아래 서
    int[] dice2 = {1, 5, 6, 2};    //위 남 아래 북

    int n, m, k;
    int[][] board;
    int curDir;
    Point curPoint;
    int answer;

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
            k = Integer.parseInt(tmp[2]);
            board = new int[n][m];
            for(int i=0; i<n; i++) {
                tmp = br.readLine().split(" ");
                for(int j=0; j<m; j++) {
                    board[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            curDir = 0;
            curPoint = new Point(0, 0);
            answer = 0;
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int i=0; i<k; i++) {
            rollDice(curDir);
        }
        System.out.println(answer);
    }

    public void rollDice(int dir) {
        //동 남 서 북
        int nx = curPoint.x + DX[dir];
        int ny = curPoint.y + DY[dir];
//        System.out.println("cur : " + curPoint.x + ", " + curPoint.y + ", dir : " + dir);
        if(!isInner(nx, ny)) {
            changeDirRight();
            changeDirRight();
            dir = curDir;
            nx = curPoint.x + DX[dir];
            ny = curPoint.y + DY[dir];
        }
//        System.out.println("cur2 : " + curPoint.x + ", " + curPoint.y + ", dir : " + dir + " nx, ny : " + nx + " , " + ny);

        if(dir == 0) rollRight();
        else if(dir == 1) rollDown();
        else if(dir == 2) rollLeft();
        else rollUp();
//        System.out.println("nx, ny : " + nx + ", " + ny);
        answer += getScore(nx, ny, board[nx][ny]);

        int A = dice1[2];
        int B = board[nx][ny];
        if(A > B) changeDirRight();
        else if(A < B) changeDirLeft();

        curPoint.x = nx;
        curPoint.y = ny;
    }

    public int getScore(int x, int y, int val) {
        boolean[][] vis = new boolean[n][m];
        vis[x][y] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));

        int cnt = 0;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            cnt++;
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis[nx][ny] || board[nx][ny] != val) continue;
                q.add(new Point(nx, ny));
                vis[nx][ny] = true;
            }
        }

        return cnt * val;
    }

    public void rollRight() {
        //  위 동 아래 서
        //->서 위  동  아래
        int[] tmp = new int[4];
        for(int i=0; i<4; i++) {
            tmp[(i+1)%4] = dice1[i];
        }
        dice1 = tmp;
        dice2[0] = dice1[0];
        dice2[2] = dice1[2];
    }

    public void rollLeft() {
        //  위 동 아래 서
        //<-동 아래 서  위
        int[] tmp = new int[4];
        for(int i=0; i<4; i++) {
            tmp[(i+3)%4] = dice1[i];
        }
        dice1 = tmp;
        dice2[0] = dice1[0];
        dice2[2] = dice1[2];
    }

    public void rollUp() {
        //위 남 아래 북
        //남 아래 북 위
        int[] tmp = new int[4];
        for(int i=0; i<4; i++) {
            tmp[(i+3)%4] = dice2[i];
        }
        dice2 = tmp;
        dice1[0] = dice2[0];
        dice1[2] = dice2[2];
    }

    public void rollDown() {
        //위 남 아래 북
        //북 위 남  아래
        int[] tmp = new int[4];
        for(int i=0; i<4; i++) {
            tmp[(i+1)%4] = dice2[i];
        }
        dice2 = tmp;
        dice1[0] = dice2[0];
        dice1[2] = dice2[2];
    }

    public void changeDirRight() {
        curDir = (curDir+1) % 4;
    }

    public void changeDirLeft() {
        curDir = (curDir+3) % 4;
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
