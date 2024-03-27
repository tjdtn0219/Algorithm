import java.util.*;

class Solution {
    
    static final int[] dx = {1,0,-1,0}; //남, 동, 북, 서
    static final int[] dy = {0,1,0,-1};
    
    int n, m;
    char[][] map;
    boolean[][][] lights;
    List<Integer> answer;
    
    public int[] solution(String[] grid) {
        init(grid);
        solve();
        return answer.stream().sorted().mapToInt(i -> i).toArray();
    }
    
    public void init(String[] grid) {
        n = grid.length;
        m = grid[0].length();
        map = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = grid[i].charAt(j);
            }
        }
        lights = new boolean[n][m][4];
        answer = new ArrayList<>();
    }
    
    public void solve() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int dir=0; dir<4; dir++) {
                    if(lights[i][j][dir]) continue;
                    answer.add(startLight(dir, i, j));
                }       
            }
        }
    }
    
    public int startLight(int dir, int x, int y) {
        int cnt = 0;
        
        while(true) {
            if(lights[x][y][dir]) break;
            lights[x][y][dir] = true;
            int nx = convertX(x + dx[dir]);
            int ny = convertY(y + dy[dir]);
            dir = getNxtDir(map[nx][ny], dir);
            x = nx;
            y = ny;
            cnt++;
        }
        return cnt;
    }
    
    public int getNxtDir(char c, int curDir) {
        if(c=='S') return curDir;
        else if(c=='L') return (curDir+1) % 4;
        else if(c=='R') return (curDir+3) % 4;
        return curDir;
    }
    
    public int convertX(int x) {
        if(x >= n) return 0;
        else if(x < 0) return n-1;
        return x;
    }
    
    public int convertY(int y) {
        if(y >= m) return 0;
        else if(y < 0) return m-1;
        return y;
    }
    
}