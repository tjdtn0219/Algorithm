import java.util.*;

class Solution {
    
    static final String[] D = {"d", "l", "r", "u"};
    static final int[] DX = {1, 0, 0, -1};
    static final int[] DY = {0, -1, 1, 0};
        
    int n, m, x, y, r, c, k;
    String answer;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        init(n, m, x, y, r, c, k);
        solve();
        return answer;
    }
    
    public void solve() {
        int dis = getDistance(x, y, r, c);
        if(dis > k) {
            answer = "impossible";
        } else if(dis%2 != k%2) {
            answer = "impossible";
        } else {
            dfs(0, x, y, "");
        }
    }
    
    public void dfs(int dep, int x, int y, String cmd) {
        // System.out.println("dep : " + dep + " | x, y : " + x + ", " + y);
        if(answer != null) {
            // System.out.println("TAG");
            return ;
        }
        if(dep + getDistance(x, y, r, c) > k) {
            return ;
        }
        if(dep == k) {
            // System.out.println("x, y : " + x + ", " + y);
            if(x == r && y == c) {
                answer = cmd;
            }
            return ;
        }
        
        for(int dir=0; dir<4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            dfs(dep+1, nx, ny, cmd + D[dir]);
        }
        
    }
    
    public boolean isInner(int x, int y) {
        return 0<x && 0<y && x<=n && y<=m;
    }
    
    public int getDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.abs(x1 - x2) + (int) Math.abs(y1 - y2);
    }
    
    public void init(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = c;
        this.k = k;
        this.answer = null;
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