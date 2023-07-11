import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

//        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->{
            return o2 - o1;
        });

        for(int i=0; i<n; i++) {
            String[] strings = bf.readLine().split(" ");
            for(int j=0; j<n; j++) {
                pq.add(Integer.parseInt(strings[j]));
            }
        }

        for(int i=0; i<n-1; i++) {
            pq.poll();
        }

        System.out.println(pq.poll());
    }
}