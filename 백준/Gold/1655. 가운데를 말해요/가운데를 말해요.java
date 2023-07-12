import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> maxHeap =  new PriorityQueue<>(Collections.reverseOrder());    //중간값 왼쪽
        PriorityQueue<Integer> minHeap =  new PriorityQueue<>();    //중간값 오른쪽

        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(bf.readLine());

            if(maxHeap.size() <= minHeap.size()) maxHeap.add(num);
            else minHeap.add(num);

            if(!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                minHeap.add(maxHeap.poll());
                maxHeap.add(minHeap.poll());
            }

            sb.append(maxHeap.peek() + "\n");
        }
        System.out.print(sb);
    }
}