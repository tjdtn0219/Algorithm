import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    int n, m;
    int[][] map;
    List<Point> chickenPoints;
    List<Point> housePoints;
    int[] comb;
    int answer;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        makeComb(0, 0);
//        System.out.println(getNearestDistance(1, 0, chickenPoints));
        System.out.println(answer);
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            map = new int[n][n];
            housePoints = new ArrayList<>();
            chickenPoints = new ArrayList<>();
            for(int i=0; i<n; i++) {
                tmp = br.readLine().split(" ");
                for(int j = 0; j< n; j++) {
                    map[i][j] = Integer.parseInt(tmp[j]);
                    if(map[i][j] == 1) housePoints.add(new Point(i, j));
                    if(map[i][j] == 2) chickenPoints.add(new Point(i, j));
                }
            }
            comb = new int[m];
            answer = Integer.MAX_VALUE;
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void makeComb(int k, int lastIdx) {
        if(k == m) {
            List<Point> availPoints = getAvailChickenPoints();
            answer = Math.min(answer, getNearestDistanceSum(availPoints));
            return ;
        }

        for(int i=lastIdx; i<chickenPoints.size(); i++) {
            comb[k] = i;
            makeComb(k+1, i+1);
        }
    }

    public List<Point> getAvailChickenPoints() {
        List<Point> list = new ArrayList<>();
        for(int num : comb) {
            list.add(chickenPoints.get(num));
        }
        return list;
    }

    public int getNearestDistanceSum(List<Point> chickenPoints) {
        int sum = 0;
        for(Point housePoint : housePoints) {
            int min = Integer.MAX_VALUE;
            for(Point chickenPoint : chickenPoints) {
                min = Math.min(min, Math.abs(chickenPoint.x-housePoint.x) + Math.abs(chickenPoint.y-housePoint.y));
            }
//            System.out.println("X : " + housePoint.x + " y : " + housePoint.y + " min : " + min);
            sum += min;
        }
        return sum;
    }

    public boolean isArriveChickenHouse(int x, int y, List<Point> chickenPoints) {
        for(Point point : chickenPoints) {
            if(x == point.x && y==point.y) {
                return true;
            }
        }
        return false;
    }

    public boolean isInner(int x, int y) {
        return x>=0 && y>=0 && x<n && y<n;
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