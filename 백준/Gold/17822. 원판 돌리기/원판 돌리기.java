import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int n, m, t;
    int[][] board;
    int[][] commands;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void solve() {
        for(int[] command : commands) {
            int x = command[0];
            for(int i=x; i<=n; i+=x) {
                rotate(i-1, command[1], command[2]);
            }
            boolean flag = checkAdj();
            if(!flag) {
                checkAvg();
            }
        }
        System.out.println(getSum());
    }

    public int getSum() {
        int sum = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] > 0) {
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }

    public void checkAvg() {
        int sum = 0;
        int cnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] > 0) {
                    sum += board[i][j];
                    cnt++;
                }
            }
        }

        double avg = (double) sum / cnt;
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] < 0) continue;
                if((double)board[i][j] > avg) {
                    board[i][j]--;
                } else if((double)board[i][j] < avg) {
                    board[i][j]++;
                }
            }
        }

    }

    public boolean checkAdj() {
        boolean flag = false;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] > 0) {
                    if(bfs(i, j)) flag = true;;
                }
            }
        }
        return flag;
    }

    public boolean bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        int num = board[x][y];
        q.add(new Point(x, y));
        boolean flag = false;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = getNy(cur.y + DY[dir]);
                if(!isInnerX(nx)) continue;
                if(num == board[nx][ny]) {
                    flag = true;
                    board[cur.x][cur.y] = -1;
                    board[nx][ny] = -1;
                    q.add(new Point(nx, ny));
                }
            }
        }
        return flag;
    }

    public int getNy(int y) {
        return (y+m) % m;
    }

    public boolean isInnerX(int x) {
        return 0<=x && x<n;
    }

    public void rotate(int idx, int d, int k) {
        if(d == 0) {
            //시계 방향
            board[idx] = rotateArr(board[idx], k);
        } else {
            //반시계 방향
            board[idx] = rotateArrRev(board[idx], k);
        }
    }

    public int[] rotateArr(int[] arr, int k) {
        int[] tmp = new int[m];
        for(int i=0; i<m; i++) {
            tmp[(i+k)%m] = arr[i];
        }
        return tmp;
    }

    public int[] rotateArrRev(int[] arr, int k) {
        int[] tmp = new int[m];
        for(int i=0; i<m; i++) {
            tmp[(i+m-k)%m] = arr[i];
        }
        return tmp;
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_m_t = br.readLine().split(" ");
            n = Integer.parseInt(n_m_t[0]);
            m = Integer.parseInt(n_m_t[1]);
            t = Integer.parseInt(n_m_t[2]);
            board = new int[n][m];
            for(int i=0; i<n; i++) {
                board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            commands = new int[t][3];
            for(int i=0; i<t; i++) {
                String[] x_d_k = br.readLine().split(" ");
                int x = Integer.parseInt(x_d_k[0]);
                int d = Integer.parseInt(x_d_k[1]);
                int k = Integer.parseInt(x_d_k[2]);
                commands[i][0] = x;
                commands[i][1] = d;
                commands[i][2] = k;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}