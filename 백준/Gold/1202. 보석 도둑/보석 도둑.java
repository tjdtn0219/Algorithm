import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bf.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int k = Integer.parseInt(strings[1]);

        List<Jewel> jList = new ArrayList<>();
        int[] bags = new int[k];

        for(int i=0; i<n; i++) {
            strings = bf.readLine().split(" ");
            int w = Integer.parseInt(strings[0]);
            int c = Integer.parseInt(strings[1]);
            jList.add(new Jewel(w, c));
        }

        for(int i=0; i<k; i++) {
            bags[i] = Integer.parseInt(bf.readLine());
        }
        /* *********입력********* */

        Collections.sort(jList, (o1,o2)->{
            if(o1.weight == o2.weight) return o1.cost - o2.cost;
            return o1.weight - o2.weight;
        });
        Arrays.sort(bags);

        long ans = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());    //최대 힙 우선순위 큐
        //우선순위 큐를 안쓴다면, for문을 돌리면서,
        // jewel.weight<=bag 에서 jewel.weight > bag인 경계 부분을 찾는 구현이 복잡해짐
        for(int i=0,j=0; i<k; i++) {
            while(j<n && jList.get(j).weight <= bags[i]) {
                pq.add(jList.get(j++).cost);
            }

            if(!pq.isEmpty()) ans += pq.poll();
        }

        System.out.println(ans);
   }
}

class Jewel {
    int weight;
    int cost;

    public Jewel(int w, int c) {
        this.weight = w;
        this.cost = c;
    }
}