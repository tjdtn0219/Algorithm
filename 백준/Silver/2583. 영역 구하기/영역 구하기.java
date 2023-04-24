import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int map[][];
    public static boolean vis[][];
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,-1,0,1};
    public static int M, N;

    public static Queue<Pair> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new  InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = bf.readLine().split(" ");

        M = Integer.parseInt(strings[0]);
        N = Integer.parseInt(strings[1]);
        int K = Integer.parseInt(strings[2]);

        map = new int[M][N];
        vis = new boolean[M][N];

        for(int k=0; k<K; k++) {
            strings = bf.readLine().split(" ");
            Pair p1 = new Pair(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
            Pair p2 = new Pair(Integer.parseInt(strings[2]), Integer.parseInt(strings[3]));
            paintMap(p1, p2);
        }

        List<Integer> areas = new ArrayList<>();
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j]==0 && vis[i][j]==false) {
                    queue.add(new Pair(i, j));
                    vis[i][j] = true;
                    int area = 1;
                    while (!queue.isEmpty()) {
                        Pair popped_pair = queue.poll();
                        int x = popped_pair.getX();
                        int y = popped_pair.getY();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = x + dx[dir];
                            int ny = y + dy[dir];
                            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                            if (map[nx][ny] > 0 || vis[nx][ny] == true) continue;
                            vis[nx][ny] = true;
                            queue.add(new Pair(nx, ny));
                            area++;
                        }
                    }
                    areas.add(area);
                }
            }
        }

        Collections.sort(areas);
        for(int a : areas) {
            sb.append(a + " ");
        }
        System.out.println(areas.size());
        System.out.println(sb);
    }

    public static void paintMap(Pair p1, Pair p2) {
        for(int i=p1.getX(); i<p2.getX(); i++) {
            for(int j=p1.getY(); j<p2.getY(); j++) {
                map[j][i] = 1;
            }
        }
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}