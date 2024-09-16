import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 2002;

    int s;
    boolean[][] vis;

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
            s = Integer.parseInt(br.readLine());
            vis = new boolean[MAX][MAX];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        System.out.println(bfs());
    }

    public int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 0));
        vis[1][0] = true;

        int time = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Node cur = q.poll();
                if(cur.screen == s) {
                    return time;
                }

                if(isInner(cur.screen, cur.screen) && !vis[cur.screen][cur.screen]) {
                    //화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
                    q.add(new Node(cur.screen, cur.screen));
                    vis[cur.screen][cur.screen] = true;
                }

                if(isInner(cur.screen + cur.clipBoard, cur.clipBoard) && !vis[cur.screen + cur.clipBoard][cur.clipBoard]) {
                    //클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
                    q.add(new Node(cur.screen + cur.clipBoard, cur.clipBoard));
                    vis[cur.screen + cur.clipBoard][cur.clipBoard] = true;
                }

                if(isInner(cur.screen - 1, cur.clipBoard) && !vis[cur.screen - 1][cur.clipBoard]) {
                    //화면에 있는 이모티콘 중 하나를 삭제한다.
                    q.add(new Node(cur.screen - 1, cur.clipBoard));
                    vis[cur.screen - 1][cur.clipBoard] = true;
                }
            }
            time++;
            
        }

        return -1;
    }

    public boolean isInner(int screen, int clipBoard) {
        return 0<=screen && 0<=clipBoard && screen<MAX && clipBoard<MAX;
    }

}

class Node {
    int screen;
    int clipBoard;

    public Node(int screen, int clipBoard) {
        this.screen = screen;
        this.clipBoard = clipBoard;
    }
}