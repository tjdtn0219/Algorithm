import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    
    int[][] board;
    int n, m;
    Point a;
    Point b;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        init(board, aloc, bloc);
        return solve();
    }
    
    public int solve() {
        return dfs(a, b);
    }
    
    public int dfs(Point me, Point you) {
        if(board[me.x][me.y] == 0) return 0;
        
        int x = me.x;
        int y = me.y;
        int result = 0; // 현재까지 진행한 턴 수
        for(int i = 0; i < 4; i++){
            int nx = x + DX[i];
            int ny = y + DY[i];
            
            if(!isInner(nx, ny) || board[nx][ny] == 0) continue;
            board[x][y] = 0;
            
            int val = dfs(you, new Point(nx,ny)) + 1; // 턴수 + 1
            board[x][y] = 1; // 사용한 것을 원상 복구
            
            // 지금까지 모두 진 경우고, 이번에 이겼을 때 -> 바로 이긴걸로 바꿔줌
            if(val % 2 == 1 && result % 2 == 0) result = val;
            // 지금까지도 졌고, 이 경우도 진 경우 -> 최대한 많이 움직인다.
            else if(val % 2 == 0 && result % 2 == 0 ) result = Math.max(result,val);
            // 지금까지도 이겼고, 이 경우도 이긴 경우 -> 최대한 적게 움직인다.
            else if(val % 2 == 1 && result % 2 == 1 ) result = Math.min(result,val);
        }
        return result;

    }
    
    public boolean isNotMove(int x, int y) {
        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(isInner(nx, ny) && board[nx][ny] == 1) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isSame(Point a, Point b) {
        return a.x == b.x && a.y == b.y;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
    
    public void init(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.a = new Point(aloc[0], aloc[1]);
        this.b = new Point(bloc[0], bloc[1]);
        n = board.length;
        m = board[0].length;
    }
}