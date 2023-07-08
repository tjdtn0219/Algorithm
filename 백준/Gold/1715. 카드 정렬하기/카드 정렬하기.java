import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(bf.readLine());

        for(int i=0; i<n; i++) {
            pq.add(Integer.parseInt(bf.readLine()));
        }

        long ans = 0;

        while(pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            ans += a+b;
            pq.add(a+b);
        }

        System.out.println(ans);

    }
}