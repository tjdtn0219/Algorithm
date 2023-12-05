import java.util.*;

class Solution {
    
    // public char[][] map;
    public int n, m;
    public int[][] dis;
    public int[] dx = {1,0,-1,0};
    public int[] dy = {0,-1,0,1};
    // public Queue<Pair> q = new LinkedList<>();
    
    public int solution(String[] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length();
        
        int si = 0; int sj = 0;
        int li = 0; int lj = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maps[i].charAt(j)=='S') {
                    si = i; sj = j;
                }
                if(maps[i].charAt(j)=='L') {
                    li = i; lj = j;
                }
            }
        }
        
        
        dis = new int[n][m];        
        int toLever = bfs(si, sj, maps, 'L');
        if(toLever==-1) return -1;
        
        answer += toLever;
        
        System.out.println("Lever : " + toLever);
        
        dis = new int[n][m];
        int leverToEnd = bfs(li, lj, maps, 'E');
        if(leverToEnd == -1) return -1;
        
        answer += leverToEnd;
        
        
        return answer;
    }
    
    public int bfs(int i, int j, String[] maps, char tg) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        dis[i][j] = 1;
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(dis[nx][ny]>0 || maps[nx].charAt(ny)=='X') continue;
                if(maps[nx].charAt(ny)==tg) return dis[x][y];
                q.add(new Pair(nx, ny));
                dis[nx][ny] = dis[x][y] + 1;
            }
        }
        
        return -1;
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