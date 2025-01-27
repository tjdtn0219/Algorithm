import java.util.*;

class Solution {
    
    static final int[] DX = {1, 0, 1};
    static final int[] DY = {0, 1, 1};
    static final char X = '-';
    int n, m;
    char[][] board;
    boolean hasCheck;
    
    public int solution(int m, int n, String[] board) {
        init(m, n, board);
        return solve();
    }
    
    public int solve() {
        int cnt = 0;
        // System.out.println("==INIT==");
        // printBoard();
        while(true) {
            hasCheck = false;
            boolean[][] check = getCheck();
            if(!hasCheck) break;
            delete(check);
            // System.out.println("==DELETE==");
            // printBoard();
            reLocate();
            // System.out.println("==RELOCATE==");
            // printBoard();
            // cnt++;
            // if(cnt == 5) break;
        }
        // printBoard();
        return getXCnt();
    }
    
    public int getXCnt() {
        int cnt = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == X) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void reLocate() {
        for(int i=n-2; i>=0; i--) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == X) continue;
                int ti = i;
                while((ti+1 <= n-1) && (board[ti+1][j] == X)) {
                    ti++;
                }
                // System.out.println("i, j : " + i + ", " + j + " | ti : " + ti);
                if(ti > i) {
                    board[ti][j] = board[i][j];
                    board[i][j] = X;   
                }
            }
        }
    }
    
    public void delete(boolean[][] check) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(check[i][j]) {
                    board[i][j] = X;
                }
            }
        }
    }
    
    public boolean[][] getCheck() {
        boolean[][] check = new boolean[n][m];
        for(int i=0; i<n-1; i++) {
            for(int j=0; j<m-1; j++) {
                if(board[i][j] == X) continue;
                boolean flag = true;
                for(int dir=0; dir<3; dir++) {
                    int nx = i + DX[dir];
                    int ny = j + DY[dir];
                    if(board[nx][ny] != board[i][j]) {
                        flag = false;
                    }
                }
                if(flag) {
                    hasCheck = true;
                    check[i][j] = true;
                    for(int dir=0; dir<3; dir++) {
                        int nx = i + DX[dir];
                        int ny = j + DY[dir];
                        check[nx][ny] = true;
                    }
                }
            }
        }
        return check;
    }
    
    public void init(int m, int n, String[] board) {
        this.n = m;
        this.m = n;
        System.out.println(this.n + " " + this.m);
        this.board = new char[this.n][this.m];
        for(int i=0; i<this.n; i++) {
            for(int j=0; j<this.m; j++) {
                this.board[i][j] = board[i].charAt(j);
            }
        }
    }
}