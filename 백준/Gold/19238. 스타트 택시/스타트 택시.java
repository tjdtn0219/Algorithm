import java.io.*;
import java.util.*;

public class Main {

    final int[] dx = {1,0,-1,0};
    final int[] dy = {0,1,0,-1};
    int n, m, k;
    int[][] map;
    List<Point> startList;
    List<Point> endList;
    Point taxi;


    public static void main(String[] args) {
        new Main().solution();

    }

    public void solution() {
        input();
        System.out.println(solve());
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] strings = br.readLine().split(" ");
            n = Integer.parseInt(strings[0]);
            m = Integer.parseInt(strings[1]);
            k = Integer.parseInt(strings[2]);
            map = new int[n][n];
            for(int i=0; i<n; i++) {
                strings = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(strings[j]);
                    if(map[i][j] == 1) map[i][j] = -1;
                }
            }
            strings = br.readLine().split(" ");
            taxi = new Point(Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1])-1);
            startList = new ArrayList<>();
            endList = new ArrayList<>();
            for(int i=0; i<m; i++) {
                strings = br.readLine().split(" ");
                int si = Integer.parseInt(strings[0]);
                int sj = Integer.parseInt(strings[1]);
                int ei = Integer.parseInt(strings[2]);
                int ej = Integer.parseInt(strings[3]);
                startList.add(new Point(si-1, sj-1));
                endList.add(new Point(ei-1, ej-1));
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public int solve() {
        int size = startList.size();
        int fuel = k;
//        System.out.println("init fuel : " + fuel);
        for(int i=0; i<size; i++) {
//            System.out.println("현재 택시 : " + taxi.x + " , " + taxi.y);
            Passenger nearestPassenger = getNearestPassenger();
            if(nearestPassenger == null) return -1; //택시가 다음 출발지로 이동할 수 없음
//            System.out.println("가장 가까운 승객까지의 거리 : " + nearestPassenger.point.x + " , " + nearestPassenger.point.y +" dis : " + nearestPassenger.dis);
            fuel -= nearestPassenger.dis;
//            System.out.println("승객 태운 후 fuel : " + fuel);
            int dis = getDistance(nearestPassenger.point, endList.get(nearestPassenger.idx));
            if(dis < 0) return -1;          //도착지로 이동 시킬 수 없음
            if(fuel < dis) return -1;       //도착지로 갈 연료 없음
            fuel += dis;
//            System.out.println("승객 도착지까지 이동 시킨 후 fuel : " + fuel);
            taxi.x = endList.get(nearestPassenger.idx).x;
            taxi.y = endList.get(nearestPassenger.idx).y;
            deletePoint(nearestPassenger.idx);
//            System.out.println("after fuel : " + fuel);
//            System.out.println("====================");
        }
        return fuel;

    }

    public int getDistance(Point start, Point end) {
        boolean[][] vis = new boolean[n][n];
        vis[start.x][start.y] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point polled = q.poll();
//                System.out.println("taxi : " + taxi.x + " , " + taxi.y + " polled : " + polled.x + ", " + polled.y);
                if (polled.x == end.x && polled.y == end.y) return cnt;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = polled.x + dx[dir];
                    int ny = polled.y + dy[dir];
                    if (!isInner(nx, ny)) continue;
                    if (map[nx][ny] == -1 || vis[nx][ny]) continue;
                    vis[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
            cnt++;
        }
        return -1;
    }

    public void deletePoint(int idx) {
        startList.remove(idx);
        endList.remove(idx);
    }

    public Passenger getNearestPassenger() {
        PriorityQueue<Passenger> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.dis == o2.dis) {
                if (o1.point.x == o2.point.x) return o1.point.y - o2.point.y;
                return o1.point.x - o2.point.x;
            }
            return o1.dis - o2.dis;
        });

        for(int i=0; i<startList.size(); i++) {
            Point start = startList.get(i);
            map[start.x][start.y] = i+1;
        }
        boolean[][] vis = new boolean[n][n];
        vis[taxi.x][taxi.y] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(taxi);
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point polled = q.poll();
                if (map[polled.x][polled.y] > 0) {
                    int idx = map[polled.x][polled.y];
                    pq.add(new Passenger(idx-1, polled, cnt));
                }
                for (int dir = 0; dir < 4; dir++) {
                    int nx = polled.x + dx[dir];
                    int ny = polled.y + dy[dir];
                    if (!isInner(nx, ny)) continue;
                    if (map[nx][ny] == -1 || vis[nx][ny]) continue;
                    vis[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
            cnt++;
        }
        for(int i=0; i<startList.size(); i++) {
            Point start = startList.get(i);
            map[start.x][start.y] = 0;
        }
        return pq.poll();
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }

}

class Passenger {
    int idx;
    Point point;
    int dis;
    public Passenger(int idx, Point point, int dis) {
        this.idx = idx;
        this.point = point;
        this.dis = dis;
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