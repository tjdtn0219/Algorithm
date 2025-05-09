import java.awt.*; //Point는 이거 쓰기!!
import java.util.*;
import java.util.List;

class Robot {
    Point p1, p2;
    int d;

    Robot(Point p1, Point p2, int d) {
        this.p1 = p1;
        this.p2 = p2;
        this.d = d;
    }
    
    @Override
    public boolean equals(Object o) {
        Robot robot = (Robot) o;
        return (p1.equals(robot.p1) && p2.equals(robot.p2)) || (p1.equals(robot.p2) && p2.equals(robot.p1));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}

class Solution {
    int N, ans;
    int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    int[][] map;
    Set<Robot> set = new HashSet<>();

    public int solution(int[][] board) {
        HashSet<Point> tmp = new HashSet<>();
        tmp.add(new Point(1,0));
        tmp.add(new Point(1,0));
        tmp.add(new Point(1,0));
        tmp.add(new Point(1,0));
        tmp.add(new Point(2,0));
        System.out.println("Test : " + tmp.size());
        
        N = board.length;
        map = new int[N + 2][N + 2];
        for(int i = 0; i <= N + 1; i++)
            Arrays.fill(map[i], 1);

        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                map[i][j] = board[i - 1][j - 1];

        Point E = new Point(N, N);
        Queue<Robot> q = new LinkedList<>();
        Robot S = new Robot(new Point(1, 1) ,new Point(2, 1), 0);
        q.add(S);
        set.add(S);

        while(!q.isEmpty()) {
            Robot cur = q.poll();

            if(cur.p1.equals(E) || cur.p2.equals(E)) {
                ans = cur.d;
                break;
            }

            for(Robot next : getNext(cur)) {
                if(!set.contains(next)) {
                    set.add(next);
                    q.add(next);
                }
            }
        }

        return ans;
    }

    public List<Robot> getNext(Robot cur) {
        Point p1 = cur.p1, p2 = cur.p2;
        List<Robot> next = new ArrayList<>();
        // 4방향
        for(int d = 0; d < 4; d++) {
            Point np1 = new Point(p1.x + dx[d], p1.y + dy[d]);
            Point np2 = new Point(p2.x + dx[d], p2.y + dy[d]);

            if(map[np1.y][np1.x] == 1 || map[np2.y][np2.x] == 1)
                continue;
            next.add(new Robot(np1, np2, cur.d + 1));
        }

        // 가로 -> 세로
        int[] ud = {-1, 1};
        if(p1.y == p2.y) {
            for (int d : ud) {
                if (map[p1.y + d][p1.x] == 0 && map[p2.y + d][p2.x] == 0) {
                    next.add(new Robot(new Point(p1.x, p1.y + d), p1, cur.d + 1));
                    next.add(new Robot(new Point(p2.x, p2.y + d), p2, cur.d + 1));
                }
            }
        } else { // 세로 -> 가로
            for(int d : ud) {
                if(map[p1.y][p1.x + d] == 0 && map[p2.y][p2.x + d] == 0) {
                    next.add(new Robot(new Point(p1.x + d, p1.y), p1, cur.d + 1));
                    next.add(new Robot(new Point(p2.x + d, p2.y), p2, cur.d + 1));
                }
            }
        }

        return next;
    }
}


