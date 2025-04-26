import java.util.*;

class Solution {
    
    int n;
    int[][] map;
    
    public int[] solution(int n) {
        init(n);
        return solve();
    }
    
    public int[] solve() {
        doCycle(0, 0, n-1, 1);
        
        // printMap();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] > 0) list.add(map[i][j]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void doCycle(int x, int y, int len, int cur) {
        // System.out.println(x + ", " + y + ", len : " + len);
        if(len < 0) {
            return ;
        }
        if(len == 0) {
            map[x][y] = cur;
            return ;
        }
        
        for(int i=0; i<len; i++) {
            map[x++][y] = cur++;
        }
        
        for(int i=0; i<len; i++) {
            map[x][y++] = cur++;
        }
        
        for(int i=0; i<len; i++) {
            map[x--][y--] = cur++;
        }
        
        doCycle(x+2, y+1, len-3, cur);        
    }
    
    private void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int num : map[i]) {
                if(num > 0) sb.append(num).append(" ");
                else sb.append("  ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void init(int n) {
        this.n = n;
        this.map = new int[n][n];
    }
}