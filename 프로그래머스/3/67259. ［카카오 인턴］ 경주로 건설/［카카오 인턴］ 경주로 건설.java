import java.util.*;

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    
    int[][] board;
    int[][][] dp;
    boolean[][] vis;
    int n;
    int answer;
    
    public int solution(int[][] board) {
        init(board);
        solve();
        return answer;
    }
    
    public void solve() {
        vis[0][0] = true;
        for(int dir=0; dir<4; dir++) {
            dfs(0, 0, dir, 0, 0);
        }
    }
    
    public void dfs(int x, int y, int d, int nowCost, int k) {
        // System.out.println("k : " + k + " | x, y : " + x + ", " + y + ", nowCost : " + nowCost);
        if(x == n-1 && y == n-1) {
            answer = Math.min(answer, nowCost);
            return ;
        }
        
        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            if(vis[nx][ny]) continue;
            if(board[nx][ny] == 1) continue;
            int nxtCost = 0;
            if(d == dir) {
                nxtCost = nowCost + 100;
            } else {
                nxtCost = nowCost + 600;
            }
            if(dp[nx][ny][dir] <= nxtCost) continue;
            vis[nx][ny] = true;
            dp[nx][ny][dir] = nxtCost;
            dfs(nx, ny, dir, nxtCost, k+1);
            vis[nx][ny] = false;
        }
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }
    
    public void init(int[][] board) {
        this.n = board.length;
        this.board = board;
        dp = new int[n][n][4];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        vis = new boolean[n][n];
        answer = Integer.MAX_VALUE;
    }
}