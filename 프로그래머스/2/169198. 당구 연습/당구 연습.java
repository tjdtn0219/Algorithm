import java.util.*;

class Solution {
    
    int n, m;
    int sx, sy;
    int[][] balls;
    int[] answer;
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {

        init(n, m, startX, startY, balls);
        solve();
        return answer;
    }
    
    public void solve() {
        // System.out.println("sx , sy : " + sx + ", " + sy);
        for(int i=0; i<balls.length; i++) {
            // System.out.println("balls : " + balls[i][0] + ", " + balls[i][1]);
            // answer[i] = getMinDistance(balls[i][0], balls[i][1]);
            List<int[]> symPoints = getSymPoints(balls[i][0], balls[i][1]);
            int minDistance = Integer.MAX_VALUE;
            for(int[] point : symPoints){
                int distance = calDistance(sx, sy, point[0], point[1]);
                
                minDistance = Math.min(minDistance, distance);
            }
            answer[i] = minDistance;
        }
    }
    
    public int getMinDistance(int ex, int ey) {
        HashSet<Integer> dirSet = new HashSet<>();
        for(int d=1; d<=4; d++) {
            dirSet.add(1);
        }
        if(sy == ey) {
            if(sx < ex) {
                dirSet.remove(2);
            } else {
                dirSet.remove(4);
            }
        }
        if(sx == ex) {
            if(sy < ey) {
                dirSet.remove(1);
            } else {
                dirSet.remove(3);
            }
        }
        
        int minDis = Integer.MAX_VALUE;;
        for(int dir : dirSet) {
            int[] point;
            if(dir == 1) {
                point = getSymPoint1(ex, ey);
            } else if(dir == 2) {
                point = getSymPoint2(ex, ey);
            } else if(dir == 3) {
                point = getSymPoint3(ex, ey);
            } else {
                point = getSymPoint4(ex, ey);
            }
            int dis = calDistance(sx, sy, point[0], point[1]);
            // System.out.println("dir : " + dir + ", x, y : " + point[0] + ", " + point[1] + ", dis : " + dis);
            minDis = Math.min(minDis, dis);
        }
        // System.out.println("minDis : " + minDis);
        return minDis;
    }
    
    public List<int[]> getSymPoints(int ex, int ey){
        List<int[]> syms = new ArrayList<>();

        // 4개의 방향으로 대칭이동
        // 선 대칭일 때, 벽보다 공에 먼저 맞는 경우 제외
        if(!(sx == ex && sy > ey)) syms.add(new int[]{ex, -ey});
        if(!(sx == ex && sy < ey)) syms.add(new int[]{ex, 2*n - ey});
        if(!(sx < ex && sy == ey)) syms.add(new int[]{2*m - ex, ey});
        if(!(sx > ex && sy == ey)) syms.add(new int[]{-ex, ey});

        return syms;
    }
    
    public int calDistance(int x1, int y1, int x2, int y2) {
        // return (int) Math.pow((x2-x1), 2) + (int) Math.pow((y2-y1), 2);
        int deltaX = x1 - x2;
        int deltaY = y1 - y2;

        return deltaX * deltaX + deltaY * deltaY;
    }
    
    public int[] getSymPoint1(int x, int y) {
        int ny = n + (n-y);
        return new int[]{x, ny};
    }
    
    public int[] getSymPoint2(int x, int y) {
        int nx = m + (m-x);
        return new int[]{nx, y};
    }
    
    public int[] getSymPoint3(int x, int y) {
        int ny =  -y;
        return new int[]{x, ny};
    }
    
    public int[] getSymPoint4(int x, int y) {
        int nx = -x;
        return new int[]{nx, y};
    }
    
    public void init(int n, int m, int sx, int sy, int[][] balls) {
        this.n = n;
        this.m = m;
        this.sx = sx;
        this.sy = sy;
        this.balls = balls;
        this.answer = new int[balls.length];
    }
    
    
}