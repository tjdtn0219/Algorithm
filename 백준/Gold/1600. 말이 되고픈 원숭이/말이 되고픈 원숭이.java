import java.util.*;
import java.io.*;

public class Main{

    public static int n, m, k;
    public static int[][] board;
    public static int[][][] dist;
    public static Queue<Pair> q = new LinkedList<>();

    public static int[] kdx = {1, 1, 2, 2, -1, -1, -2, -2};
    public static int[] kdy = {2, -2, 1, -1, 2, -2, 1, -1};
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(bf.readLine());
        String[] strings = bf.readLine().split(" ");
        n = Integer.parseInt(strings[1]);
        m = Integer.parseInt(strings[0]);

        board = new int[n][m];
        dist = new int[n][m][k + 2];

        for (int i = 0; i < n; i++) {
            strings = bf.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(strings[j]);
            }
        }
        q.add(new Pair(0, 0, 0));
        dist[0][0][0] = 1;
        bfs();

        int ans = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 0; i <= k; i++) {
            if (dist[n - 1][m - 1][i] > 0) {
                flag = true;
                ans = Math.min(ans, dist[n - 1][m - 1][i] - 1);
            }
        }
        if(!flag) ans = -1;
        System.out.println(ans);
    }


    public static void bfs() {
        while(!q.isEmpty()) {
            Pair popped = q.poll();
            int x = popped.getX();
            int y = popped.getY();
            int vk = popped.getVK();

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(board[nx][ny]==1 || dist[nx][ny][vk]>0) continue;
                q.add(new Pair(nx,ny,vk));
                dist[nx][ny][vk] = dist[x][y][vk] + 1;
            }
            if(vk < k) {
                for(int dir=0; dir<8; dir++) {
                    int nx = x + kdx[dir];
                    int ny = y + kdy[dir];
                    if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                    if(board[nx][ny] == 1) continue;
                    if(dist[nx][ny][vk+1] > 0) continue;            //vk+1인거 조심
                    q.add(new Pair(nx,ny,vk+1));
                    dist[nx][ny][vk+1] = dist[x][y][vk] + 1;
                }
            }
        }
    }
}
class Pair {
    int x;
    int y;
    int vk;

    public Pair(int x, int y, int vk) {
        this.vk = vk;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVK(){
        return vk;
    }
}