import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 200_000;

    int n;
    LinkedList<Problem> problems;
    PriorityQueue<Problem> pq;

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
            problems = new LinkedList<>();
            for(int i=0; i<n; i++) {
                String[] a_b = br.readLine().split(" ");
                int a = Integer.parseInt(a_b[0]);
                int b = Integer.parseInt(a_b[1]);
                problems.add(new Problem(a, b));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Collections.sort(problems, (o1, o2) -> {
            if(o1.deadLine == o2.deadLine) {
                return o2.ramen - o1.ramen;
            }
            return o2.deadLine - o1.deadLine;
        });

        pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.ramen == o2.ramen) {
                return o2.deadLine - o1.deadLine;
            }
            return o2.ramen - o1.ramen;
        });

        int ans = 0;

        for(int time = MAX; time>=1; time--) {
            // System.out.println("curTime : " + time + " ,problem.deadLine : " + problems.getFirst().deadLine);
            while(!problems.isEmpty() && problems.peek().deadLine == time) {
                pq.add(problems.pollFirst());
            }
            if(!pq.isEmpty()) {
                ans += pq.poll().ramen;
            }
            // System.out.println("ans : " + ans);
        }
        
        System.out.println(ans);
    }
}

class Problem {
    int deadLine;
    int ramen;

    public Problem(int deadLine, int ramen) {
        this.deadLine = deadLine;
        this.ramen = ramen;
    }
}