import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Lecture> list = new ArrayList<>();
//        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)->o2.e-o1.e);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            int s = Integer.parseInt(strings[0]);
            int e = Integer.parseInt(strings[1]);
            list.add(new Lecture(s, e));
        }

        Collections.sort(list, (o1,o2)->{
            if(o1.s==o2.s) return o1.e-o2.e;
            return o1.s-o2.s;
        });

        int ans = 0;

        for(Lecture lecture : list) {
            if(pq.isEmpty()) {
                pq.add(lecture.e);
                ans++;
                continue;
            }
            if(pq.peek() > lecture.s) {    //현재 강의 전에 끝나는 강의가 없을 경우
                ans++;
            } else {
                pq.poll();
            }
            pq.add(lecture.e);

        }

        System.out.println(ans);

    }
}

class Lecture {
    int s;
    int e;
    public Lecture(int s, int e) {
        this.s = s;
        this.e = e;
    }
}
