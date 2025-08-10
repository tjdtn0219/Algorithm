import java.awt.Point;
import java.util.*;

class Solution {
    
    static final int N = 5;
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,-1,0,1};
    
    char[][][] board;
    int[] answer;
    
    public int[] solution(String[][] places) {
        init(places);
        solve();
        return answer;
    }
    
    public void solve() {
        for(int k=0; k<N; k++) {
            check(k);
        }
    }
    
    public void check(int k) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[k][i][j] == 'P') {
                    if(!bfs(k, i, j)) {
                        answer[k] = 0;
                        return ;
                    }
                }
            }
        }
    }
    
    public boolean bfs(int k, int x, int y) {
        boolean[][] vis = new boolean[N][N];
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        vis[x][y] = true;
        int cnt = 0;
        
        while(!q.isEmpty() && cnt < 2) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny]) continue;
                    if(board[k][nx][ny] == 'X') continue;
                    if(board[k][nx][ny] == 'P') return false;
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
                cnt++;
            }
        }

        return true;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<N;
    }
    
    public void init(String[][] places) {
        board = new char[N][N][N];
        for(int k=0; k<N; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    board[k][i][j] = places[k][i].charAt(j);
                }
            }
        }
        answer = new int[N];
        Arrays.fill(answer, 1);
    }
}