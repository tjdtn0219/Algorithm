import java.util.*;

class Solution {
    
    final int[] dx = {1,0,-1,0};
    final int[] dy = {0,1,0,-1};
    char[][] board;
    Point sp;
    Point gp;
    int n, m;
    int answer;
    int[][] vis;
    boolean[][] visited;
    int tmp;
    
    public int solution(String[] board) {
        
        init(board);
        if(!isAbleGetGoal()) answer = -1;
        else answer = bfs();
        // else answer = dfs(0, new Point(sp.x, sp.y));
        return answer;
    }
    
    public void init(String[] arr) {
        answer = -1;
        n = arr.length;
        m = arr[0].length();
        board = new char[n][m];
        vis = new int[n][m];
        visited = new boolean[n][m];
        tmp = Integer.MAX_VALUE;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length(); j++) {
                if(arr[i].charAt(j) == 'R') {
                    sp = new Point(i, j);
                    board[i][j] = '.';
                } else if(arr[i].charAt(j) == 'G') {
                    gp = new Point(i, j);
                    board[i][j] = '.';
                } else {
                    board[i][j] = arr[i].charAt(j);    
                }
            
            }
        }
    }
    
    public boolean isAbleGetGoal() {
        for(int dir=0; dir<4; dir++) {
            int nx = gp.x + dx[dir];
            int ny = gp.y + dy[dir];
            if(!isInner(nx, ny)) return true;
            if(board[nx][ny] == 'D') return true;
        }
        return false;
    }
    
    public boolean isInner(int nx, int ny) {
        if(nx<0 || ny<0 || nx>=n || ny>=m) {
            return false;
        } 
        return true;
    }
    
    public Point getAfterMove(Point cur, int dir) {
        int x = cur.x;
        int y = cur.y;
        while(true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(!isInner(nx, ny)) break;
            if(board[nx][ny] == 'D') break;
            x += dx[dir];
            y += dy[dir];
        }
        return new Point(x, y);
    }
    
    public int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sp.x, sp.y));
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                for(int dir=0; dir<4; dir++) {
                    Point nxt = getAfterMove(cur, dir);
                    if(visited[nxt.x][nxt.y]) {
                        continue;
                    }
                    visited[nxt.x][nxt.y] = true;
                    if(isGetGoal(nxt)) {
                        return cnt; 
                    }
                    q.add(nxt);
                }
            }
        }
        return -1;
    }
    
//     public int dfs(int k, Point cur) {
//         if(k >= tmp) return -1;
        
//         if(isGetGoal(cur)) {
//             tmp = Math.min(tmp, k);
//             return k;
//         }
        
//         int min = Integer.MAX_VALUE;
//         boolean flag = false;
//         for(int dir=0; dir<4; dir++) {
//             Point nxt = getAfterMove(cur, dir);
//             if(vis[nxt.x][nxt.y]>0 && vis[nxt.x][nxt.y] <= k) continue;
//             vis[nxt.x][nxt.y]++;
//             int val = dfs(k+1, nxt);
//             vis[nxt.x][nxt.y]--;
//             if(val > 0) {
//                 flag = true;
//                 min = Math.min(min, val);
//             }
//         }
//         if(flag) return min;
//         else return -1;
//     }
    
    public boolean isGetGoal(Point cur) {
        if(cur.x == gp.x && cur.y == gp.y) return true;
        return false;
    }
}
    

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}