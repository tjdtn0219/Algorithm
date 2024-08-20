import java.io.*;
import java.util.*;

public class Main{
    static final int[] DX = {0, -1, 0, 1};
    static final int[] DY = {-1, 0, 1, 0};
    static final int MAX = 1_000_005;

    int N, M, K;
    int[][] map;
    int[][] dp;
    
    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }


    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] N_M_K = br.readLine().split(" ");
            N = Integer.parseInt(N_M_K[0]);
            M = Integer.parseInt(N_M_K[1]);
            K = Integer.parseInt(N_M_K[2]);
            dp = new int[N][M];
            for(int i=0; i<N; i++) {
                Arrays.fill(dp[i], MAX);
            }
            map = new int[N][M];
            for(int i=0; i<N; i++) {
                String s = br.readLine();
                for(int j=0; j<M; j++) {
                    map[i][j] = s.charAt(j) - '0';
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int ans = bfs();
        System.out.println(ans);
    }

    private int bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0, 1));
                
        dp[0][0] = 0;

        while(!q.isEmpty()){
            Point c = q.poll();
            
            if(c.x == N-1 && c.y == M-1){
                return c.dis;
            }

            for(int i=0; i < 4; i++){
                int nx = c.x + DX[i];
                int ny = c.y + DY[i];

                if(!isInner(nx, ny)) continue;
                if(dp[nx][ny] <= c.k) continue;
                
                if(map[nx][ny] == 1){
                    if(c.k < K && dp[nx][ny] > c.k+1){
                        q.add(new Point(nx, ny, c.k+1, c.dis+1));
                        dp[nx][ny] = c.k+1;
                    }
                } else {
                    q.add(new Point(nx, ny, c.k, c.dis+1));
                    dp[nx][ny] = c.k;
                }
            }
        }
        return -1;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<M;
    }
}

class Point{
    int x;  
    int y;  
    int k;  
    int dis;  
    public Point(int x, int y, int k, int dis){
        this.y=y;
        this.x=x;
        this.k=k;
        this.dis=dis;
    }
}
