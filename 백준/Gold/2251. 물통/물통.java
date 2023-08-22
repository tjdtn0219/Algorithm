import java.io.*;
import java.util.*;

public class Main {

    public static boolean vis[][] = new boolean[201][201];
    public static Queue<State> q = new LinkedList<>();
    public static int A,B,C;
    public static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        A = Integer.parseInt(strings[0]);
        B = Integer.parseInt(strings[1]);
        C = Integer.parseInt(strings[2]);

        q.add(new State(0, 0, C));
        vis[0][0] = true;

        bfs();

        Collections.sort(ans);

        for(int n : ans) System.out.print(n + " ");

    }

    public static void bfs() {
        while(!q.isEmpty()) {
            State polled = q.poll();
            int curA = polled.a;
            int curB = polled.b;
            int curC = polled.c;

            if(curA==0) ans.add(curC);

            int water = 0;
            //A -> B
            water = Math.min(curA, B-curB);
            pour(curA-water, curB+water, curC);
            //A -> C
            water = Math.min(curA, C-curC);
            pour(curA-water, curB, curC+water);
            //B -> A
            water = Math.min(curB, A-curA);
            pour(curA+water, curB-water, curC);
            //B -> C
            water = Math.min(curB, C-curC);
            pour(curA, curB-water, curC+water);
            //C -> A
            water = Math.min(curC, A-curA);
            pour(curA+water, curB, curC-water);
            //C -> B
            water = Math.min(curC, B-curB);
            pour(curA, curB+water, curC-water);

        }

    }

    public static void pour(int a, int b, int c) {
        if(!vis[a][b]) {
            vis[a][b] = true;
            q.add(new State(a, b, c));
        }
    }
}

class State {
    int a;
    int b;
    int c;

    public State(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}