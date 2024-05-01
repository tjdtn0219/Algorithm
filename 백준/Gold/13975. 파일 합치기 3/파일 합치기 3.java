import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[] files;
    PriorityQueue<Long> pq;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine());
            for(int i=0; i<t; i++) {
                n = Integer.parseInt(br.readLine());
                files = new int[n];
                files = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                pq = new PriorityQueue<>((o1, o2) -> (int) (o1 - o2));
                initPQ();
                solve();
            }
        } catch (Exception e) {
            System.out.println("InPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void initPQ() {
        for(int file : files) {
            pq.add((long) file);
        }
    }

    public void solve() {
        long cost = 0;
        while(pq.size() > 1) {
            long num1 = pq.poll();
            long num2 = pq.poll();
            cost += num1 + num2;
            pq.add(num1 + num2);
        }
        System.out.println(cost);
    }
}