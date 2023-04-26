import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static char[][] map;
    public static int[] comb = new int[7];
    public static boolean[] comb_vis;
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};
    public static int N;
    public static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = 5;
        map = new char[N][N];
        comb_vis = new boolean[N*N];

        for(int i=0; i<N; i++) {
            String str = bf.readLine();
            for(int j=0; j<N; j++) {
                char c = str.charAt(j);
                map[i][j] = c;
            }
        }

//        int[] temp = {5,6,7,8,9,11,16};
        combination(0, -1);
//        bfs(temp);
        System.out.println(result);
    }

    public static void combination(int k, int n) {
        if(k==7) {
            bfs(comb);
            return;
        }

        for(int i=0; i<N*N; i++) {
            if(!comb_vis[i] && i>n) {
                comb_vis[i] = true;
                comb[k] = i;
                combination(k + 1, i);
                comb_vis[i] = false;
            }
        }
    }

    public static void bfs(int[] comb) {
        int cnt = 0;
        int s_cnt = 0;
        boolean[][] vis = new boolean[N][N];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(comb[0]/5, comb[0]%5));
        vis[comb[0]/5][comb[0]%5] = true;

        while(!queue.isEmpty()) {
            Pair popped = queue.poll();
            int x = popped.getX();
            int y = popped.getY();
            if(map[x][y]=='S') s_cnt++;
            cnt++;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(vis[nx][ny] || !arr_contain(comb, 5*nx+ny)) continue;
                queue.add(new Pair(nx, ny));
                vis[nx][ny] = true;
            }
        }
//        System.out.println("cnt : " + cnt + ", s_Cnt : " + s_cnt);
        if(cnt==7 && s_cnt>=4) result++;
    }

    public static boolean arr_contain(int[] arr, int e) {
        for(int n : arr) {
            if(n==e) return true;
        }
        return false;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y){
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
