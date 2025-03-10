import java.util.*;

class Solution {
    
    int[][] board;
    int[][] dp;
    int n, m;
    int ans = 0;
    
    public int solution(int [][]board) {
        init(board);
        solve();
        return ans;
    }
    
    private void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void solve() {
        // printArr(board);
        for(int i=1; i<n; i++) {
            for(int j=1; j<m; j++) {
                if(board[i][j] == 0) continue;
                int min = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                dp[i][j] = min + 1;
            }
        }
        // printArr(dp);
        int maxLen = getMaxLen();
        ans = maxLen * maxLen;
    }
    
    public int getMaxLen() {
        int maxLen = -1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen;
    }
    
    public void init(int[][] board) {
        this.board = board;
        this.n = board.length;
        this.m = board[0].length;
        this.dp = new int[n][m];
        for(int i=0; i<n; i++) {
            dp[i] = board[i].clone();
        }
        System.out.println("n : " + n + ", m : " + m);
    }
}