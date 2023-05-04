import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n,m;
    public static int[][] map;
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");
        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);
        map = new int[n][m];

        for(int i=0; i<n; i++) {
            strings = bf.readLine().split(" ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(strings[j]);
            }
        }

        int year = 0;
        while(true) {
            year++;
            melt();
//            System.out.println("TAG1");
//            for(int i=0; i<n; i++) {
//                for(int j=0; j<m; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println("");
//            }
            int res = check_part();
            if(res==0) {
                year = 0;
                break;
            } else if(res==2) {
                break;
            }
        }
        System.out.println(year);
    }

    public static void melt() {
        int[][] zeros = new int[n][m];
        for(int i=1; i<n-1; i++) {
            for(int j=1; j<m-1; j++) {
                int cnt = 0;
                for(int dir=0; dir<4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if(map[nx][ny] == 0) cnt++;
                }
                zeros[i][j] = cnt;
            }
        }
        for(int i=1; i<n-1; i++) {
            for(int j=1; j<m-1; j++) {
                map[i][j] -= zeros[i][j];
                if(map[i][j]<0) map[i][j]=0;
            }
        }
    }

    public static int check_part() {
        boolean[][] vis = new boolean[n][m];
        int cnt = 0;
        boolean flag = false;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(!vis[i][j] && map[i][j]>0) {
                    flag = true;
                    dfs(vis, i, j);
                    cnt++;
                    if(cnt>1) return 2;
                }
            }
        }
        if(flag) return 1;
        else return 0;
    }

    public static void dfs(boolean[][] vis, int i, int j) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i,j));
        vis[i][j] = true;

        while(!queue.isEmpty()) {
            Pair popped = queue.poll();
            int x = popped.getX();
            int y = popped.getY();
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(map[nx][ny]==0 || vis[nx][ny]) continue;
                vis[nx][ny] = true;
                queue.add(new Pair(nx, ny));
            }
        }

    }

}

class Pair{
    public int x;
    public int y;

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