import java.util.*;
import java.io.*;

public class Main {

    public static final int[] dx = {1,0,-1,0};
    public static final int[] dy = {0,-1,0,1};
    public static char[][] map;
    public static boolean[][] vis;
    public static int n,m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        m = Integer.parseInt(strings[0]);
        n = Integer.parseInt(strings[1]);

        map = new char[n][m];
        vis = new boolean[n][m];

        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int wTeam = 0;
        int bTeam = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(vis[i][j]) continue;
                if(map[i][j]=='W') {
                    int num = bfs(i, j, 'W');
                    wTeam += num * num;
//                    System.out.println("num : " + num);
                } else if(map[i][j]=='B') {
                    int num = bfs(i, j, 'B');
                    bTeam += num * num;
//                    System.out.println("num : " + num);
                }
            }
        }
        System.out.println(wTeam + " " + bTeam);
    }

    public static int bfs(int i, int j, char team) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i,j));
        vis[i][j] = true;

        int cnt = 0;
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            cnt++;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(map[nx][ny]!=team || vis[nx][ny]) continue;
                q.add(new Pair(nx, ny));
                vis[nx][ny] = true;
            }
        }
        return cnt;
    }
}

class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}