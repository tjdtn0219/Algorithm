import java.util.*;

class Solution {
    
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,1,0,-1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int n = maps.length;
        int m = maps[0].length;
        int[][] dis = new int[n][m];
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        
        int L = 1;
        
        while(!q.isEmpty()) {
            Pair polled = q.poll();
                int x = polled.x;
                int y = polled.y;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                    if(dis[nx][ny] > 0 || maps[nx][ny]==0) continue;
                    dis[nx][ny] = dis[x][y] + 1;
                    q.add(new Pair(nx, ny));
                }
        }
        
        if(dis[n-1][m-1]==0) answer = -1;
        else answer = dis[n-1][m-1] + 1;
        return answer;
    }
}

class Pair{
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}