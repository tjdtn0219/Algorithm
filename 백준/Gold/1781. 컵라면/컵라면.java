import java.io.*;
import java.util.*;

public class Main {
	
    static final int MAX = 200_000;

    int n;
    LinkedList<Node> list;

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
            list = new LinkedList<>();
            n = Integer.parseInt(br.readLine());
            for(int i=0; i<n; i++) {
                String[] d_c = br.readLine().split(" ");
                int deadLine = Integer.parseInt(d_c[0]);
                int cups = Integer.parseInt(d_c[1]);
                list.add(new Node(deadLine, cups));
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            // if(o1.cups == o2.cups) {
            //     return o2.deadLine - o1.deadLine;
            // }
            return o2.cups - o1.cups;
        });

        Collections.sort(list, (o1, o2) -> {
            // if(o1.deadLine == o2.deadLine) {
            //     return o2.cups - o1.cups;
            // }
            return o2.deadLine - o1.deadLine;
        });

        long ans = 0;
        for(int days=MAX; days>=1; days--) {
            while(!list.isEmpty() && list.getFirst().deadLine == days) {
                pq.add(list.poll());
            }

            if(!pq.isEmpty()) {
                ans += pq.poll().cups;
            }
        }
        System.out.println(ans);

    }
 
}

class Node {
    int deadLine;
    int cups;
    public Node(int deadLine, int cups) {
        this.deadLine = deadLine;
        this.cups = cups;
    }
}
