class Solution
{
    public int solution(int [][]board)
    {
        int ans = 0;

        int rLen = board.length;
        int cLen = board[0].length;
        
        int[][] dp = new int[rLen][cLen];
        
        for(int i=0; i<rLen; i++) {
            for(int j=0; j<cLen; j++) {
                dp[i][j] = board[i][j];
            }
        }
        
        ans = Math.max(ans, dp[0][0]);
        
        for(int i=1; i<rLen; i++) {
            for(int j=1; j<cLen; j++) {
                if(board[i][j]==1) {
                    dp[i][j] = getMax(dp, i,j);
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        
        return ans*ans;
    }
    
    public int getMax(int[][] dp, int x, int y) {
        int a = dp[x-1][y-1];
        int b = dp[x][y-1];
        int c = dp[x-1][y];
        
        if(a==0 || b==0 || c==0) {
            return 1;
        }
        
        return Math.min(Math.min(a,b),c)+1;
    }
}