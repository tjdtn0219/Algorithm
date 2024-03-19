import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {-1,-1,0,1,1,1,0,-1};
    static final int[] DY = {0,1,1,1,0,-1,-1,-1};
    static final int MAX_LEN = 5;

    int n, m, k;
    char[][] board;
    String[] words;
    HashMap<String, Integer> wordMap;

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
            String[] tmp = br.readLine().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            k = Integer.parseInt(tmp[2]);
            board = new char[n][m];
            words = new String[k];
            wordMap = new HashMap<>();
            for(int i=0; i<n; i++) {
                String str = br.readLine();
                for(int j=0; j<m; j++) {
                    board[i][j] = str.charAt(j);
                }
            }
            for(int i=0; i<k; i++) {
                words[i] = br.readLine();
                wordMap.put(words[i], 0);
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bfs(new Point(i, j));
            }
        }
        for(String word : words) {
            System.out.println(wordMap.get(word));
        }

    }

    public void bfs(Point startPoint) {
        Queue<Trace> q = new LinkedList<>();
        q.add(new Trace(startPoint, "" + board[startPoint.x][startPoint.y]));
        int cnt = 0;

        while(!q.isEmpty() && cnt < MAX_LEN) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Trace curTrace = q.poll();
                String curStr = curTrace.str;
                putMap(curStr);
                Point curPoint = curTrace.point;
                for(int dir=0; dir<8; dir++) {
                    int nx = curPoint.x + DX[dir];
                    int ny = curPoint.y + DY[dir];
                    nx = convertX(nx);
                    ny = convertY(ny);
                    q.add(new Trace(new Point(nx, ny), curTrace.str + board[nx][ny]));
                }
            }
            cnt++;
        }
    }

    public void putMap(String str) {
        if(wordMap.containsKey(str)) {
            wordMap.put(str, wordMap.getOrDefault(str, 0) + 1);
        }
    }

    public int convertX(int x) {
        if(x<0) return n-1;
        if(x==n) return 0;
        return x;
    }

    public int convertY(int y) {
        if(y<0) return m-1;
        if(y==m) return 0;
        return y;
    }
}

class Trace {
    Point point;
    String str;
    public Trace(Point point, String str) {
        this.point = point;
        this.str = str;
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