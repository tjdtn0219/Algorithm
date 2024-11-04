import java.io.*;
import java.util.*;

public class Main {

    int n;
    List<Lecture> lectureList;
    int maxDay;

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
            lectureList = new ArrayList<>();
            maxDay = 0;
            for(int i=0; i<n; i++) {
                String[] p_d = br.readLine().split(" ");
                int p = Integer.parseInt(p_d[0]);
                int d = Integer.parseInt(p_d[1]);
                maxDay = Math.max(maxDay, d);
                lectureList.add(new Lecture(p, d));
            }
            
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        Collections.sort(lectureList, (o1, o2) -> o2.d - o1.d);
        PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.p == o2.p) {
                return o2.d - o1.d;
            }
            return o2.p - o1.p; 
        });

        int ans = 0;
        for(int day=maxDay; day>0; day--) {
            while(!lectureList.isEmpty() && lectureList.get(0).d == day) {
                // System.out.println("TAG : " + lectureList.get(0).d + " , " + lectureList.get(0).p);
                pq.add(lectureList.remove(0));
            }

            if(!pq.isEmpty()) {
                // System.out.println("+ : " + pq.peek().p);
                ans += pq.poll().p;
            }
        }
        System.out.println(ans);
    }

}

class Lecture {
    int p, d;
    public Lecture(int p, int d) {
        this.d = d;
        this.p = p;
    }
}