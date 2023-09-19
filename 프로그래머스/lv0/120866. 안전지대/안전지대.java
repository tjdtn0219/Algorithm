import java.util.*;

class Solution {
    public int[] dx = {0,1,0,-1,0,1,-1,1,-1};
    public int[] dy = {0,0,-1,0,1,1,1,-1,-1};
    public int solution(int[][] board) {
        int n = board.length;
        int ans = n*n;
        boolean vis[][] = new boolean[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j]==0) continue;
                for(int dir=0; dir<9; dir++) {
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];
                    if(ni<0 || nj<0 || ni>=n || nj>=n) continue;
                    if(vis[ni][nj]) continue;
                    ans--;
                    vis[ni][nj] = true;
                }
            }
        }
        return ans;
    }
}