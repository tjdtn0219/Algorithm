import java.io.*;
import java.util.*;

public class Main {

    static final int K = 3;
    int n, m, d;
    int origin_n;
    int[][] board;
    int[][] originBoard;
    int[] comb;
    int ans;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        init();
        dfs(0, 0);
        System.out.println(ans);
    }

    public void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] strings = br.readLine().split(" ");
            n = Integer.parseInt(strings[0]);
            m = Integer.parseInt(strings[1]);
            d = Integer.parseInt(strings[2]);
            board = new int[n][m];
            originBoard = new int[n][m];
            origin_n = n;
            comb = new int[K];
            ans = 0;
            for(int i=0; i<n; i++) {
                strings = br.readLine().split(" ");
                for(int j=0; j<m; j++) {
                    board[i][j] = Integer.parseInt(strings[j]);
                    originBoard[i][j] = board[i][j];
                }
            }
        } catch(Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public void dfs(int k, int last) {
        if(k == K) {
            ans = Math.max(ans, play());
            rollBackBoard();
            return ;
        }

        for(int i=last; i<m; i++) {
            comb[k] = i;
            dfs(k+1, i+1);
        }
    }

    public void rollBackBoard() {
        n = origin_n;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                board[i][j] = originBoard[i][j];
            }
        }
    }

    public int play() {
        int cnt = 0;
        while(n >= 0) {
            List<Enemy> attackList = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                int ai = n;
                int aj = comb[i];
                Enemy enemy = getAttackEnemy(ai, aj);
                if (enemy == null) continue;
                attackList.add(enemy);
            }
            for(Enemy enemy : attackList) {
                if(board[enemy.x][enemy.y] == 0) continue;
                board[enemy.x][enemy.y] = 0;
                cnt++;
            }
            n--;
        }
        return cnt;

    }

    public Enemy getAttackEnemy(int ai, int aj) {
        PriorityQueue<Enemy> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.dis == o2.dis) {
                return o1.y - o2.y;
            }
            return o1.dis - o2.dis;
        });

        int maxI = Math.max(n-1-d, 0);
        for(int i=n-1; i>=maxI; i--) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == 0) continue;
                int dis = Math.abs(ai-i) + Math.abs(aj-j);
                if(dis <= d) {
                    pq.add(new Enemy(i, j, dis));
                }
            }
        }

        if(pq.size() != 0) return pq.poll();
        else return null;

    }
    
}

class Enemy {
    int x;
    int y;
    int dis;
    public Enemy(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}