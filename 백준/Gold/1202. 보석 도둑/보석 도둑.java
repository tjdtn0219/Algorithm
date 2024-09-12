import java.io.*;
import java.util.*;

public class Main {

    int N;
    int K;
    LinkedList<Jewer> jewerList;
    int[] bags;

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
            String[] N_K = br.readLine().split(" ");
            N = Integer.parseInt(N_K[0]);
            K = Integer.parseInt(N_K[1]);
            jewerList = new LinkedList<>();
            for(int i=0; i<N; i++) {
                String[] m_v = br.readLine().split(" ");
                int m = Integer.parseInt(m_v[0]);
                int v = Integer.parseInt(m_v[1]);
                jewerList.add(new Jewer(m, v));
            }
            bags = new int[K];
            for(int i=0; i<K; i++) {
                bags[i] = Integer.parseInt(br.readLine());
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Arrays.sort(bags);
        Collections.sort(jewerList, (o1, o2) -> {
            if(o1.m == o2.m) {
                return o2.v - o1.v;
            }
            return o1.m - o2.m;
        });

        PriorityQueue<Jewer> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.v == o2.v) {
                return o1.m - o2.m;
            }
            return o2.v - o1.v;
        });

        long ans = 0;
        for(int weight : bags) {
            while(!jewerList.isEmpty() && jewerList.peek().m <= weight){
                pq.add(jewerList.pollFirst());
            }

            if(pq.isEmpty()) continue;
            ans += pq.poll().v;
        }

        System.out.println(ans);
    }

}

class Jewer {
    int m;
    int v;
    public Jewer(int m, int v) {
        this.m = m;
        this.v = v;
    }
}