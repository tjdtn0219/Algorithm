import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] map;
    public static int[][][] dis;
    public static Queue<Pair> queue = new LinkedList<>();
    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {1,0,-1,0};
    public static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = bf.readLine().split(" ");

        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);
        map = new int[N][M];
        dis = new int[N][M][2];

        for(int i=0; i<N; i++) {
            String str = bf.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        queue.add(new Pair(0, 0, 0));
        dis[0][0][0] = 1;
        while(!queue.isEmpty()) {
            Pair popped = queue.poll();
            int x = popped.getX();
            int y = popped.getY();
            int broken = popped.getBroken();
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(map[nx][ny]==0 && dis[nx][ny][broken]==0) {
                    dis[nx][ny][broken] = dis[x][y][broken] + 1;
                    queue.add(new Pair(nx, ny, broken));
                }
                else if(broken==0 && map[nx][ny]==1 && dis[nx][ny][1]==0) {
                    dis[nx][ny][1] = dis[x][y][0] + 1;
                    queue.add(new Pair(nx, ny, 1));
                }
            }
        }
        int ret;
        if(dis[N-1][M-1][0]==0 && dis[N-1][M-1][1]==0) ret = -1;
        else if(dis[N-1][M-1][0]==0 && dis[N-1][M-1][1]>0) ret = dis[N-1][M-1][1];
        else if(dis[N-1][M-1][0]>0 && dis[N-1][M-1][1]==0) ret = dis[N-1][M-1][0];
        else ret = Math.min(dis[N-1][M-1][0], dis[N-1][M-1][1]);

        System.out.println(ret);

    }
}

class Pair {
    int x;
    int y;
    int broken;

    public Pair(int x, int y, int broken) {
        this.x = x;
        this.y = y;
        this.broken = broken;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getBroken(){
        return broken;
    }
}