import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};
    public static int[][] map;
    public static int n, m;
    public static int year = 0;

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

        int ans = 0;
        while(true) {
            melting();
            int partCnt = getPartCnt();
            if(partCnt==0) break;
            if(partCnt>1) {
                ans = year;
                break;
            }

        }

        System.out.println(ans);
    }

    public static void melting() {
        int[][] meltingCnt = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;
                int cnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];
                    if (ni < 0 || nj < 0 || ni >= n || nj >= m) continue;
                    if (map[ni][nj] == 0) cnt++;
                }
                meltingCnt[i][j] = cnt;
            }
        }

//        System.out.println("======");
//        printArr(meltingCnt);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j]==0) continue;
                map[i][j] = Math.max(0, map[i][j] - meltingCnt[i][j]);
            }
        }
        year++;
    }

    public static int getPartCnt() {

        boolean[][] vis = new boolean[n][m];

        int partCnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j]>0 && !vis[i][j]) {
                    bfs(new Pair(i,j), vis);
                    partCnt++;
                }
            }
        }
        return partCnt;
    }

    public static void bfs(Pair pair, boolean[][] vis) {
        Queue<Pair> q = new LinkedList<>();
        q.add(pair);
        vis[pair.x][pair.y] = true;

        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(map[nx][ny]==0 || vis[nx][ny]) continue;
                q.add(new Pair(nx, ny));
                vis[nx][ny] = true;
            }
        }
    }

    public static void printMap() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void printArr(int[][] arr) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
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