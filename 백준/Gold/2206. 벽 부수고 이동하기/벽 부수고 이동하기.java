import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int n, m;
    public static int[][] board;
    public static int[][][] dist;
    public static Queue<Pair> q = new LinkedList<>();

    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        board = new int[n][m];
        dist = new int[n][m][2];
        for(int i=0; i<n; i++) {
            String str = bf.readLine();
            for(int j=0; j<m; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }
        q.add(new Pair(0,0,0));
        dist[0][0][0] = 1;
        int ans = bfs();
        System.out.println(ans);
//        print0();
//        print1();
    }

    public static void print0() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
        System.out.println("=========1===");
    }
    public static void print1() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(dist[i][j][0] + " ");
            }
            System.out.println("");
        }
        System.out.println("============");
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(dist[i][j][1]);
            }
            System.out.println("");
        }
    }
    public static int bfs() {

        while(!q.isEmpty()) {
            Pair popped = q.poll();
            int x = popped.getX();
            int y = popped.getY();
            int is_broken = popped.getIsBroken();
            if(x==n-1 && y==m-1) return dist[x][y][is_broken];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
//                if(nx==n-1 && ny==m-1) {
//                    return dist[x][y][is_broken] + 1;
//                }
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (board[nx][ny] == 1 && is_broken == 0 && dist[nx][ny][1]==0) {
                    dist[nx][ny][is_broken+1] = dist[x][y][is_broken] + 1;
                    q.add(new Pair(nx,ny,is_broken+1));
                }
                else if(board[nx][ny] == 0 && dist[nx][ny][is_broken]==0) {
                    dist[nx][ny][is_broken] = dist[x][y][is_broken] + 1;
                    q.add(new Pair(nx,ny,is_broken));

                }
            }
        }
        return -1;
    }
}
class Pair {
    int x;
    int y;
    int is_broken;

    public Pair(int x, int y, int is_broken) {
        this.x = x;
        this.y = y;
        this.is_broken = is_broken;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getIsBroken() {
        return is_broken;
    }
}