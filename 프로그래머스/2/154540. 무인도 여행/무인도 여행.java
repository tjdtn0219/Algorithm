import java.util.*;

class Solution {
    
    public int[] dx = {1,0,-1,0};
    public int[] dy = {0,1,0,-1};
    public int n, m;
    public int[][] board;
    public boolean[][] vis;
    
    public int[] solution(String[] maps) {
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        n = maps.length;
        m = maps[0].length();
        
        vis = new boolean[n][m];
        
        board = new int[n][m];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maps[i].charAt(j)=='X') board[i][j] = -1;
                else board[i][j] = maps[i].charAt(j) - '0';
            }
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] > 0 && !vis[i][j]) {
                    int val = bfs(i,j);
                    ans.add(val);
                    // System.out.println("=====" + i + ", " + j + " : " + val + "=====");
                    // ans.add(bfs(i, j));
                }
            }
        }
        
        Collections.sort(ans);
        
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) answer[i] = ans.get(i);
        if(answer.length==0) {
            answer = new int[1];
            answer[0] = -1;
        }
        
        return answer;
        
    }
    
    public int bfs(int r, int c) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(r, c));
        vis[r][c] = true;
        int sum = 0;
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            sum += board[x][y];
            // System.out.println("picked : " + x + " , " + y);
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(board[nx][ny]==-1 || vis[nx][ny]) continue;
                q.add(new Pair(nx, ny));
                vis[nx][ny] = true;
            }
        }
        return sum;
    }
}

class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}