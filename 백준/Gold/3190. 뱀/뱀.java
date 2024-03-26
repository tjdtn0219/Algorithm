import java.io.*;
import java.util.*;

public class Main {

    public static int[] dx = {1,0,-1,0};    //남,동,북,서
    public static int[] dy = {0,1,0,-1};

    int n;
    int[][] map;
    List<Point> apples;
    int[] directions;
    int curT;
    int curLen;
    Deque<Point> snake;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            apples = new ArrayList<>();
            int k = Integer.parseInt(br.readLine());
            for(int i=0; i<k; i++) {
                String[] tmp = br.readLine().split(" ");
                int x = Integer.parseInt(tmp[0]);
                int y = Integer.parseInt(tmp[1]);
                apples.add(new Point(x-1, y-1));
            }
            int l = Integer.parseInt(br.readLine());
            directions = new int[n*n+5];
            int lastT = 0;
            int lastDir = 1;    //동쪽
            for(int i=0; i<l; i++) {
                String[] tmp = br.readLine().split(" ");
                int t = Integer.parseInt(tmp[0]);
                char dir = tmp[1].charAt(0);
                Arrays.fill(directions, lastT, t, lastDir);
                lastT = t;
                lastDir = changeDirection(dir, lastDir);
            }
            Arrays.fill(directions, lastT, directions.length, lastDir);

            curT = 0;
            curLen = 0;
            snake = new LinkedList<>();
            snake.add(new Point(0, 0));
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public int changeDirection(char dir, int cur) {
        //0 1 2 3
        //남 동 북 서
        if(dir == 'L') {
            return (cur + 1) % 4;
        } else {
            return (cur + 3) % 4;
        }
    }

    public void printDirections() {
        for(int dir : directions) {
            System.out.print(dir + " ");
        }
        System.out.println();
    }

    public void printSnake() {
        StringBuilder sb = new StringBuilder();
        int[][] tmp = new int[n][n];
        for(Point point : snake) {
            tmp[point.x][point.y] = 1;
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(tmp[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        System.out.println("============================");
    }

    public void solve() {
        while(true) {
            if(!move()) break;
            curT++;
        }
//        System.out.println("curT : " + curT);
        System.out.println(curT+1);
    }

    public boolean move() {
        int dir = directions[curT];
        Point head = snake.getFirst();
        Point nxtPoint = new Point(head.x + dx[dir], head.y + dy[dir]);
        if(!isInner(nxtPoint.x, nxtPoint.y)) {
            return false;
        }
        if(isContainOwnBody(nxtPoint.x, nxtPoint.y)) {
            return false;
        }
        int appleIdx = -1;
        for(int i=0; i<apples.size(); i++) {
            Point apple = apples.get(i);
            if(nxtPoint.x == apple.x && nxtPoint.y == apple.y) {
                appleIdx = i;
            }
        }
        snake.addFirst(nxtPoint);
        if(appleIdx == -1) {
            snake.removeLast();
        } else {
            apples.remove(appleIdx);
        }

        return true;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }

    public boolean isContainOwnBody(int x, int y) {
        for(Point point : snake) {
            if(point.x == x && point.y == y) return true;
        }
        return false;
    }

}

//class Body {
//    Point point;
//    int dir;
//    public Body(Point point, int dir) {
//        this.point = point;
//        this.dir = dir;
//    }
//}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}