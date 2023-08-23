import java.io.*;
import java.util.*;

public class Main {

    public static int[] dx={1,0,-1,0};
    public static int[] dy={0,1,0,-1};
    public static int[][] board;
    public static int[][] dis;
    public static int n, m;
    public static Queue<Pair> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        m = Integer.parseInt(strings[0]);
        n = Integer.parseInt(strings[1]);

        board = new int[n][m];
        dis = new int[n][m];

        for(int i=0; i<n; i++) {
            strings = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(strings[j]);
                if(board[i][j]==1) {
                    q.add(new Pair(i, j));
                    dis[i][j] = 1;
                }
            }
        }

        bfs();

        int max = 0;
        boolean flag = false;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(dis[i][j] > max) max = dis[i][j];
                if(dis[i][j]==0 && board[i][j]==0) flag = true;
            }
        }

        int ans;
        if(flag) ans = -1;
        else ans = max-1;
        System.out.println(ans);

    }

    public static void bfs() {
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x+dx[dir];
                int ny = y+dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(board[nx][ny]==-1 || dis[nx][ny]>0) continue;
                dis[nx][ny] = dis[x][y] + 1;
                q.add(new Pair(nx,ny));
            }
        }

    }

}

class Pair{
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}