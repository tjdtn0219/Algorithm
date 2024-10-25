import java.util.*;

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    
    int[][] land;
    int n, m;
    HashMap<Integer, Integer> sizeMap;  //idx, size
    int[][] idxArr;
    
    private void test() {
        for(int key : sizeMap.keySet()) {
            System.out.println("key : " + key + ", size : " + sizeMap.get(key));
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(idxArr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public int solution(int[][] land) {
        init(land);
        System.out.println("n : " + n + ", m : " + m);
        setLandIdx();
        return getMaxOil();
    }
    
    public int getMaxOil() {
        int ans = 0;
        for(int j=0; j<m; j++) {
            HashSet<Integer> idxSet = new HashSet<>();
            for(int i=0; i<n; i++) {
                if(idxArr[i][j] == 0) continue;
                idxSet.add(idxArr[i][j]);
            }
            int sum = 0;
            for(int idx : idxSet) {
                sum += sizeMap.get(idx);
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
    
    public void setLandIdx() {
        int idx = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(idxArr[i][j] > 0 || land[i][j] == 0) continue;
                // System.out.println("i, j, idx : " + i + " , " + j + " : " + idx);
                bfs(i, j, idx++);
            }
        }
    }
    
    public void bfs(int x, int y, int idx) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        idxArr[x][y] = idx;
        
        int size = 1;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(idxArr[nx][ny] > 0 || land[nx][ny] == 0) continue;
                q.add(new Point(nx, ny));
                idxArr[nx][ny] = idx;
                size++;
            }
        }
        sizeMap.put(idx, size);
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
    
    public void init(int[][] land) {
        this.land = land;
        this.n = land.length;
        this.m = land[0].length;
        this.sizeMap = new HashMap<>();
        this.idxArr = new int[n][m];
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