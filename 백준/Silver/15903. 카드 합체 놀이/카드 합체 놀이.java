import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        strings = br.readLine().split(" ");
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i=0; i<n; i++) pq.add(Long.parseLong(strings[i]));

        for(int i=0; i<m; i++) {
            long x = pq.poll();
            long y = pq.poll();
            pq.add(x+y);
            pq.add(x+y);
        }

        long sum = 0;
        while(!pq.isEmpty()) sum += pq.poll();

        System.out.println(sum);

    }
}