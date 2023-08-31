import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static int[][] map;
    public static boolean[][] externalAir;
    public static int n, m;
    public static int time = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        n = Integer.parseInt(strings[0]);
        m = Integer.parseInt(strings[1]);

        map = new int[n][m];

        for(int i=0; i<n; i++) {
            strings = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(strings[j]);
            }
        }

        while(true) {
            checkAir();
            melt();
            time++;
            if(isAllMelted()) break;
        }

        System.out.println(time);

    }

    public static void checkAir() {
        //외부/내부 공기 판별 처리
        Queue<Pair> q = new LinkedList<>();
        externalAir = new boolean[n][m];
        q.add(new Pair(0, 0));
        externalAir[0][0] = true;
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(map[nx][ny]==1 || externalAir[nx][ny]) continue;
                q.add(new Pair(nx, ny));
                externalAir[nx][ny] = true;
            }
        }
    }

    public static void melt() {
        boolean[][] isMelt = new boolean[n][m];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j]==0) continue;
                int cnt = 0;
                for(int dir=0; dir<4; dir++) {
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];
                    if(ni<0 || nj<0 || ni>=n || nj>=m) continue;
                    if(externalAir[ni][nj]) cnt++;
                }
                if(cnt>=2) isMelt[i][j] = true;
            }
        }

        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                if(isMelt[i][j]) map[i][j]=0;
            }
        }
    }

    public static boolean isAllMelted() {
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j]>0) return false;
            }
        }
        return true;
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