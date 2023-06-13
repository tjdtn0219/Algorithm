import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static int[][] map;
    public static int[][] map2;
    public static Queue<Pair> q = new LinkedList<>();
    public static int n;
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(bf.readLine());

        map = new int[n][n];
        map2 = new int[n][n];

        for(int i=0; i<n; i++) {
            String str = bf.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int cnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] == 1 && map2[i][j] == 0) {
                    q.add(new Pair(i, j));
                    map2[i][j] = ++cnt;
                    dfs(cnt);
//                    System.out.println("(x,y) : " + i + "," + j);
//                    print_map2();
                }
            }
        }

        int[] res = new int[cnt];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map2[i][j] > 0) res[map2[i][j]-1]++;
            }
        }
        Arrays.sort(res);
        sb.append(cnt + "\n");
        for(int num : res) {
            sb.append(num + "\n");
        }
        System.out.println(sb);

    }

    public static void print_map2() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(map2[i][j]);
            }
            System.out.println("");
        }
    }

    public static void dfs(int idx) {

        while(!q.isEmpty()) {
            Pair popped = q.poll();
            int x = popped.getX();
            int y = popped.getY();
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                if(map[nx][ny] == 0 || map2[nx][ny] > 0) continue;
                q.add(new Pair(nx, ny));
                map2[nx][ny] = idx;
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

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
