import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int[][] map;
    public static boolean[][] vis;
    public static int[][] dis;
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];
        vis = new boolean[n][n];

        for(int i=0; i<n; i++) {
            String[] strings = bf.readLine().split(" ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(strings[j]);
            }
        }
        set_island_idx();
        System.out.println(get_min_bridge());

    }

    public static void print_map(int[][] arr) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int get_min_bridge() {
        int ret = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j]>0) {
                    boolean flag = false;
                    dis = new int[n][n];
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    dis[i][j] = 1;
                    while (!q.isEmpty()) {
                        Pair popped = q.poll();
                        int x = popped.getX();
                        int y = popped.getY();
                        for(int dir=0; dir<4; dir++) {
                            int nx = x + dx[dir];
                            int ny = y + dy[dir];
                            if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                            if(dis[nx][ny] > 0 || map[nx][ny] == map[i][j]) continue;
                            if(map[nx][ny] != 0 && map[nx][ny] != map[i][j]) {
                                ret = Integer.min(ret, dis[x][y]-1);
                                flag = true;
                                break;
                            }
                            dis[nx][ny] = dis[x][y] + 1;
                            q.add(new Pair(nx, ny));
                        }
                        if(flag) break;
                    }
                }
            }
        }
        return ret;
    }

    public static int set_island_idx() {
        Queue<Pair> q = new LinkedList<>();
        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!vis[i][j] && map[i][j]>0) {
                    q.add(new Pair(i, j));
                    idx++;
                    while (!q.isEmpty()) {
                        Pair popped = q.poll();
                        int x = popped.getX();
                        int y = popped.getY();
                        map[x][y] = idx;
                        for(int dir=0; dir<4; dir++) {
                            int nx = x + dx[dir];
                            int ny = y + dy[dir];
                            if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                            if(map[nx][ny]==0 || vis[nx][ny]) continue;
                            q.add(new Pair(nx, ny));
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return idx;
    }
}

class Pair{
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}