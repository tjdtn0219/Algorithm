import java.io.*;
import java.util.PriorityQueue;

public class Main {

    public static final int MAX = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int l = Integer.parseInt(strings[1]);

        PriorityQueue<Hole> pq = new PriorityQueue<>((o1, o2)->{
            if(o1.s==o2.s) return o1.e-o2.e;
            return o1.s-o2.s;
        });

        for(int i=0; i<n; i++) {
            strings = br.readLine().split(" ");
            int s = Integer.parseInt(strings[0]);
            int e = Integer.parseInt(strings[1]);
            pq.add(new Hole(s, e));
        }

        int cnt = 0;
        int nulpanTailIdx = 0;
        while(!pq.isEmpty()) {
            Hole hole = pq.poll();
//            System.out.println(hole.s + " " + hole.e);

            if(nulpanTailIdx < hole.s) nulpanTailIdx = hole.s;

            if(hole.e > nulpanTailIdx) {
                while(hole.e > nulpanTailIdx) {
                    nulpanTailIdx += l;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }
}

class Hole {
    int s;
    int e;

    public Hole(int s, int e) {
        this.s = s;
        this.e = e;
    }
}