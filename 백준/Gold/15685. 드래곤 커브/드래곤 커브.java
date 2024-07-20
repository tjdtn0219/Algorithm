import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {0,-1,0,1}; //동 북 서 남
    static final int[] DY = {1,0,-1,0};
    static final int N = 100;
    static final int[] SQ_X = {1,0,1};
    static final int[] SQ_Y = {0,1,1};

    int[][] board;
    int n;
    int[][] inputs;

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
            board = new int[N+1][N+1];
            n = Integer.parseInt(br.readLine());
            inputs = new int[n][4];
            for(int i=0; i<n; i++) {
                inputs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        drawDragonCurves();
        
    }

    public void drawDragonCurves() {
        // n = 1;
        for(int i=0; i<n; i++) {
            int y = inputs[i][0];
            int x = inputs[i][1];
            int d = inputs[i][2];
            int age = inputs[i][3];

            drawDragonCurve(x, y, d, age);

        }
        int ans = getCntOfSquare();
        System.out.println(ans);
        // printBoard();
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void drawDragonCurve(int x, int y, int d, int age) {
        Deque<Integer> dq = new LinkedList<>();

        int cnt = 0;
        board[x][y] = 1;
        while(cnt <= age) {
            if(cnt == 0) {
                int nx = x + DX[d];
                int ny = y + DY[d];
                board[nx][ny] = 1;
                dq.addLast(d);
                x = nx; y = ny;
                cnt++;
            } else {
                int size = dq.size();
                List<Integer> nextDirs = new ArrayList<>();
                for(int i=0; i<size; i++) {
                    int dir = dq.pollLast();
                    nextDirs.add(rotateDir(dir));
                    dq.addFirst(dir);
                }
                for(int dir : nextDirs) {
                    int nx = x + DX[dir];
                    int ny = y + DY[dir];
                    board[nx][ny] = 1;
                    x = nx; y = ny;
                    dq.addLast(dir);
                }
                cnt++;
            }
        }
        
    }

    public int getCntOfSquare() {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] == 0) continue;
                boolean flag = true;
                for(int dir=0; dir<3; dir++) {
                    int nx = i + SQ_X[dir];
                    int ny = j + SQ_Y[dir];
                    if(board[nx][ny] == 0) {
                        flag = false;
                        break;
                    }
                }
                if(flag) cnt++;
            }
        }
        return cnt;
    }

    //0 1 2 3
    //동 북 서 남
    public int rotateDir(int dir) {
        return (dir + 1) % 4;
    }

}

class Point {
    int x;
    int y;
    int d;
    public Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}