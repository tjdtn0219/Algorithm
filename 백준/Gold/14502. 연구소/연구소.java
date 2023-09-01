import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static int[][] map;
    public static int[][] temp;
    public static int n, m;
    public static int[] comb = new int[3];
    public static int ans = 0;

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

//        printMap(map);

        btk(0, 0);

        System.out.println(ans);

    }

    public static void btk(int k, int li) {
        if(k==3) {
            ans = Math.max(ans, getSafeArea());
            return ;
        }

        for(int i=li; i<n*m; i++) {
            if(map[i/m][i%m] > 0) continue;
//            System.out.println("K : " + k + "(" + i/m + ", " + i%m + ")");
            comb[k] = i;
            btk(k+1, i+1);
        }
    }

    public static int getSafeArea() {
        temp = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                temp[i][j] = map[i][j];
            }
        }

        for(int i=0; i<3; i++) temp[comb[i]/m][comb[i]%m] = 1;
//        printMap(temp);

        int safe_area = 0;
        boolean[][] vis = new boolean[n][m];
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                if(temp[i][j]==0 && !vis[i][j]) {
                    safe_area += bfs(i,j,vis);
                }
            }
        }

        return safe_area;

    }

    public static int bfs(int i, int j, boolean[][] vis) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        vis[i][j] = true;

        int cnt = 0;
        boolean flag = false;
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            cnt++;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(temp[nx][ny]==1 || vis[nx][ny]) continue;
                if(temp[nx][ny]==2) flag = true;
                q.add(new Pair(nx, ny));
                vis[nx][ny] = true;
            }
        }
        if(flag) return 0;
        return cnt;

    }

    public static void printMap(int[][] arr) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====================");
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