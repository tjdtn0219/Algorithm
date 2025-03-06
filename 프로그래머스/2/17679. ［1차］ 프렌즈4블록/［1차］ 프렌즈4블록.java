import java.util.*;

class Solution {
    
    static final int[] DX = {1,0,1};
    static final int[] DY = {0,1,1};
    
    int n, m;
    char[][] board;
    int ans;
    
    public int solution(int m, int n, String[] board) {
        init(m, n, board);
        solve();
        return ans;
    }
    
    private void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void solve() {
        while(true) {
            // printBoard();
            boolean[][] isDelete = new boolean[n][m];
            boolean flag = checkDelete(isDelete);
            if(!flag) break;
            
            deleteBlocks(isDelete);
            fallBlocks();
        }
        
    }
    
    public void fallBlocks() {
        for(int i=n-2; i>=0; i--) {
            for(int j=0; j<m; j++) {
                fallBlock(i, j);
            }
        }   
    }
    
    public void fallBlock(int x, int y) {
        int nx = x + 1;
        boolean flag = false;
        while(nx < n && board[nx][y] == '.') {
            flag = true;
            nx++;
        }
        if(flag) {
            --nx;
            board[nx][y] = board[x][y];
            board[x][y] = '.';
        }
    }
    
    public void deleteBlocks(boolean[][] isDelete) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(isDelete[i][j]) {
                    board[i][j] = '.';
                    ans++;
                }
            }
        }
    }
    
    public boolean checkDelete(boolean[][] isDelete) {
        boolean flag = false;
        
        for(int i=0; i<n-1; i++) {
            for(int j=0; j<m-1; j++) {
                if(board[i][j] == '.') continue;
                if(isSquare(i, j)) {
                    flag = true;
                    isDelete[i][j] = true;
                    for(int d=0; d<3; d++) {
                        isDelete[i+DX[d]][j+DY[d]] = true;
                    }
                }
            }
        }
        return flag;
    }
    
    public boolean isSquare(int x, int y) {
        for(int i=0; i<3; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];
            if(board[nx][ny] != board[x][y]) return false;
        }
        return true;
    }
    
    public void init(int m, int n, String[] board) {
        this.n = m;
        this.m = n;
        this.board = new char[this.n][this.m];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                this.board[i][j] = board[i].charAt(j);
            }
        }
    }
}